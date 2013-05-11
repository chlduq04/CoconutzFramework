package com.coconutz.HelperRef;

import coconutz.ForParser.CoconutzAjax;

import com.coconutz.Coconut;
import com.coconutz.CoconutParameter;
import com.coconutz.CoconutzCLI;
import com.coconutz.CoconutzQuery;
import com.coconutz.CoconutzRunnableClass;
import com.coconutz.G;

public class CoconutzNumber extends CoconutzAjax{
	private CoconutzCLI cli;
	private volatile static CoconutzNumber instance = null;
	
	private CoconutzNumber(){
		cli = new CoconutzCLI("../../../index.php", "", "/coconutzAPI_helper/number");
	}
	
	public static CoconutzNumber getInstance() {
		if (instance == null) {
			synchronized(CoconutzNumber.class){
				if (instance == null) {
					instance = new CoconutzNumber();
					CoconutzRunnableClass.addRunnableClass(getInstance());
				}
			}
			
		}
		return instance;
	}

	public Coconut getNumber(CoconutParameter cp){ 
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/number/getNumber\t"+ cp.toString()); }
		return new Coconut("String",cli.console("String", "getNumber", cp));
	}
}
