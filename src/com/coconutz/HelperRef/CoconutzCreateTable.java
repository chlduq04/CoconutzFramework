package com.coconutz.HelperRef;

import java.util.StringTokenizer;
import java.util.Vector;

import org.json.JSONException;
import org.json.JSONObject;

import com.coconutz.Coconut;
import com.coconutz.CoconutzCLI;
import com.coconutz.CoconutParameter;
import com.coconutz.CoconutzDataBase;
/*********************************************************************************************
* @brief  Captcha를 만들 수 있는 함수를 제공한다.
* @file  CoconutzCreateTable.java
* @author  CHOIUNGYEOP
* @date  2013. 5.
*********************************************************************************************/
public class CoconutzCreateTable {
	private CoconutzCLI cli = new CoconutzCLI("../../../index.php", "coconutz_", "/coconutAPI_DB/DB");
	private String option = "false";
	private StringBuffer query;
	private StringBuffer name;
	private String tablename;
	private Vector<String> querys;
	/*******************************************************************************************************
	 * @brief CoconutzCreateTable의 생성자
	 * @method CoconutzCreateTable
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable(){
		name = new StringBuffer();
		query = new StringBuffer();
		querys = new Vector<>();
		tablename = "";
	}

	/*******************************************************************************************************
	 * @brief ","를 기준으로 array타입으로 바꾼다.
	 * @method Stringtoken
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	private String[] Stringtoken( String argu ){
		StringTokenizer m = new StringTokenizer(argu, ",");
		String value[] = new String[m.countTokens()];
		int i=0;
		while(m.hasMoreElements()){
			value[i++] = m.nextToken().trim();
		}
		return value;
	}	
	/*******************************************************************************************************
	 * @brief 테이블의 이름들을 반환한다.
	 * @method namesArray
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public String[] namesArray(){
		return Stringtoken(name.substring(0, name.length()-1));
	}
	/*******************************************************************************************************
	 * @brief 테이블의 이름을 얻는다.
	 * @method getTable
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public String getTable(){
		return tablename;
	}

	/*******************************************************************************************************
	 * @brief 만들어진 쿼리를 얻는다.
	 * @method QUERY
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public StringBuffer QUERY(){
		return query;
	}
	/*******************************************************************************************************
	 * @brief cli를 얻는다.
	 * @method getCli
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCLI getCli(){
		return cli;
	}
	/*******************************************************************************************************
	 * @brief 테이블의 각 타입의 이름을 저장한다.
	 * @method setNameValue
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public String setNameValue( String name ){
		this.name.append(name + ",");
		return name;
	}
	/*******************************************************************************************************
	 * @brief 테이블의 이름을 시작으로 테이블을 만들기 시작한다.
	 * @method TABLE_START
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable TABLE_START( String name ){
		tablename = name; 
		query.append("CREATE TABLE "+name + "( ");
		return this;
	}
	/*******************************************************************************************************
	 * @brief 테이블의 이름을 시작으로 테이블을 만들기 시작한다.
	 * @method TABLE_NAME
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable TABLE_NAME( String name ){
		tablename = name; 
		query.append("CREATE TABLE "+name + "( ");
		return this;
	}
	/*******************************************************************************************************
	 * @brief 테이블을 끝마치는데 리셋을 하지않고 끝마친다.
	 * @method TABLE_END_NOT_RESET
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable TABLE_END_NOT_RESET(){
		return TABLE_END_NOT_RESET("");
	}
	/*******************************************************************************************************
	 * @brief 테이블을 끝마치는데 옵션을 넣되 리셋을 하지않고 끝마친다.
	 * @method TABLE_END_NOT_RESET
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable TABLE_END_NOT_RESET( String option ){
		query.replace(0, query.length(),query.substring(0, query.length()-2));
		query.append(")" + option + ";");
		console();
		return this;		
	}
	/*******************************************************************************************************
	 * @brief 테이블을 끝마치는데 리셋 하고 끝마친다.
	 * @method TABLE_END
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable TABLE_END(){
		return TABLE_END("");
	}
	/*******************************************************************************************************
	 * @brief 테이블을 끝마치는데 리셋 하고 끝마친다.
	 * @method TABLE_END
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable TABLE_END( String option ){
		query.replace(0, query.length(),query.substring(0, query.length()-2));
		query.append(")" + option + ";");
		console();
		query = new StringBuffer();
		return this;
	}
	/*******************************************************************************************************
	 * @brief cli를 이용하여 실행시킨다.
	 * @method console
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public void console(){
		try {
			cli.console("query", new CoconutParameter(new CoconutzDataBase(query).getJson()),"");
		} catch (JSONException e) {
			System.out.println("Error!!!");
			e.printStackTrace();
		}		
	}
	/*******************************************************************************************************
	 * @brief 직접 테이블을 만드는 쿼리를 실행시킨다.
	 * @method TABLE_COLUMN
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable TABLE_COLUMN( String name, String type, String option ){		
		query.append(setNameValue(name) + " " + type + " " +option +", ");
		return this;
	}
	/*******************************************************************************************************
	 * @brief AUTO_INCREMENT의 기능을 활성화 한다.
	 * @method AUTO_INCREMENT
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable AUTO_INCREMENT(){
		if(!option.equals("false")){
			query.replace(0, query.length(),query.substring(0, query.length()-2));
			query.append(" AUTO_INCREMENT, PRIMARY KEY(" + option + "), ");
			option = "false";
		}
		return this; 		
	}
	/*******************************************************************************************************
	 * @brief UNSIGNED의 기능을 활성화 한다.
	 * @method UNSIGNED
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable UNSIGNED(){
		if(!option.equals("false")){
			query.replace(0, query.length(),query.substring(0, query.length()-2));
			query.append(" UNSIGNED, ");
			option = "false";
		}
		return this; 		
	}
	/*******************************************************************************************************
	 * @brief ZEROFILL의 기능을 활성화 한다.
	 * @method ZEROFILL
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable ZEROFILL(){
		if(!option.equals("false")){
			query.replace(0, query.length(),query.substring(0, query.length()-2));
			query.append(" ZEROFILL, ");
			option = "false";
		}
		return this; 		
	}
	/*******************************************************************************************************
	 * @brief SERIAL_DEFAULT_VALUE의 기능을 활성화 한다.
	 * @method SERIAL_DEFAULT_VALUE
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable SERIAL_DEFAULT_VALUE(){
		if(!option.equals("false")){
			query.replace(0, query.length(),query.substring(0, query.length()-2));
			query.append(" SERIAL DEFAULT VALUE, ");
			option = "false";
		}
		return this; 		
	}
	/*******************************************************************************************************
	 * @brief CHARACTER_SET의 기능을 활성화 한다.
	 * @method CHARACTER_SET
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable CHARACTER_SET(){
		if(!option.equals("false")){
			query.replace(0, query.length(),query.substring(0, query.length()-2));
			query.append(" CHARACTER SET, ");
			option = "false";
		}
		return this; 		
	}
	/*******************************************************************************************************
	 * @brief BINARY의 기능을 활성화 한다.
	 * @method BINARY
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable BINARY(){
		if(!option.equals("false")){
			query.replace(0, query.length(),query.substring(0, query.length()-2));
			query.append(" BINARY, ");
			option = "false";
		}
		return this; 		
	}


	/*******************************************************************************************************
	 * @brief TINYINT의 기능을 활성화 한다.
	 * @method TINYINT
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable TINYINT( String name, int M ){ 
		query.append(setNameValue(name) +" TINYINT(" + M + "), ");
		option = name;
		return this; 
	};
	//	AUTO_INCREMENT
	//	UNSIGNED, ZEROFILL,
	//	SERIAL DEFAULT VALUE
	//	[0 if NOT NULL]

	/*******************************************************************************************************
	 * @brief SMALLINT의 기능을 활성화 한다.
	 * @method SMALLINT
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable SMALLINT( String name, int M ){
		query.append(setNameValue(name) +" SMALLINT(" + M + "), ");
		option = name;
		return this;  
	};
	//	AUTO_INCREMENT,
	//	UNSIGNED, ZEROFILL,
	//	SERIAL DEFAULT VALUE
	//	[0 if NOT NULL]

	/*******************************************************************************************************
	 * @brief MEDIUMINT의 기능을 활성화 한다.
	 * @method MEDIUMINT
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable MEDIUMINT( String name, int M ){
		query.append(setNameValue(name) +" MEDIUMINT(" + M + "), ");
		option = name;
		return this;  
	}
	//	AUTO_INCREMENT,
	//	UNSIGNED, ZEROFILL,
	//	SERIAL DEFAULT VALUE
	//	[0 if NOT NULL]

	/*******************************************************************************************************
	 * @brief INT의 기능을 활성화 한다.
	 * @method INT
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable INT( String name, int M ){
		query.append(setNameValue(name) +" INT(" + M + "), ");
		option = name;
		return this;  
	}
	//	AUTO_INCREMENT,
	//	UNSIGNED, ZEROFILL,
	//	SERIAL DEFAULT VALUE
	//	[0 if NOT NULL]

	/*******************************************************************************************************
	 * @brief BIGINT의 기능을 활성화 한다.
	 * @method BIGINT
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable BIGINT( String name, int M ){
		query.append(setNameValue(name) +" BIGINT(" + M + "), ");
		option = name;
		return this;  
	}
	//	AUTO_INCREMENT,
	//	UNSIGNED, ZEROFILL,
	//	SERIAL DEFAULT VALUE
	//	[0 if NOT NULL]

	/*******************************************************************************************************
	 * @brief FLOAT의 기능을 활성화 한다.
	 * @method FLOAT
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable FLOAT( String name, float M ){
		query.append(setNameValue(name) +"  FLOAT(" + M + "), ");
		option = name;
		return this;  
	}	
	//	UNSIGNED, ZEROFILL
	//	[0 if NOT NULL]
	//
	/*******************************************************************************************************
	 * @brief FLOAT의 기능을 활성호 한다.
	 * @method FLOAT
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable FLOAT( String name, int M, int D ){
		query.append(setNameValue(name) +" FLOAT(" + M +", " + D + "), ");
		option = name;
		return this;  
	}
	//	UNSIGNED, ZEROFILL
	//	[0 if NOT NULL]
	//
	/*******************************************************************************************************
	 * @brief DOUBLE의 기능을 활성화 한다.
	 * @method DOUBLE
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable DOUBLE( String name, int M, int D ){
		query.append(setNameValue(name) +" DOUBLE(" + M +", " + D + "), ");
		option = name;
		return this;  
	}
	//	UNSIGNED, ZEROFILL
	//	[0 if NOT NULL]
	//
	/*******************************************************************************************************
	 * @brief DECIMAL의 기능을 활성화 한다.
	 * @method DECIMAL
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable DECIMAL( String name, int M, int D){
		query.append(setNameValue(name) +" DECIMAL(" + M +", " + D + "), ");
		option = name;
		return this;  
	}
	//	UNSIGNED, ZEROFILL
	//	[0 if NOT NULL]
	//	
	/*******************************************************************************************************
	 * @brief BIT의 기능을 활성화 한다.
	 * @method BIT
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable BIT( String name, int M ){
		query.append(setNameValue(name) +" BIT(" + M + "), ");
		option = name;
		return this;  
	}
	//	Prior to 5.03
	//	TINYINT(1) Synonym
	//	[0 if NOT NULL]
	//
	/*******************************************************************************************************
	 * @brief CHAR의 기능을 활성화 한다.
	 * @method CHAR
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable CHAR( String name, int M ){
		query.append(setNameValue(name) +" CHAR(" + M+ "), ");
		option = name;
		return this;  
	}
	//	BINARY, CHARACTER SET	
	//	["" if NOT NULL]
	//
	/*******************************************************************************************************
	 * @brief VARCHAR의 기능을 활성화 한다.
	 * @method VARCHAR
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable VARCHAR( String name, int M ){
		query.append(setNameValue(name) +" VARCHAR(" + M + "), ");
		option = name;
		return this;  
	}	
	//	BINARY, CHARACTER SET	
	//	["" if NOT NULL]
	//
	/*******************************************************************************************************
	 * @brief TINYTEXT의 기능을 활성화 한다.
	 * @method TINYTEXT
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable TINYTEXT( String name ){
		query.append(setNameValue(name) +" TINYTEXT, ");
		option = name;
		return this;  
	}	
	//	BINARY, CHARACTER SET	
	//	["" if NOT NULL]
	//	
	/*******************************************************************************************************
	 * @brief TEXT의 기능을 활성화 한다.
	 * @method TEXT
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable TEXT( String name ){
		query.append(setNameValue(name) +" TEXT, ");
		option = name;
		return this;  
	}	
	//	BINARY, CHARACTER SET	
	//	["" if NOT NULL]
	//	
	/*******************************************************************************************************
	 * @brief MEDIUMTEXT의 기능을 활성화 한다.
	 * @method MEDIUMTEXT
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable MEDIUMTEXT( String name ){
		query.append(setNameValue(name) +" MEDIUMTEXT, ");
		option = name;
		return this;  
	}	
	//	BINARY, CHARACTER SET	
	//	["" if NOT NULL]
	//	
	/*******************************************************************************************************
	 * @brief LONGTEXT의 기능을 활성화 한다.
	 * @method LONGTEXT
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable LONGTEXT( String name ){
		query.append(setNameValue(name) +" LONGTEXT, ");
		option = name;
		return this;  
	}	
	//	BINARY, CHARACTER SET	
	//	["" if NOT NULL]
	//	
	/*******************************************************************************************************
	 * @brief BINARY의 기능을 활성화 한다.
	 * @method BINARY
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable BINARY(  String name, int M ){
		query.append(setNameValue(name) +" BINARY(" + M + "), ");
		option = name;
		return this;  
	}	
	//	Global Only
	//	["" if NOT NULL]
	//	
	/*******************************************************************************************************
	 * @brief VARBINARY의 기능을 활성화 한다.
	 * @method VARBINARY
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable VARBINARY(  String name, int M ){
		query.append(setNameValue(name) +" VARBINARY(" + M + "), ");
		option = name;
		return this;  
	}	
	//	Global Only
	//	["" if NOT NULL]
	//	
	/*******************************************************************************************************
	 * @brief TINYBLOB의 기능을 활성화 한다.
	 * @method TINYBLOB
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable TINYBLOB( String name ){
		query.append(setNameValue(name) +" TINYBLOB, ");
		option = name;
		return this;  
	}
	//	Global Only
	//	["" if NOT NULL]
	//	
	/*******************************************************************************************************
	 * @brief BLOB의 기능을 활성화 한다.
	 * @method BLOB
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable BLOB( String name ){
		query.append(setNameValue(name) +" BLOB, ");
		option = name;
		return this;  
	}
	//	Global Only
	//	["" if NOT NULL]
	//	
	/*******************************************************************************************************
	 * @brief MEDIUMBLOB의 기능을 활성화 한다.
	 * @method MEDIUMBLOB
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable MEDIUMBLOB( String name ){
		query.append(setNameValue(name) +" MEDIUMBLOB, ");
		option = name;
		return this;  
	}	
	//	Global Only
	//	["" if NOT NULL]
	//	
	/*******************************************************************************************************
	 * @brief LONGBLOB의 기능을 활성화 한다.
	 * @method LONGBLOB
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable LONGBLOB( String name ){
		query.append(setNameValue(name) +" LONGBLOB, ");
		option = name;
		return this;  
	}
	//	Global Only
	//	["" if NOT NULL]
	//	
	/*******************************************************************************************************
	 * @brief ENUM의 기능을 활성화 한다.
	 * @method ENUM
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable ENUM( String name ){
		query.append(setNameValue(name) +" ENUM, ");
		option = name;
		return this;  
	}
	//	CHARACTER SET	 
	//  [1st value if NOT NULL]
	/*******************************************************************************************************
	 * @brief SET의 기능을 활성화 한다.
	 * @method SET
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable SET( String name ){
		query.append(setNameValue(name) +" SET, ");
		option = name;
		return this;  
	}
	//	CHARACTER SET	
	//	["" if NOT NULL]
	//	
	/*******************************************************************************************************
	 * @brief DATE의 기능을 활성화 한다.
	 * @method DATE
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable DATE( String name ){
		query.append(setNameValue(name) +" DATE, ");
		option = name;
		return this;  
	}
	//	Global Only
	//	["0000-00-00" if NOT NULL]
	//	
	/*******************************************************************************************************
	 * @brief DATETIME의 기능을 활성화 한다.
	 * @method DATETIME
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable DATETIME( String name ){
		query.append(setNameValue(name) +" DATETIME, ");
		option = name;
		return this;  
	}	
	//	Global Only
	//	["0000-00-00 00:00:00" if NOT NULL]
	//	
	/*******************************************************************************************************
	 * @brief TIME의 기능을 활성화 한다.
	 * @method TIME
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable TIME( String name ){
		query.append(setNameValue(name) +" TIME, ");
		option = name;
		return this;  
	}
	//	Global Only
	//	["00:00:00" if NOT NULL]
	//	
	/*******************************************************************************************************
	 * @brief TIMESTAMP의 기능을 활성화 한다.
	 * @method TIMESTAMP
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable TIMESTAMP( ){
		query.append(" TIMESTAMP, ");
		option = "";
		return this;  
	}	
	//	Global Only
	//	Current Date & Time
	//
	/*******************************************************************************************************
	 * @brief YEAR의 기능을 활성화 한다.
	 * @method YEAR
	 * @file CoconutzCreateTable.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzCreateTable YEAR( ){
		query.append(" YEAR, ");
		option = "";
		return this;  
	}
	//	Global Only
	//	["0000" if NOT NULL]\
}

