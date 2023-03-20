package com.example.customer.management.domains.event;

import com.example.customer.management.domains.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import io.mediator.core.Command;

@Getter
@AllArgsConstructor
public class UserChangedDomainEvent implements Command {

    private Customer customer;
    private String fieldName;
    private String oldValue;
    private String newValue;
}
