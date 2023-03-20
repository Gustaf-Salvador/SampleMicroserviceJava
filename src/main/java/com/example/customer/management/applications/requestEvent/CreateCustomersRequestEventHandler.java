package com.example.customer.management.applications.requestEvent;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.customer.management.applications.requestEvent.model.CreateCustomersRequestEvent;
import com.example.customer.management.applications.requestEvent.model.ListCustomersRequestEvent;
import com.example.customer.management.domains.model.Customer;
import com.example.customer.management.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import io.mediator.core.RequestHandler;

@Component
@AllArgsConstructor
public class CreateCustomersRequestEventHandler implements RequestHandler<CreateCustomersRequestEvent, Customer> {

    @Autowired
    private final CustomerRepository customerRepository;

    @Override
    public Customer handle(CreateCustomersRequestEvent createCustomersRequestEvent) {
        Customer customer = Customer.create(customerRepository,
            createCustomersRequestEvent.getName(),
            createCustomersRequestEvent.getBirthdate(),
            createCustomersRequestEvent.getPhoneNumber(),
            createCustomersRequestEvent.getEmail());

        customer.save();

        return customer;
    }
}
