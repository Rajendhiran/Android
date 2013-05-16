package com.earth.demo;
import java.util.ArrayList;
import org.json.JSONObject;
import com.earth.webservice.JSONFormat;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class LoadEarthQuakeDetails extends Activity
{
	Button EQHome;
	ListView QuakeList;
	CustomAdapter adapter;
	public static final String URL="http://api.geonames.org/earthquakesJSON";
	public static final String JSON_ARRAY_NAME="earthquakes";
	public ArrayList<EarthquakeDetails> Data;
 public void onCreate(Bundle savedInstanceState)
 {
	 super.onCreate(savedInstanceState);
	 setContentView(R.layout.earthquake_list);
	 init();
	 process();
 }
 
 
 private void process() 
 {	 
	 Data = new ArrayList <EarthquakeDetails>();	 	 
	 Bundle b = getIntent().getExtras();
	 try
	 {
	   JSONObject jobject = JSONFormat.JSONFromURL(URL, b);
	   JSONQuakehandler qHandler = new JSONQuakehandler();
	   Data = qHandler.getContent(jobject);
	   
	   /*JSONArray quakes = jobject.getJSONArray(JSON_ARRAY_NAME);
	   EarthquakeDetails equake;
	   for(int i=0;i<quakes.length();i++)
	   {
		   JSONObject DataObject = quakes.getJSONObject(i);
		   equake = new EarthquakeDetails();
		   equake.setDateTime(DataObject.getString("datetime"));
		   Data.add(equake);
		   
		   Data.add(
				   new EarthquakeDetails(
						   DataObject.getString("eqid"),
						   DataObject.getString("magnitude"),
						   DataObject.getString("lng"),
						   DataObject.getString("src"),
						   DataObject.getString("datetime"),
						   DataObject.getString("depth"),
						   DataObject.getString("lat")));	
		   
		 //  Log.d("Earthquake "+(i+1), ""+DataObject.getString("eqid"));
	   }*/
	   
	   	adapter = new CustomAdapter(LoadEarthQuakeDetails.this,R.layout.list_earth,Data);
	   	QuakeList.setAdapter(adapter);

	   	QuakeList.setOnItemClickListener(new OnItemClickListener() 
	   	{
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			{			
				Bundle b = new Bundle();
				b.putString("EQname", Data.get(position).EarthQuake_Name);				
				b.putString("Magnitude",Data.get(position).Magnitude);
				b.putString("Longitude", Data.get(position).Longitude);
				b.putString("Source", Data.get(position).Source);
				b.putString("Date", Data.get(position).DateTime);
				b.putString("Depth",Data.get(position).Depth);
				b.putString("Latitude",Data.get(position).Latitude);
				startActivity(new Intent(LoadEarthQuakeDetails.this,DetailView.class).putExtras(b));
			}
		});
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 }
	 
	 EQHome.setOnClickListener(new View.OnClickListener()
	 {
		public void onClick(View v) 
		{
			finish();
			startActivity(new Intent(LoadEarthQuakeDetails.this,InputQuake.class));
		}
	});
 }
 
 private void init() 
 {
	EQHome = (Button) findViewById(R.id.eqhome);
	QuakeList = (ListView) findViewById(R.id.ListDetail);
 }
 
 
 private class CustomAdapter extends BaseAdapter
 {
	 Context ctx;
	 Holder h;
	 ArrayList<EarthquakeDetails> QuakeContent = new ArrayList<EarthquakeDetails>();
 	 LayoutInflater factory;
 	 int row;
	 
	public CustomAdapter(Context ctx,int res,ArrayList<EarthquakeDetails> obj)
	{
		this.ctx=ctx;
	    QuakeContent=obj;
	    row =res;
	}
	
	public int getCount() 
	{
		return QuakeContent.size();		
	}

	public Object getItem(int position) 
	{
	  //	return QuakeContent.get(position);
		 return null;
	}

	public long getItemId(int position) 
	{
		//return position;
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) 
	{
		if(convertView==null)
		{
			h= new Holder();
			factory=(LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = factory.inflate(row,null);
			h.EQName = (TextView) convertView.findViewById(R.id.eqnamevalue);
			h.Rictar = (TextView) convertView.findViewById(R.id.rictarvalue);
			convertView.setTag(h);
		}
		else
		{
			h=(Holder) convertView.getTag();
		}		
		
			h.EQName.setText(QuakeContent.get(position).EarthQuake_Name.toUpperCase());
			h.Rictar.setText(QuakeContent.get(position).Magnitude);
		return convertView;
	}
	
	private class Holder
	{
		TextView EQName;
		TextView Rictar;
	}	 
 }
}
