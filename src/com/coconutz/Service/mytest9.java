package com.coconutz.Service;

import com.coconutz.Coconut;
import com.coconutz.CoconutParameter;
import com.coconutz.CoconutzQuery;
import com.coconutz.HelperRef.CoconutzHelper;

public class mytest9 extends CoconutzHelper{
	public mytest9(){
		BRANCH("Plus","A,B");
		BRANCH("Minus","A,B");
	}	

	public Coconut Plus(CoconutParameter parameter){
		int A = Integer.parseInt( parameter.get("A") );
		int B = Integer.parseInt( parameter.get("B") );
		int C = A + B;
		int D = A - B;
		Coconut coconut = new Coconut();
		coconut.put("Plus", C);
		coconut.put("Minus", D);
		return coconut;
	}
}

