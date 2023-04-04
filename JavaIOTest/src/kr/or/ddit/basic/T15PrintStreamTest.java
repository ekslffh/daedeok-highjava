package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

/*
 	프린터 기능을 제공하는 보조  스트림
 */
public class T15PrintStreamTest {
	
	public static void main(String[] args) throws IOException {
		
		FileOutputStream fos = new FileOutputStream("D:/D_Other/print.txt");
		PrintStream out = new PrintStream(fos);
		out.print("안녕하세요. PrintStream입니다.\n");
		out.println("안녕하세요. PrintStream입니다2.");
		out.println("안녕하세요. PrintStream입니다3.");
		out.println(out); // 객체 출력
		out.print(3.14); // 더블값 출력
		
		out.close();
		
		//////////////////////////////////////////////
		
		FileOutputStream fos2 = new FileOutputStream("D:/D_Other/print2.txt");
		
		PrintWriter pw = new PrintWriter(
				new OutputStreamWriter(fos2, "CP949"));
		
		pw.print("안녕하세요. PrintWriter입니다.\n");
		pw.println("안녕하세요. PrintWriter입니다2.");
		pw.println("안녕하세요. PrintWriter입니다3.");
		pw.println(pw);
		
		pw.close();
	}
	
}
