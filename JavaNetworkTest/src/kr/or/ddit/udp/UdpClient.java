package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpClient {
	
	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private byte[] msg;
	
	public UdpClient() throws SocketException {
		msg = new byte[100];
		
		// 소켓 객체 생성(포트번호를 명시하지 않으면 이용가능한 임의의 포트번호가 할당됨.)
		ds = new DatagramSocket();
	}
	
	public void start() {
		
		try {
			InetAddress serverAddr = 
					InetAddress.getByName("192.168.143.3");
			
			dp = new DatagramPacket(msg, 1, serverAddr, 8888);
			ds.send(dp);
			
			dp = new DatagramPacket(msg, msg.length);
			ds.receive(dp);
			
			System.out.println("현재 서버 시간 => " 
						+ new String(dp.getData()));
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			ds.close();
		}
	}
	
	public static void main(String[] args) throws SocketException {
		new UdpClient().start();
	}
}
