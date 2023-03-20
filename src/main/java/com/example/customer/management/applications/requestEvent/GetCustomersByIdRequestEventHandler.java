package com.example.customer.management.applications.requestEvent;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.customer.management.applications.requestEvent.model.GetCustomersByIdRequestEvent;
import com.example.customer.management.applications.requestEvent.model.ListCustomersRequestEvent;
import com.example.customer.management.domains.model.Customer;
import com.example.customer.management.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import io.mediator.core.RequestHandler;

@Component
@AllArgsConstructor
public class GetCustomersByIdRequestEventHandler implements RequestHandler<GetCustomersByIdRequestEvent, Customer> {

    @Autowired
    private final CustomerRepository customerRepository;

    @Override
    public Customer handle(GetCustomersByIdRequestEvent getCustomersByIdRequestEvent) {
        return Customer.load(customerRepository, getCustomersByIdRequestEvent.getId(), null);
    }
}
