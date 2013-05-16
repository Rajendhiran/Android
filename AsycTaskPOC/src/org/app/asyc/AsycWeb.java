package org.app.asyc;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.app.bean.DetailBean;
import org.app.constant.Constant;
import org.app.parser.XmlParser;
import org.app.webservice.Webservice;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AsycWeb extends Activity
{
	TextView ContentText;
	Button Parsing;
	InputStream in;
	SAXParserFactory spf;
	SAXParser sp;
	ArrayList<DetailBean> Data;
	
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main); 
        init();
        process();
    }

	/**
	 *   @author Rajendhiran. E
	 *   Jul 9, 201212:26:01 PM
	 */
    
	private void process() 
	{
		Parsing.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg0) 
			{
				ContentText.setText("");
				ServerConnectionTask Task = new ServerConnectionTask(AsycWeb.this);
				Task.execute();		
			}
		});
				
	}

	/**
	 *   @author Rajendhiran. E
	 *   Jul 9, 201212:25:17 PM
	 */
	private void init() 
	{
	   ContentText= (TextView) findViewById(R.id.Content);
	   Parsing = (Button) findViewById(R.id.parser);
	}
	
	private class ServerConnectionTask extends AsyncTask<Void, Void,Boolean>	
	{
		Context ctx;
		ProgressDialog progress;
		
		public ServerConnectionTask(Context ctx) 
		{
			this.ctx = ctx;
		}
		
		protected void onPreExecute()
		{
			super.onPreExecute();
			progress = new ProgressDialog(ctx);
			progress.setMessage("Connecting to Server");
			progress.show();
		}
		
		protected Boolean doInBackground(Void... arg0) 
		{
			boolean Status=false;
			in = Webservice.XMLFromUrl(Constant.Web_Link);
			XmlParser handler = new XmlParser();
			InputSource is = new InputSource(in);
			spf = SAXParserFactory.newInstance();
			try 
			{
				sp = spf.newSAXParser();
				sp.parse(is,handler);
				Data = handler.getItem();
				Status=true;
			}
			catch (ParserConfigurationException e) 
			{
				e.printStackTrace();
			}
			catch (SAXException e) 
			{			
				e.printStackTrace();
			}
			catch (IOException e) 
			{			
				e.printStackTrace();
			}								
			return Status;
		}
						
		protected void onPostExecute(Boolean result)
		{
			super.onPostExecute(result);
			String ContentData="Something went wrong...";
			progress.dismiss();
			if(result)
			{
				if(Data!=null)
				{
				 if(Data.size()>0)
				 {					 
					 ContentData="";				 
					 for(int i=0;i<Data.size();i++)
						 ContentData+="\n Name: "+Data.get(i).getEname()+"\n Type: "+Data.get(i).getEtype()+"\n ID: "+Data.get(i).getEid()+"\n Age: "+Data.get(i).getEage()+"\n\n";
					 ContentText.setText(ContentData);	 					 
				 }				 
				}
				else
				{
					onErrAlert(ctx);
				}
			}
			else
			{
				onErrAlert(ctx);
			}
		}	 	
	}
	
	protected void onErrAlert(Context ctx)
	{
		new AlertDialog.Builder(ctx)
		.setTitle("Employee Details")
		.setMessage("Error in Connection...")
		.setPositiveButton("OK",new DialogInterface.OnClickListener() 
		{
			public void onClick(DialogInterface arg0, int arg1) 
			{
			}
		}).show();		
	}
}