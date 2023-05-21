package fr.camposcosta.fraudapi.controller;

import fr.camposcosta.fraudapi.model.FraudCheckResponse;
import fr.camposcosta.fraudapi.service.FraudCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fraud-check")
@Slf4j
public class FraudCheckController {

    private final FraudCheckService fraudCheckService;

    public FraudCheckController(FraudCheckService fraudCheckService) {
        this.fraudCheckService = fraudCheckService;
    }

    @PostMapping("/{customerId}")
    public FraudCheckResponse isFraudulentCustomer(@PathVariable Integer customerId) {
        log.info("isFraudulentCustomer {}", customerId);

        return new FraudCheckResponse(
                fraudCheckService.isFraudulentCustomer(customerId)
        );
    }
}
