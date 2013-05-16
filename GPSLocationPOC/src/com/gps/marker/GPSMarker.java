package com.gps.marker;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

public class GPSMarker extends MapActivity 
{
	private LocationManager myLocationManager;
	private LocationListener myLocationListener;
	private TextView myLongitude, myLatitude;
	public ProgressDialog myProgress;
	private MapView myMapView;
	public GeoPoint myGeoPoint;
	public GeoPoint myGeoPoint1;
	private MapController myMapController;
	public double dist=0f;
	public Bitmap bmp=null;
	
	private void CenterLocatio(GeoPoint centerGeoPoint)
	{
		myLongitude.setText("Current Longitude: "+String.valueOf((float)centerGeoPoint.getLongitudeE6()/1E6));
		myLatitude.setText("Current Latitude: "+String.valueOf((float)centerGeoPoint.getLatitudeE6()/1E6+"\nDistance from Pondicherry: "+dist/1000+" Kms"));
		myProgress.dismiss();
	}

	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		myMapView = (MapView)findViewById(R.id.maps);
		myLongitude = (TextView)findViewById(R.id.longitude);
		myLatitude = (TextView)findViewById(R.id.latitude);

		myProgress = new ProgressDialog(GPSMarker.this);
		myProgress.setTitle("GPS Marker");
		myProgress.setMessage("GPS Position is Searching...");
		myProgress.setCancelable(false);
		myProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);				
		myProgress.show();
		myLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		
		if ( !myLocationManager.isProviderEnabled( LocationManager.NETWORK_PROVIDER ) ) {
	        buildAlertMessageNoGps();
	    }
		
		myLocationListener = new MyLocationListener();
		/*myProgress = new ProgressDialog(GPSMarker.this);
		myProgress.setTitle("GPS Marker");
		myProgress.setMessage("GPS Position is Searching...");
		myProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);				
		myProgress.show();*/
		
		
		myLocationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 
                1000, 
                1,
                new MyLocationListener());		

				  
		//myLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000,10f,myLocationListener);



		//Get the current argLocation in start-up
		// GeoPoint initGeoPoint = new GeoPoint		
		//	   ((int)(myLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude()*1E6),
		//   (int)(myLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude()*1E6));		  
		// CenterLocatio(initGeoPoint);		 
	}



	private  class MyLocationListener implements LocationListener
	{	  
		public void onProviderDisabled(String provider)
		{
			Toast.makeText(GPSMarker.this,"GPS Disabled",Toast.LENGTH_LONG).show();
			Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			startActivity(intent);
		}

		public void onProviderEnabled(String provider) 
		{
			Toast.makeText(GPSMarker.this,"GPS Enabled",Toast.LENGTH_LONG).show();			
		}

		public void onStatusChanged(String provider,int status, Bundle extras) 
		{
			Toast.makeText(GPSMarker.this,"Location Status Changed",Toast.LENGTH_LONG).show();
		}
	
		public void onLocationChanged(Location location)
		{			
			
			Criteria hdCrit = new Criteria();
			hdCrit.setAccuracy(Criteria.ACCURACY_COARSE);

			myGeoPoint = new GeoPoint(
					(int)(location.getLatitude()*1E6),
					(int)(location.getLongitude()*1E6));
			String str = "\n CurrentLocation: "+
			"\n Latitude: "+ location.getLatitude() + 
			"\n Longitude: " + location.getLongitude() + 
			"\n Accuracy: " + location.getAccuracy();

			//double startLatitude=location.getLatitude();
			//double startLongitude =location.getLongitude();
			double endLatitude=11.938173;
			double endLongitude=79.784463;
			
			/*Location lstart = new Location("PointA");
			lstart.setLatitude(startLatitude);
			lstart.setLongitude(startLongitude);*/
			
			Location lend = new Location("PointB");
			lend.setLatitude(endLatitude);
			lend.setLongitude(endLongitude);
			
			dist = location.distanceTo(lend);
			Toast.makeText(GPSMarker.this, "Distance is "+dist, Toast.LENGTH_LONG).show();							
			myMapController = myMapView.getController();

			myMapView.setSatellite(true); //Set satellite view
			myMapView.displayZoomControls(true);
			myMapController.setZoom(20); //Fixed Zoom Level
			myMapView.showContextMenu();
			myMapController.animateTo(myGeoPoint);
			myMapView.invalidate(); 			
			
			bmp = BitmapFactory.decodeResource(getResources(),R.drawable.mark);
			MyLocationOverlay myLocationOverlay = new MyLocationOverlay();
			List<Overlay> list = myMapView.getOverlays();
			list.add(myLocationOverlay);

			Toast.makeText(GPSMarker.this,str,Toast.LENGTH_LONG).show();
			
			
			/*// Pondicherry Location
			myGeoPoint1 = new GeoPoint((int)(endLatitude*1E6),(int)(endLongitude*1E6));
			myMapController = myMapView.getController();
			myMapView.setSatellite(true); //Set satellite view
			myMapView.displayZoomControls(true);
			myMapController.setZoom(20); //Fixed Zoom Level
			myMapView.showContextMenu();
			myMapController.animateTo(myGeoPoint1);
			myMapView.invalidate(); 			
			
			bmp = BitmapFactory.decodeResource(getResources(),R.drawable.greemark);
			MyLocationOverlay myLocationOverlay1 = new MyLocationOverlay();			
			list.add(myLocationOverlay1);*/
			
			
			CenterLocatio(myGeoPoint);			
		}
	}	 
	protected boolean isRouteDisplayed() 
	{	  
		return false;
	}

	private class MyLocationOverlay extends com.google.android.maps.Overlay 
	{
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow,long when) 
		{

			super.draw(canvas, mapView, shadow);
			Paint paint = new Paint();
			// Converts lat/lng-Point to OUR coordinates on the screen.
			Point myScreenCoords = new Point();
			mapView.getProjection().toPixels(myGeoPoint, myScreenCoords);
			// paint.setStrokeWidth();
			// paint.setARGB(255, 255, 255, 255);
			paint.setColor(Color.BLUE);
			paint.setTextSize(20);
			paint.setStyle(Paint.Style.FILL);
			
			canvas.drawBitmap(bmp, myScreenCoords.x, myScreenCoords.y, paint);
			canvas.drawText("Now ur here!.",myScreenCoords.x, myScreenCoords.y, paint);
			return true;
		}
	}
	private void buildAlertMessageNoGps() {
	    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    builder.setMessage("Yout GPS seems to be disabled, do you want to enable it?")
	           .setCancelable(false)
	           .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	               public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
	                   startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
	               }
	           })
	           .setNegativeButton("No", new DialogInterface.OnClickListener() {
	               public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
	                    dialog.cancel();
	               }
	           });
	    final AlertDialog alert = builder.create();
	    alert.show();
	}
}
