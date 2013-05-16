package com.earth.demo;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONQuakehandler 
{
   public ArrayList<EarthquakeDetails> getContent(JSONObject jobject) throws JSONException
   {
	   final String JSON_ARRAY_NAME="earthquakes";
	   JSONArray quakes = jobject.getJSONArray(JSON_ARRAY_NAME);	   
	   ArrayList<EarthquakeDetails> Data = new ArrayList<EarthquakeDetails>();
	   for(int i=0;i<quakes.length();i++)
	   {
		   JSONObject DataObject = quakes.getJSONObject(i);	  
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
	   }
	   
	return Data;	
   }
}
