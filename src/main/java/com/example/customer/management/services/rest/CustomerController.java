package com.example.customer.management.services.rest;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.customer.management.applications.requestEvent.model.CreateCustomersRequestEvent;
import com.example.customer.management.applications.requestEvent.model.DeleteCustomersRequestEvent;
import com.example.customer.management.applications.requestEvent.model.GetCustomersByIdRequestEvent;
import com.example.customer.management.applications.requestEvent.model.ListCustomersRequestEvent;
import com.example.customer.management.applications.requestEvent.model.UpdateCustomersRequestEvent;
import com.example.customer.management.domains.model.Customer;
import com.example.customer.management.services.rest.mapper.CustomerInboundRestMapper;
import com.example.customer.management.services.rest.mapper.JsonPatchMapper;
import com.example.customer.management.services.rest.model.CustomerDTO;
import com.example.customer.management.services.rest.model.RequestCustomerDTO;
import com.github.fge.jsonpatch.JsonPatch;
import io.mediator.core.Mediator;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private Mediator mediator;

    @Autowired
    private CustomerInboundRestMapper customerInboundRestMapper;
    
    @Autowired
    private JsonPatchMapper jsonPatchMapper;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers(@RequestParam(name = "onlyActive", required = false) Boolean onlyActive) {

        ListCustomersRequestEvent customerListRequest = new ListCustomersRequestEvent(onlyActive);
        List<Customer> customerList = mediator.dispatch(customerListRequest);
        List<CustomerDTO> customerDTOs = customerInboundRestMapper.toCustomerDTOList(customerList);

        return ResponseEntity.ok(customerDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable UUID id) {
        GetCustomersByIdRequestEvent getCustomersByIdRequestEvent = new GetCustomersByIdRequestEvent(id);
        Customer customer = mediator.dispatch(getCustomersByIdRequestEvent);
        CustomerDTO customerDTO = customerInboundRestMapper.toCustomerDTO(customer);
        return ResponseEntity.ok(customerDTO);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody RequestCustomerDTO customerDTO) {
        CreateCustomersRequestEvent createCustomersRequestEvent = new CreateCustomersRequestEvent(customerDTO.getName(),
            customerDTO.getEmail(),
            customerDTO.getBirthdate(),
            customerDTO.getPhoneNumber());

        Customer customer = mediator.dispatch(createCustomersRequestEvent);

        CustomerDTO createdCustomerDTO = customerInboundRestMapper.toCustomerDTO(customer);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable UUID id, @RequestBody RequestCustomerDTO requestCustomerDTO) {

    	UpdateCustomersRequestEvent updateCustomersRequestEvent = new UpdateCustomersRequestEvent(id,
    			requestCustomerDTO.getName(),
    			requestCustomerDTO.getEmail(),
    			requestCustomerDTO.getBirthdate(),
    			requestCustomerDTO.getPhoneNumber());

        Customer customer = mediator.dispatch(updateCustomersRequestEvent);

        CustomerDTO customerDTO = customerInboundRestMapper.toCustomerDTO(customer);
                	
        return ResponseEntity.ok(customerDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable UUID id, @RequestBody JsonPatch patchCustomerDTO) {
        
    	GetCustomersByIdRequestEvent getCustomersByIdRequestEvent = new GetCustomersByIdRequestEvent(id);
        Customer customer = mediator.dispatch(getCustomersByIdRequestEvent);
        
        CustomerDTO customerDTO = customerInboundRestMapper.toCustomerDTO(customer);

        customerDTO = jsonPatchMapper.apply(patchCustomerDTO, customerDTO, CustomerDTO.class);
        
        ResponseEntity<CustomerDTO> responseCustomerDTO = updateCustomer(id, customerDTO);
        
        return responseCustomerDTO;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable UUID id) {
        DeleteCustomersRequestEvent deleteCustomersRequestEvent = new DeleteCustomersRequestEvent(id);
        mediator.dispatch(deleteCustomersRequestEvent);
        return ResponseEntity.noContent().build();
    }
    
}
