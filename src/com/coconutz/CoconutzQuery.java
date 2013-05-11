package com.coconutz;
import java.util.Hashtable;
import java.util.StringTokenizer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CoconutzQuery{
	protected JSONArray query;

	public CoconutzQuery(){
		query = new JSONArray();
	}
	public CoconutzQuery(JSONArray query){
		this.query  = query;
	}
	public void setQuery(JSONArray query){
		this.query = query;
	}
	public JSONArray getQuery(){
		JSONArray result = query;
		query = null;
		return result;
	}
	
	public void query_reset(){
		query = new JSONArray();
	}
	
	public String get(){
		JSONArray result = query;
		return result.toString();		
	}
	
	public String POP(){
		String argu = query.toString();
		query = new JSONArray();
		return argu;
	}
	public void setQuery( Hashtable<String, String> query ){		
		
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
	
	private CoconutzQuery superQuery( String key, String argu ) {
		JSONObject result;
		try {
			result = new JSONObject("{\"function\":\""+key+"\",\"param1\":\""+argu+"\"}");
			query.put(result);
		} catch (JSONException e) {
			e.printStackTrace();			
		}
		return this;
	}
	private CoconutzQuery superQuery( String key, String argu1,String argu2 ) {
		JSONObject result;
		try {
			result = new JSONObject("{\"function\":\""+key+"\",\"param1\":\""+argu1+"\",\"param2\":\""+argu2+"\"}");
			query.put(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return this;
	}
	private CoconutzQuery superQuery( String key, String argu1,String argu2,String argu3 ) {
		JSONObject result;
		try {
			result = new JSONObject("{\"function\":\""+key+"\",\"param1\":\""+argu1+"\",\"param2\":\""+argu2+"\",\"param3\":\""+argu3+"\"}");
			query.put(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public CoconutzQuery delete( String argu ){
		return superQuery("delete", argu);
	}
	/*--select--*/
	/** Make Selct Query
	 * ex)
	 * select("table, name, argu")
	 * -> "SELECT table, name, argu"
	 **/
	
	public CoconutzQuery select( String argu ) {
		return superQuery("select",argu);
	}
	//	public CoconutQuery select(String argu){
	//		return select(Stringtoken(argu));
	//	}
	//	public CoconutQuery select(String argu[]){
	//		String result = "SELECT ";
	//		for(int i=0;i<argu.length;i++){
	//			if(  i == argu.length-1 )
	//				result += argu[i];
	//			else
	//				result += argu[i] +",";
	//		}
	//		result += " ";
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	public CoconutzQuery select_max( String argu ) {
		return superQuery("select_max",argu);
	}
	//	public CoconutQuery select_max( String argu ){
	//		String result = "SELECT MAX("+argu+") as" + argu +" " + query;
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	public CoconutzQuery select_max( String argu, String as ) {
		return superQuery("select", argu, as);
	}
	//	public CoconutQuery select_max( String argu, String as ){
	//		String result = "SELECT MAX("+argu+") as" + as +" " + query;		
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	public CoconutzQuery select_min( String argu, String as ) {
		return superQuery("select_min", argu, as);
	}
	//	public CoconutQuery select_min( String argu ){
	//		String result = "SELECT MIN("+argu+") as" + argu +" " + query;;
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	//	public CoconutQuery select_min( String argu, String as ){
	//		String result = "SELECT MIN("+argu+") as" + as +" " + query;
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	public CoconutzQuery select_sum( String argu, String as ) {
		return superQuery("select_sum", argu, as);
	}
	//	public CoconutQuery select_sum( String argu ){
	//		String result = "SELECT SUM("+argu+") as" + query;
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	//	public CoconutQuery select_sum( String argu ,String as){
	//		String result ="SELECT SUM("+argu+") as" + as +" " + query; 
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	//	/*--from--*/
	public CoconutzQuery from( String argu ) {
		return superQuery("from",argu);
	}
	//	public CoconutQuery from( String argu ){
	//		String result = "FROM " + argu +" ";
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	//	/*--join--*/
	public CoconutzQuery join( String argu, String as ) {
		return superQuery("join", argu, as);
	}
	//	public CoconutQuery join( String argu, String as ){
	//		String result = "JOIN " + argu +" ON "+ as +" ";
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	public CoconutzQuery from( String argu, String as, String option ) {
		return superQuery("from", argu, as, option);
	}
	//	public CoconutQuery join( String argu, String as, String option ){
	//		String result = option +"JOIN " + argu +" ON "+ as +" ";;
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	//	/*--where--*/
	public CoconutzQuery where( String argu, String value ) {
		return superQuery("where", argu, value);
	}
	//	public CoconutQuery where( boolean where, String argu, String value ){
	//		return where(where,Stringtoken(argu),Stringtoken(value));
	//	}
	//	public CoconutQuery where( boolean where, String argu[], String value[] ){
	//		String result;
	//		if( where )
	//			result = "WHERE ";
	//		else
	//			result = "AND ";			
	//		for( int i=0 ; i < argu.length ; i++ ){
	//			if( i != argu.length-1 )
	//				result += argu[i] +" = '" + value[i] + "' AND ";
	//			else
	//				result += argu[i] +" = '" + value[i] + "' ";				
	//		}
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	//	/*--or_where--*/
	public CoconutzQuery or_where( String argu, String value ) {
		return superQuery("or_where", argu, value);
	}
	//	public CoconutQuery or_where(boolean where, String argu, String value ){
	//		return or_where(where, Stringtoken(argu),Stringtoken(value));
	//	}
	//	public CoconutQuery or_where( boolean where, String argu[], String value[] ){
	//		String result;
	//		if( where )
	//			result = "WHERE ";
	//		else
	//			result = "OR ";
	//		for( int i=0 ; i < argu.length ; i++ ){
	//			if( i != argu.length-1 )
	//				result += argu[i] +" = '" + value[i] + "' OR ";
	//			else
	//				result += argu[i] +" = '" + value[i] + "' ";				
	//		}
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	//	/*--or_where_in--*/
	public CoconutzQuery or_where_in( String argu, String value ) {
		return superQuery("or_where_in", argu, value);
	}
	//	public CoconutQuery or_where_in( boolean where, String argu, String value ){
	//		return or_where_in(where, argu, Stringtoken(value));
	//	}
	//	public CoconutQuery or_where_in( boolean where, String argu, String value[] ){
	//		String result;
	//		if(where)
	//			result = "WHERE " + argu + " IN (";
	//		else
	//			result = "OR " + argu + " IN (";
	//		for( int i=0 ; i < value.length ; i++ ){
	//			if( i == value.length - 1 )
	//				result += value[i] + ")";
	//			else
	//				result += value[i] + ", ";
	//		}
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	//	/*--where_not_in--*/
	public CoconutzQuery where_not_in( String argu, String value ) {
		return superQuery("where_not_in", argu, value);
	}
	//	public CoconutQuery wehre_not_in( boolean where, String argu, String value ){
	//		return where_not_in(where, argu, Stringtoken(value));
	//	}
	//	public CoconutQuery where_not_in( boolean where, String argu, String value[] ){
	//		String result;
	//		if(where)
	//			result = "WHERE " + argu + " NOT IN (";
	//		else
	//			result = "AND " + argu + " NOT IN (";
	//		for( int i=0 ; i < value.length ; i++ ){
	//			if( i == value.length - 1 )
	//				result += value[i] + ")";
	//			else
	//				result += value[i] + ", ";
	//		}
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	//	/*--or_where_not_in--*/
	public CoconutzQuery or_where_not_in( String argu, String value ) {
		return superQuery("or_where_not_in",argu,value);
	}

	//	public CoconutQuery or_where_not_in( boolean where, String argu, String value ){
	//		return or_where_not_in(where, argu, Stringtoken(value));
	//	}
	//	public CoconutQuery or_where_not_in( boolean where, String argu, String value[] ){
	//		String result;
	//		if(where)
	//			result = "WHERE " + argu + " NOT IN (";
	//		else
	//			result = "OR " + argu + " NOT IN (";
	//		for( int i=0 ; i < value.length ; i++ ){
	//			if( i == value.length - 1 )
	//				result += value[i] + ")";
	//			else
	//				result += value[i] + ", ";
	//		}
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	//	/*--like--*/
//	public CoconutQuery like( String argu, String value ) {
//		return superQuery("like", argu,value);
//	}
	//	public CoconutQuery like( boolean where, String argu, String value ){
	//		return like(where, Stringtoken(argu), Stringtoken(value));
	//	}
	//	public CoconutQuery like( boolean where, String argu[], String value[] ){
	//		String result;
	//		if( where )
	//			result = "WHERE ";
	//		else
	//			result = "AND ";
	//		for( int i=0 ; i < argu.length ; i++ ){
	//			if( i != argu.length-1 )
	//				result += argu[i] +" = ' LIKE %" + value[i] + "% ' AND ";
	//			else
	//				result += argu[i] +" = ' LIKE %" + value[i] + "% ' ";				
	//		}
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	public CoconutzQuery like( String argu, String value, String option ) {
		return superQuery("like",argu,value,option);
	}
	//	public CoconutQuery like( boolean where, String argu, String value ,String option){
	//		return like(where, Stringtoken(argu), Stringtoken(value),option);
	//	}
	//	public CoconutQuery like( boolean where, String argu[], String value[],String option ){
	//		String result;
	//		if( where )
	//			result = "WHERE ";
	//		else
	//			result = "AND ";
	//		for( int i=0 ; i < argu.length ; i++ ){
	//			if( i == argu.length-1 ){
	//				if( option.equals("before") )
	//					result +=  argu[i]+"LIKE " +"%"+value[i] +" ";
	//				else if( option.equals("after") )
	//					result += argu[i]+"LIKE " + value[i]+"% ";
	//				else if( option.equals("both") )
	//					result += argu[i]+"LIKE " +"%"+ value[i] + "% ";		
	//				else
	//					result += argu[i]+"LIKE " + value[i] +" ";
	//			}else{
	//				if( option.equals("before") )
	//					result += argu[i]+"LIKE " +"%"+value[i] + " AND ";
	//				else if( option.equals("after") )
	//					result += argu[i]+"LIKE " + value[i]+"%" + " AND ";
	//				else if( option.equals("both") )
	//					result += argu[i]+"LIKE " +"%"+ value[i] + "%" + " AND ";		
	//				else
	//					result += argu[i]+"LIKE " + value[i] + " AND ";			
	//			}
	//		}
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	//	
//	public CoconutQuery or_like( String argu, String value ) {
//		return superQuery("or_like", argu,value);
//	}	
	//	/*--or_like--*/
	//	public CoconutQuery or_like( boolean where, String argu, String value ){
	//		return or_like(where, Stringtoken(argu), Stringtoken(value));
	//	}
	//	public CoconutQuery or_like( boolean where, String argu[],String value[]){
	//		String result;
	//		if(where)
	//			result = "WHERE ";
	//		else
	//			result = "OR ";
	//		for( int i=0 ; i < argu.length ; i++ ){
	//			if( i != argu.length-1 )
	//				result += argu[i] +" = ' LIKE %" + value[i] + "% ' OR ";
	//			else
	//				result += argu[i] +" = ' LIKE %" + value[i] + "% ' ";				
	//		}
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	public CoconutzQuery or_like( String argu, String value ,String option) {
		return superQuery("or_like", argu,value,option);
	}

	//	public CoconutQuery or_like( boolean where, String argu,String value,String option){
	//		return or_like(where, Stringtoken(argu), Stringtoken(value), option);
	//	}
	//	public CoconutQuery or_like( boolean where,String argu[],String value[],String option){
	//		String result;
	//		if( where )
	//			result = "WHERE ";
	//		else
	//			result = "OR ";
	//		for( int i=0 ; i < argu.length ; i++ ){
	//			if( i == argu.length-1 ){
	//				if( option.equals("before") )
	//					result +=  argu[i]+"LIKE " +"%"+value[i] +" ";
	//				else if( option.equals("after") )
	//					result += argu[i]+"LIKE " + value[i]+"% ";
	//				else if( option.equals("both") )
	//					result += argu[i]+"LIKE " +"%"+ value[i] + "% ";		
	//				else
	//					result += argu[i]+"LIKE " + value[i] +" ";
	//			}else{
	//				if( option.equals("before") )
	//					result += argu[i]+"LIKE " +"%"+value[i] + " OR ";
	//				else if( option.equals("after") )
	//					result += argu[i]+"LIKE " + value[i]+"%" + " OR ";
	//				else if( option.equals("both") )
	//					result += argu[i]+"LIKE " +"%"+ value[i] + "%" + " OR ";		
	//				else
	//					result += argu[i]+"LIKE " + value[i] + " OR ";			
	//			}
	//		}
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	public CoconutzQuery not_like( String argu, String value ) {
		return superQuery("or_where_not_in",argu,value);
	}	
	//	public CoconutQuery not_like( boolean where,String argu,String value){
	//		return not_like(where, Stringtoken(argu), Stringtoken(value));
	//	}
	//	public CoconutQuery not_like( boolean where,String argu[],String value[]){
	//		String result;
	//		if(where)
	//			result = "WHERE ";
	//		else
	//			result = "AND ";
	//		for( int i=0 ; i < argu.length ; i++ ){
	//			if( i != argu.length-1 )
	//				result += argu[i] +" = ' NOT LIKE %" + value[i] + "% ' AND ";
	//			else
	//				result += argu[i] +" = ' NOT LIKE %" + value[i] + "% ' ";				
	//		}
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	public CoconutzQuery or_where_not_in( String argu, String value, String option ) {
		return superQuery("or_where_not_in",argu,value,option);
	}	
	//	public CoconutQuery not_like( boolean where,String argu,String value,String option){
	//		return not_like(where, Stringtoken(argu), Stringtoken(value),option);
	//	}
	//	public CoconutQuery not_like( boolean where,String argu[],String value[],String option){
	//		String result;
	//		if( where )
	//			result = "WHERE ";
	//		else
	//			result = "AND ";
	//		for( int i=0 ; i < argu.length ; i++ ){
	//			if( i == argu.length-1 ){
	//				if( option.equals("before") )
	//					result +=  argu[i]+"NOT LIKE " +"%"+value[i] +" ";
	//				else if( option.equals("after") )
	//					result += argu[i]+"NOT LIKE " + value[i]+"% ";
	//				else if( option.equals("both") )
	//					result += argu[i]+"NOT LIKE " +"%"+ value[i] + "% ";		
	//				else
	//					result += argu[i]+"NOT LIKE " + value[i] +" ";
	//			}else{
	//				if( option.equals("before") )
	//					result += argu[i]+"NOT LIKE " +"%"+value[i] + " AND ";
	//				else if( option.equals("after") )
	//					result += argu[i]+"NOT LIKE " + value[i]+"%" + " AND ";
	//				else if( option.equals("both") )
	//					result += argu[i]+"NOT LIKE " +"%"+ value[i] + "%" + " AND ";		
	//				else
	//					result += argu[i]+"NOT LIKE " + value[i] + " AND ";			
	//			}
	//		}
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	public CoconutzQuery or_not_like( String argu, String value ) {
		return superQuery("or_not_like",argu,value);
	}	
	//	public CoconutQuery or_not_like( boolean where, String argu,String value){
	//		return or_not_like(where, Stringtoken(argu), Stringtoken(value));
	//	}
	//	public CoconutQuery or_not_like( boolean where, String argu[],String value[]){
	//		String result;
	//		if(where)
	//			result = "WHERE ";
	//		else
	//			result = "OR ";
	//		for( int i=0 ; i < argu.length ; i++ ){
	//			if( i != argu.length-1 )
	//				result += argu[i] +" = ' NOT LIKE %" + value[i] + "% ' AND ";
	//			else
	//				result += argu[i] +" = ' NOT LIKE %" + value[i] + "% ' ";				
	//		}
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	public CoconutzQuery or_not_like( String argu, String value, String option ) {
		return superQuery("or_not_like",argu,value,option);
	}
	//	public CoconutQuery or_not_like( boolean where, String argu,String value,String option){
	//		return or_not_like(where, Stringtoken(argu), Stringtoken(value),option);
	//	}
	//	public CoconutQuery or_not_like( boolean where, String argu[],String value[],String option){
	//		String result;
	//		if( where )
	//			result = "WHERE ";
	//		else
	//			result = "OR ";
	//		for( int i=0 ; i < argu.length ; i++ ){
	//			if( i == argu.length-1 ){
	//				if( option.equals("before") )
	//					result +=  argu[i]+"NOT LIKE " +"%"+value[i] +" ";
	//				else if( option.equals("after") )
	//					result += argu[i]+"NOT LIKE " + value[i]+"% ";
	//				else if( option.equals("both") )
	//					result += argu[i]+"NOT LIKE " +"%"+ value[i] + "% ";		
	//				else
	//					result += argu[i]+"NOT LIKE " + value[i] +" ";
	//			}else{
	//				if( option.equals("before") )
	//					result += argu[i]+"NOT LIKE " +"%"+value[i] + " OR ";
	//				else if( option.equals("after") )
	//					result += argu[i]+"NOT LIKE " + value[i]+"%" + " OR ";
	//				else if( option.equals("both") )
	//					result += argu[i]+"NOT LIKE " +"%"+ value[i] + "%" + " OR ";		
	//				else
	//					result += argu[i]+"NOT LIKE " + value[i] + " OR ";			
	//			}
	//		}
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	public CoconutzQuery group_by( String argu ) {
		return superQuery("group_by",argu );
	}
	//	public CoconutQuery group_by( String argu ){
	//		return group_by(Stringtoken(argu));
	//	}
	//	public CoconutQuery group_by( String argu[]){
	//		String result = "GROUP BY " ;
	//		for(int i=0;i<argu.length;i++){
	//			if( i != argu.length -1 )
	//				result += argu[i] + " , ";
	//			else
	//				result += argu[i] + " ";
	//		}
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	public CoconutzQuery distinct() {
		return superQuery("distinct","");
	}
	//	public void distinct(){
	//		String value = "SELECT";
	//		int check=-1;
	//		for( int i=-1 ; (i = query.indexOf(value, i+1)) != -1 ; ){
	//			check = i+value.length();
	//		}
	//		if(check > 0){
	//			query.replace(0, query.length(), query.substring(0, check) + " DISTINCT" + query.substring(check,query.length()));
	//		}
	//	}
	public CoconutzQuery having( String argu, boolean escape ) {
		String bool = " "+escape;
		return superQuery("having",argu,bool.toUpperCase());
	}	
	//	public CoconutQuery having(  String value, boolean escape ){
	//		return having(Stringtoken(value), escape);
	//	}
	//	public CoconutQuery having( String value[], boolean escape ){
	//		String result = "HAVING ";
	//		for(int i=0;i<value.length;i++){
	//			if( i == value.length )
	//				result += value[i]+ " ";
	//			else
	//				result += value[i]+ " , ";
	//		}
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	public CoconutzQuery or_having( String argu, boolean escape  ) {
		String bool = " "+escape;
		return superQuery("having",argu,bool.toUpperCase());
	}	
	//	public CoconutQuery or_having( String value, boolean escape ){
	//		return or_having(Stringtoken(value), escape);
	//	}
	//	public CoconutQuery or_having( String value[], boolean escape ){
	//		String result = "HAVING ";
	//		for(int i=0;i<value.length;i++){
	//			if( i == value.length )
	//				result += value[i]+ " ";
	//			else
	//				result += value[i]+ " OR ";
	//		}
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	public CoconutzQuery order_by( String argu, String option ) {
		switch(option){
		case "asc":
		case "desc":
		case "random":
			break;
		default:
			option = "";
			break;
		}
		return superQuery("having",argu,option);
	}	
	//	public CoconutQuery order_by( String argu, String order_option ){
	//		return order_by(Stringtoken(argu), Stringtoken(order_option));
	//	}
	//	public CoconutQuery order_by( String argu[], String order_option[] ){
	//		String result = "ORDER BY ";		
	//		for(int i=0;i<argu.length;i++){
	//			if( i == argu.length-1 )
	//				result += " "+ argu[i] +" "+order_option[i] +" ";
	//			else
	//				result += " "+ argu[i] +" "+order_option[i] +", ";
	//		}
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	//	
//	public CoconutQuery limit( int argu ) {
//		return superQuery("limit", ""+argu);
//	}
	//	public CoconutQuery limit(int limit){
	//		String result = "LIMIT "+limit+" ";
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}
	public CoconutzQuery limit( int argu,int offset ) {
		return superQuery("limit", ""+argu,""+offset);
	}	
	//	public CoconutQuery limit(int limit, int offset){
	//		String result = "LIMIT "+limit+" "+offset+" ";
	//		query.append(result);
	//		return new CoconutQuery( query );
	//	}	
	public CoconutzQuery insert( String table, String argu, String value ) {
		return superQuery("insert", table, argu, value);
	}
	//	public void insert( String table, String argu, String value ){
	//		insert(table, Stringtoken(argu), Stringtoken(value));
	//	}
	//	public void insert( String table, String argu[], String value[] ){
	//		String result = "INSERT INTO " + table + "(";
	//		for( int i=0 ; i<argu.length ; i++ ){
	//			if( i == argu.length-1 )
	//				result += " "+ argu[i] + ") VALUES (";
	//			else
	//				result += " "+argu[i] + ", ";
	//		}
	//		for( int i=0; i<value.length ; i++){
	//			if( i == argu.length-1 )
	//				result += " '"+ value[i] + "') ";
	//			else
	//				result += " '"+ value[i] + "', ";			
	//		}
	//		query.append(result);
	//	}
	//	public void insert( String table, String argu[], String value[][] ){
	//		String result = "INSERT INTO " + table + "(";
	//		for( int i=0 ; i<argu.length ; i++ ){
	//			if( i == argu.length-1 )
	//				result += " "+ argu[i] + ") VALUES ";
	//			else
	//				result += " "+argu[i] + ", ";
	//		}
	//		for (int i = 0; i < value.length; i++) {
	//			result += " ( ";
	//			for( int j = 0; j < value[i].length ; j++){
	//				if( j == value[i].length-1 )
	//					result += " '"+ value[j] + "'  ";
	//				else
	//					result += " '"+ value[j] + "', ";			
	//			}		
	//			
	//			if( i == value.length-1 )
	//				result += ") ";
	//			else
	//				result += "), ";
	//		}
	//		query.append(result);
	//	}
	//	/**coconut ci - insert**/
	public CoconutzQuery set() {
		return superQuery("set", "");
	}
	//	public void set(){}
	//	/**coconut ci - insert batch**/	
	//	public void insert_batch(String table, String argu, String value){
	//		insert(table, argu, value);
	//		//+ escape
	//	}
	//	/**coconut ci - insert batch**/	

	//	public void insert_batch(String table, String argu[], String value[]){
	//		insert(table, argu, value);
	//		//+ escape
	//	}
	//	/**coconut ci - insert batch**/	
	//	public void insert_batch(String table, String argu[], String value[][]){
	//		insert(table, argu, value);
	//		//+ escape
	//	}
	public CoconutzQuery update(String argu) {
		return superQuery("update", argu);
	}
	//	/**coconut ci - update**/		
	//	public void update( String table,String argu, String value ){
	//	}
	//	/**coconut ci - update**/		
	//	public void update( String table,String argu[], String value[] ){
	//	}
	//	/**coconut ci - update_batch**/		
	//	public void update_batch( String table, String argu, String value ){
	//		update(table, argu, value);
	//		//+ escape
	//	}
	//	/**coconut ci - update_batch**/		
	//	public void update_batch( String table, String argu[], String value[] ){
	//		update(table, argu, value);
	//		//+ escape
	//	}
	//	/**coconut ci - delete**/		
	public CoconutzQuery delete(String argu, String where) {
		return superQuery("delete", argu , where);
	}
	
	//	public void delete( String table ){}
	//	/**coconut ci - delete**/		
	//	public void delete( String table, String where ){}
	//	/**coconut ci - delete**/		
	//	public void delete( String table[] ){}
	//	/**coconut ci - count_all**/
	public CoconutzQuery count_all(String argu) {
		return superQuery("count_all", argu);
	}
	//	public int count_all( String argu ){
	//		int result = 0;
	//		return result;
	//	}
	public CoconutzQuery count_all_result() {
		return superQuery("count_all_result", "");
	}	
	//	/**coconut ci - count_all_result**/
	//	public int count_all_result( String argu ){
	//		int result =0;
	//		return result;
	//	}
	//	/**coconut ci - escape**/
	//	public String escape( String argu ){
	//		return argu;
	//	}

}
