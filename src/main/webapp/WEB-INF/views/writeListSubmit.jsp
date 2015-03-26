<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>
<%@page import="spr.board.model.PhoneVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach var="phoneVO" items="${article}">
		<input type="text" name="name" placeholder="폰 이름" 
		required="required" readonly="readonly" size="50" value="${ phoneVO.name }"><br>
		
		<input type="text" name="manufacturer" 
		placeholder="폰 제조사" required="required" readonly="readonly" size="50" value="${ phoneVO.manufacturer }"><br>
		
		<input type="text" name="price" 
		placeholder="폰 가격" required="required" readonly="readonly" size="50" value="${phoneVO.price }"><br>
	</c:forEach>
</body>
</html>