package main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//�����ڸ�� ����������
public class Gui_AdminMain extends JFrame implements ActionListener{
	private JButton member = null;
	private JButton car = null;
	private JButton rental = null;
	private JButton event = null;
	private JButton review = null;
	private JButton comment = null;
	
	Gui_AdminMain(){
		JLabel title = new JLabel("�����ڸ��",JLabel.CENTER);
		JPanel adminP = new JPanel();
		adminP.setLayout(new GridLayout(6,1));
		member = new JButton("ȸ����ȸ");
		car = new JButton("������ȸ");
		rental = new JButton("��Ż��ȸ");
		event = new JButton("�̺�Ʈ��ȸ");
		review = new JButton("������ȸ");
		comment = new JButton("�����ȸ");
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
			//ȸ����ȸ��ư
			new Gui_AdminMember();
		}else if(e.getSource().equals(car)) {
			//������ȸ��ư
			new Gui_AdminCar();
		}else if(e.getSource().equals(rental)) {
			//��Ż��ȸ��ư
			new Gui_AdminRental();
		}else if(e.getSource().equals(event)) {
			//�̺�Ʈ��ȸ��ư
			new Gui_AdminEvent();
		}else if(e.getSource().equals(review)) {
			//������ȸ��ư
			new Gui_AdminReview();
		}else if(e.getSource().equals(comment)) {
			//�����ȸ��ư
			new Gui_AdminComment();
		}
	}
}
