package com.app.poc;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class POCApp extends Activity 
{
	public Button TimeBtn;	
	public TextView Time;
	MyCounter timer;
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
		process();
	}

	/**
	 *   @author Rajendhiran. E
	 *   Jan 16, 20134:46:25 PM
	 */
	class MyCounter extends CountDownTimer
	{
		SimpleDateFormat mSimpleDateFormat;
	   public MyCounter(long millisInFuture, long countDownInterval) 
	   {
	       super(millisInFuture, countDownInterval);
	       mSimpleDateFormat= new SimpleDateFormat("HH:mm:ss");
	       mSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	   }

	   @Override
	   public void onFinish() 
	   {
	      Log.d("Timer Completed: ","Completed!");
	       Time.setText("00:00:00");            
	   }

	   @Override
	   public void onTick(long millisUntilFinished)
	   {
		 Time.setText(mSimpleDateFormat.format(millisUntilFinished));	    
	   }
	}
	
	private void process() 
	{		
		TimeBtn.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg0) 
			{		
				if(timer!=null)
				  timer.cancel();
				timer = new MyCounter(3800*1000,1000);
				timer.start();						
			}
		});
						
	}
	/**
	 *   @author Rajendhiran. E
	 *   Jan 16, 20134:46:24 PM
	 */
	private void init() 
	{		
		TimeBtn = (Button) findViewById(R.id.TimeBtn);
		Time = (TextView) findViewById(R.id.countdown);
	}
}