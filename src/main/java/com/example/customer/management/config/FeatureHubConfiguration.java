package com.example.customer.management.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import io.featurehub.client.ClientContext;
import io.featurehub.client.FeatureHubConfig;

@Configuration
public class FeatureHubConfiguration {
	
	@Bean
	@Scope("request")
	ClientContext featureHubClient(FeatureHubConfig fhConfig, HttpServletRequest request) {
	    ClientContext fhClient = fhConfig.newContext();

	    if (request.getHeader("Authorization") != null) {
	      // you would always authenticate some other way, this is just an example
	      fhClient.userKey(request.getHeader("Authorization"));
	    }

	    return fhClient;
	  }


}
