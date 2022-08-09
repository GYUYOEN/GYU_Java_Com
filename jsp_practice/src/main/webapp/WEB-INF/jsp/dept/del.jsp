<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.myhome.web.dept.model.DeptDTO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 삭제</title>
</head>
<body>
	<%
		// data 가 무조건 있을 것임로 if문 사용해서 null인지 아닌지 판단하지 안아도 된다.
		DeptDTO data = (DeptDTO) request.getAttribute("data"); 
	%>
		<ul>
			<li>부서코드 : <%=data.getDeptId() %></li>
			<li>부서명 : <%=data.getDeptName() %></li>
		</ul>
		<p>삭제할 데이터가 맞습니까?</p>
		<div>
			<button type="submit" form="deleteForm">삭제</button> <!--  전송, 요청 -->
			<button type="button" onclick="history.back();">취소</button>
		</div>
		<!-- 
			전송, 요청 시 앞에 데이터들 다 적용안됨
			윗부분은 doGet에서 처리 form 영역에서 post에서 처리
		-->
		<form id="deleteForm" action="./del" method="post"> <!-- 전송 button이 원래는 form 안에 있어야 하지만 id로 연결시켜줄 수 있음 -->
			<input type="hidden" name="deptId" value="<%=data.getDeptId() %>"> <!-- 따라서 input을 넣어 주어야 함 -->
		</form>
</body>
</html>