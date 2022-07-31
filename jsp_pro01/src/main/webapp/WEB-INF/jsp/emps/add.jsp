<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- 제어문 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- 날짜/ 숫자 등의 포멧 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <!-- 주로 문자열과 관련된 함수 -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>직원 추가</title>
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
		btnImage.addEventListener("change", showPreview);
	}
	
</script>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<section class="container">
		<c:url var="empsAddUrl" value="/emps/add" /> <%-- 자동으로 context root가 들어감 -> value = /jsp01/myinfo --%>
		<form class="large-form" action="${empsAddUrl}" method="post" enctype="multipart/form-data">
			<%-- 
				직원 사진 프로필 업로드
					1. 로그인을 한 직원이 개정 정보 화면에서 업롣드
					2. 업로드한 이미지는 /static/img/emp/직원ID.png 경로에 저장됨
					3. 직원ID는 로그인 세션에 저장한 정보를 사용하여 이미지 파일명을 변경하여 저장
					
				새로운 직원 등록
					1. 직원 등록 권한을 가지는 계정으로 직원 정보를 추가 할 뗴 프로필 이미지를 같이 업로드
					2. 업로드한 이미지는 /static/ img/emp/직원ID.png 경로에 저장되어야 함
					3. 세로 등록하는 직원의 ID 는 아직 모르는 상태이기 때문에 기존 Ajax 로 업로드를 해봤자 사용할 직원ID를 알 수 없는 상테에 빠짐
			
			
				이미지는 데이터베이스에 이미지의 저장 위치 / 이미지 파일명을 기록하여 관리하게 한다.
			 	Employees 테이블에 별도의 이미지 파일 결로와 관련된 컬럼이 없음
			 	모든 사원의 이미지는 사원ID.png 형식으로 /static/img/emp/ 경로 안에 저장을 한다.
			 	(원래는 이미지 테이블을 따로 만들어서 조인시키고 empId(컬럼)와 imagePath를 이용해 조회할 수 있도록 한다.)
			 --%>
			<div class="img-form left">
				<c:url var="imgUrl" value="${imagePath}" />
				<input type="file" id="btnImage" name="uploadImage"
					 onchange="showPreview(this,'prevImage');" style="display: none;"> <%-- multiple 사용하면 파일 여러개 선택 가능(js 에서 배열 쓰는 이유) --%>
				<img id="prevImage" class="img-360" 
					 onclick="btnImage.click();" alt="여기에는 증명 사진이 배치됩니다." src="${imgUrl}"> <%-- preview : 브라우저에서 관리하는 경로 --%>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<c:url var="ajaxDuplicateUrl" value="/ajax/duplicate" />
					<label class="input-label w-100">ID</label>
					<input class="input-text w-auto" type="text" name="empId"
						onblur="duplicateCheck(this, '${ajaxDuplicateUrl}')" value="" data-required="ID는 필수입력입니다.">
					<label class="input-label-error"></label>
				</div>
				<div class="input-form">
					<label class="input-label w-100">이름</label>
					<input class="input-text w-auto" type="text" name="empName" value="">
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">직급</label>
					<select class="select-form w-auto" name="jobId">
						<c:forEach items="${jobDatas}" var="job">
							<option value="${job.jobId}">${job.jobName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="input-form">
					<label class="input-label w-100">부서</label>
					<select class="select-form w-auto" name="deptId">
						<c:forEach items="${deptDatas}" var="dept">
							<option value="${dept.deptId}">[${dept.deptId}] ${dept.deptName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">이메일</label>
					<input class="input-text w-auto" type="text" name="email" value="">
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<fmt:formatDate var="now" value="<%=new java.util.Date() %>" pattern="YYYY-MM-dd"/>
					<label class="input-label w-100">입사일</label>
					<input class="input-text w-auto" type="text" name="hireDate" value="${now}">
				</div>
				<div class="input-form">
					<label class="input-label w-100">전화번호</label>
					<input class="input-text w-auto" type="text" name="phone" value="">
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">급여액</label>
					<input class="input-text w-auto" type="number" name="salary" value="0">
				</div>
				<div class="input-form">
					<label class="input-label w-100">커미션</label>
					<input class="input-text w-auto" type="number" name="commission" value="0">
				</div>
			</div>
			<div class="input-form form-right">
				<button class="btn btn-outline btn-ok" type="submit">저장</button>
			</div>
		</form>
	</section>
</body>
</html>
<%-- 
	disabled : 비활성, 수정x, 전송x
	readonly : 읽기 전용, 수정x, 전송o
	이메일과 전화번호만 전송했을 때는 누가 전송했는지 모름으로 disabled 사용하면 안되고 readonly 사용
	session을 사용하고 있으므로 session에 저장된 정보를 쓰면 됨 disabled, readonly 둘다 상관없음
 --%>