package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import coupon.RandomString;
import dto.Event_DTO;
import dto.Member_DTO;

public class Event_DAO implements DAO{
	
	public static Event_DAO self = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	
	private Event_DAO() {
		driverLoad();
	}

	@Override
	public void driverLoad() {
		// TODO Auto-generated method stub
		load();
	}
	
	public static Event_DAO getInstance() {
		if(self == null) {
			self = new Event_DAO();
		}
		return self;
	}
	//현재 등록된 이벤트 리스트불러오기..당첨자가 없는 이벤트만..
	public List<Event_DTO> selectAll(){
		List<Event_DTO> list = new ArrayList<>();
		String sql = "select * from event where e_id is null";
		Connection con = null;
		try {
			con = con();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				Event_DTO e = new Event_DTO();
				e.setE_no(rs.getInt(1));
				e.setE_name(rs.getString(2));
				e.setE_coupon(rs.getString(3));
				e.setE_id(rs.getString(4));
				list.add(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	//이벤트번호를 인자값으로 받으면 쿠폰번호를 리턴하는 method
	public String couponChk(int e_no) {
		String coupon = new String();
		String sql = "select e_coupon from event where e_no = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setInt(1, e_no);
			rs = psmt.executeQuery();
			if(rs.next()) {
				coupon = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return coupon;
	}
	//당첨일경우 이벤트테이블업데이트
	public void win(Member_DTO mdto,int e_no) {
		String sql = "update event set e_id = ? where e_no = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setString(1, mdto.getM_id());
			psmt.setInt(2, e_no);
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//마이페이지 나의 당첨내역보기
	public List<Event_DTO> select(Member_DTO mdto) {
		List<Event_DTO> list = new ArrayList<>();
		String sql = "select * from event where e_id = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setString(1, mdto.getM_id());
			rs = psmt.executeQuery();
			while(rs.next()) {
				Event_DTO e = new Event_DTO();
				e.setE_no(rs.getInt(1));
				e.setE_name(rs.getString(2));
				e.setE_coupon(rs.getString(3));
				e.setE_id(rs.getString(4));
				list.add(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 전체 이벤트리스트 불러오기
	 * @return 현재 DB에 저장된 전체 Event_DTO의 리스트를 리턴해줍니다.
	 */
	public List<Event_DTO> select(){
		List<Event_DTO> list = new ArrayList<>();
		String sql = "select * from event";
		Connection con = null;
		try {
			con = con();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				Event_DTO e = new Event_DTO();
				e.setE_no(rs.getInt(1));
				e.setE_name(rs.getString(2));
				e.setE_coupon(rs.getString(3));
				e.setE_id(rs.getString(4));
				list.add(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	/**
	 * 단일 이벤트 삭제
	 * @param e_no : 이벤트번호를 인자값으로 받습니다.
	 */
	public void delete(int e_no) {
		String sql = "delete from event where e_no = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setInt(1, e_no);
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 이벤트 생성
	 * @param e_name : 이벤트 이름을 인자값으로 받습니다.
	 */
	public void insert(String e_name) {
		String sql = "insert into event values(es.nextval,?,?,null)";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setString(1, e_name);
			psmt.setString(2, RandomString.getInstance().coupon());
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
