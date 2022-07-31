<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- 제어문 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- 날짜/ 숫자 등의 포멧 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <!-- 주로 문자열과 관련된 함수 -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>개인정보</title>
	<%@ include file="../module/head.jsp" %>
</head>
<script type="text/javascript">
	window.onload = function() {
		// 수정하면 저장 버튼 활성화 js
		var form = document.forms[0];
		form.email.addEventListener("input", enableSaveButton);
		form.phone.addEventListener("input", enableSaveButton);
		
		
		// 이미지를 클릭하면 파일선택과 같은 효과가 나올 수 있게 하는 js(파일선택을 숨김처리 됨)
		prevImage.addEventListener("click", function(e) {
			btnImage.click();
		});
		
		// 이미지를 바꾸면 그 이미지가 뜰 수 있게 하는 js
		// btnImage.addEventListener("change", showPreview); // 파일을 선택하면 변경이벤트 동작하고 이벤트가 동작하면 showPreview 메서드 실행
		btnImage.addEventListener("change", ajaxUploadImage);
	}
	
	// 이미지 업로드
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
			// 파일 업로드 할때 두가지 작성 필수(일반적인 content가 아니기 때문에)
			processData: false, // 문자열 제외 데이터 전송에 필요(이미지 경로에 대해서만 false, 문자열 일때 안써도 됨)
			contentType: false, // enctype에 맞춰서 자동으로 들어감
			success: function(data, status) {
				// console.log(data.msg)
				// console.log(data.loc)
				prevImage.src = data.loc;
			},
			error: function(data, status) {
				// console.log(data.msg)
				// console.log(data.loc)
				prevImage.src = data.loc;
			}
		});
	}
	
	// 이미지를 바꾸면 그 이미지가 뜰 수 있게 하는 js
	function showPreview(e) {
		var file = e.target.files[0]; // 선택한 이미지의 파일 객체 정보 저장(배열 -> 여러개를 선택할 수 이도록 하는게 가능(multiple)하므로 배열로 있음)
		var imgUrl = URL.createObjectURL(file); // 만들어진 파일에 대한 URL 정보 저장(console.log(imgUrl))
		prevImage.src = imgUrl; // src: 서버에 들어가 있는 이미지 정보가 있음 -> 사용자가 선책한 이미지 경로 저장되도록 함
	}
	
	
	// 수정하면 저장 버튼 활성화 js
	function enableSaveButton(e) {
		var submit = document.querySelector("button[type='submit']");
		var enable = submit.getAttribute("class").replace("disable", "");
		submit.setAttribute("class", enable);
	}
</script>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<section class="container">
	<c:url var="updateUrl" value="/myinfo" /> <!-- 자동으로 context root가 들어감 -> value = /jsp01/myinfo -->
		<form class="large-form" action="${updateUrl}" method="post" enctype="multipart/form-data">
			<%-- 
				이미지는 데이터베이스에 이미지의 저장 위치 / 이미지 파일명을 기록하여 관리하게 한다.
			 	Employees 테이블에 별도의 이미지 파일 결로와 관련된 컬럼이 없음
			 	모든 사원의 이미지는 사원ID.png 형식으로 /static/img/emp/ 경로 안에 저장을 한다.
			 	(원래는 이미지 테이블을 따로 만들어서 조인시키고 empId(컬럼)와 imagePath를 이용해 조회할 수 있도록 한다.)
			 --%>
			<div class="img-form left">
				<c:url var="imgUrl" value="${imagePath}" />
				<img id="prevImage" class="img-360" alt="여기에는 증명 사진이 배치됩니다." src="${imgUrl}"> <%-- preview : 브라우저에서 관리하는 경로 --%>
				<br>
				<input type="file" id="btnImage" name="uploadImage"> <%-- multiple 사용하면 파일 여러개 선택 가능(js 에서 배열 쓰는 이유) --%>
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
									<option value="${job.jobId}" selected>${job.jobName}</option> <%-- 자신의 직급은 selected 되서 초기상태로 나옴 --%>
								</c:when>
								<c:otherwise>
									<option value="${job.jobId}">${job.jobName}</option> <%-- 나머지는 목록으로 --%>
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
								<c:when test="${dept.deptId == sessionScope.loginData.deptId}">
									<option value="${dept.deptId}" selected>${dept.deptName}</option>
								</c:when>
								<c:otherwise>
									<option value="${dept.deptId}">${dept.deptName}</option>
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
<!-- 
	disabled : 비활성, 수정x, 전송x
	readonly : 읽기 전용, 수정x, 전송o
	이메일과 전화번호만 전송했을 때는 누가 전송했는지 모름으로 disabled 사용하면 안되고 readonly 사용
	session을 사용하고 있으므로 session에 저장된 정보를 쓰면 됨 disabled, readonly 둘다 상관없음
 -->