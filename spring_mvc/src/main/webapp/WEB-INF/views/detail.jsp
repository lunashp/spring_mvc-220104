<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 보기</title>
<link rel="stylesheet" href="/css/style.css" />
</head>
<body>
	<h3>상품 상세 설명</h3>
	<table>
		<tr>
			<td><img src="/img/${item.pictureurl}" width="300" height="300"/></td>
			<td align="center">
				<table>
					<tr height="50">
						<td width="80">상품이름</td>
						<td width="160">${item.itemname}</td>
					</tr>
					<tr height="50">
						<td width="80">가격</td>
						<td width="160">${item.price}원</td>
					</tr>
					<tr height="50">
						<td width="80">비고</td>
						<td width="160">${item.description}</td>
					</tr>
					<tr>
						<td align="center" width="240" colspan="2"><a href="/">목록으로
						</a></td>
						</tr>
				</table>
</body>
</html>