package com.example.customer.management.applications.requestEvent.model;

import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import com.example.customer.management.domains.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import io.mediator.core.Request;

@Getter
@AllArgsConstructor
public class CreateCustomersRequestEvent implements Request<Customer> {

    private String name;

    private String email;

    private LocalDate birthdate;

    private String phoneNumber;
}
