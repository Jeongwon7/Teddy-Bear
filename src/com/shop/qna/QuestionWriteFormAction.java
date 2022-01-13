package com.shop.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;

public class QuestionWriteFormAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int pseq = Integer.parseInt(request.getParameter("pseq"));
		
		request.setAttribute("pseq", pseq);

	}

}
