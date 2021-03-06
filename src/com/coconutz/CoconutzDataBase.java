package com.coconutz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.coconutz.HelperRef.CoconutzCaptcha;
import com.coconutz.Service.CoconutzSetting;

public class CoconutzDataBase extends CoconutzSetting{
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
		if(!hideCode){
			for(int i=0;i<m.length();i++){
				JSONObject k = m.getJSONObject(i);		
				switch(k.get("function").toString()){
				case "select": 
					select(k.get("param1").toString());
					break;
				case "select_max":
					if(k.has("param2"))
						select_max(k.get("param1").toString(),k.get("param2").toString());
					else
						select_max(k.get("param1").toString());
					break;
				case "select_min":
					if(k.has("param2"))
						select_min(k.get("param1").toString(),k.get("param2").toString());
					else
						select_min(k.get("param1").toString());
					break;
				case "select_sum":
					if(k.has("param2"))
						select_sum(k.get("param1").toString(),k.get("param2").toString());
					else
						select_sum(k.get("param1").toString());
					break;
				case "select_count":
					if(k.has("param2"))
						select_count(k.get("param1").toString(),k.get("param2").toString());
					else
						select_count(k.get("param1").toString());
					break;
				case "from":
					from(k.get("param1").toString());
					break;
				case "join":
					if(k.has("param3"))
						join(k.get("param1").toString(),k.get("param2").toString(),k.get("param3").toString());
					else if(k.has("param2"))
						join(k.get("param1").toString(),k.get("param2").toString());
					else 
						join(k.get("param1").toString());
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
				case "where_not_in":
					where_not_in(where,k.get("param1").toString(),k.get("param2").toString());
					where = false;
					break;
				case "or_where_not_in":
					where_not_in(where,k.get("param1").toString(),k.get("param2").toString());
					where = false;
					break;
				case "like":
					if(k.has("param3"))
						like(like,k.get("param1").toString(),k.get("param2").toString(),k.get("param3").toString());	
					else
						like(like,k.get("param1").toString(),k.get("param2").toString());
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
					if(k.has("param2"))
						limit(Integer.parseInt(k.get("param1").toString()), Integer.parseInt(k.get("param2").toString()));
					else
						limit(Integer.parseInt(k.get("param1").toString()));
					break;
				case "insert":
					insert(k.get("param1").toString(), k.get("param2").toString(), k.get("param3").toString());
					break;
				case "delete":
					delete(k.get("param1").toString());
					break;
				case "max":
					max(k.get("param1").toString());
					break;
				case "set":
					set(k.get("param1").toString());
					break;
				}
			}			
		}else{
			System.out.println("this code is param");
			for(int i=0;i<m.length();i++){
				JSONObject k = m.getJSONObject(i);	
				String findString = k.get("function").toString();
				System.out.println(findString);
				if(findString.equals(""+Corder_by)){
					order_by(k.get("param1").toString(), k.get("param2").toString() );
				}
				else if(findString.equals(""+Cselect)){
					select(k.get("param1").toString());					
				}
				else if(findString.equals(""+Cselect_max)){
					if(k.has("param2"))
						select_max(k.get("param1").toString(),k.get("param2").toString());
					else
						select_max(k.get("param1").toString());
				}
				else if(findString.equals(""+Cselect_min)){
					if(k.has("param2"))
						select_min(k.get("param1").toString(),k.get("param2").toString());			
					else
						select_min(k.get("param1").toString());			
				}
				else if(findString.equals(""+Cselect_sum)){
					if(k.has("param2"))
						select_sum(k.get("param1").toString(),k.get("param2").toString());					
					else				
						select_sum(k.get("param1").toString());
				}
				else if(findString.equals(""+Cselect_count)){
					if(k.has("param2"))
						select_count(k.get("param1").toString(),k.get("param2").toString());					
					else				
						select_count(k.get("param1").toString());
				}
				else if(findString.equals(""+Cfrom)){
					from(k.get("param1").toString());
				}
				else if(findString.equals(""+Cjoin)){
					if(k.has("param3"))
						join(k.get("param1").toString(),k.get("param2").toString(),k.get("param3").toString());
					else if(k.has("param2"))
						join(k.get("param1").toString(),k.get("param2").toString());
					else 
						join(k.get("param1").toString());
					break;
				}
				else if(findString.equals(""+Cwhere)){
					where(where,k.get("param1").toString(),k.get("param2").toString());
					where = false;
				}
				else if(findString.equals(""+Cor_where)){
					or_where(where,k.get("param1").toString(),k.get("param2").toString());
					where = false;
				}
				else if(findString.equals(""+Cor_where_in)){
					or_where_in(where,k.get("param1").toString(),k.get("param2").toString());
					where = false;
				}
				else if(findString.equals(""+Cwhere_not_in)){
					where_not_in(where,k.get("param1").toString(),k.get("param2").toString());
					where = false;
				}
				else if(findString.equals(""+Cor_where_not_in)){
					or_where_not_in(where,k.get("param1").toString(),k.get("param2").toString());
					where = false;
				}
				else if(findString.equals(""+Clike)){
					if(k.has("param3"))
						like(like,k.get("param1").toString(),k.get("param2").toString(),k.get("param3").toString());	
					else
						like(like,k.get("param1").toString(),k.get("param2").toString());
				}
				else if(findString.equals(""+Cor_like)){
					or_like(like,k.get("param1").toString(),k.get("param2").toString(),k.get("param3").toString());
				}
				else if(findString.equals(""+Cnot_like)){
					not_like(like,k.get("param1").toString(),k.get("param2").toString(),k.get("param3").toString());
				}
				else if(findString.equals(""+Cor_not_like)){
					or_not_like(like,k.get("param1").toString(),k.get("param2").toString(),k.get("param3").toString());
				}
				else if(findString.equals(""+Cgroup_by)){
					group_by(k.get("param1").toString());
				}
				else if(findString.equals(""+Cdistinct)){
					distinct();
				}
				else if(findString.equals(""+Chaving)){
					having(k.get("param1").toString(), Boolean.parseBoolean(k.get("param2").toString().trim()) );					
				}
				else if(findString.equals(""+Cor_having)){
					or_having(k.get("param1").toString(), Boolean.parseBoolean(k.get("param2").toString().trim()) );					
				}
				else if(findString.equals(""+Climit)){
					if(k.has("param2"))
						limit(Integer.parseInt(k.get("param1").toString()), Integer.parseInt(k.get("param2").toString()));
					else
						limit(Integer.parseInt(k.get("param1").toString()));
				}
				else if(findString.equals(""+Cinsert)){
					insert(k.get("param1").toString(), k.get("param2").toString(), k.get("param3").toString());
				}
				else if(findString.equals(""+Cdelete)){
					delete(k.get("param1").toString());
				}
				else if(findString.equals(""+Cmax)){
					max(k.get("param1").toString());
				}
				else if(findString.equals(""+Cset)){
					set(k.get("param1").toString());
				}
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
		return this;
	}

	private CoconutzDataBase select_max( String argu ){
		String result = "SELECT MAX("+argu+") " + query;
		query.append(result);
		return this;
	}
	private CoconutzDataBase select_max( String argu, String as ){
		String result = "SELECT MAX("+argu+") as" + as +" " + query;		
		query.append(result);
		return this;
	}
	private CoconutzDataBase select_min( String argu ){
		String result = "SELECT MIN("+argu+") " + query;;
		query.append(result);
		return this;
	}
	private CoconutzDataBase select_min( String argu, String as ){
		String result = "SELECT MIN("+argu+") as" + as +" " + query;
		query.append(result);
		return this;
	}
	private CoconutzDataBase select_sum( String argu ){
		String result = "SELECT SUM("+argu+") " + query;
		query.append(result);
		return this;
	}
	private CoconutzDataBase select_sum( String argu ,String as){
		String result ="SELECT SUM("+argu+") as" + as +" " + query; 
		query.append(result);
		return this;
	}
	private CoconutzDataBase select_count( String argu ){
		String result ="SELECT COUNT("+argu+") " + query; 
		query.append(result);
		return this;
		
	}
	private CoconutzDataBase select_count( String argu, String as ){
		String result ="SELECT COUNT("+argu+") as" + as +" " + query; 
		query.append(result);
		return this;		
	}
	/*--from--*/
	private CoconutzDataBase from( String argu ){
		String result = "FROM " + argu +" ";
		query.append(result);
		return this;
	}
	/*--join--*/
	private CoconutzDataBase join( String argu ){
		String result = "JOIN " + argu +" ";
		query.append(result);
		return this;
	}	
	private CoconutzDataBase join( String argu, String as ){
		String result = "JOIN " + argu +" ON "+ as +" ";
		query.append(result);
		return this;
	}
	private CoconutzDataBase join( String argu, String as, String option ){
		String result = option +" JOIN " + argu +" ON "+ as +" ";;
		query.append(result);
		return this;
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
		return this;
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
		return this;
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
		return this;
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
		return this;
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
		return this;
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
		return this;
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
					result +=  argu[i]+" LIKE '%"+value[i] +"' ";
				else if( option.equals("after") )
					result += argu[i]+" LIKE '" + value[i]+"%' ";
				else if( option.equals("both") )
					result += argu[i]+" LIKE '%"+ value[i] + "%' ";		
				else
					result += argu[i]+" LIKE '" + value[i] +"' ";
			}else{
				if( option.equals("before") )
					result += argu[i]+" LIKE '%"+value[i] + "' AND ";
				else if( option.equals("after") )
					result += argu[i]+" LIKE '" + value[i]+"%'" + " AND ";
				else if( option.equals("both") )
					result += argu[i]+" LIKE '%"+ value[i] + "%'" + " AND ";		
				else
					result += argu[i]+" LIKE '" + value[i] + "' AND ";			
			}
		}
		query.append(result);
		return this;
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
				result += argu[i] +" = ' LIKE '%" + value[i] + "%' ' OR ";
			else
				result += argu[i] +" = ' LIKE '%" + value[i] + "%' ' ";				
		}
		query.append(result);
		return this;
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
					result +=  argu[i]+"LIKE '%"+value[i] +"' ";
				else if( option.equals("after") )
					result += argu[i]+"LIKE '" + value[i]+"%' ";
				else if( option.equals("both") )
					result += argu[i]+"LIKE '%"+ value[i] + "%' ";		
				else
					result += argu[i]+"LIKE '" + value[i] +"' ";
			}else{
				if( option.equals("before") )
					result += argu[i]+"LIKE '%"+value[i] + "' OR ";
				else if( option.equals("after") )
					result += argu[i]+"LIKE '" + value[i]+"%' OR ";
				else if( option.equals("both") )
					result += argu[i]+"LIKE '%"+ value[i] + "%' OR ";		
				else
					result += argu[i]+"LIKE '" + value[i] + "' OR ";			
			}
		}
		query.append(result);
		return this;
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
				result += argu[i] +" NOT LIKE '%" + value[i] + "% ' AND ";
			else
				result += argu[i] +" NOT LIKE '%" + value[i] + "% ' ";				
		}
		query.append(result);
		return this;
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
					result +=  argu[i]+" NOT LIKE '%"+value[i] +"' ";
				else if( option.equals("after") )
					result += argu[i]+" NOT LIKE '" + value[i]+"%' ";
				else if( option.equals("both") )
					result += argu[i]+" NOT LIKE '%"+ value[i] + "%' ";		
				else
					result += argu[i]+" NOT LIKE '" + value[i] +"' ";
			}else{
				if( option.equals("before") )
					result += argu[i]+" NOT LIKE '%"+value[i] + "' AND ";
				else if( option.equals("after") )
					result += argu[i]+" NOT LIKE '" + value[i]+"%' AND ";
				else if( option.equals("both") )
					result += argu[i]+" NOT LIKE '%"+ value[i] + "%' AND ";		
				else
					result += argu[i]+" NOT LIKE '" + value[i] + "' AND ";			
			}
		}
		query.append(result);
		return this;
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
				result += argu[i] +"  NOT LIKE '%" + value[i] + "%' AND ";
			else
				result += argu[i] +"  NOT LIKE '%" + value[i] + "%' ";				
		}
		query.append(result);
		return this;
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
					result +=  argu[i]+" NOT LIKE '%"+value[i] +"' ";
				else if( option.equals("after") )
					result += argu[i]+" NOT LIKE '" + value[i]+"%' ";
				else if( option.equals("both") )
					result += argu[i]+" NOT LIKE '%"+ value[i] + "%' ";		
				else
					result += argu[i]+" NOT LIKE '" + value[i] +"' ";
			}else{
				if( option.equals("before") )
					result += argu[i]+" NOT LIKE '%"+value[i] + "' OR ";
				else if( option.equals("after") )
					result += argu[i]+" NOT LIKE '" + value[i]+"%' OR ";
				else if( option.equals("both") )
					result += argu[i]+" NOT LIKE '%"+ value[i] + "%' OR ";		
				else
					result += argu[i]+" NOT LIKE '" + value[i] + "' OR ";			
			}
		}
		query.append(result);
		return this;
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
		return this;
	}
	
	/*--max--*/
	private CoconutzDataBase max( String argu ){
		String result = "MAX(" +argu+ ")";
		query.append(result);
		return this;
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
		return this;
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
		return this;
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
		return this;
	}

	private CoconutzDataBase limit(int limit){
		String result = "LIMIT "+limit+" ";
		query.append(result);
		return this;
	}
	private CoconutzDataBase limit(int limit, int offset){
		String result = "LIMIT "+limit+" "+offset+" ";
		query.append(result);
		return this;
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
	private void delete( String table ){
		String result = "DELETE FROM " + table +" ";
		query.append(result);
	}

	/**coconut ci - insert**/
	private void set( String argu ){
		String result = "SET "+argu+" ";
		query.append(result);
	}
	private void update( String table ){
		String result = "UPDATE "+table+" ";
		query.append(result);
	}

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
