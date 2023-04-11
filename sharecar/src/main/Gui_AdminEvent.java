package main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import dao.Event_DAO;
import dto.Event_DTO;

//�����ڸ���� �̺�Ʈ���
public class Gui_AdminEvent extends JFrame implements TableCellRenderer,ActionListener,MouseListener{
	private JTable eventTable;
	private JButton createBtn;
	private Event_DAO event = Event_DAO.getInstance();
	Gui_AdminEvent(){
		this.setBounds(200,200,500,300);
		
		JLabel title = new JLabel("�̺�Ʈ���",JLabel.CENTER);
		getContentPane().add(title, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 460, 161);
		panel.add(scrollPane);
		
		List<Event_DTO> eventList = event.select();
		String[] col = {"�̺�Ʈ��ȣ","�̺�Ʈ","������ȣ","��÷�ھ��̵�","�̺�Ʈ����"};
		Object[][] table = new Object[eventList.size()][col.length];
		for(int i=0;i<eventList.size();i++) {
			table[i][0] = eventList.get(i).getE_no();
			table[i][1] = eventList.get(i).getE_name();
			table[i][2] = eventList.get(i).getE_coupon();
			table[i][3] = eventList.get(i).getE_id();
		}
		
		eventTable = new JTable(table,col);
		eventTable.addMouseListener(this);
		eventTable.getColumn("�̺�Ʈ����").setCellRenderer(this);
		scrollPane.setViewportView(eventTable);
		
		createBtn = new JButton("�̺�Ʈ����");
		createBtn.addActionListener(this);
		createBtn.setBounds(346, 213, 126, 23);
		panel.add(createBtn);
		this.setVisible(true);
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component btn = null;
		if(column==4) {
			btn = new JButton("����");
		}
		return btn;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(createBtn)) {
			//�̺�Ʈ ������ư ���������
			dispose();
			new Gui_AdminEvent2();
			
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int col = eventTable.getSelectedColumn();
		int row = eventTable.getSelectedRow();
		int e_no = (int)eventTable.getModel().getValueAt(row, 0);
		if(col==4) {
			//������ư Ŭ���������
			event.delete(e_no);
			dispose();
			new Gui_AdminEvent();
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
