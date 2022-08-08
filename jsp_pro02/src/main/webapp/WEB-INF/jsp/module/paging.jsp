<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
	<c:forEach items="${datas.getPages(datas.currentPage - 2, datas.currentPage + 2)}" var="item">
		<li class="page-item ${datas.currentPage == item ? ' active' : ''}">
			<a class="page-link" href="${pageUrl}?page=${item}">${item}</a>
		</li>
	</c:forEach>
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
</ul>