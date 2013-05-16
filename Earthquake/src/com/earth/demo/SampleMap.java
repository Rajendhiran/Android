package com.earth.demo;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class SampleMap extends MapActivity 
{
	public MapView mv;
	public LinearLayout layout;
	public MapController mapcontrol;
	public GeoPoint Gpoint;
	double lat,lon;
	Bundle b;
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapping);
        
        mv = (MapView) findViewById(R.id.map);        
        layout =(LinearLayout) findViewById(R.id.MapZoomWorks);
        View zoomView = mv.getZoomControls();
        layout.addView(zoomView);       
        mv.displayZoomControls(true);
        
       b= getIntent().getExtras();
        lon =Double.parseDouble(b.getString("Longitude"));        
        lat = Double.parseDouble(b.getString("Latitude"));
               
        Gpoint = new GeoPoint((int)(lon*1E6),(int)(lat*1E6));
        mapcontrol = mv.getController();
        
        mapcontrol.animateTo(Gpoint);
        //mapcontrol.setZoom(17);
        mv.invalidate();
    }

	protected boolean isRouteDisplayed()
	{
		return false;
	}
}