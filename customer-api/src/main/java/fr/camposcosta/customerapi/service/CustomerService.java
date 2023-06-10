package fr.camposcosta.customerapi.service;

import fr.camposcosta.customerapi.entity.Customer;
import fr.camposcosta.customerapi.exception.FatalException;
import fr.camposcosta.customerapi.exception.InvalidRequestException;
import fr.camposcosta.customerapi.model.CustomerRequest;
import fr.camposcosta.customerapi.model.CustomerResponse;
import fr.camposcosta.customerapi.model.FraudCheckResponse;
import fr.camposcosta.customerapi.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class CustomerService {

    private final RestTemplate restTemplate;
    private final CustomerRepository customerRepository;
    private final String fraudApiUrl;

    public CustomerService(
            CustomerRepository customerRepository,
            RestTemplate restTemplate,
            @Value("${app.fraud-api.url}") String fraudApiUrl
    ) {
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
        this.fraudApiUrl = fraudApiUrl;
    }

    public CustomerResponse addCustomer(CustomerRequest customerRequest) {

        // check email is not taken
        if (customerRepository.existsByEmail(customerRequest.email())) {
            throw new InvalidRequestException(
                    String.format("Email %s already exists", customerRequest.email())
            );
        }

        // save in db
        Customer customer = customerRepository.save(
                new Customer(
                        customerRequest.firstName(),
                        customerRequest.lastName(),
                        customerRequest.email()
                )
        );

        // check if fraudster
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                fraudApiUrl,
                FraudCheckResponse.class,
                customer.getId()
        );

        if(fraudCheckResponse.isFraudster() == null) {
            throw new FatalException("isFraudster is null");
        }

        if(fraudCheckResponse.isFraudster()) {
            log.error("Customer [id={}] is fraudulent", customer.getId());
        }

        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail()
        );
    }
}
