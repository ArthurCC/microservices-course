package fr.camposcosta.customerapi.client;

import fr.camposcosta.customerapi.model.FraudCheckResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Open feign client
 * Allows to standardize client request and reuse them throughout the app
 */
@FeignClient("fraud-api")
public interface FraudClient {

    @GetMapping("/api/v1/fraud-check/{customerId}")
    FraudCheckResponse checkFraudster(@PathVariable("customerId") Integer customerId);
}
