package com.vehicle.dealership.Controller;

import com.vehicle.dealership.DTO.DealershipEventDTO;
import com.vehicle.dealership.Exception.InvalidDataException;
import com.vehicle.dealership.Exception.ItemProductIDExistsException;
import com.vehicle.dealership.Exception.ItemNotFoundException;
import com.vehicle.dealership.Model.Car;
import com.vehicle.dealership.Model.OwnedCar;
import com.vehicle.dealership.Repository.CarRepository;
import com.vehicle.dealership.Repository.OwnedCarRepository;
import com.vehicle.dealership.Service.CarService;
import com.vehicle.dealership.Utility.ConsoleColor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/car")
public class CarController {

    private final CarService carService;
    private final CarRepository carRepository;
    private final OwnedCarRepository ownedCarRepository;

    @GetMapping
    public List<Car> getAllCars() {
        log.info(ConsoleColor.Green("GET Request for a List of All Cars"));
        List<Car> carList = carService.fetchAllCars();

        log.info(ConsoleColor.Green("Car List Retrieved:"));
        return carList;
    }

    @GetMapping("/{product_id}")
    public Car getCarByProductId(@PathVariable Long product_id) {
        log.info(ConsoleColor.Green("GET Request for a Car By Product ID: {}"), product_id);
        Car car = carService.fetchCarByProductId(product_id);
        if (car == null) {
            throw new ItemNotFoundException("Car Not Found, Product ID: " + product_id);
        }

        log.info(ConsoleColor.Green("Car Retrieved: {}"), car);
        return car;
    }

    @PostMapping
    public Car addCar(@RequestBody Car car) {
        log.info(ConsoleColor.Green("POST Request for a New Car: {}"), car);

        if (car.getProductId() == null || car.getMake() == null || car.getModel() == null || car.getCarYear() == null || car.getColor() == null || car.getStock() == null) {
            throw new InvalidDataException("Invalid Car Data");
        }

        if(carRepository.findByProductId(car.getProductId()) != null) {
            throw new ItemProductIDExistsException("Car with Product ID: " + car.getProductId() + " already exists");
        }

        log.info(ConsoleColor.Green("Car Added: {}"), car);
        return carService.addCar(car);
    }

    @PutMapping()
    public Car updateCarStock(@RequestBody DealershipEventDTO dealershipEventDTO) {
        log.info(ConsoleColor.Green("PUT Request to Update Car: {}"), dealershipEventDTO);
        if (dealershipEventDTO.getProductId() == null ||  dealershipEventDTO.getQuantity() == null) {
            throw new InvalidDataException("Invalid Car Stock Update Data");
        }
        if(carRepository.findByProductId(dealershipEventDTO.getProductId()) == null) {
            throw new ItemNotFoundException("Car with Product ID: " + dealershipEventDTO.getProductId() + " does not exist");
        }

        log.info(ConsoleColor.Green("Car Updated: {}"), dealershipEventDTO);
        return carService.updateCarStock(dealershipEventDTO);
    }

    // Owned Cars
    @GetMapping("/owned")
    private List<OwnedCar> getOwnedCars() {
        log.info(ConsoleColor.Green("GET Request for a Owned Cars"));

        return ownedCarRepository.findAll();
    }

    @GetMapping("/owned/{userId}")
    public List<OwnedCar> getOwnedCarsByUserId(@PathVariable Long userId) {
        log.info("GET Request for a Owned Cars By User ID: {}", userId);

        return ownedCarRepository.findByUserId(userId);
    }
}
