<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JSP/Servlet - Model2</title>
</head>
<body>
	<h1>JSP/Servlet - Model2</h1>
	<h2>Model 1</h2>
	<p>
		고전적 웹 서비스 모델 방식으로 하나의 JSP에 화면 구성, 비지니스 로직, 데이터베이스 연결 처리를 구성하여 운용하는<br>
		형태의 모델 방식이다.
		
		개인이 서비스 개발하는 경우에는 편한 방식이다. 단, 시간이 흘러 유지보수가 자주 이루어진 경우 자신도 이전에 어떤 방식으로<br>
		코드를 작성하였는지 등의 관리가 잘 안된다.<br>
		하나의 페이지에 HTML, CSS, JS, JSP, Java, SQL 구문들이 섞여 있기 때문에 코드의 스파게티화가 발생하게 된다.<br>
		협업은 생각할 수도 없다.
	</p>
	<hr>
	<h2>Model 2 - MVC 패턴</h2>
	<p>
		Model 1 에서 기능별로 코드를 분리하여 협업도 가능하게 만들고 기능도 분리가 되여 특정 기능에 대한 추가/수정도 편하게<br>
		이루어질 수 있도록 구성되었다.
	</p>
	<ul>
		<li>Model : 사용자에게 필요한 정보를 처리하기 위한 비지니스 로직의 역할</li>
		<li>View : 사용자에게 정보를 보여주기 위한 화면을 구성하는 역할</li>
		<li>Controller : 모델과 뷰 사이에 어떤 동작이 있을 때 중간에서 조정을 하는 역할</li>
	</ul>
	<hr>
	<h2>MVC 패턴으로 DEPARTMENTS 테이블 정보 조회하기</h2>
	<ol>
		<li>/jsp01/depts 주소로 요청을 하는 경우 dept.controller.DeptController 가 동작하게 한다.</li>
		<li>DeptController 에서는 dept.service.DeptService 를 사용하여 전체 부서를 조회하도록 요청하게 한다.</li>
		<li>DeptService 에서는 dept.model.DeptDAO 를 사용하여 데이터베이스 조회 요청을 한다.</li>
		<li>DeptDAO 에서는 마이바티스 커넥션 객체를 사용하여 실질적인 조회를 수행한다.</li>
		<li>모든 처리 결과는 요청의 역순으로 결과값을 리턴하여 DeptController 까지 전달 한다.</li>
		<li>처리 결과를 받은 DeptController 는 결과에 적합한 View 를 선정하여 화면 구성을 할 수 있게 데이터를 View에 전달한다.</li>
		<li>View 는 DeptController 로 부터 전달 받은 데이터를 사용하여 화면을 구성한다.</li>
		<li>모든 작업이 완료 되면 완성된 HTML 코드를 클라이언트에게 응답 메시지로 전송 한다.</li>
	</ol>
	<button type="button" onclick="location.href='/jsp01/depts'">부서 조회</button>
	<button type="button" onclick="location.href='/jsp01/locs'">Location 조회</button>
</body>
</html>