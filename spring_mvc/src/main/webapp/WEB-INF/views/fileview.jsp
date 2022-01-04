<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 목록 보기</title>
</head>
<body>
	<div algin="center">
		<h2>파일 목록</h2>
		<table border="1">
			<tr>
				<th width="250">파일명</th>
			</tr>
			<c:forEach var="name" items="${list}">
				<tr>
					<!--  <td><a href="/img/${name}">${name}</a></td>-->
					<td><a href="/download?filename=${name}">${name}</a></td>
				</tr>

			</c:forEach>
		</table>
	</div>
</body>
</html>