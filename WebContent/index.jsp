<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "header.jsp"%>

	<div class="container">	
		<div class="row">
			
			  <!-- Wrapper for slides -->
			  <div class="slider">
			  	<div class="col-md-12">
			    	<div class="images">
			    		<img src="images/main/teddybanner1.jpg">
			    		
			    	</div>
			    </div>	
		    	<div class="col-md-12">
			    	<div class="images">
			    		<img src="images/main/teddybanner2.jpg">
			    		
			    	</div>
			    </div>	
		    	<div class="col-md-12">
			    	<div class="images">
			    		<img src="images/main/teddybanner3.jpg">
			    		
			    	</div>
			    </div>	
		 	</div>
		 </div>
	</div>
	
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
					<h2>NEW ITEM</h2>
						<div class="row">
							<c:forEach items="${newList}" var="list">
							  <div class="col-sm-6 col-md-4">
							    <div class="thumbnail">
							      <a href="productdetail.do?pseq=${list.pseq}"><img src="${pageContext.request.contextPath}/product_images/${list.image}" alt="신상품  사진"></a>
							      <div class="caption">
							        <h3>${list.name }</h3>
							        <p>
							        	<span style="color:#f00; font-size:22px;"><fmt:formatNumber value="${list.price2}"/></span>
							        	<span>원</span>
							        </p>
							      </div>
							    </div>
							  </div>
						  </c:forEach>
						 </div>  
				
					
				</div><!-- 9 end -->
			</div>
		</div>
	</section>
	<!-- contents end-->
<%@ include file = "footer.jsp"%>