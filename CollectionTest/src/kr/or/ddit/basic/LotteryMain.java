package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class LotteryMain {
	public static void main(String[] args) {
		while (true) {
			Lottery lottery = new Lottery();
			int select = lottery.printMenu();
			if (select == 1) {
				lottery.buyLottery();
			} else if (select == 2) {
				System.out.println("\n감사합니다.");
				System.exit(0);
			} else {
				System.out.println("잘못된 입력입니다.");
			}
		}
	}
}

class Lottery {
	Scanner sc = new Scanner(System.in);
	public int printMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("=========================\n");
		sb.append("Lotto 프로그램\n");
		sb.append("----------------\n");
		sb.append("1. Lotto 구입\n");
		sb.append("2. 프로그램 종료\n");
		sb.append("=========================\n");
		sb.append("메뉴선택: ");
		System.out.print(sb);
		return sc.nextInt();
	}
	public void buyLottery() {
		StringBuffer sb = new StringBuffer();
		sb.append("Lotto 구입 시작\n\n");
		sb.append("1000원에 로또번호 하나입니다.\n");
		sb.append("금액 입력: ");
		System.out.print(sb);
		int money = sc.nextInt();
		int numOfLottery = money / 1000;
		System.out.println("행운의 로또번호는 아래와 같습니다.");
		for (int i = 0; i < numOfLottery; i++) {
			HashSet<Integer> lotterySet = new HashSet<Integer>();
			while (lotterySet.size() < 6) {
				lotterySet.add(new Random().nextInt(45) + 1);
			}
			System.out.println("로또번호" + (i + 1) + ": " + lotterySet.toString().substring(1, lotterySet.toString().length() - 1));
		}
		System.out.println("\n받은 금액은 " + money + "이고 거스름돈은 " + (money % 1000) + "원입니다.\n");
	}
}
