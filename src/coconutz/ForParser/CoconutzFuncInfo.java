package coconutz.ForParser;

import java.util.Hashtable;
/*********************************************************************************************
* @brief  Ajax를 상세하게 만드는 클래스
* @file  CoconutzFuncInfo.java
* @author  CHOIUNGYEOP
* @date  2013. 5.
*********************************************************************************************/
public class CoconutzFuncInfo {
	private Hashtable<String, String> funcName;
	String childMethod;
	String parentMethod;
	/*******************************************************************************************************
	 * @brief CoconutzFuncInfo의 생성자
	 * @method CoconutzFuncInfo
	 * @file CoconutzFuncInfo.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	CoconutzFuncInfo(){	
		funcName = new Hashtable<>();
	}
	/*******************************************************************************************************
	 * @brief childMethod에 값을 넣는다
	 * @method FuncName
	 * @file CoconutzFuncInfo.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public void FuncName(String argu){
		childMethod = argu;
	}
	/*******************************************************************************************************
	 * @brief parentMethod에 값을 넣고 funcName을 생성한다.
	 * @method FuncParam
	 * @file CoconutzFuncInfo.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public void FuncParam(String argu){
		parentMethod = argu; 
		funcName.put(childMethod, parentMethod);
	}
	/*******************************************************************************************************
	 * @brief funcName을 return 한다
	 * @method getFuncInfo
	 * @file CoconutzFuncInfo.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public Hashtable<String, String> getFuncInfo(){
		return funcName;
	}
	/*******************************************************************************************************
	 * @brief 함수에 대한 확인된 정보를 초기화한다
	 * @method resetFunc
	 * @file CoconutzFuncInfo.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public void resetFunc(){
		funcName.clear();
	}
}