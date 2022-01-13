function check() {
	var notice = document.notice;
			if(!notice.writer.value) {
				alert("글쓴이 입력하세요");
				notice.writer.focus();
				return false;
			}
			if(!notice.title.value) {
				alert("제목을 입력하세요");
				notice.title.focus();
				return false;
			}
			if(!notice.content.value) {
				alert("내용을 입력하세요");
				notice.content.focus();
				return false;
			}
			return true;
		}
