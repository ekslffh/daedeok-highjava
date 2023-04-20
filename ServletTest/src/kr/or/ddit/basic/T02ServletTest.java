package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T02ServletTest extends HttpServlet {
/*
 	서블릿 동작 방식에 대하여...
 	
 	1. 사용자(클라이언트)가 URL을 클릭하면 HTTP Request를 서블릿 컨테이너로 전송(요청)함.
 	2. 컨테이너는 web.xml에 정의된 URL패턴을 확인하여 어느 서블릿을 통해 요청을 처리할지를 검색함.
 		(아직 로딩이 안된 경우에는 생성하여 적재함. 로딩시 init()메서드 호출됨.)
 	3. 서블릿 컨테이너는 요청을 처리할 개별 스레드를 생성하여 해당 서블릿 객체의 service()
 		메서드를 호출함. (HttpServletRequest 및 HttpServletResponse 객체를 생성하여
 		파라미터로 넘겨준다.)
 	4. service()메서드는 메서드타입을 체크하여 적절한 메서드를 호출한다.
 		(doGet, doPost, doPut, doDelete 등...)
 	5. 요청 처리가 완료되면 (HttpServletRequest 및 HttpServletResponse객체는 소멸된다.)
 	6. 컨테이너로부터 서블릿이 제거되는 경우에 destroy()메서드가 호출됨. 	
 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Request 객체의 메서드 확인하기
		System.out.println("req.getCharacterEncoding() : " 
				+ req.getCharacterEncoding());
		System.out.println("req.getContentLength() : " 
				+ req.getContentLength());
		System.out.println("req.getQueryString() : " 
				+ req.getQueryString());
		System.out.println("req.getProtocol() : " 
				+ req.getProtocol());
		System.out.println("req.getMethod() : " 
				+ req.getMethod());
		System.out.println("req.getRequestURI() : " 
				+ req.getRequestURI());
		System.out.println("req.getRequestedSessionId() : " 
				+ req.getRequestedSessionId());
		System.out.println("req.getHeaderNames() : " 
				+ req.getHeaderNames());
		System.out.println("req.getRemoteAddr() : " 
				+ req.getRemoteAddr());
		System.out.println("req.getRemotePort() : " 
				+ req.getRemotePort());
		System.out.println("-----------------------------------");
		
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		System.out.println("name => " + name);
		System.out.println("age => " + age);
		
		// 요청객체에 정보 저장하기
		req.setAttribute("tel", "1234-5678");
		req.setAttribute("addr", "대전시 중구 오류동");
		
		// 요청객체에 담긴 정보 꺼내오기
		System.out.println("tel => " + req.getAttribute("tel"));
		System.out.println("addr => " + req.getAttribute("addr"));
		
		//////////////////////////////////////////////////////////
		
		// 응답 메시지 인코딩 정보 설정하기
		// resp.setCharacterEncoding("UTF-8");
		// 응답 메시지의 컨텐트 타입 설정하기
		resp.setContentType("text/plain;charset=UTF-8");
		
		// 응답 내용 작성 시작....
		PrintWriter out = resp.getWriter();
		
		out.println("name => " + name);
		out.println("age => " + age);
		
		out.println("서블릿 경로 : " + req.getServletPath());
		out.println("컨텍스트 경로 : " + req.getContextPath());
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
