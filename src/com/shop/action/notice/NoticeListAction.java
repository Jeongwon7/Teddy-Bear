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
import utility.PageVO;

public class NoticeListAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//검색
		String sel="";
		String word="";
		String query="";
				
		int pageNum = 1;
		int amount = 10;
				
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			amount = Integer.parseInt(request.getParameter("amount"));
		}
				
		if(request.getParameter("sel") != null && !request.getParameter("word").equals("")) {
			sel = request.getParameter("sel");//title 이나 content가 들어간다
			word = request.getParameter("word");
			query = sel + " like '%" + word + "%'";// title like '%코딩%'
		}
				
		Criteria cri = new Criteria();
				
		cri.setPageNum(pageNum);
		cri.setAmount(amount);
		cri.setType(sel);
		cri.setKeyword(word);
				
				
		//리스트 출력
		NoticeDAO dao = NoticeDAO.getInstance();
				
		List<NoticeVO> list = dao.getNoticeListPaging(cri, query);
				
		int count=dao.noticeCount(query);
				
		PageVO pvo = new PageVO(cri, count);
				
		request.setAttribute("pageMaker", pvo);
		request.setAttribute("noticeList", list);
		request.setAttribute("count", count);//속성은 여러개 만들 수 있음

	}

}
