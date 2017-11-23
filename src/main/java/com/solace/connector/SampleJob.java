package com.solace.connector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.json.JSONArray;
import org.json.JSONObject;

public class SampleJob {
	public static void main(String[] args) throws InterruptedException {
		
		try {
		SparkConf sparkConf = new SparkConf().setAppName("Test").setMaster("local[2]");
	   JavaStreamingContext ssc = new JavaStreamingContext(sparkConf, new Duration(1000));
	    JavaReceiverInputDStream<String> 	lines = ssc.receiverStream(
							new JavaCustomReceiver("localhost",9999));
	    
	    
		  JavaDStream<String> filteredData = lines.filter(new Function<String,Boolean>(){
				public Boolean call(String str) {
					try {
						//[{"name":"siva","id":"544"},{"name":"venkat","id":"555"}]
						//[{"name":"venkat","id":555 }]
						JSONArray array = new JSONArray(str);
						System.out.println("JSON Array:"+array);
					return true;
				}catch(Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			});
		  filteredData.print();
		  
		  JavaDStream<Pojo> pojoStreamedData = 
				  filteredData.flatMap(new FlatMapFunction<String,Pojo>(){
					  
					  public Iterator<Pojo> call(String str) {
						  List<Pojo> featurePhoneList =  new ArrayList<Pojo>();
						  JSONArray array = null;
						try {
							array = new JSONArray(str);
						  for (int i = 0; i < array.length(); i++) {
					      Pojo pojo = new Pojo();
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
						//	  System.out.println("Pojo"+pojo);
							  featurePhoneList.add(pojo);
						//	  System.out.println("List:"+featurePhoneList);
							 
						}
						  } catch(Exception e) {
							  System.out.println("Exception occured :"+e.getMessage());
						  }  
						  
						return featurePhoneList.iterator();
					  }	  
				  });
		  pojoStreamedData.print();
		  
		  pojoStreamedData.foreachRDD(new VoidFunction<JavaRDD<Pojo>>() {
				
				public void call(JavaRDD<Pojo> rdd) throws Exception{
					System.out.println("Called these many times");
					List<Pojo> list = rdd.collect();
					System.out.println("list"+list);
					SQLContext sqlCon = JavaSQLContextSingleton.getInstance(rdd.context());
					System.out.println("sqlcon:"+sqlCon);
				
					Dataset<Row> frame = sqlCon.createDataFrame(rdd, Pojo.class);
					System.out.println("Frame:"+frame.count());			
				//	frame.write().parquet("data.parquet");
					frame.write().mode(SaveMode.Append).parquet("data.parquet");
				
				
				}
			});
		  
		  
		  
		  
			ssc.start();
			ssc.awaitTermination();
			ssc.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	

}
