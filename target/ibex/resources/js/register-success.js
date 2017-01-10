$(document).ready(function (){
	var myVar = setInterval(myTimer, 10000);
	function myTimer() {
		var url = $('#cpurl').val();
	    window.location.assign(url);
	}
	
	$('#timer').on('click', function(e){
		var url = $('#cpurl').val();
		window.location.assign(url);
	});
});