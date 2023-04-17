package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UdpServer {
	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private byte[] msg; // 패킷 송수신을 위한 바이트 배열 선언
	
	public UdpServer(int port) {
		try {
			// 메시지 수신을 위한 포트번호 설정
			ds = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		} 
	}
	
	public void start() throws IOException {
		
		while (true) {
			
			// 데이터를 수신하기 위한 패킷 생성
			msg = new byte[1];
			dp = new DatagramPacket(msg, msg.length);
			
			System.out.println("패킷 수신 대기 중...");
			
			// 패킷을 통해 데이터를 수신(receive)한다.
			ds.receive(dp);
			
			System.out.println("패킷 수신 완료.");
			
			// 수신한 패킷으로부터 전송한 클라이언트의 IP주소와 port번호를 알아낸다.
			InetAddress iAddr = dp.getAddress();
			int port = dp.getPort();
			
			// 서버의 현재 시간을 시분초 형태([hh:mm:ss])로 반환한다.
			SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
			String time = sdf.format(new Date());
			msg = time.getBytes(); // 시간문자열을 byte배열로 반환함.
			
			// 패킷을 생성해서 클라이언트에게 전송(send)한다.
			dp = new DatagramPacket(msg, msg.length, iAddr, port);
			
			ds.send(dp);
			
			System.out.println("서버 현재시간 패킷 전송 완료.");
		}
	}
	
	public static void main(String[] args) throws IOException {
		new UdpServer(8888).start();
	}
	
}
