<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>   
 
 <c:if test="${adminid ne 'admin'}">
	<script>
		alert("아이디와 패스워드를 확인하세요");
		location.href='admin.do';
	</script>
 </c:if>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>TEDDY BEAR</title>
<!-- 부트 - 커먼 - 메인 - 서브 순 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
     <link href="css/summernote.min.css" rel='stylesheet'>
    <link href="css/font-awesome.min.css" rel="stylesheet">
    
    <link href="css/common.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <link href="css/sub.css" rel="stylesheet">
    
	<script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/summernote.min.js"></script>
    <script src="admin/js/product.js"></script>
    <script src="js/my.js"></script>
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
  <div class="container">
  	<header>
	  	<div class="col-md-12">
	  		<h1>HANULSO ADMIN</h1>
	  	</div>
  	</header>
  	<div class="col-md-12">
  		<div class="row">
  			<div class="col-md-2">
  				<div class="nav">
  					<ul>
  						<li><a href="admin_product_list.do">상품관리</a></li>
  						<li><a href="admin_order_list.do">주문관리</a></li>
  						<li><a href="admin_member_list.do">회원관리</a></li>
  						<li><a href="admin_qna_list.do">질문답변</a></li>
  						<li><a href="main.do">홈으로</a></li>
  						<li><a href="adminlogout.do">로그아웃</a></li>
  					</ul>
  				</div>
  			</div>
</html>