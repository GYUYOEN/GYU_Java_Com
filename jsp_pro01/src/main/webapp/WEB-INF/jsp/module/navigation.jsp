<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String url = ""; 
	// 변수를 사용하고 싶으면 request.getAttribute를 이용하기
	if(request.getAttribute("url") != null) { // servlet에서 url 변수를 가져옴(request.setAttribute("url", ...)에서 ...을 가져옴, ... 은 url 주소임)
		url = (String)request.getAttribute("url");
	}
%>
<header>
	<nav class="top-nav center">
		<ul class="nav">
			<li class="nav-item dropdown<%=url.contains("/jsp_") ? " active" : "" %>"> <!-- url.contains("/jsp_") : /jsp_ 경로가 포함되어 있는지 -->
				<a class="nav-link" href="#">JSP/Servlet</a>
				<ul class="nav dropdown-nav">
					<li class="nav-item">
						<a class="nav-link" href="./jsp_script">스크립트 태그</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="./jsp_request">request 객체</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="./jsp_response">response 객체</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="./jsp_model2">Model2</a>
					</li>
				</ul>
			</li>
			<li class="nav-item<%=url.contains("/emps") ? " active" : "" %>">
				<a class="nav-link" href="./emps">직원</a>
			</li>
			<li class="nav-item<%=url.contains("/depts") ? " active" : "" %>">
				<a class="nav-link" href="./depts">부서</a>
			</li>
			<li class="nav-item<%=url.contains("/locs") ? " active" : "" %>">
				<a class="nav-link" href="./locs">지역</a>
			</li>
			<%--
			<li class="nav-item<%=url.contains("/locs") ? " active" : "" %>">
				<a class="nav-link" href="./locs"><%=request.getParameter("test") %></a>
			</li>
			--%>
		</ul>
	</nav>
</header>