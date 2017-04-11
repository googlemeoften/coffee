$(function(){
	$.get("../product/food.action",function(data){
		var strHtml='';
		for ( var i = 0; i < data.length; i++) {
			strHtml += '<div class="col-md-3">'
				+' <div class="thumbnail">'
				+'<img src="../'+data[i].productPhoto+'">'
				+'<div class="caption">'
				+'<h3 productId="'+data[i].productId+'">'+data[i].productName+'</h3>';

			if(data[i].onSale){
				strHtml+='<p>￥：<span>'+data[i].productDiscountPrice+'</span></p>';
			}else{
				strHtml+='<p>￥：<span>'+data[i].productOriginalPrice+'</span></p>';
			}

			strHtml+='<p>'
				+'<button  class="btn btn-default cart">加入购物车</button>'
				+'<button class="btn btn-default buy">购买</button>'
				+'</p>'
				+'</div>'
				+'</div>'
				+'</div>';
		}
		$(".row").append(strHtml);
		$('.cart').on("click",function(){
			val=$(this).parent().prev().prev().attr("productId");
			$.ajax({
				url:"../order/appendCart.action",
				data:"productId="+val,
				success:function(result){
					alert(result);
				}
			});
		});

		$('.buy').on("click",function(){
			//alert("click...");
			val=$(this).parent().prev().prev().attr("productId");
			price=$(this).parent().prev().children().html();
			$.ajax({
				url:"../order/purchase.action",
				data:"productId="+val+"&price="+price,
				success:function(data){
					//alert(result);
					if(data.errorMsg!=null){
						alert(data.errorMsg);
					}else{
						localStorage.setItem("product",JSON.stringify(data.product));
						//alert(localStorage.getItem("product"));
						$(location).attr('href', './confirmOrder.html');
					}
				}
			});
		});
	});

});