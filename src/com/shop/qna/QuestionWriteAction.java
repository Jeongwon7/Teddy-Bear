package com.shop.qna;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.shop.command.Command;
import com.shop.dao.QnaDAO;
import com.shop.dao.ReviewDAO;
import com.shop.dto.MemberVO;
import com.shop.dto.QuestionVO;
import com.shop.dto.ReviewVO;

public class QuestionWriteAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();//세션객체 생성
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		int sizeLimit = 5 * 1024 * 1024;
		String savePath = "product_images";
		ServletContext context = session.getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		
		MultipartRequest multi = new MultipartRequest(request, 
			uploadFilePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
		
		QuestionVO qvo = new QuestionVO();
		
		qvo.setTitle(multi.getParameter("title"));
		qvo.setContent(multi.getParameter("content"));
		qvo.setWriter(loginUser.getId());
		qvo.setCategory(Integer.parseInt(multi.getParameter("category")));
		int pseq = Integer.parseInt(multi.getParameter("pseq"));
		qvo.setPseq(pseq);
		
		QnaDAO qdao = QnaDAO.getInstance();
		qdao.insertQuestion(qvo);
		
		response.sendRedirect("productdetail.do?pseq="+pseq);

	}

}
