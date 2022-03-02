package com.order.orderkafka.service.kafka;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.order.orderkafka.model.Orders;
import com.order.orderkafka.service.impl.DefaultOrderService;
import com.order.orderkafka.service.impl.EmailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {
    EmailServiceImpl emailService;
    DefaultOrderService defaultOrderService;

    public ConsumerService(EmailServiceImpl emailService, DefaultOrderService defaultOrderService){
        this.emailService=emailService;
        this.defaultOrderService=defaultOrderService;
    }
    @KafkaListener(containerFactory = "kafkaListenerContainerFactory",
    topics = "${kafka.topic.string-demo.name}",
    groupId = "${kafka.topic.string-demo.groupId}")
    public void consume(String message){ log.info(String.format("$$$$ => Consumed Message: %s", message));}

    @KafkaListener(containerFactory = "jsonKafkaListenerContainerFactory",
            topics = "${kafka.topic.json.name}",
            groupId = "${kafka.topic.json.groupId}")
    public void consumeCustomerData(Orders orders) throws JsonProcessingException {
        log.info("Consumed Message: {}, {}", orders.getId(), orders);
        System.out.println("Email Notification:"+ orders.getId() +orders+orders);
        defaultOrderService.addWeatherReadings(orders);
        emailService.sendSimpleMessage("jaymish9558@gmail.com", "Weather", orders.toString());
    }
}
