package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DAO {
	
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String user = "system";
	String pass = "1111";
	
	//����̹� �ε�� �ʼ� �������̵� �Ǿ�� ��.
	public void driverLoad();
	//����̹� �ε�
	default void load() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @return ���ؼ��� ��ü�� �������ݴϴ�.
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
