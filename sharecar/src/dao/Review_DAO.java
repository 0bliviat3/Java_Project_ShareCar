package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Member_DTO;
import dto.Review_DTO;

public class Review_DAO implements DAO {

	public static Review_DAO self = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;

	private Review_DAO() {
		driverLoad();
	}

	@Override
	public void driverLoad() {
		// TODO Auto-generated method stub
		load();
	}

	public static Review_DAO getInstance() {
		if (self == null) {
			self = new Review_DAO();
		}
		return self;
	}

	// 리뷰리스트
	public List<Review_DTO> selectAll() {
		List<Review_DTO> list = new ArrayList<>();
		String sql = "select * from review";
		try {
			psmt = con().prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Review_DTO r = new Review_DTO();
				r.setRv_no(rs.getInt(1));
				r.setRv_id(rs.getString(2));
				r.setRv_car(rs.getString(3));
				r.setRv_title(rs.getString(4));
				r.setRv_memo(rs.getString(5));
				r.setRv_date(rs.getString(6));
				r.setRv_views(rs.getInt(7));
				list.add(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 리뷰 등록
	public void insert(Review_DTO rdto) {
		String sql = "insert into review values(rvs.nextval,?,?,?,?,default,default)";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setString(1, rdto.getRv_id());
			psmt.setString(2, rdto.getRv_car());
			psmt.setString(3, rdto.getRv_title());
			psmt.setString(4, rdto.getRv_memo());
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 리뷰 조회시 조회수 업데이트
	public void updateView(Review_DTO rdto) {
		String sql = "update review set rv_views = ? where rv_no = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setInt(1, rdto.getRv_views() + 1);
			psmt.setInt(2, rdto.getRv_no());
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 나의 리뷰리스트
	public List<Review_DTO> selectMyReview(Member_DTO mdto) {
		List<Review_DTO> list = new ArrayList<>();
		String sql = "select * from review where rv_id = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setString(1, mdto.getM_id());
			rs = psmt.executeQuery();
			while (rs.next()) {
				Review_DTO r = new Review_DTO();
				r.setRv_no(rs.getInt(1));
				r.setRv_id(rs.getString(2));
				r.setRv_car(rs.getString(3));
				r.setRv_title(rs.getString(4));
				r.setRv_memo(rs.getString(5));
				r.setRv_date(rs.getString(6));
				r.setRv_views(rs.getInt(7));
				list.add(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//나의 리뷰 삭제
	public void deleteReview(Review_DTO rdto) {
		String sql = "delete from review where rv_id = ? and rv_no = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setString(1, rdto.getRv_id());
			psmt.setInt(2, rdto.getRv_no());
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//나의 리뷰 수정
	public void updateReview(Review_DTO rdto) {
		String sql = "update review set rv_car = ?, rv_title = ?, rv_meno = ? where rv_no = ? and rv_id = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setString(1, rdto.getRv_car());
			psmt.setString(2,rdto.getRv_title());
			psmt.setString(3, rdto.getRv_memo());
			psmt.setInt(4, rdto.getRv_no());
			psmt.setString(5, rdto.getRv_id());
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//리뷰하나만 불러오기...메서드오버로딩
	public Review_DTO selectMyReview(Review_DTO rdto) {
		Review_DTO r = null;
		String sql = "select * from review where rv_id = ? and rv_no = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setString(1, rdto.getRv_id());
			psmt.setInt(2, rdto.getRv_no());
			rs = psmt.executeQuery();
			if (rs.next()) {
				r = new Review_DTO();
				r.setRv_no(rs.getInt(1));
				r.setRv_id(rs.getString(2));
				r.setRv_car(rs.getString(3));
				r.setRv_title(rs.getString(4));
				r.setRv_memo(rs.getString(5));
				r.setRv_date(rs.getString(6));
				r.setRv_views(rs.getInt(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	/**
	 * 단일리뷰 삭제
	 * @param rv_no : 리뷰번호를 인자값으로 받습니다.
	 */
	public void delete(int rv_no) {
		String sql = "delete from review where rv_no =?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setInt(1, rv_no);
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param rv_no : 리뷰번호를 인자값으로 받습니다
	 * @return 단일리뷰의 전체정보가 담긴 객체인 Review_DTO를 리턴합니다.
	 */
	public Review_DTO select(int rv_no) {
		Review_DTO rdto = null;
		String sql = "select * from review where rv_no = ?";
		try {
			psmt = con().prepareStatement(sql);
			psmt.setInt(1, rv_no);
			rs = psmt.executeQuery();
			if(rs.next()) {
				rdto = new Review_DTO();
				rdto.setRv_no(rs.getInt(1));
				rdto.setRv_id(rs.getString(2));
				rdto.setRv_car(rs.getString(3));
				rdto.setRv_title(rs.getString(4));
				rdto.setRv_memo(rs.getString(5));
				rdto.setRv_date(rs.getString(6));
				rdto.setRv_views(rs.getInt(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rdto;
	}

}
