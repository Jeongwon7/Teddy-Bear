package com.shop.qna;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.QnaDAO;

public class QuestionDeleteAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int qbno = Integer.parseInt(request.getParameter("qbno"));
		int abno = Integer.parseInt(request.getParameter("abno"));
		
		QnaDAO qdao = QnaDAO.getInstance();
		
		qdao.deleteQuestion(qbno, abno);
		
		String msg = "삭제되었습니다";
		PrintWriter out = response.getWriter();
		out.print(msg);

	}

}
