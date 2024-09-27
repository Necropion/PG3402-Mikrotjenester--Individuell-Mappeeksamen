package org.example.cart.Repository;

import org.example.cart.Model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
    public List<CartItems> findByCartId(Long cart_id);
}
