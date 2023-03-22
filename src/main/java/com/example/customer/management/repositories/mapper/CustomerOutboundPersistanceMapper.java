package com.example.customer.management.repositories.mapper;

import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;

import com.example.customer.management.domains.model.Customer;
import com.example.customer.management.repositories.CustomerRepository;
import com.example.customer.management.repositories.FeatureFlagRepository;
import com.example.customer.management.repositories.model.CustomerDSO;

@Mapper(componentModel = "spring")
public interface CustomerOutboundPersistanceMapper {
	
    default CustomerDSO toCustomerDSO(Customer customer) {
        if (customer == null) {
            return null;
        }

        CustomerDSO customerDSO = new CustomerDSO();

        customerDSO.setId(customer.getId());
        customerDSO.setName(customer.getName());
        customerDSO.setEmail(customer.getEmail());
        customerDSO.setBirthdate(customer.getBirthdate());
        customerDSO.setPhoneNumber(customer.getPhoneNumber());
        customerDSO.setActive(customer.isActive());
        customerDSO.setCreatedDateUtc(customer.getCreateDateUtc());
        customerDSO.setModifiedDateUtc(customer.getModifiedDateUtc());

        return customerDSO;
    }

    default Customer toCustomer(CustomerRepository customerRepository, FeatureFlagRepository growthBookRepository, CustomerDSO customerDSO) {
        if (customerDSO == null) {
            return null;
        }

        Customer customer = new Customer(customerRepository, growthBookRepository);
        customer.setId(customerDSO.getId());
        customer.setName(customerDSO.getName());
        customer.setEmail(customerDSO.getEmail());
        customer.setBirthdate(customerDSO.getBirthdate());
        customer.setPhoneNumber(customerDSO.getPhoneNumber());
        customer.setActive(customerDSO.isActive());
        customer.setCreateDateUtc(customerDSO.getCreatedDateUtc());
        customer.setModifiedDateUtc(customerDSO.getModifiedDateUtc());

        return customer;
    }

    default List<Customer> toCustomerList(CustomerRepository customerRepository, FeatureFlagRepository growthBookRepository, List<CustomerDSO> customerDTOs) {
        if (customerDTOs == null) {
            return null;
        }

        List<Customer> list = new ArrayList<>(customerDTOs.size());
        for (CustomerDSO customerDSO : customerDTOs) {
            list.add(toCustomer(customerRepository, growthBookRepository, customerDSO));
        }

        return list;
    }
}
