package org.example.cart.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cart.EventDriven.DealershipEvent;
import org.example.cart.Model.CarD;
import org.example.cart.Repository.CarInfoRepository;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final CarInfoRepository carInfoRepository;

    @GetMapping("/car/{productId}")
    public CarD getCarInfo(@PathVariable Long productId) {
        return carInfoRepository.getCarByProductId(productId);
    }

    @PostMapping("/car")
    public void postCarStockChanges(@RequestBody DealershipEvent dealershipEvent) {
        carInfoRepository.postProductStockChanges(dealershipEvent);
    }
}
