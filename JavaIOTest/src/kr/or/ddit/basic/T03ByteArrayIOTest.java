package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T03ByteArrayIOTest {
	public static void main(String[] args) throws IOException {
		
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		outSrc = new byte[inSrc.length]; // 공간 확보
		
		/* 직접 복사하는 방법
		for (int i = 0; i < inSrc.length; i++) {
			outSrc[i] = inSrc[i];
		}
		*/
		
		/* arrayCopy를 이용한 배열 복사방법  (깊은복사) 
		System.arraycopy(inSrc, 0, outSrc, 0, inSrc.length);
		System.out.println("직접 복사 후 outSrc => " + Arrays.toString(outSrc));
		System.out.println(Arrays.toString(inSrc));
		*/
		
		// 스트림 객체 생성 및 사용하기
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		// 읽어온 바이트 데이터를 저장하기 위한 변수
		int data = 0;
		
		// read() 메서드 => byte단위로 데이터를 읽어와 int형으로 반환한다.
		//				   더 이상 읽어올 데이터가 없으면 -1을 반환한다.
		while ((data = bais.read()) != -1) {
			baos.write(data);
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
