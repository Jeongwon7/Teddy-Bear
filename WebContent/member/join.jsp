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
					<h2>Join</h2>
					<p>회원가입 후 이벤트에 참여해 보세요. 1000포인트 무료 지급해 드립니다.</p>
					<form class="form-horizontal" name="frm" method="post">
					  <div class="form-group">
					    <label for="id" class="col-sm-2 control-label">ID</label>
					    <div class="col-sm-10">
					      <input type="text" name="id" class="form-control" id="id" placeholder="id">
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="pwd" class="col-sm-2 control-label">password</label>
					    <div class="col-sm-10">
					      <input type="password" name="pwd" class="form-control" id="pwd" placeholder="password">
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="pwdcheck" class="col-sm-2 control-label">password</label>
					    <div class="col-sm-10">
					      <input type="password" name="pwdcheck" class="form-control" id="pwdcheck" placeholder="password 확인">
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="name" class="col-sm-2 control-label">name</label>
					    <div class="col-sm-10">
					      <input type="text" name="name"class="form-control" id="name" placeholder="name">
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="email" class="col-sm-2 control-label">email</label>
					    <div class="col-sm-10">
					      <input type="text" name="email" class="form-control" id="email" placeholder="email" >
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="zip_num" class="col-sm-2 control-label">address</label>
					    <div class="col-sm-10">
						     <input id="member_post" name="zip_num"  type="text" placeholder="Zip Code" readonly >
						     <input type="button"  value="우편번호 검색"onclick="findAddr()"><br><br>
	  						 <input id="member_addr" name="address1" type="text" class="form-control" placeholder="Address" readonly> <br>
	 						 <input type="text" name="address2" class="form-control" placeholder="Detailed Address">
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="phone" class="col-sm-2 control-label">전화번호</label>
					    <div class="col-sm-10">
					      <input type="text" name="phone"class="form-control" id="phone" placeholder="phone">
					    </div>
					  </div>
					  <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					      <button type="button" class="btn btn-default" onclick="go_save()">회원가입</button>
					       <button type="reset" class="btn btn-default">취소</button>
					    </div>
					  </div>
					</form>
				</div><!-- 9 end -->
			</div>
		</div>
	</section>
	<!-- contents end-->
<%@ include file = "../footer.jsp" %>