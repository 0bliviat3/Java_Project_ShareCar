package main;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//�ű�ȸ�����Ը� �ߴ� â ȯ���ǹ��� ���
public class Banner extends JFrame implements ItemListener{
	
	private JCheckBox box = new JCheckBox("�����Ϸ� �� â�� ���� �ʱ�");
	
	Banner(String nickname){
		JLabel title = new JLabel(nickname+"�� ȯ���մϴ�",JLabel.CENTER);
		JLabel memo = new JLabel("�ű�ȸ���� 1000point �����ص帳�ϴ�!",JLabel.CENTER);
		JLabel memo2 = new JLabel("���� �ٷ� myPage���� Ȯ���ϼ���!",JLabel.CENTER);
		
		Font titleFont = new Font("�޸յձ�������",Font.BOLD,30);

		JPanel centerp = new JPanel();
		
		centerp.setLayout(new GridLayout(2,1));
		memo.setFont(titleFont);
		memo2.setFont(titleFont);
		centerp.add(memo);
		centerp.add(memo2);
		title.setFont(titleFont);
		
		box.addItemListener(this);
		
		this.add("North",title);
		this.add("Center",centerp);
		this.add("South",box);
		
		this.setBounds(200,300,800,200);
		this.setVisible(true);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getItem().equals(box)) {
			//üũ�ڽ� üũ�� â����
			this.setVisible(false);
		}
	}
}
