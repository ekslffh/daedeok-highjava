package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.comm.service.AtchFileServiceImpl;
import kr.or.ddit.comm.service.IAtchFileService;
import kr.or.ddit.comm.vo.AtchFileVO;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/member/detail.do")
public class DetailMemberController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String memId = req.getParameter("memId");

		IMemberService memberService = 
				MemberServiceImpl.getInstance();
		
		IAtchFileService fileService = 
				AtchFileServiceImpl.getInstance();
		
		MemberVO mv = memberService.getMember(memId);
		
		AtchFileVO fileVO = new AtchFileVO();
		fileVO.setAtchFileId(mv.getAtchFileId());
				
		List<AtchFileVO> atchFileList 
			= fileService.getAtchFileList(fileVO);

		req.setAttribute("mv", mv);
		
		req.setAttribute("atchFileList", atchFileList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("" + "/WEB-INF/views/member/detail.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

}
