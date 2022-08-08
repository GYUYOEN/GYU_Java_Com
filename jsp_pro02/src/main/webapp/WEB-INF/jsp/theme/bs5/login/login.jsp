<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<header class="mb-3">
		<%@ include file="../module/navigation.jsp" %>
	</header>
	<section class="container-fluid">
		<div class="card border-light mb-3" style="max-width: 520px; margin-left: auto; margin-right: auto;">
			<div class="card-header">
				<h1 class="text-center">로그인</h1>
			</div>
			<div class="card-body">
				<c:url var="loginUrl" value="/login" />
				<form action="${loginUrl}" method="post">
					<div class="form-floating mb-3">
						<input type="text" class="form-control" id="id_empId" name="empId" value=""
							placeholder="직원 ID" data-required="직원ID를 입력하세요.">
						<label for="id_empId">직원 ID</label>
					</div>
					<div class="form-floating mb-3">
						<select class="form-select" id="id_deptId" name="deptId"
							data-required="부서명을 선택하세요.">
							<option>부서명</option>
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
						<label for="id_deptId">부서명</label>
					</div>
					<div class="form-floating mb-3">
						<input type="text" class="form-control" id="id_empName" name="empName"
							placeholder="이름" value="${param.empName}" data-required="이름을 입력하세요.">
						<label for="id_empName">이름</label>
					</div>
					<div class="d-flex justify-content-end align-items-center mb-3">
						<div class="p-2">
							<input type="checkbox" class="form-check-input" id="id_deptRe" name="deptRe" ${not empty cookie.deptRe.value ? 'checked' : ''}>
							<label class="form-check-label" for="id_deptRe">부서기억하기</label>					
						</div>
						<div class="p-2">
							<button type="submit" class="btn btn-outline-primary">로그인</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</section>
</body>
</html>