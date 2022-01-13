package com.shop.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;

public class AdminProductWriteFormAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String kindList[] = {"teddy bear"};
		request.setAttribute("KindList", kindList);
	}

}
