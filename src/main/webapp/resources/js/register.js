$(document).ready(function (){
	
	
	  //Refresh the login page after time interval completes
	 
	function timer() {
	    count = count - 1;
	    if (count == -1) {
	        clearInterval(counter);
	        return;
	    }
	    var seconds = count % 60;
	    var minutes = Math.floor(count / 60);
	    var hours = Math.floor(minutes / 60);
	    minutes %= 60;
	    hours %= 60;
	    location.reload();
	}
	
	
	$('#idName').on('change', function(e){
		var name=$('#idName').val();
		if(name != '' && name.trim().length != 0){
			var delimeter=$('#delimeterId').val();
			var arr = name.split(delimeter);
			if(arr[0] != '')
			  $('#idName').val(arr[0].toUpperCase().trim())
			//alert(arr[0]);
	    }
	});
	
	$('#btnRegister').on('click', function(e){
		var name=$('#idName').val();
		if(name == '' && name.trim().length == 0){
			$("#errorMessage").text("Name is required.");
			return false;
		}
		var phoneNumber=$('#idPhoneNumber').val();
		if(phoneNumber == '' && phoneNumber.trim().length == 0){
			$("#errorMessage").text("Phone Number is required.");
			return false;
		}else if(!isNumeric(phoneNumber)){
			$("#errorMessage").html("Enter a valid Phone Number.");
			return false;
		}else if (phoneNumber != '' && isNumeric(phoneNumber)){
			if(phoneNumber.length < 8 || phoneNumber.length > 10){
				$("#errorMessage").html("Enter a valid Phone Number.");
				return false;
			}
		}
		
		var formId = document.getElementById("registerForm").submit();
	})
	
	
	var isNumeric=function(number) {
		var dateRegex = new RegExp(/^[0-9]*$/);
		return dateRegex.test(number);
	}
	
	var isValidNumber=function(number) {
		var dateRegex = new RegExp(/^[0-9]\d{0,9}(\.\d{1,3})?%?$/);
		return dateRegex.test(number);
	}
});