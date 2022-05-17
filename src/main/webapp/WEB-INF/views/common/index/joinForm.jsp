<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/include/header.jsp"%>

	<%@ include file="/WEB-INF/views/include/topmenu.jsp"%>



	<div class="container">

		<div class="card-body m-2">
			<form>
				<div class="mb-3">
					<label for="email" class="form-label">이메일</label> 
					<input type="text" class="form-control" id="email">
				</div>
				<div class="mb-3">
					<label for="password" class="form-label">비밀번호</label> 
					<input type="password" class="form-control" id="password">
				</div>
				<div class="mb-3">
					<label for="username" class="form-label">닉네임</label> 
					<input type="text" class="form-control" id="username">
				</div>
				<div class="text-center">
				<button type="button" class="btn btn-primary" id="Joinbtn">회원가입</button>
				</div>
			</form>
		</div>
	</div>

	<script src="/resources/js/common/join.js"></script>

	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
	
