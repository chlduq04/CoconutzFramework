package com.newService;

import org.json.JSONException;

import com.coconutz.Coconut;
import com.coconutz.CoconutParameter;
import com.coconutz.DriverRef.DB.CoconutzDB;

public class mytest1 extends CoconutzDB{
	public mytest1(){
	}	
	public Coconut _queryTest(CoconutParameter parameter){ 
		return super.query(parameter);
	}
}
