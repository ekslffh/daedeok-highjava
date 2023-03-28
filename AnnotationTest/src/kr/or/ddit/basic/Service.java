package kr.or.ddit.basic;

public class Service {
	
	@PrintAnnotation
	private String name;
	
	@PrintAnnotation
	public void method1() {
		System.out.println("메서드1 입니다.");
	}
	
	@PrintAnnotation("%")
	public void method2() {
		System.out.println("메서드2 입니다.");
	}
	
	@PrintAnnotation(value = "#", count = 25)
	public void method3() {
		System.out.println("메서드3 입니다.");
	}
}
