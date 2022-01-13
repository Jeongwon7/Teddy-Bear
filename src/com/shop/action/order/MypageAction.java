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

public class MypageAction implements Command {

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
			List<Integer> oseqList = orderDAO.selectSeqOrderIng(loginUser.getId());//oseqList: 주문번호 배열
			
			List<OrderVO> orderList = new ArrayList<OrderVO>();
			for(int oseq : oseqList) {//주문번호를 변수 oseq 에 하나씩 대입해서 반복
				List<OrderVO> orderListIng = orderDAO.listOrderByID(loginUser.getId(), "1", oseq);// 주문번호에 해당하는 진행중인 뭐시기 상품 객체배열
				OrderVO orderVO = orderListIng.get(0);//0번째에 저장되어있는 값
				orderVO.setPname(orderVO.getPname()+" 외"+orderListIng.size()+"건");//사이즈가 결국 주문 하나당 주문한 상품 수? 종류?
				
				int totalPrice = 0;//주문당 총액 구하기
				for(OrderVO ovo : orderListIng) {
					totalPrice += ovo.getPrice2()*ovo.getQuantity();
				}
				
				orderVO.setPrice2(totalPrice);//총액을 price2 멤버 변수에 일단 넣기
				orderList.add(orderVO);// 객체배열에 저장 
			}
			request.setAttribute("title", "진행 중인 주문내역");
			request.setAttribute("orderList", orderList);
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}

}
