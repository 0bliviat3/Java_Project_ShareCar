package main;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dao.Event_DAO;
import dto.Event_DTO;
import dto.Member_DTO;

public class Gui_WinHistory extends JFrame{
	Gui_WinHistory(Member_DTO mdto){
		Event_DAO event = Event_DAO.getInstance();
		this.setBounds(200,200,300,200);
		List<Event_DTO> eventList = event.select(mdto);
		String[] col = {"이벤트번호","이벤트","쿠폰번호"};
		Object[][] table = new Object[eventList.size()][col.length];
		for(int i=0;i<eventList.size();i++) {
			table[i][0] = eventList.get(i).getE_no();
			table[i][1] = eventList.get(i).getE_name();
			table[i][2] = eventList.get(i).getE_coupon();
		}
		JTable eventTable = new JTable(table,col);
		JScrollPane eventScroll = new JScrollPane(eventTable);
		JLabel title = new JLabel("나의 당첨내역",JLabel.CENTER);
		this.add("North",title);
		this.add("Center",eventScroll);
		this.setVisible(true);
	}
}
