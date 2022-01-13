<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../header.jsp" %>
	<!-- contents -->
	<section class="product">
		<div class="container">
			<div class="row">
				<div class="col-md-12 newitem">
					<h2 style="text-align:center;"><a href="notice.do">공지사항</a></h2>
						<div class="my_container">
							<div class="board_view">
								<h2 style="color:#000;">${view.title }</h2>
								<p class="info"><span class="user">${view.writer }
								</span> | <fmt:parseDate var="regdate" value="${view.regdate }" pattern="yyyy-MM-dd"/><!-- String에서 Date타입으로 -->
											<fmt:formatDate value="${regdate }" pattern="yyyy-MM-dd"/> | <i class="fa fa-eye"></i> ${view.viewcount }</p>
								<div class="board_body">
									${view.content }<!-- p태그 안에 쓰지말기 자동으로 들어가게 되어있대 나중에 고치겠다 했음 -->
								</div>
								<div class="prev_next">
								<c:if test="${prev != null}"><!-- 이전글이 있으면 -->
									<a href="noticeview.do?bno=${prev.bno}&pageNum=${cri.pageNum}&amount=${cri.amount}" class="btn_prev">
										<i class="fa fa-angle-left"></i>
										<span class="prev_wrap">
											<strong>이전글</strong><span>${fn:substring(prev.title,0,20)}...</span>
										</span>
									</a>
								</c:if>
									<div class="btn_3wrap">
										<a href="notice.do?pageNum=${cri.pageNum}&amount=${cri.amount}">목록</a><!-- 봤던 페이지로 돌아가도록 -->
										<c:if test="${adminid eq 'admin'}">
											<a href="noticemodify.do?bno=${view.bno}&pageNum=${cri.pageNum}&amount=${cri.amount}">수정</a><!-- 봤던 페이지로 돌아가도록 -->
											<a href="noticedelete.do?bno=${view.bno}" onClick="return confirm('삭제하겠습니까?')">삭제</a>
										</c:if>
									</div>
								<c:if test="${next != null }">
									<a href="noticeview.do?bno=${next.bno}&pageNum=${cri.pageNum}&amount=${cri.amount}" class="btn_next">
										<span class="next_wrap">
											<strong>다음글</strong><span>${fn:substring(next.title,0,20)}...</span>
										</span>
										<i class="fa fa-angle-right"></i>
									</a>
								</c:if>
								</div>
								
							</div>  
				
					</div>
						
				</div><!-- 9 end -->
			</div>
		</div>
	</section>
	<!-- contents end-->
	<script>
		$(function() {
			
			var actionForm = $("#actionForm");
			
			$(".paging > a").on("click", function(e){
				e.preventDefault();
				actionForm.find("input[name='pageNum']").val($(this).attr("href"));//현재 페이지 번호의 값을 구해서 전송해라
				actionForm.submit();
			})
		});
	</script>
<%@ include file = "../footer.jsp" %>