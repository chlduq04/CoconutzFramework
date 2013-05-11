package com.coconutz.HelperRef;

import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.text.html.FormView;

import org.json.JSONException;

import coconutz.ForParser.CoconutzAjax;
import coconutz.ForParser.CoconutzMakeAjax;
import coconutz.ForParser.CoconutzParser;

import com.coconutz.Coconut;
import com.coconutz.CoconutParameter;
import com.coconutz.CoconutzCLI;
import com.coconutz.CoconutzDataBase;
import com.coconutz.CoconutzFormBase;
import com.coconutz.CoconutzQuery;
import com.coconutz.CoconutzRunnableClass;
import com.coconutz.G;
import com.coconutz.DriverRef.DB.CoconutzDB;

public class CoconutzTesthelper extends CoconutzAjax{
	public CoconutzQuery QUERY = new CoconutzQuery();
	public CoconutzCreateTable table = new CoconutzCreateTable();
	protected CoconutzCLI CLI ;
	private String ClassName;
	private CoconutzCaptcha CAPTCHA;
	protected CoconutzMakeAjax AJAX = new CoconutzMakeAjax();
	private CoconutzTable TABLE =  new CoconutzTable();
	private CoconutzPagination PAGE = new CoconutzPagination();
	private CoconutzDataBase DATABASE = new CoconutzDataBase();
	private CoconutzFormBase FORMBASE = new CoconutzFormBase();
	public static CoconutzCreateTable CREATETABLE = new CoconutzCreateTable();
	
