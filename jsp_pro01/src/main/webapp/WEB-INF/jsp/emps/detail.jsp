<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- 제어문 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- 날짜/ 숫자 등의 포멧 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <!-- 주로 문자열과 관련된 함수 -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>직원 상세</title>
	<%@ include file="../module/head.jsp" %>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<section class="container">
		<div class="large-form">
			<div class="img-form left">
				<c:url var="imgUrl" value="${imagePath}" />
				<input type="file" id="btnImage" name="uploadImage"
					 onchange="showPreview(this,'prevImage')" style="display: none;" disabled> <%-- multiple 사용하면 파일 여러개 선택 가능(js 에서 배열 쓰는 이유) --%>
				<img id="prevImage" class="img-360" 
					 onclick="btnImage.click();" alt="여기에는 증명 사진이 배치됩니다." src="${imgUrl}"> <%-- preview : 브라우저에서 관리하는 경로 --%>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<c:url var="ajaxDuplicateUrl" value="/ajax/duplicate"/>
					<label class="input-label w-100">ID</label>
					<input class="input-text w-auto" type="text" name="empId"
						value="${data.empId}" data-required="ID는 필수입력입니다." disabled>
					<label class="input-label-error"></label>
				</div>
				<div class="input-form">
					<label class="input-label w-100">이름</label>
					<input class="input-text w-auto" type="text" name="empName" 
						value="${data.empName}" disabled>
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">직급</label>
					<select class="select-form w-auto" name="jobId" disabled>
						<option value="${data.jobId}" selected>${data.jobName}</option>
						<%--
						<c:forEach items="${jobDatas}" var="job">
							<c:choose>
								<c:when test="${job.jobId == session.Scope.loginData.jobId}">
									<option value="${job.jobId}" selected>${job.jobName}</option> <!-- 자신의 직급은 selected 되서 초기상태로 나옴 -->
								</c:when>
								<c:otherwise>
									<option value="${job.jobId}">${job.jobName}</option> <!-- 나머지는 목록으로 -->
								</c:otherwise>
							</c:choose>
						</c:forEach>
						--%>
					</select>
				</div>
				<div class="input-form">
					<label class="input-label w-100">부서</label>
					<select class="select-form w-auto" name="deptId" disabled>
						<option value="${data.deptId}">[${data.deptId}] ${data.deptName}</option>
					</select>
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">이메일</label>
					<input class="input-text w-auto" type="text" name="email" value="${data.email}" disabled>
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<fmt:formatDate var="hireDate" value="${dataDetail.hireDate}" pattern="YYYY-MM-dd"/>
					<label class="input-label w-100">입사일</label>
					<input class="input-text w-auto" type="date" name="hireDate" value="${hireDate}" disabled>
				</div>
				<div class="input-form">
					<label class="input-label w-100">전화번호</label>
					<input class="input-text w-auto" type="text" name="phone" value="${dataDetail.phone}" disabled>
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">급여액</label>
					<input class="input-text w-auto" type="number" name="salary" value="${dataDetail.salary}" disabled>
				</div>
				<div class="input-form">
					<label class="input-label w-100">커미션</label>
					<input class="input-text w-auto" type="number" name="commission" value="${dataDetail.commission}" disabled>
				</div>
			</div>
			<div class="input-form form-right">
				<c:url var="empModUrl" value="/emps/modify">
					<c:param name="id" value="${data.empId}" />
				</c:url>
				<c:url var="empDelUrl" value="/emps/delete">
					<c:param name="id" value="${data.empId}" />
				</c:url>
				<button class="btn btn-outline btn-ok" type="button" onclick="location.href='${empModUrl}'">수정</button>
				<button class="btn btn-outline btn-cancel" type="button" data-bs-toggle="modal" data-bs-target="#deleteModal">삭제</button>
			</div>
		</div>
	</section>
	<div class="modal" tabindex="-1" id="deleteModal">
  		<div class="modal-dialog modal-dialog-centered">
    		<div class="modal-content">
      			<div class="modal-header">
        			<h5 class="modal-title">직원 삭제</h5>
        			<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
     			</div>
      			<div class="modal-body">
        			<p>해당 직원의 정보를 삭제하시겠습니까?</p>
     			</div>
	    	   <div class="modal-footer">
	           		<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
	        		<button type="button" class="btn btn-danger" data-bs-dismiss="modal" onclick="empDelete(${data.empId});">삭제</button>
	      	  </div>
   	 	 </div>
   	</div>
	</div>
	<div class="modal" tabindex="-1" id="resultModal">
	  	<div class="modal-dialog modal-dialog-centered">
	    	<div class="modal-content">
	      		<div class="modal-header">
	        		<h5 class="modal-title">결과 확인</h5>
	        		<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	     		</div>
	      		<div class="modal-body">
	        		<p>석제되었습니다.</p>
	     		</div>
	    	   <div class="modal-footer">
	        		<button type="button" class="btn btn-primary" data-bs-dismiss="modal" onclick="location.href='/emps'">확인</button>
	      	  </div>
	   	  </div>
	   </div>
	</div>
</body>
<script type="text/javascript">
	function empDelete(empId) {
		$.ajax ({
			type: "post",
			url: "/ajax/delete",
			data: {
				id: empId,
				type: "emp"
			},
			dataType: "json",
			success: function(data){
				// 삭제를 눌렀을때 서버에서 응답이 오면 다시 모달이 나올 수있도록 하기 위한 작업
				var myModal = new Bootstrap.Modal(document.getElementById("resultModal"), {
					keyboard: false
				});
				
				// console.log(myModal);
				
				var title = myModal._element.querySelector(".modal-title");
				var body = myModal._element.querySelector(".modal-body");
				// title.innerText = "값 변경 확인";
				// body.innerHTML = "<p>" + "데이터 변경 확인을 하였습니다." + "</p>";
				title.innerText = data.title;
				body.innerText = "<p>" + data.message + "/<p>"
				
				myModal.show();
			}
		})
	}
</script>
</html>
<!-- 
	disabled : 비활성, 수정x, 전송x
	readonly : 읽기 전용, 수정x, 전송o
	이메일과 전화번호만 전송했을 때는 누가 전송했는지 모름으로 disabled 사용하면 안되고 readonly 사용
	session을 사용하고 있으므로 session에 저장된 정보를 쓰면 됨 disabled, readonly 둘다 상관없음
 -->