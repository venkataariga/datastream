package com.solace.connector;

import java.io.Serializable;
import java.lang.reflect.Field;

public class FeaturePhonePojo implements Serializable{
	
	/**
	 * 
	 */

	String Name="";
	String DT = "";
	String DI1 = "";
	String DI2 = "";
	String DI3 = "";
	String DI4 = "";
	String DI5 ="";
	String LI1 = "";
	String LI2  = "";
String LI3 = "";
	@Override
	public String toString() {
		return "FeaturePhonePojo [Name=" + Name + ", DT=" + DT + ", DI1=" + DI1 + ", DI2=" + DI2 + ", DI3=" + DI3
				+ ", DI4=" + DI4 + ", DI5=" + DI5 + ", LI1=" + LI1 + ", LI2=" + LI2 + ", LI3=" + LI3 + ", SI1=" + SI1
				+ ", RI1=" + RI1 + ", VI2=" + VI2 + ", LI5=" + LI5 + ", LI6=" + LI6 + ", NI1=" + NI1 + ", LI4=" + LI4
				+ ", SI2=" + SI2 + ", SI3=" + SI3 + ", TI1=" + TI1 + ", TI2=" + TI2 + ", RI2=" + RI2 + ", RI3=" + RI3
				+ ", RI4=" + RI4 + ", RI6=" + RI6 + ", RI8=" + RI8 + ", RI10=" + RI10 + ", RI11=" + RI11 + ", RI12="
				+ RI12 + ", RI13=" + RI13 + ", RI14=" + RI14 + ", RI15=" + RI15 + ", NI4=" + NI4 + ", VI1=" + VI1
				+ ", VI3=" + VI3 + ", VI4=" + VI4 + ", NC1=" + NC1 + ", NC2=" + NC2 + ", NC3=" + NC3 + ", NC4=" + NC4
				+ ", NC5=" + NC5 + ", NC6=" + NC6 + ", NC7=" + NC7 + ", NC8=" + NC8 + ", NC9=" + NC9 + ", PI1=" + PI1
				+ ", PI2=" + PI2 + ", PI3=" + PI3 + ", BI1=" + BI1 + ", WI1=" + WI1 + ", WI2=" + WI2 + ", WI3=" + WI3
				+ ", WI4=" + WI4 + ", DC1=" + DC1 + ", HI1=" + HI1 + ", HI2=" + HI2 + ", LI7=" + LI7 + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	String SI1 = "";
	String RI1  = "";
	String VI2 = "";
	String LI5 = "";
	String LI6 = "";
	String NI1 = "";
	String LI4 = "";
	String SI2 = "";
	String SI3="";
	String TI1 = "";
	String TI2 = "";
	String RI2 = "";
	String RI3 = "";
	String RI4 = "";
	String RI6 = "";
	String RI8 = "";
	String RI10 = "";
	String RI11 = "";
	String RI12 = "";
	String RI13 = "";
	String RI14 = "";
	String RI15 = "";
	String NI4 = "";
	String VI1 = "";
	String VI3 = "";
	String VI4 = "";
	String NC1 = "";
	String NC2 = "";
	String NC3 = "";
	String NC4 = "";
	String NC5 = "";
	String NC6 = "";
	String NC7 = "";
	String NC8 = "";
	String NC9 = "";
	String PI1 = "";
	String PI2 = "";
	String PI3 = "";
	String BI1 = "";
	String WI1 = "";
	String WI2 = "";
	String WI3 = "";
	String WI4 = "";
	String DC1 = "";
	String HI1 = "";
	String HI2 = "";
	
	String LI7 = "";
	
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

	public String getDI2() {
		return DI2;
	}

	public void setDI2(String dI2) {
		DI2 = dI2;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDT() {
		return DT;
	}

	public void setDT(String dT) {
		DT = dT;
	}

	public String getDI1() {
		return DI1;
	}

	public void setDI1(String dI1) {
		DI1 = dI1;
	}

	public String getDI3() {
		return DI3;
	}

	public void setDI3(String dI3) {
		DI3 = dI3;
	}

	public String getDI4() {
		return DI4;
	}

	public void setDI4(String dI4) {
		DI4 = dI4;
	}

	public String getDI5() {
		return DI5;
	}

	public void setDI5(String dI5) {
		DI5 = dI5;
	}

	public String getLI1() {
		return LI1;
	}

	public void setLI1(String lI1) {
		LI1 = lI1;
	}

	public String getLI2() {
		return LI2;
	}

	public void setLI2(String lI2) {
		LI2 = lI2;
	}

	public String getLI3() {
		return LI3;
	}

	public void setLI3(String lI3) {
		LI3 = lI3;
	}

	public String getSI1() {
		return SI1;
	}

	public void setSI1(String sI1) {
		SI1 = sI1;
	}

	public String getRI1() {
		return RI1;
	}

	public void setRI1(String rI1) {
		RI1 = rI1;
	}

	public String getVI2() {
		return VI2;
	}

	public void setVI2(String vI2) {
		VI2 = vI2;
	}

	public String getLI5() {
		return LI5;
	}

	public void setLI5(String lI5) {
		LI5 = lI5;
	}

	public String getLI6() {
		return LI6;
	}

	public void setLI6(String lI6) {
		LI6 = lI6;
	}

	public String getNI1() {
		return NI1;
	}

	public void setNI1(String nI1) {
		NI1 = nI1;
	}

	public String getLI4() {
		return LI4;
	}

	public void setLI4(String lI4) {
		LI4 = lI4;
	}

	public String getSI2() {
		return SI2;
	}

	public void setSI2(String sI2) {
		SI2 = sI2;
	}

	public String getSI3() {
		return SI3;
	}

	public void setSI3(String sI3) {
		SI3 = sI3;
	}

	public String getTI1() {
		return TI1;
	}

	public void setTI1(String tI1) {
		TI1 = tI1;
	}

	public String getTI2() {
		return TI2;
	}

	public void setTI2(String tI2) {
		TI2 = tI2;
	}

	public String getRI2() {
		return RI2;
	}

	public void setRI2(String rI2) {
		RI2 = rI2;
	}

	public String getRI3() {
		return RI3;
	}

	public void setRI3(String rI3) {
		RI3 = rI3;
	}

	public String getRI4() {
		return RI4;
	}

	public void setRI4(String rI4) {
		RI4 = rI4;
	}

	public String getRI6() {
		return RI6;
	}

	public void setRI6(String rI6) {
		RI6 = rI6;
	}

	public String getRI8() {
		return RI8;
	}

	public void setRI8(String rI8) {
		RI8 = rI8;
	}

	public String getRI10() {
		return RI10;
	}

	public void setRI10(String rI10) {
		RI10 = rI10;
	}

	public String getRI11() {
		return RI11;
	}

	public void setRI11(String rI11) {
		RI11 = rI11;
	}

	public String getRI12() {
		return RI12;
	}

	public void setRI12(String rI12) {
		RI12 = rI12;
	}

	public String getRI13() {
		return RI13;
	}

	public void setRI13(String rI13) {
		RI13 = rI13;
	}

	public String getRI14() {
		return RI14;
	}

	public void setRI14(String rI14) {
		RI14 = rI14;
	}

	public String getRI15() {
		return RI15;
	}

	public void setRI15(String rI15) {
		RI15 = rI15;
	}

	public String getNI4() {
		return NI4;
	}

	public void setNI4(String nI4) {
		NI4 = nI4;
	}

	public String getVI1() {
		return VI1;
	}

	public void setVI1(String vI1) {
		VI1 = vI1;
	}

	public String getVI3() {
		return VI3;
	}

	public void setVI3(String vI3) {
		VI3 = vI3;
	}

	public String getVI4() {
		return VI4;
	}

	public void setVI4(String vI4) {
		VI4 = vI4;
	}

	public String getNC1() {
		return NC1;
	}

	public void setNC1(String nC1) {
		NC1 = nC1;
	}

	public String getNC2() {
		return NC2;
	}

	public void setNC2(String nC2) {
		NC2 = nC2;
	}

	public String getNC3() {
		return NC3;
	}

	public void setNC3(String nC3) {
		NC3 = nC3;
	}

	public String getNC4() {
		return NC4;
	}

	public void setNC4(String nC4) {
		NC4 = nC4;
	}

	public String getNC5() {
		return NC5;
	}

	public void setNC5(String nC5) {
		NC5 = nC5;
	}

	public String getNC6() {
		return NC6;
	}

	public void setNC6(String nC6) {
		NC6 = nC6;
	}

	public String getNC7() {
		return NC7;
	}

	public void setNC7(String nC7) {
		NC7 = nC7;
	}

	public String getNC8() {
		return NC8;
	}

	public void setNC8(String nC8) {
		NC8 = nC8;
	}

	public String getNC9() {
		return NC9;
	}

	public void setNC9(String nC9) {
		NC9 = nC9;
	}

	public String getPI1() {
		return PI1;
	}

	public void setPI1(String pI1) {
		PI1 = pI1;
	}

	public String getPI2() {
		return PI2;
	}

	public void setPI2(String pI2) {
		PI2 = pI2;
	}

	public String getPI3() {
		return PI3;
	}

	public void setPI3(String pI3) {
		PI3 = pI3;
	}

	public String getBI1() {
		return BI1;
	}

	public void setBI1(String bI1) {
		BI1 = bI1;
	}

	public String getWI1() {
		return WI1;
	}

	public void setWI1(String wI1) {
		WI1 = wI1;
	}

	public String getWI2() {
		return WI2;
	}

	public void setWI2(String wI2) {
		WI2 = wI2;
	}

	public String getWI3() {
		return WI3;
	}

	public void setWI3(String wI3) {
		WI3 = wI3;
	}

	public String getWI4() {
		return WI4;
	}

	public void setWI4(String wI4) {
		WI4 = wI4;
	}

	public String getDC1() {
		return DC1;
	}

	public void setDC1(String dC1) {
		DC1 = dC1;
	}

	public String getHI1() {
		return HI1;
	}

	public void setHI1(String hI1) {
		HI1 = hI1;
	}

	public String getHI2() {
		return HI2;
	}

	public void setHI2(String hI2) {
		HI2 = hI2;
	}

	public String getLI7() {
		return LI7;
	}

	public void setLI7(String lI7) {
		LI7 = lI7;
	}


}
