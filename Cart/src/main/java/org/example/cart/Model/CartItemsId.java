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

    private Long cart_id;
    private Long product_id;

    public CartItemsId() {}

    public CartItemsId(Long cart_id, Long product_id) {
        this.cart_id = cart_id;
        this.product_id = product_id;
    }
}
