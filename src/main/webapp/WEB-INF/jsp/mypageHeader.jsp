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

	<c:if test="${not empty account }">
		<a href="Posts" class="a-link">投稿</a><br>
		<a href="AccountDelete" class="a-link">アカウント削除</a>
	</c:if>
	
</body>
</html>