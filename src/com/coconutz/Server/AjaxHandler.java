package com.coconutz.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

import org.json.JSONException;
import org.json.JSONObject;

import com.coconutz.Coconut;
import com.coconutz.CoconutzLog;
import com.coconutz.G;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

public class AjaxHandler {
	
	long logCnt;
	
	public AjaxHandler(long logCnt) {
		this.logCnt = logCnt;
	}

	public JSONObject getAjaxJson(HttpExchange xchg) {
		JSONObject json = null;
		String readLine = "";
		
		InputStream is = xchg.getRequestBody();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;

		try {
			while ( (line = br.readLine()) != null) {
				readLine += line;
			}
		} catch (IOException e) {
			CoconutzLog.put(logCnt, e.getMessage());
		}
		
		try {
			json = new JSONObject(readLine);
		} catch (JSONException e) {
			CoconutzLog.put(logCnt, e.getMessage());
		}		
		return json;
	}

	public void responseAjax(HttpExchange xchg, Coconut responseCoconut) throws IOException {
		JSONObject responseJSON = null;
		
		responseJSON = responseCoconut.responseData();
		

		Headers header = xchg.getResponseHeaders();

		header = setType(responseCoconut.getReturnType(), header);		
		header.set("Access-Control-Allow-Origin", "*");
		header.set("Access-Control-Allow-Headers", "X-Requested-With");//
		header.set("Access-Control-Allow-Methods", "POST");

		xchg = setType(responseCoconut.getReturnType(), xchg, responseJSON);

		OutputStream os = xchg.getResponseBody();

		os = setType(responseCoconut.getReturnType(), os, responseJSON);

		os.flush();
		os.close();
		xchg.close();
		
		System.out.println("\n-----------------------------------------------------------------------\n");
		CoconutzLog.clear(logCnt);
	}

	private Headers setType(String type, Headers header) {
		switch ( type ){
		case "json":
			header.add("Content-Type", "application/json");
			break;
		case "xml":
			header.add("Content-Type", "text/xml");
			break;
		case "raw":
			header.add("Content-Type", "text/plain");
			break;
		case "html":
			header.add("Content-Type", "text/html");
			break;
		default:
			break;
		}
		return header;
	}

	private HttpExchange setType(String type, HttpExchange xchg,
			JSONObject responseJSON) {
		try {
			switch (type) {
			case "json":
				xchg.sendResponseHeaders(200, responseJSON.toString().length());
				break;
			case "xml":
				String jsonData = responseJSON.toString();           
	            XMLSerializer serializer = new XMLSerializer(); 
	            JSON json = JSONSerializer.toJSON( jsonData );
	            String xml = serializer.write( json );  
				xchg.sendResponseHeaders(200, xml.length());
				break;
			case "raw":
				try {
					xchg.sendResponseHeaders(200, responseJSON.get("data").toString().length());
				} catch (JSONException e) {
					xchg.sendResponseHeaders(200, new String("ERROR").length());
					CoconutzLog.put(logCnt, e.getMessage());
				}
				break;
			case "html":
				try {
					xchg.sendResponseHeaders(200, ((JSONObject)responseJSON.get("data")).get("html").toString().length());
				} catch (JSONException e) {
					xchg.sendResponseHeaders(200, new String("ERROR").length());
					CoconutzLog.put(logCnt, e.getMessage());
				}
				break;
			default:
				break;
			}
		} catch (IOException e) {
			CoconutzLog.put(logCnt, e.getMessage());
		}
		return xchg;
	}
	private OutputStream setType(String type, OutputStream os, JSONObject responseJSON) {
		try {
			switch ( type ){
			case "json":
				os.write(responseJSON.toString().getBytes("UTF-8"));
				break;
			case "xml":
				String jsonData = responseJSON.toString();
	            XMLSerializer serializer = new XMLSerializer();
	            JSON json = JSONSerializer.toJSON( jsonData );
	            String xml = serializer.write( json );
				if (G.DEBUG) { G.debugPrint(xml); }
				os.write(xml.toString().getBytes("UTF-8"));
				break;
			case "raw":
				try {
					os.write(responseJSON.get("data").toString().getBytes("UTF-8"));
				} catch (JSONException e) {
					os.write(new String("ERROR").getBytes("UTF-8"));
					CoconutzLog.put(logCnt, e.getMessage());
				}
				break;
			case "html":
				try {
					os.write(((JSONObject)responseJSON.get("data")).get("html").toString().getBytes("UTF-8"));
				} catch (JSONException e) {
					os.write(new String("ERROR").getBytes("UTF-8"));
					CoconutzLog.put(logCnt, e.getMessage());
				}
				break;
			default:
				break;
			}
		} catch (UnsupportedEncodingException e) {
			CoconutzLog.put(logCnt, e.getMessage());
		} catch (IOException e) {
			CoconutzLog.put(logCnt, e.getMessage());
		}
		return os;
	}
}
