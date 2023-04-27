package member.controller;

import java.util.List;
import java.util.Scanner;
import member.service.IMemberService;
import member.service.MemberServiceImpl;
import member.vo.MemberVO;

public class MemberController {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		IMemberService memberService = MemberServiceImpl.getInstance();
		while (true) {
			System.out.println("1. 전체조회, 2. 특정멤버조회, 3. 종료");
			System.out.print("입력 : ");
			String select = scanner.nextLine();
			if (select.equals("1")) {
				// 전체조회
				List<MemberVO> memList = memberService.findAll();
				if (memList.size() == 0) {
					System.out.println("멤버가 비어있습니다.");
				} else {
					for (MemberVO mv : memList) {
						System.out.println(mv);
					}
				}
			} else if (select.equals("2")) {
				// 특정멤버조회
				System.out.print("id 입력 :");
				String memId = scanner.nextLine();
				MemberVO mv = memberService.findById(memId);
				if (mv == null) {
					System.out.println("해당 멤버는 존재하지 않습니다.");
				} else {
					System.out.println(mv);
				}
			} else if (select.equals("3")) {
				// 종료
				System.out.println("종료합니다.");
				System.exit(0);
			} else {
				System.out.println("잘못된 입력입니다.");
			}
		}

	}
}
