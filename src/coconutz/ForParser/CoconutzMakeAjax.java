package coconutz.ForParser;

import java.util.Hashtable;
import java.util.StringTokenizer;

import org.json.JSONObject;
	public class CoconutzMakeAjax extends CoconutzSetting{
		private StringBuffer ajax;
		private StringBuffer type;
		private StringBuffer url;
		private StringBuffer cache;
		private StringBuffer dataType;
		private StringBuffer data;
		private StringBuffer success;
		private StringBuffer error;	
		
		public CoconutzMakeAjax(){
			ajax = new StringBuffer();
			type = new StringBuffer("type : ");
			url = new StringBuffer();
			cache = new StringBuffer();
			dataType = new StringBuffer();
			data = new StringBuffer();
			success = new StringBuffer();
			error = new StringBuffer();
		}
		private String SCRIPT_MAKE(){
			ajax.append("$.ajax({\r\n");
			ajax.append(type);
			ajax.append(url);
			ajax.append(cache);
			ajax.append(dataType);
			ajax.append(data);
			ajax.append(success);
			ajax.append(error);
			ajax.append("});\r\n");
			return ajax.toString();
		}
		public CoconutzMakeAjax SCRIPT_POLLING( String type, String url, Boolean cache, String dataType, String data, String success_param, String success_method, String error_param, String error_method,String time, String other){
			SCRIPT_START();
			TYPE(type);
			URL(url);
			CACHE(""+cache);
			DATATYPE(dataType);
			DATA(data);
			SUCCESS(success_param, success_method);
			ERROR(error_param, error_method);
			ajax.append("(function poll(){\r\n");
			ajax.append("$.ajax({\r\n");
			ajax.append(this.type);
			ajax.append(this.url);
			ajax.append(this.cache);
			ajax.append(this.dataType);
			ajax.append(this.data);
			ajax.append(success);
			ajax.append(error);
			ajax.append("complete : poll,\r\n");
			ajax.append("timeout : " + time + "\r\n");
			ajax.append("});\r\n");
			ajax.append("})();\r\n");
			ajax.append(other);
			SCRIPT_END();
			return this;
		}
		public CoconutzMakeAjax SCRIPT( String type, String url, Boolean cache, String dataType, String data, String success_param, String success_method, String error_param, String error_method, String other){
			SCRIPT_START();
			TYPE(type);
			URL(url);
			CACHE(""+cache);
			DATATYPE(dataType);
			DATA(data);
			SUCCESS(success_param, success_method);
			ERROR(error_param, error_method);
			SCRIPT_MAKE();
			SCRIPT_END();
			ajax.append(other);
			return this;
		}
		public CoconutzMakeAjax SCRIPT_INSERT_DATA( String classname, String jsname, String data ){
			SCRIPT_INCLUDE("http://code.jquery.com/jquery-1.9.1.min.js");
			SCRIPT_START();
			TYPE("'POST'");
			URL("'"+super.url+"'");
			CACHE(""+false);
			DATATYPE("'json'");
			DATA("\'{\"user\" : \""+username+"\", \"class\" : \""+classname+"\", \"function\" : \""+jsname+"\", \"parameter\" : "+data+" }\'");
			SUCCESS("jdata", "document.getElementById(\"a\").innerHTML = jdata");
			ERROR("xhr,status,e", "alert('Error');");
			SCRIPT_MAKE();
			SCRIPT_END();
			ajax.append("<div id = \"a\"></div>");
			return this;
		}
		public CoconutzMakeAjax SCRIPT( String classname, String jsname, String data ){
			Hashtable< String, Integer > table = new Hashtable<>();
			int i = 0;
			for( String argu : Stringtoken(data)){
				table.put(argu,++i);
			}
			SCRIPT_INCLUDE("http://code.jquery.com/jquery-1.9.1.min.js");
			SCRIPT_START();
			TYPE("'POST'");
			URL("'"+super.url+"'");
			CACHE(""+false);
			DATATYPE("'json'");
			DATA("\'{\"user\" : \""+username+"\", \"class\" : \""+classname+"\", \"function\" : \""+jsname+"\", \"parameter\" : "+new JSONObject( table ).toString()+" }\'");
			SUCCESS("jdata", "document.getElementById(\"a\").innerHTML = jdata");
			ERROR("xhr,status,e", "alert('Error');");
			SCRIPT_MAKE();
			SCRIPT_END();
			ajax.append("<div id = \"a\"></div>");
			return this;
		}

		private String[] Stringtoken( String argu ){
			StringTokenizer m = new StringTokenizer(argu, ",");
			String value[] = new String[m.countTokens()];
			int i=0;
			while(m.hasMoreElements()){
				value[i++] = m.nextToken().trim();
			}
			return value;
		}	
		public CoconutzMakeAjax SCRIPT_INCLUDE(String path){
			if(path == null){}
			else
				ajax.append("<script src=\""+path+"\"></script>\r\n");		
			return this;
		}
		public CoconutzMakeAjax SCRIPT_DETAIL(String argu){
			ajax.append(argu);
			return this;
		}
		public CoconutzMakeAjax SCRIPT_START(){
			ajax.append("<script>\r\n");
			return this;
		}
		public CoconutzMakeAjax TYPE(String argu){
			type = new StringBuffer("type : " + argu + ",\r\n");
			return this;
		}
		public CoconutzMakeAjax URL(String argu){
			url = new StringBuffer("url : " + argu + ",\r\n");
			return this;
		}
		public CoconutzMakeAjax CACHE(String argu){
			cache = new StringBuffer("cache : " + argu + ",\r\n");
			return this;
		}
		public CoconutzMakeAjax DATATYPE(String argu){
			dataType = new StringBuffer("dataType : " + argu + ",\r\n");
			return this;
		}
		public CoconutzMakeAjax DATA(String argu){
			data = new StringBuffer("data : " + argu + ",\r\n");
			return this;
		}
		public CoconutzMakeAjax SUCCESS(String parameter, String argu){
			success = new StringBuffer("success : function("+parameter+"){\r\n" + argu + "\r\n},\r\n");
			return this;
		}
		public CoconutzMakeAjax ERROR(String parameter, String argu){
			error = new StringBuffer("error : function("+parameter+"){\r\n" + argu + "\r\n},\r\n");
			return this;
		}
		public CoconutzMakeAjax SCRIPT_END(){
			ajax.append("</script>\r\n");
			return this;	
		}
		public String getAjax(){
			return ajax.toString();
		}
		public String popAjax(){
			String result = ajax.toString();
			ajax = new StringBuffer();
			return result;
		}
	}