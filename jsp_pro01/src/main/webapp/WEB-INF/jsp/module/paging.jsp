<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- 제어문 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- 날짜/ 숫자 등의 포멧 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <!-- 주로 문자열과 관련된 함수 -->
<%@ page import="java.util.*" %>
<%--
<%
	List<Integer> pageList = (List<Integer>) request.getAttribute("pageList");
	int currentPage = (int)request.getAttribute("page"); // page : 현재페이지 (depts?page=1 에서 1 을 의미)
	int prevPage = currentPage - 1;
	int nextPage = currentPage + 1;
%>
--%>
<%--
	<c:set var="currentPage" value="${page}" />
	<c:set var="prevPage" value="${currentPage - 1}" />
	<c:set var="nextPage" value="${currentPage + 1}" />
	<c:set var="maxPage" value="${pageList.get(pageList.size() - 1)}" /> <!-- pageList.size() : 전체크기, (pageList.size() - 1) : 마지막 페이지-->
--%>
<ul class="pagination justify-content-center">
	<c:choose>
		<c:when test="${datas.hasPrevPage()}">
			<li class="page-item">
				<a class="page-link bi bi-caret-left-fill" href="${pageUrl}?page=${datas.prevPage}"></a>
			</li>
		</c:when>
		<c:otherwise>
			<li class="page-item disabled">
				<a class="page-link bi bi-caret-left-fill" href="#"></a>
			</li>
		</c:otherwise>
	</c:choose>
		<%--
		<% if(prevPage <= 0) { %>
			<a class="page-link disabled material-symbols-outlined" href="#">keyboard_arrow_left</a>
		<% } else { %>
			<a class="page-link material-symbols-outlined" href="./depts?page=<%=prevPage %>">keyboard_arrow_left</a>
		<% } %>
		--%>
	<%-- <c:forEach begin="${currentPage}" end="${currentPage + 4}" var="item"> <!-- item 은 배열(5, 6, 7, 8, 9)  --> --%>
	<c:forEach items="${datas.getPages(datas.currentPage - 2, datas.currentPage + 2)}" var="item">
		<li class="page-item ${datas.currentPage == item ? ' active' : ''}">
			<a class="page-link" href="${pageUrl}?page=${item}">${item}</a> <!-- (item - 1) : index 번호이므로 -1 해줌 -->
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
	<c:choose>
		<c:when test="${datas.hasNextPage()}">
			<li class="page-item">
				<a class="page-link bi bi-caret-right-fill" href="${pageUrl}?page=${datas.nextPage}"></a>
			</li>
		</c:when>
		<c:otherwise>
			<li class="page-item disabled">
				<a class="page-link bi bi-caret-right-fill" href="#"></a>
			</li>
		</c:otherwise>
	</c:choose>
		<%--
		<% if(nextPage > pageList.size()) { %>
			<a class="page-link disabled material-symbols-outlined" href="#">keyboard_arrow_right</a>
		<% } else { %>
			<a class="page-link material-symbols-outlined" href="./depts?page=<%=nextPage %>">keyboard_arrow_right</a>
		<% } %>
		--%>
</ul>