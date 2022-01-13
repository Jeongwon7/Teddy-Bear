package com.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.action.AdminLoginAction;
import com.shop.action.IndexAction;
import com.shop.action.LoginAction;
import com.shop.action.LogoutAction;
import com.shop.action.MemberJoin;
import com.shop.action.ProductAllListAction;
import com.shop.action.ProductiDetailAction;
import com.shop.action.admin.AdminOrderListAction;
import com.shop.action.admin.AdminOrderSaveAction;
import com.shop.action.admin.AdminProductImageAction;
import com.shop.action.admin.AdminProductListAction;
import com.shop.action.admin.AdminProductWriteAction;
import com.shop.action.admin.AdminProductWriteFormAction;
import com.shop.action.cart.CartDeleteAction;
import com.shop.action.cart.CartInsertAction;
import com.shop.action.cart.CartListAction;
import com.shop.action.notice.NoticeDeleteAction;
import com.shop.action.notice.NoticeListAction;
import com.shop.action.notice.NoticeModifyAction;
import com.shop.action.notice.NoticeModifyForm;
import com.shop.action.notice.NoticeViewAction;
import com.shop.action.notice.NoticeWriteAction;
import com.shop.action.notice.NoticeWriteProAction;
import com.shop.action.order.MypageAction;
import com.shop.action.order.OrderAllAction;
import com.shop.action.order.OrderDetailAction;
import com.shop.action.order.OrderInsertAction;
import com.shop.action.order.OrderListAction;
import com.shop.action.review.GetReviewAjaxAction;
import com.shop.action.review.ReviewDeleteAction;
import com.shop.action.review.ReviewModifyAction;
import com.shop.action.review.ReviewModifyFormAction;
import com.shop.action.review.Review_WriteAction;
import com.shop.action.review.Review_Write_ProAction;
import com.shop.qna.AnserWriteAction;
import com.shop.qna.AnswerDeleteAction;
import com.shop.qna.AnswerModifyAction;
import com.shop.qna.AnswerModifyForm;
import com.shop.qna.AnswerWriteFormAction;
import com.shop.qna.QnaListAjaxAction;
import com.shop.qna.QnaPwdCheckAction;
import com.shop.qna.QuestionDeleteAction;
import com.shop.qna.QuestionModifyAction;
import com.shop.qna.QuestionModifyFormAction;
import com.shop.qna.QuestionWriteAction;
import com.shop.qna.QuestionWriteFormAction;

import adminlogout.AdminLogoutAction;

