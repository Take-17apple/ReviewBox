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

	<c:choose>
		<c:when test="${not empty account }">
			<a href="Main" class="a-link">メインページ</a><a href="Mypage" class="a-link">マイページ</a><a href="Logout" class="a-link">ログアウト</a>
		</c:when>
		<c:otherwise>
			<a href="Top" class="a-link">トップ</a><a href="Register" class="a-link">新規登録</a><a href="Login" class="a-link">ログイン</a>
		</c:otherwise>
	</c:choose>
	<br>
	
</body>
</html>