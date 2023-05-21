package fr.camposcosta.fraudapi.controller;

import fr.camposcosta.fraudapi.model.FraudCheckResponse;
import fr.camposcosta.fraudapi.service.FraudCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fraud-check")
@Slf4j
public class FraudCheckController {

    private final FraudCheckService fraudCheckService;

    public FraudCheckController(FraudCheckService fraudCheckService) {
        this.fraudCheckService = fraudCheckService;
    }

    @GetMapping("/{customerId}")
    public FraudCheckResponse isFraudulentCustomer(@PathVariable Integer customerId) {
        log.info("isFraudulentCustomer {}", customerId);

        return fraudCheckService.isFraudulentCustomer(customerId);
    }
}
