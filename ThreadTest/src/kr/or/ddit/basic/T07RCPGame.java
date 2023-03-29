package kr.or.ddit.basic;

import java.util.Random;

import javax.swing.JOptionPane;

public class T07RCPGame {
	
	public static boolean isInputOk = false;
	
//	enum RCP {
//		PAPER("보"), CISSORS("가위"), ROCK("바위");
//		private String value;
//		RCP(String value) {
//			this.value = value;
//		}
//		public String getValue() {
//			return value;
//		}
//	}
//	
//	public void start() {
//		
//	}
//	
//	public int compare(String player, String computer) {
//		if (player.equals(computer)) {
//			return 0;
//		} 
//		if (player.)
//	}
//	
	public static void main(String[] args) {
		Thread thCoundDown = new CountDown();
		thCoundDown.start();
		String input = showInputDialog();
		int value = new Random().nextInt(3);
		System.out.println("=== 결과 ===");
		// 0: 가위, 1: 바위, 2: 보
		switch (value) {
		case 0:
			System.out.println("컴퓨터: 가위");
			System.out.println("당신: " + input);
			if (input.equals("가위")) {
				System.out.println("결과: 비겼습니다.");
			} else if (input.equals("바위")) {
				System.out.println("결과: 당신이 이겼습니다.");
			} else if (input.equals("보")) {
				System.out.println("결과: 당신이 졌습니다.");
			} else {
				System.out.println("잘못된 입력");
			}
			break;
		case 1:
			System.out.println("컴퓨터: 바위");
			System.out.println("당신: " + input);
			if (input.equals("가위")) {
				System.out.println("결과: 당신이 졌습니다.");
			} else if (input.equals("바위")) {
				System.out.println("결과: 비겼습니다.");
			} else if (input.equals("보")) {
				System.out.println("결과: 당신이 이겼습니다.");
			} else {
				System.out.println("잘못된 입력");
			}
			break;
		case 2:
			System.out.println("컴퓨터: 보");
			System.out.println("당신: " + input);
			if (input.equals("가위")) {
				System.out.println("결과: 당신이 졌습니다.");
			} else if (input.equals("바위")) {
				System.out.println("결과:당신이 이겼습니다.");
			} else if (input.equals("보")) {
				System.out.println("결과: 비겼습니다.");
			} else {
				System.out.println("잘못된 입력");
			}
			break;
		}
	}
	
	public static String showInputDialog() {
		String value =  JOptionPane.showInputDialog("아무거나 입력하세요.");
		isInputOk = true;
		return value;
	}
}

class CountDown extends Thread {
	@Override
	public void run() {
		for (int i = 5; i > 0; i--) {
			
			if (T07RCPGame.isInputOk) {
				return;
			}
			
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("당신이 졌습니다.");
		System.exit(0);
	}
}
