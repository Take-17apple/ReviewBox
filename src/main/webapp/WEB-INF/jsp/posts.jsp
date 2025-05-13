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

	<h5>作品を投稿する</h5>
	<form action="Posts?accountId=${account.accountId }" class="post-form" method="post">
		<label for="title">タイトル</label>
			<input type="text" id="title" name="title"/><br>
		<label for="creator">作者名</label>
			<input type="text" id="creator" name="creator"/><br>
		<label for="genre">ジャンル</label>
			<select id="genre" name="genre">
				<option value="選択してください">選択してください</option>
				<option value="movie">映画</option>
				<option value="book">本</option>
			</select><br>
		<label for="price">価格</label>
			<input type="text" id="price" name="price" placeholder="金額を入力してください" /><br>
		<label for="plot">内容</label>
			<textarea id="plot" name="plot" rows="3" cols="20" placeholder="内容を入力してください"></textarea><br>
		<label for="rating">評価</label>
			<input type="radio" id="rating" name="rating" value="5">A
			<input type="radio" id="rating" name="rating" value="3">B
			<input type="radio" id="rating" name="rating" value="2">C<br>
		<button type="submit">投稿</button><br>
	</form>
	
	<c:if test="${not empty myPostList }">
		<table class="post-table">
			<tr>
				<th>作品名</th><th>作者名</th><th>ジャンル</th><th>価格</th><th>内容</th><th>評価</th>
			</tr>
			<c:forEach var="work" items="${myPostList }">
			<tr>
				<td><c:out value="${work.title }" ></c:out></td>
				<td><c:out value="${work.creator }"></c:out></td>
				<td><c:out value="${work.genre }"></c:out></td>
				<td><c:out value="${work.price }"></c:out></td>
				<td><c:out value="${work.plot }"></c:out></td>
				<td><c:out value="${work.rating }"></c:out></td>
			</tr>
			</c:forEach>
		</table>
	</c:if>
	
</body>
</html>