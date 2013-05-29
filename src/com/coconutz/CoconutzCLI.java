package com.coconutz;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.json.JSONException;
import org.json.JSONObject;

import com.coconutz.Service.CoconutzSetting;



public class CoconutzCLI extends CoconutzSetting{
	private static String OS;
	private int os_type;  
	private String ProjPath = "";
	private String coconutz_prefix = "";
	private String coconutz_php="";
	public String dbdriver = "mysql://"+username+":"+password+"@"+hostname+"/"+database+"?char_set=utf8&dbcollat=utf8_general_ci&cache_on=false&cachedir=/path/to/cache" ;

	public CoconutzCLI(String projpath, String prefix, String phpfile){
		ProjPath = projpath;
		coconutz_prefix = prefix;
		coconutz_php = phpfile;
		OS = System.getProperty("os.name").toLowerCase();
		if (isWindows()) {
			os_type = 0;			
		} else if (isMac()) {
			os_type = 1;
		} else if (isUnix()) {
			os_type = 2;
		} else if (isSolaris()) {
			os_type = 3;
		} else {
			os_type = 4;
		}
	}

	int getOSType(){
		return os_type;
	}
	
	private String encoding(String argu){
		try {
			return java.net.URLEncoder.encode(argu, "UTF-8").replaceAll("\\+", "%20").replaceAll("\\*", "%2A");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}		
	}
	
