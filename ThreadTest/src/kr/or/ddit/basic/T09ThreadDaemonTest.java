package kr.or.ddit.basic;

public class T09ThreadDaemonTest {
	
	public static void main(String[] args) {
		
		AutoSaveThread autoSave = new AutoSaveThread();
		
		// 데몬 스레드로 설정하기(start() 호출하기 전에 설정한다.)
		autoSave.setDaemon(true);
		autoSave.start();
		
		for (int i = 1; i <= 20; i++) {
			System.out.println("작업  - " + i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(Thread.currentThread().getName() + " 스레드 종료.");
	}
}

/*
 * 자동 저장하는 기능을 제공하는 스레드
 * (3초에 한번씩 자동저장한다.)
 */
class AutoSaveThread extends Thread {
	
	public void save() {
		System.out.println("작업 내용을 저장합니다...");
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			save(); // 저장
		}
	}
}