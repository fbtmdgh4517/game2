<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<h3>상세</h3>
<table class="table table-bordered shadow">
	<tr>
		<th class="bg-primary text-white">번호</th>
		<td id="biNum">${param.biNum}</td>
	</tr>
	<tr>
		<th class="bg-secondary text-white">제목</th>
		<td id="biTitle"></td>
	</tr>
	<tr>
		<th class="bg-success text-white">내용</th>
		<td id="biContent"></td>
	</tr>
	<tr>
		<th class="bg-danger text-white">작성자</th>
		<td id="uiNum"></td>
	</tr>
	<tr>
		<th class="bg-primary text-white">작성일</th>
		<td id="credat"></td>
	</tr>
	<tr>
		<td colspan="4" align="right">
			<button type="button" onclick="location.href='/board-info/update?biNum=${boardInfo.biNum}'" class="btn btn-primary">수정</button>
			<button class="btn btn-danger">삭제</button>
		</td>
	</tr>
</table>
</div>
<script>
	function goPage(url) {
		location.href = url;
	}
	function loadFunc() {
		const xhr = new XMLHttpRequest();
		xhr.open('GET', '/json/one?biNum=${param.biNum}');
		xhr.onreadystatechange = function() {
			if(xhr.readyState === 4) {
				if(xhr.status === 200) {
					const board = JSON.parse(xhr.responseText);
					for(let key in board) {
						console.log(key, board[key]);
						if(document.querySelector('#' + key)) {
							document.querySelector('#' + key).innerText = board[key];
						}
					}
				}
			}
		}
		xhr.send();
	}
	window.addEventListener('load', loadFunc);
</script>
</body>
</html>