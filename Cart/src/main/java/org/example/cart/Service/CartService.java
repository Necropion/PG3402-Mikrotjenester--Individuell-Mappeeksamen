package org.example.cart.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cart.Model.Cart;
import org.example.cart.Model.CartItems;
import org.example.cart.Model.CartItemsId;
import org.example.cart.Repository.CartItemsRepository;
import org.example.cart.Repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemsRepository cartItemsRepository;

    public List<CartItems> fetchCartItemsList() {
        return cartItemsRepository.findAll();
    }

    public CartItems createCartItem(CartItems cartItem) {

        Cart cart = cartRepository.findById(cartItem.getId().getCart_id())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        log.info(cart.toString());

        cartItem.setCart(cart);
        cartItemsRepository.save(cartItem);

        return cartItem;
    }
}
