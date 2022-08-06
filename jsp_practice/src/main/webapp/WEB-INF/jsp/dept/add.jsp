<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.*, dept.model.DeptDTO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 추가</title>
	<%@ include file="../module/head.jsp" %>
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<!-- 
		action : 전송주소(현재위치에서 add) 
		현재 위치(./) : localhost:8080/jpr/depts
		doGet에서 받은 요청 post에 전달
	-->
	<section class="container">
		<div>
			<form class="small-form" action="./add" method="post"> 
				<div class="input-form wide">
					<label class="input-label">부서ID</label>
					<input class="input-text" type="text" name="deptId" value="${data.deptId == -1 ? '' : data.deptId}" data-required="부서 ID를 입력하세요.">
					<c:if test="${not empty error.deptId}">
						<label class="input-label-error">${error.deptId}</label>
					</c:if>
				</div>
				<div class="input-form wide">
					<label class="input-label">부서명</label>
					<input class="input-text" type="text" name="deptName" value="${data.deptName}" data-required="부서명을 입력하세요." >
					<c:if test="${not empty error.deptId}">
						<label class="input-label-error">${error.deptName}</label>
					</c:if>
				</div>
				<div class="input-form wide">
					<label class="input-label">관리자ID</label>
					<input class="input-text" type="text" name="mngId" value="${data.mngId == -1 ? '' : data.mngId}" data-required="관리자 ID를 입력하세요.">
					<c:if test="${not empty error.mngId}">
						<label class="input-label-error">${error.mngId}</label>
					</c:if>
				</div>
				<div class="input-form wide">
					<label class="input-label">지역ID</label>
					<input class="input-text" type="text" name="locId" value="${data.locId == -1 ? '' : data.locId}" data-required="지역 ID를 입력하세요.">
					<c:if test="${not empty error.locId}">
						<label class="input-label-error">${error.locId}</label>
					</c:if>
				</div>
				<div class="input-form wide">
					<button class="btn btn-outline btn-ok" type="submit">저장</button>
					<button class="btn btn-outline btn-cancel" type="button" onclick="location.href='<%=request.getContextPath() %>/depts'">취소</button>
				</div>
			</form>
		</div>
	</section>
</body>
</html>