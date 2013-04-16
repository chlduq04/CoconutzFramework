package com.coconutz.HelperRef;

import com.coconutz.CoconutParameter;
import com.coconutz.CoconutzCLI;
import com.coconutz.CoconutzRunnableClass;
import com.coconutz.G;

public class CoconutzDate {
	private CoconutzCLI cli;
	private volatile static CoconutzDate instance = null;

	
	private CoconutzDate(){
		cli = new CoconutzCLI("../../../index.php", "", "/coconutzAPI_helper/date");
	}
	
	public static CoconutzDate getInstance() {
		if (instance == null) {
			synchronized(CoconutzDate.class){
				if (instance == null) {
					instance = new CoconutzDate();
					CoconutzRunnableClass.addRunnableClass(getInstance());
				}
			}
		}
		return instance;
	}
	
	
	
	
	public int getNow(CoconutParameter parameter)
	{
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/date/getNow\t"+ parameter.toString()); }		
//		String result = excutePHP.excute("/coconutzAPI_helper/date/getNow", parameter);
		return (int)cli.console("string", "getNow", parameter);
	}
	
	public String getMdate(CoconutParameter parameter)
	{
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/date/getMdate\t"+ parameter.toString()); }
//		String result = excutePHP.excute("/coconutzAPI_helper/date/getMdate", parameter);
		return (String)cli.console("string", "getMdate", parameter);
	}
	
	public String getStandardDate(CoconutParameter parameter)
	{
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/date/getStandardDate\t"+ parameter.toString()); }
//		String result = excutePHP.excute("/coconutzAPI_helper/date/getStandardDate", parameter);
		return (String)cli.console("string", "getStandardDate", parameter);
	}
	
	public String getLocalToGMT(CoconutParameter parameter)
	{
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/date/getLocalToGMT\t"+ parameter.toString()); }
//		String result = excutePHP.excute("/coconutzAPI_helper/date/getLocalToGMT", parameter);
		return (String)cli.console("string", "getLocalToGMT", parameter);
	}
	
	public String getGMTToLocal(CoconutParameter parameter)
	{
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/date/getGMTToLocal\t"+ parameter.toString()); }
//		String result = excutePHP.excute("/coconutzAPI_helper/date/getGMTToLocal", parameter);
		return (String)cli.console("string", "getGMTToLocal", parameter);
	}
	
	public String getMysqlToUnix(CoconutParameter parameter)
	{
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/date/getMysqlToUnix\t"+ parameter.toString()); }
//		String result = excutePHP.excute("/coconutzAPI_helper/date/getMysqlToUnix", parameter);
		return (String)cli.console("string", "getMysqlToUnix", parameter);
	}
	
	public String getUnixToHuman(CoconutParameter parameter)
	{
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/date/getUnixToHuman\t"+ parameter.toString()); }
//		String result = excutePHP.excute("/coconutzAPI_helper/date/getUnixToHuman", parameter);
		return (String)cli.console("string", "getUnixToHuman", parameter);
	}
	
	public String getHumanToUnix(CoconutParameter parameter)
	{
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/date/getHumanToUnix\t"+ parameter.toString()); }
//		String result = excutePHP.excute("/coconutzAPI_helper/date/getHumanToUnix", parameter);
		return (String)cli.console("string", "getHumanToUnix", parameter);
	}
	
	public String getTimespan(CoconutParameter parameter)
	{
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/date/getTimespan\t"+ parameter.toString()); }
//		String result = excutePHP.excute("/coconutzAPI_helper/date/getTimespan", parameter);
		return (String)cli.console("string", "getTimespan", parameter);
	}
	
	public String getDaysInMonth(CoconutParameter parameter)
	{
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/date/getDaysInMonth\t"+ parameter.toString()); }
//		String result = excutePHP.excute("/coconutzAPI_helper/date/getDaysInMonth", parameter);
		return (String)cli.console("string", "getDaysInMonth", parameter);
	}
	
	public String getTimezones(CoconutParameter parameter)
	{
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/date/getTimezones\t"+ parameter.toString()); }
//		String result = excutePHP.excute("/coconutzAPI_helper/date/getTimezones", parameter);
		return (String)cli.console("string", "getTimezones", parameter);
	}
	
	public String getTimezoneMenu(CoconutParameter parameter)
	{
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/date/getTimezoneMenu\t"+ parameter.toString()); }
		return (String)cli.console("string", "getTimezoneMenu", parameter);
	}
}
