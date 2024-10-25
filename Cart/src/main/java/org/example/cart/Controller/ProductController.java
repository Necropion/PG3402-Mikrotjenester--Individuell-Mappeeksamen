package org.example.cart.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cart.EventDriven.DealershipEvent;
import org.example.cart.Exception.InvalidDataException;
import org.example.cart.Model.CarD;
import org.example.cart.Repository.CarInfoRepository;
import org.example.cart.Utility.ConsoleColor;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final CarInfoRepository carInfoRepository;

    @GetMapping("/car/{productId}")
    public CarD getCarInfo(@PathVariable Long productId) {
        log.info(ConsoleColor.Green("Get car info for Product ID: {}"), productId);
        CarD carProduct = carInfoRepository.getCarByProductId(productId);

        log.info(ConsoleColor.Green("Car Product retrieved from Dealership {}"), carProduct);
        return carProduct;
    }

    @PostMapping("/car")
    public void postCarStockChanges(@RequestBody DealershipEvent dealershipEvent) {
        log.info(ConsoleColor.Green("Post car stock changes: {}"), dealershipEvent);
        if (dealershipEvent.getProductId() == null || dealershipEvent.getQuantity() == null) {
            throw new InvalidDataException("Invalid Stock Change Data");
        }

        carInfoRepository.postProductStockChanges(dealershipEvent);
        log.info(ConsoleColor.Green("Car Stock Changed!"));
    }
}