	public CoconutzTesthelper() {
		CLI = new CoconutzCLI("../../../index.php", "", "/coconutzAPI_helper/testhelper");
		ClassName = this.getClass().getSimpleName();
		
		Hashtable<String, String> table = new Hashtable<>();
		table.put("","[]");
		table.put("getTable", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\"table1\",\"function\":\"from\"}]");
		table.put("getPagination", "{\"base_url\":\"http://example.com/index.php/test/page/\",\"total_rows\":\"200\",\"per_page\":\"20\"}");
		table.put( "Select", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\"table1\",\"function\":\"from\"}]" );
		table.put( "SelectMax", "[{\"param1\":\"*\",\"function\":\"select_max\"},{\"param1\":\"table1\",\"function\":\"from\"}]" );
		table.put( "SelectMin", "[{\"param1\":\"*\",\"function\":\"select_min\"},{\"param1\":\"table1\",\"function\":\"from\"}]" );
		table.put( "Join", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\"table1\",\"function\":\"from\"}]" );
		table.put( "Where", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\"table1\",\"function\":\"from\"},{\"param1\":\"id\",\"param2\":\"choi\",\"function\":\"where\"}]" );
		table.put( "Like", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\"table1\",\"function\":\"from\"}]" );
		table.put( "OrderBy", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\"table1\",\"function\":\"from\"}]" );

		Hashtable<String, String> divtable = new Hashtable<>();
		divtable.put("insertTable", "");
		divtable.put("deleteTable", " ");
		CoconutzParser parser = new CoconutzParser();
		if(parser.parseString(new File("").getAbsolutePath()+"/src/"+ClassName+".java")){
			Hashtable<String, String> result = parser.getResult();
			Enumeration<String> functionkey = result.keys();
			while(functionkey.hasMoreElements()){
				String funcName = (String) functionkey.nextElement();
				String funcSuper = (String) result.get(funcName);
				Enumeration<String> enumerationKey = table.keys();
				while(enumerationKey.hasMoreElements()){
					String string = (String) enumerationKey.nextElement();
					if(string.equals(funcSuper)){
						getAjax(funcName, table.get(string));
					}
				}
			}
		}
	}
	public CoconutzTesthelper( CoconutzCreateTable newTable ) {
		CLI = new CoconutzCLI("../../../index.php", "", "/coconutzAPI_helper/testhelper");
		ClassName = this.getClass().getSimpleName();
		Hashtable<String, String> table = new Hashtable<>();
		table.put("","[]");
		table.put("getTable", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\"table3\",\"function\":\"from\"}]");
		table.put("getPagination", "{\"base_url\":\"http://example.com/index.php/test/page/\",\"total_rows\":\"200\",\"per_page\":\"20\"}");
		table.put( "Select", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\"table1\",\"function\":\"from\"}]" );
		table.put( "SelectMax", "[{\"param1\":\"*\",\"function\":\"select_max\"},{\"param1\":\"table1\",\"function\":\"from\"}]" );
		table.put( "SelectMin", "[{\"param1\":\"*\",\"function\":\"select_min\"},{\"param1\":\"table1\",\"function\":\"from\"}]" );
		table.put( "Join", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\"table1\",\"function\":\"from\"}]" );
		table.put( "Where", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\"table1\",\"function\":\"from\"},{\"param1\":\"id\",\"param2\":\"choi\",\"function\":\"where\"}]" );
		table.put( "Like", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\"table1\",\"function\":\"from\"}]" );
		table.put( "OrderBy", "[{\"param1\":\"*\",\"function\":\"select\"},{\"param1\":\"table1\",\"function\":\"from\"}]" );
		CoconutzParser parser = new CoconutzParser();
		if(parser.parseString(new File("").getAbsolutePath()+"/src/"+ClassName+".java")){
			Hashtable<String, String> result = parser.getResult();
			Enumeration<String> functionkey = result.keys();
			
			while(functionkey.hasMoreElements()){
				String funcName = (String) functionkey.nextElement();
				String funcSuper = (String) result.get(funcName);
				Enumeration<String> enumerationKey = table.keys();
				if(funcSuper.equals("insertTable")){
					FORMBASE.form_open("frmData","", "");
					for( String argu : newTable.namesArray() ){
						FORMBASE.form_input("name", argu);
					}
					FORMBASE.form_close().form_button("id", "submit", "click");
					super.startInput(funcName, ClassName, FORMBASE.getForm(), newTable.getTable()); 
				}else if(funcSuper.equals("deleteTable")){
					FORMBASE.form_button("id", "delete", "delete");
					super.startInput(funcName, ClassName, FORMBASE.getForm(), newTable.getTable()); 
				}else if(funcSuper.equals("viewTable")){
					
				}
				else{
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
	
	protected void CoconutAjax(String name,String param){
		CoconutAjax(name, AJAX.SCRIPT(ClassName, name, param));
	}
	protected void CoconutAjax_db(String name, String param){
		CoconutAjax(name, AJAX.SCRIPT_INSERT_DATA(ClassName, name, param));
	}		
	
	protected Coconut CoconutAjax_result(CoconutParameter cp){
		try {
			return new Coconut("String",CLI.console("String", "query", new CoconutParameter(new CoconutzDataBase(cp.getJsonArray()).getJson()),""));
		} catch (JSONException e) {
			e.printStackTrace();
			return new Coconut("String",CLI.console("String", "query", cp ));		
		}
	}
	
	
	private boolean getAjax( String file_name, CoconutzQuery query2 ){
		return startParser(file_name, ClassName, query2.getQuery().toString());
	}
	private boolean getAjax( String file_name, String json ){
		return startParser(file_name, ClassName, json );
	}
	protected Coconut Select(CoconutParameter cp){
		if (G.DEBUG) { G.debugPrint( cp.toString()); }
		try {
			return new Coconut("String",CLI.console("String", "query", new CoconutParameter(new CoconutzDataBase(cp.getJsonArray()).getJson()),""));
		} catch (JSONException e) {
			e.printStackTrace();
			return new Coconut("String",CLI.console("String", "query", cp ));		
		}
	}	
	protected Coconut SelectMax(CoconutParameter cp){
		if (G.DEBUG) { G.debugPrint( cp.toString()); }
		try {
			return new Coconut("String",CLI.console("String", "query", new CoconutParameter(new CoconutzDataBase(cp.getJsonArray()).getJson()),""));
		} catch (JSONException e) {
			e.printStackTrace();
			return new Coconut("String",CLI.console("String", "query", cp ));		
		}
	}	
	protected Coconut SelectMin(CoconutParameter cp){
		if (G.DEBUG) { G.debugPrint( cp.toString()); }
		try {
			return new Coconut("String",CLI.console("String", "query", new CoconutParameter(new CoconutzDataBase(cp.getJsonArray()).getJson()),""));
		} catch (JSONException e) {
			e.printStackTrace();
			return new Coconut("String",CLI.console("String", "query", cp ));		
		}
	}	
	protected Coconut Join(CoconutParameter cp){
		if (G.DEBUG) { G.debugPrint( cp.toString()); }
		try {
			return new Coconut("String",CLI.console("String", "query", new CoconutParameter(new CoconutzDataBase(cp.getJsonArray()).getJson()),""));
		} catch (JSONException e) {
			e.printStackTrace();
			return new Coconut("String",CLI.console("String", "query", cp ));		
		}
	}	
	protected Coconut Where(CoconutParameter cp){
		if (G.DEBUG) { G.debugPrint( cp.toString()); }
		try {
			return new Coconut("String",CLI.console("String", "query", new CoconutParameter(new CoconutzDataBase(cp.getJsonArray()).getJson()),""));
		} catch (JSONException e) {
			e.printStackTrace();
			return new Coconut("String",CLI.console("String", "query", cp ));		
		}
	}	
	protected Coconut Like(CoconutParameter cp){
		if (G.DEBUG) { G.debugPrint( cp.toString()); }
		try {
			return new Coconut("String",CLI.console("String", "query", new CoconutParameter(new CoconutzDataBase(cp.getJsonArray()).getJson()),""));
		} catch (JSONException e) {
			e.printStackTrace();
			return new Coconut("String",CLI.console("String", "query", cp ));		
		}
	}	
	protected Coconut OrderBy(CoconutParameter cp){
		if (G.DEBUG) { G.debugPrint( cp.toString()); }
		try {
			return new Coconut("String",CLI.console("String", "query", new CoconutParameter(new CoconutzDataBase(cp.getJsonArray()).getJson()),""));
		} catch (JSONException e) {
			e.printStackTrace();
			return new Coconut("String",CLI.console("String", "query", cp ));		
		}
	}	
	protected Coconut query(CoconutParameter cp){
		if (G.DEBUG) { G.debugPrint( cp.toString()); }
		try {
			return new Coconut("String",CLI.console("String", "query", new CoconutParameter(new CoconutzDataBase(cp.getJsonArray()).getJson()),""));
		} catch (JSONException e) {
			e.printStackTrace();
			return new Coconut("String",CLI.console("String", "query", cp ));		
		}
	}	
	
	private boolean getAjaxContainTags(String tags, String file_name, String json ){
		return startParserdiv( tags, file_name, ClassName, json );
	}
	private boolean getAjaxContainTags(String tags, String file_name ){
		return startInput(file_name, ClassName, tags );
	}
	public Coconut insertTable(CoconutParameter parameter){
		return TABLE.Insert(parameter);
	}
	public Coconut deleteTable(CoconutParameter parameter){
		return TABLE.Delete(parameter);
	}
	public Coconut viewTable(CoconutParameter parameter){
		return TABLE.View(parameter);		
	}
	public Coconut getPagination(CoconutParameter parameter){
		return PAGE.getPagination(parameter);
	}
	public Coconut getTable(CoconutParameter parameter){
		return TABLE.getTableFromDB(parameter);
	}
	public Coconut TableTest(CoconutParameter parameter){
		return TABLE.getTest(parameter);		
	}
	
	public Coconut getTest(CoconutParameter parameter){
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/testhelper/getTest\t"+ parameter.getEncodingParam()); }		
		return new Coconut("String",CLI.console("String", "getTest", parameter));
	}
	public Coconut setTest(CoconutParameter parameter){ 
		if (G.DEBUG) { G.debugPrint("/coconutzAPI_helper/testhelper/setTest\t"+ parameter.getEncodingParam()); }		
		return new Coconut("String",CLI.console("String", "setTest", parameter));
	}
}
