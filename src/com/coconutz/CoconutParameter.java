package com.coconutz;

import java.io.UnsupportedEncodingException;

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
	public CoconutParameter() throws JSONException{
		jsonObject = new JSONObject("{\"empty\":\"empty\"}");
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
	
	/*----------------------- coconutDB -----------------------*/
}
