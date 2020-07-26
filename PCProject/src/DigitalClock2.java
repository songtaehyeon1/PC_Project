

import java.awt.BorderLayout;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DigitalClock2 extends JPanel implements Runnable{
	
	JLabel time = new JLabel("", JLabel.CENTER);
	Font fnt = new Font("Arial", Font.BOLD, 20);

	public DigitalClock2() {
		this(0,0);
	}
	
	public DigitalClock2(int x, int y) {
		setLayout(new BorderLayout());	// FlowLayout -> BorderLayout으로 변경
		time.setFont(fnt);
		add(time);
		
		setVisible(true);
	}

	public void run() {
		while(true) {
			setTime();
			try {
				Thread.sleep(1000);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setTime() {
		Calendar now = Calendar.getInstance();
//		int ampmint = now.get(Calendar.AM_PM);
//		String ampmstr;
//		if(ampmint == 1) {
//			ampmstr = "오전";
//		}else {
//			ampmstr = "오후";
//		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd    HH : mm");
		String timeStr = sdf.format(now.getTime());
//		timeStr += ampmstr;
		time.setText(timeStr);
	}

}
