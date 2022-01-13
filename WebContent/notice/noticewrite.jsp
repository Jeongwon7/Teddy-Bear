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
				  <h2>공지사항 글쓰기</h2>
					 <form class="form-horizontal" name="notice" method="post" action="noticewritepro.do" onsubmit="return check();" enctype="multipart/form-data">
				  <!-- action을 처리하기전에 check()사용자 함수를 실행하고 되돌아 와라-->
						<table class="table">
							<caption class="sr-only">공지사항 입력 표</caption>
							<colgroup>
								<col width="10%">
								<col width="*">
							</colgroup>
							<tbody>
								<tr>
									<th>글쓴이</th>
									<td><input type="text" name="writer" style="width:100%;" value="관리자"></td>
								</tr>
								<tr>
									<th>제목</th>
									<td><input type="text" name="title" style="width:100%;"></td>
								</tr>
								<tr>
									<th>내용</th>
									<td><textarea name="content"  id="summernote"></textarea></td>
								</tr>
							</tbody>
						</table>
						<div class="form-group">
						    <div class="col-sm-offset-2 col-sm-10 row text-center">
								<input type="submit" value="저장" class="btn btn-default">&nbsp;&nbsp;
								<input type="reset" value="다시쓰기" class="btn btn-default">&nbsp;&nbsp;
								<input type="button" value="목록" class="btn btn-default" onclick="location.href='notice.do';">
							</div>
						</div>
					</form>
				</div>
			</div><!-- row end -->
		</div>
	</section>
	<!-- contents end-->
<%@ include file = "../footer.jsp" %>