package com.shop.action.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.ReviewDAO;
import com.shop.dto.ReviewVO;

public class ReviewModifyFormAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int rbno = Integer.parseInt(request.getParameter("rbno"));
		
		ReviewDAO rdao = ReviewDAO.getInstance();
		
		ReviewVO rvo = rdao.getReview(rbno);
		
		request.setAttribute("rvo", rvo);
	}

}
