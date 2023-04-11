package main;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

//¥Á√∑¿Œ¡ˆ ≤Œ¿Œ¡ˆ messegebox √‚∑¬
public class Gui_event2 extends JFrame implements ActionListener{
	private JButton win= new JButton("¥Á√∑!");
	private JButton lose = new JButton("≤Œ!");
	Gui_event2(boolean flag){
		this.setBounds(300,300,300,300);
		win.addActionListener(this);
		lose.addActionListener(this);
		if(flag) {
			JLabel winImg = new JLabel();
			ImageIcon winIcon = new ImageIcon(Gui_event2.class.getResource("../image/win.jpg"));
			Image win = winIcon.getImage();
			Image win2 = win.getScaledInstance(250, 250, win.SCALE_SMOOTH);
			ImageIcon winIcon2 = new ImageIcon(win2);
			winImg.setIcon(winIcon2);
			this.add("Center",winImg);
			this.add("South",this.win);
		}else {
			JLabel loseImg = new JLabel();
			ImageIcon loseIcon = new ImageIcon(Gui_event2.class.getResource("../image/lose.jpg"));
			Image lose = loseIcon.getImage();
			Image lose2 = lose.getScaledInstance(250, 250, lose.SCALE_SMOOTH);
			ImageIcon loseIcon2 = new ImageIcon(lose2);
			loseImg.setIcon(loseIcon2);
			this.add("Center",loseImg);
			this.add("South",this.lose);
		}
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(win)) {
			this.setVisible(false);
		}else if(e.getSource().equals(lose)) {
			this.setVisible(false);
		}
	}
}
