package com.earth.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailView extends Activity
{
 Button Detail_Home_Button,Content_View_Button,Map_View_Button;
 View Data_View;
 TextView eqname,rictar,news,date,time,depth,latitude,longitude;
 LayoutInflater factory;
 LinearLayout layout;
 String DateTime [];
 Bundle b;

 public void onCreate(Bundle savedInstanceState)
 {
	 super.onCreate(savedInstanceState);
	 setContentView(R.layout.detail_view);
	 init();
	 process();
 }
 
 private void process() 
 {
	 ContentViewPopulate();
	 Detail_Home_Button.setOnClickListener(new View.OnClickListener() 
	 {
		public void onClick(View v) 
		{
			finish();
			startActivity(new Intent(DetailView.this,InputQuake.class));
		}
	});
	 
	 Content_View_Button.setOnClickListener(new View.OnClickListener() 
	 {		 
		public void onClick(View v) 
		{
			ContentViewPopulate();
		}
	 });
	 
	 
	 Map_View_Button.setOnClickListener(new View.OnClickListener() 
	 {
		public void onClick(View v) 
		{
			//layout.removeAllViews();
			//Data_View = factory.inflate(R.layout.mapview, null);
			//layout.addView(Data_View);
			Intent i = new Intent(DetailView.this,SampleMap.class);
			i.putExtras(b);
			startActivity(i);			
		}
	 });
 }
 
 
 private void init() 
 {
	 
	 Detail_Home_Button = (Button) findViewById(R.id.detailhome);
	 Content_View_Button = (Button) findViewById(R.id.contentButton);
	 Map_View_Button = (Button) findViewById(R.id.mapButton);	 	 
	 b=getIntent().getExtras();
	 factory = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  	 layout = (LinearLayout) findViewById(R.id.viewcontent);  	 
 } 
 
 private void ContentViewPopulate()
 {
		layout.removeAllViews();
		Data_View = factory.inflate(R.layout.contentview,null);
		
		 eqname = (TextView) Data_View.findViewById(R.id.quakevalue);
		 eqname.setText(b.getString("EQname").toUpperCase());
		 
		 rictar = (TextView) Data_View.findViewById(R.id.rictarscalevalue);
		 rictar.setText(b.getString("Magnitude"));
		 
		 news = (TextView) Data_View.findViewById(R.id.sourcevalue);
		 news.setText(b.getString("Source").toUpperCase());
		 
		 String d = b.getString("Date");
		 DateTime=d.split(" ");
		 
		 date = (TextView) Data_View.findViewById(R.id.datevalue);
		 date.setText(DateTime[0]);
		 
		 time = (TextView) Data_View.findViewById(R.id.timevalue);
		 time.setText(DateTime[1]);
		 
		 depth = (TextView) Data_View.findViewById(R.id.depthvalue);
		 depth.setText(b.getString("Depth"));
		 
		 latitude = (TextView) Data_View.findViewById(R.id.lativalue);
		 latitude.setText(b.getString("Latitude"));

		 
		 longitude = (TextView) Data_View.findViewById(R.id.longivalue);
		 longitude.setText(b.getString("Longitude"));
		 
		layout.addView(Data_View);			
 }
 
}
