package com.vehicle.dealership.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Getter
@Setter
@Value
public class DealershipEventDTO {

    Long productId;
    Long quantity;
}
