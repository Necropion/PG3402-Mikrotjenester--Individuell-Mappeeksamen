package com.vehicle.dealership.EventDriven;

import com.vehicle.dealership.DTO.DealershipEventDTO;
import com.vehicle.dealership.Exception.EventProcessingException;
import com.vehicle.dealership.Service.CarService;
import com.vehicle.dealership.Utility.ConsoleColor;
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
        log.info(ConsoleColor.Green("Received Dealership event from Cart: {}"), event);
        try {
            log.info(ConsoleColor.Green("Car Stock Updated!: {}"), event);
            carService.updateCarStock(event);
        } catch (Exception e) {
            String errorString = "Error processing event: " + event;
            throw new EventProcessingException(errorString);
        }
    }

}
