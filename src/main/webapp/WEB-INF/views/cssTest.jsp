<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	//글자색 바꾸기
	function changeColor(id, color) {
		var e = document.getElementById(id);
		e.style.color = color;
	}
	//배경색 바꾸기
	function changeBgColor(id, color) {
		var e = document.getElementById(id);
		e.style.backgroundColor = color;
	}
</script>
</head>
<body>
	<p id = "title">Javascript</p>
	<input type = "button" value = "글자색:blue" onclick = "changeColor('title', 'blue')">
	<input type = "button" value = "글자색:black" onclick = "changeColor('title, 'black')">
	<input type = "button" value = "배경색:yellow" onclick = "changeBgColor('title', 'yellow')">
	<input type = "button" value = "배경색:white" onclick = "changeBgColor('title', 'white')">
</body>
</html>