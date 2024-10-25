package org.example.cart.Client;

import lombok.extern.slf4j.Slf4j;
import org.example.cart.DTO.CarDTO;
import org.example.cart.Exception.Product.Car.ExternalServiceException;
import org.example.cart.Utility.ConsoleColor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
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

        try {
            ResponseEntity<CarDTO> response = restTemplate.getForEntity(URL, CarDTO.class);
            return response.getBody();

        } catch (ResourceAccessException e) {
            // Gateway is unreachable
            log.error(ConsoleColor.Red("Failed to connect to Gateway Service."));
            throw new ExternalServiceException(e.getMessage());

        } catch (HttpStatusCodeException e) {
            // Gateway responded but returns an error with Dealership
            if (e.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) {
                log.error(ConsoleColor.Red("Failed to connect to Dealership Service."));
                throw new ExternalServiceException(e.getMessage());

            }

            throw e;

        } catch (RestClientException e) {
            log.error(ConsoleColor.Red("An error occurred while contacting external services."));
            throw new ExternalServiceException(e.getMessage());

        }
    }
}
