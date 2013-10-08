<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${param.title}</title>
<link rel="stylesheet" href="resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="resources/css/signin.css" />
<!-- JQuery -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery-2.0.3.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-2.0.3.min.map" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</head>
<body>

	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<c:url var="homeUrl" value="/index.jsp" />
				<a class="navbar-brand" href="${homeUrl}">Mailer</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li ${param.page == 'index' ? 'class="active"' : ''}><a
						href="${homeUrl}">Send email</a></li>
					<c:url var="listEmailsUrl" value="/list-emails.html" />
					<li ${param.page == 'emails' ? 'class="active"' : ''}><a
						href="${listEmailsUrl}">List emails</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
	<br />
	<div class="container">