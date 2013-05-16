package com.earth.demo;

import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InputQuake extends Activity
{
	public Button submit,clear;	
	public EditText EditNorth,EditSouth,EditEast,EditWest;
	public TextView Linker;
	
 public void onCreate(Bundle savedInstanceState)
 {
	 super.onCreate(savedInstanceState);
	 setContentView(R.layout.input_quake);
	 init();
	 process();
 }

 private void process() 
 {
	clear.setOnClickListener(new View.OnClickListener()
	{
		public void onClick(View v) 
		{
			ClearEditText();
		}
	});
	
	submit.setOnClickListener(new View.OnClickListener()
	{
		public void onClick(View v) 
		{
			if(Validate())
			{
				finish();
				Bundle b = new Bundle();
				b.putString("north",EditNorth.getText().toString());
				b.putString("south",EditSouth.getText().toString());
				b.putString("east",EditEast.getText().toString());
				b.putString("west",EditWest.getText().toString());
				startActivity(new Intent(InputQuake.this,LoadEarthQuakeDetails.class).putExtras(b));
			}
		}
	});
	
	Pattern p = Pattern.compile("tamilcpu.blogspot.com");
	Linkify.addLinks(Linker,p,"http://");
 }

 private void init() 
 {
	submit = (Button) findViewById(R.id.Submit);
	clear = (Button) findViewById(R.id.Clear);
	
	Linker = (TextView) findViewById(R.id.goog);
	EditNorth = (EditText) findViewById(R.id.NorthEdit);
	EditSouth = (EditText) findViewById(R.id.SouthEdit);
	EditEast = (EditText) findViewById(R.id.EastEdit);
	EditWest = (EditText) findViewById(R.id.WestEdit);
 }
 
 private void ClearEditText() 
 {
	EditNorth.setText("");
	EditSouth.setText("");
	EditEast.setText("");
	EditWest.setText("");
 }
 
 private boolean Validate()
 {
	boolean Input_Flag=true;	
	  if((EditNorth.getText().toString().contentEquals(""))||(EditSouth.getText().toString().contentEquals(""))||(EditEast.getText().toString().contentEquals(""))||(EditWest.getText().toString().contentEquals("")))
	  {
		  Input_Flag=false;
		  new AlertDialog.Builder(InputQuake.this) 
		  .setTitle("US Earthquake Details")
		  .setMessage("Input Required...")
		  .setNeutralButton("OK", new DialogInterface.OnClickListener()
		  {
			public void onClick(DialogInterface dialog, int which) 
			{			  	
			}
		}).show();		  
	  }  
	 return Input_Flag;
 }
}
