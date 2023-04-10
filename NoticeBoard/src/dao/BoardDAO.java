package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import dto.BoardDTO;
import util.JDBCUtil;

public class BoardDAO implements DAOIfs<BoardDTO> {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Scanner scan = new Scanner(System.in);

	@Override
	public List<BoardDTO> findAll() {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from board";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				String id = rs.getString("b_id");
				Date date = rs.getDate("b_date");
				String title = rs.getString("b_title");
				String content = rs.getString("b_content");
				String writer = rs.getString("b_writer");
				list.add(new BoardDTO(id, date, title, content, writer));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		return list;
	}

	@Override
	public BoardDTO findById(String id) {
		BoardDTO boardDTO = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from board";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			rs.next();
			Date date = rs.getDate("b_date");
			String title = rs.getString("b_title");
			String content = rs.getString("b_content");
			String writer = rs.getString("b_writer");
			boardDTO = new BoardDTO(id, date, title, content, writer);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return boardDTO;
	}

	@Override
	public int insert(BoardDTO dto) {
		boolean isExist = false;

		System.out.print("작성자 >> ");
		String writer = scan.nextLine();
		
		System.out.print("제목 >> ");
		String title = scan.nextLine();
		
		System.out.println("내용입력");
		String content = scan.nextLine();
		
		int cnt = 0;
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = " insert into board (b_id, b_date, b_title, b_content, b_writer) " 
					       + " values (board_sequence.nextval, sysdate, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);
			
			cnt = pstmt.executeUpdate();
			
			if (cnt > 0) {
				System.out.println(writer + "회원 추가 작업 성공!");
			} else {
				System.out.println(writer + "회원 추가 작업 실패!!!");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public int update(BoardDTO dto) {
		return 0;
	}

	@Override
	public int deleteById(String id) {
		return 0;
	}

}
