package com.coconutz.HelperRef;

import com.coconutz.CoconutParameter;
import com.coconutz.CoconutzCLI;
import com.coconutz.CoconutzRunnableClass;
import com.coconutz.G;

public class CoconutzTable {
	private CoconutzCLI cli;
	private volatile static CoconutzTable instance = null;

	
	public CoconutzTable(){
		cli = new CoconutzCLI("../../../index.php", "", "/coconutzAPI_Class/HTMLTable");
	}	

	public static CoconutzTable getInstance() {
		if (instance == null) {
			synchronized(CoconutzDate.class){
				if (instance == null) {
					instance = new CoconutzTable();
					CoconutzRunnableClass.addRunnableClass(getInstance(), "TableHelper");
				}
			}
		}
		return instance;
	}
		
	public int getTableFromDB(CoconutParameter parameter)
	{
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_Class/HTMLTable/getTable\t"+ parameter.toString()); }		
		return (int)cli.console("string", "generateFromDB", parameter);
	}	
}
