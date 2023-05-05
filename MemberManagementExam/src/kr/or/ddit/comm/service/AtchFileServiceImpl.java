package kr.or.ddit.comm.service;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class AtchFileServiceImpl implements IAtchFileService {

	private static IAtchFileService atchFileService = new AtchFileServiceImpl();

	public static IAtchFileService getInstance() {
		return atchFileService;
	}

	@Override
	public String saveAtchFile(HttpServletRequest req) throws Exception {
		String uploadPath = req.getServletContext().getRealPath("/") + "images";
		File uploadDir = new File(uploadPath);

		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		String fileName = "";

		for (Part part : req.getParts()) {

			fileName = getFileName(part);

			if (fileName != null && !fileName.equals("")) { // 파일인 경우...

				String saveFilePath = uploadPath + File.separator + fileName;

				System.out.println("saveFilePath: " + saveFilePath);
				// 업로드파일(원본파일) 저장
				// part.write("d:/D_Other/sm/" + fileName);
				part.write(saveFilePath);
				System.out.println("saveFilePath: " + saveFilePath);
				System.out.println("fileName: " + fileName);
			}
		}

		return fileName;
	}

	/**
	 * Part 정보를 파싱하여 파일이름 추출하기
	 * 
	 * @param part 파일이름 추출할 Part객체
	 * @return 파일명: 파일명이 존재하지 않으면(폼필드인 경우) null 리턴함.
	 */
	private String getFileName(Part part) {
		/*
		 * Content-Disposition 헤더에 대하여...
		 * 
		 * 파일 업로드에서 사용되는 경우...
		 * 
		 * Content-Disposition: form-data Content-Disposition: form-data; name="필드명"
		 * Content-Disposition: form-data; name="필드명"; filename="파일명"
		 */
		for (String content : part.getHeader("Content-Disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf("=") + 1).trim().replace("\"", "");
			}
		}
		return null; // filename이 없는 경우...(폼필드)
	}

}
