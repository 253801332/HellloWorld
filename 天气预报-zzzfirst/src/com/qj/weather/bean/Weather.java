package com.qj.weather.bean;

import java.util.List;

public class Weather {
	public Data data;
	public String desc;
	public int status;
	public class Data{
		public String  aqi;
		public String  city;
		public List<Datainfo> forecast;		
		public class Datainfo{
			public String date;
			public String fengli;
			public String fengxiang;
			public String high;
			public String low;
			public String type;
			@Override
			public String toString() {
				return  date+ "\r\n"
					  +"风力:  " + fengli+"         \t"
					  +"风向:  " + fengxiang +"\r\n"
					  +"最高温度:  " + high.substring(2)+"     " 
					  +"最低温度:  " + low.substring(2)+"\r\n" 
					  +"天气情况:  " + type;
			}	
			
		}
		@Override
		public String toString() {
			return "Data [aqi=" + aqi + ", city=" + city + ", forecast="
					+ forecast + "]";
		}	
		
	}
}
