package com.newService;

import com.coconutz.CoconutzQuery;
import com.coconutz.CoconutzRunnableClass;
import com.coconutz.DriverRef.DB.CoconutzDB;
import com.coconutz.HelperRef.CoconutzCaptcha;
import com.coconutz.HelperRef.CoconutzDate;
import com.coconutz.HelperRef.CoconutzForm;
import com.coconutz.HelperRef.CoconutzTable;

public class MyRunnableClass extends CoconutzRunnableClass {
	
	public MyRunnableClass() {
		defaultClass();
		userClass();
	}

	private void defaultClass() {
		CoconutzCaptcha captcha = CoconutzCaptcha.getInstance();
		CoconutzDate date= CoconutzDate.getInstance();	
		CoconutzTable table = CoconutzTable.getInstance();
	}
	
	private void userClass() {
		addRunnableClass( new Mytest(), "MytestService" );
		addRunnableClass( new mytest4(), "Mytest4Service" );
		addRunnableClass( new FormTest(), "FormTestService" );
		addRunnableClass( new mytest5(), "Mytest5Service");
//		addRunnableClass( new mytest3() );		
//		addRunnableClass(new Object1);
//		addRunnableClass(new Object2);
	}
}
