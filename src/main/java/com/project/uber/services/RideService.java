package com.project.uber.services;

import com.project.uber.dto.DriverRideDTO;
import com.project.uber.entities.Rider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.project.uber.dto.RideDTO;
import com.project.uber.dto.RideRequestDTO;
import com.project.uber.entities.Driver;
import com.project.uber.entities.Ride;
import com.project.uber.entities.RideRequest;
import com.project.uber.entities.enums.RideStatus;
import org.springframework.data.domain.Pageable;

public interface RideService {
	
	public Ride getRideById(Long rideId);
	Ride createNewRide(RideRequest rideRequest,Driver driver);
	RideDTO updateRideStatus(Ride ride, RideStatus status);
	Page<Ride>getAllRidesOfRider(Rider rider, Pageable pageable);
	Page<Ride>getAllRidesOfDriver(Driver driver, Pageable pageable);

}
