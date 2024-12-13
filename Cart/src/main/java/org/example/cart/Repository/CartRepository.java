package org.example.cart.Repository;

import org.example.cart.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    public Cart findById(long cart_id);
    public List<Cart> findByUserId(long user_id);
}
