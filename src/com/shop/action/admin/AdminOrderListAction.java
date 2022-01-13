package com.shop.action.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.OrderDAO;
import com.shop.dto.OrderVO;

public class AdminOrderListAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = "admin/order/orderlist.jsp";
		
		String key = "";
		if(request.getParameter("key") != null) {
			key = request.getParameter("key");
		}
		OrderDAO orderDAO = OrderDAO.getInstance();
		
		List<OrderVO> orderList = orderDAO.listOrder(key);
		
		request.setAttribute("orderList", orderList);
		request.getRequestDispatcher(page).forward(request, response);

	}

}
