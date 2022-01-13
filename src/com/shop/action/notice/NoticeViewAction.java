package com.shop.action.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.NoticeDAO;
import com.shop.dto.NoticeVO;

import utility.Criteria;

public class NoticeViewAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		int amount = Integer.parseInt(request.getParameter("amount"));
		
		Criteria cri = new Criteria(pageNum, amount);
		
		NoticeDAO ndao = NoticeDAO.getInstance();
		
		
		NoticeVO nvo = ndao.getNotice(bno);
		NoticeVO prev = ndao.prevByBno(bno);
		NoticeVO next = ndao.nextByBno(bno);
		
		request.setAttribute("cri", cri);
		request.setAttribute("view", nvo);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);

	}

}
