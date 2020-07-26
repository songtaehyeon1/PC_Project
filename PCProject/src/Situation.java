import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Situation extends JPanel implements Runnable, ActionListener{

	JPanel pan = new JPanel(new BorderLayout());
		JPanel pan1 = new JPanel(new BorderLayout());
			JPanel infopan = new JPanel(new GridLayout(2, 2));
				JLabel ilbl = new JLabel(" 아이디 : ");
				JLabel idlbl = new JLabel();
				JLabel relbl = new JLabel(" 남은 시간 : ");
				JLabel restlbl = new JLabel();
			JButton exitbtn = new JButton("사용종료");
		JPanel pan2 = new JPanel(new BorderLayout());
			JTextArea ta = new JTextArea();
				JScrollPane sb = new JScrollPane(ta);
			JPanel pan2_2 = new JPanel(new BorderLayout());
				JTextField tf = new JTextField();
				JButton messagebtn = new JButton("메시지 보내기");

	MulticastSocket ms = null;
	DatagramPacket dp = null;
	InetAddress ia = null;
	InetAddress ip = null;
				
	AllDAO dao = new AllDAO();
	static String user[] = new String[2];
	ExitInfoVO exitvo = new ExitInfoVO();
//	MessageThread mt = new MessageThread();
	
	public Situation() {
		setLayout(new BorderLayout());
		infopan.add(ilbl);
		infopan.add(idlbl);
		infopan.add(relbl);
		infopan.add(restlbl);
		pan1.add(infopan);
		pan1.add(exitbtn, "East");
		pan2.add(sb, "Center");
		ta.setBackground(new Color(0, 0, 0));
		pan2_2.add(tf);
		pan2_2.add(messagebtn, "East");
		pan2.add(pan2_2, "South");
		pan.add(pan1, "North");
		pan.add(pan2);
		add(pan);

		try {
			ms = new MulticastSocket();
			ip = InetAddress.getLocalHost();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ta.setEnabled(false);
		setVisible(true);
		
		tf.addActionListener(this);
		messagebtn.addActionListener(this);

//		Thread t = new Thread(mt);
//		t.start();
	}
	
	int hour, min, sec;
	static int time;
	
	public void run() {
		idlbl.setText(idlbl.getText() + user[0]);
		time = Integer.parseInt(user[1]);
		while(true) {
			try {
				if(time == 0) {
					break;
				}
				time--;
				sec = time;
				min = sec / 60;
				hour = min / 60;
				sec = sec % 60;
				min = min % 60;
				restlbl.setText(hour + ":" + min + ":" + sec);
				Thread.sleep(1000);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		exitvo.setResttime(time);
		dao.userUpdate(exitvo);
		JOptionPane.showMessageDialog(this, "남은 시간이 없습니다.");
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == tf || obj == messagebtn) {
			try {
				String str = ip + "/" + Login.id + "/" + tf.getText();
				str = str.substring(str.indexOf("/") + 1, str.length());
				ia = InetAddress.getByName("230.1.2.3");
				dp = new DatagramPacket(str.getBytes(), str.getBytes().length, ia, 15000);
//				보내기
				ms.send(dp);
			}catch(Exception ee) {
				ee.printStackTrace();
			}
			ta.append(Login.id + " : " + tf.getText() + "\n");
			tf.setText("");
			ta.setCaretPosition(ta.getText().length());
		}
	}

}
