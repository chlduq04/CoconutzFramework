package com.coconutz.HelperRef;

import org.json.JSONException;

import com.coconutz.Coconut;
import com.coconutz.CoconutParameter;
import com.coconutz.CoconutzCLI;
import com.coconutz.CoconutzDataBase;
import com.coconutz.CoconutzRunnableClass;
import com.coconutz.G;

public class CoconutzPagination {
	private CoconutzCLI cli;
	private volatile static CoconutzPagination instance = null;

	
	public CoconutzPagination(){
		cli = new CoconutzCLI("../../../index.php", "", "/coconutzAPI_Class/Pagination");
	}	

	public static CoconutzPagination getInstance() {
		if (instance == null) {
			synchronized(CoconutzPagination.class){
				if (instance == null) {
					instance = new CoconutzPagination();
					CoconutzRunnableClass.addRunnableClass(getInstance());
				}
			}
		}
		return instance;
	}
	public Coconut getPagination(CoconutParameter parameter){
		if (G.DEBUG) { G.debugPrint(parameter.toString()); }		
			return new Coconut("String",cli.console("String", "generatePagination", parameter ));		
	}
	public Coconut getPaginationCustomize(CoconutParameter parameter){
		if (G.DEBUG) { G.debugPrint(parameter.toString()); }		
			return new Coconut("String",cli.console("String", "generatePaginationCustom", parameter ));		
	}
/*	
	public Coconut getTest(CoconutParameter parameter){
		if (G.DEBUG) { G.debugPrint(parameter.toString()); }	
		return new Coconut("String",cli.console("String", "generateTest", parameter));
	}
	public Coconut getTableFromDB(CoconutParameter parameter){
		if (G.DEBUG) { G.debugPrint(parameter.toString()); }		
		try {
			return new Coconut("String",cli.console("String", "generateFromDB", new CoconutParameter(new CoconutzDataBase(parameter.getJsonArray()).getJson())));
		} catch (JSONException e) {
			e.printStackTrace();
			return new Coconut("String",cli.console("String", "generateFromDB", parameter ));		
		}
	}	
	*/
}
