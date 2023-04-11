package main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//관리자모드 메인페이지
public class Gui_AdminMain extends JFrame implements ActionListener{
	private JButton member = null;
	private JButton car = null;
	private JButton rental = null;
	private JButton event = null;
	private JButton review = null;
	private JButton comment = null;
	
	Gui_AdminMain(){
		JLabel title = new JLabel("관리자모드",JLabel.CENTER);
		JPanel adminP = new JPanel();
		adminP.setLayout(new GridLayout(6,1));
		member = new JButton("회원조회");
		car = new JButton("차량조회");
		rental = new JButton("렌탈조회");
		event = new JButton("이벤트조회");
		review = new JButton("리뷰조회");
		comment = new JButton("댓글조회");
		member.addActionListener(this);
		car.addActionListener(this);
		rental.addActionListener(this);
		event.addActionListener(this);
		review.addActionListener(this);
		comment.addActionListener(this);
		adminP.add(member);
		adminP.add(car);
		adminP.add(rental);
		adminP.add(event);
		adminP.add(review);
		adminP.add(comment);
		this.add("North",title);
		this.add("Center",adminP);
		this.setBounds(600,200,400,400);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(member)) {
			//회원조회버튼
			new Gui_AdminMember();
		}else if(e.getSource().equals(car)) {
			//차량조회버튼
			new Gui_AdminCar();
		}else if(e.getSource().equals(rental)) {
			//렌탈조회버튼
			new Gui_AdminRental();
		}else if(e.getSource().equals(event)) {
			//이벤트조회버튼
			new Gui_AdminEvent();
		}else if(e.getSource().equals(review)) {
			//리뷰조회버튼
			new Gui_AdminReview();
		}else if(e.getSource().equals(comment)) {
			//댓글조회버튼
			new Gui_AdminComment();
		}
	}
}
