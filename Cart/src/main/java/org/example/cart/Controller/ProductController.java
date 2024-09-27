package org.example.cart.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cart.Model.CarD;
import org.example.cart.Repository.CarInfoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final CarInfoRepository carInfoRepository;

    @GetMapping("/api/car/{productId}")
    public CarD getCarInfo(@PathVariable Long productId) {
        return carInfoRepository.getCarByProductId(productId);
    }
}
