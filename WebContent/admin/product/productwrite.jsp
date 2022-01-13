<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../adminheader.jsp"%>
  			<div class="col-md-10">
  				<h2>상품등록</h2>
				<div class="col-md-12">
					<form class="form-horizontal" name="frm" method="post" enctype="multipart/form-data">
					  <div class="form-group">
					    <label for="kind" class="col-sm-2 control-label">상품분류</label>
					    <div class="col-sm-10">
					    	<select name="kind">
					    		<c:forEach items="${KindList}" var = "kind" varStatus="status"><!-- 반복실행하는 상태의 값을 저장 status -->
					    			<option value="${status.count}">${kind}</option><!-- count 값은 1부터 시작한다, 배열은 0부터 시작 -->
					    		</c:forEach>
					    	</select>
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="name" class="col-sm-2 control-label">상품명</label>
					    <div class="col-sm-10">
					      <input type="text" name="name" class="form-control" id="name" placeholder="상품명 입력">
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="price1" class="col-sm-2 control-label">원가</label>
					    <div class="col-sm-10">
					      <input type="text" name="price1" class="form-control" id="price1" placeholder="원가 입력" onKeyUp="NumberFormat(this)">
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="price2" class="col-sm-2 control-label">판매가</label>
					    <div class="col-sm-10">
					      <input type="text" name="price2"class="form-control" id="price2" placeholder="판매가 입력" onBlur="go_ab()" onKeyUp="NumberFormat(this)">
					    </div><!-- onBlur판매가 입력하고 빠져 나왔으 ㄹ때 -->
					  </div>
					  <div class="form-group">
					    <label for="price3" class="col-sm-2 control-label">순이익</label>
					    <div class="col-sm-10">
					      <input type="text" name="price3" class="form-control" id="price3" placeholder="마진 입력"  onKeyUp="NumberFormat(this)" readonly>
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="content" class="col-sm-2 control-label">상품설명</label>
					    <div class="col-sm-10">
							<textarea name="content" id="summernote" class="form-control" rows="10"></textarea>
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="image" class="col-sm-2 control-label">대표이미지</label>
					    <div class="col-sm-10">
					      <input type="file" name="image"class="form-control" id="image">
					    </div>
					  </div>
					  <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					      <button type="button" class="btn btn-default" onclick="go_save()">상품등록</button>
					       <button type="button" class="btn btn-default" onclick="go_mov()">목록으로</button>
					    </div>
					  </div>
					</form>
				</div>
  			</div>
  		</div>
  	</div>
  </div>
  
   		<script>
  	
  		
  		</script>
  </body>