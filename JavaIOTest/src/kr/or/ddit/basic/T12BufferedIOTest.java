package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 	입출력 성능향상을 위한 보조스트림 예제
 	(바이트기반의 Buffered스트림 예제)
 */
public class T12BufferedIOTest {
	public static void main(String[] args) {
		
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		
		try {
			fos = new FileOutputStream("D:/D_Other/bufferTest.txt");
			
			// 버퍼의 크기를 지정하지 않으면 기본적으로 버퍼의 크기는 
			// 8192byte(8Kb)로 설정된다.
			bos = new BufferedOutputStream(fos, 5);
			
			for (char ch = '1'; ch <= '9'; ch++) {
				bos.write(ch);
			}
			
			System.out.println("작업 끝...");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
