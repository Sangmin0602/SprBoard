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
<title>jqGrid 적용</title>
<jsp:include page="/WEB-INF/views/include/common-js.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/include/jqgrid.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<c:forEach var="pos" items="${allPosts }">
<li>
	<span>[${pos.writer.nickName}]</span>
	<span><a href="${ctxPath }/postings/${pos.seq}">${pos.title }</a></span>
</c:forEach>
<div><input id="showSelected" type="button" value="선택된 row" onclick="getSelectedRows()"/></div>
<table id="postingTable"></table>
<div id="pager"></div>

<script type="text/javascript">
function asTitleLink(cellValue, options, rowData, action) {
	var link = '<a href=/web/postings/' + rowData.seq + '>' + cellValue + '</a>' ;
	return link;
}
function getSelectedRows() {
    var grid = $("#postingTable");
    var rowKey = grid.getGridParam("selrow");

    if (!rowKey)
        alert("No rows are selected");
    else {
    	console.log( $("#postingTable").getRowData(rowKey));
        var selectedIDs = grid.getGridParam("selarrrow");
        var result = "";
        for (var i = 0; i < selectedIDs.length; i++) {
            result += "[" + selectedIDs[i] + "], ";
        }

        alert(result);
    }                
}

$(document).ready(function () {
	var grid = $("#postingTable").jqGrid({
        mtype: "",
        datatype: "json",
        colModel: [
        	{ label: 'SEQ', name: 'seq', key: true, width: 75, sortable:false },
			{ label: 'TITLE', 
        		name: 'title', 
        		width: 250, 
        		sortable:false, 
        		formatter: asTitleLink },
			{ label: 'WRITER', name: 'writer', width: 150 },
			{ label: 'DATE', name: 'when_created', width: 150 }
		],
		caption: "Postings",
		viewrecords: true,
        width: 600,
        height: 250,
        rowNum: 10,
        /*rownumbers: true,
        rownumWidth: 30,*/
        multiselect : true,
        multiboxonly : true,
        pager: "#pager",
        regional : "kr" ,
        /*,
        scroll: 1,
        emptyrecords: 'Scroll to bottom to retrieve new page'*/
        
        onSelectRow : function ( rowid, status, e ) {
    		console.log("selected row : " + rowid, "status : " + status);
    	}
	});
	
	grid.setGridParam({url: '${ctxPath}/postings.json', mtype:'GET'})
		.trigger('reloadGrid');
	
});
 
</script>
</body>
</html>