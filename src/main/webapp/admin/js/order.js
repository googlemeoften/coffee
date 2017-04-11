$(function () {
    var url = window.location.search;
    var status = url.substring(url.lastIndexOf('=') + 1);
    //alert(status);

    $.post("../order/findOrderByStatus.action",{status:status},function(data){
        //console.log(data);
        var html='';
        for(var i=0;i<data.length;i++){
        	html+='<tr>'
        	+'<td>'+data[i].user.username+'</td>'
        	+'<td>';

        	for(var j=0;j<data[i].itemList.length;j++){
        		html+='<p><span>'+data[i].itemList[j].product.productName+'</span>-----<span>'+data[i].itemList[j].amount+'</span></p>';
        	}

        	html+='</td>'
        	+'<td>'+data[i].note+'</td>'
        	+'<td>已付款</td>'
        	+'<td><button class="btn flish" orderId='+data[i].orderId+'>完成</button></td>'
        	+'</tr>'
        }
        $('table').append(html);

        if(status!=2){
	        $('.flish').on("click",function(){
	        	var val = $(this).attr('orderId');
	        	//alert(val);
	        	$.post("../order/completeTransaction.action",{orderId:val},function(data){
	        		alert(data);
	        	});
	        });
        }
    });
});