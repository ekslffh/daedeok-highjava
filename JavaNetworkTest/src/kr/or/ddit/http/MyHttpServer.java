package kr.or.ddit.http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.StringTokenizer;

public class MyHttpServer {

	private final int port = 80;
	private final String encoding = "UTF-8";

	public void start() {

		System.out.println("HTTP 서버가 시작되었습니다.");

		try (ServerSocket server = new ServerSocket(this.port)) {

			while (true) {
				try {
					Socket socket = server.accept();

					// Http 서비스 처리 스레드 생성 및 시작
					HttpHandler handler = new HttpHandler(socket);
					handler.start();

				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Http 요청 처리를 위한 스레드 클래스
	 */
	class HttpHandler extends Thread {

		private final Socket socket;

		public HttpHandler(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			OutputStream out = null;
			BufferedReader br = null;

			try {
				out = new BufferedOutputStream(socket.getOutputStream());
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				// Request Line
				String reqLine = br.readLine();

				printMsg("request Line : ", reqLine);

				// 요청헤더 정보 파싱하기
				StringBuilder sb = new StringBuilder();

				while (true) {
					String str = br.readLine();

					if (str.equals(""))
						break; // emptyLine 체크

					sb.append(str + "\n");
				}

				printMsg("요청헤더 : ", sb.toString());

				String reqPath = ""; // 요청 경로

				// 요청페이지 정보 가져오기
				StringTokenizer st = new StringTokenizer(reqLine);
				while (st.hasMoreTokens()) {
					String token = st.nextToken();
					if (token.startsWith("/")) {
						reqPath = token;
						break;
					}
				}
				
				// URL 디코딩 처리(한글 깨짐 문제 처리)
				reqPath = URLDecoder.decode(reqPath, "UTF-8");

				// 파일 실제 경로
				String filePath = "./WebContent" + reqPath;

				// 해당 파일 이름을 이용하여 Content-Type 정보 추출하기
				String contentType = URLConnection.getFileNameMap().getContentTypeFor(filePath);

				// CSS파일인 경우 인식이 안되서 추가함.
				if (contentType == null && filePath.endsWith(".css")) {
					contentType = "text/css";
				}
				
				File file = new File(filePath);

				if (!file.exists()) {
					// 404 에러 메시지 전송
					makeErrorPage(out, 404, "Not Found");
					return;
				}

				byte[] body = makeResponseBody(filePath);

				byte[] header = makeResponseHeader(body.length, contentType);
				
				out.write(header); // 헤더정보 전송
				
				// 응답내용 보내기 전 반드시 Empty Line보내기...
				out.write("\r\n\r\n".getBytes());
				
				out.write(body); // 바디정보 전송
				
				out.flush(); // 강제 데이터 방출

			} catch (IOException ex) {

			} finally {
				try {
					socket.close();// 소켓 닫기 (연결종료)
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	/**
	 * 응답헤더 생성하기
	 * 
	 * @param length      응답내용(body) 크기
	 * @param contentType 마임타입
	 * @return
	 */
	private byte[] makeResponseHeader(int length, String contentType) {
		String header = "HTTP/1.1 200 OK\r\n" + "Server: MyHttpServer 1.0\r\n" + "Content-length: " + length
				+ "Content-Type: " + contentType + "; charset=" + this.encoding;
		return header.getBytes();
	}

	/**
	 * 응답 내용 생성하기
	 * 
	 * @param filePath 응답으로 사용될 파일경로
	 * @return 바이트배열 형태의 응답내용
	 */
	private byte[] makeResponseBody(String filePath) {

		FileInputStream fis = null;
		byte[] data = null;

		try {
			File file = new File(filePath);

			data = new byte[(int) file.length()];

			fis = new FileInputStream(file);
			fis.read(data);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return data;
	}

	private void makeErrorPage(OutputStream out, int statusCode, String errMsg) {

		String statusLine = "HTTP/1.1" + " " + statusCode + " " + errMsg;

		try {
			out.write(statusLine.getBytes());

			out.flush(); // 남아있는 모든 데이터 방출
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void printMsg(String title, String msg) {
		System.out.println("====================================");
		System.out.println(title);
		System.out.println("====================================");
		System.out.println(msg);
		System.out.println("------------------------------------");
	}

	public static void main(String[] args) {
		new MyHttpServer().start();
	}

}
