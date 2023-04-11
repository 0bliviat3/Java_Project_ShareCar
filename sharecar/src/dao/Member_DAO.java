package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.Member_DTO;

public class Member_DAO implements DAO {

	public static Member_DAO self = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;

	private Member_DAO() {
		driverLoad();
	}

	public static Member_DAO getInstance() {
		if (self == null) {
			self = new Member_DAO();
		}
		return self;
	}

	@Override
	public void driverLoad() {
		load();
	}

	// ȸ������
	public void insert(Member_DTO mdto) {
		String sql = "insert into member values (?,?,?,?,?,?,default,?,?,?,?,?,?,?,default,default)";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setString(1, mdto.getM_id());
			psmt.setString(2, mdto.getM_name());
			psmt.setString(3, mdto.getM_pass());
			psmt.setString(4, mdto.getM_gender());
			psmt.setInt(5, mdto.getM_age());
			psmt.setString(6, mdto.getM_nickname());
			psmt.setString(7, mdto.getM_email());
			psmt.setString(8, mdto.getM_phone());
			psmt.setInt(9, mdto.getM_car_license());
			psmt.setString(10, mdto.getM_addr());
			psmt.setString(11, mdto.getM_near_addr());
			psmt.setString(12, mdto.getM_account());
			psmt.setString(13, mdto.getM_bank());

			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ���̵� �ߺ�üũ
	// ���ϰ� boolean ���̵� �����ϸ� true ����
	public boolean selectID(String id) {
		String sql = "select * from member where m_id = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// �г��� �ߺ�üũ
	// �ߺ��Ǹ� true
	public boolean selectNickname(String nickname) {
		String sql = "select * from member where m_nickname = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setString(1, nickname);
			rs = psmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// �α���
	// ȸ���� �ƴѰ�� null, ȸ���ΰ�� Member_DTO, �������� ��� �α��� Ŭ�������� �ذ�
	public Member_DTO selectIdPass(String id, String pass) {
		String sql = "select * from member where m_id = ? and m_pass = ?";
		Member_DTO m = null;
		try {
			psmt = con().prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			if (rs.next()) {
				m = new Member_DTO();
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
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return m;
	}

	// ���������� ��������
	public void mod(Member_DTO mdto) {
		String sql = "update member set m_pass = ?,m_email = ?,m_phone = ?,m_addr = ?,m_near_addr = ?,m_account = ?,m_bank = ? where m_id = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setString(1, mdto.getM_pass());
			psmt.setString(2, mdto.getM_email());
			psmt.setString(3, mdto.getM_phone());
			psmt.setString(4, mdto.getM_addr());
			psmt.setString(5, mdto.getM_near_addr());
			psmt.setString(6, mdto.getM_account());
			psmt.setString(7, mdto.getM_bank());
			psmt.setString(8, mdto.getM_id());
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ȸ�� Ż��
	public void delete(Member_DTO mdto) {
		String sql = "delete from member where m_id = ? and m_pass = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setString(1, mdto.getM_id());
			psmt.setString(2, mdto.getM_pass());
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ��Ż�� 1000����Ʈ ����
	public void point(Member_DTO mdto) {
		String sql = "update member set m_point = ? where m_id = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setInt(1, mdto.getM_point() + 1000);
			psmt.setString(2, mdto.getM_id());
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ȸ���� ��� ������Ʈ
	public void updateGrade(Member_DTO mdto) {
		String sql = "update member set m_g_no = 1 where m_id = ?";
		String sql2 = "update member set m_g_no = 2 where m_id = ?";
		String sql3 = "update member set m_g_no = 0 where m_id = ?";
		int point = mdto.getM_point() + 1000;
		try {
			if ((point >= 5000) && (point < 10000)) {
				psmt = con().prepareStatement(sql);
			} else if (point >= 10000) {
				psmt = con().prepareStatement(sql2);
			} else {
				psmt = con().prepareStatement(sql3);
			}
			psmt.setString(1, mdto.getM_id());
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ȸ���� ����Ʈ ������ ȸ���� ���� ����Ʈ���� ����
	public Member_DTO usePoint(Member_DTO mdto) {
		String sql = "update member set m_point = ? where m_id = ?";
		String sql2 = "select m_point from member where m_id =?";
		Connection con = null;
		try {
			con = con();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, mdto.getM_point() - 1000);
			psmt.setString(2, mdto.getM_id());
			psmt.executeUpdate();
			psmt = con.prepareStatement(sql2);
			psmt.setString(1, mdto.getM_id());
			rs = psmt.executeQuery();
			if (rs.next()) {
				mdto.setM_point(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mdto;
	}

	// ȸ���� ����ȣ ������Ʈ
	/**
	 * 
	 * @param m_id   : ȸ���� ���̵� ���ڰ����� �޽��ϴ�.
	 * @param m_w_no : ȸ������ �ο��� ����ȣ�� ���ڰ����� �޽��ϴ�.
	 */
	public void warning(String m_id, int m_w_no) {
		String sql = "update member set m_w_no = ? where m_id = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setInt(1, m_w_no);
			psmt.setString(2, m_id);
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param id : ȸ���� ���̵� ���ڰ����� �޽��ϴ�.
	 * @return ȸ���� ������ ��� DTO��ü�� �����մϴ�.
	 */
	public Member_DTO selectId(String id) {
		String sql = "select * from member where m_id = ?";
		Member_DTO m = null;
		try {
			psmt = con().prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				m = new Member_DTO();
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
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return m;
	}

}
