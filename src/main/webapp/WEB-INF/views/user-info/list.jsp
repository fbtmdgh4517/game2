<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
<script src="/resource/js/bootstrap.js"></script>
<script src="/resource/js/bootstrap.bundle.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.9.2/umd/popper.min.js" integrity="sha512-2rNj2KJ+D8s1ceNasTIex6z4HWyOnEYLVC3FigGOmyQCZc2eBXKgOxQmo3oKLHyfcj53uz4QMsRCWNbLd32Q1g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet" href="/resource/css/bootstrap.css">
<link rel="stylesheet" href="/resource/css/bootstrap-grid.css">
<link rel="stylesheet" href="/resource/css/bootstrap-reboot.css">
</head>
<body>
<div class="container">
	<h3>user list</h3>
	<select name="searchType" id="searchType">
		<option value="1">이름</option>
		<option value="2">아이디</option>
	</select>
	<input type="text" name="searchStr" placeholder="검색어" id="searchStr">
	<button onclick="loadFunc()">검색</button>
	<table class="table table-bordered shadow">
		<thead>
			<tr>
				<th class="bg-primary text-white">번호</th>
				<th class="bg-secondary text-white">이름</th>
				<th class="bg-success text-white">아이디</th>
				<th class="bg-danger text-white">비밀번호</th>
				<th class="bg-warning text-white">desc</th>
			</tr>
		</thead>
		<tbody id="tBody">
			
		</tbody>
		<tr>
			<td colspan="5" align="right">
				<button class="btn btn-primary" onclick="location.href='/views/user-info/insert'">등록</button>
			</td>
		</tr>
	</table>
</div>
<script>
	function loadFunc() {
		const xhr = new XMLHttpRequest();
		const searchStr = document.querySelector('#searchStr');
		const searchType = document.querySelector('#searchType');
		let url = '/user-info/list?';
		if(searchStr.value !== '') {
			url += 'searchType=' + searchType.value + '&searchStr=' + searchStr.value;
		}
		xhr.open('GET', url);
		xhr.onreadystatechange = function () {
			if(xhr.readyState === 4) {
				if(xhr.status === 200) {
					const userList = JSON.parse(xhr.responseText);
					let html = '';
					console.log(userList);
					for(const user of userList) {
						html += '<tr>';
						html += '<td>' + user.uiNum + '</td>';
						html += '<td><a href="/views/user-info/view?uiNum=' + user.uiNum + '">' + user.uiName + '</a></td>';
						html += '<td>' + user.uiId + '</td>';
						html += '<td>' + user.uiPwd + '</td>';
						html += '<td>' + user.uiDesc + '</td>';
						html += '</tr>';
					}
					document.querySelector('#tBody').innerHTML = html;
				}
			}
		}
		xhr.send()
	}
	window.addEventListener('load', loadFunc);
</script>
</body>
</html>