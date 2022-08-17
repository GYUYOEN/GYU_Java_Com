<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 추가</title>
	<%@ include file="../module/head.jsp" %>
</head>
<%-- 아래 테그를 이용하면 contextPath가 바뀌어도 에러가 안남 --%>
<c:url var="ajaxDuplicateUrl" value="/ajax/duplicate" />
<c:url var="ajaxExistsUrl" value="/ajax/exists" />
<body>
	<%@ include file="../module/navigation.jsp" %>
	<!-- 
		action : 전송주소(현재위치에서 add) 
		현재 위치(./) : localhost:8080/jpr/depts
		doGet에서 받은 요청 post에 전달
	-->
	<section class="container">
		<div>
			<form class="small-form" action="./add" method="post"> 
				<div class="input-form wide">
					<label class="input-label">부서ID</label>
					<!-- onblur : 한번에 인식, oninput : 글자 하나하나 인식(특별한 경우 아니면 가급적 쓰지 않기) -->
					<input class="input-text" type="text" name="deptId" onblur="duplicateCheck(this, '${ajaxDuplicateUrl}')"
						value="${data.deptId == -1 ? '' : data.deptId}" data-required="부서 ID를 입력하세요.">
					<label class="input-label-error"></label>
				</div>
				<div class="input-form wide">
					<label class="input-label">부서명</label>
					<input class="input-text" type="text" name="deptName" 
						value="${data.deptName}" data-required="부서명을 입력하세요." >
					<label class="input-label-error"></label>
				</div>
				<div class="input-form wide">
					<label class="input-label">관리자ID</label>
					<input class="input-text" type="text" name="mngId" onblur="existsCheck(this, '${ajaxExistsUrl}');"
						value="${data.mngId == -1 ? '' : data.mngId}" data-required="관리자 ID를 입력하세요.">
					<label class="input-label-error"></label>
				</div>
				<div class="input-form wide">
					<label class="input-label">지역ID</label>
					<input class="input-text" type="text" name="locId" onblur="existsCheck(this, '${ajaxExistsUrl}');"
						value="${data.locId == -1 ? '' : data.locId}" data-required="지역 ID를 입력하세요.">
					<label class="input-label-error"></label>
				</div>
				<div class="input-form wide">
					<button class="btn btn-outline btn-ok" type="submit">저장</button>
					<button class="btn btn-outline btn-cancel" type="button" onclick="location.href='<%=request.getContextPath() %>/depts'">취소</button>
				</div>
			</form>
		</div>
	</section>
</body>
</html>