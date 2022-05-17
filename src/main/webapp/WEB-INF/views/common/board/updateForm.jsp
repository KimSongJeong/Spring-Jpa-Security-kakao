<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/include/header.jsp"%>

<body>
<%@ include file="/WEB-INF/views/include/topmenu.jsp"%>




	<div class="container">

		<div class="m-2">
			<form>
			<input type="hidden" id="id" value="${board.id}">
				<div class="mb-3">
					<input type="text" class="form-control" id=title value="${board.title}">
				</div>
				
				<div class="mb-3">
					<textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
				</div>

			</form>
				<button type="submit" class="btn btn-primary" id="boardUpdate" >수정</button>
		</div>
	</div>



<script src="/resources/js/board/board.js"></script>
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
