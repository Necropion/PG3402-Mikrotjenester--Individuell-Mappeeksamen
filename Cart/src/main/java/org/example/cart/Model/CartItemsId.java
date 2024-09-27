package org.example.cart.Model;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class CartItemsId implements Serializable {

    private Long cartId;
    private Long productId;

    public CartItemsId() {}

    public CartItemsId(Long cartId, Long productId) {
        this.cartId = cartId;
        this.productId = productId;
    }
}
