function NumberFormat(won){
	comma = won.value;
	comma = comma.replace(/\D/g,'');//d:숫자를 찾음, D:숫자가 아닌 문자를 찾음
	len = comma.length-3;
	
	while(len >0){
		comma = comma.substr(0, len)+','+comma.substr(len);
		len -= 3;
	}

	won.value = comma;
	return won;
}

function go_ab() {
	var theForm = document.frm;
	var a = theForm.price2.value.replace(/,/g,"");
	var b = theForm.price1.value.replace(/,/g,"");
	var ab = parseInt(a) - parseInt(b);
	theForm.price3.value = ab;
}

function go_save(){
	var theForm = document.frm;//문서에서 네임이 frm인 폼태그를 변수에 저장
	if(!theForm.kind.value){
		alert("상품분류 선택하세요");
		theForm.kind.focus();
	}else if(!theForm.name.value){
		alert("상품명 입력하세요");
		theForm.name.focus();
	}else if(!theForm.price1.value){
		alert("원가 입력하세요");
		theForm.price1.focus();
	}else if(!theForm.price2.value){
		alert("판매가 입력하세요");
		theForm.price2.focus();
	}else if(!theForm.price3.value){
		alert("이익 입력하세요");
		theForm.price3.focus();
	}else if(!theForm.content.value){
		alert("상품상세설명 입력하세요");
		theForm.content.focus();
	}else if(!theForm.image.value){
		alert("대표상품 입력");
		theForm.image.focus();
	}else{
		theForm.encoding = "multipart/form-data";
		theForm.price1.value = removeComma(theForm.price1);
		theForm.price2.value = removeComma(theForm.price2);
		theForm.price3.value = removeComma(theForm.price3);
		theForm.action = "admin_product_write.do";
		theForm.submit();
	}
}
function removeComma(input){
	return input.value.replace(/,/gi,"");//콤마 빼라
	// g: 발생할 모든 패턴에 대한 전역 검색
	// i: 대소문자 구분 안함
}
function go_mov(){
	var theForm = document.frm;
	theForm.action = "admin_product_list.do";
	theForm.submit();	
}
function go_write(){
	location.href="admin_product_write_form.do";
}

function go_search(){
	document.frm.action = "admin_order_list.do";
	document.frm.submit();
}


