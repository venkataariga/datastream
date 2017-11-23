package com.solace.connector;

import java.io.Serializable;
import java.lang.reflect.Field;

public class Pojo implements Serializable{
	
	@Override
	public String toString() {
		return "Pojo [name=" + name + ", id=" + id + "]";
	}


	String name;
	
	String id;


//public String getName() {
//		return name;
//	}
//
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//
//	public String getId() {
//		return id;
//	}
//
//
//	public void setId(String id) {
//		this.id = id;
//	}


public void set(String name,String value) {
		
		Field field= null;
		try {
			field = getClass().getDeclaredField(name);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			field.set(this, value);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
