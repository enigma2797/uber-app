package com.project.uber.strategies.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.uber.entities.Driver;
import com.project.uber.entities.RideRequest;
import com.project.uber.repositories.DriverRepository;
import com.project.uber.strategies.DriverMatchingStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverMatchingHighestRatedDriverStrategyImpl implements DriverMatchingStrategy{

	private final DriverRepository driverRepo;
	@Override
	public List<Driver> findMatchingDrivers(RideRequest rideRequest) {
		// TODO Auto-generated method stub
		return driverRepo.findNearbyTopRatedDrivers(rideRequest.getPickupLocation());
	}

}
