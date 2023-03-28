package kr.or.ddit.basic;

public class T02ThreadTest {
	public static void main(String[] args) {
		
		// 멀티 스레드 프로그램 방식
		
		// 방법1 : Thread클래스를 상속한 class의 인스턴스를 생성한 후
		//		    이 인스턴스의 start() 메서드 호출한다.
		Thread th1 = new MyThread1();
		th1.start();
		
		// 방법2 : Runnable인터페이스를 구현한 class의 인스턴스를 생성한 후
		//		    이 인스터스를 Thread객체 생성시 생성자의 매개벼수로 넘겨준다.
		//		    이때 생성된 Thread객체의 start()메서드를 호출한다.
		Runnable r = new MyThread2();
		Thread th2 = new Thread(r);
		th2.start(); // 결국 내부적으로 start()메서드 내에서 run()실행
		
		// 방법3 : 익명클래스를 이용하는 방법
		//		  Runnable 인터페이스를 구현한 익명클래스를 이용하여 Thread객체를 생성한다.
		Thread th3 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 200; i++) {
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					System.out.print("@");
				}
			}
		});
		th3.start();
		
	}
}

class MyThread1 extends Thread {
	
	@Override
	public void run() {
		
		for (int i = 1; i <= 200; i++) {
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.print("*");
		}
		
	}
	
}

class MyThread2 implements Runnable {

	@Override
	public void run() {
for (int i = 1; i <= 200; i++) {
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.print("$");
		}
	}
}