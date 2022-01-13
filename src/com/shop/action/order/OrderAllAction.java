package com.shop.action.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.command.Command;
import com.shop.dao.OrderDAO;
import com.shop.dto.MemberVO;
import com.shop.dto.OrderVO;

public class OrderAllAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		String page = "mypage/mypage.jsp";
		
		if(loginUser == null) {
			page = "login_form.do";
		}else {
			OrderDAO orderDAO = OrderDAO.getInstance();
			List<Integer> oseqList = orderDAO.selectSeqOrder(loginUser.getId());
			
			List<OrderVO> orderList = new ArrayList<OrderVO>();
			for(int oseq : oseqList) {
				List<OrderVO> orderListIng = orderDAO.listOrderByID(loginUser.getId(), "%", oseq);
				OrderVO orderVO = orderListIng.get(0);//0번째에 저장되어있는 값
				orderVO.setPname(orderVO.getPname()+" 외"+orderListIng.size()+"건");
				int totalPrice = 0;
				for(OrderVO ovo : orderListIng) {
					totalPrice += ovo.getPrice2()*ovo.getQuantity();
				}
				orderVO.setPrice2(totalPrice);
				orderList.add(orderVO);
			}
			request.setAttribute("title", "총 주문내역");
			request.setAttribute("orderList", orderList);
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

}
