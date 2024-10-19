package org.example.cart.Client;

import lombok.extern.slf4j.Slf4j;
import org.example.cart.DTO.CarDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class CarClient {

    private final String restServiceURL;
    private final RestTemplate restTemplate;

    public CarClient(
            RestTemplateBuilder restTemplateBuilder,
            @Value("${client.host}") final String url
    ) {
        this.restTemplate = restTemplateBuilder.build();
        this.restServiceURL = url;
    }

    public CarDTO carInfoFromDealership(Long product_id) {
        String URL = restServiceURL + "/api/car/" + product_id;
        ResponseEntity<CarDTO> response = null;

        try {
            response = restTemplate.getForEntity(URL, CarDTO.class);
        } catch (Exception e) {

            log.error(e.getMessage());
            e.printStackTrace();
            return null;
        }

        return response.getBody();
    }
}
