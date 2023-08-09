<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<h3>test list</h3>
	<table>
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
</div>
<script>
const loadFunc = function() {
	const xhr = new XMLHttpRequest();
	
	let url = '/test-info/list';
	xhr.open('GET', url);	// 내가 내 서버거 가져다쓰는거면 2번째 파라미터 주소에 http://localhost/ 생략해도됨
	xhr.onreadystatechange = function() {
		if(xhr.readyState === 4) {
			if(xhr.status === 200) {
				const obj = JSON.parse(xhr.responseText);
				console.log(obj);
				let html = '';
				for(let i=0; i<obj.length; i++) {
					const list = obj[i];
					html += '<tr>';
					html += '<td>' + list.tiNum + '</td>';
					html += '<td><a href="/views/test-info/view?biNum=' + list.tiNum + '">' + list.tiName + '</a></td>';
					html += '<td>' + list.tiAge + '</td>';
					html += '<td>' + list.tiAddr + '</td>';
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