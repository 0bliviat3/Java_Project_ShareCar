package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import dao.MemberGradeJoin_DAO;
import dao.Member_DAO;
import dao.My_comment_DAO;
import dao.RentalJoin_DAO;
import dao.Review_DAO;
import dto.Member_DTO;
import dto.My_comment_DTO;
import dto.RentalJoin_DTO;
import dto.Review_DTO;
import passCheck.PassChk;
import testcode.CmTableCellRenderer;
import testcode.RvTableCellRenderer;

//����������
public class Gui_Mypage extends JFrame implements ActionListener,MouseListener{
	
	private Review_DAO reviewdao = Review_DAO.getInstance();
	private My_comment_DAO myComment = My_comment_DAO.getInstance();
	//���信���� Ŭ������,��ۿ����� Ŭ������ �����ϱ� ���� flagNum
	private int flagNum = 0;
	
	//�޴��г�
	private JPanel menuPanel = new JPanel();
	
	private JButton mod = new JButton("��������");
	private JButton use = new JButton("���� �̿���Ȳ");
	private JButton point = new JButton("my point");
	private JButton review = new JButton("���� �Խñ�");
	private JButton comment = new JButton("���� ���");
	private JButton delete = new JButton("ȸ��Ż��");
	
	//�������� �г�
	private JPanel modPanel = new JPanel();
	
	private JPasswordField pwf = new JPasswordField(15);
	private JTextField emailf = new JTextField(10);
	private JTextField callf = new JTextField(10);
	private JTextField addrf = new JTextField(15);
	private JTextField nearAddrf = new JTextField(15);
	private JTextField accountf = new JTextField(15);
	private JComboBox mailBox = null;
	private JComboBox callBox = null;
	private JComboBox bankBox = null;
	private JButton modBtn = new JButton("�����Ϸ�");
	
	//�����Ȳ �г�
	private JPanel usePanel = new JPanel();
	private JButton rental = new JButton("�̿����� ��Ż");
	private JButton mycar = new JButton("������� ����");
	private JPanel usePanel1 = new JPanel();
	private JPanel usePanel2 = new JPanel();
	private JTable myRental = null;
	private JScrollPane myRentalTableScroll = null;
	private JTable myCartable = null;
	private JScrollPane mycarTableScroll = null;
	
	private Member_DTO mdto = null;
	
	//Ż�� �г�
	private JPanel m_delete = new JPanel();
	private JPasswordField lastPwf = new JPasswordField();
	private JButton deleteBtn = new JButton("ȸ�� Ż��");
	
	//���� ���� �г�
	private JPanel reviewP = new JPanel();
	private JScrollPane reviewScroll = null;
	private JTable reviewTable = null;
	private DefaultTableCellRenderer rvRenderer = null;
	private JPanel rvModP = new JPanel();
	private JTextField rv_carf = null;
	private JTextField rv_title = null;
	private JTextArea rv_memo = null;
	private JScrollPane rv_memo_scroll = null;
	private JButton rvModBtn = null;
	private Review_DTO rdto = null;
	
	//���� ��� �г�
	private JPanel commentP = new JPanel();
	private JScrollPane commentScroll = null;
	private JTable commentTable = null;
	private DefaultTableCellRenderer cmRenderer = null;
	
	//���� ����Ʈ �г�
	private JPanel pointP = new JPanel();
	private JLabel pointNicknameL = null;
	private JLabel pointPointL = null;
	private JLabel pointGradeL = null;
	private JLabel pointWarningL = null;
	private JButton pointEventBtn = null;
	
