function pushToParent ( $address ) {
	function strip ( ch, text) {
		var as = text.split ( ch );
		var t = '';
		for ( var i  = 0 ; i < as.length ; i++ ) {
			if ( as[i].length > 0 ) {
				t += as[i] + ' ';
			}
		}
		return t.trim();
	}
	var z0 = $address.find('span.zipnum0').text();
	var z1 = $address.find('span.zipnum1').text();
	var addr = strip('@', $address.find('span.addr-main').text() );
	
	$('#zip0').val ( z0 );
	$('#zip1').val ( z1 );
	$('#addr-main').val ( addr );
}

function renderToZipWin ( json ) {
	//console.log( 'total : ' + json.total);
	var $win = $('#zipcodeWin');
	var data = json.data;
	var $result = $('#result');
	$result.empty();
	
	var $template = $('#template-zipcode-elem').html();
	alert(data.length);
	for ( var i = 0 ; i < data.length ; i++ ) {
		var seq = data[i].seq;
		var zip0 = data[i].zipcode.substring(0, 3);
		var zip1 = data[i].zipcode.substring(3);
		var mainAddr = data[i].mainAddr;
		var fullAddr = data[i].fullAddr;
		
		var row = $template;
		row = row.replace ( '{seq}', seq);
		row = row.replace ( '{zip0}', zip0);
		row = row.replace ( '{zip1}', zip1);
		row = row.replace ( '{mainaddr}', mainAddr);
		row = row.replace ( '{fulladdr}', fullAddr);
		$result.append ( row );
	}
	
	var $elems = $('#result .zipcode-elem'); 
	$elems.mouseover ( function () {
		$(this).css ( 'background-color', 'aqua');
	}).mouseout ( function() {
		$(this).css ( 'background-color', 'white');		
	}).click ( function() {
		pushToParent ( $(this) );
		$win.dialog('close');
	});
}


function searchUMD(){
	var umdVal = $('#umd').val();
	$.post(ctxpath + '/addr/umd',{q:umdVal},function(json){
		//console.log(json);
		renderToZipWin( json );
	})
}

function showZipWin ( e ) {
	
	var $win = $('#zipcodeWin').dialog ({width:500, height:400, modal : true});
	$win.dialog ('open');
	$('#btnSearch').click(searchUMD);
}

$(document).ready ( function () {
	$('#zip0').click ( showZipWin );
	$('#zip1').click ( showZipWin );
});
