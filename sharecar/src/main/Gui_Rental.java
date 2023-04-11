package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dao.RentalJoin_DAO;
import dto.Member_DTO;
import dto.RentalJoin_DTO;

//렌탈가능한 차량리스트에서 선택하는 창
//마우스로 튜플선택하고 선택버튼 누르면 해당튜플선택
public class Gui_Rental extends JFrame implements MouseListener,ActionListener{
	private JTable table;
	private JButton choiceBtn = null;
	private Member_DTO mdto = null;
	private int carChoice = -1;
	public Gui_Rental(Member_DTO mdto) {
		this.mdto = mdto;
		
		RentalJoin_DAO rentaljoin = RentalJoin_DAO.getInstance();
		
		this.setBounds(200,200,900,400);
		
		JLabel title = new JLabel("렌탈가능한 차량",JLabel.CENTER);
		getContentPane().add(title, BorderLayout.NORTH);
		
		choiceBtn = new JButton("선택");
		getContentPane().add(choiceBtn, BorderLayout.SOUTH);
		choiceBtn.addActionListener(this);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		List<RentalJoin_DTO> list = rentaljoin.selectRental(mdto);
		String[] col = {"차종","차량등록일자","차량상태","렌탈가능기간","연식","번호판번호","렌탈지역","차주닉네임","렌탈번호"};
		Object[][] rantalTable = new Object[list.size()][col.length];
		for(int i = 0; i<list.size();i++) {
			rantalTable[i][0] = list.get(i).getJ_kind();
			rantalTable[i][1] = list.get(i).getJ_date().substring(0, 10);
			rantalTable[i][2] = list.get(i).getJ_value();
			rantalTable[i][3] = list.get(i).getJ_term();
			rantalTable[i][4] = list.get(i).getJ_years();
			rantalTable[i][5] = list.get(i).getJ_num();
			rantalTable[i][6] = list.get(i).getJ_addr();
			rantalTable[i][7] = list.get(i).getJ_nickname();
			rantalTable[i][8] = list.get(i).getJ_no();
		}
		table = new JTable(rantalTable,col);
		table.addMouseListener(this);
		scrollPane.setViewportView(table);
		this.setVisible(true);
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		int col = 8;//rental pk
//		System.out.println(table.getModel().getValueAt(row, col));
		carChoice = (int) table.getModel().getValueAt(row, col);
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(choiceBtn)) {
			//상세정보 기입하는 창으로 이동
			new Gui_rental2(mdto,carChoice);
			this.setVisible(false);
		}
	}
}
