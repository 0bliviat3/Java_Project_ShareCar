package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Member_DTO;
import dto.My_commentMemberJoin_DTO;

public class My_commentMemberJoin_DAO implements DAO{
	
	public static My_commentMemberJoin_DAO self = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;

	@Override
	public void driverLoad() {
		// TODO Auto-generated method stub
		load();
	}
	
	public static My_commentMemberJoin_DAO getInstance() {
		if(self == null) {
			self = new My_commentMemberJoin_DAO();
		}
		return self;
	}
	//댓글 리스트 불러오기 현재 게시글의 댓글만 불러올수있게 인자값넘겨줘야함
	public List<My_commentMemberJoin_DTO> selectCommentList(int rv_no){
		List<My_commentMemberJoin_DTO> list = new ArrayList<>();
		String sql = "select m.m_nickname,cm.cm_date,cm.cm_memo,cm.cm_likes,cm.cm_no from my_comment cm join member m on cm.cm_id = m.m_id where cm.cm_rv_no = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setInt(1, rv_no);
			rs = psmt.executeQuery();
			while(rs.next()) {
				My_commentMemberJoin_DTO m = new My_commentMemberJoin_DTO();
				m.setM_nickname(rs.getString(1));
				m.setCm_date(rs.getString(2));
				m.setCm_memo(rs.getString(3));
				m.setCm_likes(rs.getInt(4));
				m.setCm_no(rs.getInt(5));
				list.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//닉네임으로 댓글불러오기
	public My_commentMemberJoin_DTO select(String nickname) {
		My_commentMemberJoin_DTO mcdto = null;
		String sql = "select cm.* from my_comment cm join member m on cm.cm_id = m.m_id where m.m_nickname = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setString(1, nickname);
			rs = psmt.executeQuery();
			if(rs.next()) {
				mcdto = new My_commentMemberJoin_DTO();
				mcdto.setCm_no(rs.getInt(1));
				mcdto.setCm_rv_no(rs.getInt(2));
				mcdto.setCm_id(rs.getString(3));
				mcdto.setCm_memo(rs.getString(4));
				mcdto.setCm_date(rs.getString(5));
				mcdto.setCm_likes(rs.getInt(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mcdto;
	}
	//신규회원찾는 쿼리
	public boolean selectNewMember(Member_DTO mdto) {
		String sql = "select m.* from member m left outer join my_comment c on m.m_id = c.cm_id where cm_no is null and m.m_point = 1000 and m_id = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setString(1, mdto.getM_id());
			rs = psmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

}
