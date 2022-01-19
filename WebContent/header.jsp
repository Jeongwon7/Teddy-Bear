<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>   

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>TEDDY BEAR</title>
    <link href="images/bearFavicon.ico" rel="shortcut icon">
<!-- 부트 - 커먼 - 메인 - 서브 순 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/summernote.min.css" rel='stylesheet'>
    <link href="css/font-awesome.min.css" rel="stylesheet">
    
     
	
   
    
	<script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/summernote.min.js"></script>
    <script src="js/my.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="member/member.js"></script>
    <script src="mypage/mypage.js"></script>
    <script src="review/review.js"></script>
    <script src="notice/notice.js"></script>
    <script src="qna/qna.js"></script>
    
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
	<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
    
     <link href="css/common.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <link href="css/sub.css" rel="stylesheet">
   
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script>
    $(document).ready(function(){
    	
    	 $('.slider').bxSlider({
    		    mode: 'fade',
    		    auto: true,
    		    pause: 3000
    		  });
    	     
    });
    
	</script>	
  </head>
  <body>
	<header id="header">
		<div class="header">
			<div class="container">
				<div class="row">
					<div class="col-md-4">
						<h1 class="logo"><a href="main.do">TEDDY BEAR</a></h1>
					</div>
					<div class="col-md-8" style="margin-top:20px;">
						<ul class="nav navbar-nav pull-right">
							<c:choose>
								<c:when test="${empty sessionScope.loginUser}">
									<li><a href="login_form.do"><i class="fa fa-lock"></i>Login</a></li>
									<li><a href="join.do"><i class="fa fa-lock"></i>Join</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="logout.do"><i class="fa fa-lock"></i>Logout</a></li>
									<li><a href="memberModify.do"><i class="fa fa-lock"></i>정보수정</a></li>
								</c:otherwise>
							</c:choose>
									<li><a href="cartlist.do"><i class="fa fa-shopping-cart"></i>Cart</a></li>
									<li><a href="mypage.do"><i class="fa fa-user"></i>MyPage</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="header_bottom">
			<div class="container">
				<div class="row">
					<div class="col-md-9" style="padding:0;">
						<div class="navbar-header">
						      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						        <span class="sr-only">Toggle navigation</span>
						        <span class="icon-bar"></span>
						        <span class="icon-bar"></span>
						        <span class="icon-bar"></span>
						      </button>
						 </div>
						 <div class="mainmenu pull-left">
						 	  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							      <ul class="nav navbar-nav">
							        <li class="active"><a href="main.do">HOME<span class="sr-only">(current)</span></a></li>
							        <li class="dropdown">
							          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Product<span class="caret"></span></a>
							          <ul class="dropdown-menu" role="menu">
							            <li><a href="productall.do">Teddy Bear</a></li>
							          </ul>
							        </li>
							        <li><a href="notice.do">Notice</a></li>
							      </ul>
							   </div>
						 </div>
					</div>
					
				</div>
			</div>
		</div>
	</header>