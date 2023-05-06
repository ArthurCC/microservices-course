package fr.camposcosta.customerapi.controller;

import fr.camposcosta.customerapi.model.CustomerRequest;
import fr.camposcosta.customerapi.model.CustomerResponse;
import fr.camposcosta.customerapi.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/customers")
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerResponse addCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
        log.info("addCustomer {}", customerRequest);

        return customerService.addCustomer(customerRequest);
    }
}
