<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
    <!-- 구글검색창에 'maven jstl 1.2' 첫번째 링크에 jar 다운 -->
    <!-- jstl 은 다음 태그들이 있어야 사용할 수 있음, EL은 없어도 사용가능 -->
    <!-- el은 로직에는 사용할 수 없고 값 출력하는 용도로만 사용가능 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- 제어문 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- 날짜/ 숫자 등의 포멧 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <!-- 주로 문자열과 관련된 함수 -->
<!DOCTYPE html>
<%-- 메인 화면 --%>
<html>
<head>
	<meta charset="UTF-8">
	<title>Welcome JSP/Servlet</title>
	<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/default.css"> --%>
	<%--
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/default.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/navigation.css">
	--%>
	<%@ include file="./module/head.jsp" %>
</head>
<script type="text/javascript">
	// ajax를 사용하면 페이지 전환을 하지 않아도 화면 변경이 가능(JS 많이 필요), 비동기식
	function sendAjax() {
		$.ajax({
			// 자바스크립트의 오브젝트 형식으로 만들어짐
			type: "get", // method 방식
			url: "/ajax/test", // Ajax를 처리할 서버 주소
			data: {
				x: 1, y: "A" // 서버에 전달할 데이터 작성(controller에 parameter로 받음)
			}, 
			dataType: "json", // 서버로 부터 전달 받을 데이터 타입(json, text, xml, html, ...), 데이터 타입 형식에 안맞춰주면 에러
			success: function(data, status) { // 받을 데이터 인자 필요
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
				// 응답코드 200이 아닌 모든 응답일 때 동작할 함수
				// console.log("error 발생");
				console.log(data);
				console.log(status);
			},
			complete: function() {
				// 성공/실패 유무와 상관없이 동작할 함수
				console.log("complete 무조건 실행");
			},
			beforeSend: function() {
				// 서버에 데이터를 전송하기 전에 동작할 함수
				console.log("beforeSend 데이터 전송 전")
			}
		});
	}
