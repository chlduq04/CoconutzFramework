package com.coconutz;

import org.json.JSONException;
import org.json.JSONObject;

import com.sun.media.sound.JavaSoundAudioClip;

/*********************************************************************************************
* @brief  return type을 결정하는 Coconut 클래스
* @file  Coconut.java
* @author  LEEJEONGSUB
* @date  2013. 5.
*********************************************************************************************/
public class Coconut {
	
	private String returnType;
	private JSONObject returnJson = new JSONObject();
	private JSONObject data = null;
	/*******************************************************************************************************
	 * @brief Coconut의 생성자
	 * @method Coconut
	 * @file Coconut.java
	 * @author LEEJEONGSUB
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public Coconut(){
		setReturnType("json");
		data = new JSONObject();
	}
	/*******************************************************************************************************
	 * @brief Coconut의 생성자 jsonobject를 받는다
	 * @method Coconut
	 * @file Coconut.java
	 * @author LEEJEONGSUB
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public Coconut( JSONObject obj ){
		setReturnType("json");
		data = obj;
	}
	/*******************************************************************************************************
	 * @brief jsonobject의 값을 string값으로 반환한다.
	 * @method getString
	 * @file Coconut.java
	 * @author LEEJEONGSUB
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public String getString() {
		return data.toString();
	}
	/*******************************************************************************************************
	 * @brief type을 return 한다
	 * @method getReturnType
	 * @file Coconut.java
	 * @author LEEJEONGSUB
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public String getReturnType() {
		return returnType;
	}
	/*******************************************************************************************************
	 * @brief type을 설정한다
	 * @method setReturnType
	 * @file Coconut.java
	 * @author LEEJEONGSUB
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	/*******************************************************************************************************
	 * @brief json에 key와 value를 통하여 집어넣는다
	 * @method put
	 * @file Coconut.java
	 * @author LEEJEONGSUB
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public boolean put(String key, int value) {
		try {
			data.put(key, value);
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/*******************************************************************************************************
	 * @brief json에 key와 value를 통하여 집어넣는다
	 * @method put
	 * @file Coconut.java
	 * @author LEEJEONGSUB
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public boolean put(String key, long value) {
		try {
			data.put(key, value);
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/*******************************************************************************************************
	 * @brief json에 key와 value를 통하여 집어넣는다
	 * @method put
	 * @file Coconut.java
	 * @author LEEJEONGSUB
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public boolean put(String key, double value) {
		try {
			data.put(key, value);
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/*******************************************************************************************************
	 * @brief json에 key와 value를 통하여 집어넣는다
	 * @method put
	 * @file Coconut.java
	 * @author LEEJEONGSUB
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public boolean put(String key, boolean value) {
		try {
			data.put(key, value);
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/*******************************************************************************************************
	 * @brief json에 key와 value를 통하여 집어넣는다
	 * @method put
	 * @file Coconut.java
	 * @author LEEJEONGSUB
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public boolean put(String key, String value) {
		try {
			data.put(key, value);
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/*******************************************************************************************************
	 * @brief 성공적으로 들어갔을 경우에 result와 data를 포함한 json 타입의 결과를 반환한다.
	 * @method responseData
	 * @file Coconut.java
	 * @author LEEJEONGSUB
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public JSONObject responseData()
	{
		try {
			returnJson.put("result", "success");
			returnJson.put("data", data);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return returnJson;
	}

}
