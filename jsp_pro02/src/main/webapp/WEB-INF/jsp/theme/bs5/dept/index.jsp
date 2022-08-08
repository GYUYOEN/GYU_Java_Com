<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>부서</title>
	<%@ include file="../module/head.jsp" %>
</head>
<body>
	<header class="mb-3">
		<%@ include file="../module/navigation.jsp" %>
	</header>
	<section class="container-fluid">
		<div class="row mb-1">
			<div class="col-auto me-auto">
				<c:url var="deptAddUrl" value="/depts/add" />
				<button class="btn btn-sm btn-outline-secondary" type="button" onclick="location.href='${deptAddUrl}'">추가</button>
			</div>
			<c:url var="deptUrl" value="/depts" />
			<form class="col-auto" action="${deptUrl}" method="get">
				<div class="row g-1">
					<div class="col-auto">
						<input class="form-control form-control-sm" type="text" name="search" data-required="부서코드를 입력하세요.">
					</div>
					<div class="col-auto">
						<button class="btn btn-sm btn-outline-secondary" type="submit">조회</button>
					</div>
					<div class="col-auto">
						<select class="form-select form-select-sm" onchange="location.href='${deptUrl}?pgc=' + this.value">
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
				<col class="col col-auto">
				<col class="col col-1">
			</colgroup>
			<thead>
				<tr>
					<th>부서ID</th>
					<th>부서명</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty deptDatas}">
					<c:forEach items="${deptDatas}" var="data">
						<tr>
							<td>${data.deptId}</td>
							<td>${data.deptName}</td>
							<td>
								<c:url var="deptModUrl" value="/depts/mod">
									<c:param name="id" value="${data.deptId}" />
								</c:url>
								<button type="button" class="btn btn-outline-primary btn-sm" onclick="location.href='${deptModUrl}'">
									<span class="bi bi-card-text"></span>
								</button>
								<c:url var="deptDelUrl" value="/depts/del">
									<c:param name="id" value="${data.deptId}" />
								</c:url>
								<button type="button" class="btn btn-outline-danger btn-sm" onclick="location.href='${deptDelUrl}'">
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
				<c:url var="pageUrl" value="/depts" />
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