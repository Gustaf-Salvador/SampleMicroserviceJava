package com.example.customer.management.services.rest.mapper;

import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import com.example.customer.management.domains.model.Customer;
import com.example.customer.management.services.rest.model.CustomerDTO;

@Mapper(componentModel = "spring")
public interface CustomerInboundRestMapper {

    default CustomerDTO toCustomerDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setBirthdate(customer.getBirthdate());
        customerDTO.setActive(customer.isActive());

        return customerDTO;
    }

    default List<CustomerDTO> toCustomerDTOList(List<Customer> customers) {
        if ( customers == null ) {
            return null;
        }

        List<CustomerDTO> list = new ArrayList<CustomerDTO>( customers.size() );
        for ( Customer customer : customers ) {
            list.add( toCustomerDTO( customer ) );
        }

        return list;
    }
}
