package com.shop.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.command.Command;
import com.shop.dao.MemberDAO;
import com.shop.dto.MemberVO;
import com.shop.utility.SecurityPassword;

public class AdminLoginAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String pw = SecurityPassword.encoding(request.getParameter("pwd"));
		
		String id_rem = request.getParameter("id_rem");
		Cookie cookie = null;
		
		if(id_rem != null && id_rem.trim().equals("on")) {
			cookie = new Cookie("id", java.net.URLEncoder.encode(id));
			cookie.setMaxAge(60*60*24*365);
			response.addCookie(cookie);//내컴퓨터에 쿠키 값 저장 1년 동안 저장된다
		}else {
			cookie = new Cookie("id", null);
			cookie.setMaxAge(0);//유효시간을 0으로
			response.addCookie(cookie);//내컴퓨터에 쿠키 값 저장
		}
		
		
		if((id.equals("admin")) && (pw.equals(SecurityPassword.encoding("1234")))){
			MemberDAO dao = MemberDAO.getInstance();
			
			int result = dao.memberIdPwCheck(id, pw);
			
			HttpSession session = request.getSession();//세션 객체 생성
			
			if(result == 1) {
				session.setAttribute("adminid", id);//세션 속성에 저장(페이지를 벗어나도 로그인 유지)
				response.sendRedirect("admin_product_list.do");
			}else if (result == 0) {
				request.setAttribute("msg", "아이디 또는 비밀번호를 확인하세요");//리퀘스트 속성에 저장 login/login.jsp 에서만 유지된다
				RequestDispatcher rd = request.getRequestDispatcher("admin/adminlogin.jsp");
				rd.forward(request, response);
			}else if(result == -1) {
				request.setAttribute("msg", "회원가입 후 로그인 하세요");
				RequestDispatcher rd = request.getRequestDispatcher("admin/adminlogin.jsp");
				rd.forward(request, response);
			}
			
		}else {
			request.setAttribute("msg", "아이디 또는 비밀번호를 확인하세요");
			RequestDispatcher rd = request.getRequestDispatcher("admin/adminlogin.jsp");
			rd.forward(request, response);
		}

	}

}
