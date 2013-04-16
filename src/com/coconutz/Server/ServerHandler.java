package com.coconutz.Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import com.coconutz.Coconut;
import com.coconutz.G;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


public class ServerHandler implements HttpHandler {

	public static Map<String, Object> usedClassList = new HashMap<String, Object>();

	public void handle(HttpExchange xchg) throws IOException {

		if ( xchg.getRequestMethod().compareTo("POST") == 0 ) {
			
			//System.out.println("method : " + xchg.getRequestMethod());	//type get/post			
			checkAjaxOrForm(xchg);
		}
	}

	public void checkAjaxOrForm(HttpExchange xchg) throws IOException{
		
		Coconut responseCoconut = null;
		
		// 헤더 가져오기
		Headers headers = xchg.getRequestHeaders();
		Set<Map.Entry<String, List<String>>> entries = headers.entrySet();

		StringBuffer request = new StringBuffer();
		for (Map.Entry<String, List<String>> entry : entries) {
			request.append(entry.toString() + "\n");	
		}
		
		// 바운더리 구분자 찾기
		int boundary_index = request.lastIndexOf("[multipart/form-data; boundary=");
		
		if (boundary_index == -1){
			//-1 post json ajax
			boundary_index = request.lastIndexOf("[application/x-www-form-urlencoded;");
			AjaxHandler ajaxHandler = new AjaxHandler();
			if (boundary_index != -1) {
				//ajax post json데이터만 들어있음
				
				
				JSONObject requestJson = ajaxHandler.getAjaxJson( xchg );
				
				responseCoconut = ajaxHandler.callFunction(requestJson);
				if (G.DEBUG) { G.debugPrint("call Function return String : " + responseCoconut.getString()); }
			}
			ajaxHandler.responseAjax(xchg, responseCoconut);
		}
		else {
			// != -1 Form File data

			String boundary_str = request.substring(boundary_index+31);
			boundary_index = boundary_str.indexOf("]");
			boundary_str = boundary_str.substring(0, boundary_index);
			
			ArrayList<HashMap<String, String>> formData = new ArrayList<HashMap<String, String>>();
			FormHandler formHandler = new FormHandler();
			
			formData = formHandler.getFormData(xchg, "--"+boundary_str);
			
			//responseForm(xchg, responseCoconut, boundary_str);
		}
		
		/*if ( returnStr == null ) {
			returnStr = "{ \"result\" : \"fail\" }";
			returnJSON = new JSONObject(returnStr);
		} else {
			returnJSON = new JSONObject(returnStr);
		}*/
		
	}
	
	
//	public Object getCoconut(Coconut coconut)
//	{
//		if (coconut.type == "aaaa")
///			return (String) coconut.value;
//	}
	
}

