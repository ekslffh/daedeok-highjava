package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 	입출력 성능향상을 위한 보조스트림 예제
 	(문자기반의 Buffered스트림 예제)
 */
public class T13BufferedIOTest {
	public static void main(String[] args) {
		
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader("./src/kr/or/ddit/basic/T12BufferedIOTest.java");
			br = new BufferedReader(fr);
			
			String temp = "";
			
			int cnt = 1;
			while ((temp = br.readLine()) != null) {
				System.out.printf("%4d : %s\n", cnt++, temp);
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
