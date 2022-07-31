<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- 제어문 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- 날짜/ 숫자 등의 포멧 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <!-- 주로 문자열과 관련된 함수 -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>직원 수정</title>
	<%@ include file="../module/head.jsp" %>
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<section class="container">
	<c:url var="empsModUrl" value="/emps/modify" /> <!-- 자동으로 context root가 들어감 -> value = /jsp01/myinfo -->
		<form class="large-form" action="${empsModUrl}" method="post" enctype="multipart/form-data">
			<div class="img-form left">
				<c:url var="imgUrl" value="${imagePath}" />
				<input type="file" id="btnImage" name="uploadImage"
					 onchange="showPreview(this,'prevImage')" style="display: none"> <%-- multiple 사용하면 파일 여러개 선택 가능(js 에서 배열 쓰는 이유) --%>
				<img id="prevImage" class="img-360" 
					 onclick="btnImage.click();" alt="여기에는 증명 사진이 배치됩니다." src="${imgUrl}"> <%-- preview : 브라우저에서 관리하는 경로 --%>
				<br>
			</div>
			<div class="input-form inline">
				<div class="input-form"> 
					<!-- PK는 되도록 수정x -->
					<label class="input-label w-100">ID</label>
					<input class="input-text w-auto" type="text" name="empId"
						value="${data.empId}" readonly>
					<label class="input-label-error"></label>
				</div>
				<div class="input-form">
					<label class="input-label w-100">이름</label>
					<input class="input-text w-auto" type="text" name="empName" value="${data.empName}">
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">직급</label>
					<select class="select-form w-auto" name="jobId">
						<c:forEach items="${jobDatas}" var="job">
							<c:choose>
								<c:when test="${job.jobId eq data.jobId}"> <%-- eq 는 == --%>
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
					<select class="select-form w-auto" name="deptId">
						<c:forEach items="${deptDatas}" var="dept">
							<c:choose>
								<c:when test="${dept.deptId eq data.deptId}">
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
					<input class="input-text w-auto" type="text" name="email" value="${data.email}">
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<fmt:formatDate var="hireDate" value="${dataDetail.hireDate}" pattern="YYYY-MM-dd"/>
					<label class="input-label w-100">입사일</label>
					<input class="input-text w-auto" type="date" name="hireDate" value="${hireDate}">
				</div>
				<div class="input-form">
					<label class="input-label w-100">전화번호</label>
					<input class="input-text w-auto" type="text" name="phone" value="${dataDetail.phone}">
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">급여액</label>
					<input class="input-text w-auto" type="number" name="salary" value="${dataDetail.salary}">
				</div>
				<div class="input-form">
					<label class="input-label w-100">커미션</label>
					<input class="input-text w-auto" type="number" name="commission" value="${dataDetail.commission}">
				</div>
			</div>
			<div class="input-form form-right">
				<c:url var="empDetailUrl" value="/emps/detail">
					<c:param name="id" value="${data.empId}"/>
				</c:url>
				<button class="btn btn-outline btn-ok" type="submit">저장</button>
				<button class="btn btn-outline btn-cancel" type="button" onclick="location.href='${empDetailUrl}'">취소</button>
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