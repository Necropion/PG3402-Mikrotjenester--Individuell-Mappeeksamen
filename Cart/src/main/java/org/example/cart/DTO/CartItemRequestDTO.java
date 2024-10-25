package org.example.cart.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartItemRequestDTO {

    private Long cartId;
    private Long productId;
    private Long quantity;

}
