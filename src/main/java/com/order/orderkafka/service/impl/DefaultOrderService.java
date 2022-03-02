package com.order.orderkafka.service.impl;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.orderkafka.model.Orders;
import com.order.orderkafka.repo.OrderRepo;
import com.order.orderkafka.repo.OrderRepository;
import com.order.orderkafka.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DefaultOrderService implements OrderService {
    //List<Weather> list=new LinkedList<>();

    private OrderRepository orderRepository;
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;
    private OrderRepo orderRepo;

    @Autowired
    public DefaultOrderService(OrderRepository orderRepository, RestTemplate restTemplate, ObjectMapper objectMapper, OrderRepo orderRepo){
        this.orderRepository = orderRepository;
        this.restTemplate=restTemplate;
        this.objectMapper=objectMapper;
        this.orderRepo=orderRepo;
    }

    @Override
    public boolean addWeatherReadings(Orders orders) throws JsonProcessingException {
        orderRepo.saveOrder(orders);
        return true;
    }

    @Override
    public List<Orders> getWeatherReadingsSorted() {
        List<Orders> weatherList= (List<Orders>) orderRepository.findAll();
        return weatherList;
    }
}
