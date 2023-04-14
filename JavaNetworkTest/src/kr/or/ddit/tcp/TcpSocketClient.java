package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpSocketClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		String serverIp = "127.0.0.1";
		// 자기 자신 컴퓨터(호스트)를 나타내는 방법
		// IP : 127.0.0.1
		// 컴이름 : localhost
		
		System.out.println(serverIp + " 서버에 접속 중 입니다...");
		
		// 소켓을 생성해서 서버에 연결을 요청한다.
		Socket socket = new Socket(serverIp, 7777);
		
		// 연결이 되면 이 후의 작업을 해주면 된다.
		System.out.println("연결되었습니다...");
		
		// 서버에서 보내온 메시지 받기
		// 소켓에서 InputStream 객체를 가져와 사용한다.
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		
		// 서버로부터 받은 메시지 출력하기
		System.out.println("받은 메시지 : " + dis.readUTF());
		System.out.println("연결종료.");
		
		dis.close();
	}
}
