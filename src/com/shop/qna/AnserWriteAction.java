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
import com.shop.dto.AnswerVO;
import com.shop.dto.QuestionVO;

public class AnserWriteAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();//세션객체 생성
		
		int sizeLimit = 5 * 1024 * 1024;
		String savePath = "product_images";
		ServletContext context = session.getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		
		MultipartRequest multi = new MultipartRequest(request, 
			uploadFilePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
		
		AnswerVO avo = new AnswerVO();
		
		avo.setTitle(multi.getParameter("title"));
		avo.setContent(multi.getParameter("content"));
		avo.setWriter(multi.getParameter("writer"));
		int qbno = Integer.parseInt(multi.getParameter("qbno"));
		int pseq = Integer.parseInt(multi.getParameter("pseq"));
		int ref = Integer.parseInt(multi.getParameter("ref"));
		avo.setRef(ref);
		avo.setQbno(qbno);
		
		QnaDAO qdao = QnaDAO.getInstance();
		qdao.insertAnswer(avo);
		
		response.sendRedirect("productdetail.do?pseq="+pseq);
	}

}
