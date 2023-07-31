<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="POST" action="/board-info/delete">
<input type="hidden" name="biNum" value="${boardInfo.biNum}">
<table border="1">
	<tr>
		<th>번호</th>
		<td>${boardInfo.biNum}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${boardInfo.biTitle}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>${boardInfo.biContent}</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>${boardInfo.uiNum}</td>
	</tr>
	<tr>
		<th>작성일</th>
		<td>${boardInfo.creDat}</td>
	</tr>
	<c:if test="${user.uiNum == boardInfo.uiNum}">
	<tr>
		<th>
			<button type="button" onclick="location.href='/board-info/update?biNum=${boardInfo.biNum}'">수정</button>
			<button>삭제</button>
		</th>
	</tr>
	</c:if>
</table>
</form>
</body>
</html>