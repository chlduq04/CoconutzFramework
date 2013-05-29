package com.coconutz.HelperRef;

import coconutz.ForParser.CoconutzAjax;

import com.coconutz.Coconut;
import com.coconutz.CoconutParameter;
import com.coconutz.CoconutzCLI;
import com.coconutz.CoconutzQuery;
import com.coconutz.CoconutzRunnableClass;
import com.coconutz.G;
/*********************************************************************************************
* @brief XML
* @file  CoconutzXml.java
* @author  CHOIUNGYEOP
* @date  2013. 5.
*********************************************************************************************/
public class CoconutzXml extends CoconutzAjax{
	private CoconutzCLI cli;
	private volatile static CoconutzXml instance = null;
	/*******************************************************************************************************
	 * @brief CoconutzXml의 생성자
	 * @method CoconutzXml
	 * @file CoconutzXml.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzXml(){
		cli = new CoconutzCLI("../../../index.php", "", "/coconutzAPI_helper/xml");
	}
	/*******************************************************************************************************
	 * @brief CoconutzXml의 생성자
	 * @method getInstance
	 * @file CoconutzXml.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
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
	/*******************************************************************************************************
	 * @brief XML을 얻을 수 있다.
	 * @method getXml
	 * @file CoconutzXml.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public Coconut getXml(CoconutParameter cp){ 
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/xml/getXml\t"+ cp.toString()); }
		return new Coconut(cli.console("getXml", cp));
	}
}
