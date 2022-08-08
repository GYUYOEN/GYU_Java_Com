<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>지역 조회 결과</title>
	<%@ include file="../module/head.jsp" %>
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<section class="container">
		<div>
			<form action="./locs" method="get">
				<div class="input-form form-left">
					<button class="btn btn-outline" type="button" onclick="location.href='./locs/add'">추가</button>
				</div>
				<div class="input-form form-right">
					<input class="input-text" type="text" name="search" data-required="지역코드를 입력하세요.">
					<button class="btn btn-outline" type="submit">조회</button>
				</div>
			</form>
		</div>
		<table class="table wide vertical-hidden hover">
			<colgroup>
				<col class="col-120">
				<col class="col-auto">
				<col class="col-120">
				<col class="col-120">
				<col class="col-120">
				<col class="col-120">
				<col class="col-120">
			</colgroup>
			<thead>
				<tr>
					<th>지역코드</th>
					<th>주소</th>
					<th>우편번호</th>
					<th>도시명</th>
					<th>주</th>
					<th>국가코드</th>
					<th class="border-hidden-right"></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty locsDatas}">
					<c:forEach items="${locsDatas}" var="data">
						<tr>
							<td>${data.locId}</td>
							<td>${data.stAddr}</td>
							<td>${data.postal}</td>
							<td>${data.city}</td>
							<td>${data.state}</td>
							<td>${data.ctyId}</td>
							<td class="border-hidden-right">
								<c:url var="modUrl" value="./locs/mod">
									<c:param name="id" value="${data.locId}" />
								</c:url>
								<button type="button" class="btn btn-icon" onclick="location.href='${modUrl}'">
									<span class="material-symbols-outlined">edit</span>
								</button>
								<c:url var="delUrl" value="./locs/del">
									<c:param name="id" value="${data.locId}" />
								</c:url>
								<button type="button" class="btn btn-icon" onclick="location.href='${delUrl}'">
									<span class="material-symbols-outlined">delete</span>
								</button>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</section>
</body>
</html>