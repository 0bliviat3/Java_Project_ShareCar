package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.Member_DAO;
import dto.Member_DTO;
import passCheck.PassChk;

public class SignUp01 extends JFrame implements ActionListener{
	
	private Member_DAO member = Member_DAO.getInstance();
	//중복체크 확인용 카운트
	private int chkCnt = 0;
	
	private JLabel title = new JLabel("Sign up",JLabel.CENTER);
	//버튼을 누르면 page1 panel에서 page2 panel로 이동
	private JPanel page1 = new JPanel();
	private JPanel southP = new JPanel();

	private JTextField idf = new JTextField(15);
	private JTextField namef = new JTextField(15);
	private JPasswordField pwf = new JPasswordField(15);
	private JPasswordField rePwf = new JPasswordField(15);
	private JTextField agef = new JTextField(5);
	private JTextField nicknamef = new JTextField(15);
	private JButton idChkBtn = new JButton("아이디 중복 확인");
	private JButton nicknameChkBtn = new JButton("닉네임 중복 확인");
	private JComboBox genderBox = null;
	//회원가입 안내 메세지 라벨
	private JLabel info = new JLabel("",JLabel.CENTER);
	
	private JButton next = new JButton("다음");
	
	
	//page2
	private JPanel page2 = new JPanel();
	
	private JTextField emailf = new JTextField(10);
	private JTextField callf = new JTextField(10);
	private JTextField licensef = new JTextField(15);
	private JTextField addrf = new JTextField(15);
	private JTextField nearAddrf = new JTextField(15);
	private JTextField accountf = new JTextField(15);
	
	private JComboBox mailBox = null;
	private JComboBox callBox = null;
	private JComboBox bankBox = null;
	
