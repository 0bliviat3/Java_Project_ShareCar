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
		Font titleFont = new Font("궁서 보통",Font.BOLD,30);
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
			//로그인
			//관리자모드분류필요
			//회원에 따라서 리뷰나 댓글을 작성한 이력이 없는 회원은 환영배너 띄우기
			//경고번호 2번에 해당되는 회원은 로그인못하게 블라인드 처리
			id = idf.getText();
			pass = p.pwString(pwf.getPassword());
			if((id.equals("admin"))&&(pass.equals("admin1234!"))) {
				//관리자모드 접속
				new Gui_AdminMain();
				this.setVisible(false);
			}else {
				//회원의 정보를 불러오는 method
				mdto = member.selectIdPass(id, pass);
				if(mdto!=null) {
					//메인엔 mdto인자값으로 주고
					if(mdto.getM_w_no()==2) {
						//영구정지상태의 회원인경우
						idf.setText("영구정지상태입니다.");
						pwf.setText("");
					}else {
						new Gui_main(mdto);
						if(mcmj.selectNewMember(mdto)) {
							//배너엔 닉네임만 인자값으로 줄것.
							new Banner(mdto.getM_nickname());
						}		
						this.setVisible(false);
					}
				}else {
					//회원이 아닌경우
					idf.setText("입력한 정보가 틀립니다.");
					pwf.setText("");
				}
			}
		}else if(e.getSource().equals(signUp)) {
			//회원가입
			new SignUp01();
			this.setVisible(false);
		}
	}
}
