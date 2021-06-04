<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<style>
.writerWrap {
	margin: 0 auto;
}

.tit {
	text-align: center;
}
</style>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 class="tit">게시물 작성</h1>


	<div class="col-md-12">
		<div class="col-md-8 writerWrap">
			<form action="/board" method="post" id="frm" onsubmit="return validChk()">
				<div class="form-group">
					<label for="title">제목</label> <input type="text" name="title"
						class="form-control" id="title">
				</div>
				<div class="form-group">
					<label for="title">작성자</label> <input type="text" name="writer"
						class="form-control" id="writer" value="${loginUser }" readonly="readonly">
				</div>
				<div class="form-group">
					<label for="title">내용</label>
					<textarea name="content" class="form-control" id="content"></textarea>
				</div>
				<button class="btn btn-primary">작성</button>
			</form>
		</div>
	</div>
	<script>
	function validChk(){
		if(frm.title.value == ''){
			alert('제목을 입력해주세요.')
			frm.title.focus();
			return false;
		}
		
		if(frm.content.value == ""){
			alert("내용을 입력해주세요.")
			frm.content.focus();
			return false;
		}
	}
	</script>

</body>
</html>