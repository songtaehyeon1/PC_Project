package member;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

public class MemberMain extends JPanel implements ActionListener{
	
//	툴바
	JToolBar tb = new JToolBar();
	JButton addbtn = new JButton("등록");
	JButton editbtn = new JButton("수정");
	JButton delbtn = new JButton("삭제");
	JButton clearbtn = new JButton("지우기");
	
//	폼만들기
	JPanel northForm = new JPanel(new BorderLayout());
		JPanel formlbl = new JPanel(new GridLayout(10, 1));
			JLabel namelbl = new JLabel("이름");
			JLabel idlbl = new JLabel("아이디");
			JLabel pwlbl = new JLabel("비밀번호");
			JLabel humannumlbl = new JLabel("주민등록번호");
			JLabel birthlbl = new JLabel("생년월일");
			JLabel genderlbl = new JLabel("성별");
			JLabel tellbl = new JLabel("전화번호");
			JLabel emaillbl = new JLabel("이메일");
			JLabel resttimelbl = new JLabel("남은 시간");
			JLabel moneytemplbl = new JLabel("받아야 되는 돈");
		JPanel formCenter = new JPanel(new GridLayout(10, 1));
			JTextField nametf = new JTextField();
			JTextField idtf = new JTextField();
			JTextField pwtf = new JTextField();
			JTextField humannumtf = new JTextField();
			JTextField birthtf = new JTextField();
			JTextField gendertf = new JTextField();
			JTextField teltf = new JTextField();
			JTextField emailtf = new JTextField();
			JTextField resttimetf = new JTextField("0");
			JTextField moneytemptf = new JTextField("0");
			
//	JTable
	JTable table;
	DefaultTableModel model;
//	제목
	String[] title = {"이름", "아이디", "비밀번호", "주민등록번호", "생년월일", "성별", "전화번호", "이메일", "남은 시간", "받아야 되는 돈"};
	Object[][] data = new Object[0][0];
	JLabel lbl = new JLabel("선택한 데이터 표시되는 곳");
	JScrollPane sp;
	
