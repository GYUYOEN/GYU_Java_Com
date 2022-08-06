<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- 제어문 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- 날짜/ 숫자 등의 포멧 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <!-- 주로 문자열과 관련된 함수 -->
<%
	String url = "";
	// url 주소는 filter에 있음
	if(request.getAttribute("url") != null) { // servlet 에서 setAttribute("url", ...) 의 ...을 이용 ...은 url path 정보가 들어감(ex) /depts, /detps/add...)
		url = (String) request.getAttribute("url");
	}
%>
<header>
	<nav class="top-nav center">
		<c:if test="${not empty sessionScope.loginData}">
			<ul class="nav">
				<li class="nav-item<%=url.contains("/emps") ? " active" : "" %>">
					<a class="nav-link" href="./emps">직원</a>
				</li>
				<li class="nav-item<%=url.contains("/depts") ? " active" : "" %>">
					<a class="nav-link" href="./depts">부서</a>
				</li>
				<li class="nav-item<%=url.contains("/locs") ? " active" : "" %>">
					<a class="nav-link" href="./locs">지역</a>
				</li>
			</ul>
		</c:if>
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