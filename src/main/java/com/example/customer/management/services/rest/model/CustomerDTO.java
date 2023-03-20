package com.example.customer.management.services.rest.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDTO extends RequestCustomerDTO {
	
    private UUID id;

    private boolean isActive;
}

