<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>main 화면</title>
	<%@ include file="./module/head.jsp" %>
</head>
<body>
	<%@ include file="./module/navigation.jsp" %>
	<section class="container">
		${sessionScope.loginData.empName} 님 환영합니다.
	</section>
</body>
</html>