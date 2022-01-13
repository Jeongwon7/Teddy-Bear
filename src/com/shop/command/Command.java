package com.shop.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;//예외 발생시 호출한 곳으로 예외 던진다
}
