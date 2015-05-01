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
<div style="display: relative;">
<table id="userTable"></table>
<div id="pager"></div>
</div>

<script type="text/javascript">
var ctxpath = ctxpath || '${ctxPath}';

function asTitleLink(cellValue, options, rowData, action) {
	var link = '<a href=/web/postings/' + rowData.seq + '>' + cellValue + '</a>' ;
	return link;
}
function getSelectedRows() {
    var grid = $("#userTable");
    var rowKey = grid.getGridParam("selrow");

    if (!rowKey)
        alert("No rows are selected");
    else {
    	console.log( $("#userTable").getRowData(rowKey));
        var selectedIDs = grid.getGridParam("selarrrow");
        var result = "";
        for (var i = 0; i < selectedIDs.length; i++) {
            result += "[" + selectedIDs[i] + "], ";
        }

        alert(result);
    }                
}

function onResponseReceived( response, rowid, cellname, value, iRow, iCol) {
	var jsonRes = response.responseJSON;
	return [jsonRes.success, jsonRes.message];
}

function onCellSaved ( rowid, cellname, value, iRow, iCol ) {
	console.log ("updated : " + rowid + "value: " + value );
}
/*
 * 전체 포스팅 : 11개
 *
 * ((요청 전송시))
 * 페이지당 3개씩 - rowNum : 3
 * 원하는 페이지 번호 - page : 1
 */
$(document).ready(function () {
	var grid = $("#userTable").jqGrid({
        mtype: "",
        datatype: "json",
        toppager: true,
        editurl : ctxpath + '/users/new',
        /*
        cellEdit : true,
        cellsubmit : 'remote',
        cellurl : ctxpath + '/users/edit/password',
        afterSubmitCell : onResponseReceived ,
        afterSaveCell : onCellSaved ,
        */
        colModel: [
        	{ label: 'SEQ', name: 'seq', key: true, width: 75, sortable:false },
			{ label: 'UserId', 
        		name: 'userId', 
        		width: 250, 
        		sortable:false, 
        		editable:true},
			{ label: 'NickName', name: 'nickname', editable:true, width: 150 },
			{ label: 'Eamil', name: 'email', editable:true, width: 150 },
			{ label: 'Password', name: 'password', editable:true, editable:true, width: 150 },
			{ label: 'WhenJoined', name: 'whenjoined', width: 150 }
		],
		total : "total",
		page : "page",
		records : "records",
		caption: "Users",
		viewrecords: true,
        width: 600,
        height: 250,
        rowNum: 4,
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
	
	grid.navGrid('#pager', 
		// button settings
		{
			edit : true,
			add : true,
			del : true,
			search : true,
			refresh : true,
			view : true,
			position : "left",
			cloneToTop : true
		},
		// option - edit
		{},
		// option - add
		{},
		// option - del
		{
			url : ctxpath + '/users/del'
		}
	
	);

	grid.setGridParam({
		url : '${ctxPath}/users.json',
		mtype : 'GET'
	}).trigger('reloadGrid');

});
</script>
</body>
</html>