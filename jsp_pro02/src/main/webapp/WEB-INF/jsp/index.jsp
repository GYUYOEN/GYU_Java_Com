<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>Welcome JSP/Servlet</title>
	<%@ include file="./module/head.jsp" %>
</head>
<script type="text/javascript">
	function sendAjax() {
		$.ajax({
			type: "get",
			url: "/ajax/test",
			data: {
				x: 1, y: "A"
			},
			dataType: "json",
			success: function(data, status) {
				// 응답이 성공(응답코드 200)적으로 이루어 졌을 때 동작할 함수
				console.log("success: " + data);
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
				// 응답코드 200 이 아닌 모든 응답일 때 동작할 함수
				console.log(data);
				console.log(status);
			},
			complete: function() {
				// 성공/실패 유무와 상관없이 동작할 함수
				console.log("complete 무조건 실행");
			},
			beforeSend: function() {
				// 서버에 데이터를 전송하기 전에 동작할 함수
				console.log("beforeSend 데이터 전송 전");
			}
		});
	}
</script>
<body>
	<%@ include file="./module/navigation.jsp" %>
	<section class="container">
		<div>
			<button type="button" onclick="sendAjax()">전송</button>
		</div>
	</section>
</body>
</html>












