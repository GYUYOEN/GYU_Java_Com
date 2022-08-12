<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>개인정보</title>
	<%@ include file="../module/head.jsp" %>
</head>
<script type="text/javascript">
	window.onload = function() {
		var form = document.forms[0];
		form.email.addEventListener("input", enableSaveButton);
		form.phone.addEventListener("input", enableSaveButton);
		
		// 선택한 이미지에 대해서 뜨게 화면상에 뜨게하기 위해서 사용
		prevImage.addEventListener("click", function(e) {
			// input을 숨김처리하고 대신에 이미지를 클릭했을 뗴 input 파일 선택과 같은 동작이 이루어지게 함
			btnImage.click();
		});
		
		btnImage.addEventListener("change" , ajaxUploadImage);
	}
	
	function ajaxUploadImage(e) {
		var file = e.target.files[0];
		var fData = new FormData();
		fData.append("uploadImage", file, file.name);
		
		$.ajax({
			type: "post",
			url: "/ajax/imageUpload",
			enctype: "multipart/form-data",
			data: fData,
			processData: false, // 문자열 제외 데이터 전송할 때
			contentType: false, // 서버에 데이터 보낼 떄
			success: function(data, status) {
				prevImage.src = data.loc;
			},
			error: function(data, status) {
				prevImage.src = data.loc;
			}
		});
	}
	
	function showPreview(e) {
		var file = e.target.files[0]; // 선택한 이미지의 파일 객체 정보(여러이미지를 선택할 수 있으므로 배열로 되어있음 -> multiple)
		var imgUrl = URL.createObjectURL(file); // file에 대한 url 정보를 가져옴
		prevImage.src = imgUrl; // src에는 원래 서버에 저장되어 있는 이미지 정보
	}
	
	function enableSaveButton(e) {
		var submit = document.querySelector("button[type='submit']");
		var enable = submit.getAttribute("class").replace("disable", "");
		submit.setAttribute("class", enable);
	}
</script>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<section class="container">
		<c:url var="updateUrl" value="/myinfo" />
		<form class="large-form" action="${updateUrl}" method="post" enctype="multipart/form-data">
			<div class="img-form left">
				 <!-- 
				 	원래는 이미지는 데이터베이스에 이미지의 저장 위치 / 이미지 파일명을 기록하여 관리하게 된다. 
				 	근데 여기서는 EMPLOYESS 테이블에 이미지 관력 컬럼이 없으므로
				 	모든 사원의 이미지는 사원ID.png 형식으로 /static/img/emp/ 경로 란에 저장을 한다.
				 	
					empId		imagePath
					 100		/static/img/emp/servertyv.png
				 -->
				<c:url var="imgUrl" value="${imagePath}" />
				<img id="prevImage" class="img-360" alt="여기에는 증명 사진이 배치됩니다." src="${imgUrl}">
				<br>
				<input type="file" id="btnImage" name="uploadImage">
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">ID</label>
					<input class="input-text w-auto" type="text" name="empId" value="${sessionScope.loginData.empId}" disabled> 
				</div>
				<div class="input-form">
					<label class="input-label w-100">이름</label>
					<input class="input-text w-auto" type="text" name="empName" value="${sessionScope.loginData.empName}" disabled>
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">직급</label>
					<select class="select-form w-auto" name="jobId" disabled>
						<c:forEach items="${jobDatas}" var="job">
							<c:choose>
								<c:when test="${job.jobId == sessionScope.loginData.jobId}">
									<option value="${job.jobId}" selected>[${job.jobId}] ${job.jobName}</option>
								</c:when>
								<c:otherwise>
									<option value="${job.jobId}">[${job.jobId}] ${job.jobName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
				<div class="input-form">
					<label class="input-label w-100">부서</label>
					<select class="select-form w-auto" name="deptId" disabled>
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
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">이메일</label>
					<input class="input-text w-auto" type="text" name="email" value="${sessionScope.loginData.email}">
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<fmt:formatDate var="fmtHireDate" value="${empDetail.hireDate}" dateStyle="long"/>
					<label class="input-label w-100">입사일</label>
					<input class="input-text w-auto" type="text" name="hireDate" value="${fmtHireDate}" disabled>
				</div>
				<div class="input-form">
					<label class="input-label w-100">전화번호</label>
					<input class="input-text w-auto" type="text" name="phone" value="${empDetail.phone}">
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<fmt:formatNumber var="fmtSalary" value="${empDetail.salary}" />
					<label class="input-label w-100">급여액</label>
					<input class="input-text w-auto" type="text" name="salary" value="${fmtSalary}" disabled>
				</div>
				<div class="input-form">
					<fmt:formatNumber var="fmtCommission" value="${empDetail.commission}" type="percent" />
					<label class="input-label w-100">커미션</label>
					<input class="input-text w-auto" type="text" name="commission" value="${fmtCommission}" disabled>
				</div>
			</div>
			<div class="input-form form-right">
				<button class="btn btn-outline btn-ok disable" type="submit">저장</button>
			</div>
		</form>
	</section>
</body>
</html>