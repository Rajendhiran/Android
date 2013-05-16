package com.app.poc;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class POCApp extends Activity 
{
	public Button DiaBtn;
	public Dialog DataDialog;
	public TableLayout layout;
	public ScrollView scroll;	
	
	public ArrayList<TestBean> Data = new ArrayList<TestBean>();
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
		process();
	}

	
	private void process() 
	{
		DiaBtn.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg0) 
			{
				DataDialog = new Dialog(POCApp.this);
				DataDialog.setTitle("Test POC");
				DataDialog.setContentView(R.layout.custom_dialog);
				
				layout = (TableLayout) DataDialog.findViewById(R.id.TestLayout);
				scroll = (ScrollView) DataDialog.findViewById(R.id.testScroll);
				//scroll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
				
				TableRow tr_head = new TableRow(POCApp.this);				
				tr_head.setBackgroundColor(Color.GRAY);				
				tr_head.setLayoutParams(new LayoutParams(
				LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
				
				 TextView label_date = new TextView(POCApp.this);		         
		         label_date.setText("Year");
		         //label_date.setBackgroundResource(R.drawable.rectgrad1);
		         label_date.setTextColor(Color.WHITE);
		         label_date.setPadding(10, 10, 10, 10);
		         tr_head.addView(label_date);// add the column to the table row here

		         TextView label_jan = new TextView(POCApp.this);
		         label_jan.setText("Jan"); // set the text for the header
		         //label_jan.setBackgroundResource(R.drawable.rectgrad1);
		         label_jan.setTextColor(Color.WHITE); // set the color
		         label_jan.setPadding(10, 10, 10, 10);
		         tr_head.addView(label_jan); // add the column to the table row here
				
		         TextView label_feb = new TextView(POCApp.this);
		         label_feb.setText("Feb"); // set the text for the header
		         //label_feb.setBackgroundResource(R.drawable.rectgrad1);
		         label_feb.setTextColor(Color.WHITE); // set the color
		         label_feb.setPadding(10, 10, 10, 10);
		         tr_head.addView(label_feb); // add the column to the table row here
				
		         TextView label_mar = new TextView(POCApp.this);
		         label_mar.setText("Mar"); // set the text for the header
		         //label_mar.setBackgroundResource(R.drawable.rectgrad);
		         label_mar.setTextColor(Color.WHITE); // set the color
		         label_mar.setPadding(10, 10, 10, 10);
		         tr_head.addView(label_mar); // add the column to the table row here
		         
		         TextView label_apr = new TextView(POCApp.this);
		         label_apr.setText("Apr"); // set the text for the header
		         //label_apr.setBackgroundResource(R.drawable.rectgrad);
		         label_apr.setTextColor(Color.WHITE); // set the color
		         label_apr.setPadding(10, 10, 10, 10);
		         tr_head.addView(label_apr); // add the column to the table row here
		         
		         TextView label_may = new TextView(POCApp.this);
		         //label_may.setBackgroundResource(R.drawable.rectgrad);
		         label_may.setText("May"); // set the text for the header 
		         label_may.setTextColor(Color.WHITE); // set the color
		         label_may.setPadding(10, 10, 10, 10);
		         tr_head.addView(label_may); // add the column to the table row here
		         
		         TextView label_jun = new TextView(POCApp.this);
		         //label_jun.setBackgroundResource(R.drawable.rectgrad);
		         label_jun.setText("Jun"); // set the text for the header 
		         label_jun.setTextColor(Color.WHITE); // set the color
		         label_jun.setPadding(10, 10, 10, 10);
		         tr_head.addView(label_jun); 
		         
		         TextView label_jul = new TextView(POCApp.this);
		         //label_jul.setBackgroundResource(R.drawable.rectgrad);
		         label_jul.setText("July"); // set the text for the header 
		         label_jul.setTextColor(Color.WHITE); // set the color
		         label_jul.setPadding(10, 10, 10, 10);
		         tr_head.addView(label_jul); // add the column to the table row here
		         
		         TextView label_aug = new TextView(POCApp.this);
		         //label_aug.setBackgroundResource(R.drawable.rectgrad);
		         label_aug.setText("Aug"); // set the text for the header 
		         label_aug.setTextColor(Color.WHITE); // set the color
		         label_aug.setPadding(10, 10, 10, 10);
		         tr_head.addView(label_aug);
		         
		         TextView label_sep = new TextView(POCApp.this);
		         //label_sep.setBackgroundResource(R.drawable.rectgrad);
		         label_sep.setText("Sep"); // set the text for the header 
		         label_sep.setTextColor(Color.WHITE); // set the color
		         label_sep.setPadding(10, 10, 10, 10); // set the padding (if required)
		         tr_head.addView(label_sep);
		         
		         TextView label_oct = new TextView(POCApp.this);
		         //label_oct.setBackgroundResource(R.drawable.rectgrad);
		         label_oct.setText("Oct"); // set the text for the header 
		         label_oct.setTextColor(Color.WHITE); // set the color
		         label_oct.setPadding(10, 10, 10, 10); // set the padding (if required)
		         tr_head.addView(label_oct);
		         
		         TextView label_nov = new TextView(POCApp.this);
		         //label_nov.setBackgroundResource(R.drawable.rectgrad);
		         label_nov.setText("Nov"); // set the text for the header 
		         label_nov.setTextColor(Color.WHITE); // set the color
		         label_nov.setPadding(10, 10, 10, 10); // set the padding (if required)
		         tr_head.addView(label_nov);
		         
		         TextView label_dec = new TextView(POCApp.this);
		         //label_dec.setBackgroundResource(R.drawable.rectgrad);
		         label_dec.setText("Dec"); // set the text for the header 
		         label_dec.setTextColor(Color.WHITE); // set the color
		         label_dec.setPadding(10, 10, 10, 10); // set the padding (if required)
		         tr_head.addView(label_dec);
		         
		         layout.addView(tr_head);//, new TableLayout.LayoutParams(
		                 //LayoutParams.FILL_PARENT,
		                 //LayoutParams.WRAP_CONTENT));
		         
		         for(int i =0;i<Data.size();i++)
		         {
		        	 TableRow tr = new TableRow(POCApp.this);
		        	 tr.setBackgroundResource(R.drawable.rectgrad);
		        	 
		        	 tr.setGravity(Gravity.CENTER);
		        	 
		        	// tr.setLayoutParams(new LayoutParams(
		        			// LayoutParams.FILL_PARENT,
		        		//	 LayoutParams.WRAP_CONTENT));
		        	 
		        	 TextView YearData = new TextView(POCApp.this);
		        	 YearData.setText(Data.get(i).getYear());
		        	 YearData.setBackgroundResource(R.drawable.rectgrad);
		        	// YearData.setPadding(5, 5, 5, 5);
		        	 YearData.setTextColor(Color.WHITE);
		        	 YearData.setGravity(Gravity.CENTER);
		        	 tr.addView(YearData);
		        	 
		        	 TextView JanData = new TextView(POCApp.this);		 
		        	 JanData.setBackgroundResource(R.drawable.rectgrad);
		        	 JanData.setText(Data.get(i).getJanData());
		        	 //JanData.setPadding(5, 5, 5, 5);
		        	 JanData.setTextColor(Color.WHITE);
		        	 JanData.setGravity(Gravity.CENTER);
		        	 tr.addView(JanData);
		        	 
		        	 TextView FebData = new TextView(POCApp.this);
		        	 FebData.setBackgroundResource(R.drawable.rectgrad);
		        	 FebData.setText(Data.get(i).getFebData());
		        	// FebData.setPadding(5, 5, 5, 5);
		        	 FebData.setTextColor(Color.WHITE);
		        	 FebData.setGravity(Gravity.CENTER);
		        	 tr.addView(FebData);		        	 
		        	 
		        	 TextView MarData = new TextView(POCApp.this);
		        	 MarData.setBackgroundResource(R.drawable.rectgrad);
		        	 MarData.setText(Data.get(i).getMarchData());
		        	// MarData.setPadding(5, 5, 5, 5);
		        	 MarData.setTextColor(Color.WHITE);
		        	 MarData.setGravity(Gravity.CENTER);
		        	 tr.addView(MarData);
		        	 
		        	 TextView AprData = new TextView(POCApp.this);
		        	 AprData.setBackgroundResource(R.drawable.rectgrad);
		        	 AprData.setText(Data.get(i).getAprilData());
		        	 //AprData.setPadding(5, 5, 5, 5);
		        	 AprData.setTextColor(Color.WHITE);
		        	 AprData.setGravity(Gravity.CENTER);
		        	 tr.addView(AprData);
		        	 
		        	 
		        	 
		        	 TextView MayData = new TextView(POCApp.this);
		        	 MayData.setBackgroundResource(R.drawable.rectgrad);
		        	 MayData.setText(Data.get(i).getMayData());
		        	 //MayData.setPadding(5, 5, 5, 5);
		        	 MayData.setTextColor(Color.WHITE);
		        	 MayData.setGravity(Gravity.CENTER);
		        	 tr.addView(MayData);
		        	 
		        	 
		        	 TextView JuneData = new TextView(POCApp.this);
		        	 JuneData.setBackgroundResource(R.drawable.rectgrad);
		        	 JuneData.setText(Data.get(i).getJuneData());
		        	 //JuneData.setPadding(5, 5, 5, 5);
		        	 JuneData.setTextColor(Color.WHITE);
		        	 JuneData.setGravity(Gravity.CENTER);
		        	 tr.addView(JuneData);
		        	 
		        	 
		        	 
		        	 TextView JulyData = new TextView(POCApp.this);
		        	 JulyData.setBackgroundResource(R.drawable.rectgrad);
		        	 JulyData.setText(Data.get(i).getJulyData());
		        	// JulyData.setPadding(5, 5, 5, 5);
		        	 JulyData.setTextColor(Color.WHITE);
		        	 JulyData.setGravity(Gravity.CENTER);
		        	 tr.addView(JulyData);
		        	 
		        	 
		        	 TextView AugData = new TextView(POCApp.this);
		        	 AugData.setBackgroundResource(R.drawable.rectgrad);
		        	 AugData.setText(Data.get(i).getAugustDat());
		        	// AugData.setPadding(5, 5, 5, 5);
		        	 AugData.setTextColor(Color.WHITE);
		        	 AugData.setGravity(Gravity.CENTER);
		        	 tr.addView(AugData);
		        	 		        	 
		        	 
		        	 TextView SepData = new TextView(POCApp.this);
		        	 SepData.setBackgroundResource(R.drawable.rectgrad);
		        	 SepData.setText(Data.get(i).getSepData());
		        	// SepData.setPadding(5, 5, 5, 5);
		        	 SepData.setTextColor(Color.WHITE);
		        	 SepData.setGravity(Gravity.CENTER);
		        	 tr.addView(SepData);
		        	 
		        	 
		        	 TextView OctData = new TextView(POCApp.this);
		        	 OctData.setBackgroundResource(R.drawable.rectgrad);
		        	 OctData.setText(Data.get(i).getOctData());
		        	// SepData.setPadding(5, 5, 5, 5);
		        	 OctData.setTextColor(Color.WHITE);
		        	 OctData.setGravity(Gravity.CENTER);
		        	 tr.addView(OctData);
		        	 
		        	 
		        	 
		        	 TextView NovData = new TextView(POCApp.this);
		        	 NovData.setBackgroundResource(R.drawable.rectgrad);
		        	 NovData.setText(Data.get(i).getNovData());
		        	// SepData.setPadding(5, 5, 5, 5);
		        	 NovData.setTextColor(Color.WHITE);
		        	 NovData.setGravity(Gravity.CENTER);
		        	 tr.addView(NovData);
		        	 
		        	 
		        	 TextView DecData = new TextView(POCApp.this);
		        	 DecData.setBackgroundResource(R.drawable.rectgrad);
		        	 DecData.setText(Data.get(i).getDecData());
		        	// SepData.setPadding(5, 5, 5, 5);
		        	 DecData.setTextColor(Color.WHITE);
		        	 DecData.setGravity(Gravity.CENTER);
		        	 tr.addView(DecData);
		        	 
		        	 
		        	 layout.addView(tr);
				    }		         
		        // scroll.addView(layout);
		         DataDialog.show();
		         }			
		});
	}

	
	private void init() 
	{
		DiaBtn = (Button) findViewById(R.id.Testbtn);
		Data.add(new TestBean("2005", "100", "200", "399", "600", "392", "600", "783", "344", "332","322","123","150"));
		Data.add(new TestBean("2006", "100", "200", "399", "600", "392", "600", "783", "344", "332","322","123","150"));
		Data.add(new TestBean("2007", "100", "200", "399", "600", "392", "600", "783", "344", "332","322","123","150"));
		Data.add(new TestBean("2008", "100", "200", "399", "600", "392", "600", "783", "344", "332","322","123","150"));
		Data.add(new TestBean("2009", "100", "200", "399", "600", "392", "600", "783", "344", "332","322","123","150"));
		Data.add(new TestBean("2010", "100", "200", "399", "600", "392", "600", "783", "344", "332","322","123","150"));

		Data.add(new TestBean("2005", "100", "200", "399", "600", "392", "600", "783", "344", "332","322","123","150"));
		Data.add(new TestBean("2006", "100", "200", "399", "600", "392", "600", "783", "344", "332","322","123","150"));
		Data.add(new TestBean("2007", "100", "200", "399", "600", "392", "600", "783", "344", "332","322","123","150"));
		Data.add(new TestBean("2008", "100", "200", "399", "600", "392", "600", "783", "344", "332","322","123","150"));
		Data.add(new TestBean("2009", "100", "200", "399", "600", "392", "600", "783", "344", "332","322","123","150"));
		Data.add(new TestBean("2010", "100", "200", "399", "600", "392", "600", "783", "344", "332","322","123","150"));
		
		Data.add(new TestBean("2005", "100", "200", "399", "600", "392", "600", "783", "344", "332","322","123","150"));
		Data.add(new TestBean("2006", "100", "200", "399", "600", "392", "600", "783", "344", "332","322","123","150"));
		Data.add(new TestBean("2007", "100", "200", "399", "600", "392", "600", "783", "344", "332","322","123","150"));
		Data.add(new TestBean("2008", "100", "200", "399", "600", "392", "600", "783", "344", "332","322","123","150"));
		Data.add(new TestBean("2009", "100", "200", "399", "600", "392", "600", "783", "344", "332","322","123","150"));
		Data.add(new TestBean("2010", "100", "200", "399", "600", "392", "600", "783", "344", "332","322","123","150"));
		
		Data.add(new TestBean("2005", "100", "200", "399", "600", "392", "600", "783", "344", "332","322","123","150"));
		Data.add(new TestBean("2006", "100", "200", "399", "600", "392", "600", "783", "344", "332","322","123","150"));
		Data.add(new TestBean("2007", "100", "200", "399", "600", "392", "600", "783", "344", "332","322","123","150"));
		Data.add(new TestBean("2008", "100", "200", "399", "600", "392", "600", "783", "344", "332","322","123","150"));
		Data.add(new TestBean("2009", "100", "200", "399", "600", "392", "600", "783", "344", "332","322","123","150"));
		Data.add(new TestBean("2010", "100", "200", "399", "600", "392", "600", "783", "344", "332","322","123","150"));
	}
}