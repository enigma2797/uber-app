package com.project.uber.strategies;

import java.util.List;

import com.project.uber.dto.RideRequestDTO;
import com.project.uber.entities.Driver;
import com.project.uber.entities.RideRequest;

public interface DriverMatchingStrategy {

	public List<Driver> findMatchingDrivers(RideRequest rideRequest);
}