	Gui_Mypage(Member_DTO mdto) {
		
		this.mdto = mdto;
		
		//�����ӻ�ܿ� ����
		JLabel title = new JLabel("mypage",JLabel.CENTER);
		Font titleFont = new Font("�޸յձ�������",Font.BOLD,30);
		Color titleColor = new Color(0x794B0082,true);
		
		//�޴��г�
		//�гλ��
		JLabel subTitle = new JLabel("menu",JLabel.CENTER);
		
		menuPanel.setLayout(new GridLayout(7,1));
		menuPanel.add(subTitle);
		menuPanel.add(mod);
		menuPanel.add(use);
		menuPanel.add(point);
		menuPanel.add(review);
		menuPanel.add(comment);
		menuPanel.add(delete);
		
		title.setFont(titleFont);
		title.setForeground(titleColor);
		
		mod.addActionListener(this);
		use.addActionListener(this);
		point.addActionListener(this);
		review.addActionListener(this);
		comment.addActionListener(this);
		delete.addActionListener(this);
		
		//�����г�
		modPanel.setLayout(new GridLayout(7,1));
		JLabel modTitle = new JLabel("��������",JLabel.CENTER);
		
		JLabel pw = new JLabel("��й�ȣ");
		JLabel email = new JLabel("�̸���");
		JLabel call = new JLabel("��ȭ��ȣ");
		JLabel addr = new JLabel("�ּ�");
		JLabel nearAddr = new JLabel("���ּ�");
		JLabel account = new JLabel("����");
		
		JPanel pwLine = new JPanel();
		JPanel emailLine = new JPanel();
		JPanel callLine = new JPanel();
		JPanel addrLine = new JPanel();
		JPanel nearAddrLine = new JPanel();
		JPanel accountLine = new JPanel();
		
		String[] emailKind = {"@naver.com","@gmail.com","@hanmail.net","@icloud.com","@kakao.com"};
		String[] callKind = {"010","011","016","017","019"};
		String[] bankKind = {"���","����","����","�ϳ�","����","����","�츮"};
		
		modPanel.add(modTitle);
		pwLine.add(pw);
		pwLine.add(pwf);
		modPanel.add(pwLine);
		emailLine.add(email);
		emailLine.add(emailf);
		mailBox = new JComboBox(emailKind);
		emailLine.add(mailBox);
		modPanel.add(emailLine);
		callLine.add(call);
		callBox = new JComboBox(callKind);
		callLine.add(callBox);
		callLine.add(callf);
		modPanel.add(callLine);
		addrLine.add(addr);
		addrLine.add(addrf);
		modPanel.add(addrLine);
		nearAddrLine.add(nearAddr);
		nearAddrLine.add(nearAddrf);
		modPanel.add(nearAddrLine);
		accountLine.add(account);
		bankBox = new JComboBox(bankKind);
		accountLine.add(bankBox);
		accountLine.add(accountf);
		modPanel.add(accountLine);
		
		modBtn.addActionListener(this);
		
		//Ż���г�
		JLabel goodBye = new JLabel("Ż�� ���� ��й�ȣ�� �Է��ϼ���.");
		m_delete.setLayout(new GridLayout(3,1));
		m_delete.add(goodBye);
		m_delete.add(lastPwf);
		m_delete.add(deleteBtn);
		
		deleteBtn.addActionListener(this);
		
		//�̿���Ȳ�г�
		JLabel mycarL = new JLabel("���� ��ϵ� ����",JLabel.CENTER);
		JLabel myRental = new JLabel("���� ��Ż�� ����",JLabel.CENTER);
		usePanel.setLayout(new GridLayout(2,1));
		usePanel.add(rental);
		usePanel.add(mycar);
		rental.addActionListener(this);
		mycar.addActionListener(this);
		//���� ������� ���� �г�
		usePanel2.setLayout(new BorderLayout());
		usePanel2.add("North",mycarL);
		//���� ��Ż����
		usePanel1.setLayout(new BorderLayout());
		usePanel1.add("North",myRental);
		
		//���� ���� �г�
		JLabel rvTitle = new JLabel("���� ����");
		reviewP.setLayout(new BorderLayout());
		reviewP.add("North",rvTitle);
		rvModP.setLayout(null);
		JLabel rvMod = new JLabel("���� ���� ����",JLabel.CENTER);
		rvMod.setBounds(189, 10, 93, 28);
		rvModP.add(rvMod);
		JLabel rvModTitle = new JLabel("����",JLabel.CENTER);
		rvModTitle.setBounds(12, 40, 69, 28);
		rvModP.add(rvModTitle);
		rv_title = new JTextField();
		rv_title.setBounds(93, 44, 250, 21);
		rvModP.add(rv_title);
		rv_title.setColumns(10);
		JLabel rvModCar = new JLabel("����",JLabel.CENTER);
		rvModCar.setBounds(12, 78, 69, 28);
		rvModP.add(rvModCar);
		rv_carf = new JTextField();
		rv_carf.setBounds(93, 82, 158, 21);
		rvModP.add(rv_carf);
		rv_carf.setColumns(10);
		JLabel rvModMemo = new JLabel("����",JLabel.CENTER);
		rvModMemo.setBounds(12, 116, 69, 28);
		rvModP.add(rvModMemo);
		rv_memo = new JTextArea();
		rv_memo_scroll = new JScrollPane(rv_memo);
		rv_memo_scroll.setBounds(93, 124, 250, 167);
		rvModP.add(rv_memo_scroll);
		rvModBtn = new JButton("���");
		rvModBtn.addActionListener(this);
		rvModBtn.setBounds(185, 328, 97, 23);
		rvModP.add(rvModBtn);
		 
		//���� ����г�
		JLabel cmTitle = new JLabel("���� ���");
		commentP.setLayout(new BorderLayout());
		commentP.add("North",cmTitle);
		
		//���� ����Ʈ �г�
		MemberGradeJoin_DAO mgdao = MemberGradeJoin_DAO.getInstance();
		pointP.setLayout(new GridLayout(5,1));
		pointNicknameL = new JLabel(mdto.getM_nickname()+" ��");
		pointGradeL = new JLabel("�������� : "+mgdao.selectGrade(mdto));//�����ؼ� ��ް�������
		pointPointL = new JLabel("���� ����Ʈ : "+mdto.getM_point());
		pointWarningL = new JLabel("���Ƚ��"+mdto.getM_w_no()+"ȸ");
		pointEventBtn = new JButton("��÷����Ȯ��");
		pointEventBtn.addActionListener(this);
		pointP.add(pointNicknameL);
		pointP.add(pointGradeL);
		pointP.add(pointPointL);
		pointP.add(pointWarningL);
		pointP.add(pointEventBtn);
		
		
		
		this.add("Center",menuPanel);
		this.add("North",title);
		this.setBounds(150,150,400,600);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Member_DAO member = Member_DAO.getInstance();
		RentalJoin_DAO rentalJoin = RentalJoin_DAO.getInstance();
		
		PassChk p = PassChk.getInstance();
		if(e.getSource().equals(mod)) {
			//����
			this.remove(menuPanel);
			int idx = mdto.getM_email().indexOf("@");
			emailf.setText(mdto.getM_email().substring(0, idx));
			mailBox.setSelectedItem(mdto.getM_email().substring(idx));
			callBox.setSelectedItem(mdto.getM_phone().substring(0, 3));
			callf.setText(mdto.getM_phone().substring(3));
			addrf.setText(mdto.getM_addr());
			nearAddrf.setText(mdto.getM_near_addr());
			bankBox.setSelectedItem(mdto.getM_bank());
			accountf.setText(mdto.getM_account());
			this.add("Center",modPanel);
			this.add("South",modBtn);
			this.setVisible(true);
		}else if(e.getSource().equals(modBtn)) {
			//�����Ϸ� ��ư�� ������ ���
			char[] pass = pwf.getPassword();
			if(p.pwValue(pass)) {
				if(p.pwLength(pass)) {
					mdto.setM_pass(p.pwString(pass));
					mdto.setM_email(emailf.getText()+(mailBox.getSelectedItem().toString()));
					mdto.setM_phone(callBox.getSelectedItem().toString()+callf.getText());
					mdto.setM_addr(addrf.getText());
					mdto.setM_near_addr(nearAddrf.getText());
					mdto.setM_account(accountf.getText());
					mdto.setM_bank(bankBox.getSelectedItem().toString());
					member.mod(mdto);
					this.setVisible(false);
				}else {
					modBtn.setText("��й�ȣ�� 8�ڸ� �̻��Դϴ�.");
				}
			}else {
				modBtn.setText("��й�ȣ�� Ư���� ���� �մϴ�.");
			}
			
		}else if(e.getSource().equals(use)) {
			//�����Ȳ��ư
			this.remove(menuPanel);
			this.add("Center",usePanel);
			this.setVisible(true);
		}else if(e.getSource().equals(rental)) {
			//���� ��Ż�� ����
			List<RentalJoin_DTO> rentalList = rentalJoin.myPageLental(mdto);
			String[] col = {"����","������","����","����ȣ","��Ż�Ⱓ","��Ż����"};
			Object[][] table = new Object [rentalList.size()][col.length];
			for(int i=0;i<rentalList.size();i++) {
				table[i][0] = rentalList.get(i).getJ_kind();
				table[i][1] = rentalList.get(i).getJ_value();
				table[i][2] = rentalList.get(i).getJ_years();
				table[i][3] = rentalList.get(i).getJ_num();
				table[i][4] = rentalList.get(i).getJ_r_date();
				table[i][5] = rentalList.get(i).getJ_r_addr();
			}
			myRental = new JTable(table, col);
			myRentalTableScroll = new JScrollPane(myRental);
			usePanel1.add("Center",myRentalTableScroll);
			this.remove(usePanel);
			this.add("Center",usePanel1);
			this.setVisible(true);
		}else if(e.getSource().equals(mycar)) {
			//���� ������� | ��Ż �������� ǥ�����ָ� ������
			List<RentalJoin_DTO> carList = rentalJoin.myCar(mdto);
			String[] col = {"����","����� ��","������ȣ","����","������","��ϱⰣ","��Ż�ѻ��(�г���)"};
			Object[][] table = new Object [carList.size()][col.length];
			for(int i=0;i<carList.size();i++) {
				table[i][0] = carList.get(i).getJ_kind();
				table[i][1] = carList.get(i).getJ_date().substring(0, 7);
				table[i][2] = carList.get(i).getJ_num();
				table[i][3] = carList.get(i).getJ_years();
				table[i][4] = carList.get(i).getJ_value();
				table[i][5] = carList.get(i).getJ_term();
				table[i][6] = carList.get(i).getJ_r_nickname();
			}
			myCartable = new JTable(table, col);
			mycarTableScroll = new JScrollPane(myCartable);
			usePanel2.add("Center",mycarTableScroll);
			this.remove(usePanel);
			this.add("Center",usePanel2);
			this.setVisible(true);
		}else if(e.getSource().equals(point)) {
			//������ư
			//���� ��ް� ����Ʈ, ���Ƚ��, ��÷�� �������ִٸ� ������ȣ���
			this.remove(menuPanel);
			this.add("Center",pointP);
			this.setVisible(true);
		}else if(e.getSource().equals(this.review)) {
			//�����ư
			//flagNum 
			flagNum = 1;
			//���� �ۼ��� ���� ���̺��� ����,������ư Ŭ������
			//���̺��� �����ȣ,����,�ۼ�����,����,����
			rvRenderer = new RvTableCellRenderer();
//			System.out.println(10);
			List<Review_DTO> myreviewList = reviewdao.selectMyReview(mdto);
//			System.out.println(20);
			String[] col = {"�����ȣ","�ۼ�����","����","����","����"};
			Object[][] table = new Object[myreviewList.size()][col.length];
//			System.out.println(30);
			for(int i=0;i<myreviewList.size();i++) {
				table[i][0] = myreviewList.get(i).getRv_no();
				table[i][1] = myreviewList.get(i).getRv_date().substring(0,10);
				table[i][2] = myreviewList.get(i).getRv_title();
			}
//			System.out.println(0);
			reviewTable = new JTable(table,col);
			reviewTable.getColumn("����").setCellRenderer(rvRenderer);
//			System.out.println(1);
			reviewTable.getColumn("����").setCellRenderer(rvRenderer);
//			System.out.println(2);
			reviewScroll = new JScrollPane(reviewTable);
			reviewP.add("Center",reviewScroll);
			reviewTable.addMouseListener(this);
			this.remove(menuPanel);
			this.add("Center",reviewP);
			this.setVisible(true);
		}else if(e.getSource().equals(comment)) {
			//��۹�ư
			//flagNum
			flagNum = 2;
			//����� ���������� �����
			cmRenderer = new CmTableCellRenderer();
			List<My_comment_DTO> myCommentList = myComment.selectMyComment(mdto);
			String[] col = {"��۹�ȣ","�ۼ�����","����","����"};
			Object[][] table = new Object[myCommentList.size()][col.length];
			for(int i=0;i<myCommentList.size();i++) {
				table[i][0] = myCommentList.get(i).getCm_no();
				table[i][1] = myCommentList.get(i).getCm_date();
				table[i][2] = myCommentList.get(i).getCm_memo();
			}
			commentTable = new JTable(table,col);
			commentTable.getColumn("����").setCellRenderer(cmRenderer);
			commentScroll = new JScrollPane(commentTable);
			commentTable.addMouseListener(this);
			commentP.add("Center",commentScroll);
			this.remove(menuPanel);
			this.add("Center",commentP);
			this.setVisible(true);
		}else if(e.getSource().equals(delete)) {
			//Ż���ư
			this.remove(menuPanel);
			this.add("Center",m_delete);
			this.setVisible(true);
		}else if(e.getSource().equals(deleteBtn)) {
			char[] pass = lastPwf.getPassword();
			String chkPw = p.pwString(pass);
			if(chkPw.equals(mdto.getM_pass())) {
				member.delete(mdto);
				this.setVisible(false);
				System.exit(0);
			}else {
				deleteBtn.setText("��й�ȣ�� Ʋ���ϴ�.");
			}
		}else if(e.getSource().equals(rvModBtn)) {
			//���������ư
			rdto.setRv_car(rv_carf.getText());
			rdto.setRv_title(rv_title.getText());
			rdto.setRv_memo(rv_memo.getText());
			reviewdao.updateReview(rdto);
			this.setVisible(false);
		}else if(e.getSource().equals(pointEventBtn)) {
			//��÷�������� ��ư
			//��÷�������� ������ ��ü����
			new Gui_WinHistory(mdto);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//���� ����,���� Ŭ�� �׼�
		if(flagNum == 1) {
			int col = reviewTable.getSelectedColumn();
			int row = reviewTable.getSelectedRow();
			if(col==3) {
				//����Ŭ��
				rdto = new Review_DTO();
				int rv_no = (int)reviewTable.getModel().getValueAt(row, 0);
				rdto.setRv_no(rv_no);
				rdto.setRv_id(mdto.getM_id());
				rdto = reviewdao.selectMyReview(rdto);
				rv_carf.setText(rdto.getRv_car());
				rv_title.setText(rdto.getRv_title());
				rv_memo.setText(rdto.getRv_memo());
				this.remove(reviewP);
				this.add("Center",rvModP);
				this.setVisible(true);
			}else if(col==4) {
				//����Ŭ��
				rdto = new Review_DTO();
				int rv_no = (int)reviewTable.getModel().getValueAt(row, 0);
				rdto.setRv_no(rv_no);
				rdto.setRv_id(mdto.getM_id());
				reviewdao.deleteReview(rdto);
				//���ΰ�ħ �ʿ�
				dispose();
				new Gui_Mypage(mdto);
//				reviewP.setVisible(true);
			}
		}else if(flagNum == 2) {
			//��� ���� Ŭ�� �׼�
			int col = commentTable.getSelectedColumn();
			int row = commentTable.getSelectedRow();
			if(col==3) {
				//����Ŭ��
				My_comment_DTO mcdto = new My_comment_DTO();
				int cm_no = (int)commentTable.getModel().getValueAt(row, 0);
				mcdto.setCm_no(cm_no);
				mcdto.setCm_id(mdto.getM_id());
				myComment.deleteMyComment(cm_no);
				dispose();
				new Gui_Mypage(mdto);
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
