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
<h3>인덱스 ㅋㅋ</h3>
<c:if test="${user == null}">
<form method="POST" action="/user-info/login">
	<label for="uiId">아이디</label>
	<input type="text" id="uiId" name="uiId">
	<label for="uiId">비밀번호</label>
	<input type="password" id="uiPwd" name="uiPwd">
	<button>로그인</button>
</form>
</c:if>
<c:if test="${user != null}">
	${user.uiName}님 ㅎㅇ ㅋㅋ
</c:if>
<a href="/user-info/list">사용자 목록</a>
<a href="/board-info/list">게시물 목록</a>
<a href="/user-info/logout">로그아웃</a>
</body>
</html>