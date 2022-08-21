<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- 제어문 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- 날짜/ 숫자 등의 포멧 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <!-- 주로 문자열과 관련된 함수 -->
<ul class="pagination justify-content-center">
	<li class="page-item">
		<c:choose>
			<c:when test="${datas.hasPrevPage()}">
				<li class="page-item">
					<a class="page-link bi bi-caret-left-fill" href="${pageUrl}?page=${datas.prevPage}"></a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="page-item disabled">
					<a class="page-link bi bi-caret-left-fill" href="#">keyboard_arrow_left</a> <!-- disabled : 비활성화 -->
				</li>
			</c:otherwise>
		</c:choose>
	</li>
	<c:forEach items="${data.getPages(data.currentPage - 2, data.currentPage + 2)}" var="item">
		<li class="page-item ${data.currentPage == item ? ' active' : ''}">
			<a class="page-link" href="${pageUrl}?page=${item}">${item}</a>
		</li>
	</c:forEach>
	<li class="page-item">
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
	</li>
</ul>
