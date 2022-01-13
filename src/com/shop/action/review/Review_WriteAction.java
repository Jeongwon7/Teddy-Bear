package com.shop.action.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;

public class Review_WriteAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int pseq = Integer.parseInt(request.getParameter("pseq"));
		
		request.setAttribute("pseq", pseq);

		request.getRequestDispatcher("review/review_write.jsp").forward(request, response);
	}

}
