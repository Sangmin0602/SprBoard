<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	//textArea
	function check(form) {
		alert(form.a.value);
	}
	//Password
	function check(form) {
		var str = "ID : " + form.id.value + "\n";
		str += "Password :" + form.pwd.value;
		alert(str);
	}
	
	// hidden
	function check(form) {
		var str = "CustomerId : " + form.CustomerId.value + "\n";
		str += "ArticleId" + form.ArticleId.value;
		alert(str);
	}
	
	//buttom
	function buttonClick(button) {
		alert(button.value + "을 눌렀습니다.");
	}
	
	//submit
	function beforeSubmit() {
		//Yes 버튼을 누르면 true가 리턴될 것이다.
		return confirm("데이터를 정말로 보낼까요?")
	}
	
	//Reset
	function beforeReset() {
		//Yes버튼을 누르면 true가 리턴될 것이다.
		return confirm("데이터를 리셋하겠습니까?");
	}
	
</script>
</head>
<body>
	<form name = "myform1">
		<textarea id = 'a' name = "s" rows = "10" cols="65">내용입력</textarea>
		<br>
		<input type="button" value = "확인" onclick = "check(this.form)">
	</form>
	
	<br><br>
	
	<form name = "myform2">
		ID : <input type = "text" name = "id" size = "10">
		Password : <input type ="password" name = "pwd" size = "10">
		<input type="button" value = "로그인" onclick = "check(this.form)">
	</form>
	
	<br><br>
	
	<form name = "myform3">
		<input type = "hidden" name = "CustomerId" value = "12345">
		<input type = "hidden" name = "ArticleId" value = "1">
		<input type = "button" value = "Check Info" onclick= "check(this.form)">
	</form>
	
	<br><br>
	
	<form name = "myform4">
		<input type = "text" name = "t1">
		<input type = "text" name = "t2">
		<input type = "button" value = "복사" onclick="this.form.t2.value = this.form.t1.value">
	</form>
	
	<br><br>
	
	<form name = "myform5">
		<input type = "button" value = "버튼 1" onclick = "buttonClick(this)">
		<input type = "button" value = "버튼 2" onclick = "buttonClick(this)">
		<input type = "button" value = "버튼 3" onclick = "buttonClick(this)">
		<input type = "button" value = "버튼 4" onclick = "buttonClick(this)">
		<input type = "button" value = "버튼 5" onclick = "buttonClick(this)">
		<input type = "button" value = "버튼 6" onclick = "buttonClick(this)">
		<input type = "button" value = "버튼 7" onclick = "buttonClick(this)">
	</form>
	
	<br><br>
	
	<form name = "myform6" action = "nothing">
		 이름 : <input type = "text">
		 <input type = "submit" value = "Submit" onclick = "return beforeSubmit()">
	</form>
	
	<br><br>
	
	<form name = "myform7" action = "nothing">
		이름 : <input type = "text" value = "이름">
		주소 : <input type = "text" value = "주소">
		<input type = "reset" value = "Reset" onclick = "return beforeReset()">
	</form>
</body>
</html>