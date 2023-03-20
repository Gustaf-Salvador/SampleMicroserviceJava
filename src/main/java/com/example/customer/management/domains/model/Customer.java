package com.example.customer.management.domains.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.customer.management.domains.event.UserChangedDomainEvent;
import com.example.customer.management.domains.event.UserCreatedDomainEvent;
import com.example.customer.management.domains.event.UserRemovedDomainEvent;
import com.example.customer.management.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.SneakyThrows;
import io.mediator.domain.AbstractDomainBase;
import javassist.NotFoundException;

@Getter
@AllArgsConstructor
@Component
public class Customer extends AbstractDomainBase {

	private UUID id;

	private String name;

    private String email;

    private String phoneNumber;
    
    private LocalDate birthdate;
    
    private LocalDateTime createDateUtc;

    private LocalDateTime modifiedDateUtc;

	public void setId(UUID id) {
		this.id = id;
	}

	public void setName(String name) {
		if (this.name != null && !this.name.equals(name)) {
			addDomainEvent(new UserChangedDomainEvent(this, "name", this.name, name));
		}

		this.name = name;
	}

	public void setEmail(String email) {
		if (this.email != null && !this.email.equals(email)) {
			addDomainEvent(new UserChangedDomainEvent(this, "email", this.email, email));
		}

		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		if (this.phoneNumber != null && !this.phoneNumber.equals(phoneNumber)) {
			addDomainEvent(new UserChangedDomainEvent(this, "phoneNumber", this.phoneNumber, phoneNumber));
		}

		this.phoneNumber = phoneNumber;
	}

	public void setBirthdate(LocalDate birthdate) {
		if (this.birthdate != null && !this.birthdate.equals(birthdate)) {
			addDomainEvent(new UserChangedDomainEvent(this, "birthdate", this.birthdate.toString(), birthdate.toString()));
		}

		this.birthdate = birthdate;
	}

	public void setCreateDateUtc(LocalDateTime createDateUtc) {
		this.createDateUtc = createDateUtc;
	}

	public void setModifiedDateUtc(LocalDateTime modifiedDateUtc) {
		this.modifiedDateUtc = modifiedDateUtc;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	private boolean isActive;

	private CustomerRepository customerRepository;

	public static Customer create(
		CustomerRepository customerRepository,
		String name, @NotNull LocalDate birthdate, String phoneNumber, String email) {
		Customer customer = new Customer(customerRepository, name, birthdate, phoneNumber, email);

		customer.validate();

		return customer;
	}

	@Autowired
	public Customer(CustomerRepository customerRepository){
		this.customerRepository = customerRepository;
	}

	public Customer(
		CustomerRepository customerRepository,
		String name, @NotNull LocalDate birthdate, String phoneNumber, String email) {
		this(customerRepository);
		
		this.id = UUID.randomUUID();
		this.name = name;
		this.birthdate = birthdate;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.createDateUtc = LocalDateTime.now(ZoneId.of("UTC"));
		this.isActive = true;
		
		addDomainEvent(new UserCreatedDomainEvent(this));
	}

	public static List<Customer> list(
		CustomerRepository customerRepository,
		Boolean onlyActive) {
		return customerRepository.findAll(onlyActive);
	}

	@SneakyThrows
	public static Customer load(
		CustomerRepository customerRepository,
		UUID id,
		Boolean onlyActive) {
		Customer customer = customerRepository.findById(id, onlyActive);

		if (customer == null) {
			throw new NotFoundException("Customer not found");
		}

		return customer;
	}

	public void save() {
		this.modifiedDateUtc = LocalDateTime.now(ZoneId.of("UTC"));
		customerRepository.save(this);
	}

	public void delete() {
		this.isActive = false;
		
		addDomainEvent(new UserRemovedDomainEvent(this));

		customerRepository.delete(this);
	}

	@SneakyThrows
	public void validate() {
		LocalDate now = LocalDate.now();
		int age = Period.between(birthdate, now).getYears();

		if (age < 18) {
			throw new Exception("You should have more than 18 years to register");
		}
	}
}

