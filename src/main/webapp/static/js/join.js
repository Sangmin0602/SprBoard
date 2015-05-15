
var dialogConfig = {
	autoOpen : false,
	height : 500,
	width : 400,
	modal : true
};
var $win ;
function showZipWin(e, sWinId) {
	//sWinId = sWinId || '#zipcodeWin';
	//var $win = $(sWinId);
	$win = $('#zipcodeWin').dialog ( dialogConfig );
	$win.dialog('open');
}


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

function renderAddresses ( json ) {
	console.log( 'total : ' + json.total);
	var data = json.data;
	var $result = $('#result');
	$result.empty();
	
	var $template = $('#template-zipcode-elem').html();
	for ( var i = 0 ; i < data.length ; i++ ) {
		var seq = data[i].seq;
		var zip0 = data[i].zipcode.substring(0, 3);
		var zip1 = data[i].zipcode.substring(3);
		var mainAddr = data[i].mainaddr;
		var fullAddr = data[i].fulladdr;
		
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
/**
 * 읍/면/동 요청 전송
 * @param sQuery
 */
function sendZipCodeRequest ( sQuery ) {
	var url = ctxpath + '/addr/umd';
	$.post ( url, {q: sQuery}, function (json ){
		console.log ( json );
		renderAddresses ( json );
	} );
}

$(document).ready ( function() {
	$('#zip0').click ( showZipWin );
	$('#zip1').click ( showZipWin );
	
	//$('#zipcodeWin').dialog('open');
	var $umd = $('#umd');
	$umd.keyup ( function ( e ){
		if ( e.keyCode == 13) {
			sendZipCodeRequest( $umd.val() );
		}
	});
});