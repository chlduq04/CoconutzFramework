package com.coconutz.HelperRef;

import coconutz.ForParser.CoconutzAjax;

import com.coconutz.Coconut;
import com.coconutz.CoconutParameter;
import com.coconutz.CoconutzCLI;
import com.coconutz.CoconutzQuery;
import com.coconutz.CoconutzRunnableClass;
import com.coconutz.G;

public class CoconutzEmail extends CoconutzAjax{
	private CoconutzCLI cli;
	private volatile static CoconutzEmail instance = null;
	
	private CoconutzEmail(){
		cli = new CoconutzCLI("../../../index.php", "", "/coconutzAPI_helper/email");
	}
	
	public static CoconutzEmail getInstance() {
		if (instance == null) {
			synchronized(CoconutzEmail.class){
				if (instance == null) {
					instance = new CoconutzEmail();
					CoconutzRunnableClass.addRunnableClass(getInstance());
				}
			}
			
		}
		return instance;
	}

	public Coconut validEmail(CoconutParameter cp){ 
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/email/validEmail\t"+ cp.toString()); }
		return new Coconut("String",cli.console("String", "validEmail", cp));
	}
	
	public Coconut sendEmail(CoconutParameter cp){ 
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/email/sendEmail\t"+ cp.toString()); }		
		return new Coconut("String",cli.console("String", "sendEmail", cp));
	}

}
