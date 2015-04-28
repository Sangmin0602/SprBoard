<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/views/include/common-js.jsp"></jsp:include>

<title>Insert title here</title>
	<style type="text/css">
		.focusRed{
			color:red;
		}
		.plusfontsize{
			font-size: 20pt;
		}
	</style>


	<script type="text/javascript">
	$(function(){	
		//동적으로 추가된 태그는 일반적인 이벤트가 동작되지 않는다.
		$("#makebtn").click(function(){
			var dynamicTag = '<input type="button" id= "dynamicbtn" value="경고"/>';
			$("body").html(dynamicTag);
		});
		//$("#dynamicbtn").click(function() {
			//alert("!");
		//}); 
		//1.7이후버전에는 on이벤트가 생겼다
		$(document).on("click","#dynamicbtn",function(){  
			alert("!");
		});
		//태그 적용
		var tag = "<input type=\"checkbox\"><br />변경후HTML";
		//html함수 적용하여 htmltest태그내의 html 변경
		$("#htmlbutton").click(function() {
			$("#htmltest").html(tag);
		});
		//text함수를 적용하여 html함수를 사용했던 같은 내용의 문자열을 표출해본다.
		$("#textbutton").click(function(){
			$("#texttest").text(tag);
		})
		
		//체크가 안되었을때
		alert($("#check1").attr("checked") + "." + $("#check1").prop("checked"));
		//-> 결과 undefined, false
		//체크가 되어있을때
		alert($("#check2").attr("checked") + "." + $("#check2").prop("checked"));
		//->결과 checked, true
		
		
		//CSS 변경 스크립트
		//a태그 클릭시
		$("#atag").click(function() {
			//a태그에 focusRed라는 클래스를 추가한다.
			$("#atag").addClass("focusRed");
		});
		//id가classChangeButton이라는 버튼 클릭시
		$("#classChangeButton").click(function(){
			//라벨태그에 클래스가 존재하지 않는다면,
			if(!$("#fontLabel").hasClass("plusfontsize")){
				//plusfontsize클래스 아이디를 fontLable 아이디를 가지고 았는 label에 클래스추가
				$("#fontLabel").addClass("plusfontsize");
			//라벨태그에 클래스가 존재할 경우
			}else{
				//plusfontsize 클래스 아이드를 fontLable 아이디를 가지고 있는 label클래스제거
				$("#fontLabel").removeClass("plusfontsize");
			}
		});
		
		
		//공백제거 $.trim($("#").val()) 
		alert("공백제거전::" + $("#trimtest").val());
		alert("공백제거후::" + $.trim($("#trimtest").val()));
		
		
		//show/hide, slideDown/slideUp, fadeIn/fadeOut
		$("#showbtn").click(function(){
			if($("#displayselect").val()=="1"){
				$("#displaydiv").show();
			} else if($("#displayselect").val()=="2") {
				$("#displaydiv").slideDown();
			} else if($("#displayselect").val()=="3") {
				$("#displaydiv").fadeIn();
			}
		});
		$("#hidebtn").click(function(){
			if($("#displayselect").val() == "1") {
				$("#displaydiv").hide();
			} else if($("#displayselect").val() == "2") {
				$("#displaydiv").slideUp();
			} else if($("#displayselect").val()=="3") {
				$("#displaydiv").fadeOut();
			}
		});
	})
	</script>
</head>
<body>
	<input type="button" id="makebtn" value="버튼생성"/>
	<br/>
	<br/>
	<br/>

	<div id ="htmltest" style="border-width:1px;border-style:solid;">
		<input type="text" value="test"/>
		<br/>
		변경전HTML
		<br/>
	</div>
	<br/>
	<div id="texttest" style="border-width:1px;border-style:solid;"></div>
	<input type="button" value="html태그변경" id="htmlbutton"/>
	<input type="button" value="태크를 text로 출력" id="textbutton"/>

	<br/>
	<br/>
	<br/>
	<input type="checkbox" id="check1" checked ="checked"/>
	<input type="checkbox" id="check2" checked="checked"/>

	<br/>
	<br/>
	<br/>
	
	<a href="#" id="atag"> 빨~~~갛게</a><br/>
	<input type="button" value = "클래스적용토글" id="classChangeButton"/><br/>
	<lable id="fontLabel">글자11</lable>
	
	<br/>
	<br/>
	<br/>

	<input type="text" id="trimtest" value="   abc"/>
	<br/>
	<br/>


	<select id="displayselect">
		<option value="1">show/hide</option>
		<option value="2">slide</option>
		<option value="3">fade</option>
	</select>
	
	<input type="button" id="showbtn" value="보여주기">
	<input type="button" id="hidebtn" value="숨기기">
	
	<div style="display:none;" id="displaydiv">
		<span>초기 숨겨져 있는 화면1</span>
		<span>초기 숨겨져 있는 화면2</span>
		<span>초기 숨겨져 있는 화면3</span>
		<span>초기 숨겨져 있는 화면4</span>
	</div>
	
</body>
</html>