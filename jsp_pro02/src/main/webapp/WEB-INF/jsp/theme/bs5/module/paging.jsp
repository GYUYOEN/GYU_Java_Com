<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<nav>
	<c:set var="currentPage" value="${page}" />
	<c:set var="prevPage" value="${currentPage - 1}" />
	<c:set var="nextPage" value="${currentPage + 1}" />
	<c:set var="maxPage" value="${pageList.get(pageList.size() - 1)}" />
	<ul class="pagination justify-content-center">
		<li class="page-item ${prevPage <= 0 ? 'disabled' : ''}">
			<c:choose>
				<c:when test="${prevPage <= 0}">
					<a class="page-link bi bi-chevron-left" href="#"></a>
				</c:when>
				<c:otherwise>
					<a class="page-link bi bi-chevron-left" href="${pageUrl}?page=${prevPage}"></a>
				</c:otherwise>
			</c:choose>
		</li>
		<c:forEach begin="${currentPage - 2 <= 0 ? 1 : currentPage - 2}" end="${currentPage + 2 > maxPage ? maxPage : currentPage + 2}" var="item">
			<li class="page-item ${currentPage == pageList.get(item-1) ? ' active' : ''}">
				<a class="page-link"
					href="${pageUrl}?page=${pageList.get(item-1)}">${pageList.get(item-1)}</a>
			</li>
		</c:forEach>
		<li class="page-item ${nextPage > pageList.size() ? 'disabled' : ''}">
			<c:choose>
				<c:when test="${nextPage > pageList.size()}">
					<a class="page-link bi bi-chevron-right" href="#"></a>
				</c:when>
				<c:otherwise>
					<a class="page-link bi bi-chevron-right" href="${pageUrl}?page=${nextPage}"></a>
				</c:otherwise>
			</c:choose>
		</li>
	</ul>
</nav>