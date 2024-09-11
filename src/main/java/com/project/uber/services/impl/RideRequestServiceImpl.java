package com.project.uber.services.impl;

import org.springframework.stereotype.Service;

import com.project.spring_mvc.exceptions.ResourceNotFoundException;
import com.project.uber.entities.RideRequest;
import com.project.uber.repositories.RideRequestRepository;
import com.project.uber.services.RideRequestService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService{

	private final RideRequestRepository rideRequestRepo;
	@Override
	public RideRequest findRideRequestById(Long rideRequestId) {
		// TODO Auto-generated method stub
		return rideRequestRepo.findById(rideRequestId).orElseThrow(()-> new ResourceNotFoundException("Ride request"
				+ "not found with id"+rideRequestId));
	}
	@Override
	public void update(RideRequest rideRequest) {
		
		rideRequestRepo.findById(rideRequest.getId()).orElseThrow(()-> new ResourceNotFoundException("Ride request"
				+ "not found with id"+rideRequest.getId()));
		rideRequestRepo.save(rideRequest);
		
	}

}
