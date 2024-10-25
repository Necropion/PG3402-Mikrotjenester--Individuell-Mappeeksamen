package org.example.cart.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CarD {

    private Long productId;
    private String make;
    private String model;
    private Long carYear;
    private String color;
    private Long stock;
}
