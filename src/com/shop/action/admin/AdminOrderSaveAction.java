package com.shop.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.command.Command;
import com.shop.dao.OrderDAO;

public class AdminOrderSaveAction implements Command {

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      String page = "admin_order_list.do";
      
      String[] resultArr = request.getParameterValues("result");
      
      for(String odseq: resultArr) {
         OrderDAO odao = OrderDAO.getInstance();
         odao.updateOrderResult(odseq);
      }
         response.sendRedirect(page);
   }
}
