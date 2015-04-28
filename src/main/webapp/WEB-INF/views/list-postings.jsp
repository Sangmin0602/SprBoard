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
<jsp:include page="/WEB-INF/views/include/jqgrid.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<c:forEach var="pos" items="${allPosts }">
<li>
	<span>[${pos.writer.nickName}]</span>
	<span><a href="${ctxPath }/postings/${pos.seq}">${pos.title }</a></span>
</c:forEach>

<div>
	<table id="postingTable"></table>
	<div id="postingPager"></div>
</div>
<div><input id="showSelected" type="button" value="선택된 row" onclick="getSelectedRows()"/></div>
<table id="demoTable"></table>
<div id="demoPager"></div>

<script type="text/javascript">
function getSelectedRows() {
    var grid = $("#demoTable");
    var rowKey = grid.getGridParam("selrow");

    if (!rowKey)
        alert("No rows are selected");
    else {
    	console.log( $("#demoTable").getRowData(rowKey));
        var selectedIDs = grid.getGridParam("selarrrow");
        var result = "";
        for (var i = 0; i < selectedIDs.length; i++) {
            result += "[" + selectedIDs[i] + "], ";
        }

        alert(result);
    }                
}

$(document).ready(function () {
	$("#demoTable").jqGrid({
    	url: 'http://trirand.com/blog/phpjqgrid/examples/jsonp/getjsonp.php?callback=?&qwery=longorders',
        mtype: "GET",
        datatype: "jsonp",
        colModel: [
        	{ label: 'OrderID', name: 'OrderID', key: true, width: 75 },
			{ label: 'Customer ID', name: 'CustomerID', width: 150, sortable:false },
			{ label: 'Order Date', name: 'OrderDate', width: 150 },
			{ label: 'Freight', name: 'Freight', width: 150 },
			{ label:'Ship Name', name: 'ShipName', width: 150, editable:true }
		],
		caption: "Demo jqGrid",
		viewrecords: true,
        width: "800",
        height: 400,
        rowNum: 20,
        rownumbers: true,
        rownumWidth: 30,
        multiselect : true,
        multiboxonly : true,
        pager: "#demoPager",
        regional : "kr"
        /*,
        scroll: 1,
        emptyrecords: 'Scroll to bottom to retrieve new page'*/
        });
});
 
</script>
</body>
</html>