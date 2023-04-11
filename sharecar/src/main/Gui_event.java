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

import coupon.RandomString;
import dao.Event_DAO;
import dao.Member_DAO;
import dto.Event_DTO;
import dto.Member_DTO;
//이벤트 페이지
public class Gui_event extends JFrame implements TableCellRenderer,MouseListener{
	private JTable eventTable = null;
	private JScrollPane eventScroll = null;
	private Event_DAO event = Event_DAO.getInstance();
	private Member_DAO member = Member_DAO.getInstance();
	private Member_DTO mdto = null;
	private JLabel hiddenL = new JLabel("");
	
	Gui_event(Member_DTO mdto){
		this.mdto = mdto;
		
		this.setBounds(200,200,300,250);
		JLabel title = new JLabel("이벤트",JLabel.CENTER);
		List<Event_DTO> eventList = event.selectAll();
		String[] col = {"이벤트번호","이벤트 이름","참가"};
		Object[][] table = new Object[eventList.size()][col.length];
		for(int i=0;i<eventList.size();i++) {
			table[i][0] = eventList.get(i).getE_no();
			table[i][1] = eventList.get(i).getE_name();
		}
		eventTable = new JTable(table,col);
		eventTable.getColumn("참가").setCellRenderer(this);
		eventTable.addMouseListener(this);
		eventScroll = new JScrollPane(eventTable);
		
		
		this.add("North",title);
		this.add("Center",eventScroll);
		this.add("South",hiddenL);
		this.setVisible(true);
		
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component attendBtn = null;
		if(column==2) {
			attendBtn = new JButton("참가");
		}
		return attendBtn;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		RandomString r = RandomString.getInstance();
		int col = eventTable.getSelectedColumn();
		int row = eventTable.getSelectedRow();
		if(col==2) {
			//참가버튼을 누른시점
			int e_no = (int)eventTable.getModel().getValueAt(row, 0);
			//회원의 포인트 확인
			if(mdto.getM_point()>=1000) {
				//1000point 차감
				mdto = member.usePoint(mdto);
				//회원의 등급 업데이트
				member.updateGrade(mdto);
				//난수생성
				String myCoupon = r.coupon();
				//난수와 쿠폰번호가 일치하는지 확인
				if(myCoupon.equals(event.couponChk(e_no))) {
					//일치할경우 당첨처리후 event 테이블 update
					//인자값으로 회원아이디와 이벤트 번호를 받아야한다.
					event.win(mdto, e_no);
					new Gui_event2(true);
				}else {
					//불일치할경우 꽝
					new Gui_event2(false);
				}
				dispose();
				
			}else {
				hiddenL.setText("포인트가 부족합니다.");
				this.setVisible(true);
			}
			
		}
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
}
