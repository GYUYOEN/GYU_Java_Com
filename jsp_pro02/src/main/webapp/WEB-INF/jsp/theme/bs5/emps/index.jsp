<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>직원</title>
	<%@ include file="../module/head.jsp" %>
</head>
<body>
	<header class="mb-3">
		<%@ include file="../module/navigation.jsp" %>
	</header>
	<section class="container-fluid">
		<div class="row mb-1">
			<div class="col-auto me-auto">
				<c:url var="empsAddUrl" value="/emps/add" />
				<button class="btn btn-sm btn-outline-secondary" type="button" onclick="location.href='${empsAddUrl}'">추가</button>
			</div>
			<c:url var="empsUrl" value="/emps" />
			<form class="col-auto" action="${empsUrl}" method="get">
				<div class="row g-1">
					<div class="col-auto">
						<input class="form-control form-control-sm" type="text" name="search" data-required="직원 이름을 입력하세요.">
					</div>
					<div class="col-auto">
						<button class="btn btn-sm btn-outline-secondary" type="submit">조회</button>
					</div>
					<div class="col-auto">
						<select class="form-select form-select-sm" onchange="location.href='${empsUrl}?pageCount=' + this.value">
							<option value="5" ${sessionScope.pageCount == 5 ? 'selected' : ''}>5 개</option>
							<option value="10" ${sessionScope.pageCount == 10 ? 'selected' : ''}>10 개</option>
							<option value="15" ${sessionScope.pageCount == 15 ? 'selected' : ''}>15 개</option>
							<option value="20" ${sessionScope.pageCount == 20 ? 'selected' : ''}>20 개</option>
						</select>
					</div>
				</div>
			</form>
		</div>
		<table class="table table-striped table-hover table-sm">
			<colgroup>
				<col class="col col-1">
				<col class="col col-2">
				<col class="col col-3">
				<col class="col col-3">
				<col class="col col-2">
				<col class="col col-1">
			</colgroup>
			<thead>
				<tr>
					<th>직원ID</th>
					<th>이름</th>
					<th>이메일</th>
					<th>직급</th>
					<th>부서</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty datas}">
					<c:forEach items="${datas}" var="data">
						<tr>
							<td>${data.empId}</td>
							<td>${data.empName}</td>
							<td>${data.email}</td>
							<td>${data.jobName}</td>
							<td>${data.deptName}</td>
							<td>
								<c:url var="empsModUrl" value="/emps/mod">
									<c:param name="id" value="${data.empId}" />
								</c:url>
								<button type="button" class="btn btn-outline-primary btn-sm" onclick="location.href='${empsModUrl}'">
									<span class="bi bi-card-text"></span>
								</button>
								<c:url var="empsDelUrl" value="/emps/del">
									<c:param name="id" value="${data.empId}" />
								</c:url>
								<button type="button" class="btn btn-outline-danger btn-sm" onclick="location.href='${empsDelUrl}'">
									<span class="bi bi-trash"></span>
								</button>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<c:choose>
			<c:when test="${not empty pageList}">
				<c:url var="pageUrl" value="/emps" />
				<%@ include file="../module/paging.jsp" %>
			</c:when>
			<c:otherwise>
				<div class="input-form wide form-left">
					<button class="btn btn-outline btn-ok" type="button" onclick="location.href='${pageContext.request.contextPath}/depts'">전체보기</button>
				</div>				
			</c:otherwise>
		</c:choose>
	</section>
</body>
</html>