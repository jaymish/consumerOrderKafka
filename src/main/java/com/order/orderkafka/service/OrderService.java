package com.order.orderkafka.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.order.orderkafka.model.Orders;
import com.order.orderkafka.model.Updates;

import java.util.List;

public interface OrderService {
    boolean addWeatherReadings(Orders orders) throws JsonProcessingException;
    Orders cancelOrder(String id);
    Orders updateOrder(Updates updates);
}
