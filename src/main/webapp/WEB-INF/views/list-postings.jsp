<%@page import="java.util.List"%>
<%@page import="spr.board.model.PostVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>테스트 게시판</title>
<jsp:include page="/WEB-INF/views/include/common-js.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<c:forEach var="pos" items="${allPosts }">
<li>
	<span>[${pos.writer.nickName}]</span>
	<span><a href="${ctxPath }/postings/${pos.seq}">${pos.title }</a></span>
</c:forEach>
</body>
</html>