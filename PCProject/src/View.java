import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

public class View extends JFrame implements ActionListener{

	JDesktopPane desk = new JDesktopPane();
	Calculator2 cal = new Calculator2();
	Situation st = new Situation();
	Thread tst = new Thread(st);
	DigitalClock2 clock = new DigitalClock2();
	Thread tclock = new Thread(clock);
	AllDAO dao = new AllDAO();
	MessageThread mt = new MessageThread(st.ta);
	
	public View() {
		super("비트캠프PC방");
//		setTitle("비트캠프PC방");

		add(desk, "Center");
		JInternalFrame if1 = new JInternalFrame("계산기", false, false, true, true);
			if1.add(cal);
			if1.setSize(new Dimension(400, 300));
			if1.setVisible(true);
			desk.add(if1);
		
		JInternalFrame if2 = new JInternalFrame("비트캠프PC방");
			tst.start();
			if2.add(st);
			if2.setBounds(1095, 0, 400, 600);
			if2.setVisible(true);
			desk.add(if2);

		JInternalFrame if3 = new JInternalFrame();
			tclock.start();
			if3.add(clock);
			if3.setBounds(1235, 693, 260, 80);
			if3.setVisible(true);
			desk.add(if3);
		
		setSize(1500, 800);
		setVisible(true);
		setLocationRelativeTo(null);	// 화면 가운데에서 창이 나옴
		setResizable(false);	// 정해진 사이즈에서 변경불가
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		st.exitbtn.addActionListener(this);

		Thread t = new Thread(mt);
		t.start();
	}

	MulticastSocket ms = null;
	DatagramPacket dp = null;
	InetAddress ia = null;
	InetAddress ip = null;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == st.exitbtn) {
			tst.stop();
			Login.exitvo.setResttime(st.time);
			dao.userUpdate(Login.exitvo);
			
			try {
				ms = new MulticastSocket();
				ip = InetAddress.getLocalHost();
				String str = ip + "/" + "null" + "/";
				str = str.substring(str.indexOf("/") + 1, str.length());
				ia = InetAddress.getByName("230.1.2.3");
				dp = new DatagramPacket(str.getBytes(), str.getBytes().length, ia, 15000);
//				보내기
				ms.send(dp);
				ms.close();
			}catch(Exception ee) {
				ee.printStackTrace();
			}
			
			st.ms.close();
			dispose();
		}
	}

}
