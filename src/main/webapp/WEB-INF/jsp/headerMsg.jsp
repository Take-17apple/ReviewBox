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

	<%-- 処理成功時、失敗時に出力するメッセージ --%>
	<c:if test="${not empty successMsg }">
		<c:out value="${successMsg }"></c:out>
	</c:if>
	<c:if test="${not empty errorMsg }">
		<c:out value="${errorMsg }"></c:out>
	</c:if>
	<%-- 登録、ログイン成功時に出力 --%>
	<c:if test="${not empty mainMsg }">
		<c:out value="${mainMsg }"></c:out><br>
		<p>ようこそ＜<c:out value="${account.name }" />＞さん</p>
	</c:if>
	<br>
	
</body>
</html>