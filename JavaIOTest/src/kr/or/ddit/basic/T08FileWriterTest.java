package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class T08FileWriterTest {
	
	public static void main(String[] args) {
		// 사용자가 입력한 내용을 그대로 파일로 지정하기
		
		// 콘솔(표준 입출력장치)과 연결된 입력을 문자 스트림 생성
		// InputStreamReader => 바이트 기반 스트림을 문자 기반으로 
		//						변환해 주는 보조스트림
		InputStreamReader isr = new InputStreamReader(System.in);
		
		FileWriter fw = null;
		
		try {
			// 파일 출력용 문자기반 스트림 객체  생성하기
			fw = new FileWriter("D:/D_Other/testChar.txt");
			
			int data = 0;
			
			System.out.println("아무거나 입력하세요.");
			
			// 콘솔에서 입력을 할때 입력의 끝을 의미하는 표시는 Ctrl + Z키를 
			// 누르면 된다. (EOF)
			while ((data = isr.read()) != -1) {
				System.out.println(data);
				fw.write(data); // 콘솔에서 입력받은 값을 파일에 출력하기
			}
			
			System.out.println("작업 끝...");
			
			isr.close(); // 보조스트림 닫기
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
