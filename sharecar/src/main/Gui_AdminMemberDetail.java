package main;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dto.Member_DTO;

public class Gui_AdminMemberDetail extends JFrame{
	Gui_AdminMemberDetail(Member_DTO mdto){
		this.setBounds(200,200,300,400);
		
		JLabel title = new JLabel("ȸ������",JLabel.CENTER);
		getContentPane().add(title, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel id = new JLabel("id : "+mdto.getM_id());
		id.setBounds(12, 10, 121, 15);
		panel.add(id);
		
		JLabel name = new JLabel("�̸� : "+mdto.getM_name());
		name.setBounds(145, 10, 127, 15);
		panel.add(name);
		
		JLabel pass = new JLabel("��й�ȣ : "+mdto.getM_pass());
		pass.setBounds(12, 35, 260, 15);
		panel.add(pass);
		
		JLabel gender = new JLabel("���� : "+mdto.getM_gender());
		gender.setBounds(12, 60, 121, 15);
		panel.add(gender);
		
		JLabel age = new JLabel("���� : "+mdto.getM_age());
		age.setBounds(145, 60, 139, 15);
		panel.add(age);
		
		JLabel nickname = new JLabel("�г��� : "+mdto.getM_nickname());
		nickname.setBounds(12, 85, 121, 15);
		panel.add(nickname);
		
		JLabel point = new JLabel("point : "+mdto.getM_point());
		point.setBounds(145, 85, 127, 15);
		panel.add(point);
		
		JLabel email = new JLabel("email : "+mdto.getM_email());
		email.setBounds(12, 110, 260, 15);
		panel.add(email);
		
		JLabel call = new JLabel("��ȭ��ȣ : "+mdto.getM_phone());
		call.setBounds(12, 135, 260, 15);
		panel.add(call);
		
		JLabel carLicense = new JLabel("���������� : "+mdto.getM_car_license());
		carLicense.setBounds(12, 160, 260, 15);
		panel.add(carLicense);
		
		JLabel addr = new JLabel("�ּ� : "+mdto.getM_addr());
		addr.setBounds(12, 185, 260, 15);
		panel.add(addr);
		
		JLabel addrDetail = new JLabel("���ּ� : "+mdto.getM_near_addr());
		addrDetail.setBounds(12, 210, 260, 15);
		panel.add(addrDetail);
		
		JLabel account = new JLabel("���� : "+mdto.getM_bank()+" "+mdto.getM_account());
		account.setBounds(12, 235, 260, 15);
		panel.add(account);
		
		JLabel warning = new JLabel("����ȣ : "+mdto.getM_w_no());
		warning.setBounds(12, 260, 121, 15);
		panel.add(warning);
		
		JLabel grade = new JLabel("��޹�ȣ : "+mdto.getM_g_no());
		grade.setBounds(145, 260, 127, 15);
		panel.add(grade);
		this.setVisible(true);
	}
}
