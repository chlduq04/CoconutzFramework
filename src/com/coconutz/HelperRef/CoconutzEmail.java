package com.coconutz.HelperRef;

import coconutz.ForParser.CoconutzAjax;

import com.coconutz.Coconut;
import com.coconutz.CoconutParameter;
import com.coconutz.CoconutzCLI;
import com.coconutz.CoconutzQuery;
import com.coconutz.CoconutzRunnableClass;
import com.coconutz.G;
/*********************************************************************************************
* @brief DirectoryMap을 만든다.
* @file  CoconutzEmail.java
* @author  KWONEUNJI
* @date  2013. 5.
*********************************************************************************************/
public class CoconutzEmail extends CoconutzAjax{
	private CoconutzCLI cli;
	private volatile static CoconutzEmail instance = null;
	/*******************************************************************************************************
	 * @brief CoconutzEmail의 생성자
	 * @method getInstance
	 * @file CoconutzEmail.java
	 * @author KWONEUNJI
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	private CoconutzEmail(){
		cli = new CoconutzCLI("../../../index.php", "", "/coconutzAPI_helper/email");
	}
	/*******************************************************************************************************
	 * @brief instance을 얻어오고 없으면 생성한다.
	 * @method getInstance
	 * @file CoconutzEmail.java
	 * @author KWONEUNJI
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
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
	/*******************************************************************************************************
	 * @brief email의 형식이 맞는지 검사해 준다.
	 * @method getInstance
	 * @file CoconutzEmail.java
	 * @author KWONEUNJI
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public Coconut validEmail(CoconutParameter cp){ 
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/email/validEmail\t"+ cp.toString()); }
		return new Coconut(cli.console("validEmail", cp));
	}
	/*******************************************************************************************************
	 * @brief 
	 * @method getInstance
	 * @file CoconutzEmail.java
	 * @author KWONEUNJI
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public Coconut sendEmail(CoconutParameter cp){ 
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/email/sendEmail\t"+ cp.toString()); }		
		return new Coconut(cli.console("sendEmail", cp));
	}

}
