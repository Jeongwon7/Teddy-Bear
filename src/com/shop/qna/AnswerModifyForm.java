package com.shop.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.QnaDAO;
import com.shop.dto.AnswerVO;
import com.shop.dto.QuestionVO;

public class AnswerModifyForm implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int abno = Integer.parseInt(request.getParameter("abno"));
		int pseq = Integer.parseInt(request.getParameter("pseq"));
		
		QnaDAO qdao = QnaDAO.getInstance();
		
		AnswerVO avo = qdao.getAnswerModify(abno);
		
		request.setAttribute("avo", avo);
		request.setAttribute("pseq", pseq);
		request.setAttribute("abno", abno);
	}

}
