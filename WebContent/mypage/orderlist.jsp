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
					<h2>주문 목록</h2>
					<form method="post" name="formm">
						
								<table class="table">
									<tr>
										<th>상품명</th>
										<th>수량</th>
										<th>가격</th>
										<th>주문일</th>
										<th>진행상태</th>
									</tr>
									<c:forEach items="${orderLsit}" var="orderVO">
										<tr>
											<td><a href="productdetail.do?pseq=${orderVO.pseq}">${orderVO.pname}</a></td>
											<!-- <td style="text-align:center;">
												<img src="${pageContext.request.contextPath}/product_images/${cartVO.image}" alt="" style="width:50px;">
											</td> -->
											<td>${orderVO.quantity}</td>
											<td><fmt:formatNumber value="${orderVO.price2*orderVO.quantity}" type="currency"/></td>
											<td>${fn:substring(orderVO.indate,0,10)}</td>
											<td>처리 진행 중</td>
										</tr>
									</c:forEach>
									<tr>
										<th colspan="2">총액</th>
										<th colspan="2"><fmt:formatNumber value="${totalPrice}" type="currency"/></th>
										<th>주문 처리가 완료되었습니다</th>
									</tr>
								</table>
						<div class="col-md-12">
							<input type="button" class="btn btn-default" value="쇼핑계속하기" onclick="location.href='main.do'">
						</div>
					</form>
						
				</div><!-- 9 end -->
			</div>
		</div>
	</section>
	<!-- contents end-->
<%@ include file = "../footer.jsp" %>