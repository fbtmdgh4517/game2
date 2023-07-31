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
<h3>board list</h3>
<div class="container">
<form action="/board-info/list" method="GET">
<select name="searchType">
	<option value="1">제목</option>
	<option value="2">작성자</option>
	<option value="3">내용</option>
	<option value="4">제목+내용</option>
	<option value="5">작성자+내용</option>
	<option value="6">제목+작성자</option>
	<option value="7">제목+작성자+내용</option>
</select>
<input type="text" name="searchStr" placeholder="검색어">
<button>검색</button>
</form>
<table class="table table-bordered shadow">
	<tr>
		<th class="bg-primary text-white">번호</th>
		<th class="bg-secondary text-white">제목</th>
		<th class="bg-success text-white">작성자</th>
		<th class="bg-danger text-white">작성일</th>
	</tr>
	<c:forEach items="${boardInfoList}" var="boardInfo">
		<tr class="bg-dark text-white">
			<td>${boardInfo.biNum}</td>
			<td>
				<a class="text-white" href="/board-info/view?biNum=${boardInfo.biNum}">${boardInfo.biTitle}</a>
			</td>
			<td>${boardInfo.uiName}</td>
			<td>${boardInfo.creDat}</td>
		</tr>
	</c:forEach>
	<c:if test="${user != null}">
	<tr>
		<td colspan="4" align="right">
			<button class="btn btn-primary" onclick="location.href='/board-info/insert'">등록</button>
		</td>
	</tr>
	</c:if>
</table>
</div>
</body>
</html>