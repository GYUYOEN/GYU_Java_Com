<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${data.title}</title>
	<link rel="stylesheet" type="text/css" href="/static/bs5/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
	<script type="text/javascript" src="/static/bs5/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/static/js/jquery-3.6.0.min.js"></script>
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
				<fmt:formatDate value="${data.createDate}" var="createDate" dateStyle="long" />
				<label class="pe-3 text-secondary text-opacity-75">${createDate}</label>
				<label class="pe-3 text-secondary text-opacity-75">조회수: ${data.viewCnt}</label>
			</div>
			<div class="mb-1 border-bottom border-2 border-secondary">
				<p>${data.content}</p>
			</div>
			<div class="mb-1">
				<div onclick="incLike(id_like, ${data.id});">
					<i class="bi bi-hand-thumbs-up text-secondary text-opacity-50"></i>
					<label id="id_like" class="text-secondary text-opacity-75">${data.like}</label>
				</div>
			</div>
			<div class="mb-3 text-end">
				<c:url var="boardUrl" value="/board" />
				<button class="btn btn-primary" type="button" onclick="location.href='${boardUrl}'">목록</button>
				<c:if test="${data.empId eq sessionScope.loginData.empId}">
					<button class="btn btn-success" type="button" onclick="location.href='${boardUrl}/modify?id=${data.id}'">수정</button>
					<button class="btn btn-danger" type="button" data-bs-toggle="modal" data-bs-target="#deleteModal">삭제</button>
				</c:if>
			</div>
		</div>
		
		<div class="mb-3">
			<c:url var="pageUrl" value="/board/detail">
				<c:param name="id">${data.id}</c:param>
			</c:url>
			<ul class="pagination justify-content-center">
				<c:choose>
					<c:when test="${commentPage.hasPrevPage()}">
						<li class="page-item">
							<a class="page-link bi bi-caret-left-fill" href="${pageUrl}&page=${commentPage.prevPage}"></a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="page-item disabled">
							<a class="page-link bi bi-caret-left-fill" href="#"></a>
						</li>
					</c:otherwise>
				</c:choose>
				<c:forEach items="${commentPage.getPages(commentPage.currentPage - 2, commentPage.currentPage + 2)}" var="item">
					<li class="page-item ${commentPage.currentPage == item ? ' active' : ''}">
						<a class="page-link" href="${pageUrl}&page=${item}">${item}</a>
					</li>
				</c:forEach>
				<c:choose>
					<c:when test="${commentPage.hasNextPage()}">
						<li class="page-item">
							<a class="page-link bi bi-caret-right-fill" href="${pageUrl}&page=${commentPage.nextPage}"></a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="page-item disabled">
							<a class="page-link bi bi-caret-right-fill" href="#"></a>
						</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		
		<div class="mb-3">
			<c:forEach items="${commentPage.pageDatas}" var="comment">
				<div class="mb-1">
					<div class="card border-light">
						<div class="card-header">
							<div class="d-flex justify-content-between">
								<span><small>${comment.empName}</small></span>
								<span><small>${comment.createDate}</small></span>
							</div>
						</div>
						<div class="card-body">
							<input type="hidden" name="cid" value="${comment.id}">
							<p class="card-text">${comment.content}</p>
							<c:if test="${sessionScope.loginData.empId eq comment.empId}">
								<div class="text-end">
									<button class="btn btn-sm btn-outline-dark" type="button" onclick="changeModify(this);">수정</button>
									<button class="btn btn-sm btn-outline-dark" type="button" onclick="commentDelete(this);">삭제</button>
								</div>
							</c:if>
						</div>
					</div>
				</div>
			</c:forEach>
			<div class="mb-1">
				<c:url var="commentUrl" value="/comment" />
				<form action="${commentUrl}/add" method="post">
					<input type="hidden" name="bid" value="${data.id}">
					<div class="input-group">
						<textarea class="form-control" name="content" rows="3" placeholder="댓글 작성"></textarea>
						<button class="btn btn-outline-dark" type="button" onclick="formCheck(this.form)">작성</button>
					</div>
				</form>
			</div>
		</div>
		
		<div class="modal fade" tabindex="-1" id="deleteModal">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">글 삭제</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<p>해당 게시글을 삭제하시겠습니까?</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
						<button type="button" class="btn btn-danger" data-bs-dismiss="modal" onclick="boardDelete(${data.id});">삭제</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" tabindex="-1" id="resultModal">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">결과 확인</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<p>삭제되었습니다.</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-bs-dismiss="modal" onclick="location.href='${boardUrl}'">확인</button>
					</div>
				</div>
			</div>
		</div>
	</section>
	<footer></footer>
	<script type="text/javascript">
		function formCheck(form) {
			if(form.content.value.trim() === "") {
				alert("댓글을 입력하세요.");
			} else {
				form.submit();
			}
		}
		function changeModify(element) {
			element.innerText = "확인";
			element.nextElementSibling.remove();
			
			var content = element.parentElement.previousElementSibling.innerText;
			var textarea = document.createElement("textarea");
			textarea.value = content;
			textarea.setAttribute("class", "form-control");
			element.parentElement.previousElementSibling.innerText = "";
			element.parentElement.previousElementSibling.append(textarea);
			
			element.addEventListener("click", commentUpdate);
		}
		function changeText(element, value) {
			element.innerText = "수정";
			
			var btnDelete = document.createElement("button");
			btnDelete.innerText = "삭제";
			btnDelete.setAttribute("type", "button");
			btnDelete.setAttribute("class", "btn btn-sm btn-outline-dark");
			btnDelete.setAttribute("onclick", "commentDelete(this);");
			
			element.parentElement.append(btnDelete);
			
			element.parentElement.previousElementSibling.children[0].remove();
			element.parentElement.previousElementSibling.innerText = value;
		}
		function commentUpdate(e) {
			var cid = e.target.parentElement.parentElement.firstElementChild.value;
			var value = e.target.parentElement.previousElementSibling.children[0].value;
			
			$.ajax({
				url: "/comment/modify",
				type: "post",
				data: {
					id: cid,
					content: value
				},
				success: function(data) {
					changeText(e.target, data.value);
				},
				complete: function() {
					e.target.removeEventListener("click", commentUpdate);
				}
			});
		}
		function commentDelete(element) {
			var cid = element.parentElement.parentElement.firstElementChild.value;
			var card = element.parentElement.parentElement.parentElement.parentElement;
			
			$.ajax({
				url: "/comment/delete",
				type: "post",
				data: {
					id: cid
				},
				success: function(data) {
					// 서버에 데이터 전송 후 삭제 성공 하면 화면 상에서도 삭제.
					if(data.code === "success") {
						card.remove();
					}
				}
			})
		}
		function incLike(element, id) {
			$.ajax({
				url: "/board/detail",
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
		function boardDelete(boardId) {
			$.ajax({
				type: "post",
				url: "/board/delete",
				data: {
					id: boardId
				},
				dataType: "json",
				success: function(data) {
					var myModal = new bootstrap.Modal(document.getElementById("resultModal"), {
						keyboard: false
					});
					
					var title = myModal._element.querySelector(".modal-title");
					var body = myModal._element.querySelector(".modal-body");
					title.innerText = data.title;
					body.innerHTML = "<p>" + data.message + "</p>"
						
					myModal.show();
				}
			})
		}
	</script>
	<c:if test="${sessionScope.error}">
		<script type="text/javascript">
			alert("${sessionScope.error}");
		</script>
	</c:if>
</body>
</html>