	private JButton sign = new JButton("회원가입");
	
	
	//넘길값들
	private String id = null;
	private String nickname = null;
	private String name = null;
	private String realpass = null;
	private int age = 0;
	private String gender = null;
	
	
	SignUp01(){
		
		Font titleFont = new Font("궁서 보통",Font.BOLD,30);
		
		//page1
		JLabel id = new JLabel("아이디");
		JLabel pw = new JLabel("비밀번호(특문포함 8자리이상)");
		JLabel name = new JLabel("이름");
		JLabel rePw = new JLabel("비밀번호 확인");
		JLabel gender = new JLabel("성별");
		JLabel age = new JLabel("나이");
		JLabel nickname = new JLabel("닉네임");
		
		JPanel idLine = new JPanel();
		JPanel nameLine = new JPanel();
		JPanel pwLine = new JPanel();
		JPanel rePwLine = new JPanel();
		JPanel genderLine = new JPanel();
		JPanel nicknameLine = new JPanel();
		
		String[] genderKind = {"m","w"};
		
		//page2
		JLabel email = new JLabel("이메일");
		JLabel call = new JLabel("전화번호");
		JLabel license = new JLabel("운전면허");
		JLabel addr = new JLabel("주소");
		JLabel nearAddr = new JLabel("상세주소");
		JLabel account = new JLabel("계좌");
		
		JPanel emailLine = new JPanel();
		JPanel callLine = new JPanel();
		JPanel licenseLine = new JPanel();
		JPanel addrLine = new JPanel();
		JPanel nearAddrLine = new JPanel();
		JPanel accountLine = new JPanel();
		
		String[] emailKind = {"@naver.com","@gmail.com","@hanmail.net","@icloud.com","@kakao.com"};
		String[] callKind = {"010","011","016","017","019"};
		String[] bankKind = {"기업","국민","농협","하나","신협","수협","우리"};
		
		//page1
		title.setFont(titleFont);
		page1.setLayout(new GridLayout(7,1));		
		
		idLine.add(id);
		idLine.add(idf);
		idLine.add(idChkBtn);
		page1.add(idLine);
		nameLine.add(name);
		nameLine.add(namef);
		page1.add(nameLine);
		pwLine.add(pw);
		pwLine.add(pwf);
		page1.add(pwLine);
		rePwLine.add(rePw);
		rePwLine.add(rePwf);
		page1.add(rePwLine);
		genderLine.add(gender);
		
		genderBox = new JComboBox(genderKind);
		genderLine.add(genderBox);
		genderLine.add(age);
		genderLine.add(agef);
		page1.add(genderLine);
		nicknameLine.add(nickname);
		nicknameLine.add(nicknamef);
		nicknameLine.add(nicknameChkBtn);
		page1.add(nicknameLine);
		page1.add(info);
		
		next.addActionListener(this);
		southP.add(next);
		
		//page2
		page2.setLayout(new GridLayout(6,1));
		
		emailLine.add(email);
		emailLine.add(emailf);
		mailBox = new JComboBox(emailKind);
		emailLine.add(mailBox);
		page2.add(emailLine);
		callLine.add(call);
		callBox = new JComboBox(callKind);
		callLine.add(callBox);
		callLine.add(callf);
		page2.add(callLine);
		licenseLine.add(license);
		licenseLine.add(licensef);
		page2.add(licenseLine);
		addrLine.add(addr);
		addrLine.add(addrf);
		page2.add(addrLine);
		nearAddrLine.add(nearAddr);
		nearAddrLine.add(nearAddrf);
		page2.add(nearAddrLine);
		accountLine.add(account);
		bankBox = new JComboBox(bankKind);
		accountLine.add(bankBox);
		accountLine.add(accountf);
		page2.add(accountLine);
		
		sign.addActionListener(this);
		
		idChkBtn.addActionListener(this);
		nicknameChkBtn.addActionListener(this);
		
		this.setBounds(100,100,400,600);
		this.add("South",southP);
		this.add("Center",page1);
		this.add("North",title);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		Member_DTO m = new Member_DTO();
		PassChk p = PassChk.getInstance();
		info.setForeground(Color.red);
		
		// TODO Auto-generated method stub
		if(e.getSource().equals(idChkBtn)) {
			//아이디 중복체크
			String tempId = idf.getText();
			System.out.println(tempId);
//			System.out.println(1);
			if(member.selectID(tempId)) {
				//중복인경우
				info.setText("중복되는 아이디 입니다.");
			}else {
//				m.setM_id(tempId);
				id=tempId;
				chkCnt=1;
				info.setText("사용가능한 아이디 입니다.");
//				System.out.println(2);
			}
		}else if(e.getSource().equals(nicknameChkBtn)) {
			//닉네임 중복체크
			String tempNickname = nicknamef.getText();
			if(member.selectNickname(tempNickname)) {
				info.setText("중복되는 닉네임 입니다.");
			}else {
//				m.setM_nickname(tempNickname);
				nickname = tempNickname;
				chkCnt=2;
				info.setText("사용가능한 닉네임 입니다.");
			}
		}
		//중복체크했는지 체크하고
		if(chkCnt==2) {
			if(e.getSource().equals(next)) {
				//비밀번호 특문체크
				char[] pass = pwf.getPassword();
				if(p.pwValue(pass)) {
					if(p.pwLength(pass)) {
						String password = p.pwString(pass);
						String rePassword = p.pwString(rePwf.getPassword());
						//입력한 비밀번호가 두번다 일치하는지 체크
						if(password.equals(rePassword)) {
//							m.setM_pass(password);
							realpass = password;
//							m.setM_name(namef.getText());
							name = namef.getText();
//							m.setM_age(Integer.valueOf(agef.getText()));
							age = Integer.valueOf(agef.getText());
//							m.setM_gender(genderBox.getSelectedItem().toString());
							gender = genderBox.getSelectedItem().toString();
							//다음 페이지로 이동
//							this.remove(page1);
							page1.setVisible(false);
							this.add("West",page2);
							southP.remove(next);
							southP.add(sign);
							this.setVisible(true);
						}else {
							info.setText("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
						}
					}else {
						info.setText("비밀번호는 8자리 이상입력해야 합니다.");
					}
				}else {
					info.setText("특수문자 하나이상 넣어주세요.");
				}
			}else if(e.getSource().equals(sign)) {
				//회원가입 완료시 로그인화면으로 복귀
				m.setM_id(id);
				m.setM_name(name);
				m.setM_pass(realpass);
				m.setM_age(age);
				m.setM_gender(gender);
				m.setM_nickname(nickname);
				m.setM_email(emailf.getText()+(mailBox.getSelectedItem().toString()));
				m.setM_phone(callBox.getSelectedItem().toString()+callf.getText());
				m.setM_car_license(Integer.valueOf(licensef.getText()));
				m.setM_addr(addrf.getText());
				m.setM_near_addr(nearAddrf.getText());
				m.setM_account(accountf.getText());
				m.setM_bank(bankBox.getSelectedItem().toString());
				member.insert(m);
				new Gui_login();
				this.setVisible(false);
			}
		}
	}
}
