package com.shop.qna;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.shop.command.Command;
import com.shop.dao.QnaDAO;
import com.shop.dto.QnaVO;

public class QnaListAjaxAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/x-json; charset=utf-8");
		
		String pseq = request.getParameter("pseq");
		
		QnaDAO qdao = QnaDAO.getInstance();
		
		List<QnaVO> qlist = qdao.getQnAListByProduct(pseq);
		
		Gson gson = new Gson();
		String QnaList = gson.toJson(qlist);
		//System.out.println("cmtList: "+cmtList);
		PrintWriter out = response.getWriter();
		out.print(QnaList);

	}

}
