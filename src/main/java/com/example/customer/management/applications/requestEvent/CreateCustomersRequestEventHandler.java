package com.example.customer.management.applications.requestEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.customer.management.applications.requestEvent.model.CreateCustomersRequestEvent;
import com.example.customer.management.domains.model.Customer;
import com.example.customer.management.repositories.CustomerRepository;
import com.example.customer.management.repositories.FeatureFlagRepository;

import lombok.AllArgsConstructor;
import io.mediator.core.RequestHandler;

@Component
@AllArgsConstructor
public class CreateCustomersRequestEventHandler implements RequestHandler<CreateCustomersRequestEvent, Customer> {

    @Autowired
    private final CustomerRepository customerRepository;
    
    @Autowired
    private final FeatureFlagRepository featureFlagRepository;

    @Override
    public Customer handle(CreateCustomersRequestEvent createCustomersRequestEvent) {
        Customer customer = Customer.create(customerRepository, featureFlagRepository,
            createCustomersRequestEvent.getName(),
            createCustomersRequestEvent.getBirthdate(),
            createCustomersRequestEvent.getPhoneNumber(),
            createCustomersRequestEvent.getEmail());

        customer.save();

        return customer;
    }
}
