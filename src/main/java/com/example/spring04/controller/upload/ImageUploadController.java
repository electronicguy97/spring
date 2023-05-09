package com.example.spring04.controller.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageUploadController {
	
	@RequestMapping("imageUpload.do")
	public void imageUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) throws Exception {
		response.setCharacterEncoding("utf-8"); //인코딩 설정 
		response.setContentType("text/html;charset=utf-8"); // 컨텐트 타입 설정
		OutputStream out=null;
		PrintWriter printWriter=null;
		String fileName=upload.getOriginalFilename(); //첨부파일의 이름 
		byte[] bytes=upload.getBytes(); //첨부파일을 바이트 배열로 변환
		ServletContext application=request.getSession().getServletContext();
		String uploadPath=application.getRealPath("/WEB-INF/views/images/"); //배포 디렉토리
		out=new FileOutputStream(new File(uploadPath+fileName)); 
		out.write(bytes); // 파일 저장
		printWriter=response.getWriter();
		String fileUrl=request.getContextPath()+"/images/"+fileName;
		//json 형식으로 콜백함수에 값 전달  
		printWriter.println("{\"filename\" : \"" + fileName +"\",\"uploaded\" : 1, \"url\": \"" + fileUrl + "\"}");
		printWriter.flush();
	}

}




