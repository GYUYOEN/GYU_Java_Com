<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- 제어문 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- 날짜/ 숫자 등의 포멧 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <!-- 주로 문자열과 관련된 함수 -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>직원</title>
	<%@ include file="../module/head.jsp" %>
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<section class="container">
		<div>
			<form action="./emps" method="get"> <!-- get 으로 보내서 doGet 안에서 추출 -->
				<div class="input-form form-left">
					<button class="btn btn-outline" type="button" onclick="location.href='./emps/add'">추가</button> <!-- './depts/add' : 추가기능 url 주소 -->
				</div>
				<div class="input-form form-right">
					<input class="input-text" type="text" name="search" data-required="직원 이름을 입력하세요."> <!-- search 라는 parameter name으로 사용자가 입력한 값을 ./depts(controller) 에다가 요청 -->
					<button class="btn btn-outline" type="submit">조회</button>
					<select class="select-form" onchange="location.href='./emps?pageCount=' + this.value"> <!-- onchange 값이 바뀌면 감지(servlet에 요청함 - servlet에서 만듦), this는 select 요소를 지칭 -->
						<option value="5" ${sessionScope.pageCount == 5 ? 'selected' : ''}>5 개</option>
						<option value="10" ${sessionScope.pageCount == 10 ? 'selected' : ''}>10 개</option>
						<option value="15" ${sessionScope.pageCount == 15 ? 'selected' : ''}>15 개</option>
						<option value="20" ${sessionScope.pageCount == 20 ? 'selected' : ''}>20 개</option>
					</select>
				</div>
			</form>
		</div>
		<table class="table wide vertical-hidden hover">
			<colgroup>
				<col class="col-120">
				<col class="col-240">
				<col class="col-240">
				<col class="col-240">
				<col class="col-240">
			</colgroup>
			<thead>
				<tr>
					<th>직원ID</th>
					<th>이름</th>
					<th>이메일</th>
					<th>직급</th>
					<th>부서</th>
					<%--
					<th class="border-hidden-right"></th>
					--%>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty datas}"> <!-- not empty : null이 아님, empty :  null임 -->
					<c:forEach items="${datas}" var="data"> <!-- foreach =  for(DeptDTO data: datas), var = data-->
						<c:url var="detailUrl" value="/emps/detail">
							<c:param name="id" value="${data.empId}"/>
						</c:url>
					<%--<tr onclick="location.href='${detailUrl}?id=${data.empId}'">--%>
						<tr onclick="location.href='${detailUrl}'">
							<td>${data.empId}</td>
							<td>${data.empName}</td>
							<td>${data.email}</td>
							<td>${data.jobName}</td>
							<td>${data.deptName}</td>
							<%--
							<td class="border-hidden-right">
								<c:url var="modUrl" value="./emps/mod">
									<c:param name="id" value="${data.empId}"/>
								</c:url>
								<button type="button" class="btn btn-icon" onclick="location.href='${modUrl}'"> <!-- location.href : 해당 url 주소로 이동 -->
									<span class="material-symbols-outlined">edit</span>
								</button>
								<c:url var="delUrl" value="./emps/del">
									<c:param name="id" value="${data.empId}"/>
								</c:url>
								<button type="button" class="btn btn-icon" onclick="location.href='${delUrl}'">
									<span class="material-symbols-outlined">delete</span>
								</button>
							</td>
							--%>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<c:choose>
			<c:when test="${not empty pageList}">
				<c:url var="pageUrl" value="./emps"/>
				<%@ include file ="../module/paging.jsp" %>
			</c:when>
			<c:otherwise>
				<div class="input-form wide form-left">
					<button class="btn btn-outline btn-ok" type="button" onclick="location.href='${pageContext.request.contextPath}/emps'">전체보기</button>
				</div>
			</c:otherwise>
		</c:choose>
	</section>
</body>
</html>