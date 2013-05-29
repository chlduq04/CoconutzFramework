package Common;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;


public class Option {
	
	public static HashMap<String, String> op_set = new HashMap<String, String>();
	
	//SERVICE_NAME
	//DAEMON_NAME
	//DM_PORT
	
	public static void setOption() {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(
					"./config.d")));
			String str;
			while ((str = br.readLine()) != null) {
				String[] op = str.split(" ");
				Option.op_set.put(op[0], op[1]);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
