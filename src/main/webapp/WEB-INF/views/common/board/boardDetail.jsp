<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/include/header.jsp"%>

<body>
	<%@ include file="/WEB-INF/views/include/topmenu.jsp"%>




	<div class="container">

		<div class="m-2">
			<div class="mb-3">
				<h3>${board.title}</h3>
				글번호 : <span id="id"><i>${board.id}</i></span> 작성자 : <span id="id"><i>${board.user.username}</i></span> 작성일 : <span id="id"><i>${board.createDate}</i></span>
			</div>
			<hr>
			<div class="mb-3">
				<div>${board.content}</div>
			</div>

			<div class="text-right">
				<button type="button" class="btn btn-outline-secondary" onclick="history.back()">돌아가기</button>
				<c:if test="${board.user.id == principal.user.id}">
					<a class="btn btn-outline-success" href="/board/${board.id}/updateForm">수정</a>
					<button type="button" class="btn btn-outline-danger" id="boardDelete">삭제</button>
				</c:if>
			</div>
		</div>

		<hr>
		<div class="card">
			<form>
				<input type="hidden" id="userId" value="${principal.user.id}"> <input type="hidden" id="boardId" value="${board.id}">
				<div class="card-body">
					<textarea id="replyContent" class="form-control" rows="1"></textarea>
				</div>
				<div class="card-footer">
					<button type="button" id="replyBtn" class="btn btn-sm btn-primary">등록</button>
				</div>
			</form>
		</div>

		<hr>
		<div class="card">
			<div class="card-header">댓글 리스트</div>
			<ul id="reply-Box" class="list-group">
				<c:forEach var="reply" items="${board.replys}">
					<li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
						<div>${reply.content}</div>
						<div class="d-flex">
							<div>작성자 : ${reply.user.username} &nbsp;</div>
							<button class="btn btn-sm btn-outline-danger" onClick="replyDelete(${board.id}, ${reply.id})">삭제</button>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>



	<script src="/resources/js/board/board.js"></script>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>