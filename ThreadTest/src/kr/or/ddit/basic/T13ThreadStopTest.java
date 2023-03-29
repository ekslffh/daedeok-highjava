package kr.or.ddit.basic;

public class T13ThreadStopTest {
	public static void main(String[] args) {
		
		Thread th1 = new ThreadStopEx();
		th1.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		th1.stop();
		
	}
}

class ThreadStopEx extends Thread {
	
	private boolean isStoped;
	
	@Override
	public void run() {
		while (!isStoped) {
			System.out.println("스레드 처리 중...");
		}
		
		System.out.println("자원 정리 중...");
	}
	
}
