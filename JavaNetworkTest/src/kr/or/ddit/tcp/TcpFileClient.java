package kr.or.ddit.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TcpFileClient {

	private Socket socket;
	private FileOutputStream fos;
	private DataInputStream dis;
	private DataOutputStream dos;

	public void clientStart() {
		File file = new File("D:/D_Other/loop.jpg"); // 요청 및 저장 파일 객체 생성

		try {
			socket = new Socket("192.168.143.8", 7777);

			// 소켓 접속이 성공하면 요청파일명을 전송한다.
			dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(file.getName());

			// 요청 결과를 받아온다.
			dis = new DataInputStream(socket.getInputStream());

			String resultMsg = dis.readUTF();
			if (resultMsg.equals("OK")) {
				fos = new FileOutputStream(file);

				BufferedInputStream bis = 
						new BufferedInputStream(socket.getInputStream());
				BufferedOutputStream bos = 
						new BufferedOutputStream(fos);

				int data = 0;
				while ((data = bis.read()) != -1) {
					bos.write(data);
				}

				bis.close();
				bos.close();
				socket.close();

				System.out.println("파일 다운로드 완료....");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new TcpFileClient().clientStart();
	}
}
