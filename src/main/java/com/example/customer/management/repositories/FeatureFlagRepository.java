package com.example.customer.management.repositories;

public interface FeatureFlagRepository {
	
	boolean isOn(String fetureFlag);
}
