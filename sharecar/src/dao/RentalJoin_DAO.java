package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Member_DTO;
import dto.RentalJoin_DTO;

public class RentalJoin_DAO implements DAO{
	
	public static RentalJoin_DAO self = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	
	private RentalJoin_DAO() {
		driverLoad();
	}
	
	@Override
	public void driverLoad() {
		// TODO Auto-generated method stub
		load();
	}
	
	public static RentalJoin_DAO getInstance() {
		if(self == null) {
			self = new RentalJoin_DAO();
		}
		return self;
	}
	
	//렌탈가능한 차량 리스트
	public List<RentalJoin_DTO> selectRental(Member_DTO mdto){
		List<RentalJoin_DTO> list = new ArrayList<>();
		String sql = "select c.c_kind,c.c_date,c.c_value,c.c_term,c.c_years,c.c_num,m.m_addr,m.m_nickname,r.r_no from (select r_no,r_c_no from rental where r_user_id is null and r_owner_id <> ?) r join car c on c.c_no=r.r_c_no join member m on c.c_m_id = m.m_id";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setString(1, mdto.getM_id());
			rs = psmt.executeQuery();
			while(rs.next()) {
				RentalJoin_DTO j = new RentalJoin_DTO();
				j.setJ_kind(rs.getString(1));
				j.setJ_date(rs.getString(2));
				j.setJ_value(rs.getString(3));
				j.setJ_term(rs.getInt(4));
				j.setJ_years(rs.getInt(5));
				j.setJ_num(rs.getString(6));
				j.setJ_addr(rs.getString(7));
				j.setJ_nickname(rs.getString(8));
				j.setJ_no(rs.getInt(9));
				list.add(j);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	//마이페이지 렌탈한 차량 리스트
	public List<RentalJoin_DTO> myPageLental (Member_DTO mdto){
		List<RentalJoin_DTO> list = new ArrayList<>();
		String sql = "select c.c_kind,c.c_value,c.c_years,c.c_num,r.r_date,r.r_addr from car c join rental r on c.c_no = r.r_c_no where r.r_user_id = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setString(1, mdto.getM_id());
			rs = psmt.executeQuery();
			while(rs.next()) {
				RentalJoin_DTO j = new RentalJoin_DTO();
				j.setJ_kind(rs.getString(1));
				j.setJ_value(rs.getString(2));
				j.setJ_years(rs.getInt(3));
				j.setJ_num(rs.getString(4));
				j.setJ_r_date(rs.getInt(5));
				j.setJ_r_addr(rs.getString(6));
				list.add(j);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//마이페이지 현재 등록된 차량 리스트 렌탈여부까지 확인
	public List<RentalJoin_DTO> myCar (Member_DTO mdto){
		List<RentalJoin_DTO> list = new ArrayList<>();
		String sql = "select c.c_kind,c.c_date,c.c_num,c.c_years,c.c_value,c.c_term,m.m_nickname from car c join rental r on c.c_no = r.r_c_no left outer join member m on r.r_user_id = m.m_id where c.c_m_id = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setString(1, mdto.getM_id());
			rs = psmt.executeQuery();
			while(rs.next()) {
				RentalJoin_DTO j = new RentalJoin_DTO();
				j.setJ_kind(rs.getString(1));
				j.setJ_date(rs.getString(2));
				j.setJ_num(rs.getString(3));
				j.setJ_years(rs.getInt(4));
				j.setJ_value(rs.getString(5));
				j.setJ_term(rs.getInt(6));
				j.setJ_r_nickname(rs.getString(7));
				list.add(j);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

}
