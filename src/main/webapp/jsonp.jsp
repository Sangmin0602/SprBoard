<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Daum 웹 검색 API</title>   
	<script type="text/javascript" src="/web/static/js/jquery-1.11.0.js"></script>
	<script type="text/javascript">

function search_query() {      
	var query = $("#query").val();
	var category = $("#category").val();
	var url = "http://apis.daum.net/search/" + category;
  /*
	url += "?callback=?";
  	url += "&output=jsonp";
	url += "&apikey=b62b20a07b737c1cca5b88737980adb11809762a"
	url += "&q=" + query;
*/	
  	$.ajax({
          type: 'get',
          url : url ,
          dataType: 'jsonp',
          jsonp  : 'callback',
          data: {
            apiKey : 'b62b20a07b737c1cca5b88737980adb11809762a',
            output : 'json',
            q : query
          },
          success : function ( json, status, error) {
            var data = json.channel;
            var buf = '';
            buf += '<b>size : </b>' + data.result;
            for( var i = 0 ; i < data.item.length ; i++ ) {
            	buf += '<li>' +  data.item[i].title + '</li>';
            }
            $('#results').html(buf);
          },
          error : function ( xhr, status, error ) {
            $('#results').html ( status );
          }
        });
    
    /*url,function(data) {
		alert(data.channel.item[0].title);
	}).error(function(XMLHttpRequest, textStatus, errorThrown)
	{          
		alert(textStatus);
	}).complete(function(){                                  
	});*/
}   
	</script>
	</head>
	<body>
		<form id="search_form" action="javascript:search_query();" method="get">
            <select id="category">
                <option value="web">web</option>
                <option value="board">board</option>
                <option value="knowledge">knowledge</option>
                <option value="blog">blog</option>
                <option value="cafe">cafe</option>
                <option value="book">book</option>
                <option value="image">image</option>
                <option value="vclip">vclip</option>
            </select>
			<input type="text" size="10" id="query" name="query"/>
			<input type="submit" >
		</form>
         	<h3>RESULT</h3>
		<div id="results"></div>
	</body>
</html>
