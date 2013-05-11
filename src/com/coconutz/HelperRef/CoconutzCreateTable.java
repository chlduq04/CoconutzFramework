package com.coconutz.HelperRef;

import java.util.StringTokenizer;

import org.json.JSONException;
import org.json.JSONObject;

import com.coconutz.Coconut;
import com.coconutz.CoconutzCLI;
import com.coconutz.CoconutParameter;
import com.coconutz.CoconutzDataBase;

public class CoconutzCreateTable {
	private CoconutzCLI cli = new CoconutzCLI("../../../index.php", "coconutz_", "/coconutAPI_DB/DB");
	private String option = "false";
	private StringBuffer query;
	private StringBuffer name;
	private String tablename;
	
	public CoconutzCreateTable(){
		name = new StringBuffer();
		query = new StringBuffer();
		tablename = "";
	}

	private String[] Stringtoken( String argu ){
		StringTokenizer m = new StringTokenizer(argu, ",");
		String value[] = new String[m.countTokens()];
		int i=0;
		while(m.hasMoreElements()){
			value[i++] = m.nextToken().trim();
		}
		return value;
	}	
	public String names(){
		return name.substring(0, name.length()-1);
	}
	public String[] namesArray(){
		return Stringtoken(name.substring(0, name.length()-1));
	}
	public String getTable(){
		return tablename;
	}
	public StringBuffer getName(){
		return name;
	}
	public StringBuffer QUERY(){
		return query;
	}
	public CoconutzCLI getCli(){
		return cli;
	}
	public String setNameValue( String name ){
		this.name.append(name + ",");
		return name;
	}
	public CoconutzCreateTable TABLE_START( String name ){
		tablename = name; 
		query.append("CREATE TABLE "+name + "( ");
		return this;
	}
	public CoconutzCreateTable TABLE_NAME( String name ){
		tablename = name; 
		query.append("CREATE TABLE "+name + "( ");
		return this;
	}
	public CoconutzCreateTable TABLE_END_RESET(){
		return TABLE_END_RESET("");
	}
	public CoconutzCreateTable TABLE_END_RESET( String option ){
		query.replace(0, query.length(),query.substring(0, query.length()-2));
		query.append(")" + option + ";");
		try {
			cli.console("","query", new CoconutParameter(new CoconutzDataBase(query).getJson()),"");
		} catch (JSONException e) {
			System.out.println("Error!!!");
			e.printStackTrace();
		}
		query = new StringBuffer();
		return this;		
	}
	public CoconutzCreateTable TABLE_END(){
		return TABLE_END("");
	}
	public CoconutzCreateTable TABLE_END( String option ){
		query.replace(0, query.length(),query.substring(0, query.length()-2));
		query.append(")" + option + ";");
		try {
			cli.console("","query", new CoconutParameter(new CoconutzDataBase(query).getJson()),"");
		} catch (JSONException e) {
			System.out.println("Error!!!");
			e.printStackTrace();
		}
		return this;
	}
	public CoconutzCreateTable TABLE_COLUMN( String name, String type, String option ){		
		query.append(setNameValue(name) + " " + type + " " +option +", ");
		return this;
	}
	public CoconutzCreateTable AUTO_INCREMENT(){
		if(!option.equals("false")){
			query.replace(0, query.length(),query.substring(0, query.length()-2));
			query.append(" AUTO_INCREMENT, PRIMARY KEY(" + option + "), ");
			option = "false";
		}
		return this; 		
	}
	public CoconutzCreateTable UNSIGNED(){
		if(!option.equals("false")){
			query.replace(0, query.length(),query.substring(0, query.length()-2));
			query.append(" UNSIGNED, ");
			option = "false";
		}
		return this; 		
	}
	public CoconutzCreateTable ZEROFILL(){
		if(!option.equals("false")){
			query.replace(0, query.length(),query.substring(0, query.length()-2));
			query.append(" ZEROFILL, ");
			option = "false";
		}
		return this; 		
	}
	public CoconutzCreateTable SERIAL_DEFAULT_VALUE(){
		if(!option.equals("false")){
			query.replace(0, query.length(),query.substring(0, query.length()-2));
			query.append(" SERIAL DEFAULT VALUE, ");
			option = "false";
		}
		return this; 		
	}
	public CoconutzCreateTable CHARACTER_SET(){
		if(!option.equals("false")){
			query.replace(0, query.length(),query.substring(0, query.length()-2));
			query.append(" CHARACTER SET, ");
			option = "false";
		}
		return this; 		
	}
	public CoconutzCreateTable BINARY(){
		if(!option.equals("false")){
			query.replace(0, query.length(),query.substring(0, query.length()-2));
			query.append(" BINARY, ");
			option = "false";
		}
		return this; 		
	}


