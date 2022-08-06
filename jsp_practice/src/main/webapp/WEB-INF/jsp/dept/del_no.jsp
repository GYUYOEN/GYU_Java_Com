<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>삭제 확인</title>
</head>
<body>
	<!-- 
		화면이 전체적으로 바뀔 때 -> 새로운 jsp 생성 
		회원용, 게스트용 과 같이 특정 일부분만 안보이게 할려고 햘 때 -> jsp에서 로직짬
		로직이 복작해지면 새로운 jsp 생성해도 됨
	-->
	<p>삭제 대상 데이터가 존재하지 않습니다.</p>
	<div>
		<button type="button" onclick="history.back();">돌아가기</button>
	</div>
</body>
</html>