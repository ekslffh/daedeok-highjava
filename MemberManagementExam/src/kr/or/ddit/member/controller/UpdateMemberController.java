package kr.or.ddit.member.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.or.ddit.comm.service.AtchFileServiceImpl;
import kr.or.ddit.comm.service.IAtchFileService;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@MultipartConfig
@WebServlet("/member/update.do")
public class UpdateMemberController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memId = req.getParameter("memId");
		
		IMemberService memberService = 
				MemberServiceImpl.getInstance();
		
		MemberVO mv = memberService.getMember(memId);
		
		System.out.println("mv정보: " + mv);
		req.setAttribute("mv", mv);
		
		RequestDispatcher dispatcher =
				req.getRequestDispatcher(""
						+ "/WEB-INF/views/member/updateForm.jsp");
		
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memId = req.getParameter("memId");
		String memPass = req.getParameter("memPass");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");
		
		String memPhoto = req.getParameter("memPhoto");
		
		// 서비스 객체 생성
		IMemberService memService = 
				MemberServiceImpl.getInstance();
		IAtchFileService fileService =
				AtchFileServiceImpl.getInstance();
		
		MemberVO mv = new MemberVO(memId, memPass, memName, memTel, memAddr);
		System.out.println("사진넣기전 mv: " + mv);
		
		String newFileName = "";
		
		try {
			newFileName = fileService.saveAtchFile(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (newFileName == null || newFileName.equals("")) {
			// 새로운 파일이 없으면 기존파일 유지
			mv.setMemPhoto(memPhoto);
		} else {
			// 새로운 파일이 들어오면 새로운 프로필 사진으로 대체하기
			mv.setMemPhoto(newFileName);
		}
		System.out.println("사진넣은후 mv: " + mv);

		
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
