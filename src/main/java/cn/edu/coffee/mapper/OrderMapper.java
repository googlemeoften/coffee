package cn.edu.coffee.mapper;

import cn.edu.coffee.model.Order;

import java.util.List;

/**
 * Created by HeYong on 2017/3/13.
 */
public interface OrderMapper {

    public int insertOrder(Order order);

    public void updateOrderStatus(Order order);

    public Order getOrderById(int orderId);

    public List<Order> getOrderByUserId(int userId);

    public Order getOrderByExtendId(String extendId);

    public List<Order> getOrderByStatus(int status);

    public List<Order> getOrderToday();

}
