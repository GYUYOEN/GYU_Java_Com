<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인</title>
	<%@ include file="../module/head.jsp" %>
</head>
<body>
<%@ include file="../module/navigation.jsp" %> <!-- include 지시자 : 해당파일의 코드를 사입 후 navigation이랑 같이 실행(정적 패아지에 사용됨). 내부 서버에서 사용됨(내부에 jsp 파일이 만들어져 있어야함) -->
	<section class="container">
		<div>
			<button type="button" onclick="sendAjax()">전송</button>
		</div>
		<c:url var="loginUrl" value="/login" />
		<form class="small-form" action="${loginUrl}" method="post"><!-- post 전송주소 localhost:8080/jsp01/dept/add -->
			<div class="input-form wide">
				<label class="input-label">직원ID</label>
				<input type="text" class="input-text" name="empId" value="" data-required="직원ID를 입력하세요."> <!-- data : setAttribute로 전달된 값 -->
				<c:if test="${not empty error}"> <!-- 참이면 출력, not empty : error가 비어져 있지 않은지 확인 -->
					<label class="input-label-error">${error}</label> <!-- error.deptId : error.(map의 키) -->
				</c:if>
			</div>
			<div class="input-form wide">
				<label class="input-label">부서명</label>
				<select class="select-form" name="deptId" data-required="부서명을 선택하세요.">
					<c:forEach items="${deptList}" var="deptDto">						
						<c:choose>	<%-- 로그인 정보가 다를때 넣은 부서명 유지 --%>
							<c:when test="${empty error and cookie.deptRe.value == deptDto.deptId}">
								<option value="${deptDto.deptId}" selected>
									[${deptDto.deptId}] ${deptDto.deptName}
								</option>
							</c:when>
							<c:when test="${not empty error and param.deptId == deptDto.deptId}"> <%-- 로그인 에러(로그인 정보를 다시 확인하세요!) 있을때 유지 하는 기능 --%>
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
				<input type="text" class="input-text" name="empName" value="${param.empName}" data-required="이름을 입력하세요."><!-- 로그인 정보가 다를때 넣은 이름 유지하고 싶을때 value에 ${param.empName} 작성 아니면 그냥 "" -->
			</div>
			<div class="input-form wide form-rignt">
				부서기억하기<input type="checkbox" name="deptRe" ${not empty cookie.deptRe.value ? 'checked' : ''}>
				<button class="btn btn-outline btn-ok" type="submit">로그인</button>
			</div>
		</form>
	</section>
</body>
</html>