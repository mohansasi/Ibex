$(document).ready(function (){
	    if (self === top) {
	        var antiClickjack = document.getElementById("antiClickjack");
	        antiClickjack.parentNode.removeChild(antiClickjack);
	        var topSectionHeight=$("#ahProfileDetailsFragment").innerHeight();
	    	var logoInnerHeight=$( "#navFragment").innerHeight();
	    	var totalHeight=logoInnerHeight+topSectionHeight+10;
	    	
	    	if(topSectionHeight!= undefined && topSectionHeight!= null && topSectionHeight>0){
	    		$( ".ah-prof-container" ).css("margin-top",totalHeight+"px");
	    		$( ".fixed-nav-bar" ).css("top",totalHeight+"px");
	    		$('#ahProfileDetailsFragment').css('top',logoInnerHeight+'px');
	    		$('.ah-summery-prof-nav').css('top',logoInnerHeight+'px');
	    	}else{
	    		$( ".ah-search-container" ).css("margin-top",logoInnerHeight+10+"px");
	    	}
	    	
	    	/** for Onboarding tab left menu pages
	    	 */
	    	var topBulkUploadSectionHeight=$("#bulk-upload-navId").innerHeight();
	    	var totalBulkHeight=logoInnerHeight+topBulkUploadSectionHeight+10;
	    	if(topBulkUploadSectionHeight!= undefined && topBulkUploadSectionHeight!= null && topBulkUploadSectionHeight>0){
	    		$('#bulk-upload-navId').css('top',logoInnerHeight+'px');
	    		$( ".ah-prof-container" ).css("margin-top",totalBulkHeight+"px");
	    	}
	    	var topTaxFeeSectionHeight=$("#limits-fees-tax-navId").innerHeight();
	    	var totalFeeHeight=logoInnerHeight+topTaxFeeSectionHeight+10;
	    	if(topTaxFeeSectionHeight!= undefined && topTaxFeeSectionHeight!= null && topTaxFeeSectionHeight>0){
	    		$('#limits-fees-tax-navId').css('top',logoInnerHeight+'px');
	    		$( ".ah-prof-container" ).css("margin-top",totalFeeHeight+"px");
	    	}
	    	/**
	    	 * For displaying wizard buttons consistent in size
	    	 */
	    	var wizardPage= $("#personalInfoWizard").val();
	    	if(wizardPage!= null && wizardPage!= undefined){
	    		var highestBox = 0;
		        $('.reg-steps-btn').each(function(){
		            if($(this).height() > highestBox) 
		               highestBox = $(this).height(); 
		        });  
		        $('.reg-steps-btn').height(highestBox);
	    	}
	    	
	    } else {
	        top.location = self.location;
	    }
	 
		/**
		 * allow csrf token to be passed as header param from ajax request
		 */
		var token = $("input[name='_csrf']").val();
		var header = "X-CSRF-TOKEN";
	    $(document).ajaxSend(function(e, xhr, options) {
	      xhr.setRequestHeader(header, token);
	    });
	    
		/**
		 * function to prevent/restrict the browser bck/fwd button functionality 
		 */
		 window.location.hash="";
		 window.location.hash="NO";
		 window.onhashchange=function(){window.location.hash="";}

      /**
       * disable the right click functionality
       */  
        $(this).bind("contextmenu", function(e) {
            e.preventDefault();
            $("#disableMouseClickOverlay").css("display","block");
			$("#disableMouseClickModal").addClass("in");
			$("#disableMouseClickModal").attr("aria-hidden","true");
			$("#disableMouseClickModal").attr("role","dialog");
			$("#disableMouseClickModal").attr("aria-labelledby","myModalLabel");
			$("#disableMouseClickModal").show();
        });
        $('.disableRightClickModalClose').on('click', function(e){
    		$("#disableMouseClickModal").hide();
    		$("#disableMouseClickOverlay").hide();
    	});
        
        $('.menu-icon').on('click',function(){
        	$('.quisk-left-container').toggle();
        });
        
        $(window).resize(function(){
        	var winW = $(window).width();
        	
        	if(winW >768){
        		$('.quisk-left-container').removeAttr('style');
        	}
        });
        
     // Start of Jquery data table custom sorting
    	jQuery.fn.dataTableExt.oSort['custom_date-asc'] = function(x, y) {
    	    var xVal = getCustomDateValue(x);
    	    var yVal = getCustomDateValue(y);
    	 
    	    if (xVal < yVal) {
    	        return -1;
    	    } else if (xVal > yVal) {
    	        return 1;
    	    } else {
    	        return 0;
    	    }
    	}
    	 
    	jQuery.fn.dataTableExt.oSort['custom_date-desc'] = function(x, y) {
    	    var xVal = getCustomDateValue(x);
    	    var yVal = getCustomDateValue(y);
    	 
    	    if (xVal < yVal) {
    	        return 1;
    	    } else if (xVal > yVal) {
    	        return -1;
    	    } else {
    	        return 0;
    	    }
    	}
    	 
    	function getCustomDateValue(strDate) {
    	    var frDatea = $.trim(strDate).split('/');
    	    var x = (frDatea[2] + frDatea[1] + frDatea[0]);
    	    x = x * 1;
    	    return x;
    	}
    	
    	// Start of Jquery data table custom sorting
    	jQuery.fn.dataTableExt.oSort['custom_dateMonthYear-asc'] = function(x, y) {
    		var xVal = getCustomDateMonthYearValue(x);
    		var yVal = getCustomDateMonthYearValue(y);	 
    		if (xVal < yVal) {
    			return -1;
    		} else if (xVal > yVal) {
    			return 1;
    		} else {
    			return 0;
    		}
    	}

    	jQuery.fn.dataTableExt.oSort['custom_dateMonthYear-desc'] = function(x, y) {
    		var xVal = getCustomDateMonthYearValue(x);
    		var yVal = getCustomDateMonthYearValue(y); 	 
    		if (xVal < yVal) {
    			return 1;
    		} else if (xVal > yVal) {
    			return -1;
    		} else {
    			return 0;
    		}
    	}

    	function getCustomDateMonthYearValue(strDate) {
    		var frDatea = $.trim(strDate).split('-');
    		var month=getMonthId(frDatea[1]);
    		var x = (frDatea[2] + month + frDatea[0] + frDatea[0]);
    		x = x * 1;
    		return x;
    	}
    	
    	function getMonthId(monthName){
    		var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
    		var monthId=jQuery.inArray(monthName, months);
    		if(monthId <= 9){
    			monthId = "0" + monthId;
    		}
    		return monthId;
    	}
    	// End of Jquery data table custom sorting
    	
    	// Start of Jquery data table custom sorting of date/time format 
    	jQuery.fn.dataTableExt.oSort['custom_dateTime-asc'] = function(x, y) {
    	    var xVal = getCustomDateTimeValue(x);
    	    var yVal = getCustomDateTimeValue(y);
    	 
    	    if (xVal < yVal) {
    	        return -1;
    	    } else if (xVal > yVal) {
    	        return 1;
    	    } else {
    	        return 0;
    	    }
    	}
    	 
    	jQuery.fn.dataTableExt.oSort['custom_dateTime-desc'] = function(x, y) {
    	    var xVal = getCustomDateTimeValue(x);
    	    var yVal = getCustomDateTimeValue(y);
    	 
    	    if (xVal < yVal) {
    	        return 1;
    	    } else if (xVal > yVal) {
    	        return -1;
    	    } else {
    	        return 0;
    	    }
    	}
    	 
    	function getCustomDateTimeValue(strDate) {
    		if(strDate!= ''){
    	    var frDatea = $.trim(strDate).split(' ');
    	    if(frDatea!= ''){
    	    var frTimea = frDatea[1].split(':');
    	    var frDatea2 = frDatea[0].split('-');
    	    var month=getMonthId(frDatea2[1]);
    	    var x = (frDatea2[2] + month + frDatea2[0] + frTimea[0] + frTimea[1] + frTimea[2]);
    	    x = x * 1;
    	    	}
    		}
    	    return x;
    	}
    	// End of Jquery data table custom sorting of date/time format
    	
    	/**
    	 *  Close/Open the overlay for Profile view
    	 */
    	$("#profileView").on("click", function(e) {
    		$("#profileOverlay").attr("class","modal-backdrop fade in");
    		$("#profileOverlay").css("display","block");
    		$("#profilemodal").addClass("in");
    		$("#profilemodal").attr("aria-hidden","true");
    		$("#profilemodal").attr("role","dialog");
    		$("#profilemodal").attr("aria-labelledby","myModalLabel");
    		$("#profilemodal").show();
    	});
    	
    	$(".closeProfileView").on("click", function(e) {
    		$("#profilemodal").css("display","none");
    		$("#profilemodal").attr("aria-hidden","false");
    		$("#profilemodal").removeClass("fade in");
    		$("#profileOverlay").removeClass("fade in");
    		$("#profileOverlay").css("display","none");
    	});
    	
    	$(window).resize(function(){
    		var topSectionHeight=$("#ahProfileDetailsFragment").innerHeight();
	    	var logoInnerHeight=$( "#navFragment").innerHeight();
	    	var totalHeight=logoInnerHeight+topSectionHeight+10;
	    	
	    	/** for Onboarding tab left menu pages
	    	 */
	    	var topBulkUploadSectionHeight=$("#bulk-upload-navId").innerHeight();
	    	var totalBulkHeight=logoInnerHeight+topBulkUploadSectionHeight+10;
	    	if(topBulkUploadSectionHeight!= undefined && topBulkUploadSectionHeight!= null && topBulkUploadSectionHeight>0){
	    		$('#bulk-upload-navId').css('top',logoInnerHeight+'px');
	    		$( ".ah-prof-container" ).css("margin-top",totalBulkHeight+"px");
	    	}
	    	var topTaxFeeSectionHeight=$("#limits-fees-tax-navId").innerHeight();
	    	var totalFeeHeight=logoInnerHeight+topTaxFeeSectionHeight+10;
	    	if(topTaxFeeSectionHeight!= undefined && topTaxFeeSectionHeight!= null && topTaxFeeSectionHeight>0){
	    		$('#limits-fees-tax-navId').css('top',logoInnerHeight+'px');
	    		$( ".ah-prof-container" ).css("margin-top",totalFeeHeight+"px");
	    	}
	    	
	    	if(topSectionHeight!= undefined && topSectionHeight!= null && topSectionHeight>0){
	    		$( ".ah-prof-container" ).css("margin-top",totalHeight+"px");
	    		$( ".fixed-nav-bar" ).css("top",totalHeight+"px");
	    		$('#ahProfileDetailsFragment').css('top',logoInnerHeight+'px');
	    		$('.ah-summery-prof-nav').css('top',logoInnerHeight+'px');
	    		
	    	}else{
	    		$( ".ah-search-container" ).css("margin-top",logoInnerHeight+10+"px");
	    	}
	    	
	    	/**
	    	 * For displaying wizard buttons consistent in size
	    	 */
	    	
	    	$('.reg-steps-btn').css('height','');
	    	var wizardPageDiv= $("#personalInfoWizard").val();
	    	if(wizardPageDiv!= null && wizardPageDiv!= undefined){
	    		var highBox = 0;
		        $('.reg-steps-btn').each(function(){
		            if($(this).height() > highBox) 
		            	highBox = $(this).height(); 
		        });  
		        $('.reg-steps-btn').css("height",highBox+20);
	    	}
    	});
    	
	
});