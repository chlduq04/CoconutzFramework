package System;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;

public class DSystemStatusReader {
		
		public static String getTimeStamp() {
			String result = "";
			try {
				Date date = new Date();
				result = (new Timestamp(date.getTime()).toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		
		public static int cpuUsage() {
			int result = -1;
			
			return result;
		}
		
		public static int memUsage() {
			Runtime rt = Runtime.getRuntime();
			int maxMemory = (int)rt.maxMemory();
			int allocatedMemory = (int)rt.totalMemory();
			int freeMemory = (int)rt.freeMemory();
			
			return allocatedMemory;
		}
		
		public static String getIpAddress() {

			String ip = null;
			try {
				boolean isLoopBack = true;
				Enumeration<NetworkInterface> en = NetworkInterface
						.getNetworkInterfaces();
				while (en.hasMoreElements()) {
					NetworkInterface ni = en.nextElement();
					if (ni.isLoopback())
						continue;

					Enumeration<InetAddress> inetAddresses = ni.getInetAddresses();
					while (inetAddresses.hasMoreElements()) {
						InetAddress ia = inetAddresses.nextElement();
						if (ia.getHostAddress() != null
								&& ia.getHostAddress().indexOf(".") != -1) {
							ip = ia.getHostAddress();
							System.out.println(ip);
							isLoopBack = false;
							break;
						}
					}
					if (!isLoopBack)
						break;
				}
			} catch (SocketException e1) {
				e1.printStackTrace();
			}
			System.out.println(" IP= " + ip);
			return ip;
		}
}
