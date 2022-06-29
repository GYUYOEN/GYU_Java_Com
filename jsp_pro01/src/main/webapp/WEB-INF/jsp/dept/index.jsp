<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, dept.model.DeptDTO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 조회 결과</title>
</head>
<script type="text/javascript">
	window.onload = function() {
		// form 은 getElement... 사용할 필요 없음
		var form = document.forms[0];
		form.addEventListener("submit", formCheck);
	}
	
	function formCheck(e) {
		if(this.search.value === "") {
			e.preventDefault();
		} else {
			this.submit(); // form을 전송
		}
	}
</script>
<body>
	<h1>부서 조회 결과</h1>
	<div>
		<form action="./depts" method="get">
			<div>
				<input type="text" name="search">
				<button type="submit">조회</button>
			</div>
		</form>
	</div>
	<table>
		<tr>
			<th>DeptId</th>
			<th>DeptName</th>
			<th>MngId</th>
			<th>LocId</th>
		</tr>
	<%
		if(request.getAttribute("deptDatas") != null) {
			List<DeptDTO> datas = (List<DeptDTO>) request.getAttribute("deptDatas"); // 다운캐스팅
			for(DeptDTO data: datas) {
	%>
		<tr>
			<th><%= data.getDeptId() %></th>
			<th><%= data.getDeptName() %></th>
			<th><%= data.getMngId() %></th>
			<th><a href="./locs?search=<%= data.getLocId() %>"><%=data.getLocId()%></a></th>
		</tr>
	<%
			}
		}
	%>
	</table>
</body>
</html>