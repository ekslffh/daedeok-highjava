package kr.or.ddit.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 은행의 입출금을 스레드로 처리하는 예제
 * (Lock 객체를 이용한 동기화 처리)
 */
public class T17LockAccountTest {
	
	public static void main(String[] args) {
		
		/*
		 	락 기능을 제공하는 클래스
		 	
		 	ReentrantLock : Read 및 Write 구분없이 사용하기 위한 락 클래스로 동기화 처리를
		 		위해 사용된다. synchronized를 이용한 동기화 처리보다 부가적인 기능이 제공됨.
		 		ex) fairness 설정 등. => 오래 기다린 스레드가 가장 먼저 락 획득함.
		 		
		 	ReentrantReadWriteLock : Read 및 Write락을 구분하여 사용가능함.
		 	 		여러 스레드가 동시에 read작업은 가능하지만 write작업은 단지 하나의 스레드만
		 	 		가능함(exclusive)
		 	 		=> Write 보다 Read위주의 작업이 많이 발생하는 경우에 사용하면
		 	 		      처리량(Throughput)이 좋아진다.
		 */
		
		ReentrantLock lock = new ReentrantLock(true);
		
		LockAccount lAcc = new LockAccount(lock);
		lAcc.deposit(10000);
		
		BankThread2 bth1 = new BankThread2(lAcc);
		BankThread2 bth2 = new BankThread2(lAcc);
		
		bth1.start();
		bth2.start();
		
	}
}

// 입출금을 담당하는 클래스
class LockAccount {
	
	private int balance;
	
	public int getBalance() {
		return balance;
	}

	// Lock객체 변수 생성 => 되도록이면 private final로 만든다.
	private final Lock lock;
	
	public LockAccount(Lock lock) {
		this.lock = lock;
	}
	
	// 입금하는 메서드
	public void deposit(int money) {
		balance += money;
	}
	
	// 출금하는 메서드(출금 성공: true, 출금실패: false 반환)
	public boolean withdraw(int money) {
		boolean chk = false;
		
		// try ~ catch 블럭을 사용할 경우에 unlock()메서드 호출은
		// finally 블럭에서 하도록 한다.
		try {
			// 락 설정하기
			lock.lock();
			
			if (balance >= money) {
				for (int i = 0; i <= 1000000000; i++) {} // 시간 때우기용
				balance -= money;
				System.out.println("메서드 안에서 balance = " + getBalance());
				chk = true;
			} 
		} catch (Exception ex) {
			chk = false;
		} finally {
			lock.unlock(); // 락 해제
		}
		
		return chk;
	}
}

// 은행업무를 처리하는 스레드
class BankThread2 extends Thread {
	
	private LockAccount lAcc;
	
	public BankThread2(LockAccount lAcc) {
		this.lAcc = lAcc;
	}
	
	@Override
	public void run() {
		boolean result = lAcc.withdraw(6000);
		System.out.println(Thread.currentThread().getName()
				+ " 스레드 안에서 result = " + result
				+ " , balance = " + lAcc.getBalance());
	}
}