	public CoconutzCreateTable TINYINT( String name, int M ){ 
		query.append(setNameValue(name) +" TINYINT(" + M + "), ");
		option = name;
		return this; 
	};
	//	AUTO_INCREMENT
	//	UNSIGNED, ZEROFILL,
	//	SERIAL DEFAULT VALUE
	//	[0 if NOT NULL]

	public CoconutzCreateTable SMALLINT( String name, int M ){
		query.append(setNameValue(name) +" SMALLINT(" + M + "), ");
		option = name;
		return this;  
	};
	//	AUTO_INCREMENT,
	//	UNSIGNED, ZEROFILL,
	//	SERIAL DEFAULT VALUE
	//	[0 if NOT NULL]

	public CoconutzCreateTable MEDIUMINT( String name, int M ){
		query.append(setNameValue(name) +" MEDIUMINT(" + M + "), ");
		option = name;
		return this;  
	}
	//	AUTO_INCREMENT,
	//	UNSIGNED, ZEROFILL,
	//	SERIAL DEFAULT VALUE
	//	[0 if NOT NULL]

	public CoconutzCreateTable INT( String name, int M ){
		query.append(setNameValue(name) +" INT(" + M + "), ");
		option = name;
		return this;  
	}
	//	AUTO_INCREMENT,
	//	UNSIGNED, ZEROFILL,
	//	SERIAL DEFAULT VALUE
	//	[0 if NOT NULL]

	public CoconutzCreateTable BIGINT( String name, int M ){
		query.append(setNameValue(name) +" BIGINT(" + M + "), ");
		option = name;
		return this;  
	}
	//	AUTO_INCREMENT,
	//	UNSIGNED, ZEROFILL,
	//	SERIAL DEFAULT VALUE
	//	[0 if NOT NULL]

