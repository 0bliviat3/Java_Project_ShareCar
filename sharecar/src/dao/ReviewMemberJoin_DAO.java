package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.ReviewMemberJoin_DTO;

public class ReviewMemberJoin_DAO implements DAO{
	
	public static ReviewMemberJoin_DAO self = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	
	private ReviewMemberJoin_DAO() {
		driverLoad();
	}

	@Override
	public void driverLoad() {
		// TODO Auto-generated method stub
		load();
	}
	
	public static ReviewMemberJoin_DAO getInstance() {
		if(self == null) {
			self = new ReviewMemberJoin_DAO();
		}
		return self;
	}
	//전체 리뷰 리스트 불러오기...작성자 닉네임 포함
	public List<ReviewMemberJoin_DTO> selectAll(){
		List<ReviewMemberJoin_DTO> list = new ArrayList<>();
		String sql = "select r.*,m.m_nickname from review r join member m on r.rv_id = m.m_id";
		try {
			psmt = con().prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				ReviewMemberJoin_DTO r = new ReviewMemberJoin_DTO();
				r.setRv_no(rs.getInt(1));
				r.setRv_id(rs.getString(2));
				r.setRv_car(rs.getString(3));
				r.setRv_title(rs.getString(4));
				r.setRv_memo(rs.getString(5));
				r.setRv_date(rs.getString(6));
				r.setRv_views(rs.getInt(7));
				r.setM_nickname(rs.getString(8));
				list.add(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//특정 리뷰 하나만 불러오기 닉네임 포함
	public ReviewMemberJoin_DTO select(int rv_no) {
		ReviewMemberJoin_DTO rm = null;
		String sql = "select r.*,m.m_nickname from review r join member m on r.rv_id = m.m_id where r.rv_no = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setInt(1, rv_no);
			rs = psmt.executeQuery();
			rm = new ReviewMemberJoin_DTO();
			if(rs.next()) {
				rm.setRv_no(rs.getInt(1));
				rm.setRv_id(rs.getString(2));
				rm.setRv_car(rs.getString(3));
				rm.setRv_title(rs.getString(4));
				rm.setRv_memo(rs.getString(5));
				rm.setRv_date(rs.getString(6));
				rm.setRv_views(rs.getInt(7));
				rm.setM_nickname(rs.getString(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rm;
	}
	

}
