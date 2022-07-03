<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JSP - Response 객체</title>
</head>
<body>
	<h1>JSP - Response 객체</h1>
	<h2>sendRedirect(String url)</h2>
	<% 
		// response.sendRedirect("./");
	%>
	<p>
		클리이언트에게 다른 주소로 재요청을 하게 만들기 위해 사용하는 기능(HTML의 refresh와 같은 기능)<br>
		url : 이동할 페이지 주소를 적어줌<br>
		콘솔창 -> Network<br>
		HTTP 상테 코드 : 302
	</p>
	<hr>
	<h2>setStatus(int statusCode)</h2> <!-- 내가 원하는 상테코드로 변환 할 수 있는 기능 -->
	<%
		// response.setStatus(201);
		// response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 가급적이면 정해진 함수 사용
	%>
	<p>
		상태 코드 설정 가능 -> 설정된 코드로 동작x<br>
		구글창에 http status code 검색<br>
		2xx : 정상, 3xx : 리다이렉트, 4xx : 요청오류, 5xx : 서버오류 -> 상태코드 확인<br>
		콘솔창 -> Network 상테코드 확인<br>
		창 변화x
	</p>
	<hr>
	<h2>sendError(int statusCode)</h2>
	<%  
		//response.sendError(HttpServletResponse.SC_NOT_FOUND);
		//response.sendError(HttpServletResponse.SC_NOT_FOUND, "잘못된 url 주소로 요청 하였습니다.");
	%>
	<p>
		404 의 경우 sendError로 많이 사용<br>
		창 변화o -> 에러 페이지 뜸<br>
		추가로 문자를 사용해서 메시지를 변경할 수 있음<br><br>
		
		WEB-INF에 문서를 둔 이유 : 보안상의 직접 접근을 막기 위해서<br>
		동적페이지 -> WEB-INF<br>
		정적페이지 -> webapp(html, static, css, js)<br>
	</p>
	<hr>
	<h2>setContentType(String mimeType)</h2>
	<% 
		// response.setContentType("text/javascript"); 
		// response.setContentType("text/html"); 
	%>
	<p>
		브라우저에 전송하는 데이터를 어떤 컨텐츠로 보이게 할 것 인지 알려줌<br><br>
		
		request, response 메서드 전부 servlet 에서도 똑같이 사용 가능
	</p>
</body>
</html>