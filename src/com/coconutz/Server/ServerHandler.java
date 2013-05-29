package com.coconutz.Server;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.coconutz.Coconut;
import com.coconutz.CoconutParameter;
import com.coconutz.CoconutzLog;
import com.coconutz.G;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ServerHandler implements HttpHandler {

	public static Map<String, Object> usedClassList = new HashMap<String, Object>();

	public void handle(HttpExchange xchg) throws IOException {

		if ( xchg.getRequestMethod().compareTo("POST") == 0 ) {
			
			//System.out.println("method : " + xchg.getRequestMethod());	//type get/post			
			checkAjaxOrForm(xchg, CoconutzLog.logCnt++);
		}
	}
	public void checkAjaxOrForm(HttpExchange xchg, long logCnt) throws IOException{
		Coconut responseCoconut = null;
		
		// 헤더 가져오기
		Headers headers = xchg.getRequestHeaders();
		Set<Map.Entry<String, List<String>>> entries = headers.entrySet();
		long fileSize = 0;

		StringBuffer request = new StringBuffer();
		for (Map.Entry<String, List<String>> entry : entries) {
			//System.out.println(entry.toString());
			if (entry.toString().matches(".*Content-length.*") ) {
				String size = entry.toString().substring(16, entry.toString().length()-1);
				fileSize = Long.parseLong(size);
			}
			request.append(entry.toString() + "\n");	
		}
		
		// 바운더리 구분자 찾기
		int boundary_index = request.lastIndexOf("[multipart/form-data; boundary=");
		
		if (boundary_index == -1){
			//-1 post json ajax
			boundary_index = request.lastIndexOf("[application/x-www-form-urlencoded;");
			AjaxHandler ajaxHandler = new AjaxHandler(logCnt);
			if (boundary_index != -1) {
				//ajax post json데이터만 들어있음
				
				CoconutzLog.put(logCnt, "Ajax request", 1);
				CoconutzLog.putTime(logCnt, System.currentTimeMillis()/1000);
				JSONObject requestJson = ajaxHandler.getAjaxJson( xchg );

				responseCoconut = callFunction(requestJson, logCnt);
								
				if (G.DEBUG) { G.debugPrint("call Function return String : " + responseCoconut.getString()); }
			}
			ajaxHandler.responseAjax(xchg, responseCoconut);
		}
		else {
			// != -1 Form File data

			String boundary_str = request.substring(boundary_index+31);
			boundary_index = boundary_str.indexOf("]");
			boundary_str = boundary_str.substring(0, boundary_index);
			
			Map<String, String> params = null;
			FormHandler formHandler = new FormHandler(logCnt);

			CoconutzLog.put(logCnt, "Form post  request", 1);
			CoconutzLog.putTime(logCnt, System.currentTimeMillis()/1000);
			params = formHandler.getFormData(xchg, "--"+boundary_str, fileSize);
			JSONObject requestJson = formHandler.toJson(params);
			
			responseCoconut = callFunction(requestJson, logCnt);
			if (G.DEBUG) { G.debugPrint("call Function return String : " + responseCoconut.getString()); }

			formHandler.responseForm(xchg, responseCoconut, boundary_str);
		}
		
		/*if ( returnStr == null ) {
			returnStr = "{ \"result\" : \"fail\" }";
			returnJSON = new JSONObject(returnStr);
		} else {
			returnJSON = new JSONObject(returnStr);
		}*/
		
	}

	public Coconut callFunction( JSONObject json, long logCnt ) {
		
		Coconut result = null;
		boolean notUsed = true;
		
		/*
		 * daemon, function 등 필수요소 확인 후 처리하기
		 */
		
		CoconutParameter cp = makeParameter(json);
		try {
			for ( Entry<String, Object> entry : ServerHandler.usedClassList.entrySet() ) {
					if ( entry.getKey().compareTo( (String) json.get("class") ) == 0 ) {
						CoconutzLog.put(logCnt, "class : " + entry.getKey() + " find");
						result = invoke(entry.getValue(), (String) json.get("function"), cp, logCnt );
						notUsed = false;
						break;
					}
			}
		} catch (JSONException e) {
			CoconutzLog.put(logCnt, e.getMessage());
		}	
		if (result == null || notUsed) {
			try {
				result = new Coconut(new JSONObject("{\"error\":\"wrong service or daemon\"}"));
			} catch (JSONException e) {
				CoconutzLog.put(logCnt, e.getMessage());
				result = null;
			}
		}
		
		try {
			switch ((String)json.get("returntype")){
			case "json":
				result.setReturnType("json");
				break;
			case "xml":
				result.setReturnType("xml");
				break;
			case "raw":
				result.setReturnType("raw");
				break;
			case "html":
				result.setReturnType("html");
				break;
			default:
				result.setReturnType("json");
				break;
			}
		} catch (JSONException e) {
			result.setReturnType("json");
			CoconutzLog.put(logCnt, e.getMessage());
		}
		
		return result;
	}
	
	
	/**
	 * 특정 클래스의 내용을 invoke
	 * 
	 * @param obj
	 *            Method Invoke할 오브젝트
	 * @param methodName
	 *            Method Name
	 * @param object
	 *            Parameter Object List
	 * @return
	 */
	public static Coconut invoke(Object obj, String methodName, Object object, long logCnt) {
		Method[] methods = obj.getClass().getMethods();

		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equals(methodName)) {
				try {
					CoconutzLog.put(logCnt, "function : " + methodName + " call");
					if (methods[i].getReturnType().getName().equals("void")) {
						return (Coconut) methods[i].invoke(obj, object);
					} else {
						return (Coconut)methods[i].invoke( obj, object );
					}
				} catch (IllegalAccessException lae) {
					CoconutzLog.put(logCnt, "LAE : " + lae.getMessage());
				} catch (InvocationTargetException ite) {
					CoconutzLog.put(logCnt, "ITE : " + ite.getMessage());
				}
			}
		}
		return null;
	}

	public static Coconut invoke(Object obj, String methodName, Object[] object, long logCnt) {
		Method[] methods = obj.getClass().getMethods();

		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equals(methodName)) {
				try {
					CoconutzLog.put(logCnt, "function : " + methodName + " call");
					if (methods[i].getReturnType().getName().equals("void")) {
						return (Coconut)methods[i].invoke(obj, object);
					} else {
						methods[i].getParameterAnnotations();
						return (Coconut)methods[i].invoke( obj, object );
					}
				} catch (IllegalAccessException lae) {
					CoconutzLog.put(logCnt, "LAE : " + lae.getMessage());
				} catch (InvocationTargetException ite) {
					CoconutzLog.put(logCnt, "ITE : " + ite.getMessage());
				}
			}
		}
		return null;
	}

	public CoconutParameter makeParameter(JSONObject json)
	{
		// parameter 없을 경우
		Object parametertest = null;
		JSONObject parameter = null;
		JSONArray parameterArray = null;
		try {
			parameter = (JSONObject) json.get("parameter");
		} catch (JSONException e1) {
			try {
				parameter = new JSONObject("{\"empty\":\"empty\"}");
			} catch (JSONException e) {
				e.printStackTrace();
			}				
		} catch(Exception e){
			try{
				parameterArray = json.getJSONArray("parameter");
				return new CoconutParameter(parameterArray);				
			}
			catch(JSONException e2){
			}

		}
		if (G.DEBUG) { G.debugPrint("parameter : " + parameter.toString()); }

		return new CoconutParameter(parameter);
	}
	
}

