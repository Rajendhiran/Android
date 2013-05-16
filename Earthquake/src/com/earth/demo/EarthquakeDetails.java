package com.earth.demo;

public class EarthquakeDetails
{
  public String EarthQuake_Name,Magnitude,Longitude,Source,DateTime,Depth,Latitude;
  
  public String getEarthQuake_Name() 
  {
	return EarthQuake_Name;
   }

public void setEarthQuake_Name(String earthQuake_Name) 
{
	EarthQuake_Name = earthQuake_Name;
}

public String getMagnitude() {
	return Magnitude;
}

public void setMagnitude(String magnitude) {
	Magnitude = magnitude;
}

public String getLongitude() {
	return Longitude;
}

public void setLongitude(String longitude) {
	Longitude = longitude;
}

public String getSource() {
	return Source;
}

public void setSource(String source) {
	Source = source;
}

public String getDateTime() {
	return DateTime;
}

public void setDateTime(String dateTime) {
	DateTime = dateTime;
}

public String getDepth() {
	return Depth;
}

public void setDepth(String depth) {
	Depth = depth;
}

public String getLatitude() {
	return Latitude;
}

public void setLatitude(String latitude) {
	Latitude = latitude;
}

public EarthquakeDetails()
  {
	  EarthQuake_Name="";
	  Magnitude="";
	  Longitude="";
	  Source="";
	  DateTime="";
	  Depth="";
	  Latitude="";
  }
  
  public EarthquakeDetails(String EarthQuake_Name, String Magnitude, String Longitude, String Source, String DateTime, String Depth, String Latitude)  
  {
	  this.EarthQuake_Name=EarthQuake_Name;
	  this.Magnitude=Magnitude;
	  this.Longitude=Longitude;
	  this.Source=Source;
	  this.DateTime=DateTime;
	  this.Depth=Depth;
	  this.Latitude=Latitude;
  }
}
