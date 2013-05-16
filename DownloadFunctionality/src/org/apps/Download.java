package org.apps;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Download extends Activity
{
	public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    private Button startBtn,uploadBtn,CheckNet;
    private ProgressDialog mProgressDialog;
    private ImageView upImg;
    
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        startBtn = (Button)findViewById(R.id.startBtn);
        uploadBtn = (Button) findViewById(R.id.uploadBtn);
        upImg = (ImageView) findViewById(R.id.upimg);
        CheckNet = (Button) findViewById(R.id.CheckNet);
        
        CheckNet.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View arg0) 
			{
				ConnectivityManager  cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo info = cm.getActiveNetworkInfo();
				
				if((info!=null)&&(info.isAvailable())&&(info.isConnected()))
				{
					String k="\n Type Name: "+info.getTypeName();
					k+="\n Detailed State : "+info.getDetailedState();
					k+="\n Reason: "+info.getReason();
					k+="\n Sub Types: "+info.getSubtypeName();
					k+="\n Type: "+info.getType();	
					Toast.makeText(Download.this,"Connection Exists : \n"+k ,Toast.LENGTH_LONG).show();
					Log.d("Infor. : ","info: "+k);
				}
				else
					Toast.makeText(Download.this,"Connection Not Exists" ,Toast.LENGTH_LONG).show();
			}
		});
        
        uploadBtn.setOnClickListener(new View.OnClickListener()
        {
			public void onClick(View v) 
			{
				startUpload();
			}
		});
        
        startBtn.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v) 
			{
                startDownload();
			}
		 });		 
	}
   
    private void startUpload()
    {
    	Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
    	//intent.setAction(Intent.ACTION_);    	
    	Uri startDir = Uri.fromFile(new File("/sdcard"));
    	intent.setDataAndType(startDir, "CATEGORY_OPENABLE");
    	//intent.addCategory(Intent.C);
    	intent.setType("*/*");
    	Intent i = Intent.createChooser(intent, "File");
    	startActivityForResult(i, 0);
    	//startActivity(i);    	
    	Log.d("ActivityResult", ""+i);
    	Toast.makeText(getApplicationContext(), ""+i,Toast.LENGTH_LONG).show();
    }
    
  /*  protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) 
    { 
        	super.onActivityResult(requestCode, resultCode, imageReturnedIntent);         
            if(resultCode == RESULT_OK)
            {  
                Uri selectedImage = imageReturnedIntent.getData();
                InputStream imageStream = null;
				try {
					imageStream = getContentResolver().openInputStream(selectedImage);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String filePath = cursor.getString(columnIndex);
                cursor.close();


                //Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePathColumn[0]);
                upImg.setImageBitmap(yourSelectedImage);
            }        
    }*/
    
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
       if (requestCode == 0)
       {
       if (resultCode == RESULT_OK)
       {
          Uri uri = intent.getData();
          String type = intent.getType();
          Log.d("pick","Pick completed: "+ uri + " "+type);
          Toast.makeText(getApplicationContext(), ""+"Pick completed: URI : "+ uri + " Type : "+type,Toast.LENGTH_LONG).show();
          if (uri != null)
          {
             String path = uri.getPath();
                           // Selected file/directory path is below
                //path = (new File(URI.create(path))).getAbsolutePath();             
                Toast.makeText(getApplicationContext(), "Absolute Path : "+path.toString(),Toast.LENGTH_LONG).show();
                upImg.setImageURI(uri);
               /* File f = new File(path);                
                FileInputStream imageStream = null;
				try {
					imageStream = new FileInputStream(path);
					Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
	                upImg.setImageBitmap(yourSelectedImage);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
                
          }
       }
       else 
       {
    	   Log.d("tester","Back from pick with cancel status");
    	   Toast.makeText(getApplicationContext(), "tester Back from pick with cancel status",Toast.LENGTH_LONG).show();
       }       
       }
    }
     
    private void startDownload() 
    {
        //String url = "http://farm1.static.flickr.com/114/298125983_0e4bf66782_b.jpg";
        //String url = "http://192.168.244.203/axaflow_build/document/download_document/16";
        String url = "http://www.openoffice.org/sc/excelfileformat.pdf";
        new DownloadFileAsync().execute(url);
    }
    
    protected Dialog onCreateDialog(int id) 
    {
        switch (id) 
        {
			case DIALOG_DOWNLOAD_PROGRESS:
				mProgressDialog = new ProgressDialog(this);
				mProgressDialog.setMessage("Downloading file..");
				mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				mProgressDialog.setCancelable(false);
				mProgressDialog.show();
				return mProgressDialog;
			default:
				return null;
        }
    }       
class DownloadFileAsync extends AsyncTask<String, String, String> 
{
	protected void onPreExecute() 
	{
		super.onPreExecute();
		showDialog(DIALOG_DOWNLOAD_PROGRESS);
	}
	
	protected String doInBackground(String... aurl) 
	{
		int count;
		try 
		{
			URL url = new URL(aurl[0]);
			URLConnection connection = url.openConnection();
			connection.connect();
		
			int lenghtOfFile = connection.getContentLength();
			Log.d("ANDRO_ASYNC", "Lenght of file: " + lenghtOfFile);
		
			InputStream input = new BufferedInputStream(url.openStream());
			OutputStream output = new FileOutputStream("/sdcard/excelformat.pdf");
		
			byte data[] = new byte[1024];
			
			long total = 0;
			
				while ((count = input.read(data)) != -1)
				{
					total += count;
					publishProgress(""+(int)((total*100)/lenghtOfFile));
					output.write(data, 0, count);
					Log.d("Count: ", "Count: "+count);
				}					
				Toast.makeText(Download.this, "Download Completed...", Toast.LENGTH_LONG).show();
				output.flush();
				output.close();
				input.close();
			} 
		 catch (Exception e) 
		 {
			 
		 }
		return null;
	}
	
	protected void onProgressUpdate(String... progress) 
	{
		 Log.d("ANDRO_ASYNC",progress[0]);
		 mProgressDialog.setProgress(Integer.parseInt(progress[0]));
	}
	
	protected void onPostExecute(String unused) 
	{
		dismissDialog(DIALOG_DOWNLOAD_PROGRESS);
	}
  }
}