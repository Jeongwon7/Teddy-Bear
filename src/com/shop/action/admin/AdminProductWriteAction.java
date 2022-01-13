package com.shop.action.admin;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.shop.command.Command;
import com.shop.dao.ProductDAO;
import com.shop.dto.ProductDTO;

public class AdminProductWriteAction implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();//세션객체 생성
		int sizeLimit = 5 * 1024 * 1024;
		String savePath = "product_images";
		ServletContext context = session.getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		
		MultipartRequest multi = new MultipartRequest(request, 
			uploadFilePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
		
		//첨부가 있을 때 요청 방뻡
		
		ProductDTO pvo = new ProductDTO();
		
		pvo.setKind(multi.getParameter("kind"));
		pvo.setName(multi.getParameter("name"));
		pvo.setPrice1(Integer.parseInt(multi.getParameter("price1")));
		pvo.setPrice2(Integer.parseInt(multi.getParameter("price2")));
		pvo.setPrice3(Integer.parseInt(multi.getParameter("price2")) - Integer.parseInt(multi.getParameter("price1")));
		pvo.setContent(multi.getParameter("content"));
		pvo.setImage(multi.getFilesystemName("image"));
		
		ProductDAO pdao = ProductDAO.getInstance();
		
		int result = pdao.productInsert(pvo);
		
	}	
}
