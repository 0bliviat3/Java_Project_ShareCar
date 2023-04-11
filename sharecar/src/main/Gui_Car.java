package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.Car_DAO;
import dao.Rental_DAO;
import dto.Car_DTO;
import dto.Member_DTO;
//차량등록 창 차량 등록할때 자동으로 렌탈테이블에도 등록되야한다.
public class Gui_Car extends JFrame implements ActionListener{
	private JTextField carNumf;
	private JTextField carYearsf;
	private JTextField carKindf;
	private JTextField carTermf;
	private JComboBox valueBox;
	private JButton carBtn;
	private Member_DTO mdto = null;
	
	public Gui_Car(Member_DTO mdto) {
		this.mdto = mdto;
		
		this.setBounds(200,200,400,500);
		getContentPane().setLayout(null);
		
		JLabel title = new JLabel("차량등록",JLabel.CENTER);
		title.setBounds(0, 0, 384, 55);
		getContentPane().add(title);
		
		JLabel carNum = new JLabel("차량번호");
		carNum.setBounds(10, 65, 57, 15);
		getContentPane().add(carNum);
		
		carNumf = new JTextField();
		carNumf.setBounds(126, 65, 233, 21);
		getContentPane().add(carNumf);
		carNumf.setColumns(10);
		
		JLabel carYears = new JLabel("연식");
		carYears.setBounds(12, 121, 57, 15);
		getContentPane().add(carYears);
		
		carYearsf = new JTextField();
		carYearsf.setBounds(126, 118, 233, 21);
		getContentPane().add(carYearsf);
		carYearsf.setColumns(10);
		
		JLabel carKind = new JLabel("차종");
		carKind.setBounds(12, 181, 57, 15);
		getContentPane().add(carKind);
		
		carKindf = new JTextField();
		carKindf.setBounds(126, 178, 233, 21);
		getContentPane().add(carKindf);
		carKindf.setColumns(10);
		
		JLabel carValue = new JLabel("차 상태");
		carValue.setBounds(12, 230, 57, 15);
		getContentPane().add(carValue);
		
		String[] value = {"상","중","하"};
		
		valueBox = new JComboBox(value);
		valueBox.setBounds(126, 227, 57, 21);
		getContentPane().add(valueBox);
		
		JLabel carTerm = new JLabel("등록 기간");
		carTerm.setBounds(10, 286, 57, 15);
		getContentPane().add(carTerm);
		
		carTermf = new JTextField();
		carTermf.setBounds(126, 283, 233, 21);
		getContentPane().add(carTermf);
		carTermf.setColumns(10);
		
		carBtn = new JButton("차량 등록");
		carBtn.setBounds(0, 403, 384, 48);
		getContentPane().add(carBtn);
		
		carBtn.addActionListener(this);
		
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Car_DAO car = Car_DAO.getInstance();
		Car_DTO cdto = new Car_DTO();
		Rental_DAO rental = Rental_DAO.getInstance();
		// TODO Auto-generated method stub
		if(e.getSource().equals(carBtn)) {
			//차량등록
			cdto.setC_num(carNumf.getText());
			cdto.setC_years(Integer.valueOf(carYearsf.getText()));
			cdto.setC_term(Integer.valueOf(carTermf.getText()));
			cdto.setC_kind(carKindf.getText());
			cdto.setC_value(valueBox.getSelectedItem().toString());
			car.insert(cdto, mdto);
			cdto = car.rentalInsert(mdto);
			rental.insert(cdto);
			this.setVisible(false);
		}
	}
}
