<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, dept.model.DeptDTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- 제어문 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- 낳짜/ 숫자 등의 포멧 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <!-- 주로 문자열과 관련된 함수 -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 추가</title>
	<%@ include file="../module/head.jsp" %>
</head>
<c:url var="ajaxDuplicateUrl" value="/ajax/duplicate" />
<c:url var="ajaxExistsUrl" value="/ajax/exists" />
<body>
	<%@ include file="../module/navigation.jsp" %>
	<section class="container">
	<!--  
		<div>
			<h2>EL 확인용</h2>
			<ul>
				 // <li>request.getParameter("deptId")</li> 와 같음 
				<li>${param.deptId}</li> // 입력한 값을 출력  
				<li>${param.deptName}</li> 
				<li>${param.mngId}</li> 
				<li>${param.locId}</li> 
				<li>${param.name}</li> // 저장된 값이 없으면 그냥 빈 문자열로 나타남(error x, null x) 
			</ul>
		</div>
	-->
	<!-- requestScope 는 setAtrribute 했던 것을 가져오고 param은 parameter 데이터를 기져옴 -->
	<!-- 
		<div>
			<h2>EL request 에 설정된 속성 사용</h2>
			<ul>
				<li>${requestScope.data.deptId}</li> // request에 설정된 data(DeptDTO)의 멤버변수명 
				<li>${requestScope.data.deptName}</li>
				<li>${requestScope.data.mngId}</li>
				<li>${requestScope.data.locId}</li>
			</ul>
		</div>
	-->
		<%--
		<%
			<!-- Map<String, String> error = request.getAttribute("error") != null ? (Map<String, String>)request.getAttribute("error") : null -->
			Map<String, String> error = request.getAttribute("error") != null ? (Map<String, String>)request.getAttribute("error") : new HashMap<String, String>();
			DeptDTO data = request.getAttribute("data") != null ? (DeptDTO)request.getAttribute("date") : null;
		%>
		--%>
		<form class="small-form" action="./add" method="post"><!-- post 전송주소 localhost:8080/jsp01/dept/add -->
			<div class="input-form wide">
				<label class="input-label">부서ID</label>
				<%--
				<% if(error == null) { %> 
					<input type="text" class="input-text" name="deptId" value="<%=data == null ? "" : data.getDeptId() %>" data-required="부서 ID를 입력하세요."> // value input에 저장한 값을 보존하 수 있도록 해줌
				<!-- <input type="text" class="input-text" name="deptId" value="${requestScope.data.deptId == -1 ? '' : requestScope.data.deptId}" data-required="부서 ID를 입력하세요."> // requestScope : 생략가능, data가 있든 없든(초기상테) 없으면 빈문자열 있으면 입력값 -->
				<% } else { %>
				--%>
				<input type="text" class="input-text" name="deptId" onblur="duplicateCheck(this, '${ajaxDuplicateUrl}');"<%-- onchange : 값이 반드시 바껴야 함, oninput : 입력하는 순간마다 인식(서버 과부하 걸림) --%>
					value="${data.deptId == -1 ? '' : data.deptId}" data-required="부서 ID를 입력하세요."> <!-- data : setAttribute로 전달된 값, this -> js에 element로 전달, this.value하면 value값만 전달 -->
				<%-- <input type="text" class="input-text" name="deptId" value="<%=data.getDeptId() == -1 ? "" : data.getDeptId() %>" data-required="부서 ID를 입력하세요."> --%>
				<!-- <input type="text" class="input-text" name="deptId" value="${param.deptId}" data-required="부서 ID를 입력하세요."> -->
				<%--
				<c:if test="${not empty error.deptId}"> <!-- 참이면 출력, not empty : error가 비어져 있지 않은지 확인 -->
					<label class="input-label-error">${error.deptId}</label> <!-- error.deptId : error.(map의 키) -->
				</c:if>
				--%>
				<label class="input-label-error"></label>
				<%--
				<% if(error.get("deptId") != null) { %>
					<label class="input-label-error"><%= error.get("deptId") %></label>
				<% } %>
				<% } %>
				--%>
			</div>
			<div class="input-form wide">
				<label class="input-label">부서명</label>
				<input type="text" class="input-text" name="deptName" value="${data.deptName}" data-required="부서명을 입력하세요.">
				<label class="input-label-error"></label>
			</div>
			<div class="input-form wide">
				<label class="input-label">관리자ID</label>
				<input type="text" class="input-text" name="mngId" onblur="existsCheck(this, '${ajaxExistsUrl}');"
					value="${data.mngId == -1 ? '' : data.mngId}" data-required="관리자 ID를 입력하세요.">
				<label class="input-label-error"></label>
			</div>
			<div class="input-form wide">
				<label class="input-label">지역ID</label>
				<input type="text" class="input-text" name="locId" onblur="existsCheck(this, '${ajaxExistsUrl}');"
					value="${data.locId == -1 ? '' : data.locId}" data-required="지역 ID를 입력하세요.">
				<label class="input-label-error"></label>
			</div>
			<div class="input-form wide form-right">
				<button class="btn btn-outline btn-ok" type="submit">저장</button>
				<button class="btn btn-outline btn-cancel" type="button" onclick="location.href='<%=request.getContextPath() %>/depts'">취소</button>
			</div>
		</form>
	</section>
</body>
</html>