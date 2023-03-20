package com.example.customer.management.applications.requestEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.customer.management.applications.requestEvent.model.DeleteCustomersRequestEvent;
import com.example.customer.management.applications.requestEvent.model.GetCustomersByIdRequestEvent;
import com.example.customer.management.domains.model.Customer;
import com.example.customer.management.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import io.mediator.core.RequestHandler;

@Component
@AllArgsConstructor
public class DeleteCustomersRequestEventHandler implements RequestHandler<DeleteCustomersRequestEvent, Customer> {

    @Autowired
    private final CustomerRepository customerRepository;

    @Override
    public Customer handle(DeleteCustomersRequestEvent getCustomersByIdRequestEvent) {
        var customer = Customer.load(customerRepository, getCustomersByIdRequestEvent.getId(), true);

        customer.delete();

        return customer;
    }
}
