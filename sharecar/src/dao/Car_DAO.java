package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Car_DTO;
import dto.Member_DTO;

public class Car_DAO implements DAO {

	public static Car_DAO self = null;

	private PreparedStatement psmt = null;
	private ResultSet rs = null;

	private Car_DAO() {
		driverLoad();
	}

	@Override
	public void driverLoad() {
		// TODO Auto-generated method stub
		load();
	}

	public static Car_DAO getInstance() {
		if (self == null) {
			self = new Car_DAO();
		}
		return self;
	}

	// insert
	public void insert(Car_DTO cdto, Member_DTO mdto) {
		String sql = "insert into car values (cs.nextval,?,?,?,?,?,?,default)";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setString(1, mdto.getM_id());
			psmt.setString(2, cdto.getC_num());
			psmt.setInt(3, cdto.getC_years());
			psmt.setInt(4, cdto.getC_term());
			psmt.setString(5, cdto.getC_kind());
			psmt.setString(6, cdto.getC_value());
			psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// my car select
	public List<Car_DTO> selectCar(Member_DTO mdto){
		List<Car_DTO> list = new ArrayList<>();
		String sql = "select * from car where c_m_id = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setString(1, mdto.getM_id());
			rs = psmt.executeQuery();
			while(rs.next()) {
				Car_DTO c = new Car_DTO();
				c.setC_num(rs.getString("c_num"));
				c.setC_date(rs.getString("c_date"));
				c.setC_kind(rs.getString("c_kind"));
				c.setC_years(rs.getInt("c_years"));
				c.setC_term(rs.getInt("c_term"));
				c.setC_value(rs.getString("c_value"));
				list.add(c);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	// insert 하면 바로 select 해서 rental로 insert
	public Car_DTO rentalInsert(Member_DTO mdto) {
		Car_DTO c = null;
		String sql = "select * from car where c_m_id = ? order by c_no desc";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setString(1, mdto.getM_id());
			rs = psmt.executeQuery();
			if(rs.next()) {
				c = new Car_DTO();
				c.setC_no(rs.getInt(1));
				c.setC_m_id(rs.getString(2));
				c.setC_num(rs.getString(3));
				c.setC_years(rs.getInt(4));
				c.setC_term(rs.getInt(5));
				c.setC_kind(rs.getString(6));
				c.setC_value(rs.getString(7));
				c.setC_date(rs.getString(8));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return c;
	}
	/**
	 * 
	 * @return 전체차량의 개별 정보가 담긴 객체인 Car_DTO의 리스트를 리턴합니다.
	 */
	public List<Car_DTO> selectAll(){
		List<Car_DTO> list = new ArrayList<>();
		String sql = "select * from car";
		try {
			psmt = con().prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				Car_DTO c = new Car_DTO();
				c.setC_no(rs.getInt(1));
				c.setC_m_id(rs.getString(2));
				c.setC_num(rs.getString(3));
				c.setC_years(rs.getInt(4));
				c.setC_term(rs.getInt(5));
				c.setC_kind(rs.getString(6));
				c.setC_value(rs.getString(7));
				c.setC_date(rs.getString(8));
				list.add(c);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
}
