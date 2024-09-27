package org.example.cart.Repository;

import org.example.cart.Model.CarD;

public interface CarInfoRepository {
    CarD getCarByProductId(Long productId);
}
