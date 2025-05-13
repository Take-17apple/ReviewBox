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

	<form action="Register" method="post" class="login-form">
		<label for="userName">ユーザーネーム</label>
			<input type="text" id="userName" name="name"><br>
		<label for="password">パスワード</label>
			<input type="password" id="password" name="pass"><br>
		<button type="submit">登録</button><br>
	</form>
	
</body>
</html>