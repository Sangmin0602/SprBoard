<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>로그인</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/views/include/common-js.jsp"></jsp:include>
<script type="text/javascript">
(function(){
	var $ = jQuery;
	var ctxpath = ctxpath || '${ctxPath}';
	function processLogin ( ) {
		var id = $('#user').val();
		var pass = $('#password').val();
		checkParams ( id, pass);
		
		$.post ( ctxpath + '/login.json', $('#frm').serialize(), function ( json) {
			if ( json.success) {
				document.location.href = ctxpath + json.nextUrl;
			} else {
				$('#error-panel').html (ERR_MSG[json.ecode].msg);
			}
		});
	}
	function checkParams ( id, pass) {
		// FIXME 나중에 하자.
	}
	
	$(document).ready( function () {
		$('#btnLogin').click ( processLogin);
	});
})();
</script>
</head>
<body>
<div id="error-panel"></div>
<form name="frm" id="frm">
<%	
	String qs = request.getQueryString();
	if ( qs != null) { 
		qs = qs.substring("target=".length());
%>
	<input type="hidden" name="target" value="<%=qs %>"/>
<%}%>
	<input type="text" name="user" value="" id="user"/>
	<input type="password" name="password" id="password" />
	<input type="button" value="로그인" id="btnLogin"/>
</form>
</body>
</html>