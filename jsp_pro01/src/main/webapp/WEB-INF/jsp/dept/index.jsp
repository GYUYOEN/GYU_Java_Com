<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, dept.model.DeptDTO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 조회 결과</title>
</head>
<script type="text/javascript"> // 빈 문자열을 조회하면 반응하지 않게 하기 위한 로직
	// 보통 view와 controller 양쪽에서 전부 로직을 짜서 체크하는데 안전
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
		<button type="button" onclick="location.href='./depts/add'">추가</button> <!-- './depts/add' : 추가기능 url 주소 -->
	</div>
	<div>
		<form action="./depts" method="get"> <!-- get 으로 보내서 doGet 안에서 추출 -->
			<div>
				<input type="text" name="search"> <!-- search 라는 parameter name으로 사용자가 입력한 값을 ./depts(controller) 에다가 요청 -->
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
			<th></th>
		</tr>
	<%
		// controller에서 설정하여 전달된 객체 불러오기 -> request.getAttribute("deptDatas")
		if(request.getAttribute("deptDatas") != null) {
			List<DeptDTO> datas = (List<DeptDTO>) request.getAttribute("deptDatas"); // 다운캐스팅
			for(DeptDTO data: datas) {
	%>
		<tr>
			<td><%= data.getDeptId() %></td>
			<td><%= data.getDeptName() %></td>
			<td><%= data.getMngId() %></td>
			<td><a href="./locs?search=<%= data.getLocId() %>"><%=data.getLocId()%></a></td><!-- 부서에서 locId 를 클릭하면 지역이 나오도록 함  -->
			<td>
				<button type="button" onclick="location.href='./depts/mod?id=<%=data.getDeptId() %>'">수정</button> <!-- location.href : 해당 url 주소로 이동 -->
				<button type="button" onclick="location.href='./depts/del?id=<%=data.getDeptId() %>'">삭제</button>
			</td>
		</tr>
	<%
			}
		}
	%>
	</table>
	<div>
		<ul>
			<li><a href="">Prev</a></li>
			<%
				if(request.getAttribute("pageList") != null) {
					List<Integer> pageList = (List<Integer>) request.getAttribute("pageList");
					for(Integer n: pageList) {
					
			%>
						<li><a href="./depts?page=<%=n %>"><%=n %></a></li>
			<%
					}
				}
			%>
			<li><a href="">Next</a></li>
		</ul>
	</div>
</body>
</html>