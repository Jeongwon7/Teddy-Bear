<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Cookie[] cookie = request.getCookies();
//찾은 쿠키를 저장할 배열을 생성 request를 통해 쿠키를 받아 온다
String id="";//아이디에 해당된 쿠키를 찾았을 경우 저장할 문자열
if(cookie != null){//만약 쿠키가 null이 아니라면
	for(int i=0; i<cookie.length; i++){//총 쿠키 배열의 크기만큼 for문 돌고
		if(cookie[i].getName().trim().equals("id")){
			System.out.println(cookie[i].getValue());
			id=cookie[i].getValue();//id라는 이름의 키 (쿠키값)가 있을 경우 문자열에 그 쿠키의 값을 넣는다
		}
	}
}

%>
    
    
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
    <link href="css/font-awesome.min.css" rel="stylesheet">
    
    <link href="css/common.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <link href="css/sub.css" rel="stylesheet">
    
	<script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
  <div class="container" style="margin:150px 0 0 500px;">
	 <div class="row">
		<div class="col-md-6">
			<h1>ADMIN LOGIN</h1>
			<form class="form-horizontal" name="admin" method="post" action="adminok.do">
			  <div class="form-group">
			    <label for="id" class="col-sm-2 control-label">ID</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" name="id" id="id" value="<%=id%>" placeholder="관리자 아이디">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="pwd" class="col-sm-2 control-label">PW</label>
			    <div class="col-sm-10">
			      <input type="password" class="form-control" name="pwd" id="pwd" placeholder="관리자 패스워드">
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <div class="checkbox">
			        <label>
			          <input type="checkbox" name="id_rem" <%if(id.length()>1) out.println("checkd"); %> >Remember me
			        </label>
			      </div>
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <button type="submit" class="btn btn-default" onclick="return fn_login()">log in</button>
			    </div>
			  </div>
		</form>
		</div> 
		</div> 
	</div>
  	
   <script>
		$(function() {
			
			var msg = "${msg}";
			if(msg){
				alert("${msg}");//msg 값을 띄움
			}
			
			
		});
	</script>
	<script>
		function fn_login(){
			
			if(admin.id.value==""){
				alert("아이디를 입력하세요");
				admin.id.focus();
				return false;
			}
			if(admin.pwd.value==""){
				alert("패스워드를 입력하세요");
				admin.pwd.focus();
				return false;
			}
			return true;
			//var form = document.login;//폼 이름 login을 변수에 저장(다룰 수 있게 된다)
			//form.method='post';
			//form.action="login.do";
			//form.submit();
		}
	</script>
  </body>