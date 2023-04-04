package kr.or.ddit.basic;

public class T07CharTest {
	public static void main(String[] args) {
		
		char ch1 = 'A'; 	 // 문자를 직접 저장
		char ch2 = 65; 		 // 10진수로 저장
		char ch3 = '\u0041'; // 16진수로 저장(유니코드)
		
		char ch4 = '가'; 	 // 문자를 직접 저장
		char ch5 = 44032;    // 10진수로 저장
		char ch6 = '\uAC00'; // 16진수로 저장(유니코드)
		
		int uniCode = ch1;	 // 유니코드값 얻기
		
		System.out.println(ch1);
		System.out.println(ch2);
		System.out.println(ch3);
		System.out.println(ch4);
		System.out.println(ch5);
		System.out.println(ch6);
		System.out.println(uniCode);
		
	}
}
