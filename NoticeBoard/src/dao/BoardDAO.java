//package dao;
//
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//import dto.BoardDTO;
//import kr.or.ddit.util.JDBCUtil3;
//import util.JDBCUtil;
//
//public class BoardDAO implements DAOIfs<BoardDTO> {
//
//	private Connection conn;
//	private Statement stmt;
//	private PreparedStatement pstmt;
//	private ResultSet rs;
//
//	private Scanner scan = new Scanner(System.in);
//
//	@Override
//	public List<BoardDTO> findAll() {
//		List<BoardDTO> list = new ArrayList<BoardDTO>();
//		
//		try {
//			conn = JDBCUtil.getConnection();
//			String sql = "select * from board";
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(sql);
//			
//			while (rs.next()) {
//				String id = rs.getString("b_id");
//				Date date = rs.getDate("b_date");
//				String title = rs.getString("b_title");
//				String content = rs.getString("b_content");
//				String writer = rs.getString("b_writer");
//				list.add(new BoardDTO(id, date, title, content, writer));
//			}
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		} finally {
//			JDBCUtil.close(conn, stmt, pstmt, rs);
//		}
//		
//		return list;
//	}
//
//	@Override
//	public BoardDTO findById(String id) {
//		BoardDTO boardDTO = null;
//		try {
//			conn = JDBCUtil.getConnection();
//			String sql = "select * from board";
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(sql);
//			
//			rs.next();
//			Date date = rs.getDate("b_date");
//			String title = rs.getString("b_title");
//			String content = rs.getString("b_content");
//			String writer = rs.getString("b_writer");
//			boardDTO = new BoardDTO(id, date, title, content, writer);
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		} finally {
//			JDBCUtil.close(conn, stmt, pstmt, rs);
//		}
//		return boardDTO;
//	}
//
//	@Override
//	public int insert(BoardDTO dto) {
//		boolean isExist = false;
//
//		do {
//			System.out.println();
//			System.out.println("제목");
//			System.out.print("이믈");
//
//			memId = scan.next();
//
//			isExist = checkMember(memId);
//
//			if (isExist) {
//				System.out.println("회원ID가 " + memId + "인 회원은 이미 존재합니다.");
//				System.out.println("다시 입력 해 주세요.");
//			}
//		} while (isExist);
//		
//		System.out.print("회원 이름 >> ");
//		String memName = scan.next();
//		
//		System.out.print("회원 전화번호 >> ");
//		String memTel = scan.next();
//		
//		scan.nextLine(); // 버퍼 비우기
//		
//		System.out.print("회원 주소 >> ");
//		String memAddr = scan.nextLine();
//		
//		try {
//			conn = JDBCUtil3.getConnection();
//			
//			String sql = " insert into board (b_id, b_date, b_title, b_content, b_writer) " 
//					       + " values (board_sequence.nextval, sysdate, '게시판제목1', '게시판내용', '나성민') ";
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, memId);
//			pstmt.setString(2, memName);
//			pstmt.setString(3, memTel);
//			pstmt.setString(4, memAddr);
//			
//			int cnt = pstmt.executeUpdate();
//			
//			if (cnt > 0) {
//				System.out.println(memId + "회원 추가 작업 성공!");
//			} else {
//				System.out.println(memId + "회원 추가 작업 실패!!!");
//			}
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		} finally {
//			JDBCUtil3.close(conn, stmt, pstmt, rs);
//		}
//	}
//
//	@Override
//	public int update(BoardDTO dto) {
//		return 0;
//	}
//
//	@Override
//	public int deleteById(String id) {
//		return 0;
//	}
//
//}
