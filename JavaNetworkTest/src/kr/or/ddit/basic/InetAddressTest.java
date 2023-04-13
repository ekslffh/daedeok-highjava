package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException {
		// InetAddress 클래스 => IP주소를 다루기 위한 클래스
		
		// getByName()메서드는 www.naver.com 또는 SEM-PC 등과 같은 도메인(머신)이름이나
		// IP주소를 파라미터를 이용하여 유효한  InetAddress객체를 제공한다.
		// IP주소 자체를 파라미터로 넣으면 주소 구성 자체의 유효성을 체크한다.
		
		// 네이버 사이트 IP정보 가져오기
//		InetAddress naverIp = InetAddress.getByName("www.naver.com");
		InetAddress naverIp = InetAddress.getByName("www.ddit.or.kr");
		System.out.println("Host Name => " + naverIp.getHostName());
		System.out.println("Host Address => " + naverIp.getHostAddress());
		System.out.println();
		
		// 자기 자신 컴퓨터의 IP정보 가져오기
		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println("내 컴퓨터의 Host Name => " + localIp.getHostName());
		System.out.println("내 컴퓨터의 Host Address => " + localIp.getHostAddress());
		System.out.println();
		
		// IP주소가 여러개인 호스트의 정보 가져오기
		InetAddress[] naverIps = InetAddress.getAllByName("www.naver.com");
		for (InetAddress iAddr : naverIps) {
			System.out.println(iAddr.toString());
		}
	}

}
