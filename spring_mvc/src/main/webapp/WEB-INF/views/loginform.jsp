<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Spring Custom Tag를 사용하기 위한 설정 -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- Spring Form Tag를 사용하기 위한 설정 -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="login.form.title" /></title>
</head>
<body>
	<!-- action이 없으면 자신이 보여지게 된 요청 그대로 설정 하고 method가 없으면 post -->
	<form:form modelAttribute="member">
		<p>
			<label for="email"> <spring:message code="email" />
			</label>
			<form:input path="email" />
			<form:errors path="email" />
		</p>

		<p>
			<label for="password"> <spring:message code="password" />
			</label>
			<form:password path="pw" />
			<form:errors path="pw" />
		</p>

		<p>
			<label for="loginType">로그인 유형</label>
			<form:select path="loginType" items="${loginTypes}" />
		</p>
		<input type="submit" value="<spring:message code="login.form.login"/>" />
	</form:form>
</body>
</html>