package com.shop.action.notice;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.shop.command.Command;
import com.shop.dao.NoticeDAO;
import com.shop.dto.NoticeVO;

public class NoticeWriteProAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();//세션객체 생성
		
		int sizeLimit = 5 * 1024 * 1024;
		String savePath = "product_images";
		ServletContext context = session.getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		
		MultipartRequest multi = new MultipartRequest(request, 
			uploadFilePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
		
		NoticeVO nvo = new NoticeVO();
		
		nvo.setTitle(multi.getParameter("title"));
		nvo.setContent(multi.getParameter("content"));
		nvo.setWriter(multi.getParameter("writer"));
		
		NoticeDAO ndao = NoticeDAO.getInstance();
		
		ndao.noticeInsert(nvo);

	}

}
