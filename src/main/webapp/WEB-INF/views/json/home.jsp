<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html >
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<form enctype="multipart/form-data" action='<c:url value="/home/fileUpload" />' method="post">

파일첨부 <input type="file" name="file1" >

<br />
<input type="submit"> 파일 업로드</input>
</form>

 업로드 한 파일 이름 : ${targetFileInfo} <br />
 업로드된 임시 파일 위치 : ${uploadFilePath} <br />
 업로드된 이미지파일은 썸네일이 표시됩니다. 썸네일 사이즈는  100x100 입니다. <br />
 <img alt="" src="${imageBase64}">
</body>
</html>