	public CoconutzCreateTable FLOAT( String name, float M ){
		query.append(setNameValue(name) +"  FLOAT(" + M + "), ");
		option = name;
		return this;  
	}	
	//	UNSIGNED, ZEROFILL
	//	[0 if NOT NULL]
	//
	public CoconutzCreateTable FLOAT( String name, int M, int D ){
		query.append(setNameValue(name) +" FLOAT(" + M +", " + D + "), ");
		option = name;
		return this;  
	}
	//	UNSIGNED, ZEROFILL
	//	[0 if NOT NULL]
	//
	public CoconutzCreateTable DOUBLE( String name, int M, int D ){
		query.append(setNameValue(name) +" DOUBLE(" + M +", " + D + "), ");
		option = name;
		return this;  
	}
	//	UNSIGNED, ZEROFILL
	//	[0 if NOT NULL]
	//
	public CoconutzCreateTable DECIMAL( String name, int M, int D){
		query.append(setNameValue(name) +" DECIMAL(" + M +", " + D + "), ");
		option = name;
		return this;  
	}
	//	UNSIGNED, ZEROFILL
	//	[0 if NOT NULL]
	//	
	public CoconutzCreateTable BIT( String name, int M ){
		query.append(setNameValue(name) +" BIT(" + M + "), ");
		option = name;
		return this;  
	}
	//	Prior to 5.03
	//	TINYINT(1) Synonym
	//	[0 if NOT NULL]
	//
	public CoconutzCreateTable CHAR( String name, int M ){
		query.append(setNameValue(name) +" CHAR(" + M+ "), ");
		option = name;
		return this;  
	}
	//	BINARY, CHARACTER SET	
	//	["" if NOT NULL]
	//
	public CoconutzCreateTable VARCHAR( String name, int M ){
		query.append(setNameValue(name) +" VARCHAR(" + M + "), ");
		option = name;
		return this;  
	}	
	//	BINARY, CHARACTER SET	
	//	["" if NOT NULL]
	//
	public CoconutzCreateTable TINYTEXT( String name ){
		query.append(setNameValue(name) +" TINYTEXT, ");
		option = name;
		return this;  
	}	
	//	BINARY, CHARACTER SET	
	//	["" if NOT NULL]
	//	
	public CoconutzCreateTable TEXT( String name ){
		query.append(setNameValue(name) +" TEXT, ");
		option = name;
		return this;  
	}	
	//	BINARY, CHARACTER SET	
	//	["" if NOT NULL]
	//	
	public CoconutzCreateTable MEDIUMTEXT( String name ){
		query.append(setNameValue(name) +" MEDIUMTEXT, ");
		option = name;
		return this;  
	}	
	//	BINARY, CHARACTER SET	
	//	["" if NOT NULL]
	//	
	public CoconutzCreateTable LONGTEXT( String name ){
		query.append(setNameValue(name) +" LONGTEXT, ");
		option = name;
		return this;  
	}	
	//	BINARY, CHARACTER SET	
	//	["" if NOT NULL]
	//	
	public CoconutzCreateTable BINARY(  String name, int M ){
		query.append(setNameValue(name) +" BINARY(" + M + "), ");
		option = name;
		return this;  
	}	
	//	Global Only
	//	["" if NOT NULL]
	//	
	public CoconutzCreateTable VARBINARY(  String name, int M ){
		query.append(setNameValue(name) +" VARBINARY(" + M + "), ");
		option = name;
		return this;  
	}	
	//	Global Only
	//	["" if NOT NULL]
	//	
	public CoconutzCreateTable TINYBLOB( String name ){
		query.append(setNameValue(name) +" TINYBLOB, ");
		option = name;
		return this;  
	}
	//	Global Only
	//	["" if NOT NULL]
	//	
	public CoconutzCreateTable BLOB( String name ){
		query.append(setNameValue(name) +" BLOB, ");
		option = name;
		return this;  
	}
	//	Global Only
	//	["" if NOT NULL]
	//	
	public CoconutzCreateTable MEDIUMBLOB( String name ){
		query.append(setNameValue(name) +" MEDIUMBLOB, ");
		option = name;
		return this;  
	}	
	//	Global Only
	//	["" if NOT NULL]
	//	
	public CoconutzCreateTable LONGBLOB( String name ){
		query.append(setNameValue(name) +" LONGBLOB, ");
		option = name;
		return this;  
	}
	//	Global Only
	//	["" if NOT NULL]
	//	
	public CoconutzCreateTable ENUM( String name ){
		query.append(setNameValue(name) +" ENUM, ");
		option = name;
		return this;  
	}
	//	CHARACTER SET	 
	//  [1st value if NOT NULL]
	public CoconutzCreateTable SET( String name ){
		query.append(setNameValue(name) +" SET, ");
		option = name;
		return this;  
	}
	//	CHARACTER SET	
	//	["" if NOT NULL]
	//	
	public CoconutzCreateTable DATE( String name ){
		query.append(setNameValue(name) +" DATE, ");
		option = name;
		return this;  
	}
	//	Global Only
	//	["0000-00-00" if NOT NULL]
	//	
	public CoconutzCreateTable DATETIME( String name ){
		query.append(setNameValue(name) +" DATETIME, ");
		option = name;
		return this;  
	}	
	//	Global Only
	//	["0000-00-00 00:00:00" if NOT NULL]
	//	
	public CoconutzCreateTable TIME( String name ){
		query.append(setNameValue(name) +" TIME, ");
		option = name;
		return this;  
	}
	//	Global Only
	//	["00:00:00" if NOT NULL]
	//	
	public CoconutzCreateTable TIMESTAMP( ){
		query.append(" TIMESTAMP, ");
		option = "";
		return this;  
	}	
	//	Global Only
	//	Current Date & Time
	//
	public CoconutzCreateTable YEAR( ){
		query.append(" YEAR, ");
		option = "";
		return this;  
	}
	//	Global Only
	//	["0000" if NOT NULL]\
}

