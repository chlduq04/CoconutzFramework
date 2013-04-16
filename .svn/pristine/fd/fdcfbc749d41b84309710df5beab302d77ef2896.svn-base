package com.coconutz.HelperRef;

import com.coconutz.Coconut;
import com.coconutz.CoconutParameter;
import com.coconutz.CoconutzCLI;
import com.coconutz.CoconutzRunnableClass;
import com.coconutz.G;

public class CoconutzCaptcha {
	private CoconutzCLI cli;
	private volatile static CoconutzCaptcha instance = null;
	
	private CoconutzCaptcha(){
		cli = new CoconutzCLI("../../../index.php", "", "/coconutzAPI_helper/capcha");
	}
	
	public static CoconutzCaptcha getInstance() {
		if (instance == null) {
			synchronized(CoconutzCaptcha.class){
				if (instance == null) {
					instance = new CoconutzCaptcha();
					CoconutzRunnableClass.addRunnableClass(getInstance());
				}
			}
		}
		return instance;
	}

	public Coconut getCaptcha(CoconutParameter cp){ 
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/captcha/getCaptcha\t"+ cp.toString()); }
		return new Coconut("String",cli.console("String", "getCaptcha", cp));
	}
	
	public Coconut getFormCaptcha(CoconutParameter cp){ 
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/captcha/getFormCaptcha\t"+ cp.toString()); }		
		return new Coconut("String",cli.console("String", "getFormCaptcha", cp));
	}
}
