package com.newService;

import com.coconutz.Coconut;
import com.coconutz.CoconutParameter;

import how.to.HelloJar;

public class FormTest {
	
	public Coconut _getString(CoconutParameter parameter){
		HelloJar helloJar = new HelloJar();
		
		Coconut<String> coconut = new Coconut<String>( "String", helloJar.PrintMsg("aaa") );

		return coconut;
	}	
	
	public Coconut _getSum(CoconutParameter parameter){
		HelloJar helloJar = new HelloJar();
		
		Coconut<Long> coconut = new Coconut<Long>( "Long", helloJar.Sum() );

		return coconut;
	}
}
