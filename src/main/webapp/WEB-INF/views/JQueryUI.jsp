<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/views/include/common-js.jsp"></jsp:include>
<title>Insert title here</title>
<script type="text/javascript">
$(function(){
	$( "#autocompleteText" ).autocomplete({
		source : function( request, response ) {
			//많이 봤죠? jquery Ajax로 비동기 통신한 후
			//json객체를 서버에서 내려받아서 리스트 뽑는 작업
	        $.ajax({
	        	//호출할 URL
	            url: "/web/postings/search",
	            //우선 jsontype json으로
	            dataType: "json",
	            // parameter 값이다. 여러개를 줄수도 있다.
	            data: {
	              //request.term >> 이거 자체가 text박스내에 입력된 값이다.
	              searchValue: request.term
	            },
	            success: function( result ) {
	            	//return 된놈을 response() 함수내에 다음과 같이 정의해서 뽑아온다.
	                response( 
	                	$.map( result, function( item ) {
	                			return {
	                			//label : 화면에 보여지는 텍스트
	                			//value : 실제 text태그에 들어갈 값
	                			//본인은 둘다 똑같이 줬음
	                			//화면에 보여지는 text가 즉, value가 되기때문 
	                  				label: item.data,
	                  				value: item.data
	                			}
	              		})
	              	);
	            }
	          });
	    },
		        //최소 몇자 이상되면 통신을 시작하겠다라는 옵션
		minLength: 2,
		//자동완성 목록에서 특정 값 선택시 처리하는 동작 구현
		//구현없으면 단순 text태그내에 값이 들어간다.
		select: function( event, ui ) {}
	});
	
	//날짜 
	$("#dateText").datepicker({
		//달력에 일자 선택시 text태그에 들어갈 연월일 포맷
		dateFormat:"yy-mm-dd",
		//changeYear, changeMonth을 true로 주면 연/월  부분을
		//selectbox로 날짜 이동가능
		changeYear:true,
		changeMonth:true
	});
	
	//1번 태그 =  제목, 2번 태그 = 내용
	$("#accordion").accordion();
	
	//레이어팝업 UI 생성후 화면에 안보여주기
	$("#dialog").dialog({
		//이벤트 발생했을때 보여주려면 auoOpen : false로 지정해줘야 한다.
		autoOpen : false,
		//레이어팝업 넓이
		width : 800,
		
		height : 800,
		//뒷배경을 disabl 시키고 싶다면 true
		model : true,
		//버튼종류
		buttons: [
			          {
			        	  //버튼 텍스트
			        	  text:"OK",
			        	  //클릭이벤트 발생시 동작
			        	  click: function(){
			        		  $(this).dialog("close");
			        	  }
			          },
			          {
			        	  //버튼텍스트
			        	  text: "Cancel",
			        	  //클릭이벤트 발생시 도작
			        	  click: function() {
			        		  $(this).dialog("close");
			        	  }
			          }
		          ]
	});
	
	//버튼 클릭이벤트시, 숨어있는 레이어팝업 오픈하기
	$("#button").click(function(){
		 $( "#dialog" ).dialog( "open" );
	});
	
	//툴팁 title="툴팁" 추가하면 된다.
	$(document).tooltip();
	
	//tab
	$( "#tabs" ).tabs();
	
	//위치바꾸기
	$( "#sortable" ).sortable();

})
</script>
</head>
<body>

<input id= "autocompleteText" type="text"/>
<br/>
<br/>
<br/>

<input id = "dateText" type="text" readonly="readonly" title="dateText"/>

<br/>
<br/>
<br/>

<div id="accordion">
	<div>제목1</div>
	<div>내용1</div>
	<div>제목2</div>
	<div>내용2</div>
	<div>제목3</div>
	<div>제목3</div>
	<div>제목4</div>
	<div>제목4</div>
</div>

<div id="dialog" title="레이어팝업 제목">
	해당부분은 레이어 팝업의 내용이다. br태그없이 알아서 자동 줄바꿈 처리가 되있음....
</div>

<br/>
<br/>
<br/>

<input id = "button" type="button" value="레이어팝업띄우기" title="레이어링크"/>

<br/>
<br/>
<br/>

<a href="http://www.naver.com" title="네이버사이트로 가는링크">네이버이동</a><br/><br/><br/><br/>
<a href="http://www.daum.net" title="다음으로 가봅시다">다음이동</a>
<br/>
<br/>
<br/>

<div id="tabs">
	<ul>
		<li><a href="#tab-1">첫번째탭</a></li>
		<li><a href="#tab-2">두번째탭</a></li>
		<li><a href="#tab-3">세번째탭</a></li>
	</ul>
	<div id="tab-1">첫번째 탭 내용번째 탭내용</div>
	<div id="tab-2">두번째 탭 내용</div>
	<div id="tab-3">세번째 탭 내용</div>
</div>



<ul id="sortable">
	<li>홍길동</li>
	<li>이순신</li>
	<li>스티브잡스</li>
	<li>웨인루니</li>
</ul>
</body>
</html>