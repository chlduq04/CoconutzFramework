package com.coconutz;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.json.JSONObject;

public class CoconutzCLI{
	private static String OS;
	private int os_type;  
	private String ProjPath = "";
	private String coconutz_prefix = "";
	private String coconutz_php="";
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

	public Object console( String type, String function, CoconutParameter param ){		
		Runtime rt = Runtime.getRuntime();
		Process p;
		String execStr = null;
		String result = "";
		try{
			switch(os_type){
			case 0:
				execStr = "cmd /c cd/&cd "+ProjPath+"&php index.php "+coconutz_php+" "+coconutz_prefix+function+" "+param.getEncodingParam()+"";
				break;
			case 1:
				execStr = "php "+ProjPath+" "+coconutz_php+"/"+coconutz_prefix+function+" "+param.getEncodingParam()+"";
				break;
			case 2:
				execStr = "php "+ProjPath+" "+coconutz_php+"/"+coconutz_prefix+function+" "+param.getEncodingParam()+"";
				break;
			default:
				execStr = "php "+ProjPath+" "+coconutz_php+"/"+coconutz_prefix+function+" "+param.getEncodingParam()+"";
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

	public String console( String function, CoconutParameter param ){
		Runtime rt = Runtime.getRuntime();
		String result = null;
		Process p;
		try{
			switch(os_type){
			case 0:
				p = rt.exec("cmd /c cd/&cd "+ProjPath+"&php index.php "+coconutz_php+" "+coconutz_prefix+function+" "+param.getEncodingParam()+"");
				break;
			case 1:
				p = rt.exec("php "+ProjPath+" "+coconutz_php+" "+coconutz_prefix+function+" "+param.getEncodingParam()+"");
				break;
			case 2:
				p = rt.exec("php "+ProjPath+" "+coconutz_php+" "+coconutz_prefix+function+" "+param.getEncodingParam()+"");
				break;
			default:
				p = rt.exec("php "+ProjPath+" "+coconutz_php+" "+coconutz_prefix+function+" "+param.getEncodingParam()+"");
				break;
			}
			InputStream in = p.getInputStream();	
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = br.readLine()) != null) {
				result += line;
			}
			in.close();
			return result;
		}
		catch (IOException e) {
			e.printStackTrace();
			return "Error!";
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
