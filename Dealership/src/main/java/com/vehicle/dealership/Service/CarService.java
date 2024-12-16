package com.vehicle.dealership.Service;

import com.vehicle.dealership.DTO.DealershipEventDTO;
import com.vehicle.dealership.Model.Car;
import com.vehicle.dealership.Model.OwnedCar;
import com.vehicle.dealership.Repository.CarRepository;
import com.vehicle.dealership.Repository.OwnedCarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final OwnedCarRepository ownedCarRepository;

    public List<Car> fetchAllCars() {
        return carRepository.findAll();
    }

    public Car fetchCarByProductId(Long product_id) {
        return carRepository.findByProductId(product_id);
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCarStock (DealershipEventDTO dealershipEventDTO) {
        Car car = carRepository.findByProductId(dealershipEventDTO.getProductId());

        if(car.getStock() < dealershipEventDTO.getQuantity()) {
            throw new RuntimeException("Not enough in stock of item ID: " + dealershipEventDTO.getProductId());
        }
        car.setStock(car.getStock() - dealershipEventDTO.getQuantity());

        for(int i = 0; i < dealershipEventDTO.getQuantity(); i++) {

            OwnedCar newOwnedCar = new OwnedCar();
            newOwnedCar.setUserId(dealershipEventDTO.getUserId());
            newOwnedCar.setProductId(dealershipEventDTO.getProductId());
            newOwnedCar.setReceiptId(dealershipEventDTO.getReceiptId());

            addOwnedCar(newOwnedCar);
        }

        return carRepository.save(car);
    }

    public OwnedCar addOwnedCar (OwnedCar ownedCar) {
        return ownedCarRepository.save(ownedCar);
    }
}
