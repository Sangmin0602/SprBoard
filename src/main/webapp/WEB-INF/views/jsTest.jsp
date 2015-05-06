<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function checkText() {
		var str
		
		//Form의 이름이 myForm이기 때문에 document.myform으로 지정
		if(document.myform.elements[0].value != "") {
			str = "이름은" +  document.myform.elements[0].value+"입니다.\n";
			str += "데이터를 보냈습니다";
			
			document.myform.submit();
			alert(str);
		}
		else{
			alert("이름을 입력하지 않아서 데이터를 보내지 않았습니다");
		}
	}
	
	function beforeSubmit() {
		//Yes 버튼을 누르면 true가 리턴 될 것이다.
		return confirm("데이터를 정말로 보낼까요?");
	}
	
	function makeUpper1() {
		var str = document.myform1.input.value
		document.myform1.input.value = str.toUpperCase();
	}
	
	function makeUpper2() {
		var str = document.myform2.input.value
		document.myform2.input.value = str.toUpperCase();
	}
	
	function makeLowwer(form) {
		var str = form.input.value
		form.input.value = str.toUpperCase();
	}
	
	function makeUpper2(field) {
		var str = field.value
		field.value = str.toUpperCase();
	}
	
</script>
</head>
<body>
	<h2>Form 객체</h2> <br>
	<form name="myform" action="nothing" onsubmit= "return beforeSubmit()">
		이름 : <input type = "text" maxLength = "10">
		<input type = "button" value = "입력 확인 후 보내기" onclick="checkText()">
		<br>
		
		<input type = "submit" id ="submit" value = "submitQuery" size = "300" disabled = "true">
		
	</form>
	
	<br><br><br>
	
	<form name = "myform1">
		Input 1: <input type = "text" name = "input" value="test" readonly>
		<input type = "button" value="대문자로" onclick = "makeUpper1()">
	</form>
	
	<form name="myform2">
		Input 2: <input type = "text" name = "input" value="test" disabled = "false">
		<input type = "button" value="대문자로"  onclick="makeUpper2()">
	</form>
	
	<br><br><br>
	
	<form name = "myform3">
		Input 1: <input type = "text" name = "input" value = "lowwer" disabled = "false">
		<input type = "button" value = "소문자로 " onmouseup="makeLowwer(this.form)">
	</form>
	
	<form name = "myform4">
		Input 2: <input type = "text" name = "input" value = "lowwer" readonly>
		<input type = "button" value = "소문자로" onmousedown="makeLowwer(this.form)">
	</form>
	
	<br><br><br>
	
	<form name = "myform5">
		Input 1: <input type="text" name = "input1" value = "field" readonly>
		<input type = "button" value = "대문자로" ondblclick="makeUpper2(this.form.input1)">
	</form>
	
	<form name = "myform6">
		Input 1: <input type = "text" name = "input2" value = "field" disabled = "false">
		<input type = "button" value = "대문자로" ondblclick="makeUpper2(this.form.input2)">
	</form>
	
	
	
</body>
</html>