package com.earth.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class EarthSplash extends Activity
{
	
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        process();
    }
    
	
	private void process() 
	{	   
		new Handler().postDelayed(new Runnable()
	   {
		    public void run()
		    {
		    	finish();
		    	startActivity(new Intent(EarthSplash.this,InputQuake.class));		    
		    }		   
	   }, 3000);	
	}
}