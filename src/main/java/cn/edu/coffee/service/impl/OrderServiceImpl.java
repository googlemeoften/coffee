package cn.edu.coffee.service.impl;

import cn.edu.coffee.mapper.OrderItemMapper;
import cn.edu.coffee.mapper.OrderMapper;
import cn.edu.coffee.mapper.ProductMapper;
import cn.edu.coffee.mapper.UserMapper;
import cn.edu.coffee.model.Order;
import cn.edu.coffee.model.OrderItem;
import cn.edu.coffee.model.Product;
import cn.edu.coffee.model.User;
import cn.edu.coffee.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private UserMapper userMapper;

	@Override
	public void createOrder(Order order) {
		orderMapper.insertOrder(order);

		List<OrderItem> orderItems = order.getItemList();
		for (OrderItem item : orderItems) {
			item.setOrderId(order.getOrderId());
			orderItemMapper.insertItem(item);
		}
	}

	@Override
	public Order findOrderByExtendId(String extendId) {
		Order order = orderMapper.getOrderByExtendId(extendId);
		fullOrderInfo(order);
		return order;
	}

	@Override
	public List<Order> findOrderByStatus(int status) {
		List<Order> orders = orderMapper.getOrderByStatus(status);

		fullOrderInfo(orders);
		fullUserInfo(orders);

		return orders;
	}

	@Override
	public Order findOrderByOrderId(int orderId) {
		return orderMapper.getOrderById(orderId);
	}

	@Override
	public List<Order> findOrderByUserId(int userId) {
		List<Order> orders = orderMapper.getOrderByUserId(userId);

		fullOrderInfo(orders);

		return orders;
	}

	@Override
	public List<Order> findOrderBetweenTime() {
		return null;
	}

	@Override
	public void editOrderStatus(int orderId, int status) {
		Order order = new Order();
		order.setOrderId(orderId);
		order.setStatus(status);
		orderMapper.updateOrderStatus(order);
	}

	@Override
	public List<Order> findOrderToday() {
		List<Order> orders =  orderMapper.getOrderToday();
		fullOrderInfo(orders);

		return orders;
	}

	public void fullOrderInfo(List<Order> orders) {

		if (orders == null)
			return;

		for (Order order : orders) {
			List<OrderItem> itemList = orderItemMapper.getItemByOrderId(order
					.getOrderId());
			for (OrderItem item : itemList) {
				Product product = productMapper.findProductById(item
						.getProductId());
				item.setProduct(product);
			}
			order.setItemList(itemList);
		}
	}

	private void fullOrderInfo(Order order) {
		if (order == null)
			return;

		List<OrderItem> itemList = orderItemMapper.getItemByOrderId(order
				.getOrderId());
		for (OrderItem item : itemList) {
			Product product = productMapper
					.findProductById(item.getProductId());
			item.setProduct(product);
		}
		order.setItemList(itemList);
	}

	private void fullUserInfo(List<Order> orders) {
		if (orders == null) {
			return;
		}

		for (Order order : orders) {
			int userId = order.getUserId();
			User user = new User();
			user.setUserId(userId);
			User result = userMapper.findUserByProperties(user);
			order.setUser(result);
		}
	}
}
