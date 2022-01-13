package com.shop.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.ProductDAO;
import com.shop.dto.ProductDTO;

public class IndexAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		ProductDAO pdao = ProductDAO.getInstance();
		List<ProductDTO> newlist = pdao.listNewProduct();
		List<ProductDTO> bestlist = pdao.listBestProduct();
		
		request.setAttribute("newList", newlist);
		request.setAttribute("bestList", bestlist);
	}

}
