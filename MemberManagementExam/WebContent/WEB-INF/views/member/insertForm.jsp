<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신규회원 등록</title>
<style>
	table {
		border: 2px solid blue;
   	 	border-collapse : collapse;
	}
	tr, td {
		padding: 5px;
	}
</style>
</head>

<body>
	<h3>회원 정보 입력 폼</h3>
	<form method="post" enctype="multipart/form-data">
		<table border=1>
			<tr>
				<td>회원ID</td>
				<td>
					<input type="text" name="memId" value="">
				    <button id="idChk">중복확인</button>
	                <span id="disp"></span>
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="text" name="memPass" value=""></td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td><input type="text" name="memPassCheck" value=""></td>
			</tr>
			<tr>
				<td>회원이름</td>
				<td><input type="text" name="memName" value=""></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="memTel" value=""></td>
			</tr>
			<tr>
				<td>회원주소</td>
				<td><input type="text" name="memAddr" value=""></td>
			</tr>
			<tr>
				<td>프로필 사진</td>
				<td><input type="file" name="atchFile"></td>
			</tr>
			<tr align="center">
				<td colspan="2">
					<input id="add" type="submit" value="저장" disabled>
				    <button onclick="location.href='list.do'">취소</button>
				    <button onclick="location.href='list.do'">회원목록</button>
				</td>
			</tr>
		</table>
	</form>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>
	$('#idChk').on('click', function() {

		// click이벤트 실행후에 submit이벤트 발생하여 화면전환되는 현상을 막기
		event.preventDefault();
		
		// 영역초기화
		$('#disp').text("");
	
		// id정보 가져오기
		let idVal = $('input[name=memId]').val();
		
		// 입력된 id정보가 없는 경우(null체크)
		if (idVal.trim().length == 0) return;
		
		// jqueryAJAX
		$.ajax({
			url:'../idChk.jsp',
			type:'post',
			// data:'key=value',
			// javascript의 경우 Object형태의 데이터를 json형태로 변환시킨 후 전송 필요 
			// 이때 setRequestHeader설정(urlencoded형식변환)도 필요
			// jQuery ajax사용시 객체 데이터를 urlencoded(key=value)형식으로 자동변환해서 넘겨줌
			data: {'id' : idVal},
			success:function(data) {
				console.log(data);
				if (data.code == "ok") {
					$('#disp').text(data.msg).css('color', 'green');
					$('#add').removeAttr("disabled");
				} else {
					$('#disp').text(data.msg).css('color', 'red');
				}
			},
			error:function(xhr) {
				alert("상태>>" + xhr.status);
			},
			dataType:'json'
		});
	});
	
	$('form').submit(function(e) {
	    e.preventDefault();  // 폼 서브밋 막기

	    let memPass = $('input[name=memPass]').val();
	    let memPassCheck = $('input[name=memPassCheck]').val();
	    
	    // 입력한 비밀번호 두개가 일치하지 않으면,서버로 데이터를 제출하지 않음
	    if (memPass != memPassCheck) {
	    	alert("입력한 두 비밀번호가 일치하지 않습니다.");
	    	return;
	    }

	    // FormData 생성
	    var formData = new FormData(this);

	    // Ajax 요청
	    $.ajax({
			type: 'post',
			url: ' ./insert.do',
			data: formData,
			processData: false,
			contentType: false,
			success: function(result) {
				console.log(result);
				if (result.trim() == "성공") {
					location.href = "list.do";
				}
			},
			error: function(xhr) {
				alert("상태: " + xhr.status);
			},
			dataType: 'text'
		}); 
	});
	
</script>
</html>