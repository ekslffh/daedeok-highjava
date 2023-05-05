package kr.or.ddit.comm.service;

import javax.servlet.http.HttpServletRequest;

public interface IAtchFileService {
	
	/**
	 * 첨부파일 목록을 저장하기 위한 메서드
	 * @param req Part정보를 꺼내기 위한 요청객체
	 * @return 첨부파일ID를 저장하고 있는 AtchFileVO객체
	 * @throws Exception 
	 */
	public String saveAtchFile(HttpServletRequest req) throws Exception;
	
}
