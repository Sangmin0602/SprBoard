<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/views/include/common-js.jsp"></jsp:include>

<title>Insert title here</title>
	<script type="text/javascript">
	$(function() {
		//id,class,name으로 찾기
		$("#idbtn").click(function(){
			console.log($("#txt1"));
		});
		$("#classbtn").click(function() {
			console.log($(".txt1"));
		});
		$("#namebtn").click(function(){
			console.log($("input[name=txt1]"));
		})
		//없는 아이디를 찾을경우!
		$("#nonebtn").click(function(){
			console.log($("#noneText"));
		});
		
		//클릭이벤트
		$("#clickBtn").click(function(){
			alert("버튼 클릭했네? ㅋㅋㅋ");
		});
		
		//셀렉트박스 옵션
		$("#changeBox").change(function(){
			alert("변경!!");
		});
		//더블클릭으로 찾기
		$("#dblbutton").dblclick(function() {
			alert("따닥!");
		});
		
		//포커스
		$("#focusBtn").click(function(){
			$("#txt2").focus();
		});
		$("#txt2").keypress(function(e){
			console.log("이벤트코드변경",e.which);
		});
		
		//셀렉터
		$("#selectButton").click(function(){
			//1.#btn의 부모 class 찾기
			console.log($("#btn").parent().attr("class"));
			//2.#prnt 의 자식 value 찾기
			console.log($("#prnt").find("#aaa").val());
			//3.#a의 다음 태그값 찾기
			console.log($("#a").next().val());
			//4.#b의 이저태그값 찾기
			console.log($("#b").prev().val());
		});
	})
	</script>
</head>
<body>
	<input type="button" id="selectButton" value="일괄처리"/>
	<div class ="부모class 찾기">
		<input type="button" id="btn" value="부모찾기버튼"/>
	</div>
	<div id="prnt">
		<input type="text" id="aaa" value="자식클래스찾기"/>
	</div>
	<input type="text" id="a" value="a값"/>
	<input type="text" id="b" value="b값"/>
	<br/>
	<br/>


	<input type="button" value="이놈을 클릭하면 두변째 텍스트에 포커스!" id="focusBtn"/><br/>
	<input type="text" id ="txt1"/><br/>
	<input type="text" id ="txt2"/><br/>
	<br/>

	<input type="button" value="클릭클릭~" id="dblbutton"/><br/>
	<br/>
	
	<select id ="changeBox">
		<option value="">체인지</option>
		<option value="0">체인지1</option>
		<option value="1">체인지2</option>
		<option value="2">체인지3</option>
	</select><br/>
	<br/>
	
	<input type="button" value="클릭해보자~" id="clickBtn"/> <br/>
	<br/>
	
	<input type="text" id = "txt1" value = "txt1 아이디값"/> <br/>
	<input type="button" id = "idbtn" value="아이디로찾기"/><br/>
	
	<input type="text" class="txt1" value = "txt1 클래스값"/><br/>
	<input type="text" class="txt1" value = "txt1클래스값"/><br/>
	<input type="button" id="classbtn" value = "클래스로 찾기"/><br/>
	
	<input type ="text" name="txt1" value="txt1 네임값"/><br/>
	<input type ="button" id="namebtn" value="네임으로찾기"/><br/>
	
	<input type = "button" id="nonebtn" value="없는놈 찾기"/>
</body>
</html>