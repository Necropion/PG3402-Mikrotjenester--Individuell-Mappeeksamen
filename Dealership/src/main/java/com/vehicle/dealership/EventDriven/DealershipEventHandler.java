package com.vehicle.dealership.EventDriven;

import com.vehicle.dealership.DTO.DealershipEventDTO;
import com.vehicle.dealership.Service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DealershipEventHandler {

    private final CarService carService;

    @RabbitListener(queues = "dealership.queue")
    void handleDealershipEvent(DealershipEventDTO event) {
        log.info("Received Dealership event from Cart: {}", event);
        try {
            carService.updateCarStock(event);
        } catch (Exception e) {
            log.error("Error processing Dealership event: {}", event, e);
        }
    }

}
