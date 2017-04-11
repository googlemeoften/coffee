package cn.edu.coffee.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.coffee.model.Order;
import cn.edu.coffee.model.OrderItem;
import cn.edu.coffee.model.Product;
import cn.edu.coffee.model.User;
import cn.edu.coffee.service.OrderService;
import cn.edu.coffee.service.ProductService;

import cn.edu.coffee.utils.DataFormatUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @ResponseBody
    @RequestMapping("/appendCart")
    public boolean addToShopCart(Integer productId, HttpSession session) {
        System.out.println(productId);
        Set<Integer> shopCart = (Set<Integer>) session.getAttribute("shopcart");
        if (shopCart == null) {
            shopCart = new HashSet<Integer>();
        }
        shopCart.add(productId);
        session.setAttribute("shopcart", shopCart);
        return true;
    }

    @ResponseBody
    @RequestMapping("/removeShopCart")
    public boolean removeShopCart(Integer productId, HttpSession session) {
        System.out.println(productId);
        Set<Integer> shopCart = (Set<Integer>) session.getAttribute("shopcart");
        if (shopCart == null) {
            return true;
        }
        shopCart.remove(productId);
        session.setAttribute("shopcart", shopCart);
        return true;
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    @ResponseBody
    @RequestMapping("/shopCart")
    public List<Product> shopCart(HttpSession session) {
        Set<Integer> shopcart = (Set<Integer>) session.getAttribute("shopcart");
        List<Product> products = new ArrayList<Product>();
        if (shopcart != null) {
            for (Integer productId : shopcart) {
                products.add(productService.finProductById(productId));
            }
        }
        return products;
    }

    @ResponseBody
    @RequestMapping("/purchase")
    public Map<String, Object> purchase(int productId, int price) {
        boolean isSatisfy = true;
        verifyProduct(productId, price);
        Map<String, Object> result = new HashMap<String, Object>();
        if (isSatisfy) {
            Product product = productService.finProductById(productId);
            result.put("product", product);
            result.put("redirect", "./createOrder.html");
        } else {
            result.put("errorMsg", "订单出错");
        }

        return result;
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    @ResponseBody
    @RequestMapping("/ownOrder")
    public Object ownOrder(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setUserId(1);
        }
        List<Order> orders = orderService.findOrderByUserId(user.getUserId());

        return JSON.toJSON(orders);
    }

    @ResponseBody
    @RequestMapping("/purchaseFromCart")
    public boolean purchaseFromCart(String productIds, int totalPrice,
                                    String description, HttpSession session) {

        JSONArray jsonArray = JSON.parseArray(productIds);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setUserId(1);
        }

        Order order = new Order();

        String extendId = DataFormatUtils.getRandomStr();
        order.setExtendId(extendId);
        order.setTotalPrice(totalPrice);
        order.setTransactionTime(new Date());
        order.setStatus(0);

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            int productId = Integer.parseInt(obj.get("productId").toString()
                    .trim());
            int amount = Integer.parseInt(obj.get("amount").toString().trim());
            int cost = Integer.parseInt(obj.get("cost").toString().trim());

            OrderItem item = new OrderItem();
            item.setAmount(amount);
            Product product = productService.finProductById(productId);
            int total = 0;
            if (product.isOnSale()) {
                total = amount * product.getProductDiscountPrice();
            } else {
                total = amount * product.getProductOriginalPrice();
            }

            if (cost != total) {
                return false;
            }
            item.setProductId(productId);
            if (product.isOnSale()) {
                item.setUnitPrice(product.getProductDiscountPrice());
            } else {
                item.setUnitPrice(product.getProductOriginalPrice());
            }
            item.setAmount(amount);
            item.setTotalPrice(total);
            order.addItem(item);
            order.setUserId(user.getUserId());
        }
        orderService.createOrder(order);

        return true;
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    @ResponseBody
    @RequestMapping("/findOrderByStatus")
    public Object findOrderByStatus(int status, HttpSession session) {
        List<Order> orders = orderService.findOrderByStatus(status);

        return JSON.toJSON(orders);
    }

    @ResponseBody
    @RequestMapping("/completeTransaction")
    public boolean completeTransaction(int orderId) {
        Order order = orderService.findOrderByOrderId(orderId);

        if (order != null) {

            orderService.editOrderStatus(orderId, 2);
        }

        return true;
    }

    private boolean verifyProduct(int productId, int price) {
        Product product = productService.finProductById(productId);
        if (product.isOnSale()) {
            if (product.getProductDiscountPrice() == price) {
                return true;
            }
        } else {
            if (product.getProductOriginalPrice() == price) {
                return true;
            }
        }

        return false;
    }

}
