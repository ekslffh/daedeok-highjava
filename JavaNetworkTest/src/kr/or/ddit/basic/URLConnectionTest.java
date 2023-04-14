package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class URLConnectionTest {
	public static void main(String[] args) throws IOException {
		
		// URLConnection => 애플리케이션과 URL(서버)간의 통신연결을 위한 추상 클래스
		
		// 네이버 서버에 연결하여 정보 및 데이터 가져오기
		URL url = new URL("https://www.naver.com/index.html");
	
		// Header 정보 가져오기
		URLConnection urlConn = url.openConnection();
		System.out.println("Content-Type : " + urlConn.getContentType());
		System.out.println("Encoding : " + urlConn.getContentEncoding());
		System.out.println("Content : " + urlConn.getContent());
		System.out.println();

		// 전체 Header정보 출력하기
		Map<String, List<String>> headerMap = urlConn.getHeaderFields();
		// Header의 Key값 구하기
		Iterator<String> iterator = headerMap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			System.out.println(key + " : " + headerMap.get(key));
		}
		System.out.println("-----------------------------------------------");
		
		InputStream is = (InputStream) urlConn.getContent();
		InputStreamReader isr = new InputStreamReader(is);
		
//		int data = 0;
//		while ((data = isr.read()) != -1) {
//			System.out.print((char) data);
//		}
		
		BufferedReader br = new BufferedReader(isr);
		
		String temp = "";
		while ((temp = br.readLine()) != null) {
			System.out.println(temp);
		}
		
		// 스트림 닫기
		br.close();
	}
}
