function go_cart(){
	alert("상품이 장바구니에 담겼습니다");
	document.formm.action="cartInsert.do";//수량, 상품번호만 넘어온다
	document.formm.submit();
}

function goCartDelete(){
	
	var count = 0;
	
	if(document.formm.cseq.length == undefined){//checkbox input이 한 개일 때
		if(document.formm.cseq.checked == true){
			count++;
		}
	}
	
	for(var i=0 ; i<document.formm.cseq.length ; i++){
		if(document.formm.cseq[i].checked == true){
			count++;
		}
	}
	if(count == 0){
		alert("삭제할 상품을 선택하세요");
		
	}else{
		document.formm.action="cartDelete.do";
		document.formm.submit();//배열 형태로 체크된 값이 넘어간다
	}
}

function goOrderInsert(){
	document.formm.action = "orderinsert.do";
	document.formm.submit();
}