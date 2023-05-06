package fr.camposcosta.customerapi.service;

import fr.camposcosta.customerapi.entity.Customer;
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

        // TODO : check email is not taken

        Customer customer = customerRepository.save(
                Customer.builder()
                        .firstName(customerRequest.firstName())
                        .lastName(customerRequest.lastName())
                        .email(customerRequest.email())
                        .build()
        );

        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail()
        );
    }
}
