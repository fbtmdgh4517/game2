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
<h3>view</h3>
<form method="POST" action="/user-info/delete">
<input type="hidden" name="uiNum" value="${userInfo.uiNum}">
<table border="1">
	<tr>
		<th>번호</th>
		<td>${userInfo.uiNum}</td>
	</tr>
	<tr>
		<th>이름</th>
		<td>${userInfo.uiName}</td>
	</tr>
	<tr>
		<th>아이디</th>
		<td>${userInfo.uiId}</td>
	</tr>
	<tr>
		<th>비번</th>
		<td>${userInfo.uiPwd}</td>
	</tr>
	<tr>
		<th>desc</th>
		<td>${userInfo.uiDesc}</td>
	</tr>
	<c:if test="${user.uiNum == userInfo.uiNum}">
	<tr>
		<th colspan="5">
			<button type="button" onclick="location.href='/user-info/update?uiNum=${userInfo.uiNum}'">수정</button>
			<button>삭제</button>
		</th>
	</tr>
	</c:if>
</table>
</form>
</body>
</html>