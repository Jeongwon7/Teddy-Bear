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
					  <div class="search_wrap">
						<div class="record_group">
							<p>총게시글 <span>${count }</span>건</p><!-- ?=$count? php언어 ?= ?가 좌측깔대기퍼센트골뱅이 -->
						</div>
						<div class="search_group">
							<form name="myform" method="get" action="notice.do">
								<select name="sel" class="select">
									<option value="title">제목</option>
									<option value="content">내용</option>
								</select>
								<input type="text" name="word" class="search_word">
								<button class="btn_search" type="submit"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
							</form>
						</div>
					  </div> <!-- search end -->
					  <div class="bord_list">
						<table class="table" summary="이표는 번호,제목,글쓴이,날자,조회수로 구성되어 있습니다">
							<caption class="sr-only">공지사항 리스트</caption>
							<colgroup>
								<col width="10%">
								<col width="*">
								<col width="10%">
								<col width="15%">
								<col width="10%">
							</colgroup>
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>작성일</th>
									<th>조회수</th>
								</tr>
							</thead>
							<tbody>
							<c:set var="num" value="${count - ((pageMaker.cri.pageNum-1) * 10)}"/>
							<c:forEach var="list" items="${noticeList}">
							<!-- noticeList 속성이름(알아서 0번째 dto를 가리킨다) 
							c:forEach 는 반복문
							-->
								<tr>
									<td>${num}</td>
									<td class="title"><a href="noticeview.do?bno=${list.bno}&pageNum=${pageMaker.cri.pageNum}&amount=${pageMaker.cri.amount}">${list.title }</a></td>
									<td>${list.writer }</td>
									<td>
										<fmt:parseDate var="regdate" value="${list.regdate }" pattern="yyyy-MM-dd"/><!-- String에서 Date타입으로 -->
										<fmt:formatDate value="${regdate }" pattern="yyyy-MM-dd"/><!-- 날짜 형식을 출력 -->
									</td>
									<td>${list.viewcount }</td>
								</tr>
								<c:set var="num" value="${num-1}" />
							</c:forEach>
							</tbody>
						</table>
						<div class="paging">
							<c:if test="${pageMaker.prev }">
								<a href="${pageMaker.startPage-1}"><i class="fa  fa-angle-double-left"></i></a>
							</c:if>
							<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
								<a href="${num}" class="${pageMaker.cri.pageNum == num?'active':''}">${num}</a>
							</c:forEach>
							<c:if test="${pageMaker.next }">
								<a href="${pageMaker.endPage+1}"><i class="fa  fa-angle-double-right"></i></a>
							</c:if>
							<c:if test="${adminid eq 'admin'}">
								<div>
									<a href="noticewrite.do" class="btn btn-default pull-right">글쓰기</a>
								</div>
							 </c:if>
							<form id="actionForm" action="notice.do" method="get">
								<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
								<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
								<input type="hidden" name="sel" value="${pageMaker.cri.type}">
								<input type="hidden" name="word" value="${pageMaker.cri.keyword}">
							</form>
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