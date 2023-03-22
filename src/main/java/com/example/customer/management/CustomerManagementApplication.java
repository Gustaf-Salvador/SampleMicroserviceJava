package com.example.customer.management;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import io.featurehub.client.EdgeFeatureHubConfig;
import io.featurehub.client.FeatureHubConfig;
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

	public CustomerManagementApplication(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

    @Bean
    Registry registry() {
		return new SpringRegistry(applicationContext);
	}

    @Bean
    Mediator mediator(Registry registry) {
		return new SpringMediator(registry);
	}
    
    @Bean
    FeatureHubConfig featureHubConfig() {
      String host = System.getenv("FEATUREHUB_EDGE_URL");

      if (host == null) {
        throw new RuntimeException("Unable to determine the host for FeatureHub");
      }

      String apiKey = System.getenv("FEATUREHUB_API_KEY");

      if (apiKey == null) {
        throw new RuntimeException("Unable to determine the API key for FeatureHub");
      }

      FeatureHubConfig config = new EdgeFeatureHubConfig(host, apiKey);
      config.init();

      return config;
    }
}
