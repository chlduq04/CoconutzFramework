package coconutz.ForParser;

import java.util.Hashtable;
import java.util.StringTokenizer;
import org.json.JSONObject;

import com.coconutz.Service.CoconutzSetting;


/*********************************************************************************************
 * @brief Ajax를 상세하게 만드는 클래스
 * @file CoconutzMakeAjax.java
 * @author CHOIUNGYEOP
 * @date 2013. 5.
 *********************************************************************************************/
public class CoconutzMakeAjax extends CoconutzSetting {
	private StringBuffer ajax;
	private StringBuffer type;
	private StringBuffer url;
	private StringBuffer cache;
	private StringBuffer dataType;
	private StringBuffer data;
	private StringBuffer success;
	private StringBuffer error;

	/*******************************************************************************************************
	 * @brief CoconutzMakeAjax의 생성자
	 * @method CoconutzMakeAjax
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	public CoconutzMakeAjax() {
		ajax = new StringBuffer();
		type = new StringBuffer("type : ");
		url = new StringBuffer();
		cache = new StringBuffer();
		dataType = new StringBuffer();
		data = new StringBuffer();
		success = new StringBuffer();
		error = new StringBuffer();
	}
	/*******************************************************************************************************
	 * @brief js가 포함된 html 예제파일을 규칙에 맞추어서 생성한다.
	 * @method SCRIPT_MAKE
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	private String SCRIPT_MAKE(String function) {
		ajax.append("function "+function+"(name){\r\n");
		ajax.append("$.ajax({\r\n");
		ajax.append(type);
		ajax.append(url);
		ajax.append(cache);
		ajax.append(dataType);
		ajax.append(data);
		ajax.append(success);
		ajax.append(error);
		ajax.append("});\r\n");
		ajax.append("};\r\n");
		ajax.append(""+function+"('a');");
		return ajax.toString();
	}
	/*******************************************************************************************************
	 * @brief ajax가 포함된 js를 규칙에 맞추어서 생성한다.
	 * @method JS_MAKE
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	private String JS_MAKE(String function){
		ajax.append("function "+function+"(name){\r\n");
		ajax.append("$.ajax({\r\n");
		ajax.append(type);
		ajax.append(url);
		ajax.append(cache);
		ajax.append(dataType);
		ajax.append(data);
		ajax.append(success);
		ajax.append(error);
		ajax.append("});\r\n");
		ajax.append("};\r\n");
		return ajax.toString();
	}
	/*******************************************************************************************************
	 * @brief polling ajax가 포함된 js를 규칙에 맞추어서 생성한다.
	 * @method POLLING_MAKE
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	private String POLLING_MAKE(String function){
		ajax.append("function "+function+"(name){\r\n");		
		ajax.append("(function poll(){\r\n");
		ajax.append("$.ajax({\r\n");
		ajax.append(type);
		ajax.append(url);
		ajax.append(cache);
		ajax.append(dataType);
		ajax.append(data);
		ajax.append(success);
		ajax.append(error);
		ajax.append("complete : poll,\r\n");
		ajax.append("timeout : 70000\r\n");		
		ajax.append("});\r\n");
		ajax.append("})();\r\n");
		ajax.append("};\r\n");
		return ajax.toString();	
	}
	/*******************************************************************************************************
	 * @brief ajax가 포함된 html을 원하는 테그 아이디에  맞게 생성한다.
	 * @method SCRIPT_MAKE
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	private String SCRIPT_MAKE(String function ,String id) {
		ajax.append("function "+function+"(name){\r\n");
		ajax.append("$.ajax({\r\n");
		ajax.append(type);
		ajax.append(url);
		ajax.append(cache);
		ajax.append(dataType);
		ajax.append(data);
		ajax.append(success);
		ajax.append(error);
		ajax.append("});\r\n");
		ajax.append("};\r\n");
		ajax.append(function+"('"+id+"');");
		return ajax.toString();
	}
	/*******************************************************************************************************
	 * @brief ajax가 포함된 js를 원하는 테그 아이디에  맞게 생성한다.
	 * @method JS_MAKE
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	private String JS_MAKE(String function, String id){
		ajax.append("function "+function+"(name){\r\n");
		ajax.append("$.ajax({\r\n");
		ajax.append(type);
		ajax.append(url);
		ajax.append(cache);
		ajax.append(dataType);
		ajax.append(data);
		ajax.append(success);
		ajax.append(error);
		ajax.append("});\r\n");
		ajax.append("};\r\n");
		ajax.append(function+"('"+id+"');");
		return ajax.toString();
	}
	/*******************************************************************************************************
	 * @brief polling ajax가 포함된 js를 원하는 테그 아이디에  맞게 생성한다.
	 * @method POLLING_MAKE
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	private String POLLING_MAKE(String function, String id){
		ajax.append("function "+function+"(name){\r\n");		
		ajax.append("(function poll(){\r\n");
		ajax.append("$.ajax({\r\n");
		ajax.append(type);
		ajax.append(url);
		ajax.append(cache);
		ajax.append(dataType);
		ajax.append(data);
		ajax.append(success);
		ajax.append(error);
		ajax.append("complete : poll,\r\n");
		ajax.append("timeout : 70000\r\n");		
		ajax.append("});\r\n");
		ajax.append("})();\r\n");
		ajax.append("};\r\n");
		ajax.append(function+"('"+id+"');");
		return ajax.toString();	
	}

	/*******************************************************************************************************
	 * @brief polling ajax를 규칙에 맞추어서 생성한다. 각 파라미터에 맞추어 ajax가 생성된다.
	 * @method SCRIPT_POLLING
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	public CoconutzMakeAjax SCRIPT_POLLING(String function, String type, String url,
			Boolean cache, String dataType, String data, String success_param,
			String success_method, String error_param, String error_method,
			String time, String... other) {
		SCRIPT_START();
		TYPE(type);
		URL(url);
		CACHE("" + cache);
		DATATYPE(dataType);
		DATA(data);
		SUCCESS(success_param, success_method);
		ERROR(error_param, error_method);
		ajax.append("(function "+function+"(){\r\n");
		ajax.append("$.ajax({\r\n");
		ajax.append(this.type);
		ajax.append(this.url);
		ajax.append(this.cache);
		ajax.append(this.dataType);
		ajax.append(this.data);
		ajax.append(success);
		ajax.append(error);
		ajax.append("complete : "+function+",\r\n");
		ajax.append("timeout : " + time + "\r\n");
		ajax.append("});\r\n");
		ajax.append("})();\r\n");
		for (String argu : other)
			ajax.append(argu);
		SCRIPT_END();
		return this;
	}

	/*******************************************************************************************************
	 * @brief ajax를 규칙에 맞추어 만든 후에 html을 생성한다.각 파라미터에 맞추어 ajax가 생성된다.
	 * @method SCRIPT
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	public CoconutzMakeAjax SCRIPT(String function, String type, String url, Boolean cache,
			String dataType, String data, String success_param,
			String success_method, String error_param, String error_method,
			String... other) {
		SCRIPT_START();
		TYPE(type);
		URL(url);
		CACHE("" + cache);
		DATATYPE(dataType);
		DATA(data);
		SUCCESS(success_param, success_method);
		ERROR(error_param, error_method);
		SCRIPT_MAKE(function);
		SCRIPT_END();
		for (String argu : other)
			ajax.append(argu);
		return this;
	}
	/*******************************************************************************************************
	 * @brief ajax를 규칙에 맞추어 만든 후에 js를 생성한다.각 파라미터에 맞추어 ajax가 생성된다.
	 * @method JS
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	public CoconutzMakeAjax JS(String function, String type, String url, Boolean cache,
			String dataType, String data, String success_param,
			String success_method, String error_param, String error_method,
			String... other) {
		TYPE(type);
		URL(url);
		CACHE("" + cache);
		DATATYPE(dataType);
		DATA(data);
		SUCCESS(success_param, success_method);
		ERROR(error_param, error_method);
		JS_MAKE(function);
		for (String argu : other)
			ajax.append(argu);
		return this;
	}
	/*******************************************************************************************************
	 * @brief polling ajax를 규칙에 맞추어 만든 후에 js를 생성한다.각 파라미터에 맞추어 ajax가 생성된다.
	 * @method POLLING
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	public CoconutzMakeAjax POLLING(String function, String type, String url, Boolean cache,
			String dataType, String data, String success_param,
			String success_method, String error_param, String error_method,
			String... other) {
		TYPE(type);
		URL(url);
		CACHE("" + cache);
		DATATYPE(dataType);
		DATA(data);
		SUCCESS(success_param, success_method);
		ERROR(error_param, error_method);
		POLLING_MAKE(function);
		for (String argu : other)
			ajax.append(argu);
		return this;
	}
	/*******************************************************************************************************
	 * @brief data 파라미터(String)를 이용하여 json 형태의 parameter을 만든 후에 ajax를 규칙에 맞추어 만든다
	 *        후에 script를 생성한다.
	 * @method SCRIPT
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	public CoconutzMakeAjax SCRIPT(String classname, String jsname, String data, String id) {
		Hashtable<String, Integer> table = new Hashtable<>();
		int i = 0;
		for (String argu : Stringtoken(data)) {
			table.put(argu, ++i);
		}
		SCRIPT_INCLUDE("http://code.jquery.com/jquery-1.9.1.min.js");
		SCRIPT_START();
		TYPE("'POST'");
		URL("'" + super.url + "'");
		CACHE("" + false);
		DATATYPE("'json'");
		DATA("\'{\"user\" : \"" + username + "\", \"class\" : \"" + classname
				+ "\", \"function\" : \"" + jsname + "\", \"parameter\" : "
				+ new JSONObject(table).toString() + " }\'");
		SUCCESS("jdata", "document.getElementById(name).innerHTML = jdata.data.value;");
		ERROR("xhr,status,e", "alert('Error');");
		if(id.equals(""))
			SCRIPT_MAKE(classname+"_"+jsname);
		else
			SCRIPT_MAKE(classname+"_"+jsname,id);			
		SCRIPT_END();
		ajax.append("<div id = \"a\"></div>");
		return this;
	}
	/*******************************************************************************************************
	 * @brief data 파라미터(String)를 이용하여 json 형태의 parameter을 만든 후에 ajax를 규칙에 맞추어 만든다
	 *        후에 script를 생성한다.
	 * @method POLLING
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	public CoconutzMakeAjax POLLING(String classname, String jsname, String data, String id){
		Hashtable<String, Integer> table = new Hashtable<>();
		int i = 0;
		for (String argu : Stringtoken(data)) {
			table.put(argu, ++i);
		}
		SCRIPT_INCLUDE("http://code.jquery.com/jquery-1.9.1.min.js");
		TYPE("'POST'");
		URL("'" + super.url + "'");
		CACHE("" + false);
		DATATYPE("'json'");
		DATA("\'{\"user\" : \"" + username + "\", \"class\" : \"" + classname
				+ "\", \"function\" : \"" + jsname + "\", \"parameter\" : "
				+ new JSONObject(table).toString() + " }\'");
		SUCCESS("jdata", "document.getElementById(name).innerHTML = jdata.data.value;");
		ERROR("xhr,status,e", "alert('Error');");
		if(id.equals(""))
			POLLING_MAKE(classname+"_"+jsname);
		else
			POLLING_MAKE(classname+"_"+jsname, id);
		return this;
	}
	/*******************************************************************************************************
	 * @brief data 파라미터(String)를 이용하여 json 형태의 parameter을 만든 후에 ajax를 규칙에 맞추어 만든다
	 *        후에 script를 생성한다.
	 * @method JS
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	public CoconutzMakeAjax JS(String classname, String jsname, String data, String id){
		Hashtable<String, Integer> table = new Hashtable<>();
		int i = 0;
		for (String argu : Stringtoken(data)) {
			table.put(argu, ++i);
		}
		SCRIPT_INCLUDE("http://code.jquery.com/jquery-1.9.1.min.js");
		TYPE("'POST'");
		URL("'" + super.url + "'");
		CACHE("" + false);
		DATATYPE("'json'");
		DATA("\'{\"user\" : \"" + username + "\", \"class\" : \"" + classname
				+ "\", \"function\" : \"" + jsname + "\", \"parameter\" : "
				+ new JSONObject(table).toString() + " }\'");
		SUCCESS("jdata", "document.getElementById(name).innerHTML = jdata.data.value;");
		ERROR("xhr,status,e", "alert('Error');");
		if(id.equals(""))
			JS_MAKE(classname+"_"+jsname);
		else
			JS_MAKE(classname+"_"+jsname, id);			
		return this;
	}
	/*******************************************************************************************************
	 * @brief data 파라미터를 이용하여 parameter을 만든 후에 ajax를 규칙에 맞추어 만든 후에 script를 생성한다.id를 적으면 그 id에 바로 입력할 수 있도록 js를 생성한다.
	 * @method SCRIPT_INSERT_DATA
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	public CoconutzMakeAjax SCRIPT_INSERT_DATA(String classname, String jsname, String data, String id) {
		SCRIPT_INCLUDE("http://code.jquery.com/jquery-1.9.1.min.js");
		SCRIPT_START();
		TYPE("'POST'");
		URL("'" + super.url + "'");
		CACHE("" + false);
		DATATYPE("'json'");
		DATA("\'{\"user\" : \"" + username + "\", \"class\" : \"" + classname
				+ "\", \"function\" : \"" + jsname + "\", \"parameter\" : "
				+ data + " }\'");
		SUCCESS("jdata", "document.getElementById(name).innerHTML = jdata.data.value;");
		ERROR("xhr,status,e", "alert('Error');");
		if(id.equals(""))
			SCRIPT_MAKE(classname+"_"+jsname);
		else
			SCRIPT_MAKE(classname+"_"+jsname, id);
		SCRIPT_END();
		ajax.append("<div id = \"a\"></div>");
		return this;
	}	
	/*******************************************************************************************************
	 * @brief data 파라미터(String)를 이용하여 json 형태의 parameter을 만든 후에 polling ajax를 규칙에 맞추어 만든다. id를 적으면 그 id에 바로 입력할 수 있도록 js를 생성한다.
	 * @method JS_INSERT_DATA
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	public CoconutzMakeAjax JS_INSERT_DATA(String classname, String jsname, String data, String id) {
		TYPE("'POST'");
		URL("'" + super.url + "'");
		CACHE("" + false);
		DATATYPE("'json'");
		DATA("\'{\"user\" : \"" + username + "\", \"class\" : \"" + classname
				+ "\", \"function\" : \"" + jsname + "\", \"parameter\" : "
				+ data + " }\'");
		SUCCESS("jdata", "document.getElementById(name).innerHTML = jdata.data.value;");
		ERROR("xhr,status,e", "alert('Error');");
		if(id.equals(""))
			JS_MAKE(classname+"_"+jsname);
		else
			JS_MAKE(classname+"_"+jsname, id);
		return this;
	}	
	/*******************************************************************************************************
	 * @brief data 파라미터(String)를 이용하여 json 형태의 parameter을 만든 후에 polling ajax를 규칙에 맞추어 만든다. id를 적으면 그 id에 바로 입력할 수 있도록 js를 생성한다.
	 * @method POLLING_INSERT_DATA
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	public CoconutzMakeAjax POLLING_INSERT_DATA(String classname, String jsname, String data, String id) {
		TYPE("'POST'");
		URL("'" + super.url + "'");
		CACHE("" + false);
		DATATYPE("'json'");
		DATA("\'{\"user\" : \"" + username + "\", \"class\" : \"" + classname
				+ "\", \"function\" : \"" + jsname + "\", \"parameter\" : "
				+ data + " }\'");
		SUCCESS("jdata", "document.getElementById(name).innerHTML = jdata.data.value;");
		ERROR("xhr,status,e", "alert('Error');");
		if(id.equals(""))
			POLLING_MAKE(classname+"_"+jsname);
		else
			POLLING_MAKE(classname+"_"+jsname, id);
		return this;
	}
	/*******************************************************************************************************
	 * @brief "," 를 파싱하여 String형 배열로 반환한다.
	 * @method Stringtoken
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	private String[] Stringtoken(String argu) {
		StringTokenizer m = new StringTokenizer(argu, ",");
		String value[] = new String[m.countTokens()];
		int i = 0;
		while (m.hasMoreElements()) {
			value[i++] = m.nextToken().trim();
		}
		return value;
	}
	/*******************************************************************************************************
	 * @brief js include를 처리한다
	 * @method SCRIPT_INCLUDE
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	public CoconutzMakeAjax SCRIPT_INCLUDE(String path) {
		if (path == null) {
		} else
			ajax.append("<script src=\"" + path + "\"></script>\r\n");
		return this;
	}

	/*******************************************************************************************************
	 * @brief 스크립트를 시작한다 SCRIPT 태그로 시작
	 * @method SCRIPT_START
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	private CoconutzMakeAjax SCRIPT_START() {
		ajax.append("<script>\r\n");
		return this;
	}
	/*******************************************************************************************************
	 * @brief TYPE을 처리한다.
	 * @method TYPE
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	private CoconutzMakeAjax TYPE(String argu) {
		type = new StringBuffer("type : " + argu + ",\r\n");
		return this;
	}
	/*******************************************************************************************************
	 * @brief URL을 처리한다
	 * @method URL
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	private CoconutzMakeAjax URL(String argu) {
		url = new StringBuffer("url : " + argu + ",\r\n");
		return this;
	}
	/*******************************************************************************************************
	 * @brief CACHE를 처리한다
	 * @method CACHE
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	private CoconutzMakeAjax CACHE(String argu) {
		cache = new StringBuffer("cache : " + argu + ",\r\n");
		return this;
	}
	/*******************************************************************************************************
	 * @brief DATATYPE을 처리한다
	 * @method DATATYPE
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	private CoconutzMakeAjax DATATYPE(String argu) {
		dataType = new StringBuffer("dataType : " + argu + ",\r\n");
		return this;
	}
	/*******************************************************************************************************
	 * @brief 전송할 DATA를 처리한다
	 * @method DATA
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	private CoconutzMakeAjax DATA(String argu) {
		data = new StringBuffer("data : " + argu + ",\r\n");
		return this;
	}
	/*******************************************************************************************************
	 * @brief SUCCESS가 일어날 경우를 처리한다
	 * @method SUCCESS
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	private CoconutzMakeAjax SUCCESS(String parameter, String argu) {
		success = new StringBuffer("success : function(" + parameter + "){\r\n"
				+ argu + "\r\n},\r\n");
		return this;
	}
	/*******************************************************************************************************
	 * @brief ERROR이 일어난 경우를 처리한다
	 * @method ERROR
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	private CoconutzMakeAjax ERROR(String parameter, String argu) {
		error = new StringBuffer("error : function(" + parameter + "){\r\n"
				+ argu + "\r\n},\r\n");
		return this;
	}
	/*******************************************************************************************************
	 * @brief 스크립트를 중단한다 /script 태그를 추가한다.
	 * @method SCRIPT_END
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	public CoconutzMakeAjax SCRIPT_END() {
		ajax.append("</script>\r\n");
		return this;
	}

	/*******************************************************************************************************
	 * @brief 만들어진 ajax를 얻는다.
	 * @method getAjax
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	public String getAjax() {
		return ajax.toString();
	}

	/*******************************************************************************************************
	 * @brief 만들어진 ajax를 얻은 후 초기화한다.
	 * @method popAjax
	 * @file CoconutzMakeAjax.java
	 * @author CHOIUNGYEOP
	 * @date 2013. 5.
	 * @param
	 *******************************************************************************************************/
	public String popAjax() {
		String result = ajax.toString();
		ajax = new StringBuffer();
		return result;
	}
}