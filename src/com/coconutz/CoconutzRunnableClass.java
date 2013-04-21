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
	
	public static void addRunnableClass(Object obj, String name) {
//		String objStr = obj.toString();
//		objStr = objStr.substring(0, objStr.indexOf("@"));
		ServerHandler.usedClassList.put(name, obj);
		
		if (G.DEBUG) { G.debugPrint("addRunnableClass\t"+ obj.getClass().getSimpleName()); }
	}
}
