package com.coconutz.Service;

import com.coconutz.Coconut;
import com.coconutz.CoconutParameter;

import how.to.HelloJar;

public class FormTest {
	
	public Coconut _getString(CoconutParameter parameter){
		HelloJar helloJar = new HelloJar();
		
		Coconut coconut = new Coconut(  );
		coconut.put("aa", helloJar.PrintMsg("aaa"));

		return coconut;
	}
	
	public Coconut _getSum(CoconutParameter parameter){
		HelloJar helloJar = new HelloJar();
		
		Coconut coconut = new Coconut(  );
		coconut.put("aa", helloJar.Sum() );

		return coconut;
	}
	
}
