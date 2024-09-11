package com.project.uber.services;

import java.util.List;

import com.project.uber.dto.DriverDTO;
import com.project.uber.dto.RideDTO;
import com.project.uber.dto.RideRequestDTO;
import com.project.uber.dto.RiderDTO;
import com.project.uber.entities.Rider;
import com.project.uber.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RiderService {
	
	public RideRequestDTO requestRide(RideRequestDTO rideRequestDTO);
	public RideDTO cancelRide(Long rideId);
	public DriverDTO rateDriver(Long rideId,Double rating);
	public RiderDTO getMyProfile();
	public Page<RideDTO> getAllMyRides(Pageable pageable);
	public Rider createRider(User user);
	public Rider getCurrentRider();

}
