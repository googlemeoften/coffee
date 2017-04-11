package cn.edu.coffee.mapper;

import cn.edu.coffee.model.OrderItem;

import java.util.List;

/**
 * Created by HeYong on 2017/3/13.
 */
public interface OrderItemMapper {

    public int insertItem(OrderItem item);

    public void updateAmount(OrderItem item);

    public List<OrderItem> getItemByOrderId(int orderId);

    public List<OrderItem> getItemByProductId(int productId);
}
