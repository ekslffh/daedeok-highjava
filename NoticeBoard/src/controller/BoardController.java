package controller;

import java.util.Scanner;

import dao.BoardDAO;
import vo.BoardVO;

public class BoardController {
	
	private Scanner scan;
	private BoardDAO boardDao;
	
	public BoardController() {
		scan = new Scanner(System.in);
		boardDao = new BoardDAO();
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
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch (choice) {
			case 1: // 자료 입력
				insertBoard();
				break;
			case 2: // 자료 수정
				updateBoard();
				break;
			case 3: // 자료 삭제
				deleteBoard();
				break;
			case 4: // 전체 자료 출력
				dispalyAllBoard();
				break;
			case 5: // 검색
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
		String writer = scan.next();
		System.out.print("제목: ");
		String title = scan.next();
		System.out.print("내용: ");
		scan.nextLine();
		String content = scan.nextLine();
				
	}

	private void updateBoard() {
		// TODO Auto-generated method stub
		
	}

	private void deleteBoard() {
		// TODO Auto-generated method stub
		
	}

	private void dispalyAllBoard() {
		// TODO Auto-generated method stub
		
	}

	private void searchBoard() {
		// TODO Auto-generated method stub
		
	}
	
}
