package coconutz.ForParser;

import java.util.Hashtable;

public class CoconutzFuncInfo {
	private Hashtable<String, String> funcName;
	String childMethod;
	String parentMethod;
	CoconutzFuncInfo(){	
		funcName = new Hashtable<>();
	}
	public void FuncName(String argu){
		childMethod = argu;
	}
	public void FuncParam(String argu){
		parentMethod = argu;
		funcName.put(childMethod, parentMethod);
	}
	public Hashtable<String, String> getFuncInfo(){
		return funcName;
	}
	public void resetFunc(){
		funcName.clear();
	}
}
