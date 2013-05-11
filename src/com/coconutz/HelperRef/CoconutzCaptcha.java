package com.coconutz.HelperRef;

import org.json.JSONException;
import org.json.JSONObject;

import coconutz.ForParser.CoconutzAjax;

import com.coconutz.Coconut;
import com.coconutz.CoconutParameter;
import com.coconutz.CoconutzCLI;
import com.coconutz.CoconutzQuery;
import com.coconutz.CoconutzRunnableClass;
import com.coconutz.G;

public class CoconutzCaptcha extends CoconutzAjax{
	private CoconutzCLI cli;
	private volatile static CoconutzCaptcha instance = null;
	
	private CoconutzCaptcha(){
		cli = new CoconutzCLI("../../../index.php", "", "/coconutzAPI_helper/captcha");
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
		try {
			new CoconutParameter(new JSONObject("{\"1\":\"*\",\"2\":\"table\"}"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new JSONObject(cli.console("String", "getCaptcha", cp));
		return new Coconut("String",cli.console("String", "getCaptcha", cp));
	}
	
	public Coconut getFormCaptcha(CoconutParameter cp){ 
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/captcha/getFormCaptcha\t"+ cp.toString()); }		
		return new Coconut("String",cli.console("String", "getFormCaptcha", cp));
	}

}
