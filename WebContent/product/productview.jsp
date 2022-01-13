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
						    <div class="panel-heading" role="tab">
						      <h4 class="panel-title">
						        <a href="productall.do" >
						          PRODUCTS
						        </a>
						      </h4>
						    </div>
						  </div>
					</div>
				</div>
				<div class="col-md-9 newitem">
					<form method="post" name="formm">
						<input type="hidden" name="pseq" value="${pvo.pseq}">
						<div class="details">
							<div class="col-md-5">
								<img src="${pageContext.request.contextPath}/product_images/${pvo.image}" alt=""> 
							</div>
							<div class="col-md-7">
								<div class="information">
									<h2>${pvo.name}</h2>
									<p>배송비 2,500원 (40,000원 이상 무료) </p>
									<p>선물하기로 1만원 이상 구매 시, 300P 최대 10회 적립</p>
									<p>11번가 신한카드 첫 결제할인 + 최대 2% 적립</p>
									<p>최대 22개월 카드무이자 할부</p>
									<p style="color:#f00; font-size:22px;">
									<span class="${pvo.price2}"></span><fmt:formatNumber value="${pvo.price2}"/>원</p>
								</div>
								<div class="" style="padding:20px 0;" >
									<button class="minus" type="button">-</button>
									<input type="text" name="quantity" class="quantity" value="1" readonly>
									<button class="plus" type="button">+</button>
								</div>
								<div style="padding:30px 0;" >
									<strong>총 합계금액 : </strong>
									<span class="total"></span>
								</div>
								<div class="" style="padding:30px 0;">
									<c:choose>
										<c:when test="${empty loginUser}">
											<button type="button" class="btn btn-default" onclick="alert('로그인이 필요한 기능입니다');">장바구니</button>
											<button type="button" class="btn btn-danger" onclick="alert('로그인이 필요한 기능입니다');">구매하기</button>
										</c:when>
										<c:otherwise>
											<button type="button" class="btn btn-default" onclick="go_cart()">장바구니</button>
											<button type="button" class="btn btn-danger" onclick="go_order()">구매하기</button>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div><!-- details end -->
					</form>
						<div role="tabpanel" class="category-tab">

							  <!-- Nav tabs -->
							  <ul class="nav nav-tabs" role="tablist"><!-- a태그와 밑에 id 이름이 같게 -->
							    <li role="presentation" class="active"><a href="#productview" aria-controls="productview" role="tab" data-toggle="tab">상품설명</a></li>
							    <li role="presentation"><a href="#review" aria-controls="review" role="tab" data-toggle="tab">사용후기</a></li>
							    <li role="presentation"><a href="#qna" aria-controls="qna" role="tab" data-toggle="tab">상품문의</a></li>
							  </ul>
							
							  <!-- Tab panes -->
							  <div class="tab-content">
							    <div role="tabpanel" class="tab-pane active" id="productview">
							    	<div>
							    		${pvo.content}
							    	</div>
							    </div>
							    <div role="tabpanel" class="tab-pane" id="review">
							     	<div class="my_review">
							     		<table class="table table-hover my_reviewtbl">
							     		<colgroup>
							     			<col width="*">
							     			<col width="40%">
							     			<col width="*">
							     			<col width="*">
							     			<col width="*">
							     		</colgroup>
								     		<thead>
												<tr>
													<th scope="col">번호</th>
													<th scope="col">제목</th>
													<th scope="col">평점</th>
													<th scope="col">작성자</th>
													<th scope="col">작성일</th>
												</tr>
											</thead>
											<tbody class="review_box">
											
											<c:set var="num" value="${rlist.size()}"/>
												<c:forEach var="rlist" items="${rlist}">
													
														<c:if test ="${not empty rlist}">
														
															<tr class="item">
																<td>${num}</td>
																<td>${rlist.title}</td>
																<td>
																	<div class="star-ratings">
																		<div 
																	    class="star-ratings-fill space-x-2 text-lg"
																	    style="{ width: ratingToPercent + '%' }"
																		>
																		<c:choose>
																			<c:when test="${rlist.star == 5}">
																				<span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
																			</c:when>
																			<c:when test="${rlist.star == 4}">
																				<span>★</span><span>★</span><span>★</span><span>★</span>
																			</c:when>
																			<c:when test="${rlist.star == 3}">
																				<span>★</span><span>★</span><span>★</span>
																			</c:when>
																			<c:when test="${rlist.star == 2}">
																				<span>★</span><span>★</span>
																			</c:when>
																			<c:when test="${rlist.star == 1}">
																				<span>★</span>
																			</c:when>
																		</c:choose>
																			
																		</div>
																		<div class="star-ratings-base space-x-2 text-lg">
																			<span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
																		</div>
																	</div>
																</td>
																<td>${rlist.writer}</td>
																<td>${rlist.regdate}</td>
															</tr>
															<tr class="hide">
																<td colspan="5">
																	<div class="review_content_wrap">
																		<div class="review_content">
																			${rlist.content}
																		</div>
																		<c:choose>
																			<c:when test="${empty loginUser}">
																				<div class="review_btn">
																					<span><input type="button" onclick="alert('로그인이 필요한 기능입니다');" value="수정"></span>
																					<span><input type="button" onclick="alert('로그인이 필요한 기능입니다');" value="삭제"></span>
																				</div>
																			</c:when>
																			<c:when test="${loginUser.id eq rlist.writer}">
																				<div class="review_btn">
																					<span><input type="button" onclick="location.href='reviewmodify.do?rbno=${rlist.rbno}'" value="수정"></span>
																					<span><input type="button" onclick="review_delete(${rlist.rbno}, ${pvo.pseq}, '${loginUser.id}');" value="삭제"></span>
																				</div>
																			</c:when>
																		</c:choose>
																	</div>
																</td>
															</tr>
															
														</c:if>
														
															<c:set var="num" value="${num-1}" />
												</c:forEach>
												<c:if test="${empty rlist }">
														<tr><td colspan="5">상품 후기가 없습니다</td></tr>
												</c:if>
											</tbody>
										</table>
							     	</div>
							    </div>
							    <div role="tabpanel" class="tab-pane" id="qna">
							     	<div class="my_qna">
							     		<table class="table table-hover my_qnatbl">
							     		<colgroup>
							     			<col width="*">
							     			<col width="*">
							     			<col width="40%">
							     			<col width="*">
							     			<col width="*">
							     		</colgroup>
								     		<thead>
												<tr>
													<th scope="col">번호</th>
													<th scope="col">카테고리</th>
													<th scope="col">제목</th>
													<th scope="col">작성자</th>
													<th scope="col">작성일</th>
												</tr>
											</thead>
											<tbody class="qna_box">
												<c:set var="num" value="${qnalist.size()}"/>
													<c:forEach var="list" items="${qnalist}">
															<tr class="item">
																<td>${num}</td>
																<c:choose>
																	<c:when test="${list.category == 1}">
																		<td>상품문의</td>
																	</c:when>
																	<c:when test="${list.category == 2}">
																		<td>배송문의</td>
																	</c:when>
																	<c:when test="${list.category == 3}">
																		<td>기타문의</td>
																	</c:when>
																</c:choose>
																<td>
																	${list.qtitle}
																</td>
																<td>${list.qwriter}</td>
																<td>${list.qregdate }</td>
															</tr>
															<tr class="hide">
																<td colspan="5">
																	<div class="review_content_wrap">
																		<div class="review_content">
																			${list.qcontent }
																		</div>
																				<div class="review_btn">
																					<c:if test ="${adminid == 'admin'}">
																						<span><input type="button" onclick="location.href='answerwriteform.do?qbno=${list.qbno}&pseq=${pvo.pseq}&ref=${list.ref}'" value="답글작성"></span>
																					</c:if>
																					<c:if test="${loginUser.id  eq list.qwriter}">
																						<span><input type="button" onclick="location.href='questionmodifyform.do?qbno=${list.qbno}'" value="수정"></span>
																						<span><input type="button" onclick="question_deleteOpen(${list.qbno}, ${pvo.pseq}, '${loginUser.id}', '${adminid}', ${list.abno});" value="삭제"></span>
																					</c:if>
																				</div>
																		
																	</div>
																</td>
															</tr>
															<c:choose>
																<c:when test="${list.abno eq 0}">
																		
																	
																</c:when>
																<c:otherwise>
																	<tr class="item">
																		<td></td>
																		<td></td>
																		<td style="padding-left:100px">
																		
																			  <img src="http://img0001.echosting.cafe24.com/front/type_b/image/common/icon_re.gif" alt="답변" class="ec-common-rwd-image">
																			${list.atitle}
																		</td>
																		<td>${list.awriter}</td>
																		<td>${fn:substring(list.aregdate,0,10) } </td>
																	</tr>
																	<tr class="hide">
																		<td colspan="5">
																			<div class="review_content_wrap">
																				<div class="review_content">
																					${list.acontent}
																				</div>
																						<div class="review_btn">
																							<c:if test="${adminid == 'admin' }">
																								<span><input type="button" onclick="location.href='answermodifyform.do?abno=${list.abno}&pseq=${pvo.pseq}'" value="수정"></span>
																								<span><input type="button" onclick="answer_deleteOpen(${list.abno}, ${pvo.pseq}, '${loginUser.id}', '${adminid}');" value="삭제"></span>
																							</c:if>
																						</div>
																				
																			</div>
																		</td>
																	</tr>
																</c:otherwise>
																
															</c:choose>
															
															
															<c:set var="num" value="${num-1}" />
													</c:forEach>
															
														<c:if test="${empty qnalist }">
														<tr><td colspan="5">상품 문의가 없습니다</td></tr>
												</c:if>
											</tbody>
										</table>
										<c:choose>
											<c:when test="${empty loginUser}">
												<div class="review_btn pull-right">
													<span><input type="button" onclick="alert('로그인이 필요한 기능입니다');" value="글쓰기"></span>
												</div>
											</c:when>
											<c:otherwise>
												<div class="review_btn pull-right">
													<span><input type="button" onclick="location.href='questionwriteform.do?pseq=${pvo.pseq}'" value="글쓰기"></span>
												</div>
											</c:otherwise>
										</c:choose>
							     	</div>
							    </div>
							  </div>
							</div>
				</div><!-- 9 end -->
			</div>
		</div>
	</section>
	<!-- contents end-->
	<script>
	$(function(){ 

        var article = (".my_reviewtbl .my_show");  
        
        $(document).on('click', '.my_reviewtbl .item  td', function() { 

            var myArticle =$(this).parents().next("tr");  

            if($(myArticle).hasClass('hide')) {

                $(article).removeClass('my_show').addClass('hide');  

                $(myArticle).removeClass('hide').addClass('my_show');  

            }  

            else {  

                $(myArticle).addClass('hide').removeClass('my_show');  

            }  

        });
        
        
 		var article = (".my_qnatbl .my_show");  
        
        $(document).on('click', '.my_qnatbl .item  td', function() { 

            var myArticle =$(this).parents().next("tr");  

            if($(myArticle).hasClass('hide')) {

               

                $(myArticle).removeClass('hide').addClass('my_show');  

            }  

            else {  

                $(myArticle).addClass('hide').removeClass('my_show');  

            }  

        });
        
        function pwdCheck(qbno){
        	var pwd = $("#"+qbno).val();
        	var qbno = qbno;
        	
        	$.ajax({
        		type:'post',
        		url: 'qnaPwdCheck.do',
        		data:{qbno:qbno, pwd:pwd},
        		success:function(result){//숫자 받아오기(뷰로 셀렉트 해야해)
        			if(result == 1){//일치
        				var secret = $("#"+qbno).parents().parents().parents().parents().next("tr");
        				$(secret).addlClass('my_show').removeClass('hide');
        			}else{
        				alert("비밀번호를 확인하세요");
        			}
        		},error:function(error){
        			alert("통신에러");
        		}
        	})
        	
        }
        
});  

	</script>
<%@ include file = "../footer.jsp" %>