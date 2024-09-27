package com.vehicle.dealership.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Map the column "product_id" to the field "productId"
    @Column(name = "product_id")
    private Long productId;
    private String make;
    private String model;
    private Long carYear;
    private String color;
}
