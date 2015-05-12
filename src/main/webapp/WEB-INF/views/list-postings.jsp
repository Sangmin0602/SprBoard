<%@page import="java.util.List"%>
<%@page import="spr.board.model.PostVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>테스트 게시판</title>
<jsp:include page="/WEB-INF/views/include/common-js.jsp"></jsp:include>
<style type="text/css">
.pagenum {
	cursor: pointer;
	display : inline-block;
	background-color: #EEE;
	padding: 4px;
	border : 1px solid #CCC;
}
</style>
<script type="text/javascript">
	var ctxPath = '${ctxPath}'; // '/web'
	var DEF_ROW_CNT = 3;
	function renderToTable2 ( json ) {	
		renderPostings(json);
		renderPagenation(json);
	}
	
	function renderPostings ( json ) {
		var $tbody = $('#tbl-postings').find('tbody');
		$tbody.empty();
		for ( var i = 0 ; i < json.rows.length ; i ++ ) {
			var r = json.rows[i];
			
			$tbody.append (
				$('<tr>').append ( $('<td>').append ( r.seq ) )
						 .append ( $('<td>').append ( $('<a>').append(r.title) ) )
						 .append ( $('<td>').append ( r.title ) )
						 .append ( $('<td>').append ( r.writer ) )
						 .append ( $('<td>').append ( r.when_created ) )
			);
		}
	}
	function renderPagenation (json ) {
		var $curPage = $('#cur_page');
		$curPage.html('[' + json.page + ']');
		
		$('#total_page').html('[' + json.total + ']')
			.addClass ( 'ddd');
			
		/*
		var ePage = document.getElementById('cur_page');
		ePage.innerHTML = '<b>json.page</b>';
		*/
		
		var $pgn = $('#pagenation'); // ul tag
		$pgn.html('');
		var template = '<li><span class="pagenum" id="{n}" onclick="movePage({n})">{n}</span></li>';
		for ( var i = 0 ; i < json.total ; i ++ ) {
			var num = i + 1; // 1, 2, 3, ....
			// <li class="pagenum"><span id="1">1</span></li>
			var html = template.replace ( '{n}', num );
			html = html.replace ( '{n}', num);
			html = html.replace ( '{n}', num);
			$pgn.append ( html );
		}
		
	}
	
	/**
	 * movePage ();
	 * movePage ( 1 );
	 * movePage ( 2, 5 );
	 * @param pageNum 요청 페이지 번호
	 * @param nRows   한 페이지당 게시물의 숫자
	 */
	function movePage ( pageNum, nRows ) {
		pageNum = pageNum || 1;
		nRows = nRows || DEF_ROW_CNT;
		$.post (ctxPath+'/postings.json',{rows:nRows, page:pageNum}, function(json){
			renderToTable2 ( json );
		});
	}
	
	function renderToTable ( json ) {
		var $tbody = $('#tbl-postings').find('tbody');
		/*
		<tr>
			<td class="p-seq">2039933</td>
			<td class="p-title"><a href="ddddddd">sdldkjfldk</a></td>
		</tr>
		*/
		// $tbody.append( '<tr><td>1</td><td>2</td="dldkd" + dldkd + "dkd"><td>3</td><td>4</td></tr>');
		var $template = $('#template').find( 'table tbody').html();
		for ( var i = 0 ; i < json.rows.length ; i ++ ) {
			var r = json.rows[i]; // 임시변수
			var html = $template.replace ( '{seq}', r.seq );
			html = html.replace('{num}', r.seq);
			html = html.replace('{ctxpath}', ctxPath);
			html = html.replace('{title}',r.title);
			html = html.replace('{writer}', r.writer);
			html = html.replace('{date}', r.when_created);
			
			/*
			html = replacePattern(html, {seq, r.seq, num, r.seq, ctxpath, ctxPath });
			for ( var prop in params ) {
				var p = '{' + prop = '}'; // '{seq}';
				html = html.replace ( p, parmas[prop]);
			} */
			
			console.log ( html );
			$tbody.append ( html );
		}
		
	}
 
 	//var data = { answer : 10, ddd : 'dkdkd'}; // 객체 리터럴(상수)
 
	$(document).ready(function(){
		
		// 1. 직접 ajax 메소드에 전송할 값들과 콜벡 메소드를 설정하는 방법
		/*
		$.ajax({
			  url: ctxPath+'/postings.json',
			  data : {rows:3, page:1},
			  success: function(json ) {
				  alert ( json );
			  },
			  dataType: 'json'
			});
		*/
		// 2. $.get을 사용해서 좀 더 간단하게 요청을 보낼 수 있음.
	/* 	$.post (ctxPath+'/postings.json',{rows:3, page:1}, function(json){
			renderToTable2 ( json );
		});
 */		
 		movePage(1,3);
	} );
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

<table id="tbl-postings">
<thead>
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>글쓴이</td>
		<td>날짜</td>
	</tr>
</thead>
<tbody>
</tbody>
</table>
<a name="2">dddd</a>
<div>
	<div> <span id="cur_page">-</span>/ <span id="total_page">-</span></div>
	<ul id="pagenation">
	<!-- 
		<li class="pagenum"><span id="1">1</span></li>
		<li class="pagenum"><span id="2">2</span></li>
		<li class="pagenum"><span id="3">3</span></li>
	 -->
	</ul>
</div>
<div id="template" style="display:none">
<table><tbody>
	<tr>
	<td class="p_seq">{seq}</td>
	<td class="p_title"><a href="{ctxpath}/postings/{num}">{title}</a></td>
	<td class="p_writer">{writer}</td>
	<td class="p_date">{date}</td>
</tr></tbody>
</table>
</div>
</body>
</html>