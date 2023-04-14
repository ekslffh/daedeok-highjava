package kr.or.ddit.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Sender extends Thread {
	
	private DataOutputStream dos;
	private String name;
	private Scanner scan;
	
	public Sender(Socket socket) {
		
		name = "[" + socket.getInetAddress() + " : " 
				+ socket.getLocalPort() + "]";
		scan = new Scanner(System.in);
		
		try {
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while (dos != null) {
			try {
				dos.writeUTF(name + " >>> " + scan.nextLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
