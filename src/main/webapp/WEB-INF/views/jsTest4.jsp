<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	//Check Box
	function findChecked(form) {
		var str = "";
		
		if(form.drivea.checked == true) {
			str += "A 드라이브를 선택했습니다. \n";
			
		}
		if(form.driveb.checked == true)
			str += "B 드라이브를 선택했습니다. \n";
		if(form.drivec.checked == true)
			str += "C 드라이브를 선택했습니다. \n";
		if(form.drived.checked == true)
			str += "D 드라이브를 선택했습니다. \n";
		alert(str);
	}
	
	//Checkbox 객체의 이벤트 핸들러
	function  clickCheck(box) {
		if(box.checked == true) {
			alert(box.value + "드라이브를 체크했습니다.");
		}
		else {
			alert(box.value + "드라이브의 체크를 해제했습니다.");
		}
	} 
	
	//checked 해제
/* 	function checkAll(field){
	if (flag == 0){
		for(i = 0; i < field.length; i++) {
			field[i].checked = true;
		}
		flag = 1;
	} else {
		for(i = 0; i < field.length; i++) {
			field[i].checked = false;
		}		
		flag = 0;	
	} */
	
	//라디오 버튼
	function findSelected(form) {
		for(var i=0; i< form.drive.length; i++) {
			if(form.drive[i].checked == true) {
				alert(form.drive[i].value + "드라이브가 선택되었습니다.");
			}
		}
	}
	//Radio  객체의 이벤트 핸들러
	function change(rad) {
		document.bgColor = rad.value;
	}
	
</script>
</head>
	<h2> 드라이브 선택</h2>
	<form action = "nothing">
		<input type = "checkbox" name = "drivea" value = "A_Drive"> A 드라이브<br>
		<input type = "checkbox" name = "driveb" value = "B_Drive"> B 드라이브<br>
		<input type = "checkbox" name = "drivec" value = "C_Drive" checked> C 드라이브<br>
		<input type = "checkbox" name = "drived" value = "D_Drive"> D 드라이브<br>
		<input type = "button" value = "Check" onClick = "findChecked(this.form)">
	</form>
	
	<br><br>
	
	<form action ="nothing">
		<input type = "checkbox" name = "drivea" value = "A" onclick = "clickCheck(this)"> A 드라이브<br>
		<input type = "checkbox" name = "driveb" value = "B" onclick = "clickCheck(this)"> B 드라이브<br>
		<input type = "checkbox" name = "drivec" value = "C" onclick = "clickCheck(this)" checked> C 드라이브<br>
		<input type = "checkbox" name = "drived" value = "D" onclick = "clickCheck(this)"> D 드라이브<br>
	</form>
	
	<br><br>
	
	<form action = "nothing">
		<input type = "checkbox" name = "drivea" value = "A" onclick = "clickCheck(this)"> A 드라이브<br>
		<input type = "checkbox" name = "driveb" value = "B" onclick = "clickCheck(this)"> B 드라이브<br>
	</form>
	
	<br><br>
		
	<form action = "nothing">
		<input type = "radio" name = "drive" value = "A" checked> A드라이브 <br>
		<input type = "radio" name = "drive" value = "B">B 드라이브 <br>
		<input type = "radio" name = "drive" value = "C">C 드라이브 <br>
		<input type = "radio" name = "drives value = "D">D 드라이브 <br>
		<input type = "button" value = "Check" onclick = "findSelected(this.form)">
	</form>
	
	<form action = "nothing">
		<input type = "radio" name = "color" value = "white" onclick = "change(this)">흰색<br>
		<input type = "radio" name = "color" value = "black" onclick = "change(this)">검은색<br>
		<input type = "radio" name = "color" value = "green" onclick = "change(this)">녹색<br>
		<input type = "radio" name = "color" value = "blue" onclick = "change(this)">파란색<br>
	</form>
	
<body>
</body>
</html>