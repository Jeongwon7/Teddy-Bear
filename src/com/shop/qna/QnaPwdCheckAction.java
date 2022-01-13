package com.shop.qna;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.QnaDAO;

public class QnaPwdCheckAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/x-json; charset=utf-8");
		
		int qbno = Integer.parseInt(request.getParameter("qbno"));
		String pwd = request.getParameter("pwd");
		
		QnaDAO qdao = QnaDAO.getInstance();
		
		int result = qdao.QnaPwdCheck(qbno, pwd);

		PrintWriter out = response.getWriter();
		out.print(result);
		

	}

}
