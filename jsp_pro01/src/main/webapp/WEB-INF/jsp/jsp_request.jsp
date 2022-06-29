<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JSP - request 객체</title>
</head>
<body>
	<h1>JSP - request 객체</h1>
	<h2>getMethod()</h2> <!-- form이 필요 -->
	<p>
		request.getMethod() : <%=request.getMethod() %><br>
		<br>
		method가 get인지 post인지 알려줌 -> 이미 servlet에서 구분 해줌 (getMethod 할 필요x)
	</p>
	<form action="./jsp_request" method="get">
		<button type="submit">GET 전송</button>
	</form>
	<form action="./jsp_request" method="post">
		<button type="submit">POST 전송</button>
	</form>
	<hr>
	<h2>getParameter(name)</h2>
	<p>
		request.getParameter(name) : <%=request.getParameter("param_name") %><br>
		<br>
		param_name은 input 태그 등에 사용하는 name 속성의 값을 지칭한다.
	</p>
	<form action="./jsp_request" method="get">
		<div>
			<input type="text" name="param_name">
		</div>
		<div>
			<button type="submit">전송</button>
		</div>
	</form>
	<hr>
	<h2>getParameterValues(name)</h2>
	<p>
		request.getParameterValues(name) :
		<%
			if(request.getParameterValues("param_chk") != null) {
		%>
			<%=Arrays.asList(request.getParameterValues("param_chk")) %><br>
		<% 
			}
		%><br>
		<br>
		name(param_chk)은 input 태그 등에 사용하는 name 속성의 값을 지칭한다.<br>
		getParameterValues()는 배열로 쓰인다. -> request.getParameterValues("param_name")[0]<br>
		checkbox에 많이 쓰임
	</p>
	<form action="./jsp_request" method="get">
		<div>
			<input type="checkbox" value="a" name="param_chk">
			<input type="checkbox" value="b" name="param_chk">
			<input type="checkbox" value="c" name="param_chk">
			<input type="checkbox" value="d" name="param_chk">
		</div>
		<div>
			<button type="submit">전송</button>
		</div>
	</form>
	<hr>
	<h2>getParameterNames()</h2>
	<p>
		request.getParameterNames() :
		<%
			Iterator<String> iter = request.getParameterNames().asIterator();
			while(iter.hasNext()) {
		%>
			<%=iter.next() %>
		<% } %><br>
		<br>
		name은 input 태그 등에 사용하는 name 속성의 값을 반환한다.<br>
		collection 사용
	</p>
	<form action="./jsp_request" method="get">
		<div>
			<input type="text" name="username">
			<input type="text" name="password">
		</div>
		<div>
			<button type="submit">전송</button>
		</div>
	</form>
	<hr>
	<h2>setCharaterEncoding</h2> <!-- 한글에 대한 인코딩 표현이 안될때 사용 -->
	<p>
		request.getParameter("username") : 
		<%= request.getParameter("username") %><br>
		<br>
		request.setCharacterEncoding : 클라이언트에서 서버로 전송한 한글 데이터에 문제가 발생했을 때<br>
		servlet 의 가장 첫번째 줄에 적용을 하거나 Filter 를 만들어 적용할 것. <br>
		일일이 servlet 각 메서드에 작성해주기 귀찮음으로 web.xml 에서 filter 태그를 작성하고 filter servlet을 따로 만드는 작업해줌
	</p>
	<form action="./jsp_request" method="post">
		<div>
			<input type="text" name="username">
		</div>
		<div>
			<button type="submit">전송</button>
		</div>
	</form>
	<hr>
	<h2>getSession()</h2> <!-- 구글이나 네이버 로그인 상테 유지 (세션/쿠키) -> 세션 정보추출(서버가 저장해놓은 쿠키 사용(유통기한 있음)) -->
	<p>
		세션객체 : <%= request.getSession() %><br>
		세션ID : <%= request.getSession().getId() %><br>
		<br>
		여기서 세션 ID 는 console 창 >>> Application >> Cookies 에서의 Value 값 (세션 정보 확인 가능)<br>
		서버에서 이 세션 ID 를 생성하고 클라이언트에 저장/유지, 클라이언트가 서버에 이 세션 ID 를 보내면 서버에서 사용자 정보를 확인해서 그에 맞는 페이지를 보내줌<br>
		만료일이 있는데 만료일 지나면 세션 정보는 사라짐<br>
		로그인 세션 정보와 같은 기능을 수행하기 위해 사용하는 객체
	</p>
</body>
</html>