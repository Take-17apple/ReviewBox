<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<title>ReviewBox</title>
</head>
<body>
<%@ include file="header.jsp" %>
<%@ include file="mypageHeader.jsp" %>
<%@ include file="headerMsg.jsp" %>

	<c:if test="${not empty myWorkList }">
		<table class="work-list-table">
			<tr>
				<th>作品名</th><th>作者名</th><th>価格</th>
			</tr>
			<c:forEach var="work" items="${myWorkList }">
			<tr>
				<td><c:out value="${work.title }"></c:out></td>
				<td><c:out value="${work.creator }"></c:out></td>
				<td><c:out value="${work.price }"></c:out></td>
			</tr>
			</c:forEach>
		</table>
	</c:if>
	
</body>
</html>