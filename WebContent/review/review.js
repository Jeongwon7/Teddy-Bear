function review_modify(){
	var theForm = document.rvfrm;
	
	if(!theForm.title.value){
		alert("제목 입력하세요");
		theForm.title.focus();
	}else if(!theForm.rating.value){
		alert("별점을 매겨주세요");
	}else if(!theForm.content.value){
		alert("내용 입력하세요");
		theForm.content.focus();
	}else{
		theForm.encoding = "multipart/form-data";
		theForm.action = "reviewmodifypro.do";
		theForm.submit();
	}
}

function review_modifyOpen(rbno){
	location.href="reviewmodify.do?rbno="+rbno;
}

function review_delete(rbno, pseq, logId){
	var msg = confirm("삭제하시겠습니까?");
	if(msg == true){
		review_deletePro(rbno, pseq, logId);
	}
}

function review_deletePro(rbno, pseq, logId){
		 $.ajax({
             url: "reviewdelete.do",
             type: "POST",
             data:{rbno : rbno},
             success:function(result){
                 alert("삭제되었습니다");
				 getReview(pseq, logId);
             },
             error:function(error){
                 alert("통신실패");
             }
             
         });
	}
	
function getReview(pseq, logId){
	
	var output="";
	
	$.ajax({
		type:'get',
		url:'getReviewAjax.do',
		data: {pseq:pseq},
		dataType:"json",
		success:function(result){
			var num = result.length;
			for(var i in result){
						output += '<tr class="item">';
						output += '<td>'+num+'</td>';
						output += '<td>'+result[i].title+'</td>';
						output += '<td>';
						output += '<div class="star-ratings">';
						output += '<div class="star-ratings-fill space-x-2 text-lg">';
						if(result[i].star == 5 ){
							output += '<span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>';
						}else if(result[i].star == 4){
							output += '<span>★</span><span>★</span><span>★</span><span>★</span>';
						}else if(result[i].star == 3){
							output += '<span>★</span><span>★</span><span>★</span>';
						}else if(result[i].star == 2){
							output += '<span>★</span><span>★</span>';
						}else {
							output += '<span>★</span>';
						}
						output += '</div>';
						output += '<div class="star-ratings-base space-x-2 text-lg">';
						output += '<span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>';
						output += '</div>';
						output += '</div>';
						output += '</td>';
						output += '<td>'+result[i].writer+'</td>';
						output += '<td>'+result[i].regdate+'</td>';
						output += '</tr>';
						output += '<tr class="hide">';
						output += '<td colspan="5">';
						output += '<div class="review_content_wrap">';
						output += '<div class="review_content">'+result[i].content+'</div>';
						if(result[i].writer == logId){
							output += '<div class="review_btn">';
							output += '<span>';
							output += '<input type="button" value="수정" onclick="review_modifyOpen('+result[i].rbno+');">';
							output += '</span>';
							output += '<span>';
							output += '<input type="button" value="삭제" onclick="review_delete('+result[i].rbno +','+ result[i].pseq +', '+"'"+logId+"'"+');" >';
							output += '</span>';
							output += '</div>';
						}
					
						output += '</div>';
						output += '</td>';
						output += '</tr>'
						output += '</div>';
						output += '<c:set var="num" value="${num+1}"/>';
						num = num -1;
			}
			$(".review_box").html(output);
			
		},error:function(error){
			alert("통신에러");
		}
	})
	
}

function review_save(){
	var theForm = document.rvfrm;
	
	if(!theForm.title.value){
		alert("제목 입력하세요");
		theForm.title.focus();
	}else if(!theForm.rating.value){
		alert("별점을 매겨주세요");
	}else if(!theForm.content.value){
		alert("내용 입력하세요");
		theForm.content.focus();
	}else{
		theForm.encoding = "multipart/form-data";
		theForm.action = "review_write_pro.do";
		theForm.submit();
	}
}
