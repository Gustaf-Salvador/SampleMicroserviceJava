package com.example.customer.management;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import io.mediator.core.Mediator;
import io.mediator.core.Registry;
import io.mediator.spring.SpringMediator;
import io.mediator.spring.SpringRegistry;


@SpringBootApplication
public class CustomerManagementApplication {

	private final ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(CustomerManagementApplication.class, args);
	}

	@Autowired
	public CustomerManagementApplication(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Bean
	public Registry registry() {
		return new SpringRegistry(applicationContext);
	}

	@Bean
	public Mediator mediator(Registry registry) {
		return new SpringMediator(registry);
	}
}
