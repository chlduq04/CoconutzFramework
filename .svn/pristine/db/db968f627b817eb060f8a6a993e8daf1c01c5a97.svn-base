package com.coconutz;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.coconutz.Server.ServerHandler;
import com.newService.MyRunnableClass;
import com.sun.net.httpserver.HttpServer;



/*********************************************************************************************
* @brief  CoconutzDaemon을 실행시켜준다.
* @file  CoconutzDaemon.java
* @author  LEEJEONGSUB
* @date  2013. 4. 11.
*********************************************************************************************/

/*******************************************************************************************************
 * @brief zxcv
 * @method main
 * @file CoconutzDaemon.java
 * @author LEEJEONGSUB
 * @date  2013. 4. 11.
 * @param args
 * @throws IOException
 *******************************************************************************************************/
 



 /*********************************************************************************************
 * @brief  CoconutzDaemon을 실행시켜준다.
 * @file  CoconutzDaemon.java
 * @author  LEEJEONGSUB
 * @date  2013. 4. 11.
 *********************************************************************************************/
public class CoconutzDaemon {
	static int port = 8000;

	
	/*******************************************************************************************************
	 * @brief 
	 * @method main
	 * @file CoconutzDaemon.java
	 * @author LEEJEONGSUB
	 * @date  2013. 4. 11.
	 * @param args
	 * @throws IOException
	 *******************************************************************************************************/
	 
	public static void main(String[] args) throws IOException {
		if (args.length > 1) {
			port = Integer.parseInt(args[0]);
		}
		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
		server.createContext("/echo", new ServerHandler());
		server.start();
        System.out.println("daemon starting on port " + port);
        
        CoconutzRunnableClass coconutzRun = new MyRunnableClass();
//        captcha aaa = new test();
//        Object obj = aaa;
//        
//        System.out.println(obj.getClass());
	}
}
