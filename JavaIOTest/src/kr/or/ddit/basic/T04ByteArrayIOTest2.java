package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04ByteArrayIOTest2 {
	public static void main(String[] args) throws IOException {
		
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		// 데이터를 읽을 때 사용할 바이트 배열
		byte[] temp = new byte[4];
		
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		// 읽어온 바이트 수
		int readBytes = 0;
		
		while ((readBytes = bais.read(temp)) != -1) {
			System.out.println("readBytes : " + readBytes);
			System.out.println("temp : " + Arrays.toString(temp));
			
			// temp배열 내용을 출력한다.
			baos.write(temp, 0, readBytes);
		}
		
		// 출력된 스트림 값들을 배열로 변환해서 반환하는 메서드
		outSrc = baos.toByteArray();
		
		System.out.println("inSrc => "
				+ Arrays.toString(inSrc));
		
		System.out.println("outSrc => "
				+ Arrays.toString(outSrc));
		
		bais.close();
		baos.close();
	}
}
