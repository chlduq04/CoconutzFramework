package com.coconutz.Service;
/*********************************************************************************************
* @brief  사용자의  서버 세팅을 조절하는 클래스
* @file  CoconutzSetting.java
* @author  CHOIUNGYEOP
* @date  2013. 5.
*********************************************************************************************/
public class CoconutzSetting {
	/*
	 * coconut의 database를 설정한다.
	 * url : 서버의 주소
	 * hostname : 데이터베이스의 호스트 이름
	 * username : 데이터베이스의 아이디
	 * password : 데이터베이스의 비밀번호
	 * database : 데이터베이스의 DB이름
	 * */
	public static String url = "http://211.189.127.244:8000/echo";
	public static String usernames = "coconutz";
	public static String hostname = "localhost";
	public static String username = "root";
	public static String password = "coconut";
	public static String database = "coconut";	

	public static String USERID = "";
	public static String SERVICENAME= "";
	public static String DAEMONNAME = "";
	/*
	 * coconut의 query를 숨긴다.
	 * 각 이름에 맞는 숫자를 서로 다르게 적으면 그 숫자에 맞게 js가 바뀐다.
	 * hideCode를 false로 하면 쿼리가 그대로 보이고 true로 하면 쿼리가 숨겨진다.
	 * 
	 * */
	protected boolean hideCode = false;	
	protected int Cselect = 0;//"select"
	protected int Cselect_max = 1;//"select_max"
	protected int Cselect_min = 2;//"select_min"
	protected int Cselect_sum = 3;//"select_sum"
	protected int Cfrom = 4;//"from"
	protected int Cjoin = 5;//"join"
	protected int Cwhere = 6;//"where"	
	protected int Cor_where = 7;//"or_where"
	protected int Cor_where_in = 8;//"or_where_in"
	protected int Cwhere_not_in = 9;//"wehre_not_in"
	protected int Cor_where_not_in = 10;//"or_where_not_in"
	protected int Clike = 11;//"like"
	protected int Cor_like = 12;//"or_like"
	protected int Cnot_like = 13;//"not_like"
	protected int Cor_not_like = 14;//"or_not_like"
	protected int Cgroup_by = 15;//"group_by"
	protected int Cdistinct = 16;//"distinct"
	protected int Chaving = 17;//"having"
	protected int Cor_having = 18;//"or_having"
	protected int Corder_by = 19;//"order_by"
	protected int Climit = 20;//"limit"
	protected int Cinsert = 21;//"insert"
	protected int Cdelete = 22;//"delete"
	protected int Cmax = 23;//"max"
	protected int Cset = 24;//"set"
	protected int Cupdate = 25;//"update"
	protected int Cselect_count = 26;//cselect_count
	
}
