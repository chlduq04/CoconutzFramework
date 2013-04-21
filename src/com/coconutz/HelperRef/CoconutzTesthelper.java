package com.coconutz.HelperRef;

import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;

import coconutz.ForParser.CoconutzAjax;
import coconutz.ForParser.CoconutzParser;

import com.coconutz.Coconut;
import com.coconutz.CoconutParameter;
import com.coconutz.CoconutzCLI;
import com.coconutz.CoconutzQuery;
import com.coconutz.G;
import com.coconutz.DriverRef.DB.CoconutzDB;

public class CoconutzTesthelper extends CoconutzAjax{
	private CoconutzCLI cli;
	private String PackageName;
	private String ClassName;
	private CoconutzCaptcha Captcha;
	private CoconutzTable Table;
	
	public CoconutzTesthelper() {
		cli = new CoconutzCLI("../../../index.php", "", "/coconutzAPI_helper/testhelper");
		ClassName = this.getClass().getSimpleName();
		PackageName = this.getClass().getPackage().getName();
		Hashtable<String, String> table = new Hashtable<>();
		//	table.put("SelectAll", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\"table\",\"function\":\"from\"}]");
		//	table.put("SelectAll", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\"table\",\"function\":\"from\"}]");
		//	table.put("SelectAll", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\"table\",\"function\":\"from\"}]");
		//	table.put("SelectAll", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\"table\",\"function\":\"from\"}]");
		File path = new File("");		
		CoconutzParser parser = new CoconutzParser();

		if(parser.parseString(path.getAbsolutePath()+"/src/"+ClassName+".java")){
			Enumeration<String> enumerationKey = table.keys();
			while (enumerationKey.hasMoreElements()) {
				String string = (String) enumerationKey.nextElement();
				if(parser.getResult().contains(string)){
					getAjax(string, table.get(string));
				}
			}
		}
	}
	
	private boolean getAjax( String file_name, CoconutzQuery query2 ){
		startParser(file_name,PackageName, ClassName, query2.getQuery().toString());
		return true;
	}
	private boolean getAjax( String file_name, String json ){
		startParser(file_name,PackageName, ClassName, json );
		return true;
	}

	public Coconut getTest(CoconutParameter parameter){
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/testhelper/getTest\t"+ parameter.getEncodingParam()); }		
		return new Coconut("String",cli.console("String", "getTest", parameter));
	}
	public Coconut setTest(CoconutParameter parameter){ 
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/testhelper/setTest\t"+ parameter.getEncodingParam()); }		
		return new Coconut("String",cli.console("String", "setTest", parameter));
	}
}
