package org.example.cart.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemRequestDTO {

    private Long cartId;
    private Long productId;
    private Long quantity;

}
