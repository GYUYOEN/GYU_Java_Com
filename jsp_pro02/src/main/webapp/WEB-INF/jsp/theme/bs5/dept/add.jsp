<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 추가</title>
	<%@ include file="../module/head.jsp" %>
</head>
<c:url var="ajaxDuplicateUrl" value="/ajax/duplicate" />
<c:url var="ajaxExistsUrl" value="/ajax/exists" />
<script type="text/javascript">
function sendElementDataValid(element, url) {
	$.ajax({
		type: "get",
		url: url,
		data: {
			name: element.name,
			value: element.value
		},
		success: function(data, status) {
			setLabelState(element, data.code, data.message);
		},
		complete: function() {
			if(element.value === "" || element.value === undefined) {
				element.nextElementSibling.innerText = element.getAttribute("placeholder");
			}
		}
	});
}
function duplicateCheck(element) {
	sendElementDataValid(element, "${ajaxDuplicateUrl}")
}
function existsCheck(element) {
	sendElementDataValid(element, "${ajaxExistsUrl}")
}
function setLabelState(element, code, message) {
	if(code === "success") {
		element.nextElementSibling.innerText = message;
		element.setAttribute("class", "form-control is-valid");
	} else if(code === "error") {
		// 오류 메시지
		element.nextElementSibling.innerText = message;
		element.setAttribute("class", "form-control is-invalid");
	}
}
</script>
<body>
	<header class="mb-3">
		<%@ include file="../module/navigation.jsp" %>
	</header>
	<section class="container-fluid" style="width: 520px;">
		<c:url var="deptAddUrl" value="/dept/add" />
		<form action="${deptAddUrl}" method="post">
			<div class="form-floating mb-1">
				<input type="text" class="form-control" id="id_deptId" name="deptId" onblur="duplicateCheck(this);"
					placeholder="부서ID" value="${data.deptId == -1 ? '' : data.deptId}" data-required="부서 ID를 입력하세요.">
				<label for="id_deptId">부서ID</label>
			</div>
			<div class="form-floating mb-1">
				<input type="text" class="form-control" id="id_deptName" name="deptName"
					placeholder="부서명" value="${data.deptName}" data-required="부서명을 입력하세요.">
				<label for="id_deptName">부서명</label>
			</div>
			<div class="form-floating mb-1">
				<input type="text" class="form-control" id="id_mngId" name="mngId" onblur="existsCheck(this);"
					placeholder="관리자ID" value="${data.mngId == -1 ? '' : data.mngId}" data-required="관리자 ID를 입력하세요.">
				<label for="id_mngId">관리자ID</label>
			</div>
			<div class="form-floating mb-1">
				<input type="text" class="form-control" id="id_locId" name="locId" onblur="existsCheck(this);"
					placeholder="지역ID" value="${data.locId == -1 ? '' : data.locId}" data-required="지역 ID를 입력하세요.">
				<label for="id_locId">지역ID</label>
			</div>
			<div class="d-flex justify-content-end">
				<div class="ms-1">
					<button class="btn btn-outline-primary" type="submit">저장</button>
				</div>
				<div class="ms-1">
					<c:url var="deptUrl" value="/depts" />
					<button class="btn btn-outline-danger" type="button" onclick="location.href='${deptUrl}'">취소</button>
				</div>
			</div>
		</form>
	</section>
</body>
</html>