package com.coconutz.HelperRef;

import coconutz.ForParser.CoconutzAjax;

import com.coconutz.Coconut;
import com.coconutz.CoconutParameter;
import com.coconutz.CoconutzCLI;
import com.coconutz.CoconutzQuery;
import com.coconutz.CoconutzRunnableClass;
import com.coconutz.G;

public class CoconutzXml extends CoconutzAjax{
	private CoconutzCLI cli;
	private volatile static CoconutzXml instance = null;
	
	private CoconutzXml(){
		cli = new CoconutzCLI("../../../index.php", "", "/coconutzAPI_helper/xml");
	}
	
	public static CoconutzXml getInstance() {
		if (instance == null) {
			synchronized(CoconutzXml.class){
				if (instance == null) {
					instance = new CoconutzXml();
					CoconutzRunnableClass.addRunnableClass(getInstance());
				}
			}
			
		}
		return instance;
	}

	public Coconut getXml(CoconutParameter cp){ 
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/xml/getXml\t"+ cp.toString()); }
		return new Coconut("String",cli.console("String", "getXml", cp));
	}
}
