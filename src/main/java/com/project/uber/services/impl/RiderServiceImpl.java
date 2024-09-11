package com.project.uber.services.impl;

import java.util.List;

import com.project.spring_mvc.exceptions.ResourceNotFoundException;
import com.project.uber.dto.*;
import com.project.uber.entities.*;
import com.project.uber.entities.enums.RideStatus;
import com.project.uber.services.DriverService;
import com.project.uber.services.RatingService;
import com.project.uber.services.RideService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.uber.entities.enums.RideRequestStatus;
import com.project.uber.repositories.RideRequestRepository;
import com.project.uber.repositories.RiderRepository;
import com.project.uber.services.RiderService;
import com.project.uber.strategies.StrategyManager;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService{

	private final ModelMapper mapper;
	private final RideRequestRepository rideRequestRepo;
	private final RiderRepository riderRepo;
	private final StrategyManager strategyManager;
	private final DriverService driverService;
	private final RideService rideService;
	private final RatingService ratingService;
	@Override
	@Transactional
    public RideRequestDTO requestRide(RideRequestDTO rideRequestDTO) {
		// TODO Auto-generated method stub
		Rider rider = getCurrentRider();
		RideRequest rideRequest = mapper.map(rideRequestDTO, RideRequest.class);
		rideRequest.setRideStatus(RideRequestStatus.PENDING);
		rideRequest.setRider(rider);
		double[] distanceAndFare = strategyManager.fareCalculationStrategy().calculateFare(rideRequest);
		double distance = distanceAndFare[0];
		double fare = distanceAndFare[1];
		rideRequest.setDistance(distance);
		rideRequest.setFare(fare);
		RideRequest savedRideRequest = rideRequestRepo.save(rideRequest);
	    List<Driver>drivers = strategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDrivers(rideRequest);
		return mapper.map(savedRideRequest,RideRequestDTO.class);
	}

	@Override
	public RideDTO cancelRide(Long rideId) {
		// TODO Auto-generated method stub
		Ride ride = rideService.getRideById(rideId);
		Rider rider = getCurrentRider();
		if(!rider.equals(ride.getRider()))
			throw new RuntimeException("The rider doesn't own this ride with id:"+rideId);
		if(!ride.getRideStatus().equals(RideStatus.CONFIRMED))
			throw new RuntimeException("Ride cannot be cancelled, invalid status:"+ride.getRideStatus());

	    RideDTO savedRide =	rideService.updateRideStatus(ride,RideStatus.CANCELLED);
		driverService.updateDriverAvailability(ride.getDriver(),true);

		return savedRide;
	}

	@Override
	public DriverDTO rateDriver(Long rideId, Double rating) {
		// TODO Auto-generated method stub
		Ride ride = rideService.getRideById(rideId);
		Rider rider = getCurrentRider();
		if(!rider.equals(ride.getRider()))
			throw new RuntimeException("rider not authorised to rate rider as he didn't accept this ride with id:"+rideId);
		if(!ride.getRideStatus().equals(RideStatus.ENDED))
			throw new RuntimeException("Ride not completed so driver cannot be rated. Ride has status:"+ride.getRideStatus());
		return ratingService.rateDriver(ride,rating);

	}

	@Override
	public RiderDTO getMyProfile() {
		// TODO Auto-generated method stub
		Rider currentRider = getCurrentRider();
		return mapper.map(currentRider,RiderDTO.class);
	}

	@Override
	public Page<RideDTO> getAllMyRides(Pageable pageable) {
		// TODO Auto-generated method stub
		Rider currentRider = getCurrentRider();
		return rideService.getAllRidesOfRider(currentRider, pageable).map(ride -> mapper.map(ride,RideDTO.class));
	}

	@Override
	public Rider createRider(User user) {
		// TODO Auto-generated method stub
		Rider rider = Rider.builder().user(user).rating(0.0).build();
		
		
		return riderRepo.save(rider);
	}

	@Override
	public Rider getCurrentRider() {
		// TODO Auto-generated method stub

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return riderRepo.findByUser(user).orElseThrow(()->new ResourceNotFoundException("rider not found with user id:"+user.getId()));

	}

	

}
