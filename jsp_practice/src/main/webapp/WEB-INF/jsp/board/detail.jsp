<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글 상세</title>
	<link rel="stylesheet" type="text/css" href="/jpr/static/bs5/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
	<script type="text/javascript" src="/jpr/static/bs5/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/jpr/static/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<header></header>
	<section class="container">
		<div class="mt-3">
			<div class="mb-1 border-bottom border-2 border-secondary">
				<h1>${data.title}</h1>
			</div>
			<div class="mb-3">
				<label class="pe-3 text-secondary text-opacity-75">${empData.empName}</label>
				<fmt:formatDate value="${data.createDate}" var="createDate" dateStyle="long"/>
				<label class="pe-3 text-secondary text-opacity-75">${createDate}</label>
				<label class="pe-3 text-secondary text-opacity-75">조회수: ${data.viewCnt}</label>
			</div>
			<div class="mb-1 border-bottom border-2 border-secondary">
				<p>${data.content}</p>
			</div>
			<div class="mb-1">
				<div onclick="incLike(id_like, ${data.id})">
					<i class="bi bi-hand-thumbs-up text-secondary text-opacity-50"></i>
					<label id="id_like" class="text-secondary text-opacity-75">${data.like}</label>
				</div>
			</div>
			<div class="mb-1 text-end">
				<c:url var="boardUrl" value="/board" />
				<button class="btn btn-primary" type="button" onclick="location.href='${boardUrl}'">목록</button>
				<c:if test="${data.empId eq sessionScope.loginData.empId}">
					<button class="btn btn-primary" type="button">수정</button>
					<button class="btn btn-primary" type="button">삭제</button>
				</c:if>
			</div>
		</div>
	</section>
	<footer></footer>
	<script type="text/javascript">
		function incLike(element, id) {
			$.ajax({
				url: "/jpr/board/detail",
				type: "post",
				data: {
					id: id
				},
				success: function(data) {
					if(data.code === "success") {
						element.innerText = data.likeCnt;		
					}
				}
			});
		}
	</script>
</body>
</html>