<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>사원 추가</title>
	<%@ include file="../module/head.jsp" %>
</head>
<script type="text/javascript">
	window.onload = function() {
		var form = document.forms[0];
		form.email.addEventListener("input", enableSaveButton);
		form.phone.addEventListener("input", enableSaveButton);
		
		prevImage.addEventListener("click", function(e) {
			btnImage.click();
		});
		
		btnImage.addEventListener("change", ajaxUploadImage);
	}
	
	function ajaxUploadImage(e) {
		var file = e.target.files[0];
		var fData = new FormData();
		fData.append("uploadImage", file, file.name);
		console.log(fData);
		$.ajax({
			type: "post",
			url: "/ajax/imageUpload",
			enctype: "multipart/form-data",
			data: fData,
			processData: false,
			contentType: false,
			success: function(data, status) {
				prevImage.src = data.loc;
			},
			error: function(data, status) {
				prevImage.src = data.loc;
			}
		});
	}
	
	function showPreview(e) {
		var file = e.target.files[0];
		var imgUrl = URL.createObjectURL(file);
		prevImage.src = imgUrl;
	}
	
	function enableSaveButton(e) {
		var submit = document.querySelector("button[type='submit']");
		var enable = submit.getAttribute("class").replace("disable", "");
		submit.setAttribute("class", enable);
	}
</script>
<body>
	<header class="mb-3">
		<%@ include file="../module/navigation.jsp" %>
	</header>
	<section class="container-fluid">
		<c:url var="empsAddUrl" value="/emps/add" />
		<form class="large-form" action="${empsAddUrl}" method="post" enctype="multipart/form-data">
			<c:url var="imgUrl" value="${imagePath}" />
			<img id="prevImage" class="rounded float-start" style="width: 360px;" alt="여기에는 증명 사진이 배치됩니다." src="${imgUrl}">
			<br>
			<input type="file" class="visually-hidden" id="btnImage" name="uploadImage">
			<div class="row g-3">
				<div class="col">
					<div class="form-floating mb-3">
						<input class="form-control" type="text" id="id_empId" name="empId"
							placeholder="ID" value="">
						<label for="id_empId">ID</label>
					</div>
				</div>
				<div class="col">
					<div class="col form-floating mb-3">
						<input class="form-control" type="text" id="id_empName" name="empName"
							placeholder="이름" value="">
						<label for="id_empName">이름</label>
					</div>
				</div>
			</div>
			<div class="row g-3">
				<div class="col">
					<div class="form-floating mb-3">
						<select class="form-select" id="id_jobId" name="jobId">
							<c:forEach items="${jobDatas}" var="job">
								<c:choose>
									<c:when test="${job.jobId == session.Scope.loginData.jobId}">
										<option value="${job.jobId}" selected>${job.jobName}</option>
									</c:when>
									<c:otherwise>
										<option value="${job.jobId}">${job.jobName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						<label for="id_jobId">직급</label>
					</div>
				</div>
				<div class="col">
					<div class="form-floating mb-3">
						<select class="form-select" id="id_deptId" name="deptId">
							<c:forEach items="${deptDatas}" var="dept">
								<c:choose>
									<c:when test="${dept.deptId == session.Scope.loginData.deptId}">
										<option value="${dept.deptId}" selected>[${dept.deptId}] ${dept.deptName}</option>
									</c:when>
									<c:otherwise>
										<option value="${dept.deptId}">[${dept.deptId}] ${dept.deptName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						<label for="id_deptId">부서</label>
					</div>
				</div>
			</div>
			<div class="row g-3">
				<div class="col">
					<div class="form-floating mb-3">
						<input class="form-control" type="text" id="id_email" name="email"
							placeholder="이메일" value="">
						<label for="id_email">이메일</label>
					</div>
				</div>
			</div>
			<div class="row g-3">
				<div class="col">
					<div class="form-floating mb-3">
						<input class="form-control" type="date" id="id_hireDate" name="hireDate"
							placeholder="입사일" value="">
						<label for="id_hireDate">입사일</label>
					</div>
				</div>
				<div class="col">
					<div class="form-floating mb-3">
						<input class="form-control" type="text" id="id_phone" name="phone"
							placeholder="전화번호" value="">
						<label for="id_phone">전화번호</label>
					</div>
				</div>
			</div>
			<div class="row g-3">
				<div class="col">
					<div class="form-floating mb-3">
						<input class="form-control" type="number" min="0" id="id_salary" name="salery"
							placeholder="급여액" value="">
						<label for="id_salary">급여액</label>
					</div>
				</div>
				<div class="col">
					<div class="form-floating mb-3">
						<input class="form-control" type="number" min="0" max="100" step="1" id="id_commission" name="commission"
							placeholder="커미션" value="">
						<label for="id_commission">커미션</label>
					</div>
				</div>
			</div>
			<div class="d-flex justify-content-end align-items-center mb-3">
				<div class="p-2">
					<button class="btn btn-outline-primary" type="submit">저장</button>
				</div>
			</div>
		</form>
	</section>
</body>
</html>