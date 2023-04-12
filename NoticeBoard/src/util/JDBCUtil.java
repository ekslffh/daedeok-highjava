package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * db.properties 파일의 내용으로 DB정보를 가져오도록 설정하는 방법
 * 방법1) Properties 객체 이용하기
 * @author PC-20
 */
public class JDBCUtil {
	
	static Properties prop; // Properties객체변수 선언

	static {
		
		prop = new Properties(); // 객체 생성
		
		try {
			
			prop.load(new FileInputStream("res/db.properties"));
			
			// 드라이버 설정 확인
			Class.forName(prop.getProperty("driver"));	
			
			System.out.println("드라이버 로딩 성공!");
			
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					prop.getProperty("url"), 
					prop.getProperty("username"), 
					prop.getProperty("password"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 자원반납
	 * @param conn
	 * @param stmt
	 * @param pstmt
	 * @param rs
	 */
	public static void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) try {rs.close();} catch (SQLException ex) {}
		if (pstmt != null) try {pstmt.close();} catch (SQLException ex) {}
		if (stmt != null) try {stmt.close();} catch (SQLException ex) {}
		if (conn != null) try {conn.close();} catch (SQLException ex) {}
	}
	
}
