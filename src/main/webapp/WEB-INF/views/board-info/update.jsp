<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>board update</h3>
<form method="POST" action="/board-info/update">
<input type="hidden" name="biNum" value="${boardInfo.biNum}">
<table border="1">
	<tr>
		<th>제목</th>
		<td>
			<input type="text" name="biTitle" value="${boardInfo.biTitle}">
		</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>
			<textarea name="biContent">${boardInfo.biContent}</textarea>
		</td>
	</tr>
	<tr>
		<th colspan="5">
			<button>수정</button>
		</th>
	</tr>
</table>
</form>
</body>
</html>