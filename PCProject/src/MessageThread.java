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
//			ia�� ms�� �����Ͽ� ����Ѵ�
			ms.joinGroup(ia);
			while(true) {
//				������ Packet�� ������
				byte data[] = new byte[512];
				dp = new DatagramPacket(data, data.length);
				ms.receive(dp);
//				���۹��� ���
				byte receive[] = dp.getData();
				int cnt = dp.getLength();
//				���� ������ String���� �ٲٱ�
				String c_data = new String(receive, 0, cnt);
				if(!c_data.equals(null)) {
					ta.append("������ : " + c_data + "\n");
					ta.setCaretPosition(ta.getText().length());
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
