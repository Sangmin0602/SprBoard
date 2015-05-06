<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function verifyValue(form) {
		var ans = form.input.value;
		
		if(ans == "") {
			alert("입력하지 않으셨습니다.");
		}
		else if(ans == "a" || ans == "A") {
			alert("맞았습니다.")
		}
		else {
			alert("틀렸습니다. 다시 선택하십시오");
			//다시 입력하도록 입력 양식에 커서를 넣고 선택한다.
			form.input.focus();
			form.input.select();
		}
	}
	
	function showStatus(str) {
		var c = document.getElementById("comment");
		c.innerHTML=str;
	}
	
	function CheckNumber(form) {
		var n = form.txt.value;
		if(isNaN(n))
		{
			alert("숫자가 아닙니다.");
		}
	}
</script>
</head>
<body>
	우리나라의 수도는 어디입니까? <br>
	A) 서울 B) 광주 C) 부산 D) 대구 <p>
	
	<form  name = "myform1">
		답 : <input type ="text" name = "input" size = "3" maxlength = "3">
		<input type = "button" value="Verify" onclick="verifyValue(this.form)">
	</form>
	<br>
	<br>
	<form name = "myform2">
		이름 : <input type = "text" onfocus = "showStatus('이름을 입력하시오')"><br>
		나이 : <input type = "text" onblur = "showStatus('나이을 입력하시오')"><br>
		주소 : <input type = "text" onselect "showStatus('나이을 입력하시오')"><br>
		<div id = "comment"></div>
	</form>
	<br>
	<br>
	<form name = "myform3">
		숫자입력 : <input type = "text" id = "txt" onchange = "CheckNumber(this.form)">
	</form>
</body>
</html>