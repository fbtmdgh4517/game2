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
<h3>user list</h3>
<table border="1">
	<tr>
		<th>번호</th>
		<th>아이디</th>
		<th>비번</th>
		<th>이름</th>
	</tr>
	<c:forEach items="${userInfoList}" var="userInfo">
		<tr>
			<td>${userInfo.uiNum}</td>
			<td>
				<a href="/user-info/view?uiNum=${userInfo.uiNum}">${userInfo.uiId}</a>
			</td>
			<td>${userInfo.uiPwd}</td>
			<td>${userInfo.uiName}</td>
		</tr>
	</c:forEach>
	<tr>
		<th colspan="5" align="right">
			<button onclick="location.href='/user-info/insert'">등록</button>
		</th>
	</tr>
</table>
</body>
</html>