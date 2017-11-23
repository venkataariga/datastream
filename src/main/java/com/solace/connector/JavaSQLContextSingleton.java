package com.solace.connector;


import org.apache.spark.SparkContext;
import org.apache.spark.sql.SQLContext;


public class JavaSQLContextSingleton {
	
private static transient SQLContext instance = null; 

	public static SQLContext getInstance(SparkContext sparkConf) {
		if(instance == null) {
			instance = new SQLContext(sparkConf);
		}
		return instance;
	}

}
