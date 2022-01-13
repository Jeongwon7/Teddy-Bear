<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../header.jsp" %>
	<!-- contents -->
	<section class="product">
		<div class="container">
			<div class="row">
				<div class="col-md-3">
					<h2>CATEGORY</h2>
					<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
						  <div class="panel panel-default">
						    <div class="panel-heading" role="tab" id="headingOne">
						      <h4 class="panel-title">
						        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
						          Side Menu
						          <span class="badge pull-right"><i class="fa fa-plus"></i></span>
						        </a>
						      </h4>
						    </div>
						    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
						      <div class="panel-body">
								<ul>
									<li><a href="cartlist.do">장바구니 내역</a></li>
									<li><a href="mypage.do">진행중인 주문내역</a></li>
									<li><a href="orderAll.do">총 주문내역</a></li>
								</ul>
						      </div>
						    </div>
						  </div>
					</div>
				</div>
				<div class="col-md-9 newitem">
					<h2>MyPage (주문상세 정보)</h2>
					<form method="post" name="formm">
					<h3>주문자 정보</h3>
						<table class="table">
							<tr>
								<th>주문일자</th>
								<th>주문번호</th>
								<th>주문자</th>
								<th>주문총액</th>
							</tr>
							<tr>
								<td>${fn:substring(orderDetail.indate, 0, 10)}</td>
								<td>${orderDetail.oseq}</td>
								<td>${orderDetail.mname}</td>
								<td><fmt:formatNumber value="${totalPrice}" type="currency"/></td>
							</tr>
						</table>
						<h3>주문 상품 정보</h3>
						<table class="table">
							<tr>
								<th>상품명</th>
								<th>상품별주문번호</th>
								<th>수량</th>
								<th>가격</th>
								<th>처리상태</th>
							</tr>
							<c:forEach items="${orderList }" var="orderVO">
								<tr>
									<td>${orderVO.pname}</td>
									<td>${orderVO.odseq}</td>
									<td>${orderVO.quantity}</td>
									<td><fmt:formatNumber value="${orderVO.price2 * orderVO.quantity}" type="currency"/></td>
									<td>
										<c:choose>
											<c:when test="${orderVO.result eq '1'}">진행중</c:when>
											<c:otherwise>
												<span style="color:#f00;">처리 완료</span>
												<span> / <a href="review_write.do?pseq=${orderVO.pseq}">리뷰 작성</a></span>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
						</table>
						<div class="col-md-12">
							<input type="button" class="btn btn-default" value="쇼핑계속하기" onclick="location.href='main.do'">
						</div>
					</form>
						
				</div><!-- 9 end -->
			</div><!-- row end -->
		</div>
	</section>
	<!-- contents end-->
<%@ include file = "../footer.jsp" %>