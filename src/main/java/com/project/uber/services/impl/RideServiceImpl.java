package com.project.uber.services.impl;

import java.util.Random;

import com.project.uber.exceptions.ResourceNotFoundException;
import com.project.uber.entities.Rider;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.uber.dto.RideDTO;
import com.project.uber.entities.Driver;
import com.project.uber.entities.Ride;
import com.project.uber.entities.RideRequest;
import com.project.uber.entities.enums.RideRequestStatus;
import com.project.uber.entities.enums.RideStatus;
import com.project.uber.repositories.RideRepository;
import com.project.uber.services.RideRequestService;
import com.project.uber.services.RideService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService{

	private final RideRepository rideRepo;
	private final ModelMapper mapper;
	private final RideRequestService rideRequestService;
	@Override
	public Ride getRideById(Long rideId) {
		// TODO Auto-generated method stub
		return rideRepo.findById(rideId).orElseThrow(()-> new ResourceNotFoundException("Ride doesn't exist"));
	}

	@Override
	public Ride createNewRide(RideRequest rideRequest, Driver driver) {
		// TODO Auto-generated method stub
		rideRequest.setRideStatus(RideRequestStatus.BOOKED);
		Ride ride = mapper.map(rideRequest, Ride.class);
		ride.setDriver(driver);
		ride.setRideStatus(RideStatus.CONFIRMED);
		ride.setOtp(generateOTP());
		ride.setId(null);
		rideRequestService.update(rideRequest);
		return rideRepo.save(ride);
	}

	@Override
	public RideDTO updateRideStatus(Ride ride, RideStatus status) {
		// TODO Auto-generated method stub
		ride.setRideStatus(status);
		return mapper.map(rideRepo.save(ride),RideDTO.class);
	}

	@Override
	public Page<Ride> getAllRidesOfRider(Rider rider, Pageable pageable) {
		// TODO Auto-generated method stub
		return rideRepo.findByRider(rider,pageable);
	}

	@Override
	public Page<Ride> getAllRidesOfDriver(Driver driver, Pageable pageable) {
		// TODO Auto-generated method stub
		return rideRepo.findByDriver(driver,pageable);
	}
	
	private String generateOTP()
	{
		Random random = new Random();
		int randomNumber = 1000 + random.nextInt(9000);

        // Convert the number to a string
        return String.valueOf(randomNumber);
	}

}
