$(function(){

	$.ajax({
		url:"../order/shopCart.action",
		success:function(result){
			var html='';
			for(var i=0;i<result.length;i++){
				html+='<tr>'
				+'<td>'
				+'<img class="img-thumbnail" src="../'+result[i].productPhoto+'">'
				+'<span productId="'+result[i].productId+'"> '+result[i].productName+' </span>'
				+'</div>'
				+'</td>'
				+' <td>'
				+'<span>￥</span>';
				if(result[i].onSale){
					html+='<span class="price">'+result[i].productDiscountPrice+'</span>';		
				}else{
					html+='<span class="price">'+result[i].productOriginalPrice+'</span>';
				}
				html+='</td>'
				+'<td>'
				+'<span><input type="text" class="count" value="1"></span>'
				+'</td>'
				+'<td>'
				+'<span>￥</span>';
				if(result[i].onSale){
					console.log(333333333333333,result[i]);
					html+='<span class="cost">'+result[i].productDiscountPrice+'</span>';
				}else{
					console.log(444444444444444444,result[i]);
					html+='<span class="cost">'+result[i].productOriginalPrice+'</span>';
				}
				html+='</td>'
				+'<td>'
				+'<button class="btn btn-danger delete">刪除</button>'
				+'</td>'
				+'</td>';
			}
			$('.table').eq(0).append(html);
			
			// $('input[name="count"]').on("blur",function(){
			// 	val=$('input[name="count"]').val();
			// 	val2=$(this).parent().parent().prev().children().eq(1).html();
				
			// 	val3=Number(val*val2);
			// 	$(this).parent().parent().next().children().eq(1).html(val3);
			// });

			$('.count').on("blur",function(){
				var val = $(this).val();
				var val2 = $('.price').eq($('.count').index($(this))).html();
				console.log(22222222222222222222222222222,val,val2);
				var val3 = 	val * val2;

				$('.cost').eq($('.count').index($(this))).html(val3);
			});

			$('.delete').on("click",function(){
				var value = $("span[productId]").eq($('.delete').index($(this))).attr("productId");
				//alert(value);
				var obj = $(this);
				$.ajax({
					url:"../order/removeShopCart.action",
					data:"productId="+value,
					success: function(data){
						if(data){
							obj.parent().parent().empty();
						}
					}
				});
			});

			
			$('.calculate button').on("click",function(){
				var total=0;
				$('.cost').each(function(){
					total+=Number($(this).html());
				})
				$('.total').html(total); 
				//return false;
			});

			$('.confirm').on("click",function(){
				
				var array = new Array();
				$("span[productId]").each(function(){
					var obj = new Object();
					obj.productId=$(this).attr("productId");
					obj.amount = $('.count').eq($("span[productId]").index($(this))).val();
					obj.cost = $('.cost').eq($("span[productId]").index($(this))).html();
					//alert(obj.amount);
					array.push(obj);
				});

				var obj  = new Object();
				obj.productIds = array;
				totalPrice = $('.total').html();
				description = "null";
				
				$.post("../order/purchaseFromCart.action",
					{productIds:JSON.stringify(array),totalPrice:totalPrice,description:description},
					function(data){
						if(data){
							$(location).attr('href', './order.html');
						}
					}
				);
			});
		}
	});

});