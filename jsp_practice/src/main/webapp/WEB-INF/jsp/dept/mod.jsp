<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="dept.model.DeptDTO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 수정</title>
	<%@ include file="../module/head.jsp" %>
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<section class="container">
		<form  class="small-form" action="./mod" method="post"> 
			<!-- 
				사용자에개 보여줄 팔요 없음 -> 수정x -> hidden
				id 를 이용해 나머지 data 들도 가져와야함으로 없으면 안됨 -> hidden
			-->
			<input class="input-text" type="hidden" name="deptId" value="${data.deptId}" readonly>
			<div class="input-form wide">
				<label class="input-label">부서명</label>
				<input class="input-text" type="text" name="deptName" value="${data.deptName}" data-required="부서명을 입력하세요.">
				<c:if test="${errorCode == 'error'}">
					<label class="input-label-error">${errorMsg}</label>
				</c:if>
			</div>
			<div class="input-form wide">
				<label class="input-label">관리자ID</label>
				<input class="input-text" type="text" name="mngId" value="${data.mngId}" data-required="관리자 ID를 입력하세요.">
				<c:if test="${errorCode == 'mngId'}">
					<label class="input-label-error">${errorMsg}</label>
				</c:if>
			</div>
			<div class="input-form wide">
				<label class="input-label">지역ID</label>
				<input class="input-text" type="text" name="locId" value="${data.locId}" data-required="지역 ID를 입력하세요.">
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