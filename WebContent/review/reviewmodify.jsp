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
				  <h2>Review Modify (후기 수정)</h2>
					<form class="form-horizontal" name="rvfrm" method="post" enctype="multipart/form-data">
					<input type="hidden" name="rbno" value="${rvo.rbno}">
					<input type="hidden" name="pseq" value="${rvo.pseq}">
					 <div class="form-group">
					    <label for="title" class="col-sm-2 control-label">제목</label>
					    <div class="col-sm-10">
					      <input type="text" name="title" class="form-control" id="title" value="${rvo.title}">
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="rating" class="col-sm-2 control-label">별점</label>
						  <div class="star-rating space-x-4 mx-auto">
							<input type="radio" id="5-stars" name="rating" value="5" />
							<label for="5-stars" class="star pr-4">★</label>
							<input type="radio" id="4-stars" name="rating" value="4"/>
							<label for="4-stars" class="star">★</label>
							<input type="radio" id="3-stars" name="rating" value="3"/>
							<label for="3-stars" class="star">★</label>
							<input type="radio" id="2-stars" name="rating" value="2"/>
							<label for="2-stars" class="star">★</label>
							<input type="radio" id="1-star" name="rating" value="1" />
							<label for="1-star" class="star">★</label>
						</div>
					</div>
					   <div class="form-group">
					    <label for="content" class="col-sm-2 control-label">내용</label>
					    <div class="col-sm-10">
					      <textarea name="content" id="summernote" class="form-control" rows="10">
					      	${rvo.content }
					      </textarea>
					    </div>
					  </div>
					    <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					      <button type="button" class="btn btn-default" onclick="review_modify();">리뷰수정</button>
					       <button type="button" class="btn btn-default" onclick="history.back();">수정취소</button>
					    </div>
					  </div>
					</form>
				</div>
			</div><!-- row end -->
		</div>
	</section>
	<!-- contents end-->
<%@ include file = "../footer.jsp" %>