<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="adminheader.jsp"%>
  			<div class="col-md-10">
  			 	<div class="col-md-12" style="margin-bottom:30px;">
	  				<form class="form-inline">
					  <div class="form-group">
					    <label for="product">상품명</label>
					    <input type="text" class="form-control" id="product" placeholder="검색단어 입력">
					  </div>
					  <button type="submit" class="btn btn-default">검색</button>
					</form>
				</div>
				<div class="col-md-12" style="text-align:center;">
					<table class="table">
					 	<tr>
					 		<th>번호</th>
					 		<th>상품명</th>
					 		<th>상품이미지</th>
					 		<th>원가</th>
					 		<th>판매가</th>
					 		<th>등록일</th>
					 		<th>사용유무</th>
					 	</tr>
					 	<c:choose>
					 		<c:when test="${list eq null}">
					 			<tr>
					 				<td colspan="7">출력 데이터가 없습니다</td>
					 			</tr>
					 		</c:when>
					 		<c:otherwise>
					 		<c:forEach items="${list}" var="list">
							 	<tr>
							 		<td>${list.pseq }</td>
							 		<td>${list.name }</td>
							 		<td><img src="${pageContext.request.contextPath}/product_images/${list.image}" style="width:30px;"></td>
							 		<td><fmt:formatNumber value="${list.price1}"/></td>
							 		<td><fmt:formatNumber value="${list.price2}"/></td>
							 		<td>${list.indate }</td>
							 		<td>
							 		
							 			<c:choose>
							 				<c:when test="${list.useyn == 'y'}">사용</c:when>
							 				<c:otherwise>미사용</c:otherwise>
							 			</c:choose>
							 		</td>
							 	</tr>
							 </c:forEach>
							 </c:otherwise>
					 	</c:choose>
					</table>
						<nav class="page-group">
						<ul class="pagination">
						    <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
						    <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
						    <li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">5</a></li>
							<li>
								<a href="#" aria-label="Next">
							    <span aria-hidden="true">&raquo;</span>
							    </a>
							</li>
						</ul>
						</nav>
						<button type="button" class="btn btn-danger pull-right" onclick="go_write()">글쓰기</button>
				</div>
  			</div>
  		</div>
  	</div>
  </div>
  </body>