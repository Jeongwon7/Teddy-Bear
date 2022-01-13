package com.shop.action.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.shop.command.Command;

public class AdminProductImageAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		ServletContext svc = request.getServletContext();//프로젝트 이름 요청해서 svc가 가리키도록
		
		String path = svc.getRealPath("product_images");//업로드될 폴더 이름
		String encType = "utf-8";
		int uploadSizeMax = 20 * 1024 * 1024;
		
		//MultipartRequest 컴 오렐리
		MultipartRequest mr = new MultipartRequest(//썸머노트 안에 첨부된 이미지 업로드 완료된다
				request,
				path,
				uploadSizeMax,
				encType,
				new DefaultFileRenamePolicy()
				);
		
		String file = mr.getFilesystemName("file");
		//form_data.append('file', file); 이것과 이름이 같아야한다
		//썸머노트 안에서 업로드 시킨 실제 파일의 이름을 구한다
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print("product_images"+"/"+file);// portcontent/이미지 이름.jpg 얘를 석세스 메서드로 리턴
		out.close();
	}

}
