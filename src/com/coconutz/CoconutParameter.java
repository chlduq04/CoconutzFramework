package com.coconutz;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/*********************************************************************************************
* @brief  coconut에서 사용하는 parameter을 만들어 준다.
* @file  CoconutParameter.java
* @author  LEEJEONGSUB
* @date  2013. 5.
*********************************************************************************************/
public class CoconutParameter {
	
	JSONObject jsonObject;
	JSONArray jsonArray;
	/*******************************************************************************************************
	 * @brief CoconutParameter의 생성자 jsonarray를 받는다
	 * @method CoconutParameter
	 * @file CoconutParameter.java
	 * @author LEEJEONGSUB
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutParameter(JSONArray value){
		jsonArray = value;
	}
	/*******************************************************************************************************
	 * @brief CoconutParameter의 생성자. String 파라미터를 받아서 json array로 만든다.
	 * @method CoconutParameter
	 * @file CoconutParameter.java
	 * @author LEEJEONGSUB
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/	
	public CoconutParameter( String value ) throws JSONException{
		jsonArray = new JSONArray(value);
	}
	/*******************************************************************************************************
	 * @brief CoconutParameter의 생성자. 빈 jsonarray 값을 만든다.
	 * @method CoconutParameter
	 * @file CoconutParameter.java
	 * @author LEEJEONGSUB
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutParameter(){
		try {
			jsonObject = new JSONObject("{\"empty\":\"empty\"}");
		} catch (org.json.JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*******************************************************************************************************
	 * @brief jsonobject를 받아서 대입한다.
	 * @method CoconutParameter
	 * @file CoconutParameter.java
	 * @author LEEJEONGSUB
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutParameter(JSONObject param){
		jsonObject = param;
	}
	/*******************************************************************************************************
	 * @brief 저장된 jsonobject값을 encoding하여 반환한다.
	 * @method getEncodingParam
	 * @file CoconutParameter.java
	 * @author LEEJEONGSUB
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/	
	public String getEncodingParam(){
		return encoding( jsonObject.toString() );
	}
	/*******************************************************************************************************
	 * @brief jsonarray값을 encoding한다.
	 * @method encoding
	 * @file CoconutParameter.java
	 * @author LEEJEONGSUB
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	private String encoding(String argu){
		try {
			return java.net.URLEncoder.encode(argu, "UTF-8").replaceAll("\\+", "%20").replaceAll("\\*", "%2A");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}		
	}
	/*******************************************************************************************************
	 * @brief jsonObject를 얻을 수 있다.
	 * @method getJson
	 * @file CoconutParameter.java
	 * @author LEEJEONGSUB
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public JSONObject getJson(){
		return jsonObject;
	}
	/*******************************************************************************************************
	 * @brief jsonArray를 얻을 수 있다.
	 * @method getJsonArray
	 * @file CoconutParameter.java
	 * @author LEEJEONGSUB
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public JSONArray getJsonArray(){
		return jsonArray;
	}
	/*******************************************************************************************************
	 * @brief jsonException을 처리한다.
	 * @method JSONException
	 * @file CoconutParameter.java
	 * @author LEEJEONGSUB
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	private void JSONException(){
		System.out.println("CoconutParamter Error!!");
	}
	/*******************************************************************************************************
	 * @brief jsonObject안의 값을 얻을 수 있다.
	 * @method get
	 * @file CoconutParameter.java
	 * @author LEEJEONGSUB
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/	
	public String get(String name){
		try {
			return jsonObject.getString(name);
		} catch (org.json.JSONException e) {
			e.printStackTrace();
			return "";
		}
	}
	/*******************************************************************************************************
	 * @brief jsonObject와 jsonArray를 얻을 수 있다.
	 * @method getString
	 * @file CoconutParameter.java
	 * @author LEEJEONGSUB
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/	
	public String getString() {
		return jsonObject.toString() + jsonArray.toString();
	}
	
	/*----------------------- coconutDB -----------------------*/
}
