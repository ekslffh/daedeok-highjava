package kr.or.ddit.basic;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T09ServletContextListenerTest extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ServletContext ctx = req.getServletContext();
		
		ctx.setAttribute("ATTR1", "속성1"); // 추가
		ctx.setAttribute("ATTR1", "속성11"); // 변경
		ctx.setAttribute("ATTR2", "속성2"); // 추가
		ctx.removeAttribute("ATTR1"); // 삭제
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
