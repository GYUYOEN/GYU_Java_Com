<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- 제어문 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- 날짜/ 숫자 등의 포멧 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <!-- 주로 문자열과 관련된 함수 -->
<%--<%@ page import="java.util.*, locs.model.LocsDTO" %>--%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>지역 조회 결과</title>
	<%@ include file="../module/head.jsp" %>
	<%--
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/default.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/navigation.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/required.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/form.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/table.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/required.js"></script>
	--%>
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<h1>지역 조회 결과</h1>
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
							<td>${data.streetAdd}</td>
							<td>${data.postCode}</td>
							<td>${data.city}</td>
							<td>${data.stateProv}</td>
							<td>${data.ctyId}</td>
							<td class="border-hidden-right">
								<c:url var="modUrl" value="./locs/mod">
									<c:param name="id" value="${data.locId}"/>
								</c:url>
								<button type="button" class="btn btn-icon" onclick="location.href='${modUrl}'">
									<span class="material-symbols-outlined">edit</span>
								</button>
								<c:url var="delUrl" value="./locs/del">
									<c:param name="id" value="${data.locId}"/>
								</c:url>
								<button type="button" class="btn btn-icon" onclick="location.href='${delUrl}'">
									<span class="material-symbols-outlined">delete</span>
								</button>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<%--
				<%
					if(request.getAttribute("locsDatas") != null) {
						List<LocsDTO> datas = (List<LocsDTO>) request.getAttribute("locsDatas");
						for(LocsDTO data: datas) {
				%>
							<tr>
								<td><%=data.getLocId() %></td>
								<td><%=data.getStreetAdd() %></td>
								<td><%=data.getPostCode() %></td>
								<td><%=data.getCity() %></td>
								<td><%=data.getStateProv() %></td>
								<td><%=data.getCtyId() %></td>
								<td class="border-hidden-right">
									<button type="button" class="btn btn-icon" onclick="location.href='./locs/mod?id=<%=data.getLocId() %>'">
										<span class="material-symbols-outlined">edit</span>
									</button>
									<button type="button" class="btn btn-icon" onclick="location.href='./locs/del?id=<%=data.getLocId() %>'">
										<span class="material-symbols-outlined">delete</span>
									</button>
								</td>
							</tr>
				<%
						}
					}
				%>
				--%>
			</tbody>
		</table>
	</section>
</body>
</html>