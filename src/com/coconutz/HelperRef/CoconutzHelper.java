package com.coconutz.HelperRef;

import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;


import org.json.JSONException;

import coconutz.ForParser.CoconutzAjax;
import coconutz.ForParser.CoconutzHtmlParser;
import coconutz.ForParser.CoconutzMakeAjax;
import coconutz.ForParser.CoconutzParser;

import com.coconutz.Coconut;
import com.coconutz.CoconutParameter;
import com.coconutz.CoconutzCLI;
import com.coconutz.CoconutzDataBase;
import com.coconutz.CoconutzFormBase;
import com.coconutz.CoconutzQuery;
import com.coconutz.G;
/*********************************************************************************************
 * @brief CoconutzHelper, 모든 helper들의 모음으로 한번에 모든 함수를 사용할 수 있다.
 * @file  CoconutzHelper.java
 * @author  CHOIUNGYEOP
 * @date  2013. 5.
 *********************************************************************************************/
public class CoconutzHelper extends CoconutzAjax{
	public CoconutzQuery QUERY = new CoconutzQuery();
	public CoconutzCreateTable table = new CoconutzCreateTable();
	protected CoconutzCLI CLI_HELPER;
	public CoconutzCLI CLI_DB;
	private String ClassName;
	private CoconutzCaptcha CAPTCHA = new CoconutzCaptcha();
	protected CoconutzMakeAjax AJAX = new CoconutzMakeAjax();

