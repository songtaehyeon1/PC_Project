import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {

	JPanel pan = new JPanel(null);
		JLabel idlbl = new JLabel("아이디");
		JLabel pwlbl = new JLabel("비밀번호");
		JTextField idtxt = new JTextField("master");
		JTextField pwtxt = new JTextField("1234");
		JButton loginbtn = new JButton("로그인");
		JButton joinbtn = new JButton("회원 가입");
		JButton addtimebtn = new JButton("시간 추가");

	AllDAO dao = new AllDAO();
	Join join = new Join(this);
	Addtime addtime = new Addtime(this);
	static ExitInfoVO exitvo = new ExitInfoVO();
	
	static String id = null;
	
	public Login() {
		super("비트캠프 PC방");
		idlbl.setBounds(80, 50, 50, 30);
		idtxt.setBounds(145, 50, 150, 30);
		pwlbl.setBounds(80, 100, 80, 30);
		pwtxt.setBounds(145, 100, 150, 30);
		loginbtn.setBounds(320, 50, 100, 80);
		joinbtn.setBounds(70, 150, 175, 50);
		addtimebtn.setBounds(260, 150, 175, 50);
		
		add(idlbl);
		add(idtxt);
		add(pwlbl);
		add(pwtxt);
		add(loginbtn);
		add(joinbtn);
		add(addtimebtn);
		add(pan);
		
		setSize(500, 300);
		setVisible(true);
		setLocationRelativeTo(null);	// 화면 가운데에서 창이 나옴
		setResizable(false);	// 정해진 사이즈에서 변경불가
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		joinbtn.addActionListener(this);
		loginbtn.addActionListener(this);
		idtxt.addActionListener(this);
		pwtxt.addActionListener(this);
		addtimebtn.addActionListener(this);
		
//		mv.thread[0].start();
//		mv.manager_t.start();
	}

	MulticastSocket ms = null;
	DatagramPacket dp = null;
	InetAddress ia = null;
	InetAddress ip = null;
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		boolean check = dao.loginSelect(idtxt.getText(), pwtxt.getText());
		if(obj == idtxt || obj == pwtxt || obj == loginbtn) {
			if(idtxt.getText().equals("master") && pwtxt.getText().equals("1234")) {
				new ManagerView();
			}else if(check){
				try {
					id = idtxt.getText();
//					new View();
					Situation.user = dao.userSelect();
					dao.exitvo(exitvo);
					idtxt.setText("");
					pwtxt.setText("");
					
					ms = new MulticastSocket();
					ip = InetAddress.getLocalHost();
					String str = ip + "/" + id + "/";
					str = str.substring(str.indexOf("/") + 1, str.length());
					ia = InetAddress.getByName("230.1.2.3");
					dp = new DatagramPacket(str.getBytes(), str.getBytes().length, ia, 15000);
//					보내기
					ms.send(dp);
					ms.close();
					
					new View();
//					cs.startConnection();

				}catch (Exception e11) {
					e11.printStackTrace();
				}finally {
					dispose();
				}
			}else {
				JOptionPane.showMessageDialog(this, "맞는 아이디 또는 비밀번호를 입력해 주세요.");
			}
		}else if(obj == joinbtn) {
			join.setVisible(true);
		}else if(obj == addtimebtn) {
			addtime.setVisible(true);
		}
	}


}
