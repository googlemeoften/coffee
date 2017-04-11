$(function(){
	$.ajax({
		type: "POST",
		url:"../user/allUser.action"
		success: function(data){
	     alert( "Data Saved: " + msg );
	   }
	});
});