<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계산기</title>
</head>
<body>
	<input type="number" name="num1" id="num1">
	<select name="op" id="op">
		<option value="+">더하기</option>
		<option value="-">빼기</option>
		<option value="*">곱하기</option>
		<option value="/">나누기</option>
		<option value="%">나머지</option>
	</select>
	<input type="number" name="num2" id="num2">
	<button onclick="calc()">계산</button>
	<div id="result"></div>
	<script>
		function calc() {
			const num1 = document.querySelector('#num1').value;
			const num2 = document.querySelector('#num2').value;
			const op = document.querySelector('#op').value;
			const xhr = new XMLHttpRequest();
			xhr.open('GET', '/calc?num1=' + num1 + '&num2=' + num2 + '&op=' + encodeURIComponent(op));
			xhr.onreadystatechange = function() {
				if(xhr.readyState === 4) {
					if(xhr.status === 200) {
						document.querySelector('#result').innerText = "결과 : " + xhr.responseText;
					}
				}
			}
			xhr.send();
		}
	</script>
</body>
</html>