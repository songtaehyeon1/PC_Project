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
		JLabel idlbl = new JLabel("���̵�");
		JLabel pwlbl = new JLabel("��й�ȣ");
		JTextField idtxt = new JTextField("master");
		JTextField pwtxt = new JTextField("1234");
		JButton loginbtn = new JButton("�α���");
		JButton joinbtn = new JButton("ȸ�� ����");
		JButton addtimebtn = new JButton("�ð� �߰�");

	AllDAO dao = new AllDAO();
	Join join = new Join(this);
	Addtime addtime = new Addtime(this);
	static ExitInfoVO exitvo = new ExitInfoVO();
	
	static String id = null;
	
	public Login() {
		super("��Ʈķ�� PC��");
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
		setLocationRelativeTo(null);	// ȭ�� ������� â�� ����
		setResizable(false);	// ������ ������� ����Ұ�
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
//					������
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
				JOptionPane.showMessageDialog(this, "�´� ���̵� �Ǵ� ��й�ȣ�� �Է��� �ּ���.");
			}
		}else if(obj == joinbtn) {
			join.setVisible(true);
		}else if(obj == addtimebtn) {
			addtime.setVisible(true);
		}
	}


}
