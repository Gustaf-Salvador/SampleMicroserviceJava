package com.example.customer.management.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.customer.management.domains.model.Customer;
import com.example.customer.management.repositories.client.CustomerJpaClient;
import com.example.customer.management.repositories.mapper.CustomerOutboundPersistanceMapper;
import com.example.customer.management.repositories.model.CustomerDSO;
import lombok.AllArgsConstructor;
import io.mediator.core.Mediator;

@AllArgsConstructor
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    private final Mediator mediator;

    @Autowired
    private final CustomerJpaClient jpaClient;

    @Autowired
    private final CustomerOutboundPersistanceMapper mapper;

    @Override
    public Customer findById(UUID id, Boolean onlyActive) {
        CustomerDSO customerDSO;

        if (onlyActive != null){
            customerDSO = jpaClient.getByIdAndIsActive(id, onlyActive);
        }
        else {
            customerDSO = jpaClient.getById(id);
        }


        var customer = mapper.toCustomer(this, customerDSO);

        return customer;
    }

    @Override
    public List<Customer> findAll(Boolean onlyActive) {
        List<CustomerDSO> customerDsoList;

        if (onlyActive != null){
            customerDsoList = jpaClient.listAllByIsActive(onlyActive);
        }
        else {
            customerDsoList = jpaClient.findAll();
        }

        var customerList = mapper.toCustomerList(this, customerDsoList);

        return customerList;
    }

    @Override
    public void save(Customer customer) {
        var customerDso = mapper.toCustomerDSO(customer);

        jpaClient.save(customerDso);

        customer.raiseDomainEvents(mediator);
    }

    @Override
    public void delete(Customer customer) {
        var customerDso = mapper.toCustomerDSO(customer);

        jpaClient.save(customerDso);

        customer.raiseDomainEvents(mediator);
    }
}
