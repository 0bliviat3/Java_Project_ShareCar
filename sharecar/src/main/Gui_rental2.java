package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.Member_DAO;
import dao.Rental_DAO;
import dto.Member_DTO;
import dto.Rental_DTO;
//��Ż ������ �����ϱ�
//��Ż�� �ּҿ�, ��Ż�� �Ⱓ �Է�
public class Gui_rental2 extends JFrame implements ActionListener{
	
	private JButton cash = new JButton("����");
	private JTextField addrf = new JTextField();
	private JTextField datef = new JTextField();
	private Member_DTO mdto = null;
	private int r_no = -1;

	
	Gui_rental2(Member_DTO mdto,int carChoice){
		
		this.mdto = mdto;
		r_no = carChoice;
		
		JLabel title = new JLabel("��Ż��������",JLabel.CENTER);
		JLabel addr = new JLabel("��Ż�� �ּ�");
		JLabel date = new JLabel("��Ż�� �Ⱓ");
		
		JPanel centerP = new JPanel();
		
//		centerP.add(addr);
//		centerP.add(addrf);
//		centerP.add(date);
//		centerP.add(datef);
		
		getContentPane().add("Center",centerP);
		centerP.setLayout(null);
		

		addr.setBounds(12, 35, 78, 15);
		centerP.add(addr);
		

		date.setBounds(12, 75, 78, 15);
		centerP.add(date);
		
		addrf.setBounds(115, 32, 146, 21);
		centerP.add(addrf);
		addrf.setColumns(10);
		
		datef.setBounds(115, 72, 146, 21);
		centerP.add(datef);
		datef.setColumns(10);
		getContentPane().add("North",title);
		getContentPane().add("South",cash);
		
		cash.addActionListener(this);
		
		this.setBounds(200,200,300,200);
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Rental_DAO rental = Rental_DAO.getInstance();
		Member_DAO member = Member_DAO.getInstance();
		// TODO Auto-generated method stub
		if(e.getSource().equals(cash)) {
			//��ư������ db�� �����ϰ� ȭ������
			Rental_DTO r = new Rental_DTO();
			r.setR_user_id(mdto.getM_id());
			r.setR_addr(addrf.getText());
			r.setR_no(r_no);
			r.setR_date(Integer.valueOf(datef.getText()));
			rental.update(r);
			//1000����Ʈ ����
			//�̶� ������ ����Ʈ������ ��� ����
			member.point(mdto);
			int point = mdto.getM_point()+1000;
			if(point>=5000) {
				//5000�̻��϶��� ������Ʈ
				member.updateGrade(mdto);
			}
			this.setVisible(false);
		}
	}
}
