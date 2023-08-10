<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>이름</th>
			<td>
				<input type="text" name="uiName" id="uiName">
			</td>
		</tr>
		<tr>
			<th>아이디</th>
			<td>
				<input type="text" name="uiId" id="uiId">
			</td>
		</tr>
		<tr>
			<th>비번</th>
			<td>
				<input type="password" name="uiPwd" id="uiPwd">
			</td>
		</tr>
		<tr>
			<th>desc</th>
			<td>
				<textarea name="uiDesc" id="uiDesc"></textarea>
			</td>
		</tr>
		<tr>
			<th>생일</th>
			<td>
				<input type="date" name="uiBirth" id="uiBirth">
			</td>
		</tr>
		<tr>
			<th colspan="5">
				<button onclick="insertObj()">등록</button>
			</th>
		</tr>
	</table>
	<script>
		function insertObj() {
			const param = {
					uiName : document.querySelector('#uiName').value,
					uiId : document.querySelector('#uiId').value,
					uiPwd : document.querySelector('#uiPwd').value,
					uiDesc : document.querySelector('#uiDesc').value,
					uiBirth : document.querySelector('#uiBirth').value
			}
			const json = JSON.stringify(param);
			const xhr = new XMLHttpRequest();
			xhr.open('POST', '/user-info/insert');
			xhr.onreadystatechange = function() {
				if(xhr.readyState === 4) {
					if(xhr.status === 200) {
						console.log(xhr.responseText);
						if(xhr.responseText === '1') {
							alert('등록');
							location.href = '/views/user-info/list';
						} else {
							alert('오류');
							location.href = '/views/user-info/list';
						}
					}
				}
			}
			xhr.send(json);
		}
	</script>
</body>
</html>