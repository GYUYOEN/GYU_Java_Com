<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
		<c:url var="empModUrl" value="/emps/modify" />
		<form class="large-form" action="${empModUrl}" method="post" enctype="multipart/form-data">
			<div class="img-form left">
				<c:url var="imgUrl" value="${imagePath}" />
				<img id="prevImage" class="img-360"
					onclick="btnImage.click();" alt="여기에는 증명 사진이 배치됩니다." src="${imgUrl}">
				<input type="file" id="btnImage" name="uploadImage"
					onchange="showPreview(this, 'prevImage');" style="display: none;">
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">ID</label>
					<input class="input-text w-auto" type="text" name="empId"
						value="${data.empId}" readonly> <!-- 사용자가 작성하면 안돼지만 파라미터가 필요할 때 : readonly, disabled 는 파라미터 전달안됌 -->
					<label class="input-label-error"></label>
				</div>
				<div class="input-form">
					<label class="input-label w-100">이름</label>
					<input class="input-text w-auto" type="text" name="empName" 
						value="${data.empName}">
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">직급</label>
					<select class="select-form w-auto" name="jobId">
						<c:forEach items="${jobDatas}" var="job">
							<c:choose>
								<c:when test="${job.jobId eq data.jobId}" >
									<option value="${job.jobId}" selected>${job.jobName}</option>
								</c:when>
								<c:otherwise>
									<option value="${job.jobId}">${job.jobName}</option>
								<</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
				<div class="input-form">
					<label class="input-label w-100">부서</label>
					<select class="select-form w-auto" name="deptId">
						<c:forEach items="${deptDatas}" var="dept">
							<c:choose>
								<c:when test="${dept.deptId eq data.deptId}" >
									<option value="${dept.deptId}"selected>[${dept.deptId}] ${dept.deptName}</option>
								</c:when>
								<c:otherwise>
									<option value="${dept.deptId}">[${dept.deptId}] ${dept.deptName}</option>
								<</c:otherwise>
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
					<fmt:formatDate var="hireDate" value="${dataDetail.hireDate}" pattern="YYYY-MM-dd" />
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
					<c:param name="id" value="${data.empId}" />
				</c:url>
				<button class="btn btn-outline btn-ok" type="submit">저장</button>
				<button class="btn btn-outline btn-cancel" type="button" onclick="location.href='${empDetailUrl}'">취소</button>
			</div>
		</form>
	</section>
</body>
</html>