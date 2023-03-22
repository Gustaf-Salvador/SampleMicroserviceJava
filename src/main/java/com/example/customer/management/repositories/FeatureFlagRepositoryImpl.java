package com.example.customer.management.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.customer.management.repositories.client.GrowthBookClientImpl;

@Component
public class FeatureFlagRepositoryImpl implements FeatureFlagRepository {

	@Autowired
	private GrowthBookClientImpl growthBook;

	@Override
	public boolean isOn(String fetureFlag) {
		return growthBook.growthBook.getFeatureValue(fetureFlag, true);
	}
}
