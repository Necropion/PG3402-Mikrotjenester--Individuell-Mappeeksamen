package org.example.cart.EventDriven;

import lombok.Value;

@Value
public class DealershipEvent {

    Long userId;
    Long productId;
    Long quantity;
    Long receiptId;
}
