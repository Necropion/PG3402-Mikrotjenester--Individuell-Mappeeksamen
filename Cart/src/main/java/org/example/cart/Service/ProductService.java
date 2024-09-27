package org.example.cart.Service;

import lombok.RequiredArgsConstructor;
import org.example.cart.Client.CarClient;
import org.example.cart.Model.CarD;
import org.example.cart.Repository.CarInfoRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService implements CarInfoRepository {

    private final CarClient carClient;

    @Override
    public CarD getCarByProductId(Long productId) {
        return carClient.carInfoFromDealership(productId).getCarFromDealership();
    }
}
