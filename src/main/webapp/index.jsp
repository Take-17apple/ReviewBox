<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="java.util.List, logic.WorkLogic, model.Work" %>
<%
	List<Work> workList = new WorkLogic().workList();
	if (workList.isEmpty()){
		request.setAttribute("errorMsg", "現在投稿している作品はありません");
	}
	request.setAttribute("workList", workList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<title>ReviewBox</title>
</head>
<body>
<%@ include file="WEB-INF/jsp/header.jsp" %>
<%@ include file="WEB-INF/jsp/headerMsg.jsp" %>

	<c:if test="${not empty workList }">
		<table class="work-list-table">
			<tr>
				<th>作品名</th><th>作者名</th><th>価格</th>
			</tr>
			<c:forEach var="work" items="${workList }">
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