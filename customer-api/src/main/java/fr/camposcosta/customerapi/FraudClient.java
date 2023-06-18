package fr.camposcosta.customerapi;

import fr.camposcosta.customerapi.model.FraudCheckResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Open feign client
 * Allows to standardize client request and reuse them throughout the app
 */
@FeignClient("fraud-api")
public interface FraudClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/fraud-check/{customerId}")
    FraudCheckResponse checkFraudster(@PathVariable("customerId") Integer customerId);
}
