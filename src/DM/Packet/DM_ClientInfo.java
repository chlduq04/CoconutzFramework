package DM.Packet;
import java.io.Serializable;


public class DM_ClientInfo implements Serializable{
		//데몬의 정보
		public String dm_service_name =""; //데몬 서비스 이름
		public String dm_daemon_name ="";	// 데몬의 데몬 이름
		public String dm_user_id = "";
		public boolean is_killed  = false;  //실행여부
		public boolean is_Healthy = true;
		public String dm_timestamp = "";		//타임스템프
		public int dm_cpu_ratio = 0;		//프로세서 점유율
		public int dm_mem_ratio = 0; 		//메모리 점유율
		public String dm_ip = "";
		public String dm_port = "";			//할당된 포트번호
}
