package com.newService;

import coconutz.ForParser.CoconutzMakeAjax;

import com.coconutz.Coconut;
import com.coconutz.CoconutParameter;
import com.coconutz.CoconutzQuery;
import com.coconutz.DriverRef.DB.CoconutzDB;
import com.coconutz.HelperRef.CoconutzTesthelper;

public class mytest4 extends CoconutzTesthelper{
	public mytest4() {
		super(CREATETABLE.TABLE_START("table4").INT("id", 0).AUTO_INCREMENT().TEXT("type").TEXT("value").TABLE_END());
		CoconutAjax_db("testQuery1", QUERY.select("*").from("table4").where("id", "1").or_where("id", "3").POP());
	}
	public Coconut _query(CoconutParameter parameter){
		return super.query(parameter);
	}
	public Coconut _testQuery(CoconutParameter parameter){
		return super.Select(parameter);
	}
	public Coconut _testquququ(CoconutParameter parameter){
		return super.SelectMax(parameter);		
	}
	public Coconut testQuery1(CoconutParameter parameter){
		return super.CoconutAjax_result(parameter);
	}
	public Coconut _testInsert(CoconutParameter parameter){
		return super.insertTable(parameter);
	}
	
	
}
//OrderBy, Like, Where, Join, SelectMin, SelectMax,Select
