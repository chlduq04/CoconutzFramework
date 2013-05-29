package coconutz.ForParser;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;
/*********************************************************************************************
* @brief  html을 해석하여 javascript를 자동으로 추가하고 id에 맞추어서 css를 생성해 주는 class
* @file  CoconutzHtmlParser.java
* @author  CHOIUNGYEOP
* @date  2013. 5.
*********************************************************************************************/
public class CoconutzHtmlParser {
	/*******************************************************************************************************
	 * @brief 파일의 이름과 script 이름을 읽어서 script를 추가해 주는 함수
	 * @method ReadLine
	 * @file CoconutzFuncInfo.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public boolean ReadLine(String args , StringBuffer scripts)
	{
		StringBuffer css, html, script;
		BufferedReader in;
		if (args.length() == 0) 
		{                   
			System.err.println("Input Filename not found");
			return false;
		}
		try {
			boolean find = false;
			script = new StringBuffer();
			html = new StringBuffer();
			css = new StringBuffer();
			in = new BufferedReader(new FileReader(args+".html"));
			String s;
			while ((s = in.readLine()) != null) 
			{
				if(find)
					script.append(s+"\r\n");
				else 
					html.append(s+"\r\n");
					
				if(s.matches(".*id.*")){
					String[] array;
					array = s.split("</div>");
					for( String argu : array ){
						String test1 = argu.trim().substring(argu.indexOf("id")+1);
						String test2 = test1.substring(test1.indexOf("\"")+1);
						css.append("#"+test1.substring(test1.indexOf("\"")+1, test2.indexOf("\"")+4)+"{\r\n}\r\n");
					}
				}else if(s.matches(".*class.*")){
					String[] array;
					array = s.split("</div>");
					for( String argu : array ){
						String test1 = argu.trim().substring(argu.indexOf("id")+1);
						String test2 = test1.substring(test1.indexOf("\"")+1);
						css.append("."+test1.substring(test1.indexOf("\"")+1, test2.indexOf("\"")+14)+"{\r\n}\r\n");
					}
				}else if(s.matches(".*</body>.*")){
					find = true;
				}
				
			}
			in.close();
			write(args,css);
			write(args,html.append(scripts.toString()+"\r\n"),script);
			return true;
		} 
		catch (IOException e) 
		{
			System.err.println(e); // 에러가 있다면 메시지 출력
			return false;
		}
	}
	/*******************************************************************************************************
	 * @brief id에 맞게 css를 생성해 준다.
	 * @method write
	 * @file CoconutzFuncInfo.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public boolean write(String name, StringBuffer text){
		try {
			/*------------------- output -------------------*/
			BufferedWriter out = new BufferedWriter(new FileWriter(name+".css"));
			out.write(text.toString()); 
			out.newLine();
			out.close();
			return true;
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
			return false;
		}		
	}
	/*******************************************************************************************************
	 * @brief "Coconutz_" 라는 prefix를 넣어서 html을 생성해 준다
	 * @method write
	 * @file CoconutzFuncInfo.java
	 * @author CHOIUNGYEOP
	 * @date  2013. 5.
	 * @param 
	 *******************************************************************************************************/
	public boolean write(String name, StringBuffer text1, StringBuffer text2){
		try {
			/*------------------- output -------------------*/
			BufferedWriter out = new BufferedWriter(new FileWriter("Coconutz_"+name+".html"));
			out.write(text1.toString()); 
			out.write(text2.toString()); 
			out.newLine();
			out.close();
			return true;
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
			return false;
		}		
	}
}
