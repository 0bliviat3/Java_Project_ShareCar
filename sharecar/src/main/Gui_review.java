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

import dao.ReviewMemberJoin_DAO;
import dao.Review_DAO;
import dto.Member_DTO;
import dto.ReviewMemberJoin_DTO;
import dto.Review_DTO;

//렌탈을 이용한 사람이 리뷰를 작성하는 페이지
//리뷰 테이블에서 리뷰를 클릭하면 해당 리뷰 자세히 보기로 넘어감
public class Gui_review extends JFrame implements ActionListener,MouseListener{
	
	private JScrollPane reviewScroll = null;
	private JTable reviewTable = null;
	private JButton write = null;
	private ReviewMemberJoin_DAO review = ReviewMemberJoin_DAO.getInstance();
	private Member_DTO mdto = null;
	
	public Gui_review(Member_DTO mdto) {
		this.mdto = mdto;
		
		this.setBounds(200,200,400,400);
		
		JPanel northPanel = new JPanel();
		getContentPane().add(northPanel, BorderLayout.NORTH);
		JLabel title = new JLabel("리뷰",JLabel.CENTER);
		
		List<ReviewMemberJoin_DTO> reviewList = review.selectAll();
		String[] col = {"리뷰번호","작성일","닉네임","제목","조회수"};
		Object[][] table = new Object[reviewList.size()][col.length];
		for(int i=0;i<reviewList.size();i++) {
			table[i][0] = reviewList.get(i).getRv_no();
			table[i][1] = reviewList.get(i).getRv_date().substring(0,10);
			table[i][2] = reviewList.get(i).getM_nickname();
			table[i][3] = reviewList.get(i).getRv_title();
			table[i][4] = reviewList.get(i).getRv_views();
		}
		reviewTable = new JTable(table, col);
		reviewTable.addMouseListener(this);
//		reviewTable.setEnabled(false);
		reviewScroll = new JScrollPane(reviewTable);
		
		
		write = new JButton("리뷰 작성");
		northPanel.setLayout(new BorderLayout());
		northPanel.add("West",title);
		write.addActionListener(this);
		northPanel.add("East",write);
		
		this.add("Center",reviewScroll);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(write)) {
			//리뷰 작성 버튼
			new Gui_review2(mdto);
			this.setVisible(false);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Review_DAO review = Review_DAO.getInstance();
		int rv_no = -1;
		int rv_views = 0;
		int row = reviewTable.getSelectedRow();
//		System.out.println(100);
		Review_DTO r = new Review_DTO();
		rv_no = (int) reviewTable.getModel().getValueAt(row, 0);
		rv_views = (int) reviewTable.getModel().getValueAt(row, 4);
		r.setRv_no(rv_no);
		r.setRv_views(rv_views);
		//리뷰 조회수 업데이트
		review.updateView(r);
//		this.setVisible(true);
		this.setVisible(false);
		new Gui_review(mdto);
		//리뷰상세보기 창 띄움
		new Gui_review3(mdto,rv_no);
//		this.setVisible(true);
//		reviewTable.setVisible(true);
//		if(e.getClickCount()>1) {
//			
//		}
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
