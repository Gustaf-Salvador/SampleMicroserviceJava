package com.example.customer.management.repositories;

import java.util.List;
import java.util.UUID;
import com.example.customer.management.domains.model.Customer;

public interface CustomerRepository {

    Customer findById(UUID id, Boolean onlyActive);
    List<Customer> findAll(Boolean onlyActive);
    void save(Customer customer);
    void delete(Customer customer);
}
