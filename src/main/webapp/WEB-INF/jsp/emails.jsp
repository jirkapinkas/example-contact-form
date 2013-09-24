<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="layout/taglib.jsp"%>
<jsp:include page="layout/header.jsp">
	<jsp:param name="title" value="Emails" />
	<jsp:param name="page" value="emails"/>
</jsp:include>

<h1>Sent emails</h1>

<table class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th>subject</th>
			<th>from</th>
			<th>to</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${emails}" var="email">
			<tr>
				<td>${email.subject}</td>
				<td>${email.from}</td>
				<td>${email.to}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<jsp:include page="layout/footer.jsp" />