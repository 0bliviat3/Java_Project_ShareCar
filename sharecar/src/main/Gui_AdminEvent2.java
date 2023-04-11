package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.Event_DAO;

public class Gui_AdminEvent2 extends JFrame implements ActionListener{
	private JTextField name;
	private JButton createBtn;
	Gui_AdminEvent2(){
		JLabel title = new JLabel("�����Ϸ��� �̺�Ʈ ���� �Է��ϼ���.",JLabel.CENTER);
		JPanel centerP = new JPanel();
		name = new JTextField(20);
		centerP.add(name);
		createBtn = new JButton("�̺�Ʈ ����");
		createBtn.addActionListener(this);
		this.setTitle("�̺�Ʈ ����");
		this.setBounds(200,200,300,200);
		this.add("North",title);
		this.add("Center",centerP);
		this.add("South",createBtn);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(createBtn)) {
			Event_DAO.getInstance().insert(name.getText());
			dispose();
		}
	}
}
