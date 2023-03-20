package com.example.customer.management.applications.requestEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.customer.management.applications.requestEvent.model.DeleteCustomersRequestEvent;
import com.example.customer.management.applications.requestEvent.model.PatchCustomersRequestEvent;
import com.example.customer.management.domains.model.Customer;
import com.example.customer.management.repositories.CustomerRepository;
import com.example.customer.management.services.rest.mapper.JsonPatchMapper;
import lombok.AllArgsConstructor;
import io.mediator.core.RequestHandler;

@Component
@AllArgsConstructor
public class PatchCustomersRequestEventHandler implements RequestHandler<PatchCustomersRequestEvent, Customer> {

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private JsonPatchMapper jsonPatchMapper;

    @Override
    public Customer handle(PatchCustomersRequestEvent patchCustomersRequestEvent) {
        var customer = Customer.load(customerRepository, patchCustomersRequestEvent.getId(), true);

        Customer patchedCustomer = jsonPatchMapper.apply(patchCustomersRequestEvent.getCustomerPatched(), customer, Customer.class);

        patchedCustomer.save();

        return patchedCustomer;
    }
}
