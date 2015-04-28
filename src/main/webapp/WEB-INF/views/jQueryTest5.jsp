<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/views/include/common2-js.jsp"></jsp:include>

<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
//cellValue : 형식이 바뀔 값입니다. 즉 초기에 들어온 데이터 그 상태
//options : 해당 row의 colModel의 정보와 rowId의 정보가 들어있습니다.
//rowObject : 해당 row의 데이터 정보 전부를 가지고 있습니다.    
//name : 출력할 데이터의 이름입니다. 서버에서 받은 데이터의 변수명을 명시해줍니다.
//index : 컬럼 정렬 시 서버에 넘어가는 값입니다. index값을 설정하지 않으면 name값이 넘어갑니다.
//width : 컬럼의 넓이를 설정합니다.
//align : 컬럼 내 데이터의 정렬을 설정합니다.
//hidden : 데이터값은 설정하고 화면에서 보이고 싶지 않을 때 사용합니다.
//formatter : 데이터로 들어온 값을 특정 형식으로 변환해서 보여줄 수 있습니다.
    $(document).ready(function(){
             
       $("#list").jqGrid({
          url:'/web/postings/jQueryJson',
    	  datatype: 'json', //데이터 타입 (local, xml, json)
       	  mtype:"POST",
          colNames:['Inv No','Date', 'Client', 'Amount','Tax','Total','Notes'], //칼럼 이름
          colModel: [ //데이터 매핑 및 로우 속성
              {name:'id',index:'id', width:60, sorttype:"int", editrules:{edithidden:false}, editable:true},
              {name:'invdate',index:'invdate', width:90, sorttype:"date", editrules:{required:true, edithidden:true},
            	  eidtable:true, editoption:{size:"50", maxlength:"50",
										              				  dataEvents:[{
										              				        type:"blur",
										              				        fn:function(e){
										              				        var newCodeValue=$(e.target).val();
										              				        }
										              				  }]
            	  							}
              },
              
              {name:'name',index:'name', width:100, formatter : function(cellValue, options, rowObject){	
            	  if(cellValue == 'test2'){
                      return '테스트2';
                 }else if(cellValue== 'test'){
                      return '테스트';
                 }else {
                	 return cellValue;
                 }
           	   }}, 
              {name:'amount',index:'amount', width:80, align:"right",sorttype:"float"},
              {name:'tax',index:'tax', width:80, align:"right",sorttype:"float"},      
              {name:'total',index:'total', width:80,align:"right",sorttype:"float"},       
              {name:'note',index:'note', width:150, sortable:false}
              ],
              caption: 'ex01', //그리드 제목
              rowNum : '10',
              pager : '#pager',
              pagination:true,
              multiselect : true,
              autowidth:true,
              height:"auto",
              sortname:"id",
              sortorder:"desc",
              postData : {
                  id : 'id',
                  name : 'name'
             },
             jsonReader:{
            	 repeatitems:false
             },
             ondblClickRow:function(id) {
            	 alert("You double click row with id:" +id);
            	 //id value is rgrid key set value
            	 //column property "key : true" default "key : false";
             },
             onSelectRow: function(id) {
            	 alert("You onSelectRow click row with id:" +id);
             },
             loadComplete : function(xhr) {
            	 alert("You loadComplete :");
             },
             loadError: function(xhr,st, err) {
            	 alert(err)
             },
             editurl:"userEdit"
          }).navGrid("#pager", {edit:true, add:true, del:true,search:true});
       
/* 			var mydata;
			$.getJSON('/web/postings/jQueryJson', function(data) {
			
			    datatype: 'json',
			        mydata = data;
			    // alert(mydata);
			
			});  
			 */
/* 	      var mydata = [ //로컬 데이터
	          {id:"1",invdate:"2007-10-01",name:"test",note:"note",amount:"200",tax:"10.00",total:"210.00"},
	         {id:"2",invdate:"2007-10-02",name:"test2",note:"note2",amount:"300",tax:"20.00",total:"320.00"},
	         {id:"3",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400",tax:"30.00",total:"430.00"},
	         {id:"4",invdate:"2007-10-04",name:"test",note:"note",amount:"200",tax:"10.00",total:"210.00"},
	         {id:"5",invdate:"2007-10-05",name:"test2",note:"note2",amount:"300",tax:"20.00",total:"320.00"},
	         {id:"6",invdate:"2007-09-06",name:"test3",note:"note3",amount:"400",tax:"30.00",total:"430.00"},
	         {id:"7",invdate:"2007-10-04",name:"test",note:"note",amount:"200",tax:"10.00",total:"210.00"},
	         {id:"8",invdate:"2007-10-03",name:"test2",note:"note2",amount:"300",tax:"20.00",total:"320.00"},
	         {id:"9",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400",tax:"30.00",total:"430.00"},
	         {id:"10",invdate:"2007-10-05",name:"test2",note:"note2",amount:"300",tax:"20.00",total:"320.00"},
	         {id:"11",invdate:"2007-09-06",name:"test3",note:"note3",amount:"400",tax:"30.00",total:"430.00"},
	         {id:"12",invdate:"2007-10-04",name:"test",note:"note",amount:"200",tax:"10.00",total:"210.00"},
	         {id:"13",invdate:"2007-10-03",name:"test2",note:"note2",amount:"300",tax:"20.00",total:"320.00"},
	         {id:"14",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400",tax:"30.00",total:"430.00"}
	         ];
	         for(var i=0;i<=mydata.length;i++){ //그리드에 로컬데이터 추가
	           jQuery("#list").jqGrid('addRowData',i+1,mydata[i]);
	         } */
         
		 jQuery("#list") .jqGrid({
		        pager : '#pager',
		        recordtext: "View {0} - {1} of {2}",
		     	emptyrecords: "No records to view",
		  			loadtext: "Loading...",
		  		pgtext : "Page {0} of {1}"
		});   
         
		   $.extend($.jgrid.edit, {
		       closeAfterAdd: true,
		       recreateForm: true,
		       reloadAfterSubmit: false,
		       left : 100,
		       top : 100,
		       dataheight: '100%',
		       width: 500 ,
		       addCaption: "유저 추가",
		 editCaption: "유저 편집",
		 bSubmit: "저장-전송",
		 bCancel: "취소",
		 bClose: "닫기",
		 saveData: "Data has been changed! Save changes?",
		 bYes : "Yes",
		 bNo : "No",
		 bExit : "Cancel"
		});
    });
                      
    </script>
<table id="list"></table>
<div id="pager"></div>
</body>
</html>