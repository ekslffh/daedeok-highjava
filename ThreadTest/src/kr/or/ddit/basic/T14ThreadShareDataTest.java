package kr.or.ddit.basic;

public class T14ThreadShareDataTest {
/*
 	스레드에서 데이터를 공통(공용)으로 사용하는 방법
 	
 	1. 공통으로 사용할 데이터를 클래스로 정의한다.
 	2. 공통으로 사용할 클래스의 인스턴스를 생성한다.
 	3. 이 공유객체를 각각의 스레드에 넘겨 준다.
 	4. 각각의 스레드는 이 인스턴스에 필요한 데이터를 저장하여 서로 공유한다.
 	
 	예) 원주율을 계산하는 스레드가 있고, 계산된 원주율을 출력하는 스레드가 있다.
 	   원주율을 계산한 후, 이 값을 출력하는 프로그램을 작성하시오.
 	   (이 때 계산된 원주율을 저장할 객체가 필요하다.)
 */
	
	public static void main(String[] args) {
		
		ShareData sd = new ShareData();
		CalcPIThread cTh = new CalcPIThread(sd);
		PrintPIThread pTh = new PrintPIThread(sd);
		cTh.start();
		pTh.start();
		
	}
}

// 원주율 정보를 관리하는 클래스(공통으로 사용할 클래스)
class ShareData {
	private double result; // 원주율이 저장될 변수
	
	/*
	 	volatile => 선언된 변수를 컴파일러의 최적화 대상에서 제외시킨다.
	 		즉, 값이 변경되는 즉시 변수에 적용시킨다. 다중스레드에서 하나의
	 		변수가 완벽하게 한번에 작동하도록 보장하는 키워드(일종의 동기화)
	 		추가설명: 멀티스레드의 경우 성능상의 이유로 캐시를 사용하는데 non-volatile의 경우에는
	 		언제 메인메모리에 변경된 데이터가 쓰일지 읽어들일지 보장이 되지 않기 때문에
	 		공유데이터를 통해서 어떠한 즉시 반영되는 작업이 필요한 경우에는 해당 변수에 대하여 volatile키워드를 붙여서
	 		데이터가 메모리에 즉시 반영되도록 해줘야 한다.
	 */
	volatile private boolean isOk; // 원주율 계산이 완료되었는지 확인하기 위한 변수
	
	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}
	public boolean isOk() {
		return isOk;
	}
	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}
}

// 원주율을 계산하기 위한 스레드
class CalcPIThread extends Thread {
	
	private ShareData sd;
	
	public CalcPIThread(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
	/*
	 	원주율 = (1/1 - 1/3 + 1/5 - 1/7 + 1/9 ......) * 4;
	 			1  -  3  +  5  -  7  +  0  => 분모
	 			0     1     2     3     4  => 분모를 2로 나눈 몫 
	 */
		double sum = 0.0;
		
		for (int i = 1; i <= 10000000; i += 2) {
			if (((i / 2) % 2) == 0) { // 2로 나눈 몫이 짝수이면... +
				sum += (1.0 / i);
			} else { // 2로 나눈 몫이 홀수이면... -
				sum -= (1.0 / i);
			}
		}
		
		sd.setResult(sum * 4); // 계산된 원주율을 공통 객체의 멤버변수에 저장
		sd.setOk(true); // 계산이 완료되었음을 알려준다.
	}
}

// 계산된 원주율을 출력하는 스레드
class PrintPIThread extends Thread {
	private ShareData sd;
	
	public PrintPIThread(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
		while (true) {
			
			// 원주율 계산이 완료될 때까지 기다린다.
			if (sd.isOk()) {
				break;
			}
		}
		System.out.println();
		System.out.println("계산된 원주율 : " + sd.getResult());
		System.out.println("      PI : " + Math.PI);
	}
}
