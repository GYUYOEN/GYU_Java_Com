<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dept.model.DeptDTO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>삭제 확인</title>
</head>
<body>
	<!-- 데이터가 있는지 없는지 jsp 에서 판단 -> 동일한 구성일 때, 복잡하지 않을 때 (페이징) -->
	<%--
	<%
		DeptDTO data = null;
		if(request.getAttribute("data") != null) {
			data = (DeptDTO) request.getAttribute("data");
		}
		//  하나의 jsp에 다른 페이지 제공 (else와 서로 다른 페이지)
		if(data == null) { 
	%>
			<p>삭제 대상 데이터가 존재하지 않습니다.</p>
			<div>
				<button type="button" onclick="history.back();">돌아가기</button>
			</div>
	<% 
		} else {
	%>
			<ul>
				<li>부서코드 : <%=data.getDeptId() %></li>
				<li>부서명 : <%=data.getDeptName() %></li>
			</ul>
				<p>삭제할 데이터가 맞습니까?</p>
			<div>
				<button type="submit" form="deleteForm">삭제</button> <!-- form id와 연결 -->
				<button type="button" onclick="history.back();">취소</button>
			</div>
	--%>
	

	<!-- 데이터가 있는지 없는지 controller에서 판단 (훨씬 나음) -> 화면의 구성이 달라지면 별도의 jsp를 만들어줌 -->
	<%
	DeptDTO data = (DeptDTO) request.getAttribute("data");
	%>
	<ul>
		<li>부서코드 : <%=data.getDeptId() %></li>
		<li>부서명 : <%=data.getDeptName() %></li>
	</ul>
	<p>삭제할 데이터가 맞습니까?</p>
	<div>
		<button type="submit" form="deleteForm">삭제</button> <!-- form id와 연결 -> 해당 삭제 버튼이 눌리면 아래 폼으로 이동-->
		<button type="button" onclick="history.back();">취소</button>
	</div>
	<form id="deleteForm" action="./del" method="post">
		<input type="hidden" name="deptId" value="<%=data.getDeptId() %>">
	</form>
</body>
</html>