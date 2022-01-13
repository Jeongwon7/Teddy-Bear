package com.shop.action.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.ProductDAO;
import com.shop.dto.ProductDTO;

public class AdminProductListAction implements Command {//비즈니스 서비스 영역

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		ProductDAO pdao = ProductDAO.getInstance();
		
		List<ProductDTO> list = pdao.productList();

		request.setAttribute("list", list);
		
		
	}

}
