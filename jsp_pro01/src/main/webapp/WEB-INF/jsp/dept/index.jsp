<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- 제어문 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- 날짜/ 숫자 등의 포멧 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <!-- 주로 문자열과 관련된 함수 -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 조회 결과</title>
	<%@ include file="../module/head.jsp" %>
</head>
<!-- 
<script type="text/javascript"> // 빈 문자열을 조회하면 반응하지 않게 하기 위한 로직
	// 보통 view와 controller 양쪽에서 전부 로직을 짜서 체크하는데 안전
	window.onload = function() {
		// form 은 getElement... 사용할 필요 없음
		var form = document.forms[0];
		form.addEventListener("submit", formCheck);
	}
	
	function formCheck(e) {
		if(this.search.value === "") {
			e.preventDefault();
		} else {
			this.submit(); // form을 전송
		}
	}
</script>
-->
<body>
	<%@ include file="../module/navigation.jsp" %>
	<section class="container">
		<div>
			<form action="./depts" method="get"> <!-- get 으로 보내서 doGet 안에서 추출 -->
				<div class="input-form form-left">
					<button class="btn btn-outline" type="button" onclick="location.href='./depts/add'">추가</button> <!-- './depts/add' : 추가기능 url 주소 -->
				</div>
				<div class="input-form form-right">
					<input class="input-text" type="text" name="search" data-required="부서코드를 입력하세요."> <!-- search 라는 parameter name으로 사용자가 입력한 값을 ./depts(controller) 에다가 요청 -->
					<button class="btn btn-outline" type="submit">조회</button>
					<!-- div class="required-box show">부서코드를 입력하세요.</div -->
					<%-- 
					<select class="select-form" onchange="location.href='./depts?pgc=' + this.value"> <!-- onchange 값이 바뀌면 감지(servlet에 요청함 - servlet에서 만듦), this는 select 요소를 지칭 -->
						<option value="5" ${pageCount == 5 ? 'selected' : ''}>5 개</option>
						<option value="10" ${pageCount == 10 ? 'selected' : ''}>10 개</option>
						<option value="15" ${pageCount == 15 ? 'selected' : ''}>15 개</option>
						<option value="20" ${pageCount == 20 ? 'selected' : ''}>20 개</option>
					</select>
					--%>
					<!-- 세션의 경우 아래와 같이 사용가능(setAttribute()를 session이랑 request 두곳에 구지 저장하지 않고 session에만 작성해도 됨 -->
					<select class="select-form" onchange="location.href='./depts?pgc=' + this.value"> <!-- onchange 값이 바뀌면 감지(servlet에 요청함 - servlet에서 만듦), this는 select 요소를 지칭 -->
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
				<col class="col-60">
				<col class="col-auto">
				<col class="col-60">
				<col class="col-60">
				<col class="col-120">
			</colgroup>
			<thead>
				<tr>
					<th>DeptId</th>
					<th>DeptName</th>
					<th>MngId</th>
					<th>LocId</th>
					<th class="border-hidden-right"></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty deptDatas}"> <!-- not empty : null이 아님, empty :  null임 -->
					<c:forEach items="${deptDatas}" var="data"> <!-- foreach =  for(DeptDTO data: datas), var = data-->
						<tr>
							<td>${data.deptId}</td>
							<td>${data.deptName}</td>
							<td>${data.mngId}</td>
							<td><a href="./locs?search=${data.locId}">${data.locId}</a></td> <!-- 부서에서 locId 를 클릭하면 지역이 나오도록 함  -->
							<td class="border-hidden-right">
								<c:url var="modUrl" value="./depts/mod">
									<c:param name="id" value="${data.deptId}"/>
								</c:url>
								<%--<button type="button" class="btn btn-icon" onclick="location.href='./depts/mod?id=${data.deptId}'">--%>
								<button type="button" class="btn btn-icon" onclick="location.href='${modUrl}'"> <!-- location.href : 해당 url 주소로 이동 -->
									<span class="material-symbols-outlined">edit</span>
								</button>
								<c:url var="delUrl" value="./depts/del">
									<c:param name="id" value="${data.deptId}"/>
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
				// controller에서 설정하여 전달된 객체 불러오기 -> request.getAttribute("deptDatas")
				if(request.getAttribute("deptDatas") != null) {
					List<DeptDTO> datas = (List<DeptDTO>) request.getAttribute("deptDatas"); // 다운캐스팅
					for(DeptDTO data: datas) {
		%>
						<tr>
							<td><%=data.getDeptId() %></td>
							<td><%=data.getDeptName() %></td>
							<td><%=data.getMngId() %></td>
							<td><a href="./locs?search=<%=data.getLocId() %>"><%=data.getLocId() %></a></td> <!-- 부서에서 locId 를 클릭하면 지역이 나오도록 함  -->
							<td class="border-hidden-right">
								<button type="button" class="btn btn-icon" onclick="location.href='./depts/mod?id=<%=data.getDeptId() %>'"> <!-- location.href : 해당 url 주소로 이동 -->
									<span class="material-symbols-outlined">edit</span>
								</button>
								<button type="button" class="btn btn-icon" onclick="location.href='./depts/del?id=<%=data.getDeptId() %>'">
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
		<c:choose>
			<c:when test="${not empty pageList}">
				<c:url var="pageUrl" value="./depts"/>
				<%@ include file ="../module/paging.jsp" %>
			</c:when>
			<c:otherwise>
				<div class="input-form wide form-left">
					<button class="btn btn-outline btn-ok" type="button" onclick="location.href='${pageContext.request.contextPath}/depts'">전체보기</button>
				</div>
			</c:otherwise>
		</c:choose>
		<%--
		<% if(request.getAttribute("pageList") != null) { %>
			<%@ include file="../module/paging.jsp" %>
		<% } else { %>
			<div class="input-form wide form-left">
				<button class="btn btn-outline btn-ok" type="button" onclick="location.href='<%=request.getContextPath() %>/depts'">전체보기</button>
			</div>
		<% } %>
		--%>
	</section>
</body>
</html>