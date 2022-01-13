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
					<h2>로그인</h2>
					<form class="form-horizontal" name="frm" method="post" action="login.do">
					  <div class="form-group">
					    <label for="userid" class="col-sm-2 control-label">ID</label>
					    <div class="col-sm-10">
					      <input type="text" name="userid" class="form-control" id="userid" placeholder="id">
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="userpwd" class="col-sm-2 control-label">password</label>
					    <div class="col-sm-10">
					      <input type="password" name="userpwd" class="form-control" id="userpwd" placeholder="password">
					    </div>
					  </div>
					  <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					      <input type="submit" class="btn btn-default" value="로그인">
					      <button type="button" class="btn btn-default" onclick="location.href='join.do'">회원가입</button>
					      <button type="button" class="btn btn-default" onclick="location.href='findIdPwd.do'">아이디/비밀번호 찾기</button>
					    </div>
					  </div>
					</form>
				</div><!-- 9 end -->
			</div>
		</div>
	</section>
	<!-- contents end-->
<%@ include file = "../footer.jsp" %>