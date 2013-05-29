package com.coconutz;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.coconutz.Service.CoconutzSetting;
public class CoconutzLog {

	public static long logCnt = 0;
	
	private static HashMap<Long, String> log = new HashMap<>();
	private static HashMap<Long, Long> logTime = new HashMap<>();

	public static void put(long logCnt, String strLog, int t){
		System.out.println(strLog);
		log.put(logCnt, strLog + "\n");
	}
	
	public static void put(long logCnt, String strLog){
		System.out.println(strLog);
		log.put(logCnt, log.get(logCnt) + strLog + "\n");
	}
	
	public static void putTime(long logCnt, long time){
		logTime.put(logCnt, time);
	}
	
	public static void clear(long logCnt) {
		logEnqueue(log.get(logCnt));
		
		log.remove(logCnt);
		logTime.remove(logCnt);
	}

	public static void logEnqueue(String log) {
		CoconutzCLI cli = new CoconutzCLI("", "", "");
		String cmd1 = null;
		try {
			log = log.replace("\"", "");
			log = log.replace("\\", "%NN");
			String encode = java.net.URLEncoder.encode("{\"service\":\""+ CoconutzSetting.USERID + "_" + CoconutzSetting.SERVICENAME+ "\", \"daemon\":\""+ CoconutzSetting.DAEMONNAME+ "\", \"type\":" + "\"3\"" + ", \"log\":\""+ log + "\"}", "UTF-8").replaceAll("\\+", "%20").replaceAll("\\*", "%2A");
			cmd1 = "php ../../../index.php coconutz_Util LogStorage input "
					+ encode.replaceAll("%0A", "%NL");
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch (cli.getOSType()) {
		case 0:
			log += cmd1;
			cli.console(cmd1);
			break;
		default:
			log += cmd1;
			String cmd3[] = { "/bin/bash", "-c", cmd1 };
			cli.consoleLinux("", cmd3);
			break;
		}
		// php "+ProjPath+" "+coconutz_php+"/"+coconutz_prefix+function+"
		// "+param1.getEncodingParam()+" "+encoding(dbdriver);
	}
	
}
