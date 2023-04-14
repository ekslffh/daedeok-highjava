package kr.or.ddit.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpChatServer {
	public static void main(String[] args) {

		ServerSocket serverSocket = null;
		Socket socket = null;
		
		try {
			System.out.println("접속 대기중 입니다.");
			serverSocket = new ServerSocket(7777);
			socket = serverSocket.accept();
			
			System.out.println("채팅서버에 사용자가 접속했습니다.");
			
			// 메시지 보내기용 스레드 생성 및 실행
			Sender sender = new Sender(socket);
			sender.start();
			
			// 메시지 받기용 스레드 생성 및 실행
			Receiver receiver = new Receiver(socket);
			receiver.start();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
}
