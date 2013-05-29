package com.coconutz;

import java.io.IOException;
import java.net.InetSocketAddress;

import Common.Option;
import DM.D_ConnectToSub;
import System.DSystemStatusReader;

import com.coconutz.Server.ServerHandler;
import com.coconutz.Service.CoconutzSetting;
import com.coconutz.Service.MyRunnableClass;
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
	/*******************************************************************************************************
	 * @brief 
	 * @method main
	 * @file CoconutzDaemon.java
	 * @author LEEJEONGSUB
	 * @date  2013. 4. 11.
	 * @param args[0] - user id
	 * 			args[1] - service name
	 * 			args[2] - daemon name
	 * 			args[3] - port
	 * @throws IOException
	 *******************************************************************************************************/
	 
	public static void main(String[] args) throws IOException {
		
		Option.setOption();
		
		int port;
		
		if (args.length == 0) {
			port = 8000;
		}
		port = Integer.parseInt(Option.op_set.get("D_PORT"));
		CoconutzSetting.url = "http://" + DSystemStatusReader.getIpAddress() + ":" + port + "/echo";
		System.out.println("port!!!!!! : " + port);
		
		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
		// Sub�Ŵ��� �����Ѵ�.
		D_ConnectToSub dct = new D_ConnectToSub(server);
		dct.start();
		

		CoconutzSetting.USERID = (Option.op_set.get("USER_NAME"));
		CoconutzSetting.SERVICENAME = (Option.op_set.get("SERVICE_NAME"));
		CoconutzSetting.DAEMONNAME = (Option.op_set.get("DAEMON_NAME"));
		
		
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
