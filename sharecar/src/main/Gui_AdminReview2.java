package main;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dto.Review_DTO;

public class Gui_AdminReview2 extends JFrame{
	Gui_AdminReview2(Review_DTO rdto){
		this.setBounds(200,200,400,450);
		
		JLabel title = new JLabel("리뷰 상세보기",JLabel.CENTER);
		getContentPane().add(title, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel rvTitle = new JLabel("제목 : "+rdto.getRv_title());
		rvTitle.setBounds(12, 10, 360, 40);
		panel.add(rvTitle);
		
		JLabel rvCar= new JLabel("차종 : "+rdto.getRv_car());
		rvCar.setBounds(12, 60, 360, 27);
		panel.add(rvCar);
		
		JLabel rvReview = new JLabel("리뷰");
		rvReview.setBounds(12, 117, 75, 27);
		panel.add(rvReview);
		
		JLabel rvMemo = new JLabel(rdto.getRv_memo());
		rvMemo.setBounds(12, 154, 360, 205);
		panel.add(rvMemo);
		this.setVisible(true);
	}
}
