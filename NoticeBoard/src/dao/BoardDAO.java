package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.JDBCUtil;
import vo.BoardVO;

public class BoardDAO implements DAOIfs<BoardVO> {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public List<BoardVO> findAll() {
		List<BoardVO> list = new ArrayList<BoardVO>();
		
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
				list.add(new BoardVO(id, date, title, content, writer));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		return list;
	}

	@Override
	public BoardVO findById(String id) {
		BoardVO boardDTO = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from board where b_id = " + id;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				Date date = rs.getDate("b_date");
				String title = rs.getString("b_title");
				String content = rs.getString("b_content");
				String writer = rs.getString("b_writer");
				boardDTO = new BoardVO(id, date, title, content, writer);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return boardDTO;
	}

	@Override
	public int insert(BoardVO dto) {
		int cnt = 0;
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = " insert into board (b_id, b_date, b_title, b_content, b_writer) " 
					       + " values (board_sequence.nextval, sysdate, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getWriter());
			
			cnt = pstmt.executeUpdate();
			
			if (cnt > 0) {
				System.out.println(dto.getTitle() + "(" + dto.getWriter() + ") 게시글 추가 작업 성공!");
			} else {
				System.out.println(dto.getTitle()+ "(" + dto.getWriter()+ ") 게시글 추가 작업 실패!!!");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int update(BoardVO dto) {
		int cnt = 0;
		try {
			conn = JDBCUtil.getConnection();
			String sql = " update board set b_title = ?, b_content = ?, b_date = sysdate where b_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getId());
			cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println("게시글 수정 작업 성공!");
			} else {
				System.out.println("게시글 수정 작업 실패!");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int deleteById(String id) {
		int cnt = 0;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.createStatement();
			String sql = "delete from board where b_id = " + id;
			cnt = stmt.executeUpdate(sql);
			if (cnt > 0) {
				System.out.println("게시글 삭제 작업 성공!");
				conn.commit();
			} else {
				System.out.println("게시글 삭제 작업 실패!");
				conn.rollback();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

}
