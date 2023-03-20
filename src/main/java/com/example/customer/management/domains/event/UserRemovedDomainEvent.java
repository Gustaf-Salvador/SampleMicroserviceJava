package com.example.customer.management.domains.event;

import com.example.customer.management.domains.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import io.mediator.core.Command;

@AllArgsConstructor
@Getter
public class UserRemovedDomainEvent implements Command {

    private Customer customer;

}
