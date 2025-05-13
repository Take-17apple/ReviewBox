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
<%@ include file="headerMsg.jsp" %>

	<form action="AccountDelete" method="post" class="delete-form">
		<button type="submit">アカウント削除する</button>
	</form>
	
</body>
</html>