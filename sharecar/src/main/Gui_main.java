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
import javax.swing.JPanel;

import dto.Member_DTO;

public class Gui_main extends JFrame implements ActionListener{
	
	private JPanel mainPage = new JPanel();
	private JPanel btnP = new JPanel();
	private JPanel btnP2 = new JPanel();
	private JButton nextMenuBtn = new JButton("다음 메뉴 보기");
	private JButton beforeMenuBtn = new JButton("이전 메뉴 보기");
	
	private JButton infoBtn = new JButton("이용안내");
	private	JButton myPageBtn = new JButton("mypage");
	private JButton regCarBtn = new JButton("차량등록");
	private JButton rentalBtn = new JButton("렌탈하기");
	
	private JButton eventBtn = new JButton("이벤트");
	private JButton reviewBtn = new JButton("리뷰");
	private JButton logoutBtn = new JButton("로그아웃");
	
	private Member_DTO mdto = null;
	
	public Gui_main(Member_DTO mdto) {
		
		this.mdto = mdto;
		
		JLabel imgL = new JLabel();
		ImageIcon icon = new ImageIcon(Gui_main.class.getResource("../image/back.jpg"));
		Image i1 = icon.getImage();
		Image i2 = i1.getScaledInstance(650, 370, i1.SCALE_SMOOTH);
		ImageIcon icon2 = new ImageIcon(i2);
		
		JLabel title = new JLabel("ShareCar",JLabel.CENTER);
		Font titleFont = new Font("휴먼둥근헤드라인",Font.BOLD,30);
		Color titleColor = new Color(0x794B0082,true);
		Color backColor = new Color(0x55E6E6FA);
		
		//이미지 설정
		imgL.setIcon(icon2);
		imgL.setBounds(50, 109, 650, 371);
		//프레임 설정
		this.setBounds(100,50,800,600);
		getContentPane().setLayout(null);
		//메인페이지
		mainPage.setBounds(0, 0, 784, 561);
		getContentPane().add(mainPage);
		mainPage.setLayout(null);
		
		title.setFont(titleFont);
		title.setForeground(titleColor);
//		title.setBackground(Color.BLACK);
		title.setBounds(0, 0, 280, 55);
		mainPage.add(title);
		mainPage.setBackground(backColor);
		
		mainPage.add(imgL);
		//메뉴1
		btnP.setBounds(276, 10, 417, 65);
		mainPage.add(btnP);
		btnP.setLayout(null);
		btnP.setBackground(backColor);
		//메뉴2
		btnP2.setBounds(276, 10, 312, 65);
		//버튼눌렀을때 동작
//		mainPage.add(btnP2);
		btnP2.setLayout(null);
		btnP2.setBackground(backColor);
//		JProgressBar progressBar = new JProgressBar();
//		JScrollBar btnScroll = new JScrollBar(0,0,51,146,14);
//		btnP.add(btnScroll);
		
		infoBtn.setBounds(0, 0, 97, 46);
		btnP.add(infoBtn);
		infoBtn.addActionListener(this);
		myPageBtn.setBounds(105, 0, 97, 46);
		btnP.add(myPageBtn);
		myPageBtn.addActionListener(this);
		regCarBtn.setBounds(210, 0, 97, 46);
		btnP.add(regCarBtn);
		regCarBtn.addActionListener(this);
		rentalBtn.setBounds(315, 0, 97, 46);
		btnP.add(rentalBtn);
		rentalBtn.addActionListener(this);
		
		//메뉴패널 전환
		//다음메뉴보기
		nextMenuBtn.setBounds(566, 76, 127, 23);
		mainPage.add(nextMenuBtn);
		nextMenuBtn.addActionListener(this);
		//이전메뉴보기
		beforeMenuBtn.setBounds(427, 76, 127, 23);
		mainPage.add(beforeMenuBtn);
		beforeMenuBtn.addActionListener(this);
		
		eventBtn.setBounds(0, 0, 97, 46);
		btnP2.add(eventBtn);
		eventBtn.addActionListener(this);
		reviewBtn.setBounds(105, 0, 97, 46);
		btnP2.add(reviewBtn);
		reviewBtn.addActionListener(this);
		logoutBtn.setBounds(210, 0, 97, 46);
		btnP2.add(logoutBtn);
		logoutBtn.addActionListener(this);
//		JScrollBar scrollBar = new JScrollBar(0,17,17,17,48);
//		scrollBar.setBounds(276, 82, 418, 17);
//		mainPage.add(scrollBar);
		
//		JScrollPane bar = new JScrollPane(btnP,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//		bar.setPreferredSize(new Dimension(10,10));
//		bar.setBounds(244,10,528,89);
//		mainPage.add(bar);
		

		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(nextMenuBtn)) {
			//다음메뉴 버튼을 클릭했을경우
			mainPage.setVisible(false);
			mainPage.remove(btnP);
			mainPage.add(btnP2);
//			System.out.println(1);
			mainPage.setVisible(true);
		}else if(e.getSource().equals(beforeMenuBtn)) {
			//이전메뉴 버튼을 클릭했을경우
			mainPage.setVisible(false);
			mainPage.remove(btnP2);
			mainPage.add(btnP);
			mainPage.setVisible(true);
		}else if(e.getSource().equals(infoBtn)) {
			//이용안내 버튼 클릭
			new Gui_info();
		}else if(e.getSource().equals(myPageBtn)) {
			//마이페이지 버튼 클릭
			new Gui_Mypage(mdto);
		}else if(e.getSource().equals(regCarBtn)) {
			//차량등록 버튼 클릭
			new Gui_Car(mdto);
		}else if(e.getSource().equals(rentalBtn)) {
			//렌탈버튼 클릭
			new Gui_Rental(mdto);
		}else if(e.getSource().equals(eventBtn)) {
			//이벤트 버튼 클릭
			new Gui_event(mdto);
		}else if(e.getSource().equals(reviewBtn)) {
			//리뷰버튼 클릭...인자값 넣어줘야함
			new Gui_review(mdto);
		}else if(e.getSource().equals(logoutBtn)) {
			//로그아웃버튼 클릭
			this.setVisible(false);
			new Gui_login();
		}
	}
}
