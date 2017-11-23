package com.solace.connector;




import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import scala.Array;
import scala.Tuple2;


public class LearningStream {

	private static final Pattern SPACE = Pattern.compile(" ");
	public static void main(String[] args) throws InterruptedException {
		
		try {
	
		SparkConf sparkConf = new SparkConf().setAppName("JavaCustomReceiver").setMaster("local[2]");
	    Properties    prop = new Properties();
	    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	    InputStream is = classloader.getResourceAsStream("sparkprops.properties");
        prop.load(is);
	    JavaStreamingContext ssc = new JavaStreamingContext(sparkConf, new Duration(Integer.parseInt(prop.getProperty("spark.job.duration"))));
	    JavaReceiverInputDStream<String> lines;
		
	//	lines = ssc.receiverStream(
		//				new JavaCustomReceiver("localhost",9999));
		
			lines = ssc.receiverStream(
							new JMSReceiver(prop.getProperty("spark.solace.ip"), 
									prop.getProperty("spark.solace.vpn")	, 
									prop.getProperty("spark.solace.username"), 
									prop.getProperty("spark.solace.password"),
									prop.getProperty("spark.solace.queue"), 
									prop.getProperty("spark.solace.jndi"), 
									StorageLevel.MEMORY_ONLY_SER_2()));
		//	  JavaDStream<String> words = lines.flatMap(x -> Arrays.asList(SPACE.split(x)).iterator());
	
			   JavaDStream<String> filteredData = lines.filter(new Function<String,Boolean>(){
					public Boolean call(String str) {
						try {
					//    System.out.println("str"+str);
						JSONArray array = new JSONArray(str);
						System.out.println("JSON Array:"+array);
						return true;
					}catch(Exception e) {
						e.printStackTrace();
						return false;
					}
				}
				});
			   
			   JavaDStream<FeaturePhonePojo> pojoStreamedData = 
						  filteredData.flatMap(new FlatMapFunction<String,FeaturePhonePojo>(){
							  
							  public Iterator<FeaturePhonePojo> call(String str) {
								  List<FeaturePhonePojo> featurePhoneList =  new ArrayList<FeaturePhonePojo>();
								  JSONArray array = null;
								try {
									array = new JSONArray(str);
								  for (int i = 0; i < array.length(); i++) {
							FeaturePhonePojo pojo = new FeaturePhonePojo();
							JSONObject recordObject = new JSONObject(array.get(i).toString());
							     Iterator it = recordObject.keys();
							     String key = null;
									  while(it.hasNext()) {
										 key = it.next().toString();
										 try {
											 String value = recordObject.getString(key);
											 pojo.set(key, value);
										 }catch(Exception ex) {
											 System.out.println("Exception occured :"+ex.getMessage());
										 }
									  }
									  featurePhoneList.add(pojo);
									 
								}
								  } catch(Exception e) {
									  System.out.println("Exception occured :"+e.getMessage());
								  }  
								  
								return featurePhoneList.iterator();
							  }	  
						  });
					
			   pojoStreamedData.foreachRDD(new VoidFunction<JavaRDD<FeaturePhonePojo>>() {
					
					public void call(JavaRDD<FeaturePhonePojo> rdd) throws Exception{
						
						SQLContext sqlCon = JavaSQLContextSingleton.getInstance(rdd.context());
						Dataset<Row> frame = sqlCon.createDataFrame(rdd, FeaturePhonePojo.class);
						System.out.println("Frame:"+frame);
						frame.write().mode(SaveMode.Append).parquet(prop.getProperty("spark.hdfs.path"));
					}
				});
			//  JavaPairDStream<String, Integer> wordCounts = words.mapToPair(s -> new Tuple2<>(s, 1))
			//	        .reduceByKey((i1, i2) -> i1 + i2);

		//	wordCounts.print();
	    
	
				ssc.start();
				ssc.awaitTermination();
				ssc.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

