<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- 제어문 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- 날짜/ 숫자 등의 포멧 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <!-- 주로 문자열과 관련된 함수 -->
<%@ page import="java.util.*" %>
<div class="paging">
	<%--
	<%
		List<Integer> pageList = (List<Integer>) request.getAttribute("pageList");
		int currentPage = (int)request.getAttribute("page");
		int prevPage = currentPage - 1;
		int nextPage = currentPage + 1;
	%>
	--%>
	<c:set var="currentPage" value="${page}" />
	<c:set var="prevPage" value="${currentPage - 1}" />
	<c:set var="nextPage" value="${currentPage + 1}" />
	
	<ul class="page center">
		<li class="page-item">
			<c:choose>
				<c:when test="${prevPage <= 0}">
					<a class="page-link disabled material-symbols-outlined" href="#">keyboard_arrow_left</a>
				</c:when>
				<c:otherwise>
					<a class="page-link material-symbols-outlined" href="./depts?page=${prevPage}">keyboard_arrow_left</a>
				</c:otherwise>
			</c:choose>
			<%--
			<% if(prevPage <= 0) { %>
				<a class="page-link disabled material-symbols-outlined" href="#">keyboard_arrow_left</a>
			<% } else { %>
				<a class="page-link material-symbols-outlined" href="./depts?page=<%=prevPage %>">keyboard_arrow_left</a>
			<% } %>
			--%>
		</li>
		<c:forEach items="${pageList}" var="item">
			<li class="page-item">
				<a class="page-link ${currentPage == item ? ' active' : ''}" href="./depts?page=${item}">${item}</a>
			</li>
		</c:forEach>
		<%--
		<%
			for(Integer n: pageList) {
		%>
				<li class="page-item"><a class="page-link<%=currentPage == n ? " active" : "" %>" href="./depts?page=<%=n %>"><%=n %></a></li>
		<%
			}
		%>
		--%>
		<li class="page-item">
			<c:choose>
				<c:when test="${nextPage > pageList.size()}">
					<a class="page-link disabled material-symbols-outlined" href="#">keyboard_arrow_right</a>
				</c:when>
				<c:otherwise>
					<a class="page-link material-symbols-outlined" href="./depts?page=${nextPage}">keyboard_arrow_right</a>
				</c:otherwise>
			</c:choose>
			<%--
			<% if(nextPage > pageList.size()) { %>
				<a class="page-link disabled material-symbols-outlined" href="#">keyboard_arrow_right</a>
			<% } else { %>
				<a class="page-link material-symbols-outlined" href="./depts?page=<%=nextPage %>">keyboard_arrow_right</a>
			<% } %>
			--%>
		</li>
	</ul>
</div>