<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<P>  The time on the server is ${serverTime}. </P>

<form enctype="multipart/form-data" action='<c:url value="/home/fileUpload" />' method="post">

attach file <input type="file" name="file1" >
<br />
<input type="submit"> file upload</input>
</form>
 upload file name : ${targetFileInfo} <br />
 upload file location : <a href = "/web/postings/download?filepath=${uploadFilePath}">${uploadFilePath}</a> <br />
 thumnail size is  100x100 . <br />
 <img alt="" src="${imageBase64}">
</body>
</html>