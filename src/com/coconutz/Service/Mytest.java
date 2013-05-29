package com.coconutz.Service;

import org.json.JSONObject;

import com.coconutz.Coconut;
import com.coconutz.CoconutzCLI;
import com.coconutz.CoconutParameter;
import com.coconutz.HelperRef.CoconutzCaptcha;
import com.coconutz.HelperRef.CoconutzHelper;

public class Mytest extends CoconutzHelper {
	public Mytest() {
		
	}
	public Coconut _getTest(CoconutParameter parameter){ 
		return super.GETTEST(parameter);
	}
	public Coconut _setTest(CoconutParameter parameter){ 
		return super.SETTEST(parameter);
	}
	public Coconut useCaptcha(CoconutParameter cp){
		CoconutzCaptcha captcha = CoconutzCaptcha.getInstance();
		Coconut coconut1 = captcha.getCaptcha(cp);
		Coconut coconut2 = captcha.getFormCaptcha(cp);
		//coconut1.append(coconut2);
		return coconut1;
	}
}
