package com.project.uber.services;

import com.project.uber.entities.RideRequest;

public interface RideRequestService {

	public RideRequest findRideRequestById(Long rideRequestId);

	public void update(RideRequest rideRequest);
}
