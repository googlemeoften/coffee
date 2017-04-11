package cn.edu.coffee.schedule;


import cn.edu.coffee.mapper.OrderMapper;
import cn.edu.coffee.model.Order;
import cn.edu.coffee.model.OrderItem;
import cn.edu.coffee.service.OrderService;
import cn.edu.coffee.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 资金结算任务
 */

public class CalculateTask {
    @Autowired
    private OrderService orderService;


    public void run() {
        List<Order> orders = orderService.findOrderToday();

        HashMap<String, Object> data = new HashMap<>();
        int totalProfits = 0;

        for (Order order : orders) {
            for (OrderItem item : order.getItemList()) {
                String productName = item.getProduct().getProductName();
                Integer amount = (Integer) data.get(productName);
                if (amount == null) {
                    data.put(productName, item.getAmount());
                } else {
                    data.put(productName, amount + item.getAmount());
                }
            }

            totalProfits += order.getTotalPrice();
        }

        try {
            MailUtils.sendMail("今日收入结算",data,totalProfits);
        } catch (MessagingException e) {
            System.out.println("y邮件发送失败。。。。。。。。。。");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
