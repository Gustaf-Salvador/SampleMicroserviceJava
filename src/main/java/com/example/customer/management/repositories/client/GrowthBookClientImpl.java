package com.example.customer.management.repositories.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import growthbook.sdk.java.GBContext;
import growthbook.sdk.java.GrowthBook;

@Component
public class GrowthBookClientImpl {
	
	public GrowthBook growthBook;
	
	public GrowthBookClientImpl() throws URISyntaxException, IOException, InterruptedException {
		
		String featuresJson = getFeatures();
		
		GBContext context = GBContext.builder()
			    .featuresJson(featuresJson)
			    //.attributesJson(userAttributesObj.toString()) // Optional
			    //.trackingCallback(trackingCallback)
			    .build();
				
		growthBook = new GrowthBook(context);		
	}

	private String getFeatures() throws URISyntaxException, IOException, InterruptedException {
		
		URI featuresEndpoint = new URI("http://localhost:3100/api/features/sdk-iK0KP7E2cG23eZED");
		HttpRequest request = HttpRequest.newBuilder().uri(featuresEndpoint).GET().build();
		HttpResponse<String> response = HttpClient.newBuilder().build()
		    .send(request, HttpResponse.BodyHandlers.ofString());
		JSONObject jsonObject = new JSONObject(response.body());
		String featuresJson = jsonObject.get("features").toString();
		return featuresJson;
	}

}
