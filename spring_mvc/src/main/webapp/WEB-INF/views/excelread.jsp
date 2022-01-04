<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>엑셀의 데이터 출력</title>
</head>
<body>
	<table align="center" border="1">
	<tr>
	<th>이름</th>
	<th>설명</th>
	<th>가격</th>
	</tr>
	<c:forEach var="map" items="${list}">
	<tr>
	<td>${map.itemname}</td>
	<td>${map.description}</td>
	<td>${map.price}</td>
	</tr>
	</c:forEach>
	</table>
</body>
</html>