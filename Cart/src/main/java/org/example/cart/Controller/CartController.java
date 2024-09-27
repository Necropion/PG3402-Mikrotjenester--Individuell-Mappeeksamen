package org.example.cart.Controller;

import lombok.RequiredArgsConstructor;
import org.example.cart.Model.Cart;
import org.example.cart.Model.CartItems;
import org.example.cart.Service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart")
    public Cart postCart(@RequestBody Cart cart) {
        return cartService.createCart(cart);
    }

    @GetMapping("/item")
    public List<CartItems> getAllCartItems() {
        return cartService.fetchCartItemsList();
    }

    @GetMapping("/item/{cart_id}")
    public List<CartItems> getAllCartItems(@PathVariable Long cart_id) {
        return cartService.fetchAllCartItems(cart_id);
    }

    @PostMapping("/item")
    public CartItems postCartItem(@RequestBody CartItems cartItems) {
        return cartService.createCartItem(cartItems);
    }


}
