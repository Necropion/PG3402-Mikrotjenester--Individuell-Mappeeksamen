package com.vehicle.dealership.Repository;

import com.vehicle.dealership.Model.Car;
import com.vehicle.dealership.Model.OwnedCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnedCarRepository extends JpaRepository<OwnedCar, Long> {
    List<OwnedCar> findByUserId(Long userId);
}
