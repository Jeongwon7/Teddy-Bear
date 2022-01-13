package com.shop.action.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.NoticeDAO;
import com.shop.dto.NoticeVO;

public class NoticeModifyForm implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		NoticeDAO ndao = NoticeDAO.getInstance();
		
		NoticeVO nvo = ndao.modifyNoticeForm(bno);
		
		request.setAttribute("dto", nvo);

	}

}
