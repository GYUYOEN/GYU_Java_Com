<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- 제어문 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- 날짜/ 숫자 등의 포멧 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <!-- 주로 문자열과 관련된 함수 -->
<%--
<%
	String url = ""; 
	// 변수를 사용하고 싶으면 request.getAttribute를 이용하기
	if(request.getAttribute("url") != null) { // servlet에서 url 변수를 가져옴(request.setAttribute("url", ...)에서 ...을 가져옴, ... 은 url 주소임)
		url = (String)request.getAttribute("url");
	}
%>
--%>
<header>
	<nav class="top-nav center">
		<ul class="nav">
			<li class="nav-item dropdown ${fn:contains(url, '/jsp_') ? ' active' : ''}"> <!-- url.contains("/jsp_") : /jsp_ 경로가 포함되어 있는지 -->
				<a class="nav-link" href="/">JSP/Servlet</a>
				<ul class="nav dropdown-nav">
					<li class="nav-item">
						<c:url var="m1" value="/jsp_script"/>
						<a class="nav-link" href="${m1}">스크립트 태그</a>
					</li>
					<li class="nav-item">
						<c:url var="m2" value="/jsp_request"/>
						<a class="nav-link" href="${m2}">request 객체</a>
					</li>
					<li class="nav-item">
						<c:url var="m3" value="/jsp_response"/>
						<a class="nav-link" href="${m3}">response 객체</a>
					</li>
					<li class="nav-item">
						<c:url var="m4" value="/jsp_model2"/>
						<a class="nav-link" href="${m4}">Model2</a>
					</li>
				</ul>
			</li>
			<c:if test="${not empty sessionScope.loginData}">
				<c:if test="${sessionScope.permData.employees.pRead}">
					<li class="nav-item ${fn:contains(url, '/emps') ? ' active' : ''}">
						<c:url var="m5" value="/emps"/>
						<a class="nav-link" href="${m5}">직원</a>
					</li>
				</c:if>
				<c:if test="${sessionScope.permData.departments.pRead}">
					<li class="nav-item ${fn:contains(url, '/depts') ? ' active' : ''}">
						<c:url var="m6" value="/depts"/>
						<a class="nav-link" href="${m6}">부서</a>
					</li>	
				</c:if>
				<c:if test="${sessionScope.permData.locations.pRead}">
					<li class="nav-item ${fn:contains(url, '/locs') ? ' active' : ''}">
						<c:url var="m7" value="/locs"/>
						<a class="nav-link" href="${m7}">지역</a>
					</li>
				</c:if>
			</c:if>
			<%--
			<li class="nav-item<%=url.contains("/locs") ? " active" : "" %>">
				<a class="nav-link" href="./locs"><%=request.getParameter("test") %></a>
			</li>
			--%>
		</ul>
		<c:if test="${not empty sessionScope.loginData}">
			<ul class="nav right">
				<li class="nav-item dropdown">
					<a class="nav-link" href="#">${sessionScope.loginData.empName}</a>
					<ul class="nav dropdown-nav">
						<c:url var="myInfoUrl" value="/myinfo" />
						<li class="nav-item">
							<a class="nav-link" href="${myInfoUrl}">개인정보</a>
						</li>
						<c:url var="logoutUrl" value="/logout" />
						<li class="nav-item">
							<a class="nav-link" href="${logoutUrl}">로그아웃</a>
						</li>
					</ul>
				</li>
			</ul>
		</c:if>
	</nav>
</header>