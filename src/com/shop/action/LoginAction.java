package com.shop.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.command.Command;
import com.shop.dao.MemberDAO;
import com.shop.dto.MemberVO;
import com.shop.utility.SecurityPassword;

public class LoginAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("userid");
		String pwd = request.getParameter("userpwd");
		
		MemberDAO mdao = MemberDAO.getInstance();
		MemberVO mvo = mdao.getMember(id);
		
		HttpSession session = request.getSession();
		
		if(mvo != null) {
			if(mvo.getPwd().equals(SecurityPassword.encoding(pwd))) {
				session.setAttribute("loginUser", mvo);
				session.setAttribute("userid", id);
			}
		}

	}

}
