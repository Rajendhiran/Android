package com.intent.maps;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AppsMaps extends Activity
{
  private EditText _latitude;
  private EditText _longitude;
  
  public void onCreate(Bundle icicle) 
  {
    super.onCreate(icicle);
    setContentView(R.layout.main);    
    _latitude=(EditText)findViewById(R.id.lat);
    _longitude=(EditText)findViewById(R.id.lon);
  }
  
  public void showMe(View v) 
  {
    String _lati=_latitude.getText().toString();
    String _long=_longitude.getText().toString();    
    Uri uri=Uri.parse("geo:"+_lati+","+_long);       
    startActivity(new Intent(Intent.ACTION_VIEW, uri));
  }
}