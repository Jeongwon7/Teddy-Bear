package com.shop.action.review;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.shop.command.Command;
import com.shop.dao.ReviewDAO;
import com.shop.dto.ReviewVO;

public class GetReviewAjaxAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/x-json; charset=utf-8");
		
		String pseq = request.getParameter("pseq");
		
		ReviewDAO rdao = ReviewDAO.getInstance();
		
		List<ReviewVO> rlist = rdao.getReviewList(pseq);
		
		Gson gson = new Gson();
		String reviewList = gson.toJson(rlist);
		//System.out.println("cmtList: "+cmtList);
		PrintWriter out = response.getWriter();
		out.print(reviewList);
		
		
	}

}
