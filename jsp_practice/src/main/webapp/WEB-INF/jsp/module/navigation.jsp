<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- 제어문 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- 날짜/ 숫자 등의 포멧 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <!-- 주로 문자열과 관련된 함수 -->
<header>
	<nav class="top-nav center">
		<ul class="nav">
			<c:if test="${not empty sessionScope.loginData}">
				<c:if test="${sessionScope.permData.employees.pRead}">
					<li class="nav-item ${fn:contains(url, '/board') ? 'active' : ''}">
						<c:url var="m4" value="/board" />
						<a class="nav-link" href="${m4}">게시판</a>
					</li>
				</c:if>
				<c:if test="${sessionScope.permData.employees.pRead}">
					<li class="nav-item ${fn:contains(url, '/emps') ? 'active' : ''}">
						<c:url var="m1" value="/emps" />
						<a class="nav-link" href="${m1}">직원</a>
					</li>
				</c:if>
				<c:if test="${sessionScope.permData.departments.pRead}">
					<li class="nav-item ${fn:contains(url, '/depts') ? 'active' : ''}">
						<c:url var="m2" value="/depts" />
						<a class="nav-link" href="${m2}">부서</a>
					</li>
				</c:if>
				<c:if test="${sessionScope.permData.locations.pRead}">
					<li class="nav-item ${fn:contains(url, '/locs') ? 'active' : ''}">
						<c:url var="m3" value="/locs" />
						<a class="nav-link" href="${m3}">지역</a>
					</li>
				</c:if>
			</c:if>
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