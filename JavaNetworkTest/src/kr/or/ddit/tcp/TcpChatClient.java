package kr.or.ddit.tcp;

import java.io.IOException;
import java.net.Socket;

public class TcpChatClient {
	public static void main(String[] args) throws IOException {
		
		Socket socket = new Socket("192.168.143.8", 7777);
		
		Sender sender = new Sender(socket);
		sender.start();
		
		Receiver receiver = new Receiver(socket);
		receiver.start();
		
	}
}
