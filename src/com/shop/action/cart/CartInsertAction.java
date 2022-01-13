package com.shop.action.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.command.Command;
import com.shop.dao.CartDAO;
import com.shop.dto.CartVO;
import com.shop.dto.MemberVO;

public class CartInsertAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if(loginUser == null) {
			response.sendRedirect("login_form.do");
		}else {
			CartVO cvo = new CartVO();
			cvo.setId(loginUser.getId());//로그인유저에 mvo를 저장했으니까 개인정보 다 들어있음
			cvo.setPseq(Integer.parseInt(request.getParameter("pseq")));
			cvo.setQuantity(Integer.parseInt(request.getParameter("quantity")));
			
			CartDAO cdao = CartDAO.getInstance();
			
			cdao.insertCart(cvo);
			
		}
		
		response.sendRedirect("cartlist.do");
				
	}

}
