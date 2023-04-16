package kr.or.ddit.basic;

/*
 * 스레드를 사용하다보면 해당 스레드를 멈춰야 하는 경우가 발생한다.
 * 그럴 경우에 단순히 stop()메서드를 통해서 멈추게 된다면 문제가 발생할 수 있다.
 * 왜냐하면 특정 스레드에서 종료시점에 해줘야 하는 작업이 있을 수가 있다. (자원정리 등)
 * 따라서 단순히 stop()메서드를 이용하지 않고 interrupt()를 걸어서 해당 스레드에서 interrupt된것을 확인하고
 * 이후에 작업종료 작업을 완수하고 자연스럽게 종료되도록 할 필요가 있다.
 */

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
