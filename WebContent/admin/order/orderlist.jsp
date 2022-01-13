<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../adminheader.jsp"%>

<script>
   function go_order_save(){
      var count = 0;
      
      if(document.frm.result.length == undefined){ //checkbox가 한 개일때
         if(document.frm.result.checked == true){
            count++;
         }
      }
      
      for(var i=0; i<document.frm.result.length; i++){
         if(document.frm.result[i].checked == true){
            count++;
         }
      }
      if(count == 0){
         alert("주문처리 할 항목 선택");
      }else{
         document.frm.action = "adminOrderSave.do";
         document.frm.submit(); //배열의 형태로 체크된값이 넘어감
      }
   }
</script>

<form class="form-inline" name="frm" method="post">
  			<div class="col-md-10">
  			 	<div class="col-md-12" style="margin-bottom:30px;">
	  				
					  <div class="form-group">
					    <label for="product">주문자 이름</label>
					    <input type="text" name="key" class="form-control" id="product" placeholder="검색단어 입력">
					  </div>
					  <button type="button" class="btn btn-default" onclick="go_search()">검색</button>
					
				</div>
				<div class="col-md-12" style="text-align:center;">
					<table class="table">
					 	<tr>
					 		<th>주문번호(처리여부)</th>
					 		<th>주문자</th>
					 		<th>상품명</th>
					 		<th>수량</th>
					 		<th>우편번호</th>
					 		<th>배송지</th>
					 		<th>전화</th>
					 		<th>주문일</th>
					 	</tr>
					 	<c:forEach items="${orderList}" var="orderVO">
						 	<tr>
						 		<td>
						 			<c:choose>
						 				<c:when test="${orderVO.result eq '1'}">
						 					<span style="color:#00f;">${orderVO.odseq}</span>&nbsp;&nbsp;
						 					<input type="checkbox" name="result" value="${orderVO.odseq }">미처리
						 				</c:when>
						 				<c:otherwise>
						 					<span style="color:#f00;">${orderVO.odseq}</span>&nbsp;&nbsp;
						 					<input type="checkbox" checked="checked" disabled="disabled">처리완료
						 				</c:otherwise>
						 			</c:choose>
						 			
						 		</td>
						 		<td>${orderVO.mname}</td>
						 		<td>${orderVO.pname}</td>
						 		<td>${orderVO.quantity}</td>
						 		<td>${orderVO.zipNum}</td>
						 		<td>${orderVO.address1} ${orderVO.address2}</td>
						 		<td>${orderVO.phone}</td>
						 		<td>${fn:substring(orderVO.indate, 0, 10)}</td>
						 	</tr>
						 </c:forEach>
					</table>
					<input type="button" class="btn btn-default" value="주문처리(입금확인)" onclick="go_order_save()" style="margin-right:50px;">
						<div class="">
							<nav class="page">
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
						</div>
				</div>
  			</div>
</form>
  		</div>
  	</div>
  </div>
  </body>