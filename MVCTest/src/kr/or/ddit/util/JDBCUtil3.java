package kr.or.ddit.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;


/**
 * db.properties 파일의 내용으로 DB정보를 가져오도록 설정하는 방법
 * 방법2) ResourceBundle 객체 이용하기
 * @author PC-20
 */
public class JDBCUtil3 {
	
	static ResourceBundle bundle; // ResourceBundle 객체변수 선언

	static {
		
		bundle = ResourceBundle.getBundle("db"); // 객체 생성
		
		try {
			
			// 드라이버 설정 확인
			Class.forName(bundle.getString("driver"));	
			
			System.out.println("드라이버 로딩 성공!");
			
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					bundle.getString("url"), 
					bundle.getString("username"), 
					bundle.getString("password"));
			
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
