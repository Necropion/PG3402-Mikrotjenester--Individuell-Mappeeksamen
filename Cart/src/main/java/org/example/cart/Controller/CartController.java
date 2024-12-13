package org.example.cart.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cart.DTO.CartItemRequestDTO;
import org.example.cart.Exception.InvalidDataException;
import org.example.cart.Exception.Cart.MissingUserIdException;
import org.example.cart.Model.Cart;
import org.example.cart.Model.CartItems;
import org.example.cart.Service.CartService;
import org.example.cart.Utility.ConsoleColor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // Cart Management
    @GetMapping("/cart")
    public List<Cart> getCart() {
        return cartService.fetchAllCarts();
    }

    @PostMapping("/cart")
    public Cart postCart(@RequestBody Cart cart) {
        log.info(ConsoleColor.Green("POST Request Create Cart for User ID: {}"), cart.getUserId());
        if (cart.getUserId() == null) {
            throw new InvalidDataException("User ID cannot be null");
        }
        Cart newCart = cartService.createCart(cart);

        log.info(ConsoleColor.Green("Cart created!: {}"), newCart);
        return newCart;
    }

    @DeleteMapping("/cart/{cartId}")
    public String deleteCartByCartID(@PathVariable Long cartId) {
        cartService.deleteCartById(cartId);
        return "Cart with ID: " + cartId + ",  deleted!";
    }

    @GetMapping("/cart/{user_id}")
    public List<Cart> getAllCartByUserId(@PathVariable Long user_id) {
        return cartService.fetchAllUserCarts(user_id);
    }

    // Cart Items Management
    @GetMapping("/item")
    public List<CartItems> getAllCartItems() {
        log.info(ConsoleColor.Green("GET Request for All Cart Items"));
        List<CartItems> cartItems = cartService.fetchCartItemsList();

        log.info(ConsoleColor.Green("Cart items list: {}"), cartItems);
        return cartItems;
    }

    @GetMapping("/item/{cart_id}")
    public List<CartItems> getAllCartItems(@PathVariable Long cart_id) {
        log.info(ConsoleColor.Green("GET Request for All Cart Items from Cart ID : " + cart_id));
        List<CartItems> cartItems = cartService.fetchAllCartItems(cart_id);

        log.info(ConsoleColor.Green("Cart items list: {}"), cartItems);
        return cartItems;
    }

    @PostMapping("/item")
    public CartItems postCartItem(@RequestBody CartItemRequestDTO cartItemRequestDTO) {
        log.info(ConsoleColor.Green("POST Request Create Cart Item: {}"), cartItemRequestDTO);
        if(cartItemRequestDTO.getCartId() == null || cartItemRequestDTO.getProductId() == null || cartItemRequestDTO.getQuantity() == null) {
            throw new InvalidDataException("Invalid Cart Item Data");
        }
        CartItems cartItem = cartService.createCartItem(cartItemRequestDTO);

        log.info(ConsoleColor.Green("Cart item created! {}"), cartItem);
        return cartItem;
    }


}
