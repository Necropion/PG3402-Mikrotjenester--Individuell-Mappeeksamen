package com.vehicle.dealership.Controller;

import com.vehicle.dealership.Model.Car;
import com.vehicle.dealership.Service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/car")
public class CarController {

    private final CarService carService;

    @GetMapping
    public List<Car> getAllCars() {
        return carService.fetchAllCars();
    }

    @GetMapping("/{product_id}")
    public Car getCarByProductId(@PathVariable Long product_id) {
        return carService.fetchCarByProductId(product_id);
    }

    @PostMapping
    public Car addCar(@RequestBody Car car) {
        return carService.addCar(car);
    }
}
