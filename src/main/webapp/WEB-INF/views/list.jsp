<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	tr.link {
		background-color: white;
		color: black;
		point: pointer;
	}
	tr.link:hover {
		color: blue;
	}
</style>
</head>
<body>
	<button onclick="goInsertPage()">등록</button>
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
		function goPage(num) {
			location.href = '/views/one?num=' + num;
		}
		function goInsertPage() {
			location.href = '/views/insert';
		}
		function loadFunc() {
			const xhr = new XMLHttpRequest();
			xhr.open('GET', '/list/list');
			xhr.onreadystatechange = function() {
				if(xhr.readyState === 4) {
					if(xhr.status === 200) {
						const list = JSON.parse(xhr.responseText);
						let html = '';
						for(const info of list) {
							html += '<tr class="link" onclick="goPage(' + info.num + ')">';
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