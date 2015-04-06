<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>

<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="static/js/jquery-1.11.0.min.js"></script>
	<script>
		function postXml() {
			alert('aaaa');
			var xmlBody = 
				'<?xml version="1.0" encoding="UTF-8" standalone="yes"?>'+
				'<message-list>'+
				'<message><id>1</id><message>메시지</message><creationTime>2014-03-16T13:22:16.767+09:00</creationTime></message><message><id>2</id><message>메시지2</message><creationTime>2014-03-16T13:22:16.767+09:00</creationTime></message>'+
				'</message-list>';
			$.ajax({
				type: "post",
				url: "postings/post.xml",
				contentType: "text/xml",
				data: xmlBody,
				processData: false,
				success: function( response ){
					alert('bbb');
					alert(response);
				},
				error: function(){
					alert( "ERROR", arguments );
				}
			});
		}
	</script>
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

<li>HttpMessageConverter:
	<ul>
	<li><a href="mc/simple">/mc/simple</a>: 요청몸체-&gt;String / String-&gt;응답몸체, SimpleConverterController</li>
	<li><a href="postings/list.xml">/postings/list.xml</a>: 자바객체-&gt;XML응답, GuestMessageController.listXml()</li>
	<li><a href="javascript:postXml()">/postings/post.xml</a>: XML요청-&gt;자바객체, GuestMessageController.postXml()</li>
	<li><a href="postings/list.json">/postings/list.json</a>: 자바객체-&gt;JSON응답, GuestMessageController.listJson()</li>
	</ul>
</li>


<li>ExcelDownload:
	<ul>
	<li><a href="postings/pagestat/rank">/pagestat/rank</a>:ExcelDownload</li>
	</ul>
</li>

<li>PDFDownload:
	<ul>
	<li><a href="postings/pagestat/rankreport">/pagestat/rank</a>:PDFDownload</li>
	</ul>
</li>
</body>
</html>