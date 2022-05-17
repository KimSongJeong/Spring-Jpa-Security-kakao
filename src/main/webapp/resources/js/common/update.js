{
		$("#Updatebtn").on("click", ()=> {
			let data = {
					id : $("#id").val(),
					username : $("#username").val(),
					email : $("#email").val(),
					password : $("#password").val(),
			}
			
			$.ajax({
				type:"PUT",
				url:"/user",
				data:JSON.stringify(data),
				contentType:"application/json;charset=utf-8",
				dataType:"json"
			}).done(function(res) {
				alert("회원정보 수정 완료");
				location.href="/";
			}).fail(function(error) {
				alert(JSON.stringify(error));
			});
		});
	}