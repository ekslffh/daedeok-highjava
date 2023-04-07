package kr.or.ddit.basic;

public class MySingleton {
/*
 	싱글톤 클래스를 구성하는 방법
 	
 	1. 자기자신 class의 참조변수를 멤버변수로 선언한다.
 		(이 변수는 private static으로 지정한다.)
 	2. 생성자를 private으로 한다.
		(외부에서 생성자에 접근을 못하도록 하기 위해서... 
		즉, 외부에서 new 명령을 사용하지 못하게 하기 위해서...)
	3. 객체(인스턴스)는 내부에서 생성해서 이 생성된 객체를 반환하는 메서드로 만든다.
		(이 메서드의 이름은 보통 getInstance()로 지정한다. 객체 생성없이 호출할 수 있도록
		static으로 선언한다.)
 */
	private static MySingleton single;
	
	private MySingleton() {
		System.out.println("생성자 입니다.");
	}
	
	public static MySingleton getInstance() {
		if (single == null) {
			single = new MySingleton();
		}
		return single;
	}
	
	public void displayText() {
		System.out.println("안녕하세요. 싱글톤 객체 입니다.");
	}
	
}
