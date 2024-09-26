package org.example.cart.Controller;

import lombok.RequiredArgsConstructor;
import org.example.cart.Model.CartItems;
import org.example.cart.Service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/cart")
    public List<CartItems> getAllCartItems() {
        return cartService.fetchCartItemsList();
    }

    @PostMapping("/cart")
    public CartItems postCartItem(@RequestBody CartItems cartItems) {
        return cartService.createCartItem(cartItems);
    }
}
