<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	이름 : <input type="text" name="name" id="name"><br>
	아이디 : <input type="text" name="id" id="id"><br>
	비밀번호 : <input type="password" name="pwd" id="pwd"><br>
	자기소개 : <textarea name="desc" id="desc"></textarea><br>
	성별 : <input type="radio" name="trans" value="남">남 <input type="radio" name="trans" value="여">여<br>
	직업 : 
	<select name="job" id="job">
		<option value="">선택</option>
		<option value="개발자">개발자</option>
		<option value="백수">백수</option>
	</select>
	<button onclick="sendObj()">저장</button>
	<div id="result"></div>
	<script>
		function sendObj() {
			const param = {
					name : document.querySelector('#name').value,
					id : document.querySelector('#id').value,
					pwd : document.querySelector('#pwd').value,
					desc : document.querySelector('#desc').value,
					trans : document.querySelector('[name=trans]:checked').value,
					job : document.querySelector('#job').value
			}
			const json = JSON.stringify(param);
			
			const xhr = new XMLHttpRequest();
			xhr.open('POST', '/input');
			xhr.setRequestHeader('Content-Type', 'application/json');
			xhr.onreadystatechange = function() {
				if(xhr.readyState === 4) {
					if(xhr.status === 200) {
						console.log(xhr.responseText);
					}
				}
			}
			xhr.send(json);
		}
	</script>
</body>
</html>