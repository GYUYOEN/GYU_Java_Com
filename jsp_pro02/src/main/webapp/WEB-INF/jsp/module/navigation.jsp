<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<header>
	<nav class="top-nav">
		<ul class="nav">
			<li class="nav-item dropdown ${fn:contains(url, '/jsp_') ? 'active' : '' }">
				<a class="nav-link" href="/">JSP/Servlet</a>
				<ul class="nav dropdown-nav">
					<li class="nav-item">
						<c:url var="m1" value="/jsp_script" />
						<a class="nav-link" href="${m1}">스크립트 태그</a>
					</li>
					<li class="nav-item">
						<c:url var="m2" value="/jsp_request" />
						<a class="nav-link" href="${m2}">request 객체</a>
					</li>
					<li class="nav-item">
						<c:url var="m3" value="/jsp_response" />
						<a class="nav-link" href="${m3}">response 객체</a>
					</li>
					<li class="nav-item">
						<c:url var="m4" value="/jsp_model2" />
						<a class="nav-link" href="${m4}">Model2</a>
					</li>
				</ul>
			</li>
			<c:if test="${not empty sessionScope.loginData}">
				<c:if test="${sessionScope.permData.employees.pRead}">
					<li class="nav-item ${fn:contains(url, '/board') ? 'active' : '' }">
						<c:url var="m5" value="/board" />
						<a class="nav-link" href="${m5}">게시판</a>
					</li>
				</c:if>
				<c:if test="${sessionScope.permData.employees.pRead}">
					<li class="nav-item ${fn:contains(url, '/emps') ? 'active' : '' }">
						<c:url var="m5" value="/emps" />
						<a class="nav-link" href="${m5}">직원</a>
					</li>
				</c:if>
				<c:if test="${sessionScope.permData.departments.pRead}">
					<li class="nav-item ${fn:contains(url, 'depts') ? 'active' : '' }">
						<c:url var="m6" value="/depts" />
						<a class="nav-link" href="${m6}">부서</a>
					</li>
				</c:if>
				<c:if test="${sessionScope.permData.locations.pRead}">
					<li class="nav-item ${fn:contains(url, 'locs') ? 'active' : '' }">
						<c:url var="m7" value="/locs" />
						<a class="nav-link" href="${m7}">지역</a>
					</li>
				</c:if>
			</c:if>
		</ul>
		<ul class="nav right">
			<li class="nav-item">
				<select class="select-form" style="padding: 1rem 1.2rem;" onchange="themeChange(this.value)">
					<option value="default">기본테마</option>
					<option value="bs5">bs5테마</option>
				</select>
			</li>
			<c:if test="${not empty sessionScope.loginData}">
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
			</c:if>
		</ul>
	</nav>
</header>