</script>
<body>
	<%--
	<jsp:include page="./module/navigation.jsp" />  jsp:include 액션 태그 : 실행된 결과 코드를 삽입(navigation을 실행 후 삽입). 다른 서버에서 실행힌 결과를 받아올 수 있다.(동적 페이지에 사용됨)
		<jsp:param name="test" value="Hello"/>  parameter 형식으로 전달 (내부에 다른 서버에 요청) 
	</jsp:include>
	--%>
	<%@ include file="./module/navigation.jsp" %> <!-- include 지시자 : 해당파일의 코드를 사입 후 navigation이랑 같이 실행(정적 패아지에 사용됨). 내부 서버에서 사용됨(내부에 jsp 파일이 만들어져 있어야함) -->
	<section class="container">
		<div>
			<button type="button" onclick="sendAjax()">전송</button>
		</div>
	</section>
	<%--
	<!-- url 주소에 localhost:8080/?x=c 작성 -->
	<c:choose>
		<c:when test="${param.x == 'a'}">
			파라미터 x의 값이 a 면 실행
		</c:when>
		<c:when test="${param.x == 'b'}">
			파라미터 x의 값이 b 면 실행
		</c:when>
		<c:when test="${param.x == 'c'}">
			파라미터 x의 값이 c 면 실행
		</c:when>
		<c:otherwise> <-- else -->
			모든 when 조건에 해당하지 않으면 실행됨
		</c:otherwise>
	</c:choose>
	
	<hr>
	
	<%
		List<String> lst = new ArrayList<String>();
		lst.add("a"); lst.add("b"); lst.add("c"); lst.add("d");
		request.setAttribute("lst", lst);
	%>
	<ul>
		<!-- li 태그 반복 -> 반복문 -->
		<c:forEach begin="1" end="5" var="v"> <!-- var : 변수명 선언, 1에서 5까지 반복 -->
			<li>${v}</li>
		</c:forEach>
	</ul>
	<br>
	<ul>
		<!-- collection을 이용한 li 태그 반복 -> 반복문 -->
		<c:forEach items="${lst}" var="v">
			<li>${v}</li>
		</c:forEach>
	</ul>
	<br>
	<%
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "가"); map.put("b", "나"); map.put("b", "다");
		request.setAttribute("map", map);
	%>
	<ul>
		<c:forEach items="${map}" var="v">
			<li>${v.key} - ${v.value}</li>
		</c:forEach>
	</ul>
	--%>
	<%--
	<%
		request.setAttribute("data", "Hello");
	%>
	${data}
	<!-- 왠만하면 아래 형태 사용 -->
	<!-- 변수명이 같을때 범위 접근 방식 page -> application (범위가 넓어지는 형태) -->
	<!-- jsp 한페이지 영역에서 사용 (지역변수) -->
	<c:set var="data" value="Hello1" scope="page"/>
	<!-- 하나의 요청 영역(요청 흐름)에서 사용 가능 (첫번째 요청과 두번째 요청 공유x) / 사용자의 요청에 대하 응답을 하기전까지 모든영역에서 사용할 수 있는 범위를 가진다.(HttpRequest, HttpServletRequest() )-->
	<c:set var="data" value="Hello2" scope="request"/>
	<!-- 하나의 세션 영역 (첫번째 요청과 두번째 요청 공유o (요청간의 공유o, 사용자) / 사용자 또는 브라우저에 부여한 세션이 유효할 때 까지 모든 요청 -->
	<c:set var="data" value="Hello3" scope="session"/> 
	<!-- 
		하나의 tomcat process 안에서 공유 / WAS 어플리케이션에서 사용되며, 해당 어플리케이션이 동작 중이면 언제든 사용가능하다(전역 변수) 
		request.getServletContext()를 사용하여 ServletContext 객체를 반환 받아 사용한다.
		.setAttribute("속성명", "속성값")
		.getAttribute("속성명")
	-->
	<c:set var="data" value="Hello4" scope="application"/> 
	${pageScope.data}<br> <!-- 특정 data를 불러오고 싶을때 -->
	${requestScope.data} <br>
	${sessionScope.data} <br>
	${applicationscope.data} <br>
	
	<c:set var="arr">
		가, 나, 다, 라
	</c:set>
	${arr}<br>
	
	<!-- 제거 -->
	<c:remove var="data" scope="page"/>
	<c:remove var="data" scope="request"/>
	<c:remove var="data" scope="session"/>
	<c:remove var="data" scope="application"/>
	${pageScope.data}<br>
	${requestScope.data} <br>
	${sessionScope.data} <br>
	${applicationscope.data} <br>
	
	<hr>
	
	<!-- url 만들어줌  -->
	<c:url var="url" value="./depts">
		<c:param name="x" value="Hello"/> <!-- parameter 설정이 필요하면 사용 -->
		<c:param name="y" value="Hello"/>
	</c:url>
	${url}
	--%>
	<%--
	<fmt:formatNumber value="1000" /><br> <!-- 1,000 으로 출력 -->
	<fmt:formatNumber value="0.1" type="percent"/><br> <!-- 10% 로 출력 -->
	<fmt:formatNumber value="1000" type="currency"/><br> <!-- ₩1,000 로 출력 -->
	<fmt:formatNumber value="1000" type="currency" currencySymbol="$"/><br> <!-- $1,000 출력 -->
	
	<hr>
	
	<% 
		Date date = new Date(); 
		request.setAttribute("date", date);
	%>
	<fmt:formatDate value="${date}" type="date" /><br>
	<fmt:formatDate value="${date}" type="date" dateStyle="full" /><br>
	<fmt:formatDate value="${date}" type="date" dateStyle="long"/><br>
	<fmt:formatDate value="${date}" type="date" dateStyle="medium"/><br>
	<fmt:formatDate value="${date}" type="date" dateStyle="short"/><br>
	<fmt:formatDate value="${date}" type="date" pattern="YYYY-MM-dd E EEEE"/><br>
	
	<hr>
	
	<fmt:formatDate value="${date}" type="time"/> <br>
	<fmt:formatDate value="${date}" type="time" timeStyle="full"/> <br>
	<fmt:formatDate value="${date}" type="time" timeStyle="long"/> <br>
	<fmt:formatDate value="${date}" type="time" timeStyle="medium"/> <br>
	<fmt:formatDate value="${date}" type="time" timeStyle="short"/> <br>
	<fmt:formatDate value="${date}" type="time" pattern="a hh:mm:ss.SSS"/> <br>
	<fmt:formatDate value="${date}" type="time" pattern="HH:mm:ss Z z"/> <br>
	<fmt:formatDate value="${date}" type="time" pattern="HH:mm:ss Z z zzzz"/> <br>
	
	<hr>
	
	<fmt:formatDate value="${date}" type="both" /><br>
	<fmt:formatDate value="${date}" type="both" dateStyle="full" timeStyle="short" /><br>
	--%>
</body>
</html>