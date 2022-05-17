<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/include/header.jsp"%>

<%@ include file="/WEB-INF/views/include/topmenu.jsp"%>



<div class="container">

	<div class="m-2">
		<form>
			<input type="hidden" id="id" value="${principal.user.id}" readonly>
			<div class="mb-3">
				<label for="username" class="form-label">닉네임</label> <input type="text" class="form-control" id="username" value="${principal.username}" readonly>
			</div>

			<c:choose>
				<c:when test="${empty principal.user.oauth}">
					<div class="mb-3">
						<label for="password" class="form-label">비밀번호</label> <input type="password" class="form-control" id="password" placeholder="Enter Password">
					</div>
					<div class="mb-3">
						<label for="email" class="form-label">이메일</label> <input type="text" class="form-control" id="email" value="${principal.user.email}" placeholder="Enter email">
					</div>
				</c:when>
				<c:otherwise>
					<div class="mb-3">
						<label for="email" class="form-label">이메일</label> <input type="text" class="form-control" id="email" value="${principal.user.email}" placeholder="Enter email" readonly>
					</div>
				</c:otherwise>
			</c:choose>




			<button type="button" class="btn btn-primary" id="Updatebtn">수정</button>
		</form>
	</div>
</div>

<script src="/resources/js/common/update.js"></script>

<%@ include file="/WEB-INF/views/include/footer.jsp"%>

