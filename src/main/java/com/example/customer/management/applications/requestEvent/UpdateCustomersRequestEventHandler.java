package com.example.customer.management.applications.requestEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.customer.management.applications.requestEvent.model.UpdateCustomersRequestEvent;
import com.example.customer.management.domains.model.Customer;
import com.example.customer.management.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import io.mediator.core.RequestHandler;

@Component
@AllArgsConstructor
public class UpdateCustomersRequestEventHandler implements RequestHandler<UpdateCustomersRequestEvent, Customer> {

    @Autowired
    private final CustomerRepository customerRepository;

    @Override
    public Customer handle(UpdateCustomersRequestEvent updateCustomersRequestEvent) {


        Customer customer = Customer.load(customerRepository,
            updateCustomersRequestEvent.getId(), true);

        customer.setName(updateCustomersRequestEvent.getName());
        customer.setBirthdate(updateCustomersRequestEvent.getBirthdate());
        customer.setPhoneNumber(updateCustomersRequestEvent.getPhoneNumber());
        customer.setEmail(updateCustomersRequestEvent.getEmail());

        customer.save();

        return customer;
    }
}
