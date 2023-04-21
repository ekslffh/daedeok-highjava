package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T05ServletCookieTest extends HttpServlet {
/*
 	쿠키 정보에 대하여...
 	=> 웹서버와 브라우저는 애플리케이션을 사용하는 동안 필요한 값을 쿠키를 통해 공유하여 상태를 유지함.
 		
 	1. 구성요소?
 	- 이름
 	- 값
 	- 유효시간(초)
 	- 도메인: ex) www.somehost.com, .somehost.com
 	=> 쿠키의 도메인이 쿠키를 생성한 서버의 도메인을 벗어나면 브라우저는 쿠키를 저장(생성)하지 않는다.
 	- 경로 : 쿠키를 공유할 기준경로를 저장한다.
 		(지정하지 않으면 실행한 URL의 경로부분이 사용됨.)
 		
 	2. 동작방식
 	(서버단에서 브라우저에게 쿠키를 만들라고 한다. 즉, 쿠키는 브라우저가 만든다.)
 	- 쿠키생성단계 : 생성한 쿠키를 응답데이터의 헤더에 저장하여 브라우저에 전송한다.
 	- 쿠키저장단계 : 브라우저는 응답데이터에 포함된 쿠키를 쿠키저장소에 저장(보관)한다.
 	- 쿠키전송단계 : 브라우저는 저장한 쿠키를 요청이 있을때 마다 웹서버에 전송한다. 
 				(삭제되기 전까지...) 웹서버는 전송한 쿠키를 사용하여 필요한작업 수행함.
 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		// setCookieExam(req, resp); // 쿠키 설정(저장)하기
		// readCookieExam(req, resp); // 쿠키정보 읽기
		deleteCookieExam(req, resp); // 쿠키정보 삭제하기
		
	}
	
	private void deleteCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		/* 쿠키정보 삭제하는 방법..
		   
		   1. 사용중인 쿠키정보를 이용하여 쿠키객체를 생성한다.
		   2. 쿠키객체의 최대 지속시간을 0으로 설정한다.
		   3. 설정한 쿠키객체를 응답헤더에 추가하여 전송한다.
		*/
		Cookie[] cookies = req.getCookies();
		
		/////////////////////////////////////////////
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		String title = "쿠키정보 삭제 예제";
		
		out.println("<!doctype html><html><head><title>"
				+ title + "</title></head><body>");
		
		if (cookies != null) {
			
			out.println("<h2>" + title + "</h2>");
			
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("userId")) {
					// 쿠키 제거하기
					cookie.setMaxAge(0);
					resp.addCookie(cookie);
					out.println("<b>삭제요청한 쿠키</b> : " 
					+ cookie.getName() + "<br>");
				}
				out.print("<b>쿠키 이름</b> : " + cookie.getName() + "<br>");
				out.print("<b>쿠키 값</b> : " + cookie.getValue());
				out.print("<hr>");
			}
		} else {
			out.println("<h2>쿠키정보가 없습니다.</h2>");
		}

		out.println("</body></html>");
	}

	private void readCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		Cookie[] cookies = req.getCookies();
		
		////////////////////////////////////
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		String title = "쿠키정보 읽기 예제";
		
		out.println("<!doctype html><head><title>" 
					+ title + "</title></head>"
					+ "<body>");
		if (cookies != null) {
			out.println("<h2>" + title + "</h2>");
			
			for (Cookie cookie : cookies) {
				out.println("<b>name</b> : " + cookie.getName() + "<br>");
				out.println("<b>value</b> : " + cookie.getValue() + "<br>");
				out.println("<hr>");
			}
		} else {
			out.println("<h2>쿠키 정보가 없습니다.</h2>");
		}
		out.println("</body></html>");
		out.println("");
	}

	private void setCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		// 쿠키 생성하기
		Cookie userId = 
				new Cookie("userId", req.getParameter("userId"));
		Cookie name =
				new Cookie("name", req.getParameter("name"));
		
		// 쿠키 소멸시간(초단위) => 지정하지 않으면 브라우저 종료시 쿠키를 삭제한다.
		userId.setMaxAge(60 * 60 * 24); // 1일
		userId.setHttpOnly(true); // javascript 이용한 직접접근 방지
		
		name.setMaxAge(60 * 60 * 48); // 2일

		// 응답헤더에 쿠키 추가하기
		resp.addCookie(userId);
		resp.addCookie(name);
		
		//////////////////////////////////////
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		String title = "쿠키설정 예제";
		
		out.println("<!doctype html><html><head><title>"
				+ title + "</title></head>");
		out.println("<body>"
				+ "<h1 align=\"center\">" + title + "</h1>"
				+ "<ul><li><b>ID</b> : "
				+ req.getParameter("userId") + "</li>"
				+ "<li><b>이름</b> : "
				+ req.getParameter("name") + "</li></ul>"
				+ "</body></html>");
	}

	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
