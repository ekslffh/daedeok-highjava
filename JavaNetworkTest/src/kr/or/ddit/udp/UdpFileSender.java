package kr.or.ddit.udp;

import java.io.File;
import java.io.FileInputStream;
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

		file = new File("D:\\2.work\\vscode\\images\\너굴맨.jpg");

		if (!file.exists()) {
			System.out.println("파일이 존재하지 않습니다.");
			System.exit(0);
		}

	}

	public void start() throws InterruptedException {
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
			FileInputStream fis = new FileInputStream(file);

			byte[] buffer = new byte[1000];

			while (true) {
				
				Thread.sleep(10); // 패킷 전송간의 간격을 주기 위해서...

				int readBytes = fis.read(buffer, 0, buffer.length);

				if (readBytes == -1) { // 파일내용을 다 읽은 경우...
					break;
				}

				sendData(buffer, readBytes);

				totalReadBytes += readBytes;

				System.out.println("진행 상태 : " + totalReadBytes + "/" + fileSize + " Byte(s) ("
						+ (totalReadBytes * 100 / fileSize) + " %)");
			}

			long endTime = System.currentTimeMillis();
			long diffTime = endTime - startTime;
			double transferSpeed = fileSize / diffTime;

			System.out.println("걸린 시간 : " + diffTime + " (ms)");
			System.out.println("평균 전송속도 : " + transferSpeed + "(Bytes/ms)");

			fis.close();
			ds.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 바이트 배열 데이터 전송하기
	 * 
	 * @param data 전송할 데이터
	 * @throws IOException
	 */
	private void sendData(byte[] data) throws IOException {
		dp = new DatagramPacket(data, data.length, receiveAddr, port);
		ds.send(dp);
	}

	/**
	 * 바이트 배열 데이터 전송하기
	 * 
	 * @param data   전송할 바이트배열 데이터
	 * @param length 실제 바이트 수
	 * @throws IOException
	 */
	private void sendData(byte[] data, int length) throws IOException {
		dp = new DatagramPacket(data, length, receiveAddr, port);
		ds.send(dp);
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		new UdpFileSender("192.168.143.36", 8888).start();
	}
}
