package com.shop.action.cart;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.command.Command;
import com.shop.dao.CartDAO;
import com.shop.dto.CartVO;
import com.shop.dto.MemberVO;

public class CartListAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		String page = "mypage/cartlist.jsp";
		
		if(loginUser == null) {
			page = "login_form.do";
		}else {
			CartDAO cdao = CartDAO.getInstance();
			List<CartVO> cartlist = cdao.listCart(loginUser.getId());
			
			int totalPrice = 0;
			for(CartVO cvo : cartlist) {//향상된 for문
				totalPrice += cvo.getPrice2()*cvo.getQuantity();
			}
			request.setAttribute("cartList", cartlist);
			request.setAttribute("totalPrice", totalPrice);
		}
		
		request.getRequestDispatcher(page).forward(request, response);
		

	}

}
