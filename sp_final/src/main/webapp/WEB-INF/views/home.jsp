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
		background-color: #fff;
		min-width: 200px;
		flex-basis: 25%;
		margin: 0 10px 0 0;
		border-radius: 10px;
	}
	.chat-right-layout {
		background-color: #fff;
		min-width:200px;
		flex-basis: 25%;
		border-radius: 10px;
	}
	.chat-center-layout {
		background-color: #ffc;
		min-width:400px;
		flex-basis: 50%;
		margin: 0 10px 0 0;
		border-radius: 10px;
	}
	.card-body {
		height: 623px;
	}
	.submit-btn {
		background-color: #fff;
		width: 15%;
		border: 1px solid #dce7f1;
		border-radius: .25rem;
		margin: 0 0 0 5px;
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
	                <section class="section">
		                <div class="card">
		                    <div class="card-header">
		                        <div class="media d-flex align-items-center">
		                            <div class="avatar me-3">
		                                <img src="static/images/faces/1.jpg" alt="" srcset="">
		                                <span class="avatar-status bg-success"></span>
		                            </div>
		                            <div class="name flex-grow-1">
		                                <h6 class="mb-0">Fred</h6>
		                                <span class="text-xs">Online</span>
		                            </div>
		                            <button class="btn btn-sm">
		                                <i data-feather="x"></i>
		                            </button>
		                        </div>
		                    </div>
		                    <div class="card-body pt-4 bg-grey">
		                        <div class="chat-content">
		                            <div class="chat">
		                                <div class="chat-body">
		                                    <div class="chat-message">Hi Alfy, how can i help you?</div>
		                                </div>
		                            </div>
		                            <div class="chat chat-left">
		                                <div class="chat-body">
		                                    <div class="chat-message">I'm looking for the best admin dashboard
		                                        template</div>
		                                    <div class="chat-message">With bootstrap certainly</div>
		                                </div>
		                            </div>
		                            <div class="chat">
		                                <div class="chat-body">
		                                    <div class="chat-message">I recommend you to use Mazer Dashboard</div>
		                                </div>
		                            </div>
		                            <div class="chat chat-left">
		                                <div class="chat-body">
		                                    <div class="chat-message">That"s great! I like it so much :)</div>
		                                </div>
		                            </div>
		                        </div>
		                    </div>
		                    <div class="card-footer">
		                        <div class="message-form d-flex flex-direction-column align-items-center">
		                            <a href="http://" class="black"><i data-feather="smile"></i></a>
		                            <div class="d-flex flex-grow-1 ml-4">
		                                <input type="text" class="form-control" placeholder="Type your message..">
		                                <button type="button" class="submit-btn">전송</button>
		                            </div>
		                        </div>
		                    </div>
		                </div>
	          		</section>
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

