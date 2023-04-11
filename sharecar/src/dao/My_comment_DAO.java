package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Member_DTO;
import dto.My_comment_DTO;

public class My_comment_DAO implements DAO{
	public static My_comment_DAO self = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	
	private My_comment_DAO() {
		
	}
	
	public static My_comment_DAO getInstance() {
		if(self == null) {
			self = new My_comment_DAO();
		}
		return self;
	}
	@Override
	public void driverLoad() {
		// TODO Auto-generated method stub
		load();
	}
	//��� �Է�
	public void insert(My_comment_DTO mcdto) {
		String sql = "insert into my_comment values (cms.nextval,?,?,?,default,default)";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setInt(1, mcdto.getCm_rv_no());
			psmt.setString(2, mcdto.getCm_id());
			psmt.setString(3, mcdto.getCm_memo());
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//��õ�� ����
	public void update(My_comment_DTO mcdto) {
		String sql = "update my_comment set cm_likes = ? where cm_no = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setInt(1, mcdto.getCm_likes()+1);
			psmt.setInt(2, mcdto.getCm_no());
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//��۹�ȣ�� ���ã��
	public My_comment_DTO select(int cm_no) {
		My_comment_DTO mcdto = null;
		String sql = "select * from my_comment where cm_no = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setInt(1, cm_no);
			rs = psmt.executeQuery();
			if(rs.next()) {
				mcdto = new My_comment_DTO();
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
	//���̵�� ���ã��
	public List<My_comment_DTO> selectMyComment(Member_DTO mdto) {
		List<My_comment_DTO> list = new ArrayList<>();
		String sql = "select * from my_comment where cm_id = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setString(1, mdto.getM_id());
			rs = psmt.executeQuery();
			while(rs.next()) {
				My_comment_DTO mcdto = new My_comment_DTO();
				mcdto.setCm_no(rs.getInt(1));
				mcdto.setCm_rv_no(rs.getInt(2));
				mcdto.setCm_id(rs.getString(3));
				mcdto.setCm_memo(rs.getString(4));
				mcdto.setCm_date(rs.getString(5));
				mcdto.setCm_likes(rs.getInt(6));
				list.add(mcdto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//���������� ��ۻ���
	/**
	 * 
	 * @param cm_no : ��۹�ȣ�� ���ڰ����� �޽��ϴ�.
	 */
	public void deleteMyComment(int cm_no) {
		String sql = "delete from my_comment where cm_no = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setInt(1, cm_no);
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @return DB�� ����� ����� ��� ������ ��� ��ü�� My_comment_DTO ����Ʈ�� �����մϴ�.
	 */
	public List<My_comment_DTO> seletAll(){
		List<My_comment_DTO> list = new ArrayList<>();
		String sql = "select * from my_comment";
		try {
			psmt = con().prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				My_comment_DTO mcdto = new My_comment_DTO();
				mcdto.setCm_no(rs.getInt(1));
				mcdto.setCm_rv_no(rs.getInt(2));
				mcdto.setCm_id(rs.getString(3));
				mcdto.setCm_memo(rs.getString(4));
				mcdto.setCm_date(rs.getString(5));
				mcdto.setCm_likes(rs.getInt(6));
				list.add(mcdto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

}
