<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>

<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<P>The time on the server is ${serverTime}.</P>

<form enctype="multipart/form-data"
	action='<c:url value="/home/fileUpload" />' method="post">

	attach file <input type="file" name="file1"> <br /> <input
		type="submit"> file upload</input>
</form>
upload file name : ${targetFileInfo}
<br /> upload file location :
<a href="/web/postings/download?filepath=${uploadFilePath}">${uploadFilePath}</a>
<br /> upload file location :
<a href="/web/postings/sendMail">${"SEND EMAIL"}</a>
<br /> thumnail size is 100x100 .
<br />
<img alt="" src="${imageBase64}">

<form action="/web/postings/write" method="post">
	<input type="text" name="name" placeholder="폰 이름" required="required" size="50"><br>
	<input type="text" name="manufacturer" placeholder="폰 제조사" required="required" size="50"><br>
	<input type="text" name="price" placeholder="폰 가격" required="required" size="50"><br>
	<input type="submit" value="작성"><input type="reset" value="취소">
</form>

<form action="/web/postings/writeList" method="post">
	<input type="text" name="phoneItems[0].name" placeholder="폰 이름1" required="required" size="50"><br>
	<input type="text" name="phoneItems[0].manufacturer" placeholder="폰 제조사1" required="required" size="50"><br>
	<input type="text" name="phoneItems[0].price" placeholder="폰 가격1" required="required" size="50"><br>
	<hr>
	<input type="text" name="phoneItems[1].name" placeholder="폰 이름2" required="required" size="50"><br>
	<input type="text" name="phoneItems[1].manufacturer" placeholder="폰 제조사2" required="required" size="50"><br>
	<input type="text" name="phoneItems[1].price" placeholder="폰 가격2" required="required" size="50"><br>
	<hr>
	<input type="submit" value="작성"><input type="reset" value="취소">
</form>
</body>
</html>