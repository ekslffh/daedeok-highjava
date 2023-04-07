package kr.or.ddit.basic;

public class SingletonTest {

	public static void main(String[] args) {
		
		// MySingleton test1 = new MySingleton(); // new 명령 사용불가
		
		// getInstance()메서드를 호출하여 객체 생성
		MySingleton test2 = MySingleton.getInstance();
		test2.displayText();
		
		MySingleton test3 = MySingleton.getInstance();
		test3.displayText();
		
		System.out.println("test2 => " + test2);
		System.out.println("test3 => " + test3);
		
	}
}
