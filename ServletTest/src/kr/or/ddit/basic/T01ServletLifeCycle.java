package kr.or.ddit.basic;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T01ServletLifeCycle extends HttpServlet {
	
	public T01ServletLifeCycle() {
		System.out.println("T01ServletLifeCycle 생성자 호출됨.");
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		String initParam = config.getInitParameter("initParam");
		System.out.println("initParam : " + initParam);
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 실제적인 작업수행(서비스)이 시작되는 지점...(자바의 메인메서드 역할)
		System.out.println("service() 호출됨...");
		super.service(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 메서드 방식이 GET 방식인 경우 호출됨.
		System.out.println("doGet() 호출됨.");
		
		throw new ServletException("doGet메서드에서 예외가 발생했어요....");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 메서드 방식이 POST 방식인 경우 호출됨.
		System.out.println("doPost 호출됨.");
	}
	
	@Override
	public void destroy() {
		// 객체 소멸시(컨테이너로부터 서블릿 객체 제거시) 필요한 코드 작성...
		System.out.println("destroy() 호출됨...");
	}

}
