package com.shop.action.review;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.ReviewDAO;

public class ReviewDeleteAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int rbno = Integer.parseInt(request.getParameter("rbno"));
		
		ReviewDAO rdao = ReviewDAO.getInstance();
		
		rdao.deleteReview(rbno);
		
		String msg = "삭제되었습니다";
		PrintWriter out = response.getWriter();
		out.print(msg);
	}

}
