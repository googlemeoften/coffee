$(function(){

	$.ajax({
		url:"../order/ownOrder.action",
		success:function(data){
			var html='';
			for(var i=0;i<data.length;i++){
				html+='<tr>'
				+'<td class="extendId">'+data[i].extendId+'</td>'
				+'<td>';
				//alert(data[i].itemList.length);
				for(var j=0;j<data[i].itemList.length;j++){
					html+='<img class="photo img-thumbnail" src="../'+data[i].itemList[j].product.productPhoto+'">';
				}
				html+='</td>'
				+'<td>'
				+'<span>￥</span>'
				+'<span>'+data[i].totalPrice+'</span>'
				+'</td>'
				+'<td>'
				+'<span class="data">'+data[i].transactionTime+'</span>'
				+'</td>'
				+'<td class="status">'+data[i].status+'</td>'
				+'<td class="delete"><span>删除</span></td>'
				+'</tr>';
			}
			//alert(html);
			$('table').append(html);
		}
	});
});