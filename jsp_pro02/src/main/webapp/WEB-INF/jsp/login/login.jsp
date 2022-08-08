<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>로그인</title>
	<%@ include file="../module/head.jsp" %>
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<section class="container">
		<c:url var="loginUrl" value="/login" />
		<form class="small-form" action="${loginUrl}" method="post">
			<div class="input-form wide">
				<label class="input-label">직원ID</label>
				<input type="text" class="input-text" name="empId" value="" data-required="직원ID를 입력하세요.">
				<c:if test="${not empty error}">
					<label class="input-label-error">${error}</label>
				</c:if>
			</div>
			<div class="input-form wide">
				<label class="input-label">부서명</label>
				<select class="select-form" name="deptId" data-required="부서명을 선택하세요.">
					<c:forEach items="${deptList}" var="deptDto">
						<c:choose>
							<c:when test="${empty error and cookie.deptRe.value == deptDto.deptId}">
								<option value="${deptDto.deptId}" selected>
									[${deptDto.deptId}] ${deptDto.deptName}
								</option>
							</c:when>
							<c:when test="${not empty error and param.deptId == deptDto.deptId}">
								<option value="${deptDto.deptId}" selected>
									[${deptDto.deptId}] ${deptDto.deptName}
								</option>
							</c:when>
							<c:otherwise>
								<option value="${deptDto.deptId}">
									[${deptDto.deptId}] ${deptDto.deptName}
								</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<div class="input-form wide">
				<label class="input-label">이름</label>
				<input type="text" class="input-text" name="empName" value="${param.empName}" data-required="이름을 입력하세요.">
			</div>
			<div class="input-form wide form-right">
				부서기억하기<input type="checkbox" name="deptRe" ${not empty cookie.deptRe.value ? 'checked' : ''}>
				<button class="btn btn-outline btn-ok" type="submit">로그인</button>
			</div>
		</form>
	</section>
</body>
</html>