	public MemberMain() {
		setLayout(new BorderLayout());
//		툴바
		tb.add(addbtn);
		tb.add(editbtn);
		tb.add(delbtn);
		tb.add(clearbtn);
		northForm.add(tb, "North");
//		폼만들기
		northForm.add(formlbl, BorderLayout.WEST);
			formlbl.add(namelbl);
			formlbl.add(idlbl);
			formlbl.add(pwlbl);
			formlbl.add(humannumlbl);
			formlbl.add(birthlbl);
			formlbl.add(genderlbl);
			formlbl.add(tellbl);
			formlbl.add(emaillbl);
			formlbl.add(resttimelbl);
			formlbl.add(moneytemplbl);
		northForm.add(formCenter);
			formCenter.add(nametf);
			formCenter.add(idtf);
			formCenter.add(pwtf);
			formCenter.add(humannumtf);
			formCenter.add(birthtf);
			formCenter.add(gendertf);
			formCenter.add(teltf);
			formCenter.add(emailtf);
			formCenter.add(resttimetf);
			formCenter.add(moneytemptf);
		add(northForm, "North");
//		/////////////////////////////////////////////
		model = new DefaultTableModel(data, title);
		table = new JTable(model);
		sp = new JScrollPane(table);
		
		resttimetf.setEnabled(false);
		moneytemptf.setEnabled(false);
		
		allRecordSelect();
		
		add(sp);
		
		add(lbl, "South");
		
		setVisible(true);
		
		table.addMouseListener(new MouseAdapter() {
//			released : 클릭 후 놓을 때
			public void mouseReleased(MouseEvent me) {
//				마우스 버튼 종류 알아내기
				int click = me.getButton();
				if(click == 1) {
//					선택한 행의 index를 구한다.
					int rowIdx = table.getSelectedRow();
//					열의 수 구하기
					int colCnt = table.getColumnCount();
					String txt = "";
					for(int c = 0; c < colCnt; c++) {
						Object object = table.getValueAt(rowIdx, c);
						txt += object + ", ";
					}
					lbl.setText(txt);
					
					moneytemptf.setEnabled(true);
					
//					데이터 보여주기
					nametf.setText((String)table.getValueAt(rowIdx, 0));
					idtf.setText((String)table.getValueAt(rowIdx, 1));
					pwtf.setText((String)table.getValueAt(rowIdx, 2));
					humannumtf.setText((String)table.getValueAt(rowIdx, 3));
					birthtf.setText(String.valueOf(table.getValueAt(rowIdx, 4)));
					gendertf.setText((String)table.getValueAt(rowIdx, 5));
					teltf.setText((String)table.getValueAt(rowIdx, 6));
					emailtf.setText((String)table.getValueAt(rowIdx, 7));
//					int sec = (Integer)table.getValueAt(rowIdx, 8);
//					int hour, min;
//					min = sec / 60;
//					hour = min / 60;
//					sec = sec % 60;
//					min = min % 60;
//					resttimetf.setText(hour + ":" + min + ":" + sec);
					resttimetf.setText(String.valueOf(table.getValueAt(rowIdx, 8)));
					moneytemptf.setText(String.valueOf(table.getValueAt(rowIdx, 9)));
				}
			}
		});
		
//		ActionEvent 등록
		addbtn.addActionListener(this);
		editbtn.addActionListener(this);
		delbtn.addActionListener(this);
		clearbtn.addActionListener(this);
	}
	
//	전체 레코드 선택
	public void allRecordSelect() {
		model.setNumRows(0);
		MemberDAO dao = new MemberDAO();
		List<MemberVO> list = dao.selectAllRecord();
		for(int i = 0; i < list.size(); i++) {
			MemberVO vo = list.get(i);
			Object[] record = {vo.getName(), vo.getId(), vo.getPw(), vo.getHumannum(), vo.getBirth(), 
					vo.getGender(), vo.getTel(), vo.getEmail(), vo.getResttime(), vo.getMoneytemp()};
			model.addRow(record);
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String eventbtn = ae.getActionCommand();
		if(eventbtn.equals("지우기")) {
			formClear();
		}else if(eventbtn.equals("등록")) {
			formDataInsert();
		}else if(eventbtn.equals("수정")) {
			recordEdit();
		}else if(eventbtn.equals("삭제")) {
			recordDelete();
		}
	}
	
//	등록
	public void formDataInsert() {
		JOptionPane pane = new JOptionPane();
		if(idtf.getText().equals("")) {
			pane.showMessageDialog(this, "아이디를 입력하세요.");
//			pane.showConfirmDialog(this, "번호를 입력하세요.");
//			pane.showInputDialog(this, "번호를 입력하세요.");
		}else if(nametf.getText().equals("")) {
			pane.showMessageDialog(this, "이름을 입력하세요.");
		}else if(pwtf.getText().equals("")) {
			pane.showMessageDialog(this, "비밀번호를 입력하세요.");
		}else if(humannumtf.getText().equals("")) {
			pane.showMessageDialog(this, "주민등록번호를 입력하세요.");
		}else if(teltf.getText().equals("")) {
			pane.showMessageDialog(this, "연락처를 입력하세요.");
		}else {
			MemberVO vo = new MemberVO();
			vo.setName(nametf.getText());
			vo.setId(idtf.getText());
			vo.setPw(pwtf.getText());
			vo.setHumannum(humannumtf.getText());
			vo.setBirth(Integer.parseInt(birthtf.getText()));
			vo.setGender(gendertf.getText());
			vo.setTel(teltf.getText());
			vo.setEmail(emailtf.getText());
			vo.setResttime(0);
			vo.setMoneytemp(0);
			MemberDAO dao = new MemberDAO();
			int cnt = dao.memberInsert(vo);
			if(cnt == 0) {
				pane.showMessageDialog(this, "회원등록 실패 되었습니다.");
			}else {
				pane.showMessageDialog(this, "회원등록 되었습니다.");
			}
			allRecordSelect();
		}
	}
	
//	수정
	public void recordEdit() {
		if(idtf.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "아이디가 없으면 수정할 수 없습니다.");
		}else if(nametf.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "이름을 입력하세요.");
		}else if(teltf.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "연락처를 입력하세요.");
		}else if(pwtf.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요.");
		}else if(humannumtf.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "주민등록번호를 입력하세요.");
		}else {
			MemberVO vo = new MemberVO();
			vo.setName(nametf.getText());
			vo.setId(idtf.getText());
			vo.setPw(pwtf.getText());
			vo.setHumannum(humannumtf.getText());
			vo.setBirth(Integer.parseInt(birthtf.getText()));
			vo.setGender(gendertf.getText());
			vo.setTel(teltf.getText());
			vo.setEmail(emailtf.getText());
			vo.setResttime(Integer.parseInt(resttimetf.getText()));
//			vo.setResttime(Integer.parseInt(resttimetf.getText().substring(0, resttimetf.getText().indexOf(":"))) * 3600 
//					+ Integer.parseInt(resttimetf.getText().substring(resttimetf.getText().indexOf(":"), resttimetf.getText().lastIndexOf(":"))) * 60 
//							+ Integer.parseInt(resttimetf.getText().substring(resttimetf.getText().lastIndexOf(":"), resttimetf.getText().length())));
			vo.setMoneytemp(Integer.parseInt(moneytemptf.getText()));
			MemberDAO dao = MemberDAO.getInstance();
			int cnt = dao.updateRecord(vo);
			if(cnt <= 0) {
				JOptionPane.showMessageDialog(this, "수정 실패 하였습니다.");
			}else {
				JOptionPane.showMessageDialog(this, vo.getId() + "님의 정보가 수정 되었습니다.");
			}
			allRecordSelect();
		}
	}
	
//	삭제
	public void recordDelete() {
		if(idtf.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "아이디가 있어야 삭제 가능합니다.");
		}else {
			String id = idtf.getText();
			MemberDAO dao = MemberDAO.getInstance();
			int cnt = dao.deleteRecord(id);
			if(cnt <= 0) {
				JOptionPane.showMessageDialog(this, "삭제 실패 하였습니다.");
			}else {
				JOptionPane.showMessageDialog(this, id + "님의 정보가 삭제 되었습니다.");
			}
			allRecordSelect();
		}
	}
	
//	폼 내용 지우기
	public void formClear() {
		idtf.setText("");
		pwtf.setText("");
		humannumtf.setText("");
		gendertf.setText("");
		birthtf.setText("");
		nametf.setText("");
		teltf.setText("");
		emailtf.setText("");
		resttimetf.setText("0");
		moneytemptf.setText("0");
		resttimetf.setEnabled(false);
		moneytemptf.setEnabled(false);
	}

}
