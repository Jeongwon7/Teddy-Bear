package com.shop.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.NoticeDAO;
import com.shop.dao.ProductDAO;
import com.shop.dto.NoticeVO;
import com.shop.dto.ProductDTO;

import utility.Criteria;
import utility.PageVO;

public class ProductAllListAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//리스트 출력
		ProductDAO pdao = ProductDAO.getInstance();
		
		List<ProductDTO> list = pdao.listAllProduct();

		request.setAttribute("allList", list);
	}

}
