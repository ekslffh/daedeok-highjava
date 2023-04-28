package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload2.do")
@MultipartConfig(fileSizeThreshold = 1024*1024*3, maxFileSize = 1024*1024*40, maxRequestSize = 1024*1024*50)
public class UploadServlet2 extends HttpServlet {
	
	private static final String UPLOAD_DIR = "upload_files";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Multipart parsing 전에 파라미터 정보 조회해 보기
		System.out.println("Multipart parsing 전 : "
				+ req.getParameter("sender"));
		
		// 웹애플리케이션 루트 디렉토리 기준 업로드 경로 설정하기
		//	String uploadPath = req.getServletContext().getRealPath("/")
		//		+ UPLOAD_DIR;
		
		String uploadPath = "d:/D_Other/" + UPLOAD_DIR;
		File uploadDir = new File(uploadPath);
		
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		try {
			String fileName = "";
			
			for (Part part : req.getParts()) {
				
				System.out.println(
						part.getHeader("content-disposition"));
				
				fileName = getFileName(part);
				
				// 폼필드가 아니거나 파일이 비어있지 않은 경우...
				if (fileName != null && !fileName.equals("")) {
					part.write(
							uploadPath + File.separator + fileName); 
					System.out.println("파일명 => " + fileName
							+ "저장 완료!");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

	/**
	 * Part 정보를 파싱하여 파일이름 추출하기
	 * @param part 파일이름 추출할 Part객체
	 * @return 파일명: 파일명이 존재하지 않으면(폼필드인 경우) null 리턴함.
	 */
	private String getFileName(Part part) {
	/*
	 	Content-Disposition 헤더에 대하여...
	 	
	 	파일 업로드에서 사용되는 경우...
	 	
	 	Content-Disposition: form-data
	 	Content-Disposition: form-data; name="필드명"
	 	Content-Disposition: form-data; name="필드명"; filename="파일명"
	 */
		for (String content : 
			part.getHeader("Content-Disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf("=") + 1)
						.trim().replace("\"", "");
			}
		}
		
		return null; // filename이 없는 경우...(폼필드)
	}
	
}
