package com.newService;

import com.coconutz.Coconut;
import com.coconutz.CoconutParameter;

public class mytest8 {
//	CoconutzCreateTable CREATETABLE = new CoconutzCreateTable();
	public mytest8(){
//		CREATETABLE.TABLE_START("table3").INT("id", 100).AUTO_INCREMENT().TEXT("type").TEXT("value").TABLE_END();
	}
	
	public Coconut plus(CoconutParameter cp) {
		int A = Integer.parseInt( cp.get("A") );
		int B = Integer.parseInt( cp.get("B") );
		int C = A+ B;
		Coconut coconut = new Coconut<Integer>("int", C);
		return coconut;
	}
}
