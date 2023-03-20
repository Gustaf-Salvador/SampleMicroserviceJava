package com.example.customer.management.applications.requestEvent.model;

import java.util.UUID;
import com.example.customer.management.domains.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import io.mediator.core.Request;

@Getter
@AllArgsConstructor
public class GetCustomersByIdRequestEvent implements Request<Customer> {

    private UUID id;

}
