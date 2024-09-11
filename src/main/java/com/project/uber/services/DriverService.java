package com.project.uber.services;

import java.util.List;

import com.project.uber.dto.DriverDTO;
import com.project.uber.dto.DriverRideDTO;
import com.project.uber.dto.RideDTO;
import com.project.uber.dto.RiderDTO;
import com.project.uber.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface DriverService {

	public DriverRideDTO acceptRide(Long rideRequestId);
	public DriverRideDTO cancelRide(Long rideId);
	public DriverRideDTO startRide(Long rideId, String otp);
	public DriverRideDTO endRide(Long rideId);
	public RiderDTO rateRider(Long rideId,Double rating);
	public DriverDTO getMyProfile();
	public Page<RideDTO> getAllMyRides(Pageable pageable);
	public Driver getCurrentDriver();
	public Driver updateDriverAvailability(Driver driver,boolean available);
	public Driver createNewDriver(Driver driver);
}
