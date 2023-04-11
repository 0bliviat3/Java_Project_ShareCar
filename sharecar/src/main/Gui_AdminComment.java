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

import dao.My_comment_DAO;
import dto.My_comment_DTO;

public class Gui_AdminComment extends JFrame implements TableCellRenderer,MouseListener{
	
	private JTable commentTable;
	private My_comment_DAO comment = My_comment_DAO.getInstance();
	
	Gui_AdminComment(){
		JLabel title = new JLabel("댓글 조회",JLabel.CENTER);
		List<My_comment_DTO> commentList = comment.seletAll();
		String[] col = {"댓글번호","리뷰번호","아이디","댓글","작성일","추천수","삭제"};
		Object[][] table = new Object[commentList.size()][col.length];
		for(int i=0;i<commentList.size();i++) {
			table[i][0] = commentList.get(i).getCm_no();
			table[i][1] = commentList.get(i).getCm_rv_no();
			table[i][2] = commentList.get(i).getCm_id();
			table[i][3] = commentList.get(i).getCm_memo();
			table[i][4] = commentList.get(i).getCm_date().substring(0,10);
			table[i][5] = commentList.get(i).getCm_likes();
		}
		commentTable = new JTable(table,col);
		commentTable.getColumn("삭제").setCellRenderer(this);
		commentTable.addMouseListener(this);
		JScrollPane commentScroll = new JScrollPane(commentTable);
		this.setBounds(200,200,700,300);
		this.add("North",title);
		this.add("Center",commentScroll);
		this.setVisible(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component btn = null;
		if(column==6) {
			btn = new JButton("삭제");
		}
		return btn;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int col = commentTable.getSelectedColumn();
		int row = commentTable.getSelectedRow();
		int cm_no = (int)commentTable.getModel().getValueAt(row, 0);
		if(col==6) {
			//삭제버튼클릭했을때
			comment.deleteMyComment(cm_no);
			dispose();
			new Gui_AdminComment();
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
}
