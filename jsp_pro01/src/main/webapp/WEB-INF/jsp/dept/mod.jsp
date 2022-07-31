<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dept.model.DeptDTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- 제어문 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- 낳짜/ 숫자 등의 포멧 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <!-- 주로 문자열과 관련된 함수 -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 수정</title>
	<%@ include file="../module/head.jsp" %>
</head>	
<body>
	<%@ include file="../module/navigation.jsp"%>
	<section class="container">
		<form class="small-form" action="./mod" method="post">
			<input type="hidden" class="input-text" name="deptId" value="${data.deptId}" readonly>
			<div class="input-form wide">
				<label class="input-label">부서명</label>
				<input type="text" class="input-text" name="deptName" value="${data.deptName}" data-required="부서명을 입력하세요.">
				<c:if test="${errorCode == 'error'}">
					<label class="input-label-error">${errorMsg}</label>
				</c:if>
			</div>
			<div class="input-form wide">
				<label class="input-label">관리자ID</label>
				<input type="text" class="input-text" name="mngId" value="${data.mngId}" data-required="관리자 ID를 입력하세요."> <!-- data-required : js(javascript) 활용  -->
				<c:if test="${errorCode == 'mngId'}">
					<label class="input-label-error">${errorMsg}</label>
				</c:if>
			</div>
			<div class="input-form wide">
				<label class="input-label">지역ID</label>
				<input type="text" class="input-text" name="locId" value="${data.locId}" data-required="지역 ID를 입력하세요.">
				<c:if test="${errorCode == 'locId'}">
					<label class="input-label-error">${errorMsg}</label>
				</c:if>
			</div>
			<div class="input-form wide form-right">
				<button class="btn btn-outline btn-ok" type="submit">저장</button>
				<button class="btn btn-outline btn-cancel" type="button" onclick="location.href='${pageContext.request.contextPath}/depts'">취소</button>
			</div>
		</form>
	</section>
</body>
</html>