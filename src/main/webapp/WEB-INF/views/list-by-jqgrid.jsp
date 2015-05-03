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
<div>
	<input id="showSelected" type="button" value="선택된 row" onclick="getSelectedRows()"/>
	<input id="btnDelCheck" type="button" value="삭제 예약" onclick="checkAsDeleted()"/>
</div>
<table id="postingTable"></table>
<div id="pager"></div>

<script type="text/javascript">
var ctxpath = ctxpath || '${ctxPath}';
var grid ;
function asTitleLink(cellValue, options, rowData, action) {
	var link = '<a href=/web/postings/' + rowData.seq + '>' + cellValue + '</a>' ;
	return link;
}
function stripTitleLink(cellValue, options, rowData) {
	return cellValue;
}
function getSelectedRows() {
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
function checkAsDeleted() {
	var rows = grid.getGridParam('selarrrow');
	alert ( rows );
	$.post(ctxpath + '/postings/deleteLater', {seqs:rows}, function(json){
		grid.trigger ( 'reloadGrid');
	});
}
/*
 * 전체 포스팅 : 11개
 *
 * ((요청 전송시))
 * 페이지당 3개씩 - rowNum : 3
 * 원하는 페이지 번호 - page : 1
 */
$(document).ready(function () {
	/*
	 * datepicker가 아닌 datetimepicker를 사용하려면
	 * 별도의 플러그인을 추가해야함.
	 * http://stackoverflow.com/questions/20487077/how-to-write-time-picker-field-in-jqgrid
	 */
	
	var fnInitDateEdit = function (elem) {
        $(elem).datepicker({
            dateFormat: "yy-mm-dd",
            autoSize: true,
            changeYear: true,
            changeMonth: true,
            showButtonPanel: true,
            showWeek: true
        });
    };
    
	grid = $("#postingTable").jqGrid({
        mtype: "",
        datatype: "json",
        colModel: [
        	{ label: 'SEQ', name: 'seq', key: true, width: 75, sortable:false },
			{ label: 'TITLE', 
        		name: 'title', 
        		width: 250, 
        		sortable:false, 
        		editable:true,
        		formatter: asTitleLink,
        		unformat: stripTitleLink
        	},
			{ label: 'WRITER', name: 'writer', width: 150, editable:true },
			{ label: 'DATE', 
				name: 'when_created', 
				width: 150,
				editable:true,
				editoptions: {dataInit : fnInitDateEdit},
				editrules: { date: true}
			},
			{ label: 'DELETED', 
				name: 'deleted', 
				width:150, 
				formatter: 'checkbox',
				editable: true,
				align: 'center',
				edittype:'checkbox',
				editoptions: {value:'YES:NO'}
			}
		],
		total : "total",
		page : "page",
		records : "records",
		caption: "Postings",
		viewrecords: true,
        width: 600,
        height: 250,
        rowNum: 5,
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
        	{
        		edit : true,
        		add : true,
        		del : true,
        		search : true,
        		refresh: true,
        		position: 'left',
        		cloneToTop: true
        	},
        	{ url : ctxpath + '/postings/edit'},
        	{ url : ctxpath + '/postings/new'},
        	{ url : ctxpath + '/postings/del'}
        );
	
	grid.setGridParam({url: '${ctxPath}/postings.json', mtype:'GET'})
		.trigger('reloadGrid');
	
});
 
</script>
</body>
</html>