	private CoconutzDate DATE = new CoconutzDate();
	private CoconutzTable TABLE =  new CoconutzTable();
	private CoconutzPagination PAGE = new CoconutzPagination();
	private CoconutzDataBase DATABASE = new CoconutzDataBase();
	private CoconutzFormBase FORMBASE = new CoconutzFormBase();
	private CoconutzTypography TYPOGRAPHY = new CoconutzTypography();
	private CoconutzXml XML = new CoconutzXml();
	private CoconutzNumber NUMBER = new CoconutzNumber();
	private CoconutzDirectory DIRECTORY = new CoconutzDirectory();
	private StringBuffer script = new StringBuffer();
	/*******************************************************************************************************
	 * @brief CoconutzEmail의 생성자, cli를 생성시키고 각 사용 함수에 대한 파싱 후 그 값에 맞는 코드를 반환시켜 준다.
	 * @method getInstance
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzHelper() {
		CLI_HELPER = new CoconutzCLI("../../../index.php", "", "/coconutzAPI_helper/testhelper");
		CLI_DB = new CoconutzCLI("../ ../../index.php", "coconutz_", "/coconutAPI_DB/DB");		
		ClassName = this.getClass().getSimpleName();
		Hashtable<String, String> table = new Hashtable<>();
		TableSetting(table);
		CoconutzParser parser = new CoconutzParser();
		if(parser.parseString(new File("").getAbsolutePath()+"/src/"+ClassName+".java")){
			Hashtable<String, String> result = parser.getResult();
			Enumeration<String> functionkey = result.keys();
			while(functionkey.hasMoreElements()){
				String funcName = (String) functionkey.nextElement();
				String funcSuper = (String) result.get(funcName);
				Enumeration<String> enumerationKey = table.keys();
				if(funcSuper.equals("UPLOAD_TABLE")){
					FORMBASE.form_open("frmData","", "");
					FORMBASE.form_input("name", "id");
					FORMBASE.form_input("name", "name");
					FORMBASE.form_input("name", "value");
					FORMBASE.form_close().form_button("id", "submit", "click");
					super.getJsonHtml(FORMBASE.getForm(),funcName, ClassName ,"coconutz_db","insert");
					super.getJsonPolling(FORMBASE.getForm(),funcName, ClassName,"coconutz_db","insert");
				}else if(funcSuper.equals("UPLOAD_MULTIPART")){
					super.getMultipartForm("", funcName, ClassName, "coconutz_db");
				}else if(funcSuper.equals("DELETE_TABLE")){
					FORMBASE.form_open("frmData","", "");
					FORMBASE.form_input("name", "id");
					FORMBASE.form_input("name", "name");
					FORMBASE.form_input("name", "value");
					FORMBASE.form_close().form_button("id", "delete", "delete");
					super.getJsonHtml(FORMBASE.getForm(),funcName, ClassName ,"coconutz_db","delete");
					super.getJsonPolling(FORMBASE.getForm(),funcName, ClassName,"coconutz_db","delete");
				}else if(funcSuper.equals("VIEW_TABLE")){
				}else{
					while(enumerationKey.hasMoreElements()){
						String string = (String) enumerationKey.nextElement();
						if(string.equals(funcSuper)){
							getAjax(funcName, table.get(string));
						}
					}
				}
			}
		}
	}
	/*******************************************************************************************************
	 * @brief CoconutzHelper의 생성자로 cli를 생성시키고 각 사용 함수에 대한 파싱 후 그 값에 맞는 코드를 반환시켜 준다. 테이블을 생성한다면 그 테이블에 맞는 코드또한 생성해 준다.
	 * @method getInstance
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public CoconutzHelper( CoconutzCreateTable ...newTable ) {
		CLI_HELPER = new CoconutzCLI("../../../index.php", "", "/coconutzAPI_helper/testhelper");
		CLI_DB = new CoconutzCLI("../../../index.php", "coconutz_", "/coconutAPI_DB/DB");
		ClassName = this.getClass().getSimpleName();
		Hashtable<String, String> table = new Hashtable<>();
		TableSetting(table);
		CoconutzParser parser = new CoconutzParser();
		if(parser.parseString(new File("").getAbsolutePath()+"/src/"+ClassName+".java")){
			Hashtable<String, String> result = parser.getResult();
			Enumeration<String> functionkey = result.keys();

			while(functionkey.hasMoreElements()){
				String funcName = (String) functionkey.nextElement();
				String funcSuper = (String) result.get(funcName);
				Enumeration<String> enumerationKey = table.keys();
				for( CoconutzCreateTable argus : newTable ){
					TableResetting( table , argus.getTable() , funcSuper);
					if(funcSuper.equals("UPLOAD_TABLE")){
						FORMBASE.form_open("frmData","", "");
						for( String argu : argus.namesArray() )
							FORMBASE.form_input("name", argu);
						FORMBASE.form_close().form_button("id", "submit", "click");
						super.getJsonHtml(FORMBASE.getForm(),funcName, ClassName ,argus.getTable(),"insert");
						super.getJsonPolling(FORMBASE.getForm(),funcName, ClassName,argus.getTable(),"insert");
					}else if(funcSuper.equals("UPLOAD_MULTIPART")){
						super.getMultipartForm("", funcName, ClassName, argus.getTable());
					}else if(funcSuper.equals("DELETE_TABLE")){
						FORMBASE.form_open("frmData","", "");
						for( String argu : argus.namesArray() )
							FORMBASE.form_input("name", argu);
						FORMBASE.form_close().form_button("id", "delete", "delete");
						super.getJsonHtml(FORMBASE.getForm(),funcName, ClassName ,argus.getTable(),"delete");
						super.getJsonPolling(FORMBASE.getForm(),funcName, ClassName,argus.getTable(),"delete");
					}else if(funcSuper.equals("VIEW_TABLE")){
						FORMBASE.form_button("id", "delete", "delete");
					}else{
						while(enumerationKey.hasMoreElements()){
							String string = (String) enumerationKey.nextElement();
							if(string.equals(funcSuper)){
								getAjax(funcName, table.get(string));
							}
						}
					}					
				}
			}
		}
	}
	/*******************************************************************************************************
	 * @brief HTML을 파씽하여 css와 js를 추가해 주는 함수(미완)
	 * @method HTML
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	protected void HTML(String name){
		CoconutzHtmlParser parse = new CoconutzHtmlParser();
		parse.ReadLine(name, script);
	}
	/*******************************************************************************************************
	 * @brief 각 테이블에 대한 파라미터를 설정하고 테이블에 넣어 사용할 수 있게 만든다.
	 * @method TableSetting
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	private void TableSetting(Hashtable<String, String> table){
		if(!hideCode){
			table.put( "QUERY","[]" );
			table.put( "GET_PAGINATION", "{\"base_url\":\"http://example.com/index.php/test/page/\",\"total_rows\":\"200\",\"per_page\":\"20\"}" );
			table.put( "GET_CUSTOMIZE_PAGINATION", "{\"base_url\":\"http://example.com/index.php/test/page/\",\"total_rows\":\"200\",\"per_page\":\"1\" ,\"url_segment\":\"3\" ,\"num_links\":\"2\" ,\"use_page_numbers\":\"TRUE\" ,\"page_query_string\":\"FALSE\"   ,\"first_link\":\"First\" ,\"first_tag_open\":\"<div>\" ,\"first_tag_close\":\"</div>\"  ,\"last_link\":\"Last\" ,\"last_tag_open\":\"<div>\" ,\"last_tag_close\":\"</div>\"  ,\"next_link\":\"<\" ,\"next_tag_open\":\"<div>\" ,\"next_tag_close\":\"</div>\"  ,\"prev_link\":\">\" ,\"prev_tag_open\":\"<div>\" ,\"prev_tag_close\":\"</div>\"}" );
			table.put( "GET_TABLE", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\"coconutz_db\",\"function\":\"from\"}]" );
			table.put( "SELECT", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\"coconutz_db\",\"function\":\"from\"}]" );
			table.put( "SELECT_MAX", "[{\"param1\":\"id\",\"function\":\"select_max\"},{\"param1\":\"coconutz_db\",\"function\":\"from\"}]" );
			table.put( "SELECT_MIN", "[{\"param1\":\"id\",\"function\":\"select_min\"},{\"param1\":\"coconutz_db\",\"function\":\"from\"}]" );
			table.put( "JOIN", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\"coconutz_db\",\"function\":\"from\"},{\"param1\":\"coconutz_db\",\"function\":\"join\"}]" );
			table.put( "WHERE", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\"coconutz_db\",\"function\":\"from\"},{\"param1\":\"id\",\"param2\":\"choi\",\"function\":\"where\"}]" );
			table.put( "LIKE", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\"coconutz_db\",\"function\":\"from\"},{\"param1\":\"id\",\"param2\":\"1\",\"param3\":\"both\",\"function\":\"like\"}]" );
			table.put( "ORDER_BY", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\"coconutz_db\",\"function\":\"from\"},{\"param1\":\"id\",\"param2\":\"1\",\"function\":\"order_by\"}]" );

			table.put( "AUTO_TYPOGRAPHY", "{ \"typography\" : \"hello\" }" );
			table.put( "NL2BREXCEPTPRE", "{ \"typography\" : \"hello\" }" );
			table.put( "DIRECTORY_MAP", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\"table1\",\"function\":\"from\"}]" );
			table.put( "GET_NUMBER", "{ \"num\" : \"1024\" }" );
			table.put( "GET_XML",  "{ \"xml\" : \"<100&200>\" }"  );

		}else{
			table.put( "QUERY","[]" );
			table.put( "GET_PAGINATION", "{\"base_url\":\"http://example.com/index.php/test/page/\",\"total_rows\":\"200\",\"per_page\":\"20\"}" );
			table.put( "GET_CUSTOMIZE_PAGINATION", "{\"base_url\":\"http://example.com/index.php/test/page/\",\"total_rows\":\"200\",\"per_page\":\"1\" ,\"url_segment\":\"3\" ,\"num_links\":\"2\" ,\"use_page_numbers\":\"TRUE\" ,\"page_query_string\":\"FALSE\"   ,\"first_link\":\"First\" ,\"first_tag_open\":\"<div>\" ,\"first_tag_close\":\"</div>\"  ,\"last_link\":\"Last\" ,\"last_tag_open\":\"<div>\" ,\"last_tag_close\":\"</div>\"  ,\"next_link\":\"<\" ,\"next_tag_open\":\"<div>\" ,\"next_tag_close\":\"</div>\"  ,\"prev_link\":\">\" ,\"prev_tag_open\":\"<div>\" ,\"prev_tag_close\":\"</div>\"}" );
			table.put( "GET_TABLE", "[{\"param1\":\"*\",\"function\":\""+Cselect+"\"},{\"param1\":\"coconutz_db\",\"function\":\""+Cfrom+"\"}]" );
			table.put( "SELECT", "[{\"param1\":\"*\",\"function\":\""+Cselect+"\"},{\"param1\":\"coconutz_db\",\"function\":\""+Cfrom+"\"}]" );
			table.put( "SELECT_MAX", "[{\"param1\":\"id\",\"function\":\""+Cselect_max+"\"},{\"param1\":\"coconutz_db\",\"function\":\""+Cfrom+"\"}]" );
			table.put( "SELECT_MIN", "[{\"param1\":\"id\",\"function\":\""+Cselect_min+"\"},{\"param1\":\"coconutz_db\",\"function\":\""+Cfrom+"\"}]" );
			table.put( "JOIN", "[{\"param1\":\"*\",\"function\":\""+Cselect+"\"},{\"param1\":\"coconutz_db\",\"function\":\""+Cfrom+"\"},{\"param1\":\"coconutz_db\",\"function\":\"join\"}]" );
			table.put( "WHERE", "[{\"param1\":\"*\",\"function\":\""+Cselect+"\"},{\"param1\":\"coconutz_db\",\"function\":\""+Cfrom+"\"},{\"param1\":\"id\",\"param2\":\"choi\",\"function\":\""+Cwhere+"\"}]" );
			table.put( "LIKE", "[{\"param1\":\"*\",\"function\":\""+Cselect+"\"},{\"param1\":\"coconutz_db\",\"function\":\""+Cfrom+"\"},{\"param1\":\"id\",\"param2\":\"1\",\"param3\":\"both\",\"function\":\"like\"}]" );
			table.put( "ORDER_BY", "[{\"param1\":\"*\",\"function\":\""+Cselect+"\"},{\"param1\":\"coconutz_db\",\"function\":\""+Cfrom+"\"},{\"param1\":\"id\",\"param2\":\"1\",\"function\":\"order_by\"}]" );			

			table.put( "AUTO_TYPOGRAPHY", "{ \"typography\" : \"hello\" }" );
			table.put( "NL2BREXCEPTPRE", "{ \"typography\" : \"hello\" }" );
			table.put( "DIRECTORY_MAP", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\"table1\",\"function\":\"from\"}]" );
			table.put( "GET_NUMBER", "{ \"num\" : \"1024\" }" );
			table.put( "GET_XML", "{ \"xml\" : \"<100&200>\" }" );
		}
	}
	/*******************************************************************************************************
	 * @brief 각 테이블에 대한 파라미터를 설정하고 테이블에 넣어 사용할 수 있게 만들고 테이블의 이름으로 그 값을 채운다.
	 * @method TableSetting
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	private void TableSetting(Hashtable<String, String> table, String table_name){
		if(!hideCode){
			table.put( "QUERY","[]" );
			table.put( "GET_PAGINATION", "{\"base_url\":\"http://example.com/index.php/test/page/\",\"total_rows\":\"200\",\"per_page\":\"20\"}" );
			table.put( "GET_CUSTOMIZE_PAGINATION", "{\"base_url\":\"http://example.com/index.php/test/page/\",\"total_rows\":\"200\",\"per_page\":\"1\" ,\"url_segment\":\"3\" ,\"num_links\":\"2\" ,\"use_page_numbers\":\"TRUE\" ,\"page_query_string\":\"FALSE\"   ,\"first_link\":\"First\" ,\"first_tag_open\":\"<div>\" ,\"first_tag_close\":\"</div>\"  ,\"last_link\":\"Last\" ,\"last_tag_open\":\"<div>\" ,\"last_tag_close\":\"</div>\"  ,\"next_link\":\"<\" ,\"next_tag_open\":\"<div>\" ,\"next_tag_close\":\"</div>\"  ,\"prev_link\":\">\" ,\"prev_tag_open\":\"<div>\" ,\"prev_tag_close\":\"</div>\"}" );
			table.put( "GET_TABLE", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\""+table_name+"\",\"function\":\"from\"}]" );
			table.put( "SELECT", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\""+table_name+"\",\"function\":\"from\"}]" );
			table.put( "SELECT_MAX", "[{\"param1\":\"id\",\"function\":\"select_max\"},{\"param1\":\""+table_name+"\",\"function\":\"from\"}]" );
			table.put( "SELECT_MIN", "[{\"param1\":\"id\",\"function\":\"select\"},{\"param1\":\""+table_name+"\",\"function\":\"from\"}]" );
			table.put( "JOIN", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\""+table_name+"\",\"function\":\"from\"},{\"param1\":\"coconutz_db\",\"function\":\"join\"}]" );
			table.put( "WHERE", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\""+table_name+"\",\"function\":\"from\"},{\"param1\":\"id\",\"param2\":\"choi\",\"function\":\"where\"}]" );
			table.put( "LIKE", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\""+table_name+"\",\"function\":\"from\"},{\"param1\":\"id\",\"param2\":\"1\",\"param3\":\"both\",\"function\":\"like\"}]" );
			table.put( "ORDER_BY", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\""+table_name+"\",\"function\":\"from\"},{\"param1\":\"id\",\"param2\":\"1\",\"function\":\"order_by\"}]" );			

			table.put( "AUTO_TYPOGRAPHY", "{ \"typography\" : \"hello\" }" );
			table.put( "NL2BREXCEPTPRE", "{ \"typography\" : \"hello\" }" );
			table.put( "DIRECTORY_MAP", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\"table1\",\"function\":\"from\"}]" );
			table.put( "GET_NUMBER", "{ \"num\" : \"1024\" }" );
			table.put( "GET_XML", "{ \"xml\" : \"<100&200>\" }" );

		}else{
			table.put( "QUERY","[]" );
			table.put( "GET_PAGINATION", "{\"base_url\":\"http://example.com/index.php/test/page/\",\"total_rows\":\"200\",\"per_page\":\"20\"}" );
			table.put( "GET_CUSTOMIZE_PAGINATION", "{\"base_url\":\"http://example.com/index.php/test/page/\",\"total_rows\":\"200\",\"per_page\":\"1\" ,\"url_segment\":\"3\" ,\"num_links\":\"2\" ,\"use_page_numbers\":\"TRUE\" ,\"page_query_string\":\"FALSE\"   ,\"first_link\":\"First\" ,\"first_tag_open\":\"<div>\" ,\"first_tag_close\":\"</div>\"  ,\"last_link\":\"Last\" ,\"last_tag_open\":\"<div>\" ,\"last_tag_close\":\"</div>\"  ,\"next_link\":\"<\" ,\"next_tag_open\":\"<div>\" ,\"next_tag_close\":\"</div>\"  ,\"prev_link\":\">\" ,\"prev_tag_open\":\"<div>\" ,\"prev_tag_close\":\"</div>\"}" );
			table.put( "GET_TABLE", "[{\"param1\":\"*\",\"function\":\""+Cselect+"\"},{\"param1\":\""+table_name+"\",\"function\":\""+Cfrom+"\"}]" );
			table.put( "SELECT", "[{\"param1\":\"*\",\"function\":\""+Cselect+"\"},{\"param1\":\""+table_name+"\",\"function\":\""+Cfrom+"\"}]" );
			table.put( "SELECT_MAX", "[{\"param1\":\"id\",\"function\":\""+Cselect_max+"\"},{\"param1\":\""+table_name+"\",\"function\":\""+Cfrom+"\"}]" );
			table.put( "SELECT_MIN", "[{\"param1\":\"id\",\"function\":\""+Cselect+"\"},{\"param1\":\""+table_name+"\",\"function\":\""+Cfrom+"\"}]" );
			table.put( "JOIN", "[{\"param1\":\"*\",\"function\":\""+Cselect+"\"},{\"param1\":\""+table_name+"\",\"function\":\""+Cfrom+"\"},{\"param1\":\"coconutz_db\",\"function\":\"join\"}]" );
			table.put( "WHERE", "[{\"param1\":\"*\",\"function\":\""+Cselect+"\"},{\"param1\":\""+table_name+"\",\"function\":\""+Cfrom+"\"},{\"param1\":\"id\",\"param2\":\"choi\",\"function\":\""+Cwhere+"\"}]" );
			table.put( "LIKE", "[{\"param1\":\"*\",\"function\":\""+Cselect+"\"},{\"param1\":\""+table_name+"\",\"function\":\""+Cfrom+"\"},{\"param1\":\"id\",\"param2\":\"1\",\"param3\":\"both\",\"function\":\"like\"}]" );
			table.put( "ORDER_BY", "[{\"param1\":\"*\",\"function\":\""+Cselect+"\"},{\"param1\":\""+table_name+"\",\"function\":\""+Cfrom+"\"},{\"param1\":\"id\",\"param2\":\"1\",\"function\":\"order_by\"}]" );						

			table.put( "AUTO_TYPOGRAPHY", "{ \"typography\" : \"hello\" }" );
			table.put( "NL2BREXCEPTPRE", "{ \"typography\" : \"hello\" }" );
			table.put( "DIRECTORY_MAP", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\"table1\",\"function\":\"from\"}]" );
			table.put( "GET_NUMBER", "{ \"num\" : \"1024\" }" );
			table.put( "GET_XML", "{ \"xml\" : \"<100&200>\" }" );		
		}
	}	
	/*******************************************************************************************************
	 * @brief 각 테이블에 대한 파라미터를 설정하고 테이블에 넣어 사용할 수 있게 만들고 테이블의 이름으로 다시 그 값을 채운다.
	 * @method TableResetting
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	private void TableResetting(Hashtable<String, String> table, String table_name, String key){
		String value = "";
		if(!hideCode){
			switch( key ){
			case "QUERY":
				value = "[]";
				break;
			case "GET_PAGINATION":
				value = "{\"base_url\":\"http://example.com/index.php/test/page/\",\"total_rows\":\"200\",\"per_page\":\"20\"}";
				break;
			case "GET_CUSTOMIZE_PAGINATION":
				value = "{\"base_url\":\"http://example.com/index.php/test/page/\",\"total_rows\":\"200\",\"per_page\":\"1\" ,\"url_segment\":\"3\" ,\"num_links\":\"2\" ,\"use_page_numbers\":\"TRUE\" ,\"page_query_string\":\"FALSE\"   ,\"first_link\":\"First\" ,\"first_tag_open\":\"<div>\" ,\"first_tag_close\":\"</div>\"  ,\"last_link\":\"Last\" ,\"last_tag_open\":\"<div>\" ,\"last_tag_close\":\"</div>\"  ,\"next_link\":\"<\" ,\"next_tag_open\":\"<div>\" ,\"next_tag_close\":\"</div>\"  ,\"prev_link\":\">\" ,\"prev_tag_open\":\"<div>\" ,\"prev_tag_close\":\"</div>\"}";
				break;
			case "GET_TABLE":
				value = "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\""+table_name+"\",\"function\":\"from\"}]";
				break;
			case "SELECT":
				value = "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\""+table_name+"\",\"function\":\"from\"}]";
				break;
			case "SELECT_MAX":
				value = "[{\"param1\":\"id\",\"function\":\"select_max\"},{\"param1\":\""+table_name+"\",\"function\":\"from\"}]";
				break;
			case "SELECT_MIN":
				value = "[{\"param1\":\"id\",\"function\":\"select\"},{\"param1\":\""+table_name+"\",\"function\":\"from\"}]";
				break;
			case "JOIN":
				value = "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\""+table_name+"\",\"function\":\"from\"},{\"param1\":\"coconutz_db\",\"function\":\"join\"}]";
				break;
			case "WHERE":
				value = "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\""+table_name+"\",\"function\":\"from\"},{\"param1\":\"id\",\"param2\":\"choi\",\"function\":\"where\"}]";
				break;
			case "LIKE":
				value = "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\""+table_name+"\",\"function\":\"from\"},{\"param1\":\"id\",\"param2\":\"1\",\"param3\":\"both\",\"function\":\"like\"}]";
				break;
			case "ORDER_BY":
				value = "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\""+table_name+"\",\"function\":\"from\"},{\"param1\":\"id\",\"param2\":\"1\",\"function\":\"order_by\"}]";
				break;
			case "AUTO_TYPOGRAPHY":
				value = "{ \"typography\" : \"hello\" }";
				break;
			case "NL2BREXCEPTPRE":
				value = "{ \"typography\" : \"hello\" }";
				break;
			case "DIRECTORY_MAP":
				value = "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\"table1\",\"function\":\"from\"}]";
				break;
			case "GET_NUMBER":
				value = "{ \"num\" : \"1024\" }";
				break;
			case "GET_XML":
				value = "{ \"xml\" : \"<100&200>\" }";
				break;
			default:
				value = "[]";
				break;
			}
		}else{
			switch( key ){
			case "QUERY":
				value = "[]";
				break;
			case "GET_PAGINATION":
				value = "{\"base_url\":\"http://example.com/index.php/test/page/\",\"total_rows\":\"200\",\"per_page\":\"20\"}";
				break;
			case "GET_CUSTOMIZE_PAGINATION":
				value = "{\"base_url\":\"http://example.com/index.php/test/page/\",\"total_rows\":\"200\",\"per_page\":\"1\" ,\"url_segment\":\"3\" ,\"num_links\":\"2\" ,\"use_page_numbers\":\"TRUE\" ,\"page_query_string\":\"FALSE\"   ,\"first_link\":\"First\" ,\"first_tag_open\":\"<div>\" ,\"first_tag_close\":\"</div>\"  ,\"last_link\":\"Last\" ,\"last_tag_open\":\"<div>\" ,\"last_tag_close\":\"</div>\"  ,\"next_link\":\"<\" ,\"next_tag_open\":\"<div>\" ,\"next_tag_close\":\"</div>\"  ,\"prev_link\":\">\" ,\"prev_tag_open\":\"<div>\" ,\"prev_tag_close\":\"</div>\"}";
				break;
			case "GET_TABLE":
				value = "[{\"param1\":\"*\",\"function\":\""+Cselect+"\"},{\"param1\":\""+table_name+"\",\"function\":\""+Cfrom+"\"}]";
				break;
			case "SELECT":
				value = "[{\"param1\":\"*\",\"function\":\""+Cselect+"\"},{\"param1\":\""+table_name+"\",\"function\":\""+Cfrom+"\"}]";
				break;
			case "SELECT_MAX":
				value = "[{\"param1\":\"id\",\"function\":\""+Cselect_max+"\"},{\"param1\":\""+table_name+"\",\"function\":\""+Cfrom+"\"}]";
				break;
			case "SELECT_MIN":
				value = "[{\"param1\":\"id\",\"function\":\""+Cselect+"\"},{\"param1\":\""+table_name+"\",\"function\":\""+Cfrom+"\"}]";
				break;
			case "JOIN":
				value = "[{\"param1\":\"*\",\"function\":\""+Cselect+"\"},{\"param1\":\""+table_name+"\",\"function\":\""+Cfrom+"\"},{\"param1\":\"coconutz_db\",\"function\":\""+Cjoin+"\"}]";
				break;
			case "WHERE":
				value = "[{\"param1\":\"*\",\"function\":\""+Cselect+"\"},{\"param1\":\""+table_name+"\",\"function\":\""+Cfrom+"\"},{\"param1\":\"id\",\"param2\":\"choi\",\"function\":\""+Cwhere+"\"}]";
				break;
			case "LIKE":
				value = "[{\"param1\":\"*\",\"function\":\""+Cselect+"\"},{\"param1\":\""+table_name+"\",\"function\":\""+Cfrom+"\"},{\"param1\":\"id\",\"param2\":\"1\",\"param3\":\"both\",\"function\":\""+Clike+"\"}]";
				break;
			case "ORDER_BY":
				value = "[{\"param1\":\"*\",\"function\":\""+Cselect+"\"},{\"param1\":\""+table_name+"\",\"function\":\""+Cfrom+"\"},{\"param1\":\"id\",\"param2\":\"1\",\"function\":\""+Corder_by+"\"}]";
				break;
			case "AUTO_TYPOGRAPHY":
				value = "{ \"typography\" : \"hello\" }";
				break;
			case "NL2BREXCEPTPRE":
				value = "{ \"typography\" : \"hello\" }";
				break;
			case "DIRECTORY_MAP":
				value = "[{\"param1\":\"*\",\"function\":\""+Cselect+"\"},{\"param1\":\"table1\",\"function\":\""+Cfrom+"\"}]";
				break;
			case "GET_NUMBER":
				value = "{ \"num\" : \"1024\" }";
				break;
			case "GET_XML":
				value = "{ \"xml\" : \"<100&200>\" }";
				break;
			default:
				value = "[]";
				break;
			}
		}
		table.put( key, value );
	}
	/*******************************************************************************************************
	 * @brief 사용자가 직접 만든 함수를 이용하여 js, html파일을 만들 수 있게 하는 함수.
	 * @method Branch
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	protected void BRANCH(String name,String param){
		CoconutAjax("","html",ClassName+"_"+name, AJAX.SCRIPT(ClassName, name, param, ""));
		CoconutAjax("js_","js",ClassName+"_"+name, AJAX.SCRIPT(ClassName, name, param, ""));
		CoconutAjax("poll_","js",ClassName+"_"+name, AJAX.SCRIPT(ClassName, name, param, ""));
	}
	/*******************************************************************************************************
	 * @brief 사용자가 직접 만든 쿼리를 이용하여 js, html파일을 만들 수 있게 하는 함수.
	 * @method DB
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	protected void DB(String name, String param){
		CoconutAjax("","html",ClassName+"_"+name, AJAX.SCRIPT_INSERT_DATA(ClassName, name, param, ""));
		CoconutAjax("js_","js",ClassName+"_"+name, AJAX.SCRIPT_INSERT_DATA(ClassName, name, param, ""));
		CoconutAjax("poll_","js",ClassName+"_"+name, AJAX.SCRIPT_INSERT_DATA(ClassName, name, param, ""));
	}		
	/*******************************************************************************************************
	 * @brief 사용자가 직접 만든 함수를 이용하여 js, html파일을 만들 수 있게 하는 함수. id를 넣으면 그 id에 바로 사용할 수 있게 만들어 준다.
	 * @method Branch
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	protected void BRANCH( String name,String param, String id ){
		CoconutAjax("","html",ClassName+"_"+name, AJAX.SCRIPT(ClassName, name, param, id));
		CoconutAjax("js_","js",ClassName+"_"+name, AJAX.SCRIPT(ClassName, name, param, id));
		CoconutAjax("poll_","js",ClassName+"_"+name, AJAX.SCRIPT(ClassName, name, param, id));
	}
	/*******************************************************************************************************
	 * @brief 사용자가 직접 만든 쿼리를 이용하여 js, html파일을 만들 수 있게 하는 함수. id를 넣으면 그 id에 바로 사용할 수 있게 만들어 준다.
	 * @method DB
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	protected void DB( String name, String param, String id ){
		CoconutAjax("","html",ClassName+"_"+name, AJAX.SCRIPT_INSERT_DATA(ClassName, name, param, id));
		CoconutAjax("js_","js",ClassName+"_"+name, AJAX.SCRIPT_INSERT_DATA(ClassName, name, param, id));
		CoconutAjax("poll_","js",ClassName+"_"+name, AJAX.SCRIPT_INSERT_DATA(ClassName, name, param, id));
	}		
	/*******************************************************************************************************
	 * @brief 사용자가 만든 database를 실행하는 함수.
	 * @method DB
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	protected Coconut DB(CoconutParameter cp){
		try {
			return new Coconut(CLI_DB.console("query", new CoconutParameter(new CoconutzDataBase(cp.getJsonArray()).getJson()),""));
		} catch (JSONException e) {
			e.printStackTrace();
			return new Coconut(CLI_DB.console("query", cp ));		
		}
	}
	/*******************************************************************************************************
	 * @brief 사용자가 만든 함수로 js, html을 만드는 내부 함수.
	 * @method getAjax
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	private void getAjax( String file_name, String json ){
		getJs(file_name, ClassName ,json);
		getPoll(file_name, ClassName, json);
		getJQuery(file_name, ClassName ,json); 
	}
	/*******************************************************************************************************
	 * @brief DB helper의 SELECT 클래스를 사용할 수 있다.
	 * @method SELECT
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	protected Coconut SELECT(CoconutParameter cp){
		if (G.DEBUG) { G.debugPrint( cp.toString()); }
		try {
			return new Coconut(CLI_DB.console("query", new CoconutParameter(new CoconutzDataBase(cp.getJsonArray()).getJson()),""));
		} catch (JSONException e) {
			e.printStackTrace();
			return new Coconut(CLI_DB.console("query", cp ));		
		}
	}	
	/*******************************************************************************************************
	 * @brief DB helper의 SELECT_MAX 클래스를 사용할 수 있다.
	 * @method SELECT_MAX
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	protected Coconut SELECT_MAX(CoconutParameter cp){
		if (G.DEBUG) { G.debugPrint( cp.toString()); }
		try {
			return new Coconut(CLI_DB.console("query", new CoconutParameter(new CoconutzDataBase(cp.getJsonArray()).getJson()),""));
		} catch (JSONException e) {
			e.printStackTrace();
			return new Coconut(CLI_DB.console("query", cp ));		
		}
	}	
	/*******************************************************************************************************
	 * @brief DB helper의 SELECT_MIN 클래스를 사용할 수 있다.
	 * @method SELECT_MIN
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	protected Coconut SELECT_MIN(CoconutParameter cp){
		if (G.DEBUG) { G.debugPrint( cp.toString()); }
		try {
			return new Coconut(CLI_DB.console("query", new CoconutParameter(new CoconutzDataBase(cp.getJsonArray()).getJson()),""));
		} catch (JSONException e) {
			e.printStackTrace();
			return new Coconut(CLI_DB.console("query", cp ));		
		}
	}	
	/*******************************************************************************************************
	 * @brief DB helper의 JOIN 클래스를 사용할 수 있다.
	 * @method JOIN
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/	
	protected Coconut JOIN(CoconutParameter cp){
		if (G.DEBUG) { G.debugPrint( cp.toString()); }
		try {
			return new Coconut(CLI_DB.console("query", new CoconutParameter(new CoconutzDataBase(cp.getJsonArray()).getJson()),""));
		} catch (JSONException e) {
			e.printStackTrace();
			return new Coconut(CLI_DB.console("query", cp ));		
		}
	}	
	/*******************************************************************************************************
	 * @brief  DB helper의 WHERE 클래스를 사용할 수 있다.
	 * @method WHERE
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/	
	protected Coconut WHERE(CoconutParameter cp){
		if (G.DEBUG) { G.debugPrint( cp.toString()); }
		try {
			return new Coconut(CLI_DB.console("query", new CoconutParameter(new CoconutzDataBase(cp.getJsonArray()).getJson()),""));
		} catch (JSONException e) {
			e.printStackTrace();
			return new Coconut(CLI_DB.console("query", cp ));		
		}
	}	
	/*******************************************************************************************************
	 * @brief DB helper의 LIKE 클래스를 사용할 수 있다.
	 * @method LIKE
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/	
	protected Coconut LIKE(CoconutParameter cp){
		if (G.DEBUG) { G.debugPrint( cp.toString()); }
		try {
			return new Coconut(CLI_DB.console("query", new CoconutParameter(new CoconutzDataBase(cp.getJsonArray()).getJson()),""));
		} catch (JSONException e) {
			e.printStackTrace();
			return new Coconut(CLI_DB.console("query", cp ));		
		}
	}	
	/*******************************************************************************************************
	 * @brief DB helper의 ORDER_BY 클래스를 사용할 수 있다.
	 * @method ORDER_BY
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/	
	protected Coconut ORDER_BY(CoconutParameter cp){
		if (G.DEBUG) { G.debugPrint( cp.toString()); }
		try {
			return new Coconut(CLI_DB.console("query", new CoconutParameter(new CoconutzDataBase(cp.getJsonArray()).getJson()),""));
		} catch (JSONException e) {
			e.printStackTrace();
			return new Coconut(CLI_DB.console("query", cp ));		
		}
	}	
	/*******************************************************************************************************
	 * @brief DB helper의 QUERY 클래스를 사용할 수 있다.
	 * @method QUERY
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/	
	protected Coconut QUERY(CoconutParameter cp){
		if (G.DEBUG) { G.debugPrint( cp.toString()); }
		try {
			return new Coconut(CLI_DB.console("query", new CoconutParameter(new CoconutzDataBase(cp.getJsonArray()).getJson()),""));
		} catch (JSONException e) {
			e.printStackTrace();
			return new Coconut(CLI_DB.console("query", cp ));		
		}
	}	
	/*******************************************************************************************************
	 * @brief GETTEST 클래스를 사용할 수 있다.
	 * @method GETTEST
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/	
	public Coconut GETTEST(CoconutParameter parameter){
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/testhelper/getTest\t"+ parameter.getEncodingParam()); }		
		return new Coconut(CLI_HELPER.console("getTest", parameter));
	}	
	/*******************************************************************************************************
	 * @brief SETTEST 클래스를 사용할 수 있다.
	 * @method SETTEST
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public Coconut SETTEST(CoconutParameter parameter){ 
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/testhelper/setTest\t"+ parameter.getEncodingParam()); }		
		return new Coconut(CLI_HELPER.console("setTest", parameter));
	}

	/*******************************************************************************************************
	 * @brief Table helper의 UPLOAD_TABLE 클래스를 사용할 수 있다.
	 * @method UPLOAD_TABLE
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public Coconut UPLOAD_TABLE(CoconutParameter parameter){
		return TABLE.Insert(parameter);
	}	
	/*******************************************************************************************************
	 * @brief Table helper의 UPLOAD_MULTIPART 클래스를 사용할 수 있다.
	 * @method UPLOAD_MULTIPART
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public Coconut UPLOAD_MULTIPART(CoconutParameter parameter){
		return TABLE.InsertFile(parameter);
	}

	/*******************************************************************************************************
	 * @brief Table helper의 DELETE_TABLE 클래스를 사용할 수 있다.
	 * @method VIEW_TABLE
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public Coconut DELETE_TABLE(CoconutParameter parameter){
		return TABLE.Delete(parameter);		
	}	
	/*******************************************************************************************************
	 * @brief Table helper의 VIEW_TABLE 클래스를 사용할 수 있다.
	 * @method VIEW_TABLE
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public Coconut VIEW_TABLE(CoconutParameter parameter){
		return TABLE.View(parameter);		
	}	
	/*******************************************************************************************************
	 * @brief Table helper의 GET_TABLE 클래스를 사용할 수 있다.
	 * @method GET_TABLE
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public Coconut GET_TABLE(CoconutParameter parameter){
		return TABLE.getTableFromDB(parameter);
	}	
	/*******************************************************************************************************
	 * @brief Table helper의 TABLE_TEST 클래스를 사용할 수 있다.
	 * @method TABLE_TEST
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public Coconut TABLE_TEST(CoconutParameter parameter){
		return TABLE.getTest(parameter);		
	}

	/*******************************************************************************************************
	 * @brief PAGE helper의 GET_PAGINATION 클래스를 사용할 수 있다.
	 * @method GET_PAGINATION
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public Coconut GET_PAGINATION(CoconutParameter parameter){
		return PAGE.getPagination(parameter);
	}

	/*******************************************************************************************************
	 * @brief PAGE helper의 GET_PAGINATION 클래스를 사용할 수 있다.
	 * @method GET_PAGINATION
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public Coconut GET_CUSTOMIZE_PAGINATION(CoconutParameter parameter){
		return PAGE.getPaginationCustomize(parameter);
	}

	/*******************************************************************************************************
	 * @brief TYPOGRAPHY helper의 AUTO_TYPOGRAPHY 클래스를 사용할 수 있다.
	 * @method AUTO_TYPOGRAPHY
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public Coconut AUTO_TYPOGRAPHY(CoconutParameter parameter){
		return TYPOGRAPHY.autoTypography(parameter);
	}	
	/*******************************************************************************************************
	 * @brief TYPOGRAPHY helper의 NL2BREXCEPTPRE 클래스를 사용할 수 있다.
	 * @method NL2BREXCEPTPRE
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public Coconut NL2BREXCEPTPRE(CoconutParameter parameter){
		return TYPOGRAPHY.nl2brExceptPre(parameter);
	}

	/*******************************************************************************************************
	 * @brief DIRECTORY helper의 DIRECTORY_MAP 클래스를 사용할 수 있다.
	 * @method DIRECTORY_MAP
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public Coconut DIRECTORY_MAP(CoconutParameter parameter){
		return DIRECTORY.directoryMap(parameter);		
	}

	/*******************************************************************************************************
	 * @brief DIRECTORY helper의 GET_NUMBER 클래스를 사용할 수 있다.
	 * @method GET_NUMBER
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public Coconut GET_NUMBER(CoconutParameter parameter){
		return NUMBER.getNumber(parameter);
	}

	/*******************************************************************************************************
	 * @brief XML helper의 GET_XML 클래스를 사용할 수 있다.
	 * @method GET_XML
	 * @file CoconutzHelper.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public Coconut GET_XML(CoconutParameter parameter){
		return XML.getXml(parameter);
	}
}