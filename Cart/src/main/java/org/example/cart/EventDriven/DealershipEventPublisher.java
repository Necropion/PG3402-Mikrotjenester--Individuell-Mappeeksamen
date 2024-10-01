package org.example.cart.EventDriven;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DealershipEventPublisher {

    // Config AMQP
    // What exchange am I publishing to

    private final AmqpTemplate amqpTemplate;
    private final String exchangeName;

    public DealershipEventPublisher(
            final AmqpTemplate amqpTemplate,
            @Value("${amqp.exchange.name}") final String exchangeName
    ) {
        this.amqpTemplate = amqpTemplate;
        this.exchangeName = exchangeName;
    }

    public void publishDealershipEvent(DealershipEvent dealershipEvent) {
        String routingKey = "dealership.info";
        log.info("Here is the Rabbit message to Dealership {}", dealershipEvent);
        amqpTemplate.convertAndSend(exchangeName, routingKey, dealershipEvent);
    }
}
