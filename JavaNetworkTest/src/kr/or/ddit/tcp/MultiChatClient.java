package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MultiChatClient {
	
	// 시작 메서드
	public void clientStart() {
		Socket socket = null;
		
		try {
			socket = new Socket("192.168.143.3", 7777);
			
			// 송신용 스레드 생성 및 실행
			ClientSender sender = new ClientSender(socket);
			sender.start();
			
			// 수신용 스레드 생성 및 실행
			ClientReceiver receiver = new ClientReceiver(socket);
			receiver.start();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	// 메시지를 전송하는 스레드
	class ClientSender extends Thread {

		private DataOutputStream dos;
		private Scanner scan;
		
		public ClientSender(Socket socket) {
			scan = new Scanner(System.in);
			
			try {
				dos = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			try {
				if (dos != null) {
					// 시작하자마자 자신의 대화명을 서버로 전송
					System.out.print("대화명 >> ");
					dos.writeUTF(scan.nextLine());
				}
				
				while (dos != null) {
					// 키보드로 입력받은 메시지를 서버로 전송
					dos.writeUTF(scan.nextLine());
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	// 수신용 스레드 클래스 정의
	class ClientReceiver extends Thread {
		private DataInputStream dis;
		
		public ClientReceiver(Socket socket) {
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			while (dis != null) {
				// 서버로부터 수신한 메시지  출력하기
				try {
					System.out.println(dis.readUTF());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new MultiChatClient().clientStart();
	}
}
