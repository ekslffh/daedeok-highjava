package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyImage {
	public static void main(String[] args) {
		
		// 사진을 읽어와서 복사본으로 저장한다.
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			fis = new FileInputStream("D:/D_Other/Tulips.jpg");
			fos = new FileOutputStream("D:/D_OTher/복사본_Tuplips.jpg");
			byte[] temp = new byte[100];
			int readBytes = 0;
			while ((readBytes = fis.read(temp)) != -1) {
				fos.write(temp, 0, readBytes);
			} 
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
