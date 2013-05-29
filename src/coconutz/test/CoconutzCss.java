package coconutz.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class CoconutzCss {
	public CoconutzCss(String classname, String jsname, Vector<CoconutzCssType> values){
		try {
			/*------------------- output -------------------*/
			BufferedWriter out = new BufferedWriter(new FileWriter(classname+"_"+jsname+".css"));
			String s = "";
			for( int i=0 ; i < values.size() ; i++ ){
				if(values.elementAt(i).type.equals("id")){
					s += "";
				}else{
					s += "";					
				}
			}
			out.write(s); out.newLine();
			out.close();
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}		
	}
}