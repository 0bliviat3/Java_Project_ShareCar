package main;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dao.Car_DAO;
import dto.Car_DTO;
//�����ڸ�忡�� ������ȸ
public class Gui_AdminCar extends JFrame{
	Gui_AdminCar(){
		this.setBounds(200,200,600,200);
		JLabel title = new JLabel("������ȸ",JLabel.CENTER);
		List<Car_DTO> carList = Car_DAO.getInstance().selectAll();
		String[] col = {"����","�����������","��������","��Ż���ɱⰣ","����","��ȣ�ǹ�ȣ","���̵�","��Ż��ȣ"};
		Object[][] table = new Object[carList.size()][col.length];
		for(int i=0;i<carList.size();i++) {
			table[i][0] = carList.get(i).getC_kind();
			table[i][1] = carList.get(i).getC_date().substring(0,10);
			table[i][2] = carList.get(i).getC_value();
			table[i][3] = carList.get(i).getC_term();
			table[i][4] = carList.get(i).getC_years();
			table[i][5] = carList.get(i).getC_num();
			table[i][6] = carList.get(i).getC_m_id();
			table[i][7] = carList.get(i).getC_no();
		}
		JTable carTable = new JTable(table,col);
		JScrollPane carScroll = new JScrollPane(carTable);
		this.add("North",title);
		this.add("Center",carScroll);
		this.setVisible(true);
	}
}
