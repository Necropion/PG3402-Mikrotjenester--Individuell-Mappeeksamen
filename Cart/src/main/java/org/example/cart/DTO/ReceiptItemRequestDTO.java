package org.example.cart.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReceiptItemRequestDTO {

    private Long receiptId;
    private Long productId;
    private String productName;
    private Double productPrice;
    private Long quantity;
}
