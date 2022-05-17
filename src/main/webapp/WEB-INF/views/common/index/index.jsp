<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/include/header.jsp"%>

<body>
	<%@ include file="/WEB-INF/views/include/topmenu.jsp"%>

	<div class="container">

		<c:forEach var="board" items="${boards.content}">
			<div class="card m-2">
				<div class="card-body">
					<h5 class="card-title">${board.title}</h5>
					<p class="card-text">${board.content}</p>
					<a href="/board/${board.id}" class="btn btn-outline-primary" style="float: right;">상세보기</a>
				</div>
			</div>
		</c:forEach>

			<ul class="pagination justify-content-center">
			<c:choose>
			<c:when test="${boards.first}">
				<li class="page-item disabled"><a class="page-link">이전</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${boards.number-1}">이전</a></li>
			</c:otherwise>
			</c:choose>
			
			<c:choose>
			<c:when test="${boards.last}">
				<li class="page-item disabled"><a class="page-link">다음</a></li>
			</c:when>
			<c:otherwise>
			
				<li class="page-item"><a class="page-link" href="?page=${boards.number+1}">다음</a></li>
			</c:otherwise>
			</c:choose>
			

			</ul>

	</div>


	<%@ include file="/WEB-INF/views/include/footer.jsp"%>