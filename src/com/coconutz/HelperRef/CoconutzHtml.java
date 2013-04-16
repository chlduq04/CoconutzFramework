package com.coconutz.HelperRef;

import java.io.File;

import com.coconutz.CoconutzDataBase;

public class CoconutzHtml {
	private volatile static CoconutzHtml instance = null;
	private StringBuffer tag;
	public CoconutzHtml(){
		tag = new StringBuffer();
	}
	public CoconutzHtml( StringBuffer argu ){
		tag = argu;
	}
	public CoconutzHtml( String argu ){
		tag = new StringBuffer();
		tag.append(argu);
	}
	public void setTag( StringBuffer tag ){
		this.tag = tag;
	}
	public static CoconutzHtml getInstance( StringBuffer tag ) {
		if (instance == null) {
			synchronized(CoconutzHtml.class){
				if (instance == null) {
					instance = new CoconutzHtml(tag);
				}
			}
		}
		instance.setTag(tag);
		return instance;
	}

	public boolean ajax(File file){
		return true;
	}
	public boolean html(File file){
		return true;
	}
	public String tagId(String idname){
		return idname;
	}
	public String tagClass(String classname){
		return classname;
	}
	public CoconutzHtml jQuery(){
		return new CoconutzHtml();
	}
}
