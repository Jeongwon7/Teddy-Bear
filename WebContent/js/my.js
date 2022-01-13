



$(function(){
   var total = function(n){
	   var price= $(".newitem .information p span").attr("class");//자바속성 값을 스크립트 변수에 저장 방법
	   var amount = $(".quantity").attr("value");// 수량
	   var amount = parseInt(amount);// 정수 숫자 변환
	   var amount = amount + n //수량 증가 감소 수식

	   if(amount < 1){
	      amount = 1;
	      alert("최소 수량은 1개 입니다.")
	   }

	   var tot = price* amount; // 수량  * 단가
	   
	   $(".quantity").attr("value", amount);
	   var regexp=/\B(?=(\d{3})+(?!\d))/g;
	   var tot = tot.toString().replace(regexp,',');
	   $(".total").html(tot +"("+amount+"개)");
   }
   
   total(0);
   $(".plus").on("click",function(){
      total(1);
   });
   $(".minus").on("click",function(){
      total(-1);
   });
   
	//썸머노트
	$('#summernote').summernote({
		height: 400,
		fontNames : [ '맑은고딕', 'Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', ],
		fontNamesIgnoreCheck : [ '맑은고딕' ],
		focus: true,

	callbacks: {//콜백★ 썸머노트 안에는 콜백 기능이 없다 https://www.w3schools.com/jquery/jquery_callback.asp
	onImageUpload: function(files, editor, welEditable) {
	            for (var i = files.length - 1; i >= 0; i--) {//이미지 5개 삽입: files.length = 이미지 삽입 길이(5)
	             sendFile(files[i], this);
	            //삽입한 이미지를 sendFile 메서드로 보내라는
	            }
	        }
	}

	});

	

	function sendFile(file, el) {
	var form_data = new FormData();
	       form_data.append('file', file);
	       $.ajax({
	         data: form_data,
	         type: "POST",
	         url: 'productImage.do',//앞에 슬래시(/)치지 않기..!
	         cache: false,
	         contentType: false,
	         enctype: 'multipart/form-data',//첨부가 있을 때
	         processData: false,
	         success: function(img_name) {
	           $(el).summernote('editor.insertImage', img_name);//얜 개발자가 만들어놓은 거니까 건들지 말기
	         }
	       });
	    }


});

