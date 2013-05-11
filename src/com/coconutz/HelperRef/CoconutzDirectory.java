package com.coconutz.HelperRef;

import coconutz.ForParser.CoconutzAjax;

import com.coconutz.Coconut;
import com.coconutz.CoconutParameter;
import com.coconutz.CoconutzCLI;
import com.coconutz.CoconutzQuery;
import com.coconutz.CoconutzRunnableClass;
import com.coconutz.G;

public class CoconutzDirectory extends CoconutzAjax{
	private CoconutzCLI cli;
	private volatile static CoconutzDirectory instance = null;
	
	private CoconutzDirectory(){
		cli = new CoconutzCLI("../../../index.php", "", "/coconutzAPI_helper/directory");
	}
	
	public static CoconutzDirectory getInstance() {
		if (instance == null) {
			synchronized(CoconutzDirectory.class){
				if (instance == null) {
					instance = new CoconutzDirectory();
					CoconutzRunnableClass.addRunnableClass(getInstance());
				}
			}
			
		}
		return instance;
	}

	public Coconut directoryMap(CoconutParameter cp){ 
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/directory/directoryMap\t"+ cp.toString()); }
		return new Coconut("String",cli.console("String", "directoryMap", cp));
	}
}
