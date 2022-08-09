<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>main 화면</title>
	<%@ include file="./module/head.jsp" %>
</head>
<script type="text/javascript">
	// ajax를 사용하면 페이지 전환을 하지 않아도 화면 변경이 가능(JS 많이 필요(이미 사용중이거나 탈퇴한 아이디입니다.)), 비동기식
	function sendAjax() {
		$.ajax({
			// 자바스크립트의 오브젝트 형식으로 만들어짐
			type: "get", // method 방식
			url: "/ajax/test", // Ajax를 처리할 서버 주소
			data: {
				x: 1, y: "A" // 서버에 전달할 데이터 작성(controller에 parameter로 받음)
			}, 
			dataType: "json", // 서버로 부터 전달 받을 데이터 타입(json, text, xml, html, ...), 데이터 타입 형식에 안맞춰주면 에러
			success: function(data, status) { // data : 받을 데이터 인자 필요
				// 응답이 성공(응답코드 200)적으로 이루어 졌을 때 동작할 함수
				console.log("success: " + data);
				// console.log("success: " + data.msg);
				// console.log("success: " + data.kor);
				for(d of data) {
					console.log("success: " + d.empId);
					console.log("success: " + d.empName);
					console.log("success: " + d.deptId);
					console.log("success: " + d.deptName);
					console.log("success: " + d.jobId);
					console.log("success: " + d.jobName);
				}
			},
			error: function(data, status) {
				// 응답코드 200이 아닌 모든 응답일 때 동작할 함수
				// console.log("error 발생");
				console.log(data);
				console.log(status);
			},
			complete: function() {
				// 성공/실패 유무와 상관없이 동작할 함수
				console.log("complete 무조건 실행");
			},
			beforeSend: function() {
				// 서버에 데이터를 전송하기 전에 동작할 함수
				console.log("beforeSend 데이터 전송 전")
			}
		});
	}
</script>
<body>
	<%@ include file="./module/navigation.jsp" %>
	<h1>조회 연습 로그인 화면</h1>
	<section class="container">
		<div>
			<button type="button" onclick="sendAjax()">전송</button>
		</div>
		<c:url var="loginUrl" value="/login"/>
		<form class="small-form" action="${loginUrl}" method="post"> 
			<div class="input-form wide">
				<label class="input-label">부서ID</label>
				<input class="input-text" type="text" name="empId" value="" data-required="부서 ID를 입력하세요.">
				<c:if test="${not empty error}">
					<label class="input-label-error">${error}</label>
				</c:if>
			</div>
			<div class="input-form wide">
				<label class="input-label">부서명</label>
				<select class="select-form" name="deptId" data-required="부서명을 선택하세요.">
					<c:forEach items="${deptList}" var="deptDto">
						<c:choose>
							<c:when test="${deptDto.deptId == param.deptId}">
								<option value="${deptDto.deptId}" selected>
									[${deptDto.deptId}] ${deptDto.deptName}
								</option>
							</c:when>
							<c:otherwise>
								<option value="${deptDto.deptId}">
									[${deptDto.deptId}] ${deptDto.deptName}
								</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<div class="input-form wide">
				<label class="input-label">이름</label>
				<input class="input-text" type="text" name="empName" value="${param.empName}" data-required="부서 ID를 입력하세요.">
			</div>
			<div class="input-form wide form-rignt">
				<button class="btn btn-outline btn-ok" type="submit">로그인</button>
			</div>
			<div></div>
		</form>
	</section>
</body>
</html>