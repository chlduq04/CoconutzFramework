package com.coconutz.HelperRef;

import org.json.JSONException;
import org.json.JSONObject;

import com.coconutz.CoconutzCLI;
import com.coconutz.CoconutParameter;
import com.coconutz.CoconutzDataBase;

public class CoconutzCreateTable {
	private CoconutzCLI cli = new CoconutzCLI("../../../index.php", "coconutz_", "/coconutAPI_DB/DB");
	boolean option = false;
	private String query;
	public CoconutzCreateTable(){
		query = "";
	}
	public CoconutzCreateTable( CoconutzCreateTable table ){
		query = table.query();
	}
	public CoconutzCreateTable( String query , boolean option){
		this.option = option;
		this.query = query;
	}
	public CoconutzCreateTable( String query ){
		this.option = false;
		this.query = query;
	}
	public String query(){
		return query;
	}
	public CoconutzCLI getCli(){
		return cli;
	}
	public CoconutzCreateTable table_start( String name ){
		query += "CREATE TABLE "+name + "( ";
		return new CoconutzCreateTable(query);
	}
	public CoconutzCreateTable table_name( String name ){
		query += "CREATE TABLE "+name + "( ";
		return new CoconutzCreateTable(query);
	}
	public CoconutzCreateTable table_end(){
		return table_end("");
		
	}
	public CoconutzCreateTable table_end( String option ){
		query = query.substring(0,query.length()-2);
		query += ")" + option + ";";
		try {
			cli.console("query", new CoconutParameter(new CoconutzDataBase(query).getJson()));
		} catch (JSONException e) {
			System.out.println("Error!!!");
			e.printStackTrace();
		}
		return new CoconutzCreateTable(query);
	}
	public CoconutzCreateTable table_column( String name, String type, String option ){		
		query += name + " " + type + " " +option +", ";
		return new CoconutzCreateTable(query);
	}
	public CoconutzCreateTable AUTO_INCREMENT(){
		if(option){
			query = query.substring(0,query.length()-2);
			query += "AUTO_INCREMENT, ";
		}
		return new CoconutzCreateTable(query); 		
	}
	public CoconutzCreateTable UNSIGNED(){
		if(option){
			query = query.substring(0,query.length()-2);
			query += "UNSIGNED, ";
		}
		return new CoconutzCreateTable(query); 		
	}
	public CoconutzCreateTable ZEROFILL(){
		if(option){
			query = query.substring(0,query.length()-2);
			query += "ZEROFILL, ";
		}
		return new CoconutzCreateTable(query); 		
	}
	public CoconutzCreateTable SERIAL_DEFAULT_VALUE(){
		if(option){
			query = query.substring(0,query.length()-2);
			query += "SERIAL DEFAULT VALUE, ";
		}
		return new CoconutzCreateTable(query); 		
	}
	public CoconutzCreateTable CHARACTER_SET(){
		if(option){
			query = query.substring(0,query.length()-2);
			query += "CHARACTER SET, ";
		}
		return new CoconutzCreateTable(query); 		
	}
	public CoconutzCreateTable BINARY(){
		if(option){
			query = query.substring(0,query.length()-2);
			query += "BINARY, ";
		}
		return new CoconutzCreateTable(query); 		
	}


	public CoconutzCreateTable TINYINT( String name, int M ){ 
		query += name +" TINYINT(" + M + "), ";
		return new CoconutzCreateTable(query,true); 
	};
	//	AUTO_INCREMENT
	//	UNSIGNED, ZEROFILL,
	//	SERIAL DEFAULT VALUE
	//	[0 if NOT NULL]

	public CoconutzCreateTable SMALLINT( String name, int M ){
		query += name +" SMALLINT(" + M + "), ";
		return new CoconutzCreateTable(query,true); 
	};
	//	AUTO_INCREMENT,
	//	UNSIGNED, ZEROFILL,
	//	SERIAL DEFAULT VALUE
	//	[0 if NOT NULL]

	public CoconutzCreateTable MEDIUMINT( String name, int M ){
		query += name +" MEDIUMINT(" + M + "), ";
		return new CoconutzCreateTable(query,true); 
	}
	//	AUTO_INCREMENT,
	//	UNSIGNED, ZEROFILL,
	//	SERIAL DEFAULT VALUE
	//	[0 if NOT NULL]

	public CoconutzCreateTable INT( String name, int M ){
		query += name +" INT(" + M + "), ";
		return new CoconutzCreateTable(query,true); 
	}
	//	AUTO_INCREMENT,
	//	UNSIGNED, ZEROFILL,
	//	SERIAL DEFAULT VALUE
	//	[0 if NOT NULL]

	public CoconutzCreateTable BIGINT( String name, int M ){
		query += name +" BIGINT(" + M + "), ";
		return new CoconutzCreateTable(query,true); 
	}
	//	AUTO_INCREMENT,
	//	UNSIGNED, ZEROFILL,
	//	SERIAL DEFAULT VALUE
	//	[0 if NOT NULL]

