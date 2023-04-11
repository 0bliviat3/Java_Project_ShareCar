package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.Member_DTO;

public class MemberGradeJoin_DAO implements DAO{

	public static MemberGradeJoin_DAO self = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	
	private MemberGradeJoin_DAO() {
		driverLoad();
	}
	
	public static MemberGradeJoin_DAO getInstance() {
		if(self == null) {
			self = new MemberGradeJoin_DAO();
		}
		return self;
	}
	
	@Override
	public void driverLoad() {
		// TODO Auto-generated method stub
		load();
	}
	
	//회원의 정보를 인자값으로 받아 등급리턴해주는 method
	public String selectGrade(Member_DTO mdto) {
		String sql = "select g.g_detail from member m,grade g where m.m_g_no = g.g_no and m.m_id = ?";
		String grade = null;
		try {
			psmt = con().prepareStatement(sql);
			psmt.setString(1, mdto.getM_id());
			rs = psmt.executeQuery();
			if(rs.next()) {
				grade = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grade;
	}
	

}
