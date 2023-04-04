package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class T11FileEncodingTest {
	
	// 키보드로 입력한 내용을 파일로 저장하는데...
	// out_utf8.txt 파일은 'utf-8'인코딩 방식으로 저장하고 
	// out_ansi.txt 파일은 'cp949' 인코딩 방식으로 저장하시오.
	public static void main(String[] args) {
	// OutputStreamWriter => OutputStream을 Writer로 변환하는 스트림
	//						  이 스트림도 '인코딩방식'을 지정할 수 있다.
		
		InputStreamReader isr = new InputStreamReader(System.in);
		
		FileOutputStream fos1 = null;
		FileOutputStream fos2 = null;
		
		OutputStreamWriter osr1 = null;
		OutputStreamWriter osr2 = null;

		// 파일 출력용 기본스트림
		try {
				
			fos1 = new FileOutputStream("D:/D_Other/out_utf8.txt");
			fos2 = new FileOutputStream("D:/D_Other/out_ansi.txt");
			
			osr1 = new OutputStreamWriter(fos1, "UTF-8");
			osr2 = new OutputStreamWriter(fos2, "CP949");
			
			int data = 0;
			
			System.out.println("아무거나 입력하세요.");
			
			while ((data = isr.read()) != -1) {
				osr1.write(data);
				osr2.write(data);
			}
			
			System.out.println("작업 완료...");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				osr1.close();
				osr2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
