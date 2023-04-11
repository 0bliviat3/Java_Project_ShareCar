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

import dao.MemberGradeWarningJoin_DAO;
import dao.Member_DAO;
import dto.MemberGradeWarningJoin_DTO;
import dto.Member_DTO;
//관리자모드의 회원관리모드
public class Gui_AdminMember extends JFrame implements TableCellRenderer,MouseListener{
	private JScrollPane memberScroll = null;
	private JTable memberTable = null;
	private MemberGradeWarningJoin_DAO mgwj = MemberGradeWarningJoin_DAO.getInstance();
	Gui_AdminMember(){
		JLabel title = new JLabel("회원관리모드",JLabel.CENTER);
		List<MemberGradeWarningJoin_DTO> memberList = mgwj.AllMember(); 
		String[] col = {"회원아이디","회원등급","경고내역","경고","정지","자세히보기"};
		Object[][] table = new Object[memberList.size()][col.length];
		for(int i=0;i<memberList.size();i++) {
			table[i][0] = memberList.get(i).getM_id();
			table[i][1] = memberList.get(i).getG_detail();
			table[i][2] = memberList.get(i).getW_detail();
		}
		memberTable = new JTable(table,col);
		memberTable.addMouseListener(this);
		memberTable.getColumn("경고").setCellRenderer(this);
		memberTable.getColumn("정지").setCellRenderer(this);
		memberTable.getColumn("자세히보기").setCellRenderer(this);
		memberScroll = new JScrollPane(memberTable);
		
		this.setBounds(200,200,600,300);
		this.add("North",title);
		this.add("Center",memberScroll);
		this.setVisible(true);
		
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component btn = null;
		if(column==3) {
			btn = new JButton("경고");
		}else if(column==4) {
			btn = new JButton("정지");
		}else if(column==5) {
			btn = new JButton("상세정보");
		}
		return btn;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Member_DAO member = Member_DAO.getInstance();
		int col = memberTable.getSelectedColumn();
		int row = memberTable.getSelectedRow();
		String m_id = memberTable.getModel().getValueAt(row,0).toString();
		switch (col) {
		case 3:
			//회원경고번호 1로 업데이트
			member.warning(m_id, 1);
			break;
		case 4:
			//회원경고번호 2로 업데이트
			member.warning(m_id, 2);
			break;
		case 5:
			//개별회원의 상세정보보기
			Member_DTO mdto = member.selectId(m_id);
			new Gui_AdminMemberDetail(mdto);
			break;
		default:
			break;
		}
		this.setVisible(false);
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