@WebServlet("*.do")//전체 제어 설계도, mvc 패턴 면접질문ㄴ
public class shopController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public shopController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doActionFactory(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doActionFactory(request, response);
	}
	
	protected void doActionFactory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		String conPath = request.getContextPath();
		System.out.println(conPath);
		String com = uri.substring(conPath.length());
		System.out.println(com);
		
		String page = null;
		switch(com) {
		//사용자
		case "/main.do":
			new IndexAction().execute(request, response);
			
			page = "index.jsp";
			break;
			
		case "/productdetail.do":
			new ProductiDetailAction().execute(request, response);
			
			page = "product/productview.jsp";
			break;
			
		case "/join.do":
			page="member/join.jsp";
			break;
			
		case "/joinSave.do":
			new MemberJoin().execute(request, response);
			response.sendRedirect("main.do");
			break;
		
		case "/login_form.do":
			page = "member/login.jsp";
			break;
			
		case "/login.do":
			new LoginAction().execute(request, response);
			response.sendRedirect("main.do");
			
			break;
			
		case "/logout.do":
			new LogoutAction().execute(request, response);
			response.sendRedirect("main.do");
			
			break;
			
		case "/cartInsert.do":
			new CartInsertAction().execute(request, response);
			break;
			
		case "/cartlist.do":
			new CartListAction().execute(request, response);
			break;
		case "/cartDelete.do":
			new CartDeleteAction().execute(request, response);
			response.sendRedirect("cartlist.do");
			break;
		case "/orderinsert.do":
			new OrderInsertAction().execute(request, response);
			break;
		case "/orderlist.do":
			new OrderListAction().execute(request, response);
			break;
		case "/mypage.do":
			new MypageAction().execute(request, response);
			break;
		case "/orderDetail.do":
			new OrderDetailAction().execute(request, response);
			break;
		case "/orderAll.do":
			new OrderAllAction().execute(request, response);
			break;
		case "/review_write.do":
			new Review_WriteAction().execute(request, response);
			break;
		case "/review_write_pro.do":
			new Review_Write_ProAction().execute(request, response);
			response.sendRedirect("main.do");
			break;
		case "/reviewmodify.do":
			new ReviewModifyFormAction().execute(request, response);
			page="review/reviewmodify.jsp";
			break;
		case "/reviewmodifypro.do":
			new ReviewModifyAction().execute(request, response);
			break;
		case "/reviewdelete.do":
			new ReviewDeleteAction().execute(request, response);
			break;
		case "/getReviewAjax.do":
			new  GetReviewAjaxAction().execute(request, response);
			break;
		case "/notice.do":
			new NoticeListAction().execute(request, response);
			page="notice/notice.jsp";
			break;
		case "/noticewrite.do":
			new NoticeWriteAction().execute(request, response);
			page = "notice/noticewrite.jsp";
			break;
		case "/noticewritepro.do":
			new NoticeWriteProAction().execute(request, response);
			response.sendRedirect("notice.do");
			break;
		case "/noticeview.do":
			new NoticeViewAction().execute(request, response);
			page="notice/noticeview.jsp";
			break;
		case "/noticemodify.do":
			new NoticeModifyForm().execute(request, response);
			page="notice/noticemodify.jsp";
			break;
		case "/noticemodifypro.do":
			new NoticeModifyAction().execute(request, response);
			response.sendRedirect("notice.do");
			break;
		case "/noticedelete.do":
			new NoticeDeleteAction().execute(request, response);
			response.sendRedirect("notice.do");
			break;
		case "/questionwriteform.do":
			new QuestionWriteFormAction().execute(request, response);
			page="qna/questionwrite.jsp";
			break;
		case "/questionwrite.do":
			new QuestionWriteAction().execute(request, response);
			break;
		case "/answerwriteform.do":
			new AnswerWriteFormAction().execute(request, response);
			page="qna/answerwrite.jsp";
			break;
		case "/answerwrite.do":
			new AnserWriteAction().execute(request, response);
			break;
		case "/questionmodifyform.do"://질문 수정 폼
			new QuestionModifyFormAction().execute(request, response);
			page="qna/questionmodify.jsp";
			break;
		case "/questionmodify.do":
			new QuestionModifyAction().execute(request, response);
			break;
		case "/qnaPwdCheck.do":
			new QnaPwdCheckAction().execute(request, response);
			break;
		case "/answermodifyform.do":
			new AnswerModifyForm().execute(request, response);
			page ="qna/answermodify.jsp";
			break;
		case "/answermodify.do":
			new AnswerModifyAction().execute(request, response);
			break;
		case "/answerdelete.do":
			new AnswerDeleteAction().execute(request, response);
			break;
		case "/questiondelete.do":
			new QuestionDeleteAction().execute(request, response);
			break;
		case "/getQnaAjax.do":
			new QnaListAjaxAction().execute(request, response);
			break;
		case "/productall.do":
			new ProductAllListAction().execute(request, response);
			page="product/product.jsp";
			break;
		//관리자
		case "/admin.do":
			page="admin/adminlogin.jsp";
			break;
			
		case "/adminok.do":
			new AdminLoginAction().execute(request, response);
			break;
		case "/adminlogout.do":
			new AdminLogoutAction().execute(request, response);
			response.sendRedirect("main.do");
			break;
			
		case "/admin_product_list.do":
			new AdminProductListAction().execute(request, response);
			//걍 밑에 이프문 포워딩 하면 속성 셋한 게 넘어감
			page = "admin/admin.jsp";
			break;
		
		case "/admin_product_write_form.do":
			new AdminProductWriteFormAction().execute(request, response);
			
			page = "admin/product/productwrite.jsp";
			break;
		case "/admin_product_write.do":
			new AdminProductWriteAction().execute(request, response);
			
			response.sendRedirect("admin_product_list.do");//전할 값이 없으니까 포워딩 x
			break;
			
		case "/productImage.do":
			new AdminProductImageAction().execute(request, response);
			break;
		case "/admin_order_list.do":
			new AdminOrderListAction().execute(request, response);
			break;
			
		case "/adminOrderSave.do":
			new AdminOrderSaveAction().execute(request, response);
			break;
		}
		if(page != null) {
			//RequestDispatcher rd = request.getRequestDispatcher(page);
			//rd.forward(request, response);
			request.getRequestDispatcher(page).forward(request, response);
		}
	}

}
