package com.newService;

import com.coconutz.Coconut;
import com.coconutz.CoconutParameter;
import com.coconutz.CoconutzQuery;
import com.coconutz.HelperRef.CoconutzTesthelper;

public class mytest9 extends CoconutzTesthelper{
	public mytest9(){
		CoconutAjax("Plus","A,B");
		CoconutAjax("Minus","A,B");
	}	

	public Coconut Plus(CoconutParameter parameter){
		int A = Integer.parseInt( parameter.get("A") );
		int B = Integer.parseInt( parameter.get("B") );
		int C = A + B;
		Coconut coconut = new Coconut<Integer>("int", C);
		return coconut;
	}
	public Coconut Minus(CoconutParameter parameter){
		int A = Integer.parseInt( parameter.get("A") );
		int B = Integer.parseInt( parameter.get("B") );
		int C = A - B;
		Coconut coconut = new Coconut<Integer>("int", C);
		return coconut;
	}
}
