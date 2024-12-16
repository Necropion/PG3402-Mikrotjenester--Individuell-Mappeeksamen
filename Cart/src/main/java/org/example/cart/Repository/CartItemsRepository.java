package org.example.cart.Repository;

import org.example.cart.Model.CartItems;
import org.example.cart.Model.CartItemsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, CartItemsId> {
    List<CartItems> findByCartId(Long cart_id);
}
