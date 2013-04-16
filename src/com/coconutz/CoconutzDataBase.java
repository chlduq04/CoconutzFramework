package com.coconutz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.coconutz.HelperRef.CoconutzCaptcha;
public class CoconutzDataBase {
	private volatile static CoconutzDataBase instance = null;
	private StringBuffer query;
	public CoconutzDataBase(){
		query = new StringBuffer();
	}
	public CoconutzDataBase( StringBuffer argu ){
		query = argu;
	}
	public CoconutzDataBase( String argu ){
		query = new StringBuffer();
		query.append(argu);
	}
	public CoconutzDataBase(JSONArray json) throws JSONException{
		query = new StringBuffer();
		coconutDB(json);
	}
	public void setQuery( StringBuffer query){
		this.query = query;
	}
	public static CoconutzDataBase getInstance( StringBuffer query ) {
		if (instance == null) {
			synchronized(CoconutzDataBase.class){
				if (instance == null) {
					instance = new CoconutzDataBase( query );
				}
			}
		}
		instance.setQuery(query);
		return instance;
	}
	private ArrayList<HashMap<String, String>> parser(String argu){
		String first_argu="";
		boolean check = false;
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();
		StringTokenizer token = new StringTokenizer(argu, "},{,[,]");
		StringTokenizer detail_token;
		while(token.hasMoreElements()){
			detail_token = new StringTokenizer(token.nextToken(),"\",:");
			while(detail_token.hasMoreElements()){
				String key = detail_token.nextElement().toString();
				if(detail_token.hasMoreElements()){					
					String value = detail_token.nextElement().toString();
					if(first_argu.equals(key)){
						list.add(map);
						map = new HashMap<>();
					}
					if(!check){
						check = true;
						first_argu = key;
					}
					map.put(key, value);
				}else{
					map.put(key, "");
				}
			}
		}
		list.add(map);		
		return list;
	}
	private void coconutDB(JSONArray m) throws JSONException{
		boolean where = true;
		boolean like = true;
		for(int i=0;i<m.length();i++){
			JSONObject k = m.getJSONObject(i);		
			switch(k.get("function").toString()){
			case "select":
				select(k.get("param1").toString());
				break;
			case "select_max":
				select_max(k.get("param1").toString(),k.get("param2").toString());
				break;
			case "select_min":
				select_min(k.get("param1").toString(),k.get("param2").toString());
				break;
			case "select_sum":
				select_sum(k.get("param1").toString(),k.get("param2").toString());
				break;
			case "from":
				from(k.get("param1").toString());
				break;
			case "join":
				join(k.get("param1").toString(),k.get("param2").toString(),k.get("param3").toString());
				break;
			case "where":
				where(where,k.get("param1").toString(),k.get("param2").toString());
				where = false;
				break;
			case "or_where":
				or_where(where,k.get("param1").toString(),k.get("param2").toString());
				where = false;
				break;
			case "or_where_in":
				or_where_in(where,k.get("param1").toString(),k.get("param2").toString());
				where = false;
				break;
			case "wehre_not_in":
				where_not_in(where,k.get("param1").toString(),k.get("param2").toString());
				where = false;
				break;
			case "or_where_not_in":
				where_not_in(where,k.get("param1").toString(),k.get("param2").toString());
				where = false;
				break;
			case "like":
				like(like,k.get("param1").toString(),k.get("param2").toString(),k.get("param3").toString());
				like = false;
				break;
			case "or_like":
				or_like(like,k.get("param1").toString(),k.get("param2").toString(),k.get("param3").toString());
				like = false;
				break;
			case "not_like":
				not_like(like,k.get("param1").toString(),k.get("param2").toString(),k.get("param3").toString());
				like = false;
				break;
			case "or_not_like":
				or_not_like(like,k.get("param1").toString(),k.get("param2").toString(),k.get("param3").toString());
				like = false;
				break;
			case "group_by":
				group_by(k.get("param1").toString());
				break;
			case "distinct":
				distinct();
				break;
			case "having":
				having(k.get("param1").toString(), Boolean.parseBoolean(k.get("param2").toString().trim()) );
				break;
			case "or_having":
				or_having(k.get("param1").toString(), Boolean.parseBoolean(k.get("param2").toString().trim()) );
				break;
			case "order_by":
				order_by(k.get("param1").toString(), k.get("param2").toString() );
				break;
			case "limit":
				limit(Integer.parseInt(k.get("param1").toString()), Integer.parseInt(k.get("param2").toString()));
				break;
			case "insert":
				insert(k.get("param1").toString(), k.get("param2").toString(), k.get("param3").toString());
				break;
			}
		}
	}
	public String getQuery( ){
		String argu = query.toString().trim();
		query_reset();
		return argu;
	}
	public JSONObject getJson() throws JSONException{
		return new JSONObject("{\"query\":\""+query.toString()+"\"}");
	}
	public void query_reset(){
		query.delete(0, query.length());
	}
	public void setQuery( String argu ){
		query_reset();
		query.append( argu );
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
	/*--select--*/
	/**Make Selct Query
	 * ex)
	 * select("table, name, argu")
	 * -> "SELECT table, name, argu"
	 * **/	
	private CoconutzDataBase select(String argu){
		return select(Stringtoken(argu));
	}
	private CoconutzDataBase select(String argu[]){
		String result = "SELECT ";
		for(int i=0;i<argu.length;i++){
			if(  i == argu.length-1 )
				result += argu[i];
			else
				result += argu[i] +", ";
		}
		result += " ";
		query.append(result);
		return getInstance(query);
	}

	private CoconutzDataBase select_max( String argu ){
		String result = "SELECT MAX("+argu+") as" + argu +" " + query;
		query.append(result);
		return getInstance(query);
	}
	private CoconutzDataBase select_max( String argu, String as ){
		String result = "SELECT MAX("+argu+") as" + as +" " + query;		
		query.append(result);
		return getInstance(query);
	}
	private CoconutzDataBase select_min( String argu ){
		String result = "SELECT MIN("+argu+") as" + argu +" " + query;;
		query.append(result);
		return getInstance(query);
	}
	private CoconutzDataBase select_min( String argu, String as ){
		String result = "SELECT MIN("+argu+") as" + as +" " + query;
		query.append(result);
		return getInstance(query);
	}
	private CoconutzDataBase select_sum( String argu ){
		String result = "SELECT SUM("+argu+") as" + query;
		query.append(result);
		return getInstance(query);
	}
	private CoconutzDataBase select_sum( String argu ,String as){
		String result ="SELECT SUM("+argu+") as" + as +" " + query; 
		query.append(result);
		return getInstance(query);
	}
	/*--from--*/
	private CoconutzDataBase from( String argu ){
		String result = "FROM " + argu +" ";
		query.append(result);
		return getInstance(query);
	}
	/*--join--*/
	private CoconutzDataBase join( String argu, String as ){
		String result = "JOIN " + argu +" ON "+ as +" ";
		query.append(result);
		return getInstance(query);
	}
	private CoconutzDataBase join( String argu, String as, String option ){
		String result = option +"JOIN " + argu +" ON "+ as +" ";;
		query.append(result);
		return getInstance(query);
	}
	/*--where--*/
	private CoconutzDataBase where( boolean where, String argu, String value ){
		return where(where,Stringtoken(argu),Stringtoken(value));
	}
	private CoconutzDataBase where( boolean where, String argu[], String value[] ){
		String result;
		if( where )
			result = "WHERE ";
		else
			result = "AND ";			
		for( int i=0 ; i < argu.length ; i++ ){
			if( i != argu.length-1 )
				result += argu[i] +" = '" + value[i] + "' AND ";
			else
				result += argu[i] +" = '" + value[i] + "' ";				
		}
		query.append(result);
		return getInstance(query);
	}
	/*--or_where--*/
	private CoconutzDataBase or_where(boolean where, String argu, String value ){
		return or_where(where, Stringtoken(argu),Stringtoken(value));
	}
	private CoconutzDataBase or_where( boolean where, String argu[], String value[] ){
		String result;
		if( where )
			result = "WHERE ";
		else
			result = "OR ";
		for( int i=0 ; i < argu.length ; i++ ){
			if( i != argu.length-1 )
				result += argu[i] +" = '" + value[i] + "' OR ";
			else
				result += argu[i] +" = '" + value[i] + "' ";				
		}
		query.append(result);
		return getInstance(query);
	}
	/*--or_where_in--*/

	private CoconutzDataBase or_where_in( boolean where, String argu, String value ){
		return or_where_in(where, argu, Stringtoken(value));
	}
	private CoconutzDataBase or_where_in( boolean where, String argu, String value[] ){
		String result;
		if(where)
			result = "WHERE " + argu + " IN (";
		else
			result = "OR " + argu + " IN (";
		for( int i=0 ; i < value.length ; i++ ){
			if( i == value.length - 1 )
				result += value[i] + ")";
			else
				result += value[i] + ", ";
		}
		query.append(result);
		return getInstance(query);
	}
	/*--where_not_in--*/

	private CoconutzDataBase where_not_in( boolean where, String argu, String value ){
		return where_not_in(where, argu, Stringtoken(value));
	}
	private CoconutzDataBase where_not_in( boolean where, String argu, String value[] ){
		String result;
		if(where)
			result = "WHERE " + argu + " NOT IN (";
		else
			result = "AND " + argu + " NOT IN (";
		for( int i=0 ; i < value.length ; i++ ){
			if( i == value.length - 1 )
				result += value[i] + ")";
			else
				result += value[i] + ", ";
		}
		query.append(result);
		return getInstance(query);
	}
	/*--or_where_not_in--*/
	private CoconutzDataBase or_where_not_in( boolean where, String argu, String value ){
		return or_where_not_in(where, argu, Stringtoken(value));
	}
	private CoconutzDataBase or_where_not_in( boolean where, String argu, String value[] ){
		String result;
		if(where)
			result = "WHERE " + argu + " NOT IN (";
		else
			result = "OR " + argu + " NOT IN (";
		for( int i=0 ; i < value.length ; i++ ){
			if( i == value.length - 1 )
				result += value[i] + ")";
			else
				result += value[i] + ", ";
		}
		query.append(result);
		return getInstance(query);
	}
	/*--like--*/
	private CoconutzDataBase like( boolean where, String argu, String value ){
		return like(where, Stringtoken(argu), Stringtoken(value));
	}
	private CoconutzDataBase like( boolean where, String argu[], String value[] ){
		String result;
		if( where )
			result = "WHERE ";
		else
			result = "AND ";
		for( int i=0 ; i < argu.length ; i++ ){
			if( i != argu.length-1 )
				result += argu[i] +" = ' LIKE %" + value[i] + "% ' AND ";
			else
				result += argu[i] +" = ' LIKE %" + value[i] + "% ' ";				
		}
		query.append(result);
		return getInstance(query);
	}
	private CoconutzDataBase like( boolean where, String argu, String value ,String option){
		return like(where, Stringtoken(argu), Stringtoken(value),option);
	}
	private CoconutzDataBase like( boolean where, String argu[], String value[],String option ){
		String result;
		if( where )
			result = "WHERE ";
		else
			result = "AND ";
		for( int i=0 ; i < argu.length ; i++ ){
			if( i == argu.length-1 ){
				if( option.equals("before") )
					result +=  argu[i]+"LIKE " +"%"+value[i] +" ";
				else if( option.equals("after") )
					result += argu[i]+"LIKE " + value[i]+"% ";
				else if( option.equals("both") )
					result += argu[i]+"LIKE " +"%"+ value[i] + "% ";		
				else
					result += argu[i]+"LIKE " + value[i] +" ";
			}else{
				if( option.equals("before") )
					result += argu[i]+"LIKE " +"%"+value[i] + " AND ";
				else if( option.equals("after") )
					result += argu[i]+"LIKE " + value[i]+"%" + " AND ";
				else if( option.equals("both") )
					result += argu[i]+"LIKE " +"%"+ value[i] + "%" + " AND ";		
				else
					result += argu[i]+"LIKE " + value[i] + " AND ";			
			}
		}
		query.append(result);
		return getInstance(query);
	}

	/*--or_like--*/
	private CoconutzDataBase or_like( boolean where, String argu, String value ){
		return or_like(where, Stringtoken(argu), Stringtoken(value));
	}
	private CoconutzDataBase or_like( boolean where, String argu[],String value[]){
		String result;
		if(where)
			result = "WHERE ";
		else
			result = "OR ";
		for( int i=0 ; i < argu.length ; i++ ){
			if( i != argu.length-1 )
				result += argu[i] +" = ' LIKE %" + value[i] + "% ' OR ";
			else
				result += argu[i] +" = ' LIKE %" + value[i] + "% ' ";				
		}
		query.append(result);
		return getInstance(query);
	}
	private CoconutzDataBase or_like( boolean where, String argu,String value,String option){
		return or_like(where, Stringtoken(argu), Stringtoken(value), option);
	}
	private CoconutzDataBase or_like( boolean where,String argu[],String value[],String option){
		String result;
		if( where )
			result = "WHERE ";
		else
			result = "OR ";
		for( int i=0 ; i < argu.length ; i++ ){
			if( i == argu.length-1 ){
				if( option.equals("before") )
					result +=  argu[i]+"LIKE " +"%"+value[i] +" ";
				else if( option.equals("after") )
					result += argu[i]+"LIKE " + value[i]+"% ";
				else if( option.equals("both") )
					result += argu[i]+"LIKE " +"%"+ value[i] + "% ";		
				else
					result += argu[i]+"LIKE " + value[i] +" ";
			}else{
				if( option.equals("before") )
					result += argu[i]+"LIKE " +"%"+value[i] + " OR ";
				else if( option.equals("after") )
					result += argu[i]+"LIKE " + value[i]+"%" + " OR ";
				else if( option.equals("both") )
					result += argu[i]+"LIKE " +"%"+ value[i] + "%" + " OR ";		
				else
					result += argu[i]+"LIKE " + value[i] + " OR ";			
			}
		}
		query.append(result);
		return getInstance(query);
	}
	private CoconutzDataBase not_like( boolean where,String argu,String value){
		return not_like(where, Stringtoken(argu), Stringtoken(value));
	}
	private CoconutzDataBase not_like( boolean where,String argu[],String value[]){
		String result;
		if(where)
			result = "WHERE ";
		else
			result = "AND ";
		for( int i=0 ; i < argu.length ; i++ ){
			if( i != argu.length-1 )
				result += argu[i] +" = ' NOT LIKE %" + value[i] + "% ' AND ";
			else
				result += argu[i] +" = ' NOT LIKE %" + value[i] + "% ' ";				
		}
		query.append(result);
		return getInstance(query);
	}
	private CoconutzDataBase not_like( boolean where,String argu,String value,String option){
		return not_like(where, Stringtoken(argu), Stringtoken(value),option);
	}
	private CoconutzDataBase not_like( boolean where,String argu[],String value[],String option){
		String result;
		if( where )
			result = "WHERE ";
		else
			result = "AND ";
		for( int i=0 ; i < argu.length ; i++ ){
			if( i == argu.length-1 ){
				if( option.equals("before") )
					result +=  argu[i]+"NOT LIKE " +"%"+value[i] +" ";
				else if( option.equals("after") )
					result += argu[i]+"NOT LIKE " + value[i]+"% ";
				else if( option.equals("both") )
					result += argu[i]+"NOT LIKE " +"%"+ value[i] + "% ";		
				else
					result += argu[i]+"NOT LIKE " + value[i] +" ";
			}else{
				if( option.equals("before") )
					result += argu[i]+"NOT LIKE " +"%"+value[i] + " AND ";
				else if( option.equals("after") )
					result += argu[i]+"NOT LIKE " + value[i]+"%" + " AND ";
				else if( option.equals("both") )
					result += argu[i]+"NOT LIKE " +"%"+ value[i] + "%" + " AND ";		
				else
					result += argu[i]+"NOT LIKE " + value[i] + " AND ";			
			}
		}
		query.append(result);
		return getInstance(query);
	}
	private CoconutzDataBase or_not_like( boolean where, String argu,String value){
		return or_not_like(where, Stringtoken(argu), Stringtoken(value));
	}
	private CoconutzDataBase or_not_like( boolean where, String argu[],String value[]){
		String result;
		if(where)
			result = "WHERE ";
		else
			result = "OR ";
		for( int i=0 ; i < argu.length ; i++ ){
			if( i != argu.length-1 )
				result += argu[i] +" = ' NOT LIKE %" + value[i] + "% ' AND ";
			else
				result += argu[i] +" = ' NOT LIKE %" + value[i] + "% ' ";				
		}
		query.append(result);
		return getInstance(query);
	}
	private CoconutzDataBase or_not_like( boolean where, String argu,String value,String option){
		return or_not_like(where, Stringtoken(argu), Stringtoken(value),option);
	}
	private CoconutzDataBase or_not_like( boolean where, String argu[],String value[],String option){
		String result;
		if( where )
			result = "WHERE ";
		else
			result = "OR ";
		for( int i=0 ; i < argu.length ; i++ ){
			if( i == argu.length-1 ){
				if( option.equals("before") )
					result +=  argu[i]+"NOT LIKE " +"%"+value[i] +" ";
				else if( option.equals("after") )
					result += argu[i]+"NOT LIKE " + value[i]+"% ";
				else if( option.equals("both") )
					result += argu[i]+"NOT LIKE " +"%"+ value[i] + "% ";		
				else
					result += argu[i]+"NOT LIKE " + value[i] +" ";
			}else{
				if( option.equals("before") )
					result += argu[i]+"NOT LIKE " +"%"+value[i] + " OR ";
				else if( option.equals("after") )
					result += argu[i]+"NOT LIKE " + value[i]+"%" + " OR ";
				else if( option.equals("both") )
					result += argu[i]+"NOT LIKE " +"%"+ value[i] + "%" + " OR ";		
				else
					result += argu[i]+"NOT LIKE " + value[i] + " OR ";			
			}
		}
		query.append(result);
		return getInstance(query);
	}
	private CoconutzDataBase group_by( String argu ){
		return group_by(Stringtoken(argu));
	}
	private CoconutzDataBase group_by( String argu[]){
		String result = "GROUP BY " ;
		for(int i=0;i<argu.length;i++){
			if( i != argu.length -1 )
				result += argu[i] + " , ";
			else
				result += argu[i] + " ";
		}
		query.append(result);
		return getInstance(query);
	}
	private void distinct(){
		String value = "SELECT";
		int check=-1;
		for( int i=-1 ; (i = query.indexOf(value, i+1)) != -1 ; ){
			check = i+value.length();
		}
		if(check > 0){
			query.replace(0, query.length(), query.substring(0, check) + " DISTINCT" + query.substring(check,query.length()));
		}
	}
	private CoconutzDataBase having(  String value, boolean escape ){
		return having(Stringtoken(value), escape);
	}
	private CoconutzDataBase having( String value[], boolean escape ){
		String result = "HAVING ";
		for(int i=0;i<value.length;i++){
			if( i == value.length )
				result += value[i]+ " ";
			else
				result += value[i]+ " , ";
		}
		query.append(result);
		return getInstance(query);
	}
	private CoconutzDataBase or_having( String value, boolean escape ){
		return or_having(Stringtoken(value), escape);
	}
	private CoconutzDataBase or_having( String value[], boolean escape ){
		String result = "HAVING ";
		for(int i=0;i<value.length;i++){
			if( i == value.length )
				result += value[i]+ " ";
			else
				result += value[i]+ " OR ";
		}
		query.append(result);
		return getInstance(query);
	}
	private CoconutzDataBase order_by( String argu, String order_option ){
		return order_by(Stringtoken(argu), Stringtoken(order_option));
	}
	private CoconutzDataBase order_by( String argu[], String order_option[] ){
		String result = "ORDER BY ";		
		for(int i=0;i<argu.length;i++){
			if( i == argu.length-1 )
				result += " "+ argu[i] +" "+order_option[i] +" ";
			else
				result += " "+ argu[i] +" "+order_option[i] +", ";
		}
		query.append(result);
		return getInstance(query);
	}

	private CoconutzDataBase limit(int limit){
		String result = "LIMIT "+limit+" ";
		query.append(result);
		return getInstance(query);
	}
	private CoconutzDataBase limit(int limit, int offset){
		String result = "LIMIT "+limit+" "+offset+" ";
		query.append(result);
		return getInstance(query);
	}	
	private void insert( String table, String argu, String value ){
		insert(table, Stringtoken(argu), Stringtoken(value));
	}
	private void insert( String table, String argu[], String value[] ){
		String result = "INSERT INTO " + table + "(";
		for( int i=0 ; i<argu.length ; i++ ){
			if( i == argu.length-1 )
				result += " "+ argu[i] + ") VALUES (";
			else
				result += " "+argu[i] + ", ";
		}
		for( int i=0; i<value.length ; i++){
			if( i == argu.length-1 )
				result += " '"+ value[i] + "') ";
			else
				result += " '"+ value[i] + "', ";			
		}
		query.append(result);
	}
	private void insert( String table, String argu[], String value[][] ){
		String result = "INSERT INTO " + table + "(";
		for( int i=0 ; i<argu.length ; i++ ){
			if( i == argu.length-1 )
				result += " "+ argu[i] + ") VALUES ";
			else
				result += " "+argu[i] + ", ";
		}
		for (int i = 0; i < value.length; i++) {
			result += " ( ";
			for( int j = 0; j < value[i].length ; j++){
				if( j == value[i].length-1 )
					result += " '"+ value[j] + "'  ";
				else
					result += " '"+ value[j] + "', ";			
			}		

			if( i == value.length-1 )
				result += ") ";
			else
				result += "), ";
		}
		query.append(result);
	}
	/**coconut ci - insert**/
	private void set(){}
	/**coconut ci - insert batch**/	
	private void insert_batch(String table, String argu, String value){
		insert(table, argu, value);
		//+ escape
	}
	/**coconut ci - insert batch**/	

	private void insert_batch(String table, String argu[], String value[]){
		insert(table, argu, value);
		//+ escape
	}
	/**coconut ci - insert batch**/	
	private void insert_batch(String table, String argu[], String value[][]){
		insert(table, argu, value);
		//+ escape
	}
	/**coconut ci - update**/		
	private void update( String table,String argu, String value ){
	}
	/**coconut ci - update**/		
	private void update( String table,String argu[], String value[] ){
	}
	/**coconut ci - update_batch**/		
	private void update_batch( String table, String argu, String value ){
		update(table, argu, value);
		//+ escape
	}
	/**coconut ci - update_batch**/		
	private void update_batch( String table, String argu[], String value[] ){
		update(table, argu, value);
		//+ escape
	}
	/**coconut ci - delete**/
	private void delete( String table ){}
	/**coconut ci - delete**/		
	private void delete( String table, String where ){}
	/**coconut ci - delete**/		
	private void delete( String table[] ){}
	/**coconut ci - count_all**/
	private int count_all( String argu ){
		int result = 0;
		return result;
	}
	/**coconut ci - count_all_result**/
	private int count_all_result( String argu ){
		int result =0;
		return result;
	}
	/**coconut ci - escape**/
	private String escape( String argu ){
		return argu;
	}

}
