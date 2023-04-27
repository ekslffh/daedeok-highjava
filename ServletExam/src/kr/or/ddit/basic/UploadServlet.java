package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/upload.do")
public class UploadServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("================================");
		
		BufferedReader br = 
				new BufferedReader(
						new InputStreamReader(req.getInputStream()));
		
		String readLine = "";
		while ((readLine = br.readLine()) != null) {
			System.out.println(readLine);
		}
		
		System.out.println("================================");
	}
}
