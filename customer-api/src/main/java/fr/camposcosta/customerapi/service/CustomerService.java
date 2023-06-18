package fr.camposcosta.customerapi.service;

import fr.camposcosta.customerapi.client.FraudClient;
import fr.camposcosta.customerapi.client.NotificationClient;
import fr.camposcosta.customerapi.entity.Customer;
import fr.camposcosta.customerapi.exception.FatalException;
import fr.camposcosta.customerapi.exception.InvalidRequestException;
import fr.camposcosta.customerapi.model.CustomerRequest;
import fr.camposcosta.customerapi.model.CustomerResponse;
import fr.camposcosta.customerapi.model.FraudCheckResponse;
import fr.camposcosta.customerapi.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public CustomerService(
            CustomerRepository customerRepository,
            FraudClient fraudClient,
            NotificationClient notificationClient
    ) {
        this.customerRepository = customerRepository;
        this.fraudClient = fraudClient;
        this.notificationClient = notificationClient;
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
//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
//                fraudApiUrl,
//                FraudCheckResponse.class,
//                customer.getId()
//        );

        FraudCheckResponse fraudCheckResponse = fraudClient.checkFraudster(customer.getId());

        if(fraudCheckResponse.isFraudster() == null) {
            // Should never happen
            throw new FatalException("isFraudster is null");
        }

        if(fraudCheckResponse.isFraudster()) {
            log.error("Customer [id={}] is fraudulent", customer.getId());
        }

        // send notification
        notificationClient.sendNotification(customer.getId());

        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail()
        );
    }
}
