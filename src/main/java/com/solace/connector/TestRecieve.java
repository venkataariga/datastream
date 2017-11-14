package com.solace.connector;


import java.io.InputStream;

import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Pattern;


import org.apache.spark.SparkConf;

import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;


import scala.Tuple2;

public class TestRecieve {

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
		
		  JavaDStream<String> words = lines.flatMap(x -> Arrays.asList(SPACE.split(x)).iterator());
		  JavaPairDStream<String, Integer> wordCounts = words.mapToPair(s -> new Tuple2<>(s, 1))
			        .reduceByKey((i1, i2) -> i1 + i2);		
	

		// Path workingDir=hdfs.getWorkingDirectory();
		lines.dstream().saveAsTextFiles(prop.getProperty("spark.hdfs.path"), prop.getProperty("spark.hdfs.fileName"));
	
				ssc.start();
				ssc.awaitTermination();
				ssc.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		

	}
}
