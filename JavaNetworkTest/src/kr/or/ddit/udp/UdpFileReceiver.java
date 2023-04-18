package kr.or.ddit.udp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpFileReceiver {

	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private byte[] buffer;
	
	public UdpFileReceiver(int port) {
		
		try {
			ds = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	// 시작 메서드
	public void start() throws IOException {
		
		long fileSize = 0;
		long totalReadBytes = 0;
		
		int readBytes = 0;
		
		System.out.println("파일 수신 중...");
		
		String str = new String(receiveData()).trim();
		
		if (str.equals("start")) { // Sender에서 전송을 시작한 경우...
			
			// 전송 파일명 받기
			str = new String(receiveData()).trim();
			FileOutputStream fos = 
					new FileOutputStream("d:/D_Other/" + str);
			
			// 전송 파일 크기(bytes) 받기
			str = new String(receiveData()).trim();
			fileSize = Long.parseLong(str);
			
			long startTime = System.currentTimeMillis();
			
			while (true) {
				
				byte[] data = receiveData();
				
				readBytes = dp.getLength();
				
				fos.write(data, 0, readBytes);
				
				totalReadBytes += readBytes;
				
				System.out.println("진행 상태 : " + totalReadBytes + "/" + fileSize + " Byte(s) ("
						+ (totalReadBytes * 100 / fileSize) + " %)");
				
				if (totalReadBytes >= fileSize) {
					break;
				}
			}
			
			long endTime = System.currentTimeMillis();
			long diffTime = endTime - startTime;
			double transferSpeed = fileSize / diffTime;
			
			System.out.println("걸린 시간 : " + diffTime + " (ms)");
			System.out.println("평균 수신속도 : " + transferSpeed + "(Bytes/ms)");
			
			System.out.println("수신 처리 완료...");
			
			fos.close();
			ds.close();
		} else {
			System.out.println("비정상 데이터 발견!!!");
			ds.close();
		}
	}
	
	/**
	 * 바이트 배열 데이터 수신하기
	 * @return 수신한 바이트배열 데이터
	 * @throws IOException 
	 */
	private byte[] receiveData() throws IOException {
		buffer = new byte[1000]; // 버퍼 초기화
		
		dp = new DatagramPacket(buffer, buffer.length);
		ds.receive(dp);
		return dp.getData();
	}
	
	public static void main(String[] args) throws IOException {
		new UdpFileReceiver(8888).start();
	}
}
