package com.database;
import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TestingActivity extends Activity 
{
       private static final String test="sam";
       private static final String table ="user";
       SQLiteDatabase db=null;					
       public ListView ContentList;
       public ViewAdapter adapter;
       public ArrayList<String> Fname; 
       public ArrayList<String> Lname; 
       EditText ed1;
       EditText ed2;
       Button b;
       Button b1;
       TextView emptyView;
       
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
         init();
         process();         
    }
    
    private void init()
    {
          ed1=(EditText)findViewById(R.id.editText1);
          ed2=(EditText)findViewById(R.id.editText2);
          b=(Button)findViewById(R.id.button1);
          b1=(Button)findViewById(R.id.button2);
          emptyView = (TextView) findViewById(R.id.Empty);          
          ContentList = (ListView) findViewById(R.id.LisContent);          
          emptyView.setVisibility(TextView.GONE);
			try 
			{
				 db =  TestingActivity.this.openOrCreateDatabase(test, MODE_PRIVATE, null);            	
				 db.execSQL("CREATE TABLE IF NOT EXISTS " +table +" (FirstName VARCHAR, LastName VARCHAR );" );
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
    }
        
    private void process()
    {
        b.setOnClickListener(new OnClickListener()
        {

			public void onClick(View arg0) 
			{				
				String first=ed1.getText().toString();
				String last=ed2.getText().toString();								
				if(first.trim().equals("")||last.trim().equals(""))
				{
					new AlertDialog.Builder(TestingActivity.this)
			         .setTitle("Validation")
			         .setMessage("Firstname and Lastname Required!.")
			         .setPositiveButton("OK",new DialogInterface.OnClickListener()
			         {
						public void onClick(DialogInterface dialog, int which) 
						{
							
						}
					}).show();
				}
				else
				{
					try 
					{
						 //db =  TestingActivity.this.openOrCreateDatabase(test, MODE_PRIVATE, null);            	
						 //db.execSQL("CREATE TABLE IF NOT EXISTS " +table +" (FirstName VARCHAR, LastName VARCHAR );" );					
		            	 db.execSQL("INSERT INTO " +table +" Values ('"+first+"','"+last+"');");	            	
		            	 
		            	 Toast.makeText(TestingActivity.this, "Data saved", Toast.LENGTH_LONG).show();
		            	
		            	 ed1.setText("");
		            	 ed2.setText("");	            		            	
				
					}
					catch(SQLException e)
					{
						e.getMessage();
					}
					finally
					{
					  if(db!=null)
						  
						db.close();
					}
				}
			}

        });
        
        b1.setOnClickListener(new OnClickListener()
        {

			public void onClick(View v) 
			{
				SQLiteDatabase db=null;
			    
				 db =  TestingActivity.this.openOrCreateDatabase(test, MODE_PRIVATE, null);				 
	             Cursor c = db.rawQuery("SELECT Firstname , LastName  FROM " +table , null);
	             Fname = new ArrayList<String>();
	             Lname = new ArrayList<String>();
	            	if (c != null ) 
	            	{
	            		emptyView.setVisibility(TextView.VISIBLE);
	            		if  (c.moveToFirst()) 
	            		{
	            			do 
	            			{	            				
	            				Fname.add(c.getString(c.getColumnIndex("FirstName")));
	            				Lname.add(c.getString(c.getColumnIndex("LastName")));
	            				
	            				adapter = new ViewAdapter(TestingActivity.this,R.layout.listcontent,Fname,Lname);
	            				ContentList.setAdapter(adapter);
	            			}while (c.moveToNext());	            			
	            		} 
	            	}
	            	ContentList.setEmptyView(findViewById(R.id.Empty));
			}						        
        });
    }
    private class ViewAdapter extends BaseAdapter
	{
		Context ctx;
		Holder h;
		ArrayList<String> Fname;
		ArrayList<String> Lname;
		LayoutInflater factory;
		int row;
		

		public ViewAdapter(TestingActivity testingActivity, int listcontent,ArrayList<String> fname2, ArrayList<String> lname2) 
		{
			row = listcontent;
			ctx = testingActivity;
			Fname = fname2;
			Lname = lname2;
		}

		public int getCount() 
		{
			return Fname.size();
		}

		public Object getItem(int position) 
		{
			return null;
		}

		public long getItemId(int position) 
		{
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) 
		{
			Log.d("View", "getView()");
			if(convertView==null)
			{
				h = new Holder();
				factory = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = factory.inflate(row,null);
				h.FirstName= (TextView) convertView.findViewById(R.id.Firstname);
				h.LastName= (TextView) convertView.findViewById(R.id.Lastname);
				convertView.setTag(h);
			}
			else
			{
				h= (Holder) convertView.getTag();
			}
			
				h.FirstName.setText(Fname.get(position).toString());
				h.LastName.setText(Lname.get(position).toString());
			return convertView;
		}
		
		private class Holder
		{
			TextView FirstName;
			TextView LastName;	
		}
	}
}   