package coconutz.ForParser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class CoconutzPackage {
	public void Forder(String path,String name){
		File dirFile=new File(path);
		File []fileList=dirFile.listFiles();
		for(File tempFile : fileList) {
		  if(tempFile.isFile()) {
		    String tempFileName=tempFile.getName();
			ReadLine(path+tempFileName, name);
		  }
		}
	}
	public boolean ReadLine(String args,String packagename)
	{
		BufferedReader in;
		boolean read = false;	
		StringBuffer other;
		
		if (args.length() == 0) 
		{                   
			System.err.println("Input Filename not found");
			return false;
		}
		try {
			other = new StringBuffer();
			in = new BufferedReader(new FileReader(args));
	
			String s;
			while ((s = in.readLine()) != null) 
			{
				if(s.matches(".*package.*")){
					other.append("package "+packagename+";\r\n");
					read = true;
				}else if(read){
					other.append(s);
				}
			}
			in.close();
			write(args,other);
			return true;
		} 
		catch (IOException e) 
		{
			System.err.println(e); // 에러가 있다면 메시지 출력
			return false;
		}
	}
	public boolean write(String name, StringBuffer text){
		try {
			/*------------------- output -------------------*/
			BufferedWriter out = new BufferedWriter(new FileWriter(name));
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
}
