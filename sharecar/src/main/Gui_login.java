package main;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.Member_DAO;
import dao.My_commentMemberJoin_DAO;
import dto.Member_DTO;
import passCheck.PassChk;

public class Gui_login extends JFrame implements ActionListener{
	private JLabel title = new JLabel("ShareCar");
	private JLabel id = new JLabel("ID");
	private JLabel pw = new JLabel("password");
	private JTextField idf = new JTextField(15);
	private JPasswordField pwf = new JPasswordField(15);
	private JButton signIn = new JButton("sign in");
	private JButton signUp = new JButton("sign up");
	
	
	Gui_login(){
		this.setBounds(100,100,400,200);
		Font titleFont = new Font("�ü� ����",Font.BOLD,30);
		title.setFont(titleFont);
		title.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel centerP = new JPanel();
		centerP.setLayout(new GridLayout(2,2));
		JPanel idP = new JPanel();
		JPanel idTP = new JPanel();
		JPanel pwP = new JPanel();
		JPanel pwTP = new JPanel();
		idP.add(id);
		idTP.add(idf);
		pwP.add(pw);
		pwf.setEchoChar('*');
		pwTP.add(pwf);
		centerP.add(idP);
		centerP.add(idTP);
		centerP.add(pwP);
		centerP.add(pwTP);
		
		JPanel southP = new JPanel();
		southP.setLayout(new GridLayout(2,1));
		southP.add(signIn);
		southP.add(signUp);
		
		this.add("South",southP);
		this.add("Center",centerP);
		this.add("North",title);
		
		signIn.addActionListener(this);
		signUp.addActionListener(this);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String id = null;
		String pass = null;
		PassChk p = PassChk.getInstance();
		Member_DAO member = Member_DAO.getInstance();
		My_commentMemberJoin_DAO mcmj = My_commentMemberJoin_DAO.getInstance();
		Member_DTO mdto = null;
		if(e.getSource().equals(signIn)) {
			//�α���
			//�����ڸ��з��ʿ�
			//ȸ���� ���� ���䳪 ����� �ۼ��� �̷��� ���� ȸ���� ȯ����� ����
			//����ȣ 2���� �ش�Ǵ� ȸ���� �α��θ��ϰ� ����ε� ó��
			id = idf.getText();
			pass = p.pwString(pwf.getPassword());
			if((id.equals("admin"))&&(pass.equals("admin1234!"))) {
				//�����ڸ�� ����
				new Gui_AdminMain();
				this.setVisible(false);
			}else {
				//ȸ���� ������ �ҷ����� method
				mdto = member.selectIdPass(id, pass);
				if(mdto!=null) {
					//���ο� mdto���ڰ����� �ְ�
					if(mdto.getM_w_no()==2) {
						//�������������� ȸ���ΰ��
						idf.setText("�������������Դϴ�.");
						pwf.setText("");
					}else {
						new Gui_main(mdto);
						if(mcmj.selectNewMember(mdto)) {
							//��ʿ� �г��Ӹ� ���ڰ����� �ٰ�.
							new Banner(mdto.getM_nickname());
						}		
						this.setVisible(false);
					}
				}else {
					//ȸ���� �ƴѰ��
					idf.setText("�Է��� ������ Ʋ���ϴ�.");
					pwf.setText("");
				}
			}
		}else if(e.getSource().equals(signUp)) {
			//ȸ������
			new SignUp01();
			this.setVisible(false);
		}
	}
}
