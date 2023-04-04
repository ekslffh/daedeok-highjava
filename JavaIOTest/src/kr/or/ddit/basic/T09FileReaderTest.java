package kr.or.ddit.basic;

import java.io.FileReader;
import java.io.IOException;

public class T09FileReaderTest {
	public static void main(String[] args) {
		
		// 문자 기반 스트림을 이용한 파일내용 읽기
		FileReader fr = null;
		
		try {
			fr = new FileReader("D:/D_Other/testChar.txt");
			
			int data = 0;
			
			while ((data = fr.read()) != -1) {
				System.out.print((char) data);
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
