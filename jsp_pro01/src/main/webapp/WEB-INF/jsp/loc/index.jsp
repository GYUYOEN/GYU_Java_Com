<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, locs.model.LocsDTO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Location 조회 결과</title>
	<style type="text/css">
		.required-box {
			/* 말풍선 박스 설정 */
			margin: 0; padding: 0.3rem 0.6rem;
			box-sizing: border-box;
			display: inline;
			position: relative;
			border: 1px solid black;
			border-radius: 4px;
			background-color: black;
			color: white;
			box-shadow: 2px 2px 2px gray;
			opacity: 0; /* opacity: 투명도, 0 : 투명 */
			transition: opacity 0.5s;
		}
		.required-box.show {
		
			opacity: 1; /* 1 : 불투명 */
			transition: opacity 0.5s; /* 말풍선 서서히 나오게 표시, 속도 조절 가능 */
		}
		.required-box:after {
			/* input 을 가리키는 말풍선 화살표 설정 */
			content: '';
			position: absolute;
			top: 0; left: 15%; /* 화살표 위치 조정 */
			width: 0; height: 0;
			border: 6px solid transparent;
			/border-bottom-color: black;
			border-top: 0;
			margin-left: -6px; margin-top: -6px;
		}
		/* 테두리 모양을 하기 위해 설정
		.required-box:before { 
			content: '';
			position: absolute;
			top: 0; left: 15%;
			width: 0; height: 0;
			border: 7px solid transparent;
			border-bottom-color: black;
			border-top: 0;
			margin-left: -7px; margin-top: -7px;
		}
		*/
	</style>
</head>
<script type="text/javascript">
window.onload = function() {
	var form = document.forms[0];
	form.addEventListener("submit", formCheck);
}

function formCheck(e) {
	for(element of e.target.querySelectorAll("[data-required]")) {
		if(element.value === "") {
			e.preventDefault();
			if(!document.querySelector(".required-box")) {
				requiredBox(element, element.dataset.required);
			}
			return false;
		}
	}
	this.submit();
}

function requiredBox(element, message) {
	var box = document.createElement("div");
	box.setAttribute("class", "required-box");
	box.innerText = message;
	element.parentElement.append(box);
	
	box.style.left = element.offsetLeft - box.offsetLeft + (element.offsetWidth / 10) + "px";
	box.style.top = element.offsetHeight + 16 + "px";
	box.setAttribute("class", "required-box show");
	
	setTimeout(function() {
		box.remove();
	}, 1500); // box(말풍선) 를 1.5 초 뒤에 제거해라
}
</script>
<body>
	<h1>Location 조회 결과</h1>
	<div>
		<form action="./locs" method="get">
			<div>
				<!-- data-required : box 에 나오는 텍스트 내용 입력 -> 없으면 동작안하도록 함 -->
				<input type="text" name="search" data-required="지역코드를 입력하세요.">
				<button type="submit">조회</button>
			</div>
		</form>
	</div>
	<table>
		<tr>
			<th>Location_Id</th>
			<th>Street_Adress</th>
			<th>Postal_Code</th>
			<th>City</th>
			<th>State_Province</th>
			<th>Country_Id</th>
		</tr>
	<% 
		if(request.getAttribute("locsDatas") != null) {
			List<LocsDTO> datas = (List<LocsDTO>) request.getAttribute("locsDatas");
			for(LocsDTO data: datas) {
	%>
		<tr>
			<th><%= data.getLocId() %></th>
			<th><%= data.getStreetAdd() %></th>
			<th><%= data.getPostCode() %></th>
			<th><%= data.getCity() %></th>
			<th><%= data.getStateProv() %></th>
			<th><%= data.getCtyId() %></th>
		</tr>
	<% 
			}
		}
	%>
	</table>
</body>
</html>