package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class T06ThreadTest {
	public static void main(String[] args) {
		Thread th1 = new DataInput();
		Thread th2 = new CountDown();
		
		th1.start();
		th2.start();
		
		try {
			th1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		th2.stop();
	}
}

// 데이터의 입력을 처리하기 위한 스레드
class DataInput extends Thread {
	
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요.");
		System.out.println("입력한 값은 " + str + "입니다.");
	}
}

// 카운트다운을 처리하기 위한 스레드
class CountDown extends Thread {
	
	@Override
	public void run() {
		
		for (int i = 10; i >= 1; i--) {
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}