<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, dept.model.DeptDTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 추가</title>
	<%@ include file="../module/head.jsp" %>
</head>
<c:url var="ajaxDuplicateUrl" value="/ajax/duplicate" />
<c:url var="ajaxExistsUrl" value="/ajax/exists" />
<body>
	<%@ include file="../module/navigation.jsp" %>
	<section class="container">
		<form class="small-form" action="./add" method="post">
			<div class="input-form wide">
				<label class="input-label">부서ID</label>
				<input type="text" class="input-text" name="deptId" onblur="duplicateCheck(this, '${ajaxDuplicateUrl}');"
					value="${data.deptId == -1 ? '' : data.deptId}" data-required="부서 ID를 입력하세요.">
				<label class="input-label-error"></label>
			</div>
			<div class="input-form wide">
				<label class="input-label">부서명</label>
				<input type="text" class="input-text" name="deptName" value="${data.deptName}" data-required="부서명을 입력하세요.">
				<label class="input-label-error"></label>
			</div>
			<div class="input-form wide">
				<label class="input-label">관리자ID</label>
				<input type="text" class="input-text" name="mngId" onblur="existsCheck(this, '${ajaxExistsUrl}');"
					value="${data.mngId == -1 ? '' : data.mngId}" data-required="관리자 ID를 입력하세요.">
				<label class="input-label-error"></label>
			</div>
			<div class="input-form wide">
				<label class="input-label">지역ID</label>
				<input type="text" class="input-text" name="locId" onblur="existsCheck(this, '${ajaxExistsUrl}');"
					value="${data.locId == -1 ? '' : data.locId}" data-required="지역 ID를 입력하세요.">
				<label class="input-label-error"></label>
			</div>
			<div class="input-form wide form-right">
				<button class="btn btn-outline btn-ok" type="submit">저장</button>
				<button class="btn btn-outline btn-cancel" type="button" onclick="location.href='<%=request.getContextPath() %>/depts'">취소</button>
			</div>
		</form>
	</section>
</body>
</html>