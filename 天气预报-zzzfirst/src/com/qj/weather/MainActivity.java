package com.qj.weather;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qj.weather.bean.Weather;
import com.qj.weather.list.WeatherList;

public class MainActivity extends Activity implements OnClickListener{
	protected static final int SUCCESS = 1;
	protected static final int ERROR = 2;
	private EditText et;
	private Button bt;
	private ProgressDialog pd; 
	private TextView  tv;
	private ArrayList<Weather.Data.Datainfo> list;
	private WeatherList wl;
	private Weather.Data.Datainfo wd;
	private ListView lv;	
	private String name;
	private Handler handle=new Handler(){		
		public void handleMessage(Message msg) {
			pd.dismiss();
			switch (msg.what) {
			case SUCCESS:
				lv.setAdapter(new MyAdapter());				
				break;
			case ERROR:
				Toast.makeText(MainActivity.this, "出错了", 0).show();
				break;
			}	
		};
	};	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et=(EditText) findViewById(R.id.et);
        bt=(Button) findViewById(R.id.bt);
        bt.setOnClickListener(this);   
        lv=(ListView) findViewById(R.id.lv);      
        wl=new WeatherList(MainActivity.this);
    }  
	@Override
	public void onClick(View v) {
		name=et.getText().toString().trim();
		if (TextUtils.isEmpty(name)) {
			Toast.makeText(this, "请输入路径", 0).show();
			return;
		}
		pd = new ProgressDialog(this);
		//pd.setTitle("提醒");
		pd.setMessage("亲，天气状况加载中");
		pd.show();
		
		new Thread(){
			public void run() {
				try {
					list=wl.getList(name);
					//System.out.println(list.toString());
					Message msg=Message.obtain();
					msg.what=SUCCESS;
					//msg.obj=list;
					handle.sendMessage(msg);			
				} catch (Exception e) {
					Message msg=Message.obtain();
					msg.what=ERROR;
					handle.sendMessage(msg);
					e.printStackTrace();
				}
			};		
		}.start();
	}
	public class MyAdapter extends BaseAdapter {
	
		@Override
		public int getCount() {
			return list.size();
		}
		@Override
		public Object getItem(int position) {
			return null;
		}
		@Override
		public long getItemId(int position) {
			return 0;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view=new View(MainActivity.this);
			wd=list.get(position);
			System.out.println(wd);
			view=View.inflate(MainActivity.this, R.layout.item, null);
			tv = (TextView) view.findViewById(R.id.tv);
			tv.setText(wd.toString());	
			return view;		
		}
	}
}
