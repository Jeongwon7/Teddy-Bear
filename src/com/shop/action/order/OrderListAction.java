package com.shop.action.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.command.Command;
import com.shop.dao.OrderDAO;
import com.shop.dto.MemberVO;
import com.shop.dto.OrderVO;

public class OrderListAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		String page = "mypage/orderlist.jsp";
		
		if(loginUser == null) {
			page = "login_form.do";
		}else {
			OrderDAO orderDAO = OrderDAO.getInstance();
			int oseq = Integer.parseInt(request.getParameter("oseq"));
			List<OrderVO> orderlist = orderDAO.listOrderByID(loginUser.getId(), "1", oseq);
			
			int totalPrice = 0;
			
			for(OrderVO orderVO : orderlist) {
				totalPrice += orderVO.getPrice2()*orderVO.getQuantity();
			}
			
			request.setAttribute("orderLsit", orderlist);
			request.setAttribute("totalPrice", totalPrice);
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

}
