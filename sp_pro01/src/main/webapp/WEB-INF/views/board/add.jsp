<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>게시판 등록</title>
	<jsp:include page="../module/head.jsp" />
	<c:url value="/static/ckeditor" var="ckedit" />
	<script type="text/javascript" src="${ckedit}/ckeditor.js"></script>
</head>
<script type="text/javascript">
	function formCheck(form) {
		var modal = new bootstrap.Modal(document.getElementById("errorModal"), {
			keyboard: false
		});
		var title = modal._element.querySelector(".modal-title");
		var body = modal._element.querySelector(".modal-body");
		if(form.title.value === undefined || form.title.value.trim() === "") {
			title.innerText = "필수 입력";
			body.innerText = "제목은 공란이 올 수 없습니다. 반드시 제목을 입력하세요.";
			modal.show();
			return;
		}
		
		form.submit();
	}
	
	function uploadCheck(element) {
		var modal = new bootstrap.Modal(document.getElementById("errorModal"), {
			keyboard: false
		});
		var title = modal._element.querySelector(".modal-title");
		var body = modal._element.querySelector(".modal-body");
		
		if(element.files.length > 3) {
			title.innerText = "파일 업로드 제한";
			body.innerText = "파일 업로드는 최대 3개 까지만 할 수 있습니다.";
			element.value = "";
			modal.show();
			return;
		}
		
		for(file of element.files) {
			if(file.size / 1000 / 1000 > 5.0) {
				title.innerText = "파일 크기 제한";
				body.innerText = "파일은 최대 5MB 까지만 할 수 있습니다.";
				element.value = "";
				modal.show();
				return;
			}
		}
	}
</script>
<body>
	<header></header>
	<section class="container">
		<div class="mt-3">
			<c:url value="/board/add" var="boardAddUrl" />
			<form action="${boardAddUrl}" method="post" enctype="multipart/form-data"> <!-- 파일업로드를 위해서 enctype="multipart/form-data" 를 작성해주어야 함 -->
				<div class="mb-3">
					<input class="form-control" id="id_title" name="title" placeholder="제목을 입력하세요." value="${param.title}">
				</div>
				<div class="mb-3">
					<textarea class="form-control" id="id_content" name="content"
						rows="5" placeholder="내용을 입력하세요.">${param.content}</textarea>
				</div>
				<div class="mb-3">
					<input class="form-control" type="file" name="upload" onchange="uploadCheck(this);" multiple></input>
				</div>
				<div class="text-end">
					<button class="btn btn-primary" type="button" onclick="formCheck(this.form);">저장</button>
				</div>
			</form>
		</div>
		<div class="modal fade" id="errorModal" tabindex="-1" aria-labelledby="errorModalLabel">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="errorModalLabel"></h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						${errorMsg}
      				</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-danger btn-sm" data-bs-dismiss="modal">확인</button>
					</div>
				</div>
			</div>
		</div>
	</section>
	<footer></footer>
	<c:if test="${not empty errorMsg}">
		<script type="text/javascript">
			var modal = new bootstrap.Modal(document.getElementById("errorModal"), {
				keyboard: false
			})
			modal.show();
		</script>
	</c:if>
	<c:url var="imageUrl" value="/upload/image" />
	<script type="text/javascript">
		CKEDITOR.replace("content", {
			filebrowserUploadUrl: "${imageUrl}?type=image"
		});
	</script>
	<%-- 
	<script type="text/javascript">
		CKEDITOR.replace("content", {
			filebrowserUploadUrl: "${imageUrl}?type=image"
		});
	</script>
	--%>
</body>
</html>
