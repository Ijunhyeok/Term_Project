<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
.writerWrap {
   margin: 0 auto;
}

.tit {
   text-align: center;
}
</style>

</head>
<body>

<div class="col-md-12">
      <div class="col-md-8 writerWrap">
      <h1>로그인 페이지</h1>
         <form action="/login" method="post" id="frm" onsubmit="return validChk()">
            <div class="form-group">
               <label for="id">아이디</label> <input type="text" name="id"
                  class="form-control" id="id">
            </div>
            <div class="form-group">
               <label for="pw">패스워드</label> <input type="password" name="pw"
                  class="form-control" id="pw">
            </div>
            
            <button class="btn btn-primary">로그인</button>
            <a href="/join" role="button" class="btn btn-secondary">회원가입</a>
         </form>
      </div>
   </div>
   <script>
		function validChk(){
			if(frm.id.value == ''){
				alert('아이디를 입력해주세요.')
				frm.id.focus();
				return false;
			}
			
			if(frm.pw.value == ""){
				alert("비밀번호를 입력해주세요.")
				frm.pw.focus();
				return false;
			}
		}
	</script>

   
</body>
</html>