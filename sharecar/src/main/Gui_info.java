package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

//이용안내 페이지
public class Gui_info extends JFrame implements ActionListener{
	private JButton nextBtn;
	private int cnt = 0;
	private JLabel imgL;
	private JLabel imgL2;
	private JLabel imgL3;
	private JLabel imgL4;
	private JLabel imgL5;
	private JLabel imgL6;
	private JLabel imgL7;
	private JLabel imgL8;
	public Gui_info() {
		Font titleFont = new Font("함초롬돋움",Font.BOLD,30);
		JLabel title = new JLabel("이용 안내",JLabel.CENTER);
		Color titleColor = new Color(0x794B0082,true);
		
		imgL = new JLabel();
		ImageIcon icon = new ImageIcon(Gui_main.class.getResource("../image/step1.jpg"));
		Image i1 = icon.getImage();
		Image i2 = i1.getScaledInstance(500, 500, i1.SCALE_SMOOTH);
		ImageIcon icon2 = new ImageIcon(i2);
		imgL.setIcon(icon2);
		imgL.setHorizontalAlignment(JLabel.CENTER);
		
		imgL2 = new JLabel();
		ImageIcon icon2_1 = new ImageIcon(Gui_main.class.getResource("../image/step2.jpg"));
		Image i2_1 = icon2_1.getImage();
		Image i2_2 = i2_1.getScaledInstance(500, 500, i2_1.SCALE_SMOOTH);
		ImageIcon icon2_2 = new ImageIcon(i2_2);
		imgL2.setIcon(icon2_2);
		imgL2.setHorizontalAlignment(JLabel.CENTER);
		
		imgL3 = new JLabel();
		ImageIcon icon3_1 = new ImageIcon(Gui_main.class.getResource("../image/step3.jpg"));
		Image i3_1 = icon3_1.getImage();
		Image i3_2 = i3_1.getScaledInstance(500, 500, i3_1.SCALE_SMOOTH);
		ImageIcon icon3_2 = new ImageIcon(i3_2);
		imgL3.setIcon(icon3_2);
		imgL3.setHorizontalAlignment(JLabel.CENTER);
		
		imgL4 = new JLabel();
		ImageIcon icon4_1 = new ImageIcon(Gui_main.class.getResource("../image/step4.jpg"));
		Image i4_1 = icon4_1.getImage();
		Image i4_2 = i4_1.getScaledInstance(500, 500, i4_1.SCALE_SMOOTH);
		ImageIcon icon4_2 = new ImageIcon(i4_2);
		imgL4.setIcon(icon4_2);
		imgL4.setHorizontalAlignment(JLabel.CENTER);
		
		imgL5 = new JLabel();
		ImageIcon icon5_1 = new ImageIcon(Gui_main.class.getResource("../image/step5.jpg"));
		Image i5_1 = icon5_1.getImage();
		Image i5_2 = i5_1.getScaledInstance(500, 500, i5_1.SCALE_SMOOTH);
		ImageIcon icon5_2 = new ImageIcon(i5_2);
		imgL5.setIcon(icon5_2);
		imgL5.setHorizontalAlignment(JLabel.CENTER);
		
		imgL6 = new JLabel();
		ImageIcon icon6_1 = new ImageIcon(Gui_main.class.getResource("../image/step6.jpg"));
		Image i6_1 = icon6_1.getImage();
		Image i6_2 = i6_1.getScaledInstance(500, 500, i6_1.SCALE_SMOOTH);
		ImageIcon icon6_2 = new ImageIcon(i6_2);
		imgL6.setIcon(icon6_2);
		imgL6.setHorizontalAlignment(JLabel.CENTER);
		
		imgL7 = new JLabel();
		ImageIcon icon7_1 = new ImageIcon(Gui_main.class.getResource("../image/step7.jpg"));
		Image i7_1 = icon7_1.getImage();
		Image i7_2 = i7_1.getScaledInstance(500, 500, i7_1.SCALE_SMOOTH);
		ImageIcon icon7_2 = new ImageIcon(i7_2);
		imgL7.setIcon(icon7_2);
		imgL7.setHorizontalAlignment(JLabel.CENTER);
		
		imgL8 = new JLabel("그럼 ShareCar를 즐겨주세요!",JLabel.CENTER);
		imgL8.setFont(titleFont);
		imgL8.setForeground(titleColor);
		
		nextBtn = new JButton("다음");
		nextBtn.addActionListener(this);
		this.setBounds(100,100,700,600);
		title.setFont(titleFont);
		this.add("Center",imgL);
		this.add("North",title);
		this.add("South",nextBtn);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(nextBtn)) {
			//다음 버튼을 누를때마다 다른 사진출력
			cnt++;
			switch (cnt) {
			case 1:
				this.remove(imgL);
				this.add("Center",imgL2);
				this.setVisible(true);
				break;
			case 2:
				this.remove(imgL2);
				this.add("Center",imgL3);
				this.setVisible(true);
				break;
			case 3:
				this.remove(imgL3);
				this.add("Center",imgL4);
				this.setVisible(true);
				break;
			case 4:
				this.remove(imgL4);
				this.add("Center",imgL5);
				this.setVisible(true);
				break;
			case 5:
				this.remove(imgL5);
				this.add("Center",imgL6);
				this.setVisible(true);
				break;
			case 6:
				this.remove(imgL6);
				this.add("Center",imgL7);
				this.setVisible(true);
				break;
			case 7:
				this.remove(imgL7);
				this.add("Center",imgL8);
				this.setVisible(true);
				break;
			case 8:
				dispose();
				break;
			default:
				break;
			}
		}
	}
	
}
