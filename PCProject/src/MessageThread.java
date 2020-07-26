import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.swing.JTextArea;

public class MessageThread extends Situation implements Runnable{

	JTextArea ta;

	MulticastSocket ms = null;
	DatagramPacket dp = null;
	InetAddress ia = null;
	InetAddress ip = null;
	
	public MessageThread() {
	}
	
	public MessageThread(JTextArea ta) {
		this.ta = ta;
	}
	
	public void run() {
		try {
//			System.out.println(1);
			ms = new MulticastSocket(12312);
			ia = InetAddress.getByName("230.1.3.2");
//			ia와 ms가 조인하여 통신한다
			ms.joinGroup(ia);
			while(true) {
//				데이터 Packet에 얻어오기
				byte data[] = new byte[512];
				dp = new DatagramPacket(data, data.length);
				ms.receive(dp);
//				전송받은 경우
				byte receive[] = dp.getData();
				int cnt = dp.getLength();
//				받은 데이터 String으로 바꾸기
				String c_data = new String(receive, 0, cnt);
				if(!c_data.equals(null)) {
					ta.append("관리자 : " + c_data + "\n");
					ta.setCaretPosition(ta.getText().length());
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
