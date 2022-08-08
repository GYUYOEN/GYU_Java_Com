<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dept.model.DeptDTO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>삭제 오류</title>
</head>
<body>
	<p><%=request.getAttribute("errorMsg") %></p>
	<div>
		<button type="button" onclick="location.href='./'">돌아가기</button>
	</div>
</body>
</html>