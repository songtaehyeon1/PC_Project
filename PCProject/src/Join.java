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
		JLabel lbl1 = new JLabel("이름");
		JLabel lbl2 = new JLabel("아이디");
		JLabel lbl3 = new JLabel("비밀번호");
		JLabel lbl4 = new JLabel("비밀번호 확인");
		JLabel lbl5 = new JLabel("주민등록번호");
		JLabel lbl6 = new JLabel("생년월일");
		JLabel lbl7 = new JLabel("성별");
		JLabel lbl8 = new JLabel("휴대폰");
		JLabel lbl9 = new JLabel("이메일");
		JLabel lbl10 = new JLabel("공백 x, 5 ~ 10자만 사용 가능");
		JLabel lbl11 = new JLabel("8 ~ 20자만 사용 가능");
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
		JButton idbtn = new JButton("중복확인");
		JButton okbtn = new JButton("확인");
		JButton cancelbtn = new JButton("취소");
		
	AllDAO dao = new AllDAO();
	boolean checkid = false;
	String gen = "M";
		
	public Join() {}
	public Join(JFrame frame) {
		super(frame, "회원가입", true);
//		this.setTitle("회원가입");

		rbtn[0] = new JRadioButton("남자", true);
		rbtn[1] = new JRadioButton("여자");
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
		setLocationRelativeTo(null);	// 화면 가운데에서 창이 나옴
		setResizable(false);	// 정해진 사이즈에서 변경불가
		
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
			JOptionPane.showMessageDialog(this, "아이디를 입력해 주세요.");
		}else if(idT.getText().length() > 10 || idT.getText().length() < 5) {
			JOptionPane.showMessageDialog(this, "글자 수를 확인해 주세요.");
		}else if(idT.getText().indexOf(" ") != -1) {
			JOptionPane.showMessageDialog(this, "공백이 들어가면 안됩니다.");
		}else if(temp) {
			JOptionPane.showMessageDialog(this, "이미 사용 중인 아이디 입니다.");
		}else if(!temp) {
			JOptionPane.showMessageDialog(this, "사용 가능한 아이디 입니다.");
			checkid = true;
			tempid = idT.getText();
		}else {
			JOptionPane.showMessageDialog(this, "다른 에러다 찾아라");
		}
	}
	
	public void validation() {
		if(rbtn[1].isSelected()) {
			gen = "W";
		}else if(rbtn[0].isSelected()) {
			gen = "M";
		}
		if(nameT.getText().equals("") || nameT.getText().indexOf(" ") != -1) {
			JOptionPane.showMessageDialog(this, "이름 제대로 써라");
		}else if(idT.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "아이디를 입력해 주세요.");
		}else if(idT.getText().length() > 10 || idT.getText().length() < 5) {
			JOptionPane.showMessageDialog(this, "글자 수를 확인해 주세요.");
		}else if(idT.getText().indexOf(" ") != -1) {
			JOptionPane.showMessageDialog(this, "공백이 들어가면 안됩니다.");
		}else if(!tempid.equals(idT.getText())) {
			JOptionPane.showMessageDialog(this, "중복 확인을 다시 하세요");
		}else if(pwT.getText().equals("") || pwT.getText().indexOf(" ") != -1 || 
				pwT.getText().length() < 8 || pwT.getText().length() > 20) {
			JOptionPane.showMessageDialog(this, "비밀번호 제대로 써라");
		}else if(!pwT.getText().equals(pwcheckT.getText())) {
			JOptionPane.showMessageDialog(this, "비밀번호랑 똑같이 써라");
		}
		else if(renumT.getText().equals("") || renumT.getText().indexOf(" ") != -1 || 
				renumT.getText().length() >= 15 || renumT.getText().indexOf("-") == -1 ||
				renumT.getText().length() <= 13) {
			JOptionPane.showMessageDialog(this, "주민등록번호 제대로 써라");
		}else if(birthT.getText().equals("") || renumT.getText().indexOf(" ") != -1) {
			JOptionPane.showMessageDialog(this, "생일 제대로 써라");
		}else if(telT.getText().equals("") || telT.getText().indexOf(" ") != -1 ||
				telT.getText().length() >= 14 || telT.getText().length() <= 12 ||
				renumT.getText().indexOf("-") == -1) {
			JOptionPane.showMessageDialog(this, "전화번호 제대로 써라");
		}else if(!checkid) {
			JOptionPane.showMessageDialog(this, "중복 확인해 주세요");
		}else if(checkid) {
			JoinVO joinvo = new JoinVO(nameT.getText(), idT.getText(), pwT.getText(), renumT.getText(), 
					Integer.parseInt(birthT.getText()), gen, telT.getText(), emailT.getText());
			dao.memberInsert(joinvo);
			dispose();
			JOptionPane.showMessageDialog(this, "회원가입 되었습니다.");
		}
//		else {
//			JOptionPane.showMessageDialog(this, "오류 나왔다");
//		}
	}

}