	private boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}
	private boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}
	private boolean isUnix() {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
	}	
	private boolean isSolaris() {
		return (OS.indexOf("sunos") >= 0);
	}

	public String console(String cmd){		
		Runtime rt = Runtime.getRuntime();
		Process p;
		String execStr = null;
		String result = "";
		try{
			switch(os_type){
			case 0:
				//execStr = "cmd /c cd/&cd " + cmd;
				execStr = cmd;
				break;
			case 1:
				execStr = cmd;
				break;
			case 2:
				execStr = cmd;
				break;
			default:
				execStr = cmd;
				break;
			}
			if (G.DEBUG) { G.debugPrint(execStr); }
			p = rt.exec(execStr);
			InputStream in = p.getInputStream();	
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = br.readLine()) != null) {
				result+=line + "\n";
			}
			InputStream es = p.getErrorStream();	
			br = new BufferedReader( new InputStreamReader(es) );
			while ((line = br.readLine()) != null) {
				result+=line + "\n";
			}
			in.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			if (G.DEBUG) { G.debugPrint(e.toString()); }
		}

		if (G.DEBUG) { G.debugPrint(result); }
		return result;

	}
	
	public JSONObject console( String function, CoconutParameter param ){		
		Runtime rt = Runtime.getRuntime();
		Process p;
		String execStr = null;
		String result = "";
		try{
			switch(os_type){
			case 0:
				execStr = "cmd /c cd/&cd "+ProjPath+"&php index.php "+coconutz_php+" "+coconutz_prefix+function+" "+param.getEncodingParam()+" ";
				break;
			case 1:
				//execStr = "bash -c \"cd " + path + "&&" + cmd +"\"";
				execStr = "php "+ProjPath+" "+coconutz_php+"/"+coconutz_prefix+function+" "+param.getEncodingParam()+" ";
				break;
			case 2:
				execStr = "php "+ProjPath+" "+coconutz_php+"/"+coconutz_prefix+function+" "+param.getEncodingParam()+" ";
				break;
			default:
				execStr = "php "+ProjPath+" "+coconutz_php+"/"+coconutz_prefix+function+" "+param.getEncodingParam()+" ";
				break;
			}
			if (G.DEBUG) { G.debugPrint(execStr); }
			p = rt.exec(execStr);
			InputStream in = p.getInputStream();	
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = br.readLine()) != null) {
				result+=line;
			}
			in.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		//if (G.DEBUG) { G.debugPrint("cli return : " + result); }
		try{
			return new JSONObject(result);
		}catch(Exception e){
			try {
				return new JSONObject("{\"error\":\"json fail\"}");
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			System.out.println(e.toString() + "\nCoconutzCLI Error. json fail");
			return null;
		}
	}
	public Object input_json_console( String type, String function, String param ){		
		Runtime rt = Runtime.getRuntime();
		Process p;
		String execStr = null;
		String result = "";
		try{
			switch(os_type){
			case 0:
				execStr = "cmd /c cd/&cd "+ProjPath+"&php index.php "+coconutz_php+" "+coconutz_prefix+function+" "+encoding(param)+" ";
				break;
			case 1:
				//execStr = "bash -c \"cd " + path + "&&" + cmd +"\"";
				execStr = "php "+ProjPath+" "+coconutz_php+"/"+coconutz_prefix+function+" "+encoding(param)+" ";
				break;
			case 2:
				execStr = "php "+ProjPath+" "+coconutz_php+"/"+coconutz_prefix+function+" "+encoding(param)+" ";
				break;
			default:
				execStr = "php "+ProjPath+" "+coconutz_php+"/"+coconutz_prefix+function+" "+encoding(param)+" ";
				break;
			}
			if (G.DEBUG) { G.debugPrint(execStr); }
			p = rt.exec(execStr);
			InputStream in = p.getInputStream();	
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = br.readLine()) != null) {
				result+=line;
			}
			in.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		try{
			if( type == "boolean" ){
				return Boolean.parseBoolean(result.trim());
			}else if( type == "String" ){
				return result;
			}else if( type == "int" ){
				return Integer.parseInt(result.trim());
			}else if( type == "arraylist" ){
				return parser(result);
			}else if( type == "jsonobject" ){
				return new JSONObject(result);
			}else{
				return "";			
			}
		}catch(Exception e){
			return 0;
		}
	}
	public JSONObject console( String function, CoconutParameter param1, String prarm2 ){		
		Runtime rt = Runtime.getRuntime();
		Process p;
		String execStr = null;
		String result = "";
		try{
			switch(os_type){
			case 0:
				execStr = "cmd /c cd/&cd "+ProjPath+"&php index.php "+coconutz_php+" "+coconutz_prefix+function+" "+param1.getEncodingParam()+" "+encoding(dbdriver);
				break;
			case 1:
				//execStr = "bash -c \"cd " + path + "&&" + cmd +"\"";
				execStr = "php "+ProjPath+" "+coconutz_php+"/"+coconutz_prefix+function+" "+param1.getEncodingParam()+" "+encoding(dbdriver);
				break;
			case 2:
				execStr = "php "+ProjPath+" "+coconutz_php+"/"+coconutz_prefix+function+" "+param1.getEncodingParam()+" "+encoding(dbdriver);
				break;
			default:
				execStr = "php "+ProjPath+" "+coconutz_php+"/"+coconutz_prefix+function+" "+param1.getEncodingParam()+" "+encoding(dbdriver);
				break;
			}
			if (G.DEBUG) { G.debugPrint(execStr); }
			p = rt.exec(execStr);
			InputStream in = p.getInputStream();	
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = br.readLine()) != null) {
				result+=line;
			}
			in.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		try{
				return new JSONObject(result);
		}catch(Exception e){
			return null;
		}
	}
	public JSONObject console( String type, String function, String param1, String prarm2 ){		
		Runtime rt = Runtime.getRuntime();
		Process p;
		String execStr = null;
		String result = "";
		try{
			switch(os_type){
			case 0:
				execStr = "cmd /c cd/&cd "+ProjPath+"&php index.php "+coconutz_php+" "+coconutz_prefix+function+" "+encoding(param1)+" "+encoding(dbdriver);
				break;
			case 1:
				//execStr = "bash -c \"cd " + path + "&&" + cmd +"\"";
				execStr = "php "+ProjPath+" "+coconutz_php+"/"+coconutz_prefix+function+" "+encoding(param1)+" "+encoding(dbdriver);
				break;
			case 2:
				execStr = "php "+ProjPath+" "+coconutz_php+"/"+coconutz_prefix+function+" "+encoding(param1)+" "+encoding(dbdriver);
				break;
			default:
				execStr = "php "+ProjPath+" "+coconutz_php+"/"+coconutz_prefix+function+" "+encoding(param1)+" "+encoding(dbdriver);
				break;
			}
			if (G.DEBUG) { G.debugPrint(execStr); }
			p = rt.exec(execStr);
			InputStream in = p.getInputStream();	
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = br.readLine()) != null) {
				result+=line;
			}
			in.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		try{
				return new JSONObject(result);
		}catch(Exception e){
			return null;
		}
	}

	public void consoleLinux(String path, String[] cmd) {	
		Runtime rt = Runtime.getRuntime();
		Process p;
		try{
			if (G.DEBUG) { for (int i=0; i<cmd.length; i++) G.debugPrint(cmd[i]); }
			p = rt.exec(cmd);
			InputStream in = p.getInputStream();	
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			InputStream es = p.getErrorStream();	
			br = new BufferedReader( new InputStreamReader(es) );
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			in.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			if (G.DEBUG) { G.debugPrint(e.toString()); }
		}

	}
	
	private ArrayList<HashMap<String, String>> parser(String argu){
		String first_argu="";
		boolean check = false;
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();
		StringTokenizer token = new StringTokenizer(argu, "},{,[,]");
		StringTokenizer detail_token;
		while(token.hasMoreElements()){
			detail_token = new StringTokenizer(token.nextToken(),"\",:");
			while(detail_token.hasMoreElements()){
				String key = detail_token.nextElement().toString();
				if(detail_token.hasMoreElements()){					
					String value = detail_token.nextElement().toString();
					if(first_argu.equals(key)){
						list.add(map);
						map = new HashMap<>();
					}
					if(!check){
						check = true;
						first_argu = key;
					}
					map.put(key, value);
				}else{
					map.put(key, "");
				}
			}
		}
		list.add(map);		
		return list;
	}

}
