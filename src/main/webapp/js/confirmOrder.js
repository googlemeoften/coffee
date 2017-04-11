$(function(){
	value = JSON.parse(localStorage.getItem("product"));
	var html='';
	html+='<tr>'
	+'<td>'
	+'</span><img class="img-thumbnail" src="../'+value.productPhoto+'"><span> '+value.productName+' </span>'
	+'</td>'
	+'<td>';
	if(value.onSale){
		html+='<span>￥</span><span class="price">'+value.productDiscountPrice+'</span>';
	}else{
		html+='<span>￥</span><span class="price">'+value.productOriginalPrice+'</span>';
	}

	html+='</td>'
	+'<td><span><input type="text" name="count" value="1"></span></td>'
	+'<td>';

	if(value.onSale){
		html+='<span>￥</span><span class="cost">'+value.productDiscountPrice+'</span>';
	}else{
		html+='<span>￥</span><span class="cost">'+value.productOriginalPrice+'</span>';
	}

	html+='</td>'
	+'<td><button class="btn btn-danger delete">刪除</button></td>';

	$('.table').append(html); 		 

	$('input[name="count"]').on("blur",function(){
				val=$('input[name="count"]').val();
				val2=$(this).parent().parent().prev().children().eq(1).html();
				val3=Number(val)*Number(val2);
				$(this).parent().parent().next().children().eq(1).html(val3);
			});

			$('.delete').on("click",function(){
				$(this).parent().parent().hide();
			});
});