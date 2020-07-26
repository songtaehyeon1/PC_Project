import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Join extends JDialog implements ActionListener{

	JPanel pan = new JPanel(null);
		JLabel lbl1 = new JLabel("�̸�");
		JLabel lbl2 = new JLabel("���̵�");
		JLabel lbl3 = new JLabel("��й�ȣ");
		JLabel lbl4 = new JLabel("��й�ȣ Ȯ��");
		JLabel lbl5 = new JLabel("�ֹε�Ϲ�ȣ");
		JLabel lbl6 = new JLabel("�������");
		JLabel lbl7 = new JLabel("����");
		JLabel lbl8 = new JLabel("�޴���");
		JLabel lbl9 = new JLabel("�̸���");
		JLabel lbl10 = new JLabel("���� x, 5 ~ 10�ڸ� ��� ����");
		JLabel lbl11 = new JLabel("8 ~ 20�ڸ� ��� ����");
		JTextField nameT = new JTextField();
		JTextField idT = new JTextField();
		JTextField pwT = new JTextField();
		JTextField pwcheckT = new JTextField();
		JTextField renumT = new JTextField();
		JTextField birthT = new JTextField();
		ButtonGroup group = new ButtonGroup();
		JRadioButton rbtn[] = new JRadioButton[2];
		JTextField telT = new JTextField();
		JTextField emailT = new JTextField();
		JButton idbtn = new JButton("�ߺ�Ȯ��");
		JButton okbtn = new JButton("Ȯ��");
		JButton cancelbtn = new JButton("���");
		
	AllDAO dao = new AllDAO();
	boolean checkid = false;
	String gen = "M";
		
	public Join() {}
	public Join(JFrame frame) {
		super(frame, "ȸ������", true);
//		this.setTitle("ȸ������");

		rbtn[0] = new JRadioButton("����", true);
		rbtn[1] = new JRadioButton("����");
		for(JRadioButton r : rbtn) {
			group.add(r);
			add(r);
		}
				
		lbl1.setBounds(50, 10, 90, 30);
		lbl2.setBounds(50, 50, 90, 30);
		lbl3.setBounds(50, 100, 90, 30);
		lbl4.setBounds(50, 150, 90, 30);
		lbl5.setBounds(50, 190, 90, 30);
		lbl6.setBounds(50, 230, 90, 30);
		lbl7.setBounds(50, 270, 90, 30);
		lbl8.setBounds(50, 310, 90, 30);
		lbl9.setBounds(50, 350, 90, 30);
		nameT.setBounds(150, 10, 100, 30);
		idT.setBounds(150, 50, 100, 30);
		lbl10.setBounds(150, 75, 200, 30);
		pwT.setBounds(150, 100, 130, 30);
		lbl11.setBounds(150, 125, 200, 30);
		pwcheckT.setBounds(150, 150, 130, 30);
		renumT.setBounds(150, 190, 200, 30);
		birthT.setBounds(150, 230, 100, 30);
		rbtn[0].setBounds(150, 270, 70, 30);
		rbtn[1].setBounds(220, 270, 70, 30);
		telT.setBounds(150, 310, 200, 30);
		emailT.setBounds(150, 350, 200, 30);
		idbtn.setBounds(260, 50, 90, 30);
		okbtn.setBounds(130, 400, 60, 30);
		cancelbtn.setBounds(200, 400, 60, 30);
		add(lbl1);add(lbl2);add(lbl3);add(lbl4);add(lbl5);
		add(lbl6);add(lbl7);add(lbl8);add(lbl9);
		add(nameT);add(idT);add(pwT);add(pwcheckT);add(renumT);
		add(birthT);add(telT);add(emailT);add(lbl10);add(lbl11);
		add(idbtn);add(okbtn);add(cancelbtn);
		add(pan);
		
		setSize(400, 500);
//		setVisible(true);
		setLocationRelativeTo(null);	// ȭ�� ������� â�� ����
		setResizable(false);	// ������ ������� ����Ұ�
		
		idbtn.addActionListener(this);
		okbtn.addActionListener(this);
		cancelbtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == idbtn) {
			idcheck();
		}else if(obj == okbtn) {
			validation();
		}else if(obj == cancelbtn) {
			dispose();
		}
	}
	String tempid = "";
	public void idcheck() {
		boolean temp = dao.idcheckSelect(idT.getText());
		if(idT.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "���̵� �Է��� �ּ���.");
		}else if(idT.getText().length() > 10 || idT.getText().length() < 5) {
			JOptionPane.showMessageDialog(this, "���� ���� Ȯ���� �ּ���.");
		}else if(idT.getText().indexOf(" ") != -1) {
			JOptionPane.showMessageDialog(this, "������ ���� �ȵ˴ϴ�.");
		}else if(temp) {
			JOptionPane.showMessageDialog(this, "�̹� ��� ���� ���̵� �Դϴ�.");
		}else if(!temp) {
			JOptionPane.showMessageDialog(this, "��� ������ ���̵� �Դϴ�.");
			checkid = true;
			tempid = idT.getText();
		}else {
			JOptionPane.showMessageDialog(this, "�ٸ� ������ ã�ƶ�");
		}
	}
	
	public void validation() {
		if(rbtn[1].isSelected()) {
			gen = "W";
		}else if(rbtn[0].isSelected()) {
			gen = "M";
		}
		if(nameT.getText().equals("") || nameT.getText().indexOf(" ") != -1) {
			JOptionPane.showMessageDialog(this, "�̸� ����� ���");
		}else if(idT.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "���̵� �Է��� �ּ���.");
		}else if(idT.getText().length() > 10 || idT.getText().length() < 5) {
			JOptionPane.showMessageDialog(this, "���� ���� Ȯ���� �ּ���.");
		}else if(idT.getText().indexOf(" ") != -1) {
			JOptionPane.showMessageDialog(this, "������ ���� �ȵ˴ϴ�.");
		}else if(!tempid.equals(idT.getText())) {
			JOptionPane.showMessageDialog(this, "�ߺ� Ȯ���� �ٽ� �ϼ���");
		}else if(pwT.getText().equals("") || pwT.getText().indexOf(" ") != -1 || 
				pwT.getText().length() < 8 || pwT.getText().length() > 20) {
			JOptionPane.showMessageDialog(this, "��й�ȣ ����� ���");
		}else if(!pwT.getText().equals(pwcheckT.getText())) {
			JOptionPane.showMessageDialog(this, "��й�ȣ�� �Ȱ��� ���");
		}
		else if(renumT.getText().equals("") || renumT.getText().indexOf(" ") != -1 || 
				renumT.getText().length() >= 15 || renumT.getText().indexOf("-") == -1 ||
				renumT.getText().length() <= 13) {
			JOptionPane.showMessageDialog(this, "�ֹε�Ϲ�ȣ ����� ���");
		}else if(birthT.getText().equals("") || renumT.getText().indexOf(" ") != -1) {
			JOptionPane.showMessageDialog(this, "���� ����� ���");
		}else if(telT.getText().equals("") || telT.getText().indexOf(" ") != -1 ||
				telT.getText().length() >= 14 || telT.getText().length() <= 12 ||
				renumT.getText().indexOf("-") == -1) {
			JOptionPane.showMessageDialog(this, "��ȭ��ȣ ����� ���");
		}else if(!checkid) {
			JOptionPane.showMessageDialog(this, "�ߺ� Ȯ���� �ּ���");
		}else if(checkid) {
			JoinVO joinvo = new JoinVO(nameT.getText(), idT.getText(), pwT.getText(), renumT.getText(), 
					Integer.parseInt(birthT.getText()), gen, telT.getText(), emailT.getText());
			dao.memberInsert(joinvo);
			dispose();
			JOptionPane.showMessageDialog(this, "ȸ������ �Ǿ����ϴ�.");
		}
//		else {
//			JOptionPane.showMessageDialog(this, "���� ���Դ�");
//		}
	}

}
