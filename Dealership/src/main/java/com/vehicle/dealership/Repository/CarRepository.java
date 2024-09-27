package com.vehicle.dealership.Repository;

import com.vehicle.dealership.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByProductId(Long productId);
}
