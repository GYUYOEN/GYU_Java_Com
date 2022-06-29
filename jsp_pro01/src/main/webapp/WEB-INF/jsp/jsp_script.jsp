<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Random" %>
<%!
	// 자바주석
	public Random rd = new Random();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP - Script</title>
</head>
<body>
	<%-- 
	<%@		%> 지시자    : import
	<%!		%> 선언문    : 멤버변수 / 함수
	<%=		%> 표현식    : 값 출력
	<%		%> 스크립트릿 : 지역변수 / 로직
	--%>
	<%-- 주석 --%>
	<!-- HTML 주석 -->
	<h1>JSP - Script</h1>
	<!-- 
	workspace >>> .metadata >>> ...
	JSP파일 >>> java 파일로 변환 >>> class 파일 >>> 실행 >>> 시행 결과는 HTML 
	JSP파일이 한번 만들어 지면 다음 부터는 바로 실행으로 넘어감
	-->
	<ul>
	<%
		// 자바주석
		for(int i = 0; i < 6; i++) {
			int number = rd.nextInt(100);
			// out.println(number); // 출력
	%>
			<li><%=number %></li> <!-- = out.println(number) -->
	<%
		}
	%>
	</ul>
	<div>
		<input type="text" value="<%=rd.nextInt(100) %>">
	</div>
</body>
</html>