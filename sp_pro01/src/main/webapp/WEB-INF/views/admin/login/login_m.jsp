<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:url var="loginUrl" value="/login" />
<form action="${loginUrl}" method="post">
	<div class="form-floating mb-2">
		<input type="text" class="form-control" id="id-empId" name="empId" value="" placeholder="직원ID">
		<label for="id_empId">직원ID</label>
	</div>
	<div class="form-floating mb-2">
		<select class="form-select" id="id_deptId" name="deptId">
			<option>부서 선택</option>
			<c:forEach items="${deptDatas}" var="deptDto">
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
		<label for="id_deptId">부서 선택</label>
	</div>
	<div class="form-floating mb-2">
		<input type="text" class="form-control" id="id_empName" name="empName" value="${param.empName}" placeholder="이름">
		<label for="id_empName">이름</label>
	</div>
	<div class="mb-2 text-end">
		<div class="form-check from-check-inline form-switch">
			<input class="form-check-input" role="switch" type="checkbox" id="id_deptRe" 
				name="deptRe" ${not empty cookie.deptRe.value ? 'checked' : ''}>
			<label class="form-check-label" for="id_deptRe">부서기억하기</label>
		</div>
		<button class="btn btn-outline-primary" type="submit">로그인</button>
	</div>
</form>