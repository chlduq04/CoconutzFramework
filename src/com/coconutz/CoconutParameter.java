package com.coconutz;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CoconutParameter {
	
	JSONObject jsonObject;
	JSONArray jsonArray;
	
	public CoconutParameter(JSONArray value){
		jsonArray = value;
	}
	
	public CoconutParameter( String value ) throws JSONException{
		jsonArray = new JSONArray(value);
	}
	public CoconutParameter(){
		try {
			jsonObject = new JSONObject("{\"empty\":\"empty\"}");
		} catch (org.json.JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public CoconutParameter(JSONObject param){
		jsonObject = param;
	}
	
	public String getEncodingParam(){
		return encoding(jsonObject.toString());
	}
	private String encoding(String argu){
		try {
			return java.net.URLEncoder.encode(argu, "UTF-8").replaceAll("\\+", "%20").replaceAll("\\*", "%2A");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}		
	}
	public JSONObject getJson(){
		return jsonObject;
	}
	public JSONArray getJsonArray(){
		return jsonArray;
	}
	private void JSONException(){
		System.out.println("CoconutParamter Error!!");
	}
	
	public String get(String name){
		try {
			return jsonObject.getString(name);
		} catch (org.json.JSONException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public String getString() {
		return jsonObject.toString() + jsonArray.toString();
	}
	
	/*----------------------- coconutDB -----------------------*/
}
