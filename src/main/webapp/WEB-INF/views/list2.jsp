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
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>나이</th>
				<th>주소</th>
			</tr>
		</thead>
		<tbody id="tBody"></tbody>
	</table>
	<script>
		function loadFunc() {
			const xhr = new XMLHttpRequest();
			xhr.open('GET', '/list');
			xhr.onreadystatechange = function () {
				if(xhr.readyState === 4) {
					if(xhr.status === 200) {
						const list = JSON.parse(xhr.responseText);
						console.log(list);
						let html = '';
						for(const info of list) {
							html += '<tr>';
							html += '<td>' + info.num + '</td>';
							html += '<td>' + info.name + '</td>';
							html += '<td>' + info.age + '</td>';
							html += '<td>' + info.address + '</td>';
							html += '</tr>';
						}
						document.querySelector('#tBody').innerHTML = html;
					}
				}
			}
			xhr.send();
		}
		window.addEventListener('load', loadFunc);
	</script>
</body>
</html>