package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DAO {
	
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String user = "system";
	String pass = "1111";
	
	//드라이버 로드는 필수 오버라이드 되어야 함.
	public void driverLoad();
	//드라이버 로드
	default void load() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @return 컨넥션한 객체를 리턴해줍니다.
	 */
	default Connection con() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url,user,pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
