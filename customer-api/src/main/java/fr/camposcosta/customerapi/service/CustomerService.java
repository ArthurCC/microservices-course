package fr.camposcosta.customerapi.service;

import fr.camposcosta.customerapi.entity.Customer;
import fr.camposcosta.customerapi.exception.InvalidRequestException;
import fr.camposcosta.customerapi.model.CustomerRequest;
import fr.camposcosta.customerapi.model.CustomerResponse;
import fr.camposcosta.customerapi.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse addCustomer(CustomerRequest customerRequest) {

        // check email is not taken
        if (customerRepository.existsByEmail(customerRequest.email())) {
            throw new InvalidRequestException(
                    String.format("Email %s already exists", customerRequest.email())
            );
        }

        Customer customer = customerRepository.save(
                new Customer(
                        customerRequest.firstName(),
                        customerRequest.lastName(),
                        customerRequest.email()
                )
        );

        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail()
        );
    }
}
