package kr.or.ddit.basic;

public class Test {
	public static void main(String[] args) {
		for (char ch = 'a'; ch <= 'z'; ch++) {
			System.out.println(ch);
		}
		char ch1 = 'a';
		ch1 = ch1++;
		System.out.println(ch1);
	}
}
