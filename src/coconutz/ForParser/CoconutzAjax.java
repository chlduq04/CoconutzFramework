package coconutz.ForParser;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.coconutz.Service.CoconutzSetting;



/*********************************************************************************************
* @brief  Ajax를 만들어 준다.
* @file  CoconutzAjax.java
* @author  CHOIUNGYEOP
* @date  2013. 5.
*********************************************************************************************/
public class CoconutzAjax extends CoconutzSetting{
	private String url = super.url;
	private String username = super.usernames;
	private CoconutzMakeAjax ajax = new CoconutzMakeAjax();

	/*******************************************************************************************************
	 * @brief CoconutzMakeAjax.java를 이용하여 만들어진 ajax를 이용하여 html과 js 파일을 만들어 준다.
	 * @method CoconutAjax
	 * @file CoconutzAjax.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public String CoconutAjax(String prefix,String type, String filename, CoconutzMakeAjax aj ){
		try {
			/*------------------- output -------------------*/
			BufferedWriter out = new BufferedWriter(new FileWriter(prefix+filename+"."+type));
			out.write(aj.popAjax()); 
			out.newLine();
			out.close();
			return prefix+filename+"."+type;
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
			return "";
		}		
	}
	/*******************************************************************************************************
	 * @brief param 파라미터를 직접 작성하여 ajax를 만들 수 있다.
	 * @method getJQuery, getJs, getPoll
	 * @file CoconutzAjax.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	protected String getJQuery( String jsname, String classname, String param){
		try {
			/*------------------- output -------------------*/
			BufferedWriter out = new BufferedWriter(new FileWriter(classname+"_"+jsname+".html"));
			ajax.SCRIPT_INCLUDE("http://code.jquery.com/jquery-1.9.1.min.js").SCRIPT(classname+"_"+jsname,"'POST'","'"+url+"'", false, "'json'", "\'{\"user\" : \""+username+"\", \"class\" : \""+classname+"\", \"function\" : \""+jsname+"\", \"parameter\" : "+param+" }\'", "jdata", "document.getElementById(name).innerHTML = jdata.data.value;", "xhr,status,e", "alert('Error');","<div id = \"a\"></div>");
			out.write(ajax.popAjax()); 
			out.newLine();
			out.close();
			getJs(jsname, classname, param);
			getPoll(jsname, classname, param);
			return classname+"_"+jsname+".html";
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
			return "";
		}
	}
	/*******************************************************************************************************
	 * @brief param 파라미터를 직접 작성하여 ajax를 만들 수 있다.
	 * @method getJQuery, getJs, getPoll
	 * @file CoconutzAjax.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	protected String getJs( String jsname, String classname, String param){
		try {
			/*------------------- output -------------------*/
			BufferedWriter out = new BufferedWriter(new FileWriter("js_"+classname+"_"+jsname+".js"));
			ajax.JS("js_"+classname+"_"+jsname,"'POST'","'"+url+"'", false, "'json'", "\'{\"user\" : \""+username+"\", \"class\" : \""+classname+"\", \"function\" : \""+jsname+"\", \"parameter\" : "+param+" }\'", "jdata", "document.getElementById(name).innerHTML = jdata.data.value;", "xhr,status,e", "alert('Error');","");
			out.write(ajax.popAjax()); 
			out.newLine();
			out.close();
			return "js_"+classname+"_"+jsname+".js";
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
			return "";
		}
	}
	/*******************************************************************************************************
	 * @brief param 파라미터를 직접 작성하여 ajax를 만들 수 있다.
	 * @method getJQuery, getJs, getPoll
	 * @file CoconutzAjax.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	protected String getPoll( String jsname, String classname, String param){
		try {
			/*------------------- output -------------------*/
			BufferedWriter out = new BufferedWriter(new FileWriter("poll_"+classname+"_"+jsname+".js"));
			ajax.POLLING("poll_"+classname+"_"+jsname,"'POST'","'"+url+"'", false, "'json'", "\'{\"user\" : \""+username+"\", \"class\" : \""+classname+"\", \"function\" : \""+jsname+"\", \"parameter\" : "+param+" }\'", "jdata", "document.getElementById(name).innerHTML = jdata.data.value;", "xhr,status,e", "alert('Error');","");
			out.write(ajax.popAjax()); 
			out.newLine();
			out.close();
			return "js_"+classname+"_"+jsname+".js";
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
			return "";
		}
	}
	
	
	
	/*******************************************************************************************************
	 * @brief tags 파라미터를 이용하여 일반 html tag를, param 파라미터를 직접 작성하여 html을 만들 수 있다.
	 * @method getJQuery
	 * @file CoconutzAjax.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	protected boolean getJQuery(String tags,String jsname, String classname, String param){
		try {
			/*------------------- output -------------------*/
			BufferedWriter out = new BufferedWriter(new FileWriter(classname+"_"+jsname+".html"));
			String html = tags + ajax.SCRIPT_INCLUDE("http://code.jquery.com/jquery-1.9.1.min.js").SCRIPT(classname+"_"+jsname,"'POST'", "'"+url+"'", false, "'json'", "\'{\"user\" : \""+username+"\", \"class\" : \""+classname+"\", \"function\" : \""+jsname+"\", \"parameter\" : "+param+" }\'", "jdata", "document.getElementById(name).innerHTML = jdata.data.value;", "xhr,status,e", "alert('Error');","<div id = \"a\"></div>");
			out.write(html); 
			out.newLine();
			out.close();
			return true;
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
			return false;
		}
	}
	/*******************************************************************************************************
	 * @brief polling ajax를 포함하는 html을 만들 수 있다.
	 * @method getPolling
	 * @file CoconutzAjax.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	protected boolean getPolling(String jsname, String classname,String param){
		try {
			/*------------------- output -------------------*/
			BufferedWriter out = new BufferedWriter(new FileWriter(classname+"_"+jsname+".html"));
			ajax.SCRIPT_INCLUDE("http://code.jquery.com/jquery-1.9.1.min.js").SCRIPT_POLLING(classname+"_"+jsname,"'POST'","'"+url+"'", false, "'json'", "\'{\"user\" : \""+username+"\", \"class\" : \""+classname+"\", \"function\" : \""+jsname+"\", \"parameter\" : "+param+" }\'", "jdata", "document.getElementById(\"a\").innerHTML = jdata.data.value;", "xhr,status,e", "alert('Error');", "50000", "<div id = \"a\"></div>");
			out.write(ajax.popAjax()); 
			out.newLine();
			out.close();
			return true;
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
			return false;
		}
	}

	/*******************************************************************************************************
	 * @brief tags 파라미터를 이용하여 일반 html tag를, param 파라미터를 직접 작성하여 polling ajax를 포함하는 html을 만들 수 있다.
	 * @method getPolling
	 * @file CoconutzAjax.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	protected boolean getPolling(String tags, String jsname,String classname,String param){
		try {
			/*------------------- output -------------------*/
			BufferedWriter out = new BufferedWriter(new FileWriter(classname+"_"+jsname+".html"));
			String html = tags + ajax.SCRIPT_INCLUDE("http://code.jquery.com/jquery-1.9.1.min.js").SCRIPT_POLLING(classname+"_"+jsname,"'POST'", "'"+url+"'", false, "'json'", "\'{\"user\" : \""+username+"\", \"class\" : \""+classname+"\", \"function\" : \""+jsname+"\", \"parameter\" : "+param+" }\'", "jdata", "document.getElementById(\"a\").innerHTML = jdata.data.value;", "xhr,status,e", "alert('Error');", "50000", "<div id = \"a\"></div>");
			out.write(html); 
			out.newLine();
			out.close();
			return true;
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
			return false;
		}
	}
	/*******************************************************************************************************
	 * @brief 특수한 경우 사용되는 html을 생성하는 함수로 insert예제를 만드는데 쓰인다. tag파라미터에 insert form을 넣어서 insert를 위한 html을 생성한다.
	 * @method getJsonHtml
	 * @file CoconutzAjax.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	protected String getJsonHtml(String tags,String jsname, String classname,String tablename,String function ){
		try {
			/*------------------- output -------------------*/
			BufferedWriter out = new BufferedWriter(new FileWriter(classname+"_"+jsname+"_"+tablename+".html")); 
			StringBuffer s = new StringBuffer("<script src=\"http://code.jquery.com/jquery-1.9.1.min.js\"></script>\r\n")
			.append(tags+"\r\n")
			.append("<script>\r\n")
			.append("$.fn.serializeObject = function(){\r\n")
			.append("  var o = {};\r\n")
			.append("	o[\"param1\"] = \""+tablename+"\";\r\n")
			.append("	o[\"param2\"] = \"\";\r\n")
			.append("	o[\"param3\"] = \"\";\r\n")
			.append("	o[\"function\"] = \""+function+"\";\r\n")
			.append("	var a = this.serializeArray();\r\n")
			.append("   $.each( a, function(){\r\n")
			.append("		if(o[\"param2\"]===\"\"){\r\n")
			.append("			o[\"param2\"] += this.name;\r\n")
			.append("		}else{		\r\n")
			.append("			o[\"param2\"] += \", \"+this.name;\r\n")
			.append("		}\r\n")
			.append("		if(o[\"param3\"]===\"\"){\r\n")
			.append("			o[\"param3\"] += this.value;\r\n")
			.append("		}else{\r\n")
			.append("			o[\"param3\"] += \", \"+this.value;\r\n")
			.append("		}\r\n")
			.append("   });\r\n")
			.append("   return o;\r\n")
			.append("};\r\n")
			.append("$(function(){\r\n")
			.append("	$(\"#submit\").click(function(){\r\n")
			.append("		$.ajax({\r\n")
			.append("			type : \"POST\",\r\n")
			.append("			url:'http://211.189.127.244:8000/echo',               //데이터를 요청할 페이지\r\n")
			.append("			cache : false,\r\n")
			.append("			dataType:\"json\",\r\n")
			.append("			data:'{ \"user\" : \"")
			.append(username)
			.append("\", \"class\" : \"")
			.append(classname)
			.append("\", \"function\" : \"")
			.append(jsname)
			.append("\", \"parameter\" : ['+ JSON.stringify($('#frmData').serializeObject()) +']}',\r\n")
			.append("			error:function(xhr,status,e){       //에러 발생시 처리함수\r\n")
			.append("				alert('Error');\r\n")
			.append("			},\r\n")
			.append("			success: function(jdata){           //성공시 처리 함수, 인수는 위에서 data를 사용한 경우 \r\n")
			.append("				console.log(jdata);\r\n")
			.append("				document.getElementById(\"a\").innerHTML = jdata.data.value;\r\n")
			.append("			}\r\n")
			.append("		});\r\n")
			.append("	});\r\n")			
			.append("});\r\n")
			.append("</script>\r\n")
			.append("<div id = \"a\">\r\n")
			.append("</div>\r\n");

			
			out.write(s.toString());
			out.newLine();
			out.close();
			return classname+"_"+jsname+"_"+tablename+".html";
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
			return "";
		}
	}
	
	
	
	protected String getMultipartForm( String tags,String jsname, String classname,String tablename ){
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("js_"+classname+"_"+jsname+"_"+tablename+".js"));
			StringBuffer s = new StringBuffer();
			s.append(tags);
			s.append("<html>");
			s.append("<head>");
			s.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
			s.append("<script src=\"http://code.jquery.com/jquery-1.9.1.min.js\"></script>");
			s.append("<script>");
			s.append("	window.onload = function(){");
			s.append("		document.getElementById(\"uploadForm\").onsubmit = function(){");
			s.append("			var fileInput = document.getElementById(\"uploadFile\");");
			s.append("			var fileid = document.getElementById(\"id\");");
			s.append("			var filepassword = document.getElementById(\"password\");");
			s.append("			if(fileInput.files !== undefined){");
			s.append("				var file = fileInput.files[0];");
			s.append("				var formData = new FormData();");
			s.append("				formData.append(\"upload_file\",file);");
			s.append("				formData.append(\"id\",fileid.value);");
			s.append("				formData.append(\"password\",filepassword.value);");
			s.append("				var xhr = new XMLHttpRequest();");
			s.append("				xhr.open(\"POST\",this.getAttribute(\"action\"),true);");
			s.append("				xhr.onreadystatechange = function(){");
			s.append("					if(xhr.readyState == 4 && xhr.status == 200){");
			s.append("					alert(\"파일 업로드 완료\");");
			s.append("					}");
			s.append("				}");
			s.append("				xhr.onprogress = function(evt){");
			s.append("					console.log(\"파일 업로드 % = \"+ (evt.loaded / evt.total) + \"%\");");
			s.append("				}");
			s.append("				xhr.send(formData);");
			s.append("				return false;");
			s.append("			}else{");
			s.append("				document.getElementById(\"uploadForm\").target = \"uploadIFrame\";");
			s.append("			}");
			s.append("		}");
			s.append("		document.getElementById(\"uploadIFrame\").onload = function(){");
			s.append("			alert(\"파일 업로드 완료!\");");
			s.append("		}");
			s.append("	}");
			s.append("</script>");
			s.append("</head>");
			s.append("<body>");
			s.append("	<form id=\"uploadForm\" method=\"post\" enctype=\"multipart/form-data\" action=\"http://211.189.127.244:40024/echo\">");
			s.append("		<input name=\"uploadFile\" id=\"uploadFile\" type =\"file\"/>");
			s.append("		<input type = \"submit\" name=\"action\" value=\"value\"/>");
			s.append("		<input type = \"text\" id=\"id\" name=\"id\" value=\"\"/>");
			s.append("		<input type = \"password\" id=\"password\" name=\"password\" value=\"\"/>");
			s.append("		<iframe id=\"uploadIFrame\" name=\"uploadIFrame\" style=\"display:none;visibility:hidden\"></iframe>");
			s.append("	</form>");
			s.append("</body>");
			s.append("</html>");
			out.write(s.toString());
			out.newLine();
			out.close();
			return "js_"+classname+"_"+jsname+"_"+tablename+".js";
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
			return "";
		} 
		
	}
	/*******************************************************************************************************
	 * @brief 특수한 경우 사용되는 html을 생성하는 함수로 insert예제를 만드는데 쓰인다. tag파라미터에 insert form을 넣어서 insert를 위한 js를 생성한다.
	 * @method getJsonJQuery
	 * @file CoconutzAjax.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	protected String getJsonJQuery( String tags,String jsname, String classname,String tablename ){
		try {
			/*------------------- output -------------------*/
			BufferedWriter out = new BufferedWriter(new FileWriter("js_"+classname+"_"+jsname+"_"+tablename+".js")); 
			StringBuffer s = new StringBuffer("<script src=\"http://code.jquery.com/jquery-1.9.1.min.js\"></script>\r\n")
			.append(tags+"\r\n")
			.append("<script>\r\n")
			.append("$.fn.serializeObject = function(){\r\n")
			.append("  var o = {};\r\n")
			.append("	o[\"param1\"] = \""+tablename+"\";\r\n")
			.append("	o[\"param2\"] = \"\";\r\n")
			.append("	o[\"param3\"] = \"\";\r\n")
			.append("	o[\"function\"] = \"insert\";\r\n")
			.append("	var a = this.serializeArray();\r\n")
			.append("   $.each( a, function(){\r\n")
			.append("		if(o[\"param2\"]===\"\"){\r\n")
			.append("			o[\"param2\"] += this.name;\r\n")
			.append("		}else{		\r\n")
			.append("			o[\"param2\"] += \", \"+this.name;\r\n")
			.append("		}\r\n")
			.append("		if(o[\"param3\"]===\"\"){\r\n")
			.append("			o[\"param3\"] += this.value;\r\n")
			.append("		}else{\r\n")
			.append("			o[\"param3\"] += \", \"+this.value;\r\n")
			.append("		}\r\n")
			.append("   });\r\n")
			.append("   return o;\r\n")
			.append("};\r\n")
			.append("$(function(){\r\n")
			.append("	$(\"#submit\").click(function(){\r\n")
			.append("		$.ajax({\r\n")
			.append("			type : \"POST\",\r\n")
			.append("			url:'http://211.189.127.244:8000/echo',               //데이터를 요청할 페이지\r\n")
			.append("			cache : false,\r\n")
			.append("			dataType:\"json\",\r\n")
			.append("			data:'{ \"user\" : \"")
			.append(username)
			.append("\", \"class\" : \"")
			.append(classname)
			.append("\", \"function\" : \"")
			.append(jsname)
			.append("\", \"parameter\" : ['+ JSON.stringify($('#frmData').serializeObject()) +']}',\r\n")
			.append("			error:function(xhr,status,e){       //에러 발생시 처리함수\r\n")
			.append("				alert('Error');\r\n")
			.append("			},\r\n")
			.append("			success: function(jdata){           //성공시 처리 함수, 인수는 위에서 data를 사용한 경우 \r\n")
			.append("				console.log(jdata);\r\n")
			.append("				document.getElementById(\"a\").innerHTML = jdata.data.value;\r\n")
			.append("			}\r\n")
			.append("		});\r\n")
			.append("	});\r\n")			
			.append("});\r\n")
			.append("</script>\r\n")
			.append("<div id = \"a\">\r\n")
			.append("</div>\r\n");

			
			out.write(s.toString());
			out.newLine();
			out.close();
			return "js_"+classname+"_"+jsname+"_"+tablename+".js";
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
			return "";
		}
	}
	/*******************************************************************************************************
	 * @brief 특수한 경우 사용되는 html을 생성하는 함수로 insert예제를 만드는데 쓰인다. tag파라미터에 insert form을 넣어서 insert를 위한 html을 생성한다.
	 * @method getJsonPolling
	 * @file CoconutzAjax.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	protected String getJsonPolling(String tags, String jsname,String classname,String tablename, String function ){
		try {
			/*------------------- output -------------------*/
			BufferedWriter out = new BufferedWriter(new FileWriter("poll_"+classname+"_"+jsname+"_"+tablename+".html"));
			StringBuffer s = new StringBuffer("<script src=\"http://code.jquery.com/jquery-1.9.1.min.js\"></script>\r\n")
			.append(tags)
			.append("<script>\r\n")
			.append("$.fn.serializeObject = function(){\r\n")
			.append("  var o = {};\r\n")
			.append("	o[\"param1\"] = \""+tablename+"\";\r\n")
			.append("	o[\"param2\"] = \"\";\r\n")
			.append("	o[\"param3\"] = \"\";\r\n")
			.append("	o[\"function\"] = \""+function+"\";\r\n")
			.append("	var a = this.serializeArray();\r\n")
			.append("   $.each( a, function(){\r\n")
			.append("		if(o[\"param2\"]===\"\"){\r\n")
			.append("			o[\"param2\"] += this.name;\r\n")
			.append("		}else{		\r\n")
			.append("			o[\"param2\"] += \", \"+this.name;\r\n")
			.append("		}\r\n")
			.append("		if(o[\"param3\"]===\"\"){\r\n")
			.append("			o[\"param3\"] += this.value;\r\n")
			.append("		}else{\r\n")
			.append("			o[\"param3\"] += \", \"+this.value;\r\n")
			.append("		}\r\n")
			.append("   });\r\n")
			.append("   return o;\r\n")
			.append("};\r\n")
			.append("$(function(){\r\n")
			.append("	$(\"#submit\").click(function poll(){\r\n")
			.append("		$.ajax({\r\n")
			.append("			type : \"POST\",\r\n")
			.append("			url:'http://211.189.127.244:8000/echo',               //데이터를 요청할 페이지\r\n")
			.append("			cache : false,\r\n")
			.append("			dataType:\"json\",\r\n")
			.append("			data:'{ \"user\" : \"")
			.append(username)
			.append("\", \"class\" : \"")
			.append(classname)
			.append("\", \"function\" : \"")
			.append(jsname)
			.append("\", \"parameter\" : ['+ JSON.stringify($('#frmData').serializeObject()) +']}',\r\n")
			.append("			error:function(xhr,status,e){       //에러 발생시 처리함수\r\n")
			.append("				alert('Error');\r\n")
			.append("			},\r\n\r\n")
			.append("			success: function(jdata){           //성공시 처리 함수, 인수는 위에서 data를 사용한 경우 \r\n")
			.append("				console.log(jdata);\r\n")
			.append("				document.getElementById(\"a\").innerHTML = jdata.data.value;\r\n")
			.append("			},\r\n")
			.append("			complete : poll,")
			.append("			timeout : 70000")
			.append("		});\r\n")
			.append("	});\r\n")
			.append("});\r\n")
			.append("</script>\r\n")
			.append("<div id = \"a\">\r\n")
			.append("</div>\r\n");
			//에러 발생시 처리함수
			out.write(s.toString());
			out.newLine();
			out.close();
			return "poll_"+classname+"_"+jsname+"_"+tablename+".html";
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
			return "";
		}
	}
}