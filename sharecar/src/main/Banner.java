package main;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//신규회원에게만 뜨는 창 환영의미의 배너
public class Banner extends JFrame implements ItemListener{
	
	private JCheckBox box = new JCheckBox("오늘하루 이 창을 보지 않기");
	
	Banner(String nickname){
		JLabel title = new JLabel(nickname+"님 환영합니다",JLabel.CENTER);
		JLabel memo = new JLabel("신규회원은 1000point 지급해드립니다!",JLabel.CENTER);
		JLabel memo2 = new JLabel("지금 바로 myPage에서 확인하세요!",JLabel.CENTER);
		
		Font titleFont = new Font("휴먼둥근헤드라인",Font.BOLD,30);

		JPanel centerp = new JPanel();
		
		centerp.setLayout(new GridLayout(2,1));
		memo.setFont(titleFont);
		memo2.setFont(titleFont);
		centerp.add(memo);
		centerp.add(memo2);
		title.setFont(titleFont);
		
		box.addItemListener(this);
		
		this.add("North",title);
		this.add("Center",centerp);
		this.add("South",box);
		
		this.setBounds(200,300,800,200);
		this.setVisible(true);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getItem().equals(box)) {
			//체크박스 체크시 창종료
			this.setVisible(false);
		}
	}
}