	public CoconutzCreateTable FLOAT( String name, float M ){
		query += name +"  FLOAT(" + M + "), ";
		return new CoconutzCreateTable(query,true); 
	}	
	//	UNSIGNED, ZEROFILL
	//	[0 if NOT NULL]
	//
	public CoconutzCreateTable FLOAT( String name, int M, int D ){
		query += name +" FLOAT(" + M +", " + D + "), ";
		return new CoconutzCreateTable(query,true); 
	}
	//	UNSIGNED, ZEROFILL
	//	[0 if NOT NULL]
	//
	public CoconutzCreateTable DOUBLE( String name, int M, int D ){
		query += name +" DOUBLE(" + M +", " + D + "), ";
		return new CoconutzCreateTable(query,true); 
	}
	//	UNSIGNED, ZEROFILL
	//	[0 if NOT NULL]
	//
	public CoconutzCreateTable DECIMAL( String name, int M, int D){
		query += name +" DECIMAL(" + M +", " + D + "), ";
		return new CoconutzCreateTable(query,true); 
	}
	//	UNSIGNED, ZEROFILL
	//	[0 if NOT NULL]
	//	
	public CoconutzCreateTable BIT( String name, int M ){
		query += name +" BIT(" + M + "), ";
		return new CoconutzCreateTable(query,true); 
	}
	//	Prior to 5.03
	//	TINYINT(1) Synonym
	//	[0 if NOT NULL]
	//
	public CoconutzCreateTable CHAR( String name, int M ){
		query += name +" CHAR(" + M+ "), ";
		return new CoconutzCreateTable(query,true); 
	}
	//	BINARY, CHARACTER SET	
	//	["" if NOT NULL]
	//
	public CoconutzCreateTable VARCHAR( String name, int M ){
		query += name +" VARCHAR(" + M + "), ";
		return new CoconutzCreateTable(query,true); 
	}	
	//	BINARY, CHARACTER SET	
	//	["" if NOT NULL]
	//
	public CoconutzCreateTable TINYTEXT2( String name ){
		query += name +" TINYTEXT2, ";
		return new CoconutzCreateTable(query,true); 
	}	
	//	BINARY, CHARACTER SET	
	//	["" if NOT NULL]
	//	
	public CoconutzCreateTable TEXT2( String name ){
		query += name +" TEXT2, ";
		return new CoconutzCreateTable(query,true); 
	}	
	//	BINARY, CHARACTER SET	
	//	["" if NOT NULL]
	//	
	public CoconutzCreateTable MEDIUMTEXT2( String name ){
		query += name +" MEDIUMTEXT2, ";
		return new CoconutzCreateTable(query,true); 
	}	
	//	BINARY, CHARACTER SET	
	//	["" if NOT NULL]
	//	
	public CoconutzCreateTable LONGTEXT2( String name ){
		query += name +" LONGTEXT2, ";
		return new CoconutzCreateTable(query,true); 
	}	
	//	BINARY, CHARACTER SET	
	//	["" if NOT NULL]
	//	
	public CoconutzCreateTable BINARY(  String name, int M ){
		query += name +" BINARY(" + M + "), ";
		return new CoconutzCreateTable(query,true); 
	}	
	//	Global Only
	//	["" if NOT NULL]
	//	
	public CoconutzCreateTable VARBINARY(  String name, int M ){
		query += name +" VARBINARY(" + M + "), ";
		return new CoconutzCreateTable(query,true); 
	}	
	//	Global Only
	//	["" if NOT NULL]
	//	
	public CoconutzCreateTable TINYBLOB( String name ){
		query += name +" TINYBLOB, ";
		return new CoconutzCreateTable(query,true); 
	}
	//	Global Only
	//	["" if NOT NULL]
	//	
	public CoconutzCreateTable BLOB( String name ){
		query += name +" BLOB, ";
		return new CoconutzCreateTable(query,true); 
	}
	//	Global Only
	//	["" if NOT NULL]
	//	
	public CoconutzCreateTable MEDIUMBLOB( String name ){
		query += name +" MEDIUMBLOB, ";
		return new CoconutzCreateTable(query,true); 
	}	
	//	Global Only
	//	["" if NOT NULL]
	//	
	public CoconutzCreateTable LONGBLOB( String name ){
		query += name +" LONGBLOB, ";
		return new CoconutzCreateTable(query,true); 
	}
	//	Global Only
	//	["" if NOT NULL]
	//	
	public CoconutzCreateTable ENUM2( String name ){
		query += name +" ENUM2, ";
		return new CoconutzCreateTable(query,true); 
	}
	//	CHARACTER SET	 
	//  [1st value if NOT NULL]
	public CoconutzCreateTable SET2( String name ){
		query += name +" SET2, ";
		return new CoconutzCreateTable(query,true); 
	}
	//	CHARACTER SET	
	//	["" if NOT NULL]
	//	
	public CoconutzCreateTable DATE( String name ){
		query += name +" DATE, ";
		return new CoconutzCreateTable(query,true); 
	}
	//	Global Only
	//	["0000-00-00" if NOT NULL]
	//	
	public CoconutzCreateTable DATETIME( String name ){
		query += name +" DATETIME, ";
		return new CoconutzCreateTable(query,true); 
	}	
	//	Global Only
	//	["0000-00-00 00:00:00" if NOT NULL]
	//	
	public CoconutzCreateTable TIME( String name ){
		query += name +" TIME, ";
		return new CoconutzCreateTable(query,true); 
	}
	//	Global Only
	//	["00:00:00" if NOT NULL]
	//	
	public CoconutzCreateTable TIMESTAMP( String name ){
		query += " TIMESTAMP, ";
		return new CoconutzCreateTable(query,true); 
	}	
	//	Global Only
	//	Current Date & Time
	//
	public CoconutzCreateTable YEAR( String name ){
		query += " YEAR, ";
		return new CoconutzCreateTable(query,true); 
	}
	//	Global Only
	//	["0000" if NOT NULL]\
}

