package com.shop.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;

public class AnswerWriteFormAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int qbno = Integer.parseInt(request.getParameter("qbno"));
		
		int pseq = Integer.parseInt(request.getParameter("pseq"));
		
		int ref = Integer.parseInt(request.getParameter("ref"));
		
		request.setAttribute("qbno", qbno);
		request.setAttribute("pseq", pseq);
		request.setAttribute("ref", ref);

	}

}
