package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MultiChatServer {

	// 대화명을 key값으로 해서 클라이언트들의 소켓을 저장하기 위해 Map객체변수 선언
	private Map<String, Socket> clients;

	public MultiChatServer() {
		// 동기화 처리가 가능하도록 Map객체 동기화 처리
		clients = Collections.synchronizedMap(new HashMap<>());
	}

	// 서버 시작
	public void serverStart() {

		Socket socket = null;

		try (ServerSocket serverSocket = new ServerSocket(7777)) {
			System.out.println("서버가 시작되었습니다.");

			while (true) {
				// 클라이어트의 접속을 대기한다.
				socket = serverSocket.accept();

				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "] 에서 접속하였습니다.");

				// 메시지 전송을 처리하는 스레드 생성 및 실행
				ServerReceiver receiver = new ServerReceiver(socket);
				receiver.start();

			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// 서버에서 클라이언트로 메시지를 전송할 Thread를 Inner클래스로 정의
	// Inner클래스에서는 부모클래스의 멤버들을 직접 사용할 수 있다.
	class ServerReceiver extends Thread {
		private Socket socket;
		private DataInputStream dis;
		private String name;

		public ServerReceiver(Socket socket) {
			this.socket = socket;

			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 대화방. 즉, Map에 저장된 모든 사용자에게 안내 메시지를 전송하는 메서드
		 * @param msg 보낼 메시지
		 */
		public void sendMessage(String msg) {
			Iterator<String> it = clients.keySet().iterator();
			while (it.hasNext()) {
				try {
					String name = it.next(); // 대화명(키값) 구하기
					
					// 대화명에 해당하는 Socket객체 구해서 DataOutputStream객체 생성하기
					DataOutputStream dos = new DataOutputStream(clients.get(name).getOutputStream());
					
					dos.writeUTF(msg); // 메시지 보내기
					
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		
		/**
		 * 대화방. 즉, Map에 저장된 모든 사용자에게 안내 메시지를 전송하는 메서드
		 * @param msg 보낼 메시지
		 * @param from 메시지를 보낸 사람의 대화명
		 */
		public void sendMessage(String msg, String from) {
			
			sendMessage("[" + from + "] " + msg);
			
		}
		
		/**
		 * 특정 사용자에게 메시지를 전송하는 메서드
		 * @param msg 보낼 메시지
		 * @param from 보내는 사람의 대화명
		 * @param to 받는 사람의 대화명
		 */
		public void sendMessage(String msg, String from, String to) {
			if (clients.containsKey(to)) {
				try {
					DataOutputStream dos = new DataOutputStream(clients.get(to).getOutputStream());
					dos.writeUTF("[" + from + "님의 귓속말] " + msg);
					dos = new DataOutputStream(clients.get(from).getOutputStream());
					dos.writeUTF("[" + from + "님의 귓속말] " + msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		@Override
		public void run() {
			try {
				// 서버에서는 클라이언트가 보내는 최초의 메시지 즉, 대화명을 수신해야 한다.
				name = dis.readUTF();

				// 대화명을 받아서 다른 모든 클라이언트에게 대화방 참여 메시지를 보낸다.
				sendMessage("#" + name + "님이 입장했습니다.");

				// 대화명과 소켓객체를 Map에 저장한다.
				clients.put(name, socket);

				System.out.println("현재 서버 접속자 수는 " + clients.size() + "명 입니다.");

				// 이 이후의 메시지는 반복문으로 처리한다.
				// 한 클라이언트가 보낸 메시지를 다른 모든 클라이언트에게 보내준다.
				while (dis != null) {
					String msg = dis.readUTF();
					System.out.println("msg: " + msg);
					// 특정 상대에게만 메시지 보내기
					if (msg.startsWith("/w")) {
						String[] arr = msg.split(" ");
						String allMsg = "";
						for (int i = 2; i < arr.length; i++) {
							allMsg += arr[i];
						}
						sendMessage(arr[2], name, allMsg);
					} else {
						sendMessage(msg, name);
					}
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				// 이 영역이 실행된다는 것은 클라이언트의 접속이 종료되었다는 의미이다.
				sendMessage("#" + name + "님이 나가셨습니다.");

				// Map에서 해당 클라이언트를 제거한다.
				clients.remove(name);

				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "] 에서 접속을 종료했습니다.");

				System.out.println("현재 서버 접속자 수는 " + clients.size() + "명 입니다.");
			}
		}
	}
	
	public static void main(String[] args) {
		new MultiChatServer().serverStart();		
	}
}
