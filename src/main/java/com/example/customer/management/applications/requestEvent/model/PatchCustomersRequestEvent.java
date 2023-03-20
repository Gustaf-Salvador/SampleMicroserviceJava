package com.example.customer.management.applications.requestEvent.model;

import java.util.UUID;
import com.example.customer.management.domains.model.Customer;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import io.mediator.core.Request;

@Getter
@AllArgsConstructor
public class PatchCustomersRequestEvent implements Request<Customer> {

    private UUID id;

    private JsonPatch customerPatched;

}
