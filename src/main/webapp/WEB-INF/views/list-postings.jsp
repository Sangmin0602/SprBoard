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
<style type="text/css">
.p_writer {
	background-color: #eee;
}
</style>
<jsp:include page="/WEB-INF/views/include/common-js.jsp"></jsp:include>
<script type="text/javascript">
var ctxpath = ctxpath || '${ctxPath}';

function appendPostings1( json ) {
	
	function toTitleLink( row ) {
		var _title = '<a href="{ctxpath}/postings/{seq}">{title}</a>';
		_title = _title.replace('{ctxpath}', ctxpath);
		_title = _title.replace('{seq}', row.seq);
		_title = _title.replace('{title}', row.title);
		return _title;
	}
	
	function toUserLink ( row ) {
		var usr = '<div class="writer">{name}</div>';
		usr = usr.replace('{name}', row.writer);
		return usr;
	}
	
	var $tbody= $('#tbl-posting').find('tbody');
	for ( var i = 0 ; i < json.rows.length ; i++ ) {
		var r = json.rows[i];
		$tbody.append(
			$('<tr>')
				.append( $('<td>').append(r.seq))
				.append( $('<td>').append( toTitleLink(r)) )
				.append( $('<td>').append( toUserLink(r))  )
				.append( $('<td>').append(0))
				.append( $('<td>').append(r.when_created)  )
		);
	}
}

function appendPostings2(json ) {
	var $template= $('#template').find('table tbody');
	var $tbody= $('#tbl-posting').find('tbody');
	for ( var i = 0 ; i < json.rows.length ; i++ ) {
		var r = json.rows[i];
		var html = $template.html();
		html = html.replace ('{seq}', r.seq);
		html = html.replace ('{title}', r.title);
		html = html.replace ('{name}', r.writer);
		html = html.replace ('{vc}', 0);
		html = html.replace ('{date}', r.when_created);
		$tbody.append(html);
	}
}

$(document).ready( function() {
	var rows = 10;
	var pagenum   = 1;
	
	var path = ctxpath + '/postings.json';
	path += '?rows=' + rows + '&page=' + pagenum ;
	
	$.get(ctxpath + '/postings.json',
		function ( json ){
			appendPostings2(json);
	});
});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<table id="tbl-posting">
<thead>
<tr>
	<th>번호</th>
	<th>제목</th>
	<th>이름</th>
	<th>조회수</th>
	<th>날짜</th>
</tr>
</thead>
<tbody></tbody>
</table>
<div id="template" style="display:none">
<table>
	<tbody>
	<tr>
		<td class="p_seq">{seq}</td>
		<td class="p_title">{title}</td>
		<td class="p_name"><div class="p_writer">{name}</div></td>
		<td class="p_viewcount">{vc}</td>
		<td class="p_date">{date}</td>
	</tr>
	</tbody>
</table>
</div>
<c:forEach var="pos" items="${allPosts }">
<li>
	<span>[${pos.writer.nickName}]</span>
	<span><a href="${ctxPath }/postings/${pos.seq}">${pos.title }</a></span>
</c:forEach>
</body>
</html>