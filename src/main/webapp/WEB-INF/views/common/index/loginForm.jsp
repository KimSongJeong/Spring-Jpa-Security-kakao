<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/include/header.jsp"%>

<%@ include file="/WEB-INF/views/include/topmenu.jsp"%>



<div class="container">

<div class="card m-5">

	<div class="m-2">
		<form action="/auth/loginProc" method="post">
			<div class="mb-3">
				<label for="username" class="form-label">아이디</label> <input type="text" class="form-control" name="username" id="username" aria-describedby="emailHelp">
			</div>

			<div class="mb-3">
				<label for="password" class="form-label">비밀번호</label> <input type="password" class="form-control" name="password" id="password">
			</div>
			
			<div class="text-center">
			<button type="submit" class="btn btn-primary" id="Loginbtn">로그인</button>
			<a href="https://kauth.kakao.com/oauth/authorize?client_id=dcd90f43314bbee7e009c68decb994b9&redirect_uri=http://localhost:8088/auth/kakao/callback&response_type=code"><img height="38px"
				src="/resources/image/kakao_login_btn.png"></a>
				</div>
		</form>
	</div>
	</div>
</div>




<%@ include file="/WEB-INF/views/include/footer.jsp"%>