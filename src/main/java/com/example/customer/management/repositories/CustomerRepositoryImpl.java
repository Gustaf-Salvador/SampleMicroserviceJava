package com.example.customer.management.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.customer.management.domains.model.Customer;
import com.example.customer.management.repositories.client.CustomerJpaClientImpl;
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
    private final CustomerJpaClientImpl jpaClient;

    @Autowired
    private final CustomerOutboundPersistanceMapper customerMapper;
    
    @Autowired
    private final FeatureFlagRepository growthBookRepository;

    @Override
    public Customer findById(UUID id, Boolean onlyActive) {
        CustomerDSO customerDSO;

        if (onlyActive != null){
            customerDSO = jpaClient.getByIdAndIsActive(id, onlyActive);
        }
        else {
            customerDSO = jpaClient.getById(id);
        }


        var customer = customerMapper.toCustomer(this, growthBookRepository, customerDSO);

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

        var customerList = customerMapper.toCustomerList(this, growthBookRepository, customerDsoList);

        return customerList;
    }

    @Override
    public void save(Customer customer) {
        var customerDso = customerMapper.toCustomerDSO(customer);

        jpaClient.save(customerDso);

        customer.raiseDomainEvents(mediator);
    }

    @Override
    public void delete(Customer customer) {
        var customerDso = customerMapper.toCustomerDSO(customer);

        jpaClient.save(customerDso);

        customer.raiseDomainEvents(mediator);
    }

}
