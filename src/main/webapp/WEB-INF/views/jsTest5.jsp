<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	//옵션선택
	function selectCom(form) {
		//인덱스가 0부터 시작하므로 1을 더해준다.
		alert("당신은 " + (form.com.selectedIndex+1)+ "번째를 선택했습니다.");
	}
	//옵션 멀티 선택
	function selectCom2(form) {
		var str = "선택 : \n";
		
		for(i=0; i<form.com2.length;i++) {
			if(form.com2.options[i].selected == true) {
				str += form.com2.options[i].text +"\n";
			}
		}
		alert(str);
		
	}
	//Select 객체의 이벤트 핸들러
	function change(form) {
		document.bgColor = form.color.options[form.color.selectedIndex].value;
	}
	
	//Option 객체 추가하기
	function appendOption1(form) {
		var option1 = new Option("Option1", "1st_option", true);
		form.test_select.options[1] = option1;
	}
	
	function appendOption2(form) {
		var option2 = new Option("Option2", "1st_option");
		form.test_select.options[2] = option2;
	}
	
	//Option 추가
	function appendOpt(form) {
		if(form.new_option.value != "") {
			var option = new Option(form.new_option.value, form.new_option.value);
			var len = form.test_select2.options.length;
			
			//리스트 박스의 가장 마지막에 추가
			form.test_select2.options[len] = option;
		}
		else {
			alert("추가할 Option 이름을 넣어주세요");
			form.new_option.focus();
		}
	}
	//선택된 Option 삭제
	function removeOpt(form) {
		var len = form.test_select2.options.length;
		var sel = form.test_select2.options.selectedIndex;
		
		if(len > 0 && sel >= 0) {
			form.test_select2.options[sel] = null;
		}
		else {
			alert("삭제할 Option을 선택해 주세요");
			form.new_option.focus();
		}
	}
	
	//파일 업로드
	function checkfile(form) {
		alert("선택한 파일 : " + form.file_name.value);
	}
</script>
</head>
<body>
	<h2>컴퓨터 선택</h2> <p>
	<form action = "nothing">
		<select name = "com" size="1">
			<option value = "dual"> Dual Core</option>
			<option value = "quad"> Quad Core</option>
			<option value = "i7">i7</option>
		</select><p>
		<input type = "button" value = "선택결과" onclick="selectCom(this.form)">
	</form>
	
	<form action= "nothing"> <p>
		<select name = "com2" size="3" multiple>
			<option value = "dual"> Dual Core </option>
			<option value = "quad"> Quad Core </option>
			<option value = "i7"> i7 </option>
		</select><p>
		<input type = "button" value = "선택 결과" onclick = "selectCom2(this.form)">
	</form>
	<h2> 배경색 바꾸기</h2>
	<form action = "nothing">
		<select name = "color" size = "5" onchange="change(this.form)">
			<option value = "white">흰색</option>
			<option value = "black">검은색</option>
			<option value = "green" selected> 녹색 </option>
			<option value = "blue"> 파란색</option>
			<option value = "yellow">노란색</option>
		</select>
	</form>
	<h2>옵션 메뉴 추가</h2>
	<form>
		<select name = "test_select" size = "5">
			<option value = "option_existed">Option0</option>
		</select>
		<input type = "button" value = "append Option1" onclick = "appendOption1(this.form)">
		<input type = "button" value = "append Option2" onclick = "appendOption2(this.form)">
	</form>
	<h2>옵션 추가/삭제</h2>
	<form >
		<select name = "test_select2" size = "5">
			<option value = "option_existed">Option0</option>
		</select>
		<input type = "text" name = "new_option" size = "10">
		<input type = "button" value = "Option 추가" onclick = "appendOpt(this.form)"><br>
		<input type = "button" value = "선택된 Option 삭제" onclick = "removeOpt(this.form)">
	</form>
	<h2>파일 업로드</h2><p>
	<form name = "myform">
		File : <input type ="file" name = "file_name"><p>
		<input type = "button" value = "check" onclick = "checkfile(this.form)">
	</form>	
</body>
</html>