package com.example.customer.management.services.rest.model;

import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestCustomerDTO {
	    
    @NotBlank
    private String name;
    
    @NotBlank
    @Email
    private String email;
    
    @NotNull
    @Past
    private LocalDate birthdate;
    
    
    private String phoneNumber;
}

