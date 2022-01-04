<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파라미터 입력</title>
</head>
<body>
	<form action="/param" method="post">
		이름:<input type="text" name="name" /></br> 나이:<input type="text" name="age" /></br>
		<fieldset>
			<legend>성별</legend>
			<input type="radio" name="gender" value="woman" />여자 <input
				type="radio" name="gender" value="man" checked='checked' />남자
		</fieldset>
		
		<fieldset>
			<legend>직업</legend>
			<select name="job">
				<option value="student">학생</option>
				<option value="itengineer">IT 관련</option>
				<option value="publicofficial">공무원</option>
				<option value="etc">기타</option>
			</select><br />
		</fieldset>
		<input type="submit" value="전송" />
	</form>
</body>
</html>