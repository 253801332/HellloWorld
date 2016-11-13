package com.qj.weather.list;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.widget.Toast;

import com.qj.weather.adapter.StreamTools;
import com.qj.weather.bean.Weather;
import com.qj.weather.bean.Weather.Data;
import com.qj.weather.bean.Weather.Data.Datainfo;

public class WeatherList {
	private Weather.Data.Datainfo wd;
	private ArrayList<Weather.Data.Datainfo> list;
	private Weather we=new Weather();
	private Data da=we.new Data();
	private Context context;

	public WeatherList(Context context) {
		super();
		this.context = context;
	}


	public  ArrayList<Weather.Data.Datainfo> getList(String name){
	
	list=new ArrayList<Weather.Data.Datainfo>();
		try {
			String path="http://wthrcdn.etouch.cn/weather_mini?city="+URLEncoder.encode(name,"utf-8");
			URL url=new URL(path);
			HttpURLConnection conn=(HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");

			int code=conn.getResponseCode();
			//System.out.println(code);
			if (code==200) {
				InputStream in=conn.getInputStream();
				String json1=StreamTools.readStream(in);
				JSONObject json=new JSONObject(json1);
				String x1=json.getString("desc");
				System.out.println(x1);
				String []arr={"今天","明天","后天"};
				if ("OK".equals(x1)) {
					JSONObject x2=json.getJSONObject("data");
					//System.out.println(x2.toString());
					JSONArray x3=x2.getJSONArray("forecast");				
					for(int i=0;i<3;i++){
						wd=da.new Datainfo();
						JSONObject x4=(JSONObject)x3.get(i);						
						wd.date=arr[i]+"    "+x4.getString("date").substring(2);
						wd.fengli=x4.getString("fengli");
						wd.fengxiang=x4.getString("fengxiang");
						wd.type=x4.getString("type");
						wd.high=x4.getString("high");
						wd.low=x4.getString("low");					
						list.add(wd);
						//System.out.println(list);
					}		
				}else {
					Toast.makeText(context, "出错了吧", 0).show();
				}		
			 }	
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;	
	}	
}

