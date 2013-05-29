package coconutz.ForParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
/*********************************************************************************************
* @brief  사용자의 java를 분석하여 함수의 이름을 뽑는다.
* @file  CoconutzFuncParser.java
* @author  CHOIUNGYEOP
* @date  2013. 5.
*********************************************************************************************/
public class FuncParser {
	/*******************************************************************************************************
	 * @brief java파일을 읽어들여서 규칙에 맞게 파씽한다.
	 * @method ReadLine
	 * @file CoconutzFuncParser.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public boolean ReadLine(CoconutzFuncInfo parser, String args)
	{
		boolean func;
		BufferedReader in;
		if (args.length() == 0) 
		{                   
			System.err.println("Input Filename not found");
			return false;
		}
		try {
			func = false;
			in = new BufferedReader(new FileReader(""+args));
			String s;
			while ((s = in.readLine()) != null) 
			{
				if(func){
					String funcParam = "";
					if(s.matches(".*super.*")){
						int num = 0;
						while(num < s.length()){
							char at = s.charAt(num++);
							if( at=='(' ){
								break;
							}
							funcParam += at;
						}
						funcParam = funcParam.trim();
						StringTokenizer m = new StringTokenizer(funcParam, " .");
						while(m.hasMoreTokens()){
							funcParam = m.nextToken();
						}
						funcParam = funcParam.trim();
						func = false;
					}
					else if(s.matches(".*public.*")){
						func = false;
					}
					parser.FuncParam(funcParam);
				}
				if(!func && (s.matches(".*[)].*")&&!(s.matches(".*[.].*"))&&!(s.matches(".*[;].*"))&&s.matches(".*[_].*"))){
					String funcName = "";
					s = s.trim();
					int num = 0;
					while(num < s.length()){
						char at = s.charAt(num++);
						if( at=='(' )
							break;
						funcName += at;
					}
					StringTokenizer m = new StringTokenizer(funcName, " ");
					while(m.hasMoreTokens())
						funcName = m.nextToken();
					parser.FuncName(funcName.trim());				
					func = true;
				}
			}
			in.close();
			return true;
		} 
		catch (IOException e) 
		{
			System.err.println(e); // 에러가 있다면 메시지 출력
			return false;
		}
	}
}