package com.shop.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.ProductDAO;
import com.shop.dao.QnaDAO;
import com.shop.dao.ReviewDAO;
import com.shop.dto.ProductDTO;
import com.shop.dto.QnaVO;
import com.shop.dto.QuestionVO;
import com.shop.dto.ReviewVO;

public class ProductiDetailAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String pseq = request.getParameter("pseq");//스트링으로 받아도 검색할 때 알아서 변환이 된다
		
		ProductDAO pdao =  ProductDAO.getInstance();
		
		ProductDTO pvo = pdao.getProduct(pseq);
		
		ReviewDAO rdao = ReviewDAO.getInstance();
		
		List<ReviewVO> rlist = rdao.getReviewList(pseq);
		
		QnaDAO qdao = QnaDAO.getInstance();
		
		List<QnaVO> qnalist = qdao.getQnAListByProduct(pseq);
		
		request.setAttribute("pvo", pvo);
		request.setAttribute("rlist", rlist);
		request.setAttribute("qnalist", qnalist);
	}

}
