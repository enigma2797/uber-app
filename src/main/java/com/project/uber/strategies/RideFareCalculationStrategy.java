package com.project.uber.strategies;

import com.project.uber.entities.RideRequest;

public interface RideFareCalculationStrategy {
	
	static final double RIDE_FARE_MULTIPLIER = 10;

	double[] calculateFare(RideRequest rideRequest);
}
