package com.coconutz;

import com.coconutz.Server.ServerHandler;

public class CoconutzRunnableClass {

	public CoconutzRunnableClass() {
		defaultClass();
		userClass();
	}
	
	private void defaultClass() {
		
	}
	
	private void userClass() {
		
	}
	
	public static void addRunnableClass(Object obj) {
//		String objStr = obj.toString();
//		objStr = objStr.substring(0, objStr.indexOf("@"));
		ServerHandler.usedClassList.put(obj.getClass().getSimpleName(), obj);		
		if (G.DEBUG) { G.debugPrint("addRunnableClass\t"+ obj.getClass().getSimpleName()); }
	}
	public static String getRunnableClass(String str){
		return ServerHandler.usedClassList.get(str).toString();
	}
}
