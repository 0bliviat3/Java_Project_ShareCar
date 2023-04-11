package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.Review_DAO;
import dto.Member_DTO;
import dto.Review_DTO;

public class Gui_review2 extends JFrame implements ActionListener{
	private JTextField titlef;
	private JTextField kindf;
	private JScrollPane scrollPane;
	private JTextArea memoA;
	private JButton regBtn;
	private Member_DTO mdto = null;
	public Gui_review2(Member_DTO mdto) {
		this.mdto = mdto;
		this.setBounds(200,200,500,400);
		getContentPane().setLayout(null);
		
		JLabel title = new JLabel("리뷰작성",JLabel.CENTER);
		title.setBounds(189, 10, 93, 28);
		getContentPane().add(title);
		
		JLabel rvTitle = new JLabel("제목",JLabel.CENTER);
		rvTitle.setBounds(12, 40, 69, 28);
		getContentPane().add(rvTitle);
		
		titlef = new JTextField();
		titlef.setBounds(93, 44, 336, 21);
		getContentPane().add(titlef);
		titlef.setColumns(10);
		
		JLabel carKind = new JLabel("차종",JLabel.CENTER);
		carKind.setBounds(12, 78, 69, 28);
		getContentPane().add(carKind);
		
		kindf = new JTextField();
		kindf.setBounds(93, 82, 158, 21);
		getContentPane().add(kindf);
		kindf.setColumns(10);
		
		JLabel memo = new JLabel("내용",JLabel.CENTER);
		memo.setBounds(12, 116, 69, 28);
		getContentPane().add(memo);
		memoA = new JTextArea();
		scrollPane = new JScrollPane(memoA);
		scrollPane.setBounds(93, 124, 328, 167);
		getContentPane().add(scrollPane);
		
		regBtn = new JButton("등록");
		regBtn.addActionListener(this);
		regBtn.setBounds(185, 328, 97, 23);
		getContentPane().add(regBtn);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Review_DTO rdto = new Review_DTO();
		Review_DAO review = Review_DAO.getInstance();
		if(e.getSource().equals(regBtn)) {
			//리뷰 등록 버튼
			rdto.setRv_id(mdto.getM_id());
			rdto.setRv_car(kindf.getText());
			rdto.setRv_title(titlef.getText());
			rdto.setRv_memo(memoA.getText());
			review.insert(rdto);
			this.setVisible(false);
		}
	}
}
