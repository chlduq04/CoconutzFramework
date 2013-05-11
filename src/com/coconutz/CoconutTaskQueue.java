package com.coconutz;

public class CoconutTaskQueue {
	private CoconutzCLI cli;
	private volatile static CoconutTaskQueue instance = null;
	
	private CoconutTaskQueue() {
	}
	public static CoconutTaskQueue getInstance() {
		if (instance == null) {
			synchronized (CoconutTaskQueue.class) {
				if (instance == null) {
					instance = new CoconutTaskQueue();	
				}
			}
		}
		return instance;
	}

	public Coconut checkQueue(CoconutParameter cp) {
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/captcha/getCaptcha\t"+ cp.toString()); }
		Coconut coconut =  new Coconut("String",cli.console("String", "getCaptcha", cp));
		return coconut;
	}
	
	public int upload() {
		return 1;
	}
	
}
