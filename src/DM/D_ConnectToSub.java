package DM;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Common.Option;
import DM.Packet.DM_ClientInfo;
import DM.Packet.DM_ClientMessage;
import System.DSystemStatusReader;

import com.sun.net.httpserver.HttpServer;

public class D_ConnectToSub extends Thread {

	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Socket socket;
	private boolean isRunning = true;
	private HttpServer server;

	public D_ConnectToSub(HttpServer server) {
		this.server = server;
	}

	public void run() {
		try {
			socket = new Socket("localhost", Integer.parseInt(Option.op_set
					.get("DM_PORT")));
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());

			while (isRunning) {
				oos.writeObject(makeDMClientInfo());
				oos.flush();

				Object income = ois.readObject();
				if (income != null) {
					String line = income.getClass().toString();
					if (line == "class DaemonServer.Packet.DM_ClientMessage") {
						DM_ClientMessage dcm = (DM_ClientMessage) income;
						if(dcm.msg_type == DM_ClientMessage.KILL_ME){
							isRunning = false;
							server.stop(0);
						}
						//Message의 종류에 따라 동작하는 부분.
					}
				}
				Thread.sleep(4000);
			}

			
			destoryConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void destoryConnection() {
		try {
			ois.close();
			oos.close();
			socket.close();
		} catch (Exception e) {
			ois = null;
			oos = null;
			socket = null;
		}
		ois = null;
		oos = null;
		socket = null;
	}

	public void sendLogToSub(String log) {
		try {
			DM_ClientMessage dlog = new DM_ClientMessage();
			dlog.log = log;
			dlog.msg_type = DM_ClientMessage.D_LOG;
			dlog.timestamp = DSystemStatusReader.getTimeStamp();
			oos.writeObject(dlog);    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DM_ClientInfo makeDMClientInfo() {
		DM_ClientInfo result = null;
		result = new DM_ClientInfo();

		result.dm_mem_ratio = DSystemStatusReader.memUsage();
		result.dm_cpu_ratio = DSystemStatusReader.cpuUsage();
		result.dm_ip = DSystemStatusReader.getIpAddress();
		result.dm_service_name = Option.op_set.get("SERVICE_NAME");
		result.dm_daemon_name = Option.op_set.get("DAEMON_NAME");
		result.dm_port = Option.op_set.get("D_PORT");
		result.dm_timestamp = DSystemStatusReader.getTimeStamp();
		result.is_Healthy = true;
		
		System.out.println("--------making Daemon infomation");
		System.out.println("ip:"+result.dm_ip);
		System.out.println("time:"+result.dm_timestamp);
		System.out.println("port:"+result.dm_port);;
		System.out.println("cpu_ratio:"+result.dm_cpu_ratio);
		System.out.println("mem_ratio:"+result.dm_mem_ratio);
		System.out.println("---------------------------------");

		return result;
	}
}
