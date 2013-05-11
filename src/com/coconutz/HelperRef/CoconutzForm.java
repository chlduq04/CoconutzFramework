package com.coconutz.HelperRef;

import org.json.JSONException;

import com.coconutz.Coconut;
import com.coconutz.CoconutParameter;
import com.coconutz.CoconutzCLI;
import com.coconutz.CoconutzDataBase;
import com.coconutz.CoconutzRunnableClass;
import com.coconutz.G;

public class CoconutzForm {
	private CoconutzCLI cli;
	private volatile static CoconutzForm instance = null;

	public CoconutzForm(){
		cli = new CoconutzCLI("../../../index.php", "", "/coconutzAPI_Class/HTMLTable");
	}	

	public static CoconutzForm getInstance() {
		if (instance == null) {
			synchronized(CoconutzForm.class){
				if (instance == null) {
					instance = new CoconutzForm();
					CoconutzRunnableClass.addRunnableClass(getInstance());
				}
			}
		}
		return instance;
	}
	public Coconut getTest(CoconutParameter parameter){
		if (G.DEBUG) { G.debugPrint(parameter.toString()); }	
		return new Coconut("String",cli.console("String", "generateTest", parameter));
	}
}
