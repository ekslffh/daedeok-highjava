package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T03ServletParameterTest extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// POST방식으로 넘어오는 Body데이터를 인코딩 가져올 때 사용할 인코딩 정보를 설정함.
		// 반드시 request객체에서 값을 꺼내기 전에 먼저 설정해야 적용됨.
		req.setCharacterEncoding("UTF-8");
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String hobby = req.getParameter("hobby");
		String rlgn = req.getParameter("rlgn");
		
		String[] food = req.getParameterValues("food");
		
		///////////////////////////////////////////////
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		out.print("<html>");
		out.print("<body>");
		
		out.print("<p>username : " + username + "</p>");
		out.print("<p>password : " + password + "</p>");
		out.print("<p>gender : " + gender + "</p>");
		out.print("<p>hobby : " + hobby + "</p>");
		out.print("<p>rlgn : " + rlgn + "</p>");
		
		if (food != null) {
			for (String f : food) {
				out.print("<p>food : " + f + "</p>");
			}
		}
		
		Enumeration<String> params = req.getParameterNames();
		while (params.hasMoreElements()) {
			out.print("<p>파라미터 이름 : " + params.nextElement() 
						+ "</p>");
		}
		
		out.print("</body>");
		out.print("</html>");
	}

}
