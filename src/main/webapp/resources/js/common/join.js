{
		$("#Joinbtn").on("click", ()=> {
			let data = {
					email : $("#email").val(),
					username : $("#username").val(),
					password : $("#password").val(),
			}
			
			$.ajax({
				type:"POST",
				url:"/auth/joinProc",
				data:JSON.stringify(data),
				contentType:"application/json;charset=utf-8",
				dataType:"json"
			}).done(function(resp) {
				if(resp.status === 500) {
				alert("회원가입 실패");
										
				}else { 
				alert("회원가입 완료");
				location.href="/";
				}
			}).fail(function(error) {
				alert(JSON.stringify(error));
			});
		});
	}