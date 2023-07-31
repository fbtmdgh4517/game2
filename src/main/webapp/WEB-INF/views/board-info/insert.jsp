<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="POST" action="/board-info/insert">
<input type="hidden" name="uiNum" value="${user.uiNum}">
<table border="1">
	<tr>
		<th>제목</th>
		<td>
			<input type="text" name="biTitle">
		</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>
			<textarea name="biContent"></textarea>
		</td>
	</tr>
	<tr>
		<th colspan="5">
			<button>등록</button>
		</th>
	</tr>
</table>
</form>
</body>
</html>