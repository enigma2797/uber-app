package com.project.uber.controllers;

import com.project.uber.dto.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.project.uber.services.DriverService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/driver")
@Secured("ROLE_DRIVER")
public class DriverController {
	
	private final DriverService driverService;
	@PostMapping(path="/acceptRide/{rideRequestId}")
	public ResponseEntity<DriverRideDTO> acceptRide(@PathVariable Long rideRequestId)
	{
		return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
	}
	
	@PostMapping(path="/startRide/{rideId}")
	public ResponseEntity<DriverRideDTO>startRide(@PathVariable Long rideId, @RequestBody RideStartDTO rideStartDTO )
	{
	
		return ResponseEntity.ok(driverService.startRide(rideId,rideStartDTO.getOtp()));
	}

	@PostMapping(path="/cancelRide/{rideId}")
	public ResponseEntity<DriverRideDTO>cancelRide(@PathVariable Long rideId)
	{

		return ResponseEntity.ok(driverService.cancelRide(rideId));
	}

	@PostMapping(path="/endRide/{rideId}")
	public ResponseEntity<DriverRideDTO>endRide(@PathVariable Long rideId)
	{

		return ResponseEntity.ok(driverService.endRide(rideId));
	}

	@PostMapping(path="/rateRider")
	public ResponseEntity<RiderDTO> rateRider(@RequestBody RateDTO rateDTO)
	{
		return ResponseEntity.ok(driverService.rateRider(rateDTO.getRideId(),rateDTO.getRating()));
	}

	@GetMapping(path="/getMyProfile")
	public ResponseEntity<DriverDTO>getMyProfile()
	{
		return ResponseEntity.ok(driverService.getMyProfile());
	}

	@GetMapping(path="/getAllMyRides")
	public ResponseEntity<List<RideDTO>>getAllMyRides(@RequestParam(defaultValue = "1",required = false) int pageNo, @RequestParam(defaultValue = "10",required = false) int pageSize)
	{
		Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(Sort.Direction.DESC,"createdTime","id"));
		return ResponseEntity.ok(driverService.getAllMyRides(pageable).getContent());
	}



}
