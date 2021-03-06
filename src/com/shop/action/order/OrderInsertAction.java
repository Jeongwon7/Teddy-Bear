package com.shop.action.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.command.Command;
import com.shop.dao.CartDAO;
import com.shop.dao.OrderDAO;
import com.shop.dto.CartVO;
import com.shop.dto.MemberVO;

public class OrderInsertAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		String page = "orderlist.do";
		
		if(loginUser == null) {
			page = "login_form.do";
		}else {
			List<CartVO> cartlist = CartDAO.getInstance().listCart(loginUser.getId());
			
			OrderDAO orderDAO = OrderDAO.getInstance();
			
			int maxOseq = orderDAO.insertOrder(cartlist, loginUser.getId());
			
			page = "orderlist.do?oseq="+maxOseq;
		}
		response.sendRedirect(page);

	}

}
