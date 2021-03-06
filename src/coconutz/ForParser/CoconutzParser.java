package coconutz.ForParser;
import java.util.Hashtable;
/*********************************************************************************************
* @brief  함수를 파씽하고 그 결과를 반환하는 클래스
* @file  CoconutzParser.java
* @author  CHOIUNGYEOP
* @date  2013. 5.
*********************************************************************************************/
public class CoconutzParser {
	CoconutzFuncInfo parser = new CoconutzFuncInfo();
	/*******************************************************************************************************
	 * @brief 파일 이름을 적으면 파일을 분석한다.
	 * @method parseString
	 * @file CoconutzParser.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public boolean parseString(String file){
		try {
			return new FuncParser().ReadLine(parser,file);
		} catch (Exception e){
			e.printStackTrace();
			return false;			
		}
	}
	/*******************************************************************************************************
	 * @brief 파싱된 결과를 hashtable 형태로 반환한다.
	 * @method getResult
	 * @file CoconutzParser.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public Hashtable<String, String> getResult(){
		return parser.getFuncInfo();
	}

}
