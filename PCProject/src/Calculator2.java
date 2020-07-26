

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calculator2 extends JPanel implements ActionListener{

	Font fnt = new Font("Arial", Font.BOLD, 30);
	
	JLabel lbl = new JLabel("0.0", JLabel.RIGHT);
	JPanel panCenter = new JPanel(new BorderLayout());
		JPanel north = new JPanel(new GridLayout(1, 3));
		JPanel center = new JPanel(new GridLayout(4, 4));
	
	String btnlbl[] = {"Backspace", "Clear", "End",
						"7", "8", "9", "+",
						"4", "5", "6", "-",
						"1", "2", "3", "*",
						"0", ".", "=", "/"};
	double result;
	String operator = "";
	
	public Calculator2() {
		setLayout(new BorderLayout());
		lbl.setFont(fnt);
		add(lbl, "North");
		
		add(panCenter);
		panCenter.add(north, "North");
		panCenter.add(center);
		
		for(int i = 0; i < btnlbl.length; i++) {
			JButton btn = new JButton(btnlbl[i]);
			btn.setFont(fnt);
			if(i <= 2) {
				north.add(btn);
			}else {
				center.add(btn);
			}
			btn.addActionListener(this);
		}
		
		setVisible(true);
	}

//	double result = 0, num = 0;
//	String save;
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		String ebtn = ae.getActionCommand();	// 이벤트가 발생한 버튼의 Label
		switch(ebtn) {
			case "Backspace": setLastCut(); break;
			case "Clear": setBtnClear(); break;
			case "End": System.exit(0); break;
			case "0": case "1": case "2": case "3": case "4":
			case "5": case "6": case "7": case "8": case "9":
				setNumber(ebtn); break;
			case ".": setPoint(); break;
			case "+": case "-": case "*": case "/":
				setOperator(ebtn); break;
			case "=": getArithmetic(); break;
		}
	}
	
//	=버튼
	public void getArithmetic() {
		double num2 = Double.parseDouble(lbl.getText().trim());
		switch(this.operator) {
			case "+": result += num2; break;
			case "-": result -= num2; break;
			case "*": result *= num2; break;
			case "/": result /= num2;
		}
		lbl.setText(String.valueOf(result));
	}
	
//	연산자버튼
	public void setOperator(String oper) {
//		Label값 보관
		result = Double.parseDouble(lbl.getText().trim());
//		연산자 보관
		this.operator = oper;
//		Label 초기화
		lbl.setText(" ");
	}

//	.버튼
	public void setPoint() {
		String txt = lbl.getText();
		if(txt.indexOf(".") == -1) {
			lbl.setText(txt + ".");
		}
	}
	
//	Clear버튼
	public void setBtnClear() {
		result = 0;
		this.operator = "";
		lbl.setText("0");
	}
	
//	Backspace버튼
	public void setLastCut() {
		String txt = lbl.getText();
		if(txt.length() > 1) {
			lbl.setText(txt.substring(0, txt.length() - 1));
		}else {
			lbl.setText("0");
		}
	}
	
//	숫자버튼
	public void setNumber(String num) {
		String txt = lbl.getText();
		if(txt.equals("0.0")) {
			lbl.setText(num);
		}else {
			lbl.setText(txt + num);
		}
	}

}
