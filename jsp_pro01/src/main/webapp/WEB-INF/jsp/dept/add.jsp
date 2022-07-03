<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dept.model.DeptDTO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 추가</title>
</head>
<body>
	<h1>부서 추가</h1>
	<% 
		String deptId = "";
		String deptName = "";
		String mngId = "";
		String locId = "";
		// 초기값 설정할 떄 에러항목에 대해서는 입력값 없는 상태로 나오게 함
		if(request.getAttribute("error") != null) {
			DeptDTO dto = (DeptDTO) request.getAttribute("error");
			deptId = dto.getDeptId() == -1 ? "" : String.valueOf(dto.getDeptId());
			deptName = dto.getDeptName();
			mngId = dto.getMngId() == -1 ? "" : String.valueOf(dto.getMngId());
			locId = dto.getLocId() == -1 ? "" : String.valueOf(dto.getLocId());
	%>
		<script type="text/javascript">
			alert("<%=request.getAttribute("errorMsg") %>");
		</script>
	<% 
		}
	%>
	<form action="./add" method="post"> <!-- post 전송주소 localhost:8080/jsp01/dept/add -->
		<div>
			<input type="text" name="deptId" value="<%=deptId %>" placeholder="부서 ID"> <!-- value input에 저장한 값을 보존하 수 있도록 해줌 -->
		</div>
		<div>
			<input type="text" name="deptName" value="<%=deptName %>" placeholder="부서 이름">
		</div>
		<div>
			<input type="text" name="mngId" value="<%=mngId %>" placeholder="관리자 ID">
		</div>
		<div>
			<input type="text" name="locId" value="<%=locId %>" placeholder="지역 ID">
		</div>
		<div>
			<button type="submit">저장</button>
		</div>
	</form>
</body>
</html>