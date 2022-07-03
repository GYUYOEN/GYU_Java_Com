<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 추가</title>
</head>
<body>
	<h1>부서 추가</h1>
	<form action="./add" method="post">
		<div>
			<input type="text" name="deptId" placeholder="부서 ID">
		</div>
		<div>
			<input type="text" name="deptName" placeholder="부서 이름">
		</div>
		<div>
			<input type="text" name="mngId" placeholder="관리자 ID">
		</div>
		<div>
			<input type="text" name="locId" placeholder="지역 ID">
		</div>
	</form>
</body>
</html>