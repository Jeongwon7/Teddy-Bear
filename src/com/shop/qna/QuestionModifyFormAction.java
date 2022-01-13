package com.shop.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.QnaDAO;
import com.shop.dto.QuestionVO;

public class QuestionModifyFormAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int qbno = Integer.parseInt(request.getParameter("qbno"));
		
		QnaDAO qdao = QnaDAO.getInstance();
		
		QuestionVO qvo = qdao.getQuestionModify(qbno);
		
		request.setAttribute("qvo", qvo);

	}

}
