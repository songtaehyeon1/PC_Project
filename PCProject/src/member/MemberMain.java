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
	
//	����
	JToolBar tb = new JToolBar();
	JButton addbtn = new JButton("���");
	JButton editbtn = new JButton("����");
	JButton delbtn = new JButton("����");
	JButton clearbtn = new JButton("�����");
	
//	�������
	JPanel northForm = new JPanel(new BorderLayout());
		JPanel formlbl = new JPanel(new GridLayout(10, 1));
			JLabel namelbl = new JLabel("�̸�");
			JLabel idlbl = new JLabel("���̵�");
			JLabel pwlbl = new JLabel("��й�ȣ");
			JLabel humannumlbl = new JLabel("�ֹε�Ϲ�ȣ");
			JLabel birthlbl = new JLabel("�������");
			JLabel genderlbl = new JLabel("����");
			JLabel tellbl = new JLabel("��ȭ��ȣ");
			JLabel emaillbl = new JLabel("�̸���");
			JLabel resttimelbl = new JLabel("���� �ð�");
			JLabel moneytemplbl = new JLabel("�޾ƾ� �Ǵ� ��");
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
//	����
	String[] title = {"�̸�", "���̵�", "��й�ȣ", "�ֹε�Ϲ�ȣ", "�������", "����", "��ȭ��ȣ", "�̸���", "���� �ð�", "�޾ƾ� �Ǵ� ��"};
	Object[][] data = new Object[0][0];
	JLabel lbl = new JLabel("������ ������ ǥ�õǴ� ��");
	JScrollPane sp;
	
	public MemberMain() {
		setLayout(new BorderLayout());
//		����
		tb.add(addbtn);
		tb.add(editbtn);
		tb.add(delbtn);
		tb.add(clearbtn);
		northForm.add(tb, "North");
//		�������
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
//			released : Ŭ�� �� ���� ��
			public void mouseReleased(MouseEvent me) {
//				���콺 ��ư ���� �˾Ƴ���
				int click = me.getButton();
				if(click == 1) {
//					������ ���� index�� ���Ѵ�.
					int rowIdx = table.getSelectedRow();
//					���� �� ���ϱ�
					int colCnt = table.getColumnCount();
					String txt = "";
					for(int c = 0; c < colCnt; c++) {
						Object object = table.getValueAt(rowIdx, c);
						txt += object + ", ";
					}
					lbl.setText(txt);
					
					moneytemptf.setEnabled(true);
					
//					������ �����ֱ�
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
		
//		ActionEvent ���
		addbtn.addActionListener(this);
		editbtn.addActionListener(this);
		delbtn.addActionListener(this);
		clearbtn.addActionListener(this);
	}
	
//	��ü ���ڵ� ����
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
		if(eventbtn.equals("�����")) {
			formClear();
		}else if(eventbtn.equals("���")) {
			formDataInsert();
		}else if(eventbtn.equals("����")) {
			recordEdit();
		}else if(eventbtn.equals("����")) {
			recordDelete();
		}
	}
	
//	���
	public void formDataInsert() {
		JOptionPane pane = new JOptionPane();
		if(idtf.getText().equals("")) {
			pane.showMessageDialog(this, "���̵� �Է��ϼ���.");
//			pane.showConfirmDialog(this, "��ȣ�� �Է��ϼ���.");
//			pane.showInputDialog(this, "��ȣ�� �Է��ϼ���.");
		}else if(nametf.getText().equals("")) {
			pane.showMessageDialog(this, "�̸��� �Է��ϼ���.");
		}else if(pwtf.getText().equals("")) {
			pane.showMessageDialog(this, "��й�ȣ�� �Է��ϼ���.");
		}else if(humannumtf.getText().equals("")) {
			pane.showMessageDialog(this, "�ֹε�Ϲ�ȣ�� �Է��ϼ���.");
		}else if(teltf.getText().equals("")) {
			pane.showMessageDialog(this, "����ó�� �Է��ϼ���.");
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
				pane.showMessageDialog(this, "ȸ����� ���� �Ǿ����ϴ�.");
			}else {
				pane.showMessageDialog(this, "ȸ����� �Ǿ����ϴ�.");
			}
			allRecordSelect();
		}
	}
	
//	����
	public void recordEdit() {
		if(idtf.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "���̵� ������ ������ �� �����ϴ�.");
		}else if(nametf.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "�̸��� �Է��ϼ���.");
		}else if(teltf.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "����ó�� �Է��ϼ���.");
		}else if(pwtf.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "��й�ȣ�� �Է��ϼ���.");
		}else if(humannumtf.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "�ֹε�Ϲ�ȣ�� �Է��ϼ���.");
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
				JOptionPane.showMessageDialog(this, "���� ���� �Ͽ����ϴ�.");
			}else {
				JOptionPane.showMessageDialog(this, vo.getId() + "���� ������ ���� �Ǿ����ϴ�.");
			}
			allRecordSelect();
		}
	}
	
//	����
	public void recordDelete() {
		if(idtf.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "���̵� �־�� ���� �����մϴ�.");
		}else {
			String id = idtf.getText();
			MemberDAO dao = MemberDAO.getInstance();
			int cnt = dao.deleteRecord(id);
			if(cnt <= 0) {
				JOptionPane.showMessageDialog(this, "���� ���� �Ͽ����ϴ�.");
			}else {
				JOptionPane.showMessageDialog(this, id + "���� ������ ���� �Ǿ����ϴ�.");
			}
			allRecordSelect();
		}
	}
	
//	�� ���� �����
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
