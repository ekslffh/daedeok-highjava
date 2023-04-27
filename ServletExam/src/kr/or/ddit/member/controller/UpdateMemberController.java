package kr.or.ddit.member.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/member/update.do")
public class UpdateMemberController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memId = req.getParameter("memId");
		
		IMemberService memberService = 
				MemberServiceImpl.getInstance();
		
		MemberVO mv = memberService.getMember(memId);
		
		req.setAttribute("mv", mv);
		
		RequestDispatcher dispatcher =
				req.getRequestDispatcher(""
						+ "/WEB-INF/views/member/updateForm.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");
		
		// 서비스 객체 생성
		IMemberService memService = 
				MemberServiceImpl.getInstance();
		
		MemberVO mv = new MemberVO(memId, memName, memTel, memAddr);
		
		// 회원정보 수정하기
		int cnt = memService.modifyMember(mv);
		
		String msg = "";
		
		if (cnt > 0) {
			msg = "성공";
		} else {
			msg = "실패";
		}
		
		req.getSession().setAttribute("msg", msg);
		
//		req.getRequestDispatcher("/member/list.do").forward(req, resp); // 요청1번? : url은 그대로지만 화면만 옮기는 느낌
		
		resp.sendRedirect(req.getContextPath() + "/member/list.do"); // 브라우저의 요청이 2번 날라가는 효과 : 서버에서 다른 곳으로 이동하라고 브라우저에 요청
	}
}
