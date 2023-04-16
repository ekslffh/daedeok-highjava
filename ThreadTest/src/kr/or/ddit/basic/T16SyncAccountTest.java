package kr.or.ddit.basic;

/*
 	은행의 입출금을 스레드로 처리하는 예제
 	(synchronized를 이용한 동기화 처리)
 */
public class T16SyncAccountTest {
	public static void main(String[] args) {
		
		SyncAccount sAcc = new SyncAccount();
		sAcc.deposit(10000); // 입금
		
		BankThread bTh1 = new BankThread(sAcc);
		bTh1.start();
		BankThread bTh2 = new BankThread(sAcc);
		bTh2.start();
		
	}
}

// 은행의 입출금을 관리하는 클래스
class SyncAccount {
	
	private int balance; // 잔액

	public synchronized int getBalance() {
		return balance;
	}
	
	// 입금처리를 수행하는 메서드
	public void deposit(int money) {
		balance += money;
	}
	
	// 출금을 처리하는 메서드(출금 성공: true, 출금 실패: false반환)
	// 동기화 영역에서 호출하는 메서드도 동기화 처리를 해 주어야 한다.
	
	/*
	 * 추가설명
	 * 애초에 공유되는 데이터를 사용하려면 어떠한 동기화 처리가 필요하다.
	 * 예시로 계좌출금시에 잔액이 충분할 경우에 접근이 가능해야 하기 때문에 순차적으로 하나씩 처리가 되야 한다는 것이다.
	 * 만약에 여러개의 스레드가 동시 접근시에는 처음 if문에서는 둘다 true여서 둘다 접근하고 결과적으로 잔액이 음수로 될수도 있다는 것이다.
	 * 따라서 synchronized키워드를 통해서 임계구역으로 지정해서 한번에 한 스레드씩 접근을 하도록 해야할 필요가 있다.
	 */
	public synchronized boolean withdraw(int money) {
		
		if (balance >= money) { // 잔액이 충분할 경우...
			for (int i = 1; i <= 1000000000; i++) {} // 시간 때우기용
			
			balance -= money;
			
			System.out.println("메서드 안에서 balance = " + getBalance());
			
			return true;
		} else {
			return false;
		}
	}
}

// 은행업무를 처리하기 위한 스레드
class BankThread extends Thread {
	private SyncAccount sAcc;
	
	public BankThread(SyncAccount sAcc) {
		this.sAcc = sAcc;
	}
	
	@Override
	public void run() {
		boolean result = sAcc.withdraw(6000); // 6000원 인출
		System.out.println("스레드 안에서 result = " + result
				+ ", balance = " + sAcc.getBalance());
	}
}