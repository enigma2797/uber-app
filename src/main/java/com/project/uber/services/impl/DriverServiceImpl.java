package com.project.uber.services.impl;

import java.time.LocalDateTime;

import com.project.uber.exceptions.ResourceNotFoundException;
import com.project.uber.dto.DriverRideDTO;
import com.project.uber.entities.*;
import com.project.uber.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.uber.dto.DriverDTO;
import com.project.uber.dto.RideDTO;
import com.project.uber.dto.RiderDTO;
import com.project.uber.entities.enums.RideRequestStatus;
import com.project.uber.entities.enums.RideStatus;
import com.project.uber.repositories.DriverRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService{

	private final RideRequestService rideRequestService;
	private final RideService rideService;
	private final ModelMapper mapper;
	private final DriverRepository driverRepo;
	private final PaymentService paymentService;
	private final RatingService ratingService;

	@Override
	public DriverRideDTO cancelRide(Long rideId) {
		// TODO Auto-generated method stub
		Ride ride = rideService.getRideById(rideId);
		Driver driver = getCurrentDriver();
		if(!driver.equals(ride.getDriver()))
			throw new RuntimeException("Driver not authorised to cancel this ride");
		if(!ride.getRideStatus().equals(RideStatus.CONFIRMED))
			throw new RuntimeException("Ride cannot be cancelled, invalid status:"+ride.getRideStatus());

		rideService.updateRideStatus(ride,RideStatus.CANCELLED);
		updateDriverAvailability(driver,true);
		return mapper.map(ride, DriverRideDTO.class);
	}

	@Override
	public DriverRideDTO startRide(Long rideId,String otp) {
		// TODO Auto-generated method stub
		Ride ride = rideService.getRideById(rideId);
		Driver driver = getCurrentDriver();
		if(!driver.equals(ride.getDriver()))
			throw new RuntimeException("Driver not authorised to start this ride");
		if(!ride.getRideStatus().equals(RideStatus.CONFIRMED))
			throw new RuntimeException("Ride not in confirmed state so cannot be started "+ride.getRideStatus());
		if(!otp.equals(ride.getOtp()))
			throw new RuntimeException("OTP invalid "+otp);
		
		
		ride.setStartedAt(LocalDateTime.now());
		Payment payment = paymentService.createNewPayment(ride);
        ratingService.createNewRating(ride);

		return mapper.map(rideService.updateRideStatus(ride,RideStatus.ONGOING),DriverRideDTO.class);
}

	@Override
	public DriverRideDTO endRide(Long rideId) {
		// TODO Auto-generated method stub
		Ride ride = rideService.getRideById(rideId);
		Driver driver = getCurrentDriver();
		if(!driver.equals(ride.getDriver()))
			throw new RuntimeException("Driver not authorised to end this ride");
		if(!ride.getRideStatus().equals(RideStatus.ONGOING))
			throw new RuntimeException("Ride not in ongoing state so cannot be ended "+ride.getRideStatus());



		ride.setEndedAt(LocalDateTime.now());
		RideDTO savedRide = rideService.updateRideStatus(ride,RideStatus.ENDED);
		updateDriverAvailability(driver,true);
		paymentService.processPayment(ride);
        return savedRide;
	}

	@Override
	public RiderDTO rateRider(Long rideId, Double rating) {
		// TODO Auto-generated method stub

		Ride ride = rideService.getRideById(rideId);
		Driver driver = getCurrentDriver();
		if(!driver.equals(ride.getDriver()))
			throw new RuntimeException("Driver not authorised to rate rider as he didn't accept this ride with id:"+rideId);
		if(!ride.getRideStatus().equals(RideStatus.ENDED))
			throw new RuntimeException("Ride not completed so rider cannot be rated. Ride has status:"+ride.getRideStatus());
		return ratingService.rateRider(ride,rating);

	}

	@Override
	public DriverDTO getMyProfile() {
		// TODO Auto-generated method stub
		Driver currentDriver = getCurrentDriver();
		return mapper.map(currentDriver,DriverDTO.class);
	}

	@Override
	public Page<RideDTO> getAllMyRides(Pageable pageable) {
		// TODO Auto-generated method stub
		Driver currentDriver = getCurrentDriver();
		return rideService.getAllRidesOfDriver(currentDriver, pageable).map(ride -> mapper.map(ride,RideDTO.class));
	}

	@Override
	public DriverRideDTO acceptRide(Long rideRequestId) {
		// TODO Auto-generated method stub
		RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);
	    if(!rideRequest.getRideStatus().equals(RideRequestStatus.PENDING))
	    	throw new RuntimeException("Ride request cannot be accepted");
	   
	    Driver currentDriver = getCurrentDriver();
	    if(currentDriver!=null && !currentDriver.getAvailable())
	    	throw new RuntimeException("Driver not available");

	    Driver savedDriver = updateDriverAvailability(currentDriver,false);
	    Ride newRide = rideService.createNewRide(rideRequest, savedDriver);
	    
	    return mapper.map(newRide,DriverRideDTO.class);
	    	
	}

	@Override
	public Driver getCurrentDriver() {
		// TODO Auto-generated method stub
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return driverRepo.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("Driver not found with user id:"+user.getId()));
	}

	@Override
	public Driver updateDriverAvailability(Driver driver, boolean available) {
		driver.setAvailable(available);
		return driverRepo.save(driver);
	}

	@Override
	public Driver createNewDriver(Driver driver) {
		return driverRepo.save(driver);
	}


}
