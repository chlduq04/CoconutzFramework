package coconutz.test;

import java.io.File;
import java.util.Hashtable;

import com.coconutz.CoconutzDataBase;

public class CoconutzHtml {
	private StringBuffer tags;
	private Hashtable<String, String> id_table;
	public CoconutzHtml(){
		
	}
	public CoconutzHtml(CoconutzHtml html){
		tags = html.getTags();
	}
	public StringBuffer getTags(){
		return tags;
	}
	
	public CoconutzHtml DIV( ){
		String result = "<>";
		return this;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
 