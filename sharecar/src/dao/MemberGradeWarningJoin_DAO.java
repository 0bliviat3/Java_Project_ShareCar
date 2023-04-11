package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.MemberGradeWarningJoin_DTO;

public class MemberGradeWarningJoin_DAO implements DAO{
	public static MemberGradeWarningJoin_DAO self = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	
	private MemberGradeWarningJoin_DAO() {
		driverLoad();
	}
	/**
	 * 
	 * @return 싱글톤기법사용을 위해 생성자 리턴
	 */
	public static MemberGradeWarningJoin_DAO getInstance() {
		if(self == null) {
			self = new MemberGradeWarningJoin_DAO();
		}
		return self;
	}

	@Override
	public void driverLoad() {
		load();
		
	}
	/**
	 * 
	 * @return 모든회원의 등급,경고정보까지 갖는 리스트를 리턴합니다.
	 */
	public List<MemberGradeWarningJoin_DTO> AllMember(){
		List<MemberGradeWarningJoin_DTO> list = new ArrayList<>();
		String sql = "select m.*,g.*,w.* from member m,grade g,warning w where m.m_g_no = g.g_no and m.m_w_no = w.w_no";
		try {
			psmt = con().prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				MemberGradeWarningJoin_DTO m = new MemberGradeWarningJoin_DTO();
				m.setM_id(rs.getString(1));
				m.setM_name(rs.getString(2));
				m.setM_pass(rs.getString(3));
				m.setM_gender(rs.getString(4));
				m.setM_age(rs.getInt(5));
				m.setM_nickname(rs.getString(6));
				m.setM_point(rs.getInt(7));
				m.setM_email(rs.getString(8));
				m.setM_phone(rs.getString(9));
				m.setM_car_license(rs.getInt(10));
				m.setM_addr(rs.getString(11));
				m.setM_near_addr(rs.getString(12));
				m.setM_account(rs.getString(13));
				m.setM_bank(rs.getString(14));
				m.setM_w_no(rs.getInt(15));
				m.setM_g_no(rs.getInt(16));
				m.setG_no(rs.getInt(17));
				m.setG_detail(rs.getString(18));
				m.setW_no(rs.getInt(19));
				m.setW_detail(rs.getString(20));
				list.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

}
