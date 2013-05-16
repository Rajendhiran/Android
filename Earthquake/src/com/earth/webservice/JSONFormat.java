package com.earth.webservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;

public class JSONFormat {

	public static JSONObject JSONFromURL(String url, Bundle b) {
		JSONObject jobject = null;
		String JSONContent = "";
		Bundle extraData = b;
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			//BasicHttpParams params = new BasicHttpParams();
			HttpPost httpPost = new HttpPost(url);
			//HttpGet httpGet = new HttpGet(url);

			List<NameValuePair> pair = new ArrayList<NameValuePair>();
			pair.add(new BasicNameValuePair("north", extraData
					.getString("north")));
			pair.add(new BasicNameValuePair("south", extraData
					.getString("south")));
			pair.add(new BasicNameValuePair("east", extraData.getString("east")));
			pair.add(new BasicNameValuePair("west", extraData.getString("west")));
			pair.add(new BasicNameValuePair("username", "lion"));
			
			/*params.setParameter("north",extraData.getString("north"));
			params.setParameter("south",extraData.getString("south"));
			//params.setParameter("east",extraData.getString("east"));
			params.setParameter("west",extraData.getString("west"));
			params.setParameter("username","lion");			
			*/
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pair);
			httpPost.setEntity(entity);
			//Log.d("URL: ", "URL: "+url+params);
			//httpGet.setParams(params);
			HttpResponse httpresponse = httpClient.execute(httpPost);
			HttpEntity content = httpresponse.getEntity();
			if (content != null) {
				JSONContent = EntityUtils.toString(content);
				Log.d("JSON Content : ", "" + JSONContent);
				jobject = new JSONObject(JSONContent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobject;
	}
}
