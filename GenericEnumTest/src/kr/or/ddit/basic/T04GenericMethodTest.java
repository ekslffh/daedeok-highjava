package kr.or.ddit.basic;

class Util2 {
	public static <T extends Number> int compare(T t1, T t2) {
		double v1 = t1.doubleValue();
		double v2 = t2.doubleValue();
		
		return Double.compare(v1, v2);
	}
}

public class T04GenericMethodTest {
	public static void main(String[] args) {
		
		int result = Util2.compare(10, 20);
		System.out.println(result);
		
		result = Util2.compare(3.14, 3);
		System.out.println(result);
		
	}
}
