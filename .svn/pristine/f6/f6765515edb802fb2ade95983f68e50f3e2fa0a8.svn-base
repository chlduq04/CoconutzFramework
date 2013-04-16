package coconutz.ForParser;
import java.util.Hashtable;



public class CoconutzParser {
	CoconutzFuncInfo parser = new CoconutzFuncInfo();
	public boolean parseString(String file){
		try {
			return new CoconutzFuncParser().ReadLine(parser,file);
		} catch (Exception e){
			e.printStackTrace();
			return false;			
		}
	}
	public Hashtable<String, String> getResult(){
		return parser.getFuncInfo();
	}

}
