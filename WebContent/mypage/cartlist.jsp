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
					<h2>장바구니 목록</h2>
					<form method="post" name="formm">
						<c:choose>
							<c:when test="${cartList.size() == 0}">
								<h3>장바구니가 비어있습니다</h3>
							</c:when>
							<c:otherwise>
								<table class="table">
									<tr>
										<th>상품명</th>
										<th>상품이미지</th>
										<th>수량</th>
										<th>가격</th>
										<th>주문일</th>
										<th>삭제</th>
									</tr>
									<c:forEach items="${cartList}" var="cartVO">
										<tr>
											<td>${cartVO.pname}</td>
											<td style="text-align:center;">
												<img src="${pageContext.request.contextPath}/product_images/${cartVO.image}" alt="" style="width:50px;">
											</td>
											<td>${cartVO.quantity}</td>
											<td><fmt:formatNumber value="${cartVO.price2*cartVO.quantity}" type="currency"/></td>
											<td>${cartVO.indate}</td>
											<td><input type="checkbox" name="cseq" value="${cartVO.cseq}"></td>
										</tr>
									</c:forEach>
									<tr>
										<th colspan="2">총액</th>
										<th colspan="2"><fmt:formatNumber value="${totalPrice}" type="currency"/></th>
										<th><a href="javascript:void(0)" onclick="goCartDelete()"><h3>삭제</h3></a></th>
									</tr>
								</table>
							</c:otherwise>
						</c:choose>
						<div class="col-md-12">
							<input type="button" class="btn btn-default" value="쇼핑계속하기" onclick="location.href='main.do'">
							<button type="button" class="btn btn-default" onclick="goOrderInsert()">주문하기</button>
						</div>
					</form>
						
				</div><!-- 9 end -->
			</div>
		</div>
	</section>
	<!-- contents end-->
<%@ include file = "../footer.jsp" %>