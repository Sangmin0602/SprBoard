<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Demo 게시판</title>
<jsp:include page="/WEB-INF/views/include/common-js.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<h3>게시판 메인입니다.</h3>
</body>
</html>