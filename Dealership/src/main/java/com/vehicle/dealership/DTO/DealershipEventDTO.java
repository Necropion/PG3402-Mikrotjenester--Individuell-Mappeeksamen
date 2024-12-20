package com.vehicle.dealership.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Value;

@Getter
@Setter
@Value
@ToString
public class DealershipEventDTO {

    Long userId;
    Long productId;
    Long quantity;
    Long receiptId;
}
