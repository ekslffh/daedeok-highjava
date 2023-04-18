package kr.or.ddit.tcp;

public class tempddd {

	public static void main(String[] args) {
		String a = "/wㅇㅇ 나성민 안녕하세요";
		String[] aList = a.split(" ");
		for (String t : aList) {
			System.out.println(t);
		}
		if (a.startsWith("/w")) {
			System.out.println("hel");
		}
	}

}
