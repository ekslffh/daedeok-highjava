package kr.or.ddit.basic;

import java.nio.charset.Charset;

public class Test {
	public static void main(String[] args) {
		for (char ch = 'a'; ch <= 'z'; ch++) {
			System.out.println(ch);
		}
		char ch1 = 'a';
		ch1 = ch1++;
		System.out.println(ch1);
		System.out.println("🐌");
		String s = 	"🐌";
		 // UTF-8로 인코딩된 이모티콘 문자열 생성
        String emoji = "\uD83D\uDE0A"; // 😊 이모티콘

        // UTF-8로 인코딩된 이모티콘을 MS949로 변환
        byte[] ms949Bytes = s.getBytes(Charset.forName("MS949"));

        // 변환된 바이트 배열을 문자열로 디코딩
        String ms949String = new String(ms949Bytes, Charset.forName("MS949"));

        // 변환 결과 출력
        System.out.println("UTF-8: " + emoji);
        System.out.println("MS949: " + ms949String);

	}
}
