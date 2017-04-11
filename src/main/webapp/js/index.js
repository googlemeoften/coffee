$(function(){
	//首页数据获取
	$.get("./product/indexProdect.action",function(data){
		
		for(var i=0;i<data.length;i++){
			var strHtml='';
			for ( var j = 0; j < data[i].length; j++) {
				strHtml += '<div class="col-md-3">'
					+' <div class="thumbnail">'
					+'<img src="'+data[i][j].productPhoto+'">'
					+'<div class="caption">'
					+'<h3 productId="'+data[i][j].productId+'">'+data[i][j].productName+'</h3>';

					if(data[i][j].onSale){
						
						strHtml+='<p>￥：<span>'+data[i][j].productDiscountPrice+'</span></p>';
					}else{
						
						strHtml+='<p>￥：<span>'+data[i][j].productOriginalPrice+'</span></p>';
					}
				strHtml+='<p>'
					+'<button  class="btn btn-default cart">加入购物车</button>'
					+'<button class="btn btn-default buy">购买</button>'
					+'</p>'
					+'</div>'
					+'</div>'
					+'</div>';
			}
			$(".row").eq(i).append(strHtml);
		}
		//给该类添加事件
		$('.cart').on("click",function(){
				val=$(this).parent().prev().prev().attr("productId");
				$.ajax({
					url:"./order/appendCart.action",
					data:"productId="+val,
					success:function(result){
						alert(result);
					}
				});
		});
		//给该类添加事件
		$('.buy').on("click",function(){
			//alert("click...");
			val=$(this).parent().prev().prev().attr("productId");
			price=$(this).parent().prev().children().html();
			$.ajax({
				url:"./order/purchase.action",
				data:"productId="+val+"&price="+price,
				success:function(data){
					//alert(result);
					if(data.errorMsg!=null){
						alert(data.errorMsg);
					}else{
						localStorage.setItem("product",JSON.stringify(data.product));
						//alert(localStorage.getItem("product"));
						$(location).attr('href', './html/confirmOrder.html');
					}
				}
			});
		});
	});

	if(sessionStorage.getItem('user')!=null){
		$('#user').hide();
	}
});