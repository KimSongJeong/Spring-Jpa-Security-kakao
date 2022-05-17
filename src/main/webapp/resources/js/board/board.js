{

	$(".summernote").summernote({
		tabsize: 2,
		height: 300
	});

	// 글 생성
	$("#boardSave").on("click", () => {
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		}

		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json;charset=utf-8",
			dataType: "json"
		}).done(function(res) {
			alert("글쓰기 완료");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	});


	// 글 삭제
	$("#boardDelete").on("click", () => {
		var id = $("#id").text();
		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id,
			dataType: "json"
		}).done(function(res) {
			alert("삭제 완료");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	});


	// 글 수정
	$("#boardUpdate").on("click", () => {
		var id = $("#id").val();
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		}

		$.ajax({
			type: "PUT",
			url: "/api/board/" + id,
			data: JSON.stringify(data),
			contentType: "application/json;charset=utf-8",
			dataType: "json"
		}).done(function(res) {
			alert("글 수정 완료");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	});

	// 댓글 작성
	$("#replyBtn").on("click", () => {
		let data = {
			userId: $("#userId").val(),
			boardId: $("#boardId").val(),
			content: $("#replyContent").val(),
		}
		$.ajax({
			type: "POST",
			url: `/api/board/${data.boardId}/reply`,
			data: JSON.stringify(data),
			contentType: "application/json;charset=utf-8",
			dataType: "json"
		}).done(function(res) {
			alert("댓글 작성 완료");
			location.href = `/board/${data.boardId}`;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	});


	// 댓글 삭제
	function replyDelete(boardId, replyId) {
		$.ajax({
			type: "DELETE",
			url: `/api/board/${boardId}/reply/${replyId}`,
			dataType: "json"
		}).done(function(res) {
			alert("댓글 삭제 완료");
			location.href = `/board/${boardId}`;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});

	};


}