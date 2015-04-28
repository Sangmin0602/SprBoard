<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/views/include/common-js.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		$("#searchButton").click(function(){
			var flag = $("#resultSelect").val();
			//select box로 구분을 주었지만
			//실질적으로는 내부로직에서 db조회하여 데이터가 있으면
			//json 파싱하여 데이터 생성을 하고
			//없으면 데이터 없음으로 파싱해야한다.
			if(flag == "Y") {
				url = '/web/postings/listhtml2';
			} else{
				url = '/web/postings/listhtml3';
			}
			//비동기 통신을 하여 json타입으로 호출한다.
			$.ajax({
				url:url
				,dataType:'json'
				,success:function(data) {
					//result결과
					//json객체의 flag가 Y이면 data라는 성공메시지를 result변수에 담는다.
					if(data.flag == "Y") {
						var result = data.data;
					//json객체의 flag가 Y가 아닌 그이후는 data라는 실패메시지를 result변수에 담는다.
					} else {
						var result = data.data;
					}
					//div태그에 html로 출력!
					$("#listLayout").html(data.data);
				}
			});
		});
	})
</script>
<title>Insert title here</title>
</head>
<body>

	<select id = "resultSelect">
		<option value="Y">성공JSON</option>
		<option value="N">실패JSON</option>
	</select>
	
	<input type="button" id="searchButton" value="조회"/><br/>
	<div id="listLayout"></div>
	
</body>
</html>