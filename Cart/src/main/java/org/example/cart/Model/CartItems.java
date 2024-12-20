package org.example.cart.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class CartItems {

    @EmbeddedId
    private CartItemsId id = new CartItemsId();

    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;

    private Long quantity;
}
