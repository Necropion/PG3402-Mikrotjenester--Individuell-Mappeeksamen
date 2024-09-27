package org.example.cart.DTO;

import lombok.Getter;
import lombok.Setter;
import org.example.cart.Model.CarD;

@Getter
@Setter
public class CarDTO {

    private Long id;
    private Long productId;
    private String make;
    private String model;
    private Long carYear;
    private String color;

    public CarD getCarFromDealership() {
        CarD car = new CarD();
        car.setProductId(this.productId);
        car.setMake(this.make);
        car.setModel(this.model);
        car.setCarYear(this.carYear);
        car.setColor(this.color);

        return car;
    }
}
