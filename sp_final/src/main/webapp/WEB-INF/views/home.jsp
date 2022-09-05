<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="kor">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Mazer Admin Dashboard</title>

    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="static/css/bootstrap.css">

    <link rel="stylesheet" href="static/vendors/iconly/bold.css">

    <link rel="stylesheet" href="static/vendors/perfect-scrollbar/perfect-scrollbar.css">
    <link rel="stylesheet" href="static/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="static/css/app.css">
    <link rel="icon" href="static/images/logo/favicon.ico">
</head>
<style>
	.chat-container {
		display: flex;
		margin-bottom: 20px;
		width:auto;
		height:800px;
	}
	.chat-left-layout {
		background-color: #ffd;
		min-width: 200px;
		flex-basis: 25%;
	}
	.chat-right-layout {
		background-color: #fff;
		min-width:200px;
		flex-basis: 25%;
	}
	.chat-center-layout {
		background-color: #ffc;
		min-width:400px;
		flex-basis: 50%;
	}
</style>
<body>
    <%@ include file="./module/navigation.jsp" %>
    <div id="app">
        <div id="main">
            <div class="page-heading">
                <h3>Profile Statistics</h3>
            </div>
            <div class="chat-container">
            	<div class="chat-left-layout"></div>
            	<div class="chat-center-layout">
            	</div>
            	<div class="chat-right-layout">
            	</div>
            </div>
            <%@ include file="./module/footer.jsp" %>
        </div>
    </div>
    <script src="static/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="static/js/bootstrap.bundle.min.js"></script>

    <script src="static/vendors/apexcharts/apexcharts.js"></script>
    <script src="static/js/pages/dashboard.js"></script>

    <script src="static/js/main.js"></script>
</body>

</html>

