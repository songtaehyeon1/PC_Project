import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import member.MemberMain;

public class ManagerView extends JFrame implements ActionListener, Runnable{

	JTabbedPane tp = new JTabbedPane();
		JPanel pan = new JPanel(new GridLayout(3, 5));
			JButton btn[] = new JButton[15];
			JPanel cenPan = new JPanel();
				JTextArea ta[] = new JTextArea[15];
				JPanel souPan = new JPanel();
					JTextField tf[] = new JTextField[15];
					JScrollPane sp[] = new JScrollPane[15];
					JButton mbtn[] = new JButton[15];
		MemberMain member = new MemberMain();
		JButton managerexitbtn = new JButton("종료");

	Thread manager_t = new Thread(this);
	MulticastSocket ms = null;
	DatagramPacket dp = null;
	InetAddress ia = null;
	
	AllDAO dao = new AllDAO();
	UserVO uservo = new UserVO();
	
	int i;
	
	public ManagerView() {
		super("관리자");
		//this.setTitle("관리자");
		for(i = 0; i < 15; i++) {
			btn[i] = new JButton(String.valueOf(i + 1) + "번");
			btn[i].setBackground(new Color(250, 237, 125));
			btn[i].setFont(new Font("", Font.BOLD, 15));
			btn[i].addActionListener(this);
			cenPan = new JPanel(new BorderLayout());
			ta[i] = new JTextArea();
			souPan = new JPanel(new BorderLayout());
			tf[i] = new JTextField();
			sp[i] = new JScrollPane(ta[i]);
			mbtn[i] = new JButton("보내기");
			cenPan.add(sp[i]);
			souPan.add(tf[i]);
			tf[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Object obj = e.getSource();
					int j = 0;
					try {
						ms = new MulticastSocket();
						for(j = 0; j < 15; j++) {
							if(tf[j] == obj) {
								break;
							}
						}
						String str = tf[j].getText();
						ia = InetAddress.getByName("230.1.3." + j);
						dp = new DatagramPacket(str.getBytes(), str.getBytes().length, ia, 12312);
//						보내기
						ms.send(dp);
						ms.close();
					}catch(Exception ee) {
						ee.printStackTrace();
					}
					ta[j].append("관리자 : " + tf[j].getText() + "\n");
					tf[j].setText("");
					ta[j].setCaretPosition(ta[j].getText().length());
				}
			});
			souPan.add(mbtn[i], "East");
			mbtn[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Object obj = e.getSource();
					int k = 0;
					try {
						ms = new MulticastSocket();
						for(k = 0; k < 15; k++) {
							if(mbtn[k] == obj) {
								break;
							}
						}
						String str = mbtn[k].getText();
						ia = InetAddress.getByName("230.1.3." + k);
						dp = new DatagramPacket(str.getBytes(), str.getBytes().length, ia, 15000);
//						보내기
						ms.send(dp);
						ms.close();
					}catch(Exception ee) {
						ee.printStackTrace();
					}
					ta[k].append("관리자 : " + tf[k].getText() + "\n");
					tf[k].setText("");
					ta[k].setCaretPosition(ta[k].getText().length());
				}
			});
			cenPan.add(souPan, "South");
			pan.add(btn[i]);
			pan.add(cenPan, "East");
		}
		
		tp.add("PC", pan);
		tp.add("회원관리", member);
		member.allRecordSelect();
		managerexitbtn.setFont(new Font("", Font.BOLD, 300));
		managerexitbtn.setBackground(new Color(255, 0, 0));
		tp.add("종료", managerexitbtn);

		add(tp);

		setSize(1500, 800);
		setVisible(true);
		setLocationRelativeTo(null);	// 화면 가운데에서 창이 나옴
		setResizable(false);	// 정해진 사이즈에서 변경불가
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		managerexitbtn.addActionListener(this);
		manager_t.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == managerexitbtn) {
			try {
				manager_t.stop();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			dispose();
		}
	}

//	PC들 정보랑 메시지
	int hour, min, sec;
	
	@Override
	public void run() {
		MulticastSocket ms = null;
		DatagramPacket dp = null;
		InetAddress ia = null;
		try {
			ms = new MulticastSocket(15000);
			ia = InetAddress.getByName("230.1.2.3");
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
//				System.out.println(c_data);
				String ip = c_data.substring(0, c_data.indexOf("/"));
				String userid = c_data.substring(c_data.indexOf("/") + 1, c_data.lastIndexOf("/"));
				String message = c_data.substring(c_data.lastIndexOf("/") + 1, c_data.length());
//				if(!Login.id.equals(null)) {
				if(!userid.equals("null")) {
					if(ip.equals("192.168.0.21")) {	//192.168.0.21
						btn[1].setBackground(new Color(178, 235, 244));
						dao.seatInfo(userid, uservo);
						sec = uservo.getRest();
						min = sec / 60;
						hour = min / 60;
						sec = sec % 60;
						min = min % 60;
						btn[1].setText("<html>" + "2번" + "<br>이름 : " + uservo.getName() + "<br>아이디 : " 
						+ uservo.getId() +"<br>남은 시간 :<br>" + hour + ":" + min + ":" + sec + "<br>잔액 : "
						+ uservo.getMoney() + "</html>");
						ta[1].append("2 : " + message + "\n");
//						ta커서이동
						ta[1].setCaretPosition(ta[1].getText().length());
					}else if(ip.equals("172.30.1.5")) {
						btn[2].setBackground(new Color(178, 235, 244));
						dao.seatInfo(userid, uservo);
						sec = uservo.getRest();
						min = sec / 60;
						hour = min / 60;
						sec = sec % 60;
						min = min % 60;
						btn[2].setText("<html>" + "3번" + "<br>이름 : " + uservo.getName() + "<br>아이디 : " 
						+ uservo.getId() +"<br>남은 시간 :<br>" + hour + ":" + min + ":" + sec + "<br>잔액 : "
						+ uservo.getMoney() + "</html>");
						ta[2].append("3 : " + message + "\n");
						ta[2].setCaretPosition(ta[2].getText().length());
					}else if(ip.equals("192.168.0.5")) {
						btn[0].setBackground(new Color(178, 235, 244));
						dao.seatInfo(userid, uservo);
						sec = uservo.getRest();
						min = sec / 60;
						hour = min / 60;
						sec = sec % 60;
						min = min % 60;
						btn[0].setText("<html>" + "1번" + "<br>이름 : " + uservo.getName() + "<br>아이디 : " 
						+ uservo.getId() +"<br>남은 시간 :<br>" + hour + ":" + min + ":" + sec + "<br>잔액 : "
						+ uservo.getMoney() + "</html>");
						ta[0].append("1 : " + message + "\n");
						ta[0].setCaretPosition(ta[0].getText().length());
					}
				}else {
					if(ip.equals("192.168.0.18")) {
						btn[1].setBackground(new Color(250, 237, 125));
						btn[1].setText("2번");
						ta[1].setText("");
					}else if(ip.equals("192.168.0.18")) {
						btn[2].setBackground(new Color(250, 237, 125));
						btn[2].setText("3번");
						ta[2].setText("");
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
