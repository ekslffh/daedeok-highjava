package kr.or.ddit.basic;

public class T13ThreadStopTest {
	public static void main(String[] args) {
		
//		Thread th1 = new ThreadStopEx();
//		th1.start();
//		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		// th1.stop();
//		((ThreadStopEx) th1).setStoped(true);
		
		Thread th2 = new ThreadStopEx2();
		th2.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		th2.interrupt(); // 인터럽트 걸기

	}
}

class ThreadStopEx extends Thread {

	private boolean isStoped;

	public void setStoped(boolean isStoped) {
		this.isStoped = isStoped;
	}

	@Override
	public void run() {
		while (!isStoped) {
			System.out.println("스레드 처리 중...");
		}

		System.out.println("자원 정리 중...");
		System.out.println("실행 종료.");
	}

}

// interrupt()메서드를 이용하여 스레드를 멈추게 하는 방법
class ThreadStopEx2 extends Thread {
	
	@Override
	public void run() {
		// 방법 1 => sleep()메서드나 join()메서드 등을 사용했을 때
		//		   interrupt()메서드를 호출하면 InterruptedException이 
		//		       발생한다. 이 예외를 이용하여 처리하면 된다.	

//		try {
//			while (true) {
//				System.out.println("스레드 처리 중...");
//				Thread.sleep(1);
//			}
//		} catch (InterruptedException e) {
//			
//		}

		// 방법2 => interrupt() 호출되었는지 검사하기
		while (true) {
			System.out.println("스레드 처리 중...");

			// 검사방법1 => 스레드의 인스턴스용 메서드 이용하는 방법
//			if (this.isInterrupted()) {
//				System.out.println("인스턴스용 isInterrupted() 호출됨.");
//				break;
//			}
			
			// 검사방법2 => 스레드의 정적 메서드 이용하는 방법
			if (Thread.interrupted()) {
				System.out.println(Thread.interrupted());
				System.out.println("정적 메서드 Thread.interrupted() 호출됨.");
				break;
			}
			
		}

		System.out.println("자원 정리 중...");
		System.out.println("실행 종료.");
	}
}
