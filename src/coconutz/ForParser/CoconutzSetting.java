package coconutz.ForParser;

public class CoconutzSetting {
	/**/
	public String url = "http://211.189.127.244:8000/echo";
	/**/
	public String usernames = "coconutz";
	/**/
	public String hostname = "localhost";
	/**/
	public String username = "root";
	/**/
	public String password = "coconut";
	/**/
	public String database = "coconut";	
	
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
}
