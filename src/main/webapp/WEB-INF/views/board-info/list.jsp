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
<h3>board list</h3>
<select name="searchType" id="searchType">
	<option value="1">제목</option>
	<option value="2">작성자</option>
	<option value="3">내용</option>
	<option value="4">제목+내용</option>
	<option value="5">작성자+내용</option>
	<option value="6">제목+작성자</option>
	<option value="7">제목+작성자+내용</option>
</select>
<input type="text" name="searchStr" placeholder="검색어" id="searchStr">
<button onclick="loadFunc()">검색</button>
<table class="table table-bordered shadow">
	<thead>
		<tr>
			<th class="bg-primary text-white">번호</th>
			<th class="bg-secondary text-white">제목</th>
			<th class="bg-success text-white">작성자</th>
			<th class="bg-danger text-white">작성일</th>
		</tr>
	</thead>
	<tbody id="tBody">
	</tbody>
		<tr>
			<td colspan="4" align="right">
				<button class="btn btn-primary" onclick="location.href='/board-info/insert'">등록</button>
			</td>
		</tr>
</table>
</div>
<script>
	function goPage(url) {
		location.href = url;
	}
	const loadFunc = function() {
		const xhr = new XMLHttpRequest();
		const searchStr = document.querySelector('#searchStr');
		const searchType = document.querySelector('#searchType');
		
		let url = '/json/list?';
		if(searchStr.value !== '') {
			url += 'searchType=' + searchType.value + '&searchStr=' + searchStr.value;
		}
		xhr.open('GET', url);	// 내가 내 서버거 가져다쓰는거면 2번째 파라미터 주소에 http://localhost/ 생략해도됨
		xhr.onreadystatechange = function() {
			if(xhr.readyState === 4) {
				if(xhr.status === 200) {
					const obj = JSON.parse(xhr.responseText);
					let html = '';
					for(let i=0; i<obj.length; i++) {
						const board = obj[i];
						html += '<tr>';
						html += '<td>' + board.biNum + '</td>';
						html += '<td><a href="/views/board-info/view?biNum=' + board.biNum + '">' + board.biTitle + '</a></td>';
						html += '<td>' + board.uiName + '</td>';
						html += '<td>' + board.credat + '</td>';
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