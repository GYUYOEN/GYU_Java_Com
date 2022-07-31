<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- 제어문 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- 날짜/ 숫자 등의 포멧 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <!-- 주로 문자열과 관련된 함수 -->
<c:url var="cssUrl" value="/static/css" />
<c:url var="jsUrl" value="/static/js" />
<link rel="stylesheet" type="text/css" href="${cssUrl}/default.css">
<link rel="stylesheet" type="text/css" href="${cssUrl}/required.css">
<link rel="stylesheet" type="text/css" href="${cssUrl}/form.css">
<link rel="stylesheet" type="text/css" href="${cssUrl}/navigation.css">
<link rel="stylesheet" type="text/css" href="${cssUrl}/paging.css">
<link rel="stylesheet" type="text/css" href="${cssUrl}/table.css">
<script type="text/javascript" src="${jsUrl}/required.js"></script>
<script type="text/javascript" src="${jsUrl}/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${jsUrl}/default.js"></script>
<!-- 
	Ajax를 활용하면 비동기식 데이터 통신 가능 
	
	여태 동기식 데이터 통신 사용
-->