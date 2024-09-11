package com.project.uber.strategies.impl;

import org.springframework.stereotype.Service;


import com.project.uber.entities.RideRequest;
import com.project.uber.services.DistanceService;
import com.project.uber.strategies.RideFareCalculationStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {

	private final DistanceService distanceService;
	@Override
	public double[] calculateFare(RideRequest rideRequest) {
		// TODO Auto-generated method stub
		double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(), rideRequest.getDropOffLocation());
		
		return new double[]{distance, distance * RIDE_FARE_MULTIPLIER};
	}

}
