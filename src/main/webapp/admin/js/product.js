$(function(){
	//首页数据获取
	$.get("../product/indexProdect.action",function(data){
		
		for(var i=0;i<data.length;i++){
			var strHtml='';
			for ( var j = 0; j < data[i].length; j++) {
				strHtml += '<div class="col-md-3">'
					+' <div class="thumbnail">'
					+'<img src="../'+data[i][j].productPhoto+'">'
					+'<div class="caption">'
					+'<h3 productId="'+data[i][j].productId+'">'+data[i][j].productName+'</h3>';

				strHtml+='<p>原价 ￥：<span>'+data[i][j].productOriginalPrice+'</span></p>';	
				strHtml+='<p>折扣价 ￥：<span>'+data[i][j].productDiscountPrice+'</span></p>';	
				strHtml+='<p>'
					+'<button  class="btn btn-default edit">修改</button>'
					+'<button class="btn btn-default delete">购买</button>'
					+'</p>'
					+'</div>'
					+'</div>'
					+'</div>';
			}
			$(".row").eq(i).append(strHtml);
		}
		//给该类添加事件
		$('.edit').on("click",function(){
				val=$(this).parent().prev().prev().attr("productId");
				$.ajax({
					url:"./order/editProduct.action",
					data:"productId="+val,
					success:function(result){
						alert(result);
					}
				});
		});
		//给该类添加事件
		$('.delete').on("click",function(){
			//alert("click...");
			val=$(this).parent().prev().prev().attr("productId");
			price=$(this).parent().prev().children().html();
			$.ajax({
				url:"./order/deleteProduct.action",
				data:"productId="+val+"&price="+price,
				success:function(result){
					alert(result);
				}
			});
		});
	});
});