package com.coconutz.Service;

import coconutz.ForParser.CoconutzMakeAjax;

import com.coconutz.Coconut;
import com.coconutz.CoconutParameter;
import com.coconutz.CoconutzQuery;
import com.coconutz.DriverRef.DB.CoconutzDB;
import com.coconutz.HelperRef.CoconutzCreateTable;
import com.coconutz.HelperRef.CoconutzHelper;

public class mytest4 extends CoconutzHelper{
	public mytest4() {
		super(
				new CoconutzCreateTable().TABLE_START("table4").INT("id", 0).AUTO_INCREMENT().TEXT("type").TEXT("value").TABLE_END(),
				new CoconutzCreateTable().TABLE_START("table5").INT("id", 0).AUTO_INCREMENT().TEXT("type").TEXT("value").TABLE_END()
		);
		BRANCH( "Plus","A,B" );		
		DB( "testQuery1", QUERY.SELECT("*").FROM("table4").WHERE("id", "1").OR_WHERE("id", "3").END() );
		DB( "testQuery2", QUERY.SELECT("*").FROM("table5").END() );
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
	
	
	
	public Coconut testQuery1(CoconutParameter parameter){
		return super.DB(parameter);
	}
	
	public Coconut testQuery2(CoconutParameter parameter){
		return super.DB(parameter);
	}
	
	public Coconut _testQuery(CoconutParameter parameter){
		return super.QUERY(parameter);
	}
	
	public Coconut _testSelect(CoconutParameter parameter){	
		return super.SELECT(parameter);
	}
	
	public Coconut _testInsert(CoconutParameter parameter){
		return super.UPLOAD_TABLE(parameter);
	}
	
	public Coconut _query(CoconutParameter parameter){
		return super.SELECT(parameter);
	}
	
	public Coconut _testTable(CoconutParameter parameter){
		return super.GET_TABLE(parameter);
	}
	
	public Coconut _testPage(CoconutParameter parameter){
		return super.GET_PAGINATION(parameter);
	}
	
	public Coconut _testMakePagination(CoconutParameter parameter){
		return super.GET_CUSTOMIZE_PAGINATION(parameter);
	}
}
