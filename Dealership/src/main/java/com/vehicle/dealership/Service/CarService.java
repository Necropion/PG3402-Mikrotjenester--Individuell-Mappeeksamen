package com.vehicle.dealership.Service;

import com.vehicle.dealership.Model.Car;
import com.vehicle.dealership.Repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public List<Car> fetchAllCars() {
        return carRepository.findAll();
    }

    public Car fetchCarByProductId(Long product_id) {
        return carRepository.findByProductId(product_id);
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }
}
