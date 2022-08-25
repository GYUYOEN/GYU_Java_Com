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
					<button class="btn btn-success" type="button" onclick="location.href='${boardUrl}/modify?id=${data.id}'">수정</button>
					<button class="btn btn-danger" type="button" data-bs-toggle="modal" data-bs-target="#deleteModal">삭제</button>
				</c:if>
			</div>
		</div>
		
		<div class="mb-3">
			<div class="mb-1">			
				<div class="card border-light">
					<div class="card-header">
						<div class="d-flex justify-content-between">
							<span><small>Steven King</small></span>
							<span><small>2022년 08월 04일</small></span>
						</div>
					</div>
					<div class="card-body">
					<input type="hidden" name="cid" value="1">
						<p class="card-text">댓글 양식 확인 중</p>
						<div class="card-end">
							<button class="btn btn-sm btn-outline-dark" type="button" onclick="changeModify(this)">수정</button>
							<button class="btn btn-sm btn-outline-dark" type="button">삭제</button>
						</div>
					</div>
				</div>
			</div>
			<div class="mb-1">
				<div class="input-group">
					<textarea class="form-control" rows="3" placeholder="댓글 작성"></textarea>
					<button class="btn btn-outline-dark" type="button"s>작성</button>
				</div>
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
		function changeModify(element) { // 수정 누르면 삭제 버튼 사라지고 확인버큰이 나옴
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
		function changeText(element) {
			element.innerText = "수정";
			
			var btnDelete = document.createElement("button");
			btndelete.innerText = "삭제"
			btnDelete.setAttribute("type", "button");
			btnDelete.setAttribute("class", "btn btn-sm btn-outline-dark");
			btnDelete.setAttribute("onclick", "commentDelete(" + element.parentElement.parentElement.firstElementChild.value + ");";
			
			element.after(btnDelete);
		}
		function commentUpdate(e) {
			var cid = e.tearget.parentElement.parentElement.firstElementChild.value;
			var value = e.target.parentElement.previousElementSibling.children[0].value;
			$.ajax({
				url: "/comment/modify",
				type: "post",
				data: {
					id: cid,
					content: value
				},
				success: function(data) {
					if(data.code === "success") {
						alert("수정 되었습니다.");
					} else {
						alert("수정에 실패하였습니다.");
					}
					changeText(e.target);
				}
			});
		}
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
		
		function boardDelete(boardId) {
			$.ajax({
				type: "post",
				url: "/jpr/board/delete",
				data: {
					id: boardId,
				},
				dataType: "json",
				success: function(data) {
					var myModal = new bootstrap.Modal(document.getElementById("resultModal"), {
						keyboard: false
					});
					
					console.log(myModal._element.querySelector(".modal-title"));
					
					var title = myModal._element.querySelector(".modal-title");
					var body = myModal._element.querySelector(".modal-body");
					title.innerText = data.title;
					body.innerHTML = "<p>" + data.message + "</p>"
						
					myModal.show();
				}
			})
		}
	</script>
</body>
</html>