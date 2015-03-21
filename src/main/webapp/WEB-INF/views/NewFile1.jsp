<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
<script type="text/javascript">
(function() {
	var $ = jQuery;
	var ctxpath = ctxpath || '${ctxPath}';
	
	function processLogin() {
		var id = $('#user').val();
		var pass = $('#password').val();
		checkParams(id, pass);
		
		$.post(ctxpath + '/login.json', $('#frm').serialize(), function(json) {
			if(json.success) {
				document.location.href = ctxpath + json.nextUrl;
			} else {
				$('#error-panel').html(ERR_MSG[json.ecode].msg);
			}
		});
	}
	function checkParams(id, pass) {
		//FIXME 자중에 하자.
	}
	
	$(document).ready(function(){
		$('#btnLogin').click(processLogin);
	});
	 
})();
</script>
</head>
<body>
<div id="error-panel"></div>
<form name="frm" id="frm">
<%
	String qs = request.getQueryString();
	if(qs != null) {		
%>
	<input type="hidden" name="target" value="<%=qs%>"/>
<%}%>

	<input type = "text" name="user" value="" id="user"/>
	<input type = "password" name="password" id="password"/>
	<input type="button" value="로그인" id="btnLogin"/>

</form>

</body>
</html>