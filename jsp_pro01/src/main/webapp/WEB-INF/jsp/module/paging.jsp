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
		int currentPage = (int)request.getAttribute("page"); // page : 현재페이지 (depts?page=1 에서 1 을 의미)
		int prevPage = currentPage - 1;
		int nextPage = currentPage + 1;
	%>
	--%>
	<c:set var="currentPage" value="${page}" />
	<c:set var="prevPage" value="${currentPage - 1}" />
	<c:set var="nextPage" value="${currentPage + 1}" />
	<c:set var="maxPage" value="${pageList.get(pageList.size() - 1)}" /> <!-- pageList.size() : 전체크기, (pageList.size() - 1) : 마지막 페이지-->
	
	<ul class="page center">
		<li class="page-item">
			<c:choose>
				<c:when test="${prevPage <= 0}">
					<a class="page-link disabled material-symbols-outlined" href="#">keyboard_arrow_left</a>
				</c:when>
				<c:otherwise>
					<a class="page-link material-symbols-outlined" href="${pageUrl}?page=${prevPage}">keyboard_arrow_left</a>
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
		<%-- <c:forEach begin="${currentPage}" end="${currentPage + 4}" var="item"> <!-- item 은 배열(5, 6, 7, 8, 9)  --> --%>
		<c:forEach begin="${currentPage - 2 <= 0 ? 1 : currentPage - 2}" end="${currentPage + 2 > maxPage ? maxPage : currentPage + 2}" var="item">
			<li class="page-item">
				<a class="page-link ${currentPage == pageList.get(item - 1) ? ' active' : ''}"
				 href="${pageUrl}?page=${pageList.get(item - 1)}">${pageList.get(item - 1)}</a> <!-- (item - 1) : index 번호이므로 -1 해줌 -->
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
					<a class="page-link material-symbols-outlined" href="${pageUrl}?page=${nextPage}">keyboard_arrow_right</a>
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