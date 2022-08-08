<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:url var="bs5CssUrl" value="/static/bs5/css" />
<c:url var="bs5JsUrl" value="/static/bs5/js" />
<c:url var="jsUrl" value="/static/js" />
<link rel="stylesheet" type="text/css" href="${bs5CssUrl}/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
<script type="text/javascript" src="${jsUrl}/default.js"></script>
<script type="text/javascript" src="${jsUrl}/required.js"></script>
<script type="text/javascript" src="${jsUrl}/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${bs5JsUrl}/bootstrap.min.js"></script>