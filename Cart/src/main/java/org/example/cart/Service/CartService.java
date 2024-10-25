package org.example.cart.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cart.DTO.CartItemRequestDTO;
import org.example.cart.Exception.Cart.CartNotFoundException;
import org.example.cart.Model.Cart;
import org.example.cart.Model.CartItems;
import org.example.cart.Model.CartItemsId;
import org.example.cart.Repository.CartItemsRepository;
import org.example.cart.Repository.CartRepository;
import org.example.cart.Utility.ConsoleColor;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemsRepository cartItemsRepository;

    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public List<CartItems> fetchCartItemsList() {
        return cartItemsRepository.findAll();
    }

    public List<CartItems> fetchAllCartItems(Long cart_id) {
        if(!cartRepository.existsById(cart_id)) {
            throw new CartNotFoundException(ConsoleColor.Yellow("Cart with ID: " + cart_id + " does not exist"));
        }

        return cartItemsRepository.findByCartId(cart_id);
    }

    public CartItems createCartItem(CartItemRequestDTO cartItemRequestDTO) {

        CartItems cartItem = new CartItems();

        Cart cart = cartRepository.findById(cartItemRequestDTO.getCartId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItemsId cartItemId = new CartItemsId();
        cartItemId.setCartId(cartItemRequestDTO.getCartId());
        cartItemId.setProductId(cartItemRequestDTO.getProductId());

        cartItem.setId(cartItemId);
        cartItem.setCart(cart);
        cartItem.setQuantity(cartItemRequestDTO.getQuantity());
        cartItemsRepository.save(cartItem);

        return cartItem;
    }
}
