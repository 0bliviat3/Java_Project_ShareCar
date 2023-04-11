package main;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import dao.Review_DAO;
import dto.Review_DTO;

public class Gui_AdminReview extends JFrame implements MouseListener,TableCellRenderer{
	private JTable reviewTable;
	private Review_DAO review = Review_DAO.getInstance();
	
	Gui_AdminReview(){
		JLabel title = new JLabel("������ȸ",JLabel.CENTER);
		List<Review_DTO> reviewList = review.selectAll();
		String[] col = {"�����ȣ","���̵�","�ۼ���","����","��ȸ��","�󼼺���","����"};
		Object[][] table = new Object[reviewList.size()][col.length];
		for(int i=0;i<reviewList.size();i++) {
			table[i][0] = reviewList.get(i).getRv_no();
			table[i][1] = reviewList.get(i).getRv_id();
			table[i][2] = reviewList.get(i).getRv_date().substring(0,10);
			table[i][3] = reviewList.get(i).getRv_title();
			table[i][4] = reviewList.get(i).getRv_views();
		}
		reviewTable = new JTable(table,col);
		reviewTable.addMouseListener(this);
		reviewTable.getColumn("�󼼺���").setCellRenderer(this);
		reviewTable.getColumn("����").setCellRenderer(this);
		JScrollPane reviewScroll = new JScrollPane(reviewTable);
		this.setBounds(200,200,650,300);
		this.add("North",title);
		this.add("Center",reviewScroll);
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int col = reviewTable.getSelectedColumn();
		int row = reviewTable.getSelectedRow();
		int rv_no = (int)reviewTable.getModel().getValueAt(row, 0);
		switch (col) {
		case 5:
			//�󼼺���
			Review_DTO rdto = review.select(rv_no);
			dispose();
			new Gui_AdminReview2(rdto);
			break;
		case 6:
			//����
			review.delete(rv_no);
			dispose();
			new Gui_AdminReview();
			break;
		default:
			break;
		}
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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component btn = null;
		if(column==5) {
			//�󼼺���
			btn = new JButton("�󼼺���");
		}else if(column==6) {
			//����
			btn = new JButton("����");
		}
		return btn;
	}
}
