<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/include/common-js.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		$("#listButton").click(function(){
			$.ajax({
				type:'post'
				,url:'/web/postings/listhtml'
				,dataType:'html'
				,success:function(data) {
					$("#listDiv").html(data);
				}
			})
		})
	})
</script>
</head>
<body>
<input type="button" id="listButton" value="리스트출력"/>
<br/>
<div id="listDiv"></div>

</body>
</html>