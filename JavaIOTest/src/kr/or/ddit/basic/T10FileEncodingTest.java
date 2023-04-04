package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class T10FileEncodingTest {
/*
 	한글 인코딩 방식에 대하여...
 	
 	한글 인코딩 방식은 크게 UTF-8과 EUC-KR 방식, 두가지로 나누어 볼 수 있다.
 	원래 한글윈도우는 CP949방식을 사용했는데, 윈도우를 개발한 마이크로소프트사에서
 	EUC-KR방식의 코드표에서 확장하여 사용했기 때문에 MS949라고도 부른다.
 	CP949는 EUC-KR의 확장이며, 하위 호환성이 있다.
 	- MS949 => 한글윈도우의 기본 한글인코딩 방식(ANSI계열)
 	- UTF-8 => 유니코드 UTF-8 인코딩 방식(영문자 및 숫자: 1byte, 한글: 3byte)
 	- US-ACII => 영문 전용 인코딩 방식
 	
 	ANSI는 영어를 표기하기 위해 만든 코드 규격으로, 자체에 한글이 없었다가 나중에 여기에
 	EUC-KR, CP949 이라는 식으로 한글이 포함되었음.
 */	
	public static void main(String[] args) {
		
		FileInputStream fis = null;
		InputStreamReader isr = null;
		
		try {
//			fis = new FileInputStream("D:/D_Other/test_ansi.txt");
			fis = new FileInputStream("D:/D_Other/test_utf8.txt");
			
			// 파일 인코딩 정보를 이용하여 읽어오기
			// InputStreamReader를 이용하여 파일 인코딩 방식을 지정할 수 있다.
			// 예) new InputStreamReader(바이트기반스트림객체, 인코딩방식)
			isr = new InputStreamReader(fis, "UTF-8");
			
			int data = 0;
			while ((data = isr.read()) != -1) {
				System.out.print((char) data);
			}
			System.out.println();
			System.out.println("출력 끝...");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				isr.close(); // 보조스트림만 닫아도 된다.
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
