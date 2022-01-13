package com.shop.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.command.Command;

public class LogoutAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(false);
		//getSession(): 세션이 존재하면 현재 세션을 반환하고, 존재하지 않으면 새로운 세션을 생성한다
		//false: 세션이 존재하면 현재 세션을 반환하고 세션이 존재하지 않으면  다시 만들지 않고 null을 반환한다
		//true: 세션을 다시 만든다(디폴트값)
		//로그인 한 상태면 세션이 존재함
		
		if(session != null) {
			session.invalidate();//세션 모두 제거
		}

	}

}
