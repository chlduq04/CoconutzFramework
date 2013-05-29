package com.coconutz;




import java.util.StringTokenizer;

import com.coconutz.Service.CoconutzSetting;

public class CoconutzFormBase extends CoconutzSetting{
	
	private StringBuffer form;
	
	public CoconutzFormBase(){
		form = new StringBuffer();		
	}

	public StringBuffer getFormBuffer(){
		return form;
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
	private void reset(){
		form = new StringBuffer();
	}
	private void appendBuffer(String argu){
		form.append(argu);
	}
	
	/*form open*/
	public CoconutzFormBase div_open( String argu ){
		return div(Stringtoken(argu));
	}
	public CoconutzFormBase div( String argu[] ){
		for( String value : argu){
			appendBuffer("<div>" + value + "</div>\r\n");
		}
		return this;
	}
	
	/*form open*/
	public CoconutzFormBase form_open( String argu, String value ){
		reset();
		return form_open( Stringtoken(argu), Stringtoken(value) );
	}
	public CoconutzFormBase form_open( String id, String argu, String value ){
		reset();
		return form_open( id, Stringtoken(argu), Stringtoken(value) );
	}
	public CoconutzFormBase form_open( String argu[], String value[] ){
		appendBuffer("<form method=\"post\" accept-charset=\"utf-8\" ");
		for( int i=0 ; i < argu.length ; i++ ){
			appendBuffer(argu[i] + "=\""+value[i]+"\" ");
		}
		appendBuffer(">\r\n");
		return this;
	}
	public CoconutzFormBase form_open(String id, String argu[], String value[] ){
		appendBuffer("<form id = \""+id+"\" method=\"post\" accept-charset=\"utf-8\" ");
		for( int i=0 ; i < argu.length ; i++ ){
			appendBuffer(argu[i] + "=\""+value[i]+"\" ");
		}
		appendBuffer(">\r\n");
		return this;
	}

	/*form open mulitipart*/

	public CoconutzFormBase form_open_multipart(String id, String argu, String value ){
		reset();
		return form_open_multipart( id, Stringtoken(argu), Stringtoken(value) );
	}
	public CoconutzFormBase form_open_multipart(String id, String argu[], String value[]){
		appendBuffer("<form id =  \""+id+"\" method=\"post\" accept-charset=\"utf-8\" action = \"" + super.url+"\"");
		for( int i=0 ; i < argu.length ; i++ ){
			appendBuffer(argu[i] + "=\""+value[i]+"\" ");
		}
	 	appendBuffer("enctype=\"multipart/form-data\""+">\r\n");
		appendBuffer("<iframe id=\""+id+"Frame\" name=\"uploadIFrame\" style=\"display:none;visibility:hidden\"></iframe>\r\n");
		return this;
	}

	/*form open password*/

	public CoconutzFormBase form_open_password( String argu, String value ){
		reset();
		return form_open_password(Stringtoken(argu), Stringtoken(value));
	}
	public CoconutzFormBase form_open_password( String argu[], String value[]){
		appendBuffer("<form method=\"post\" accept-charset=\"utf-8\" ");
		for( int i=0 ; i < argu.length ; i++ ){
			appendBuffer(argu[i] + "=\""+value[i]+"\" ");
		}
		appendBuffer("type=\"password\""+">\r\n");
		return this;
	}
	public CoconutzFormBase form_open_password(String id, String argu, String value ){
		reset();
		return form_open_password(id, Stringtoken(argu), Stringtoken(value));
	}
	public CoconutzFormBase form_open_password(String id, String argu[], String value[]){
		appendBuffer("<form id =  \""+id+"\" method=\"post\" accept-charset=\"utf-8\" ");
		for( int i=0 ; i < argu.length ; i++ ){
			appendBuffer(argu[i] + "=\""+value[i]+"\" ");
		}
		appendBuffer("type=\"password\""+">\r\n");
		return this;
	}

	/*form open upload*/
	public CoconutzFormBase form_open_upload( String argu, String value ){
		reset();
		return form_open_upload(Stringtoken(argu), Stringtoken(value));
	}
	public CoconutzFormBase form_open_upload( String argu[], String value[]){
		appendBuffer("<form method=\"post\" accept-charset=\"utf-8\" ");
		for( int i=0 ; i < argu.length ; i++ ){
			appendBuffer(argu[i] + "=\""+value[i]+"\" ");
		}
		appendBuffer("type=\"file\""+">\r\n");
		return this;
	}
	public CoconutzFormBase form_open_upload(String id, String argu, String value ){
		reset();
		return form_open_upload(id, Stringtoken(argu), Stringtoken(value));
	}
	public CoconutzFormBase form_open_upload(String id, String argu[], String value[]){
		appendBuffer("<form id = \""+id+"\" method=\"post\" accept-charset=\"utf-8\" ");
		for( int i=0 ; i < argu.length ; i++ ){
			appendBuffer(argu[i] + "=\""+value[i]+"\" ");
		}
		appendBuffer("type=\"file\""+">\r\n");
		return this;
	}

	/*form open textarea*/
	public CoconutzFormBase form_open_textarea( String argu, String value ){
		return form_open_textarea(Stringtoken(argu), Stringtoken(value));
	}
	public CoconutzFormBase form_open_textarea( String argu[], String value[]){
		appendBuffer("<form method=\"post\" accept-charset=\"utf-8\" action=\"\" ");
		for( int i=0 ; i < argu.length ; i++ ){
			appendBuffer(argu[i] + "=\""+value[i]+"\" ");
		}
		appendBuffer("type=\"textarea\""+">\r\n");
		return this;
	}
	public CoconutzFormBase form_open_textarea( String id, String argu, String value ){
		return form_open_textarea(id,Stringtoken(argu), Stringtoken(value));
	}
	public CoconutzFormBase form_open_textarea( String id, String argu[], String value[]){
		appendBuffer("<form id =  \""+id+"\" method=\"post\" accept-charset=\"utf-8\" action=\"\" ");
		for( int i=0 ; i < argu.length ; i++ ){
			appendBuffer(argu[i] + "=\""+value[i]+"\" ");
		}
		appendBuffer("type=\"textarea\""+">\r\n");
		return this;
	}

	/*form open dropdown*/
	public CoconutzFormBase form_open_dropdown( String name, String argu, String value, String set ){
		return form_open_dropdown(name, Stringtoken(argu), Stringtoken(value), Stringtoken(set));
	}
	public CoconutzFormBase form_open_dropdown( String name, String argu[], String value[] ,String set[]){
		appendBuffer("<select name = \" "+name+"\">\r\n");
		for( int i=0 ; i < argu.length ; i++ ){
			boolean check_set = false;
			for( String check : set){
				if(check.equals(argu[i])){
					check_set = true;
					break;
				}
			}
			if(check_set)
				appendBuffer("<option value = \""+argu[i]+"\" selected=\"selected\">"+value[i]+"</option>\r\n");								
			else
				appendBuffer("<option value = \""+argu[i]+"\">"+value[i]+"</option>\r\n");								

		}
		appendBuffer("</select>\r\n");
		return this;
	}

	/*form open dropdown*/
	public CoconutzFormBase form_open_dropdown( String name, String argu, String value){
		return form_open_dropdown(name, Stringtoken(argu), Stringtoken(value));
	}
	public CoconutzFormBase form_open_dropdown( String name, String argu[], String value[]){
		appendBuffer("<select name = \" "+name+"\" multiple=\"nultiple\">\r\n");
		for( int i=0 ; i < argu.length ; i++ ){
			appendBuffer("<option value = \""+argu[i]+"\">"+value[i]+"</option>\r\n");
		}
		appendBuffer("</select>\r\n");
		return this;
	}

	/*form open upload*/
	public CoconutzFormBase form_fieldset( String argu ){
		appendBuffer("<fieldset>\r\n");
		appendBuffer("<legend>"+argu+"</legend>\r\n");
		return this;
	}
	public CoconutzFormBase form_fieldset_close(){
		appendBuffer("</fieldset>\r\n");
		return this;
	}

	/*form open upload*/
	public CoconutzFormBase form_checkbox( String argu, String value, boolean check ){
		return form_checkbox( Stringtoken(argu), Stringtoken(value), check);
	}
	public CoconutzFormBase form_checkbox( String argu[],String value[],boolean check ){
		appendBuffer("<input type=\"checkbox\" ");
		for( int i=0 ; i < argu.length ; i++ ){
			appendBuffer(argu[i] +"=\"" + value[i] +"\" ");
		}
		if(check)
			appendBuffer("checked = \"checked\" />\r\n");
		else
			appendBuffer("/>\r\n");
		return this;		
	}

	/*form open upload*/
	public CoconutzFormBase form_radio( String argu,String value, boolean check  ){
		return form_radio(Stringtoken(argu), Stringtoken(value), check );
	}
	public CoconutzFormBase form_radio( String argu[],String value[], boolean check ){
		appendBuffer("<input type=\"radio\" ");
		for( int i=0 ; i < argu.length ; i++ ){
			appendBuffer(argu[i] +"=\"" + value[i] +"\" ");
		}
		if(check)
			appendBuffer("checked = \"checked\" />\r\n");
		else
			appendBuffer("/>\r\n");
		return this;		
	}

	/*form open upload*/
	public CoconutzFormBase form_submit( String argu, String value ){
		return form_submit(Stringtoken(argu), Stringtoken(value));
	}
	public CoconutzFormBase form_submit( String argu[], String value[]){
		appendBuffer("<input type=\"submit\" ");
		for( int i=0 ; i < argu.length ; i++ ){
			appendBuffer(argu[i] + "=\""+value[i]+"\" ");
		}
		appendBuffer("/>\r\n");
		return this;
	}

	/*form open upload*/
	public CoconutzFormBase form_label( String name, String argu, String value, String in ){
		return form_label( name, Stringtoken(argu), Stringtoken(value), in );
	}
	public CoconutzFormBase form_label( String name, String argu[] ,String value[], String in){
		appendBuffer("<label for=\""+name+"\" ");
		for( int i=0 ; i < argu.length ; i++ ){
			appendBuffer(argu[i] + "=\""+value[i]+"\" ");
		}
		appendBuffer(">" + in + "</label>\r\n");
		return this;
	}

	/*form open upload*/
	public CoconutzFormBase form_reset( String argu, String value ){
//		<input type="button" onclick="formReset()" value="Reset form">
		return form_reset(Stringtoken(argu), Stringtoken(value));
	}
	public CoconutzFormBase form_reset( String argu[], String value[]){
		appendBuffer("<input type=\"reset\" ");
		for( int i=0 ; i < argu.length ; i++ ){
			appendBuffer(argu[i] + "=\""+value[i]+"\" ");
		}
		appendBuffer("></input>");
		return this;
	}

	/*form open upload*/
	public CoconutzFormBase form_button( String argu, String value, String in ){
		return form_button(Stringtoken(argu), Stringtoken(value) ,in);
	}
	public CoconutzFormBase form_button( String argu[], String value[], String in){
		appendBuffer("<button ");
		for( int i=0 ; i < argu.length ; i++ ){
			appendBuffer(argu[i] + "=\""+value[i]+"\" ");
		}
		appendBuffer(">"+in+"</button>\r\n");
		return this;
	}
	//	public String form_open( ,, ){}

	public CoconutzFormBase setForm(){
		appendBuffer("<form method=\"post\" accept-charset=\"utf-8\" action=\"\">\r\n");
		return this;
	}
	public void form_end(){
		form = new StringBuffer();		
	}
	public void form_reset(){
		form = new StringBuffer();
	}

	public CoconutzFormBase form_close(){
		appendBuffer("</form>\r\n");
		return this;
	}	

	public String getForm(){
		return form.toString();
	}

	/*set input form*/
	public CoconutzFormBase form_hidden(String argu,String value){
		return form_hidden(Stringtoken(argu), Stringtoken(value));
	}
	public CoconutzFormBase form_hidden(String argu[], String value[]){
		appendBuffer("<input type=\"hidden\" ");
		for( int i=0 ; i < argu.length ; i++ ){
			appendBuffer(argu[i] +"=\"" + value[i] +"\" ");			
		}
		appendBuffer("/>\r\n");
		return this;	
	}


	/*form_input*/
	public CoconutzFormBase form_input( String argu, String value ){
		return form_input(Stringtoken(argu), Stringtoken(value));
	}
	public CoconutzFormBase form_input( String argu[], String value[] ){
		appendBuffer("<input type=\"text\" ");
		for( int i=0 ; i < argu.length ; i++ ){
			appendBuffer(argu[i] +"=\"" + value[i] +"\" ");			
		}
		appendBuffer("/>\r\n");
		return this;
	}
	
	/*form_file*/
	public CoconutzFormBase form_file( String argu, String value ){
		return form_file(Stringtoken(argu), Stringtoken(value));
	}
	public CoconutzFormBase form_file( String argu[], String value[] ){
		appendBuffer("<input type=\"file\" ");
		for( int i=0 ; i < argu.length ; i++ ){
			appendBuffer(argu[i] +"=\"" + value[i] +"\" ");			
		}
		appendBuffer("/>\r\n");
		return this;
	}


}
