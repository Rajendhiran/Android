package org.app.parser;

import java.util.ArrayList;

import org.app.bean.DetailBean;
import org.app.constant.Constant;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import android.util.Log;

/**
 * @author Rajendhiran. E
 * Jul 9, 201212:03:47 PM
 * Descriptions: 
 */
public class XmlParser extends  DefaultHandler
{
	String tagValue;
	DetailBean dataObjectBean;
	ArrayList<DetailBean> Data = new ArrayList<DetailBean>();	
	public void startDocument() throws SAXException 
	{
		// Log.d(tag,"Document Starting");
	}
 
	public void endDocument() throws SAXException 
	{
		// Log.d(tag,"Document ending ");
	}
	
	public void startElement(String namespaceURI, String localName,String qName, Attributes atts) throws SAXException 
	{
		if(qName.equalsIgnoreCase(Constant.Tag_Title))
		{
			dataObjectBean = new DetailBean();									
			dataObjectBean.setEtype(atts.getValue(Constant.Tag_Type));
		}		
    }	
	
	public void characters(char ch[], int start, int length) 
	{
		 tagValue = new String(ch, start, length);		
	}
	
	public void endElement(String namespaceURI, String localName, String qName)throws SAXException 
	{	
		
		Log.d("TagValue: ","<Tag>   "+tagValue);
		if(qName.equalsIgnoreCase(Constant.Tag_Name))
			dataObjectBean.setEname(tagValue);
		if(qName.equalsIgnoreCase(Constant.Tag_Id))
			dataObjectBean.setEid(tagValue);
		if(qName.equalsIgnoreCase(Constant.Tag_Age))
			dataObjectBean.setEage(tagValue);
		if(qName.equalsIgnoreCase(Constant.Tag_Title))
			Data.add(dataObjectBean);			
	}
	public ArrayList<DetailBean> getItem()
	{
		return Data;
	}	
}
