import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Addtime extends JDialog implements ActionListener{

	JPanel pan = new JPanel(new GridLayout(1, 2));
		JPanel leftpan = new JPanel(new GridLayout(3, 2));
			JButton btn1 = new JButton("1,000��    01:00");
			JButton btn2 = new JButton("2,000��    02:00");
			JButton btn4 = new JButton("4,000��    04:00");
			JButton btn8 = new JButton("7,000��    08:00");
			JButton btn16 = new JButton("14,000��    16:00");
			JButton btn32 = new JButton("26,000��    32:00");
		JPanel rightpan = new JPanel(new BorderLayout());
			JPanel rightpan_1 = new JPanel(new BorderLayout());
				JTextField infotf = new JTextField();
				JButton infobtn = new JButton("���̵� ã��");
			JTextArea infota = new JTextArea();
			JButton backbtn = new JButton("�ڷΰ���");
	
	AllDAO dao = new AllDAO();
	
	int hour, min, sec;
	boolean haveid = false;
			
	public Addtime() {}
	public Addtime(JFrame frame) {
		super(frame, "�ð� �߰�", true);
		
		leftpan.add(btn1);
		leftpan.add(btn2);
		leftpan.add(btn4);
		leftpan.add(btn8);
		leftpan.add(btn16);
		leftpan.add(btn32);
		btn1.setFont(new Font("", Font.PLAIN, 20));
		btn2.setFont(new Font("", Font.PLAIN, 20));
		btn4.setFont(new Font("", Font.PLAIN, 20));
		btn8.setFont(new Font("", Font.PLAIN, 20));
		btn16.setFont(new Font("", Font.PLAIN, 20));
		btn32.setFont(new Font("", Font.PLAIN, 20));
		rightpan_1.add(infotf);
		rightpan_1.add(infobtn, "East");
		rightpan.add(rightpan_1, "North");
		rightpan.add(infota);
		infota.setFont(new Font("", Font.PLAIN, 30));
		rightpan.add(backbtn, "South");
		pan.add(rightpan);
		pan.add(leftpan);
		add(pan);
		
		setSize(1000, 500);
		setLocationRelativeTo(null);	// ȭ�� ������� â�� ����
		setResizable(false);	// ������ ������� ����Ұ�
		
		infobtn.addActionListener(this);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn4.addActionListener(this);
		btn8.addActionListener(this);
		btn16.addActionListener(this);
		btn32.addActionListener(this);
		backbtn.addActionListener(this);
		infotf.addActionListener(this);
	}
	
	public void setInfo() {
		infota.setText("");
		sec = dao.dbrest;
		min = sec / 60;
		hour = min / 60;
		min = min % 60;
		sec = sec % 60;
		infota.setText("�̸� : " + dao.dbname + "\n" + "���̵� : " + dao.dbid + 
				"\n" + "���� �ð� : " + hour + ":" + min + ":" + sec + "\n" + "�ܾ� : " + dao.dbmoney);
		haveid = true;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		boolean temp = false;
		if(obj == infobtn || obj == infotf) {
			temp = dao.infoSelect(infotf.getText());
			if(!temp) {
				JOptionPane.showMessageDialog(this, "���� ���̵� �Դϴ�.");
			}else {
				setInfo();
			}
		}else if(obj == btn1) {
			if(haveid) {
				if(dao.dbmoney < 1000) {
					JOptionPane.showMessageDialog(this, "�����ڿ��� �����ϼ���.");
				}else {
					dao.moneyUpdate(1000, 1);
					JOptionPane.showMessageDialog(this, "�����Ǿ����ϴ�.");
					setInfo();
				}
			}else {
				JOptionPane.showMessageDialog(this, "���̵� ã������");
			}
		}else if(obj == btn2) {
			if(haveid) {
				if(dao.dbmoney < 2000) {
					JOptionPane.showMessageDialog(this, "�����ڿ��� �����ϼ���.");
				}else {
					dao.moneyUpdate(2000, 2);
					JOptionPane.showMessageDialog(this, "�����Ǿ����ϴ�.");
					setInfo();
				}
			}else {
				JOptionPane.showMessageDialog(this, "���̵� ã������");
			}
		}else if(obj == btn4) {
			if(haveid) {
				if(dao.dbmoney < 4000) {
					JOptionPane.showMessageDialog(this, "�����ڿ��� �����ϼ���.");
				}else {
					dao.moneyUpdate(4000, 4);
					JOptionPane.showMessageDialog(this, "�����Ǿ����ϴ�.");
					setInfo();
				}
			}else {
				JOptionPane.showMessageDialog(this, "���̵� ã������");
			}
		}else if(obj == btn8) {
			if(haveid) {
				if(dao.dbmoney < 7000) {
					JOptionPane.showMessageDialog(this, "�����ڿ��� �����ϼ���.");
				}else {
					dao.moneyUpdate(7000, 8);
					JOptionPane.showMessageDialog(this, "�����Ǿ����ϴ�.");
					setInfo();
				}
			}else {
				JOptionPane.showMessageDialog(this, "���̵� ã������");
			}
		}else if(obj == btn16) {
			if(haveid) {
				if(dao.dbmoney < 14000) {
					JOptionPane.showMessageDialog(this, "�����ڿ��� �����ϼ���.");
				}else {
					dao.moneyUpdate(14000, 16);
					JOptionPane.showMessageDialog(this, "�����Ǿ����ϴ�.");
					setInfo();
				}
			}else {
				JOptionPane.showMessageDialog(this, "���̵� ã������");
			}
		}else if(obj == btn32) {
			if(haveid) {
				if(dao.dbmoney < 26000) {
					JOptionPane.showMessageDialog(this, "�����ڿ��� �����ϼ���.");
				}else {
					dao.moneyUpdate(26000, 32);
					JOptionPane.showMessageDialog(this, "�����Ǿ����ϴ�.");
					setInfo();
				}
			}else {
				JOptionPane.showMessageDialog(this, "���̵� ã������");
			}
		}else if(obj == backbtn) {
			infotf.setText("");
			infota.setText("");
			haveid = false;
			dispose();
		}
	}
	
}
