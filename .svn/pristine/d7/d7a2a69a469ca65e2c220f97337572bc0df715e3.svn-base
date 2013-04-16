package coconutz.ForParser;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class CoconutzAjax extends CoconutzSetting{
	private String url = super.url;
	private String username = super.username;
	private String funcname = super.coconutdb;

	private boolean getJQuery(String jsname,String packagename, String classname,String param){
		try {
			/*------------------- output -------------------*/
			BufferedWriter out = new BufferedWriter(new FileWriter(jsname+".html"));
			String s = 
					"<script src=\"http://code.jquery.com/jquery-1.9.1.min.js\"></script>"
					+"<script>\r\n"
					+"$.ajax({\r\n"
					+"type : \"POST\",\r\n" 
					+"url : \'"+url+"\',\r\n"
					+"cache : false,\r\n" 
					+"dataType:\"json\",\r\n"
					+"data:"				
					+"\'{\"user\" : \""+username+"\", \"service\" : \""+packagename+"\","+"\"daemon\" : \""+classname+"\", \"function\" : \""+funcname+"\","
					+"\"parameter\" : "+param+" }\',\r\n"
					+"success: function(jdata){\r\n"
					+"document.getElementById(\"a\").innerHTML = jdata.data;"
					+"},\r\n"
					+"error:function(xhr,status,e){\r\n"
					+"alert('Error');\r\n"
					+"},\r\n"
					+"});\r\n" 
					+"</script>"
					+"<div id = \"a\">"
					+"</div>"
					; 
			   //에러 발생시 처리함수
			out.write(s); out.newLine();
			out.close();
			return true;
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
			return false;
		}
	}
	private boolean getPolling(String jsname,String packagename, String classname,String param){
		try {
			/*------------------- output -------------------*/
			BufferedWriter out = new BufferedWriter(new FileWriter(jsname+".html"));
			String s = 
					"<script src=\"http://code.jquery.com/jquery-1.9.1.min.js\"></script>"
					+"<script>\r\n" 
					+"(function poll(){" 
					+"$.ajax({\r\n"
					+"type : \"POST\",\r\n" 
					+"url : \'"+url+"\',\r\n"
					+"cache : false,\r\n" 
					+"dataType:\"json\",\r\n"
					+"data:"				
					+"\'{\"user\" : \""+username+"\", \"service\" : \""+packagename+"\","+"\"daemon\" : \""+classname+"\", \"function\" : \""+funcname+"\","
					+"\"parameter\" : "+param+" }\',\r\n"
					+"success: function(jdata){\r\n"
					+"document.getElementById(\"a\").innerHTML = jdata.data;"
					+"},\r\n"
					+"error:function(xhr,status,e){\r\n"
					+"alert('Error');\r\n"
					+"},\r\n" 
					+"complete : poll,\r\n" 
					+"timeout : 70000\r\n" 
					+"});\r\n" 
					+"})();\r\n" 
					+"</script>" 
					+"<div id = \"a\">"
					+"</div>"
					; 
			   //에러 발생시 처리함수
			out.write(s); out.newLine();
			out.close();
			return true;
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
			return false;
		}
	}

	
	protected boolean startParser(String js_name, String package_name,String class_name,String param){
		if( getJQuery(js_name,package_name, class_name ,param) )
			return true;
		else
			return false;
	}
}
