package com.order.orderkafka.repo;


import com.order.orderkafka.model.Orders;
import com.order.orderkafka.model.Updates;

import java.util.List;


public interface OrderRepo{
    List<Orders> getAllOrders();
    Orders getOrderById(String orderId);
    Orders saveOrder(Orders orders);
    List<Orders> getOrderByZip(int zip);
    Orders orderCancel(String id);
    Orders updateOrderStatus(Updates updates);
}
