package main;

import java.awt.BorderLayout;
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
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.My_commentMemberJoin_DAO;
import dao.My_comment_DAO;
import dao.ReviewMemberJoin_DAO;
import dto.Member_DTO;
import dto.My_commentMemberJoin_DTO;
import dto.My_comment_DTO;
import dto.ReviewMemberJoin_DTO;
import testcode.MyTableCellRenderer;
//����󼼺��� ���ڰ����� �󼼺� ������ �����ȣ�� ���� ������� ������ ����
public class Gui_review3 extends JFrame implements ActionListener,MouseListener{
	private JTextArea commentA;
	private JButton commentBtn;
	private JTable commentTable;
	private Object[][] table;
	private My_commentMemberJoin_DAO com = My_commentMemberJoin_DAO.getInstance();
	private Member_DTO mdto = null;
	private int rv_no = 0;
	private My_comment_DAO mcdao = My_comment_DAO.getInstance();
	private My_comment_DTO mcdto = null;
	
	Gui_review3(Member_DTO mdto,int rv_no){
		this.mdto = mdto;
		this.rv_no = rv_no;
		
		
		ReviewMemberJoin_DAO rmj = ReviewMemberJoin_DAO.getInstance();
		ReviewMemberJoin_DTO rmjdto = rmj.select(rv_no);
		
		this.setBounds(150,150,500,600);
		
		JLabel title = new JLabel("���� �󼼺���",JLabel.CENTER);
		getContentPane().add(title, BorderLayout.NORTH);
		
		JPanel centerP = new JPanel();
		getContentPane().add(centerP, BorderLayout.CENTER);
		centerP.setLayout(null);
		
		JPanel reviewP = new JPanel();
		reviewP.setBounds(12, 0, 460, 308);
		centerP.add(reviewP);
		reviewP.setLayout(null);
		
		JLabel writer = new JLabel("�ۼ���");
		writer.setBounds(12, 10, 89, 22);
		reviewP.add(writer);
		
		JLabel date = new JLabel(rmjdto.getRv_date().substring(0,16));
		date.setBounds(216, 10, 115, 22);
		reviewP.add(date);
		
		JLabel nickname = new JLabel(rmjdto.getM_nickname());
		nickname.setBounds(113, 10, 91, 22);
		reviewP.add(nickname);
		
		JLabel view = new JLabel("��ȸ��");
		view.setBounds(349, 10, 57, 22);
		reviewP.add(view);
		
		JLabel viewNum = new JLabel(String.valueOf(rmjdto.getRv_views()));
		viewNum.setBounds(422, 10, 26, 22);
		reviewP.add(viewNum);
		
		JLabel rvTitle = new JLabel(rmjdto.getRv_title());
		rvTitle.setBounds(12, 42, 252, 22);
		reviewP.add(rvTitle);
		
		JLabel rvMemo = new JLabel(rmjdto.getRv_memo());
		rvMemo.setBounds(12, 74, 436, 224);
		reviewP.add(rvMemo);
		
		commentA = new JTextArea();
		
		JScrollPane commetScroll = new JScrollPane(commentA);
		commetScroll.setBounds(12, 342, 460, 52);
		centerP.add(commetScroll);
		
		JLabel comment = new JLabel("���");
		comment.setBounds(12, 318, 57, 15);
		centerP.add(comment);
		
		commentBtn = new JButton("����Է�");
		commentBtn.setBounds(375, 404, 97, 23);
		commentBtn.addActionListener(this);
		centerP.add(commentBtn);
		
		JPanel commentP = new JPanel();
		commentP.setBounds(12, 442, 460, 94);
		centerP.add(commentP);
		commentP.setLayout(null);
		DefaultTableCellRenderer myRenderer = new MyTableCellRenderer();
		//��ۺҷ��ö� ����Խñ��� ��۸� �Ҿ�;��Ѵ�
		List<My_commentMemberJoin_DTO> commentList = com.selectCommentList(rv_no);
		String[] col = {"�г���","�ۼ���","���","��õ��","��õ�ϱ�","��۹�ȣ"};
		table = new Object[commentList.size()][col.length];
		for(int i =0; i<commentList.size();i++) {
			table[i][0] = commentList.get(i).getM_nickname();
			table[i][1] = commentList.get(i).getCm_date().substring(0,10);
			table[i][2] = commentList.get(i).getCm_memo();
			table[i][3] = commentList.get(i).getCm_likes();
			table[i][5] = commentList.get(i).getCm_no();
		}
		DefaultTableModel model = new DefaultTableModel(table,col);
		commentTable = new JTable(model);
		commentTable.getColumn("��õ�ϱ�").setCellRenderer(myRenderer);
		commentTable.addMouseListener(this);
//		commentTable.setEnabled(false);
		
		JScrollPane commentListScroll = new JScrollPane(commentTable);
		commentListScroll.setBounds(12, 10, 436, 74);
		commentP.add(commentListScroll);
		
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(commentBtn)) {
			//�Է��� ��� ����
			mcdto = new My_comment_DTO();
			mcdto.setCm_rv_no(rv_no);
			mcdto.setCm_id(mdto.getM_id());
			mcdto.setCm_memo(commentA.getText());
			mcdao.insert(mcdto);
			this.setVisible(false);
			new Gui_review3(mdto,rv_no);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//��� ��õ�ϱ� ���
		int col = commentTable.getSelectedColumn();
		int row = commentTable.getSelectedRow();
		if(col==4) {
			//��õ�ϱ⸦ ��������
			//������ ���� ��۹�ȣ�� �޾ƿͼ� �ش� ����� DTO�� ���Ϲ޾ƿ�
			int cm_no = (int)commentTable.getModel().getValueAt(row, 5);
			mcdto = mcdao.select(cm_no);
			mcdao.update(mcdto);
			//â ���ΰ�ħ
			this.setVisible(false);
			new Gui_review3(mdto,rv_no);
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
