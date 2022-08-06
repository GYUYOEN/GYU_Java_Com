<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, locs.model.LocsDTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>지역 조회 결과</title>
	<%@ include file="../module/head.jsp" %>
	<style type="text/css">
		.required-box {
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
		
			opacity: 1; /* 투명도 -> 0 : 투명 , 1 : 불투명 (0 -> 1 전환이 될때 1s 라는 지속 시간동안 서서히 전환 ) */
			transition: opacity 0.5s; /* 말풍선 서서히 나오게 표시, 속도 조절 가능 */
		}
		.required-box:after {
			/* input 을 가리키는 말풍선 화살표 설정 */
			content: '';
			position: absolute;
			top: 0; left: 15%; /* 화살표 위치 조정(박스 전체 크기의 15% 차지) */
			width: 0; height: 0;
			border: 6px solid transparent; /* 크기 조정시 화실표 모양의 크기가 바뀜 */
			border-bottom-color: black; /* 화살표 색상 */
			border-top: 0;
			margin-left: -6px; margin-top: -6px; /* 크기 조정시 화실표 모양의 크기가 바뀜 */
		}
	</style>
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<section class="container">
		<div>
			<form action="./locs" method="get">
				<div class="input-form form-left">
					<button class="btn btn-outline" type="button" onclick="location.href='./locs/add'">추가</button>
				</div>
				<div class="input-form form-left">
					<input class="input-text" type="text" name="search" data-required="지역코드를 입력하세요.">
					<button class="btn btn-outline" type="submit">조회</button>
				</div>
			</form>
		</div>
		<table class="table wide vertical-hidden hover">
			<colgroup>
				<col class="col-120">
				<col class="col-auto">
				<col class="col-120">
				<col class="col-120">
				<col class="col-120">
				<col class="col-120">
				<col class="col-120">
			</colgroup>
			<thead>
				<tr>
					<th>지역코드</th>
					<th>주소</th>
					<th>우편번호</th>
					<th>도시명</th>
					<th>주</th>
					<th>국가코드</th>
					<th class="border-hidden-right"></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty locsDatas}">
					<c:forEach items="${locsDatas}" var="data">
						<tr>
							<td>${data.locId}</td>
							<td>${data.streetAdd}</td>
							<td>${data.postCode}</td>
							<td>${data.city}</td>
							<td>${data.stateProv}</td>
							<td>${data.ctyId}</td>
							<td>
								<c:url var="modUrl" value="./locs/mod">
									<c:param name="id" value="${data.locId}" />
								</c:url>
								<button type="button" class="btn btn-icon" onclick="location.href='${modUrl}'">
									<span class="material-symbols-outlined">edit</span>
								</button>
								<c:url var="delUrl" value="./locs/del">
									<c:param name="id" value="${data.locId}" />
								</c:url>
								<button type="button" class="btn btn-icon" onclick="location.href='${delUrl}'">
									<span class="material-symbols-outlined">delete</span>
								</button>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</section>
</body>
</html>