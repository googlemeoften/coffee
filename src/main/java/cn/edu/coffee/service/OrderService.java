package cn.edu.coffee.service;

import cn.edu.coffee.model.Order;

import java.util.List;

/**
 * Created by HeYong on 2017/3/14.
 */
public interface OrderService {

    public void createOrder(Order order);

    public Order findOrderByExtendId(String extendId);

    //查找某种状态的订单（0：未付款，1：已付款未收到产品，2：付款收到产品）
    public List<Order> findOrderByStatus(int status);

    public Order findOrderByOrderId(int orderId);

    public List<Order> findOrderByUserId(int userId);

    public List<Order> findOrderBetweenTime();

    public void editOrderStatus(int orderId,int status);

    public List<Order> findOrderToday();
}
