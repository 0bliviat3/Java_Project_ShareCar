package main;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dto.Member_DTO;

public class Gui_AdminMemberDetail extends JFrame{
	Gui_AdminMemberDetail(Member_DTO mdto){
		this.setBounds(200,200,300,400);
		
		JLabel title = new JLabel("회원정보",JLabel.CENTER);
		getContentPane().add(title, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel id = new JLabel("id : "+mdto.getM_id());
		id.setBounds(12, 10, 121, 15);
		panel.add(id);
		
		JLabel name = new JLabel("이름 : "+mdto.getM_name());
		name.setBounds(145, 10, 127, 15);
		panel.add(name);
		
		JLabel pass = new JLabel("비밀번호 : "+mdto.getM_pass());
		pass.setBounds(12, 35, 260, 15);
		panel.add(pass);
		
		JLabel gender = new JLabel("성별 : "+mdto.getM_gender());
		gender.setBounds(12, 60, 121, 15);
		panel.add(gender);
		
		JLabel age = new JLabel("나이 : "+mdto.getM_age());
		age.setBounds(145, 60, 139, 15);
		panel.add(age);
		
		JLabel nickname = new JLabel("닉네임 : "+mdto.getM_nickname());
		nickname.setBounds(12, 85, 121, 15);
		panel.add(nickname);
		
		JLabel point = new JLabel("point : "+mdto.getM_point());
		point.setBounds(145, 85, 127, 15);
		panel.add(point);
		
		JLabel email = new JLabel("email : "+mdto.getM_email());
		email.setBounds(12, 110, 260, 15);
		panel.add(email);
		
		JLabel call = new JLabel("전화번호 : "+mdto.getM_phone());
		call.setBounds(12, 135, 260, 15);
		panel.add(call);
		
		JLabel carLicense = new JLabel("운전면허증 : "+mdto.getM_car_license());
		carLicense.setBounds(12, 160, 260, 15);
		panel.add(carLicense);
		
		JLabel addr = new JLabel("주소 : "+mdto.getM_addr());
		addr.setBounds(12, 185, 260, 15);
		panel.add(addr);
		
		JLabel addrDetail = new JLabel("상세주소 : "+mdto.getM_near_addr());
		addrDetail.setBounds(12, 210, 260, 15);
		panel.add(addrDetail);
		
		JLabel account = new JLabel("계좌 : "+mdto.getM_bank()+" "+mdto.getM_account());
		account.setBounds(12, 235, 260, 15);
		panel.add(account);
		
		JLabel warning = new JLabel("경고번호 : "+mdto.getM_w_no());
		warning.setBounds(12, 260, 121, 15);
		panel.add(warning);
		
		JLabel grade = new JLabel("등급번호 : "+mdto.getM_g_no());
		grade.setBounds(145, 260, 127, 15);
		panel.add(grade);
		this.setVisible(true);
	}
}
