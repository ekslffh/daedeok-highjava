package kr.or.ddit.udp;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpFileSender {
	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private InetAddress receiveAddr;
	private int port;
	
	private File file;
	
	public UdpFileSender(String receiveIp, int port) throws IOException {
		
		ds = new DatagramSocket();
		this.port = port;
		receiveAddr = InetAddress.getByName(receiveIp);
		
		file = new File("D:/D_Other/aaa.jpg");
		
		if (!file.exists()) {
			System.out.println("파일이 존재하지 않습니다.");
			System.exit(0);
		}
		
	}
	
	public void start() {
		long fileSize = file.length();
		long totalReadBytes = 0;
		
		long startTime = System.currentTimeMillis();
		
		try {
			// 파일 전송시작을 알려주기 위한 문자열 전송
			sendData("start".getBytes());
			
			// 파일명을 전송
			sendData(file.getName().getBytes());
			
			// 총 파일 크기 정보를 전송
			sendData(String.valueOf(fileSize).getBytes());
			
			// 실제 파일 전송 작업 시작...
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
}
