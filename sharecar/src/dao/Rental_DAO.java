package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Car_DTO;
import dto.Rental_DTO;

public class Rental_DAO implements DAO{
	
	public static Rental_DAO self = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	
	private Rental_DAO() {
		driverLoad();
	}
	
	public static Rental_DAO getInstance() {
		if(self == null) {
			self = new Rental_DAO();
		}
		return self;
	}
	

	@Override
	public void driverLoad() {
		// TODO Auto-generated method stub
		load();
	}
	
	//��Ż������ ���� ����Ʈ ����
	//����ڵ��� ���� ����Ҷ����� ����� ���̵�� null�� ���·� �ڵ� ����Ǿ� �Ѵ�.
	public void insert(Car_DTO cdto){
		String sql = "insert into rental values(rs.nextval,?,?,null,null,null)";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setInt(1, cdto.getC_no());
			psmt.setString(2, cdto.getC_m_id());
			psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//��Ż���� ���� insert
	public void update(Rental_DTO rdto) {
		String sql = "update rental set r_user_id = ?, r_date = ? , r_addr = ? where r_no = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setString(1, rdto.getR_user_id());
			psmt.setInt(2, rdto.getR_date());
			psmt.setString(3, rdto.getR_addr());
			psmt.setInt(4, rdto.getR_no());
			psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * ��ü ��Ż ����Ʈ
	 * @return ��ü��Ż��  Rental_DTO ����Ʈ�� �����մϴ�.
	 */
	public List<Rental_DTO> selectAll(){
		List<Rental_DTO> list = new ArrayList<>();
		String sql = "select * from rental";
		try {
			psmt = con().prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				Rental_DTO r = new Rental_DTO();
				r.setR_no(rs.getInt(1));
				r.setR_c_no(rs.getInt(2));
				r.setR_owner_id(rs.getString(3));
				r.setR_user_id(rs.getString(4));
				r.setR_date(rs.getInt(5));
				r.setR_addr(rs.getString(6));
				list.add(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
}
