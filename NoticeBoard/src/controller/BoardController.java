package controller;

import java.util.List;
import java.util.Scanner;
import service.BoardServiceIfs;
import service.BoardServiceImpl;
import vo.BoardVO;

public class BoardController {
	
	private Scanner scan;
	private BoardServiceIfs boardService;
	
	public BoardController() {
		scan = new Scanner(System.in);
		boardService = BoardServiceImpl.getInstance();
	}
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 게시글 작성");
		System.out.println("  2. 게시글 수정");
		System.out.println("  3. 게시글 삭제");
		System.out.println("  4. 게시글 전체조회");
		System.out.println("  5. 게시글 조회");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	public void start() {
		int choice;
		do {
			displayMenu(); // 메뉴 출력
			choice = Integer.parseInt(scan.nextLine()); // 메뉴번호 입력받기
			switch (choice) {
			case 1: // 게시글 작성
				insertBoard();
				break;
			case 2: // 게시글 수
				updateBoard();
				break;
			case 3: // 게시글 삭제
				deleteBoard();
				break;
			case 4: // 게시글 전체조회
				dispalyAllBoard();
				break;
			case 5: // 게시글 조회
				searchBoard();
				break;
			case 6: // 작업 끝
				System.out.println("작업을 마칩니다.");
				break;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		} while (choice != 6);
	}

	private void insertBoard() {
		System.out.print("작성자: ");
		String writer = scan.nextLine();
		System.out.print("제목: ");
		String title = scan.nextLine();
		StringBuffer sb = new StringBuffer();
		System.out.println("----------------------------내용입력------------------------------");
		while (true) {
			String sbInput = scan.nextLine();
			if (sbInput.equals("quit")) break;
			sb.append(sbInput);
			sb.append("\n");
		}
		String content = sb.toString();
		
		int cnt = boardService.insert(new BoardVO(content, title, content, writer));
		if (cnt > 0) {
			System.out.println(title + "(" + writer + ") 게시글 추가 작업 성공!");
		} else {
			System.out.println(title + "(" + writer + ") 게시글 추가 작업 실패!!!");
		}
	}

	private void updateBoard() {
		System.out.print("게시글번호: ");
		String id = scan.nextLine();
		System.out.print("제목: ");
		String title = scan.nextLine();
		System.out.print("내용: ");
		String content = scan.nextLine();
		
		int cnt = boardService.update(new BoardVO(id, title, content));
		if (cnt > 0) {
			System.out.println(id + " 게시글 수정 작업 성공!");
		} else {
			System.out.println(id + " 게시글 수정 작업 실패!!!");
		}
	}

	private void deleteBoard() {
		System.out.println("게시글번호: ");
		String id = scan.nextLine();
		
		int cnt = boardService.deleteById(id);
		if (cnt > 0) {
			System.out.println(id + "번 게시글 삭제 작업 성공!");
		} else {
			System.out.println(id + "번 게시글 삭제 작업 실패!!!");
		}
	}

	private void dispalyAllBoard() {
		List<BoardVO> boardList = boardService.searchAll();
		System.out.println("----------------------------------------------------------");
		System.out.println("번호\t제목\t\t내용\t\t작성자\t작성일자");
		for (BoardVO board : boardList) {
			System.out.println(board.getId() + "\t" + board.getTitle() + "\t\t" + board.getContent() + "\t\t" + board.getWriter() + "\t" + board.getDate());
		}
		System.out.println("----------------------------------------------------------");
	}

	private void searchBoard() {
		System.out.print("게시글번호: ");
		String id = scan.nextLine();
		BoardVO board = boardService.searchById(id);
		if (board == null) {
			System.out.println("해당 게시글은 존재하지 않습니다.");
			return;
		}
		System.out.println();
		System.out.println("----------------------------------------------------------");
		System.out.println("[" + board.getId() + "] <" + board.getTitle() + ">");
		System.out.println("----------------------------------------------------------");
		System.out.println(board.getContent());
		System.out.println("----------------------------------------------------------");
	}
	
}
