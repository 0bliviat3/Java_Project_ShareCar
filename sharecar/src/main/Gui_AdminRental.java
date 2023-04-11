package main;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dao.Rental_DAO;
import dto.Rental_DTO;

public class Gui_AdminRental extends JFrame{
	Gui_AdminRental(){
		JLabel title = new JLabel("·»Å»Á¶È¸");
		List<Rental_DTO> rentalList = Rental_DAO.getInstance().selectAll();
		String[] col = {"·»Å»¹øÈ£","Â÷·®µî·Ï¹øÈ£","Â÷ÁÖ¾ÆÀÌµð","·»Å»ÀÚ¾ÆÀÌµð","·»Å»±â°£","·»Å»ÁÖ¼Ò"};
		Object[][] table = new Object[rentalList.size()][col.length];
		for(int i=0;i<rentalList.size();i++) {
			table[i][0] = rentalList.get(i).getR_no();
			table[i][1] = rentalList.get(i).getR_c_no();
			table[i][2] = rentalList.get(i).getR_owner_id();
			table[i][3] = rentalList.get(i).getR_user_id();
			table[i][4] = rentalList.get(i).getR_date();
			table[i][5] = rentalList.get(i).getR_addr();
		}
		JTable carTable = new JTable(table,col);
		JScrollPane carScroll = new JScrollPane(carTable);
		
		this.setBounds(200,200,600,200);
		this.add("North",title);
		this.add("Center",carScroll);
		this.setVisible(true);
	}

}
