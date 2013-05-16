package org.app.webservice;

import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

/**
 * @author Rajendhiran. E
 * Jul 9, 201211:43:14 AM
 * Descriptions: 
 */
public class Webservice
{	
	public static InputStream XMLFromUrl(String url)
	{
		InputStream in=null;
		//String XMLContent="";
		try
		{
			DefaultHttpClient Request = new DefaultHttpClient();	   
			HttpGet httpget = new HttpGet(url);
			httpget.setHeader("Accept","application/xml");

			HttpResponse Response = Request.execute(httpget);
			HttpEntity Content = Response.getEntity();
			in = Content.getContent();
			//String x = EntityUtils.getContentCharSet(Content);
		}
		catch(Exception e)
		{
			Log.d("Webservice Error: ","Webservice Error : "+e);			
		}

		return in;	   